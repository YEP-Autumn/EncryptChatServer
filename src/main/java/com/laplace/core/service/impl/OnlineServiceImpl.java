package com.laplace.core.service.impl;

import com.laplace.core.mapper.UserMapper;
import com.laplace.core.service.OnlineService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/16 10:20
 * @Info:
 * @Email:
 */
@Component
public class OnlineServiceImpl implements OnlineService {
    @Resource
    UserMapper userMapper;

    @Override
    public boolean isOnline(long userId) {
        if (userId == 0) {
            return true;
        }
        return userMapper.getUserById(userId) != null;
    }
}
