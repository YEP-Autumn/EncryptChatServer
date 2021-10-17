package com.laplace;

import com.laplace.server.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

@SpringBootApplication
@MapperScan("com.laplace.mapper")
public class EncryptChatServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EncryptChatServerApplication.class, args);
        WebSocketServer webSocketServer = run.getBean(WebSocketServer.class);
        webSocketServer.run();

    }

}
