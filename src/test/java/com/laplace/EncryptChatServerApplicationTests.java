package com.laplace;

import com.laplace.bean.pojo.User;
import com.laplace.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;


@SpringBootTest
class EncryptChatServerApplicationTests {

    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
//        userMapper.insert(new User(13222156L, "124223", 456L,new Timestamp(System.currentTimeMillis())));
        User userById = userMapper.getUserById(123);
        System.out.println(userById);
    }
}
