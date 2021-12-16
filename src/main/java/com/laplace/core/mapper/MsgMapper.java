package com.laplace.core.mapper;

import com.laplace.core.bean.pojo.Chat;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @Author: YEP
 * @CreateDate: 2021/10/15 17:52
 * @Info:
 * @Email:
 */
@Mapper
@Component
public interface MsgMapper {

    @Insert("INSERT INTO MSG_TABLE (FRIEND_ID,USER_ID,MSG,SEND_TIME) VALUES (#{friendId}, #{userId}, #{msg}, #{sendTime});")
    void insert(Chat chat);

    @Delete("DELETE FROM MSG_TABLE WHERE USER_ID = #{userId} AND FRIEND_ID = #{friendId};")
    int delete(@Param("userId") long userId, @Param("friendId") long friendId);

    @Select("SELECT * FROM MSG_TABLE WHERE USER_ID = #{friendId} AND FRIEND_ID = #{userId};")
    List<Chat> getMsg(long userId, long friendId);

}
