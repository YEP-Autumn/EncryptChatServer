package com.laplace.core.scheduled;

import com.google.gson.Gson;
import com.laplace.core.bean.Signalman;
import com.laplace.core.bean.YEP;
import org.java_websocket.WebSocket;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @Author: YEP
 * @CreateDate: 2021/10/18 15:48
 * @Info:
 * @Email:
 */
@Component
public class CleanScheduled {

    @Resource
    HashMap<String, WebSocket> keySocket;

    private Gson gson = new Gson();

    @Resource
    private List<String> offDevices;

    @Scheduled(fixedDelay = 1000 * 60 * 60, initialDelay = 60 * 1000 * 15)
    public void send() {
        if (offDevices.size() == 0) return;
        if (keySocket.keySet().size() == 0) return;
        keySocket.forEach(new BiConsumer<String, WebSocket>() {
            @Override
            public void accept(String s, WebSocket s2) {
                offDevices.add(s);
                s2.send(getAutumn());
            }
        });
    }


    @Scheduled(fixedDelay = 1000 * 60 * 60, initialDelay = 60 * 1000 * 30)
    public void sendSecond() {
        if (offDevices.size() == 0) return;
        if (keySocket.keySet().size() == 0) return;
        offDevices.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                WebSocket socket = keySocket.get(s);
                socket.send(getAutumn());
            }
        });
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60, initialDelay = 60 * 1000 * 35)
    public void remove() {
        if (offDevices.size() == 0) return;
        if (keySocket.keySet().size() == 0) return;
        offDevices.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                WebSocket socket = keySocket.get(s);
                socket.close();
            }
        });
    }

    private String getAutumn() {
        Signalman signalman = new Signalman("TOKEN");
        YEP autumn = new YEP();
        autumn.SIGNATURE = gson.toJson(signalman);
        return gson.toJson(autumn);
    }

}
