package com.laplace.server;

import com.laplace.bean.Signalman;
import com.laplace.bean.YEP;
import com.laplace.server.manager.MessageManagement;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.handshake.*;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;


public class WebSocketServer extends org.java_websocket.server.WebSocketServer {

    @Resource
    MessageManagement management;

    public WebSocketServer(InetSocketAddress address) {
        super(address);
    }


    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        boolean isSecurity = management.SecurityVerification(webSocket,clientHandshake);
        if (!isSecurity)webSocket.close();
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {

    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        management.dispatcherMessage(webSocket,s);

    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }

}
