package com.laplace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.laplace.mapper")
public class EncryptChatServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EncryptChatServerApplication.class, args);
    }

}
