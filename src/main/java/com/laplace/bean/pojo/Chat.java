package com.laplace.bean.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
public class Chat {

    private int friendId;
    private int userId;
    private String msg;
    private Timestamp sendTime;
    private boolean friendStatus;
    private boolean sign = true;

    public Chat(String msg, boolean sign) {
        this.msg = msg;
        this.sign = sign;
    }

    public Chat(String msg) {
        this.msg = msg;
    }
}
