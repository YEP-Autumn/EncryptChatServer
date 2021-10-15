package com.laplace.server.manager;

import com.google.gson.Gson;
import com.laplace.EncryptionUtils.AHelper;
import com.laplace.bean.Signalman;
import com.laplace.bean.YEP;
import com.laplace.bean.pojo.Chat;
import com.laplace.bean.pojo.User;
import com.laplace.mapper.MsgMapper;
import com.laplace.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @Author: YEP
 * @CreateDate: 2021/10/15 11:28
 * @Info:
 * @Email:
 */
@Component
public class MessageManagement {


    @Resource
    private UserMapper userMapper;

    @Resource
    private MsgMapper msgMapper;

    private Logger logger = Logger.getLogger(this.getClass());

    private Gson gson = new Gson();

    private Map<String, WebSocket> keySocket = new HashMap<>();


    /**
     * 登录校验
     *
     * @param webSocket
     * @param clientHandshake
     * @return
     */
    public boolean SecurityVerification(WebSocket webSocket, ClientHandshake clientHandshake) {
        try {
            String sign = clientHandshake.getFieldValue("sign");
            if ("".equals(sign)) {
                logger.warn("存在客户端不带sign发送socket");
                return false;
            }

            String time = clientHandshake.getFieldValue("TIME");
            if ("".equals(time) || System.currentTimeMillis() - Long.parseLong(time) > 60 * 1000) {
                logger.warn("存在客户端不带TIME发送socket或者时间过期");
                return false;
            }
            String friendId = clientHandshake.getFieldValue("friendId");
            if ("".equals(friendId)) {
                logger.warn("存在客户端不带friendId发送socket");
                return false;
            }

            String userId = clientHandshake.getFieldValue("userId");
            if ("".equals(userId)) {
                logger.warn("存在客户端不带userId发送socket请求");
                return false;
            }

            long userIdL = Long.parseLong(userId);
            long friendIdL = Long.parseLong(friendId);
            long timeL = Long.parseLong(time);
            long signL = (userIdL + friendIdL) * timeL;

            // 签名校验
            String signature = AHelper.toSecret("YEP", String.valueOf(signL));
            if (sign.equals(signature)) {
                // 校验通过记录用户
                Map<String, Object> map = new HashMap<>();
                map.put("userId", userIdL);
                map.put("friendId", friendIdL);
                map.put("mKey", clientHandshake.getFieldValue("Sec-WebSocket-Key"));
                saveUserInfo(webSocket, map);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.warn(e);
            return false;
        }
    }


    /**
     * 保存用户信息
     *
     * @param webSocket
     * @param map
     */
    protected void saveUserInfo(WebSocket webSocket, Map<String, Object> map) {
        long userId = (long) map.get("userId");
        String mKey = (String) map.get("mKey");
        long friendId = (long) map.get("friendId");
        // 获取好友在线信息
        boolean friendStatus = userMapper.getUserById(userId) == null;
        User user = new User(userId, mKey, friendId, new Timestamp(System.currentTimeMillis()), friendStatus);
        // 存储数据
        userMapper.insert(user);
        keySocket.put(mKey, webSocket);
        // 获取不在线时接收到数据
        List<Chat> msg = msgMapper.getMsg(userId, friendId);
        // 销毁数据
        msgMapper.delete(userId);
        // 向用户发送欢迎消息
        sayHello(webSocket, friendStatus, msg);
        // 处理用户状态
        updateUserStatus(userId, "online");
    }

    private void sayHello(WebSocket webSocket, boolean friendStatus, List<Chat> messages) {
        String mode = "WELCOME_" + String.valueOf(friendStatus).toUpperCase(Locale.ROOT);
        Signalman signalman = new Signalman(mode);
        List<String> messageList = new ArrayList<>();
        if (messages.size() != 0) {
            messages.forEach(chat -> messageList.add(chat.getMsg()));
        }
        signalman.setMessages(messageList);
        String s = gson.toJson(signalman);
        YEP autumn = new YEP();
        autumn.SIGNATURE = AHelper.toSecret(getKey(webSocket), s);
        webSocket.send(gson.toJson(autumn));
    }

    /***
     * 用户状态变更
     * @param userId
     * @param status
     */
    protected void updateUserStatus(long userId, String status) {
        List<User> usersByFriendId = userMapper.getUsersByFriendId(userId);
        // 修改数据库中自己的状态并向所有好友发送状态变更信息
        if ("online".equals(status)) {
            //修改数据库
            userMapper.updateFriendStatus(userId, true);
            //发送状态变更信息
            usersByFriendId.forEach(user -> {
                WebSocket socket = keySocket.get(user.getMKey());
                socket.send(gson.toJson(new YEP("ONLINE")));
            });
            return;
        }
        if ("offline".equals(status)) {
            userMapper.updateFriendStatus(userId, false);
            usersByFriendId.forEach(user -> {
                WebSocket socket = keySocket.get(user.getMKey());
                socket.send(gson.toJson(new YEP("OFFLINE")));
            });
        }

    }

    public void dispatcherMessage(WebSocket webSocket, String s) {

        // 消息提取加安全校验
        Signalman signalman = getSignalman(webSocket, s);
        if (signalman == null) return;

        if ("SIGN".equals(signalman.getMODE())) {
            forwardText(webSocket, s, signalman);
        }

    }

    private void forwardText(WebSocket webSocket, String s, Signalman signalman) {
        //判断信息的接收方是否在线
        long targetId = signalman.getTargetId();
        User userTarget = userMapper.getUserById(targetId);
        if (userTarget == null) {
            webSocket.send(gson.toJson(new YEP("RECEIVED")));
            //记录到数据库
            saveData(signalman);
            return;
        }
        WebSocket socket = keySocket.get(userTarget.getMKey());
        socket.send(s);
    }

    private void saveData(Signalman signalman) {
        long userId = userMapper.getUserByKey(signalman.getKey()).getUserId();
        String message = signalman.getMessage();
        long friendId = signalman.getTargetId();
        msgMapper.insert(new Chat(userId, friendId, message, new Timestamp(System.currentTimeMillis())));
    }

    /**
     * 消息提取
     *
     * @param webSocket
     * @param s
     * @return
     */
    private Signalman getSignalman(WebSocket webSocket, String s) {
        YEP autumn = gson.fromJson(s, YEP.class);
        if (autumn.MODE != null) {
            logger.error("getSignalman消息提取时：消息格式错误(多余字段MODE)");
            return null;
        }
        if (autumn.SIGNATURE == null) {
            logger.error("getSignalman消息提取时：发现空消息");
            return null;
        }
        Signalman signalman = gson.fromJson(autumn.SIGNATURE, Signalman.class);
        //验证消息来源可靠性
        if (!getKey(webSocket).equals(signalman.getKey()) || signalman.getKey() == null) {
            webSocket.close();
            logger.warn("消息来源不可靠,可能有人伪造连接");
        }
        return signalman;
    }

    /**
     * 通过socket获取key值
     *
     * @param webSocket
     * @return
     */
    private String getKey(WebSocket webSocket) {
        final String[] key = new String[1];
        keySocket.forEach(new BiConsumer<String, WebSocket>() {
            @Override
            public void accept(String s, WebSocket w) {
                if (w.equals(webSocket)) key[0] = s;
            }
        });
        return key[0];
    }

}
