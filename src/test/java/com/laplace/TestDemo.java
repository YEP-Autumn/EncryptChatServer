package com.laplace;

import com.laplace.EncryptionUtils.AHelper;
import com.laplace.bean.pojo.Chat;
import com.laplace.server.WebSocketClient;
import com.laplace.server.WebSocketServer;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 * @Author: YEP
 * @CreateDate: 2021/10/15 12:10
 * @Info:
 * @Email:
 */
public class TestDemo {


    WebSocketServer webSocketServer = new WebSocketServer(new InetSocketAddress(8788));


    @Test
    public void server() {
        webSocketServer.run();
    }

    public static void main(String[] args) throws URISyntaxException {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", "2222");
        map.put("friendId", "654321");
        long time = System.currentTimeMillis();
        map.put("time", String.valueOf(time));
        String sign = String.valueOf(((long) 2222 + (long) 654321) * time);
        map.put("sign", AHelper.toSecret("YEP", sign));
        WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://127.0.0.1:8083"), map);
        System.out.println("-----");
        webSocketClient.connect();
    }

//
}
