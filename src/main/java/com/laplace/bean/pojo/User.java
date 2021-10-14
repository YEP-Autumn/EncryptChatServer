package com.laplace.bean.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String mKey;
    private String friendId;
    private Timestamp connectTime;

}
