package com.laplace;

import com.laplace.bean.pojo.Chat;
import com.laplace.server.WebSocketClient;
import com.laplace.server.WebSocketServer;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * @Author: YEP
 * @CreateDate: 2021/10/15 12:10
 * @Info:
 * @Email:
 */
public class TestDemo {

//
//    WebSocketServer webSocketServer = new WebSocketServer(new InetSocketAddress(8788));
//
//
//    @Test
//    public void server() {
//        webSocketServer.run();
//    }
//
//
//    public static void main(String[] args) throws URISyntaxException {
//        WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://localhost:8788"),new HashMap<>());
//        webSocketClient.connect();
//    }

    public static void main(String[] args) {
        Chat chat = new Chat();
        System.out.println(chat.getUserId());
    }
}
