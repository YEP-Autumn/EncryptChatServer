package com.laplace.bean;

import com.laplace.bean.pojo.Chat;
import lombok.Data;

import java.util.List;

@Data
public class Signalman {

    /**
     * 消息模式
     * WELCOME_TRUE: 欢迎模式，用户第一次上线时发送
     * WELCOME_FALSE: 欢迎模式，用户第一次上线时发送
     * ONLINE: 好友上线
     * OFFLINE: 好友下线
     * RECEIVED: 用户不在线，记录消息
     * SIGN: 加密数据
     */
    private String MODE;

    private String message;

    private List<String> messages;

    private long targetId;

    public Signalman(String MODE) {
        this.MODE = MODE;
    }

    /**
     * 标识消息来源
     * 用于防止伪造请求
     */
    private final static String SOURCE = "SERVER";
    private String key;

}
