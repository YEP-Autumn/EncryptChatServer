package com.laplace.config;


import io.minio.MinioClient;
import org.java_websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.SqlProvider;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Configuration
public class MyConfiguration {

    @Resource
    private MinioConfig MinioConfig;

    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(MinioConfig.getEndpoint())
                .credentials(MinioConfig.getAccessKey(), MinioConfig.getSecretKey())
                .build();
    }

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
    public String getStr() {
        return "";
    }

}
