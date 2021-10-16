package com.laplace;

import com.laplace.bean.pojo.User;
import com.laplace.mapper.UserMapper;
import com.laplace.server.WebSocketClient;
import com.laplace.server.WebSocketServer;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;


@SpringBootTest
class EncryptChatServerApplicationTests {

    @Resource
    UserMapper userMapper;

    @Resource
    WebSocketServer webSocketServer;


    @Test
    public void server() throws URISyntaxException, InterruptedException {
        webSocketServer.run();
        Thread.sleep(3000);
//        WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://localhost:8788"),new HashMap<>());
//        webSocketClient.connect();
    }


    @Test
    void contextLoads() {
//        userMapper.insert(new User(13222156L, "124223", 456L,new Timestamp(System.currentTimeMillis())));
        User userById = userMapper.getUserById(123);
        System.out.println(userById);
    }
}
