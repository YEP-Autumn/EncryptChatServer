package com.laplace;

import com.laplace.core.server.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan("com.laplace.core.mapper")
@EnableScheduling
public class EncryptChatServerApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EncryptChatServerApplication.class, args);
        WebSocketServer webSocketServer = run.getBean(WebSocketServer.class);
        webSocketServer.run();
    }


}
