package com.laplace.bean.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
public class Chat {
    private long userId;
    private long friendId;
    private String msg;
    private Timestamp sendTime;
}
