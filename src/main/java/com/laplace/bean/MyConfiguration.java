package com.laplace.bean;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class MyConfiguration {

    @Bean
    public InetSocketAddress getInetSocketAddress() {
        return new InetSocketAddress(8083);
    }

}
