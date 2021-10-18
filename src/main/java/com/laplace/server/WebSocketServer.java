package com.laplace.server;

import com.laplace.server.manager.MessageManagement;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;


@Component
public class WebSocketServer extends org.java_websocket.server.WebSocketServer {

    @Resource
    private MessageManagement management;


    public WebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        boolean isSecurityVerification = management.SecurityVerification(webSocket, clientHandshake);
        if(!isSecurityVerification)webSocket.close();

    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        management.eliminateUserInfo(webSocket);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        management.dispatcherMessage(webSocket, s);

    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {
    }

}
