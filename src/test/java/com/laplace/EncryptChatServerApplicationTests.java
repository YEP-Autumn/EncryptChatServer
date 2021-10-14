package com.laplace;

import com.laplace.bean.pojo.User;
import com.laplace.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class EncryptChatServerApplicationTests {

    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = userMapper.getUser(231);
        System.out.println(user);
    }

}
