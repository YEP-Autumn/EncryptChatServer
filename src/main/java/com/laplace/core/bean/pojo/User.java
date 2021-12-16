package com.laplace.core.bean.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long userId;
    private String mKey;
    private long friendId;
    private Timestamp connectTime;
    private boolean friendStatus;
}
