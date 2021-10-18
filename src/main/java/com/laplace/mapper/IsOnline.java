package com.laplace.mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @Author: YEP
 * @CreateDate: 2021/10/18 13:30
 * @Info:
 * @Email:
 */
@Component
public class IsOnline {

    @Resource
    UserMapper userMapper;

    public boolean isOnline(long userId) {
        if (userId == 0) {
            return true;
        }
        return userMapper.getUserById(userId) != null;
    }
}
