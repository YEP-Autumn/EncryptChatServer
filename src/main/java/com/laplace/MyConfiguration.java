package com.laplace;


import org.java_websocket.WebSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.print.DocFlavor;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class MyConfiguration {

    @Bean
    public InetSocketAddress getInetSocketAddress() {
        return new InetSocketAddress(8788);
    }

    @Bean
    public HashMap<String, WebSocket> getHashMap() {
        return new HashMap<String, WebSocket>();
    }

    @Bean
    public List<String> getKeyList() {
        return new ArrayList<>();
    }

    @Bean
    public String getStr(){
        return "";
    }

}
