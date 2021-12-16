package com.laplace.core.server;


import com.google.gson.Gson;
import com.laplace.core.bean.Signalman;
import com.laplace.core.bean.YEP;
import org.java_websocket.WebSocket;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @Author: YEP
 * @Info:
 * @Email:
 */


public class WebSocketClient extends org.java_websocket.client.WebSocketClient {

    private Gson gson = new Gson();

    public WebSocketClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }


    @Override
    public void send(String text) {
        /*
         消息加密步骤：
            1.先构造一个Signalman,将消息MODE和消息(使用自己的密钥加密后的消息)封装进Signalman
            2.将Signalman转化为json格式数据
            3.将json格式数据使用key值加密
            4.创建一个YEP对象，将加密后的数据赋值给YEP.SIGNATURE
            5.将YEP对象转化为json格式发送出去
        动作传递过程：
            1.创建一个YEP对象，将动作直接交给YEP.MODE
            2.将YEP对象转化为json格式数据发送出去

         */
        YEP autumn = new YEP("SIGN");
        Signalman sign = new Signalman("SIGN");
        sign.setMessage(text);
        autumn.SIGNATURE = gson.toJson(sign);
        text = gson.toJson(autumn);
        super.send(text);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

    }

    @Override
    public void onWebsocketHandshakeReceivedAsClient(WebSocket conn, ClientHandshake request, ServerHandshake response) throws InvalidDataException {
        super.onWebsocketHandshakeReceivedAsClient(conn, request, response);
        System.out.println("Received");
        response.iterateHttpFields().forEachRemaining(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s + ":" + request.getFieldValue(s));
            }
        });
    }

    @Override
    public void onWebsocketHandshakeSentAsClient(WebSocket conn, ClientHandshake request) throws InvalidDataException {
        super.onWebsocketHandshakeSentAsClient(conn, request);
        System.out.println("Sent");
        request.iterateHttpFields().forEachRemaining(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s + ":" + request.getFieldValue(s));
            }
        });
    }

    @Override
    public void onMessage(String s) {
        System.out.println(s);

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("close: " + " " + i + " " + s + " " + b);
    }

    @Override
    public void onError(Exception e) {

    }
}
