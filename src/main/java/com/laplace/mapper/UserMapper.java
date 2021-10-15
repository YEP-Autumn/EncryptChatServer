package com.laplace.mapper;

import com.laplace.bean.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {


    @Insert("INSERT INTO USER_INFO (USER_ID,M_KEY,FRIEND_ID,CONNECT_TIME,FRIEND_STATUS) VALUES (#{userId}, #{mKey}, #{friendId}, #{connectTime}, #{friendStatus});")
    void insert(User user);

    @Select("SELECT * FROM USER_INFO WHERE USER_ID = #{userId};")
    User getUserById(long userId);

    @Select("SELECT * FROM USER_INFO WHERE M_KEY = #{mKey};")
    User getUserByKey(String mKey);


    @Select("SELECT * FROM USER_INFO WHERE FRIEND_ID = #{friendId};")
    List<User> getUsersByFriendId(long friendId);

    @Delete("DELETE FROM USER_INFO WHERE USER_ID = #{userId};")
    int deleteUserById(long userId);


    @Delete("DELETE FROM USER_INFO WHERE M_KEY = #{mKey};")
    int deleteUserByKey(String mKey);

    /**
     * 更新某位用户的状态
     * @param userId
     * @param friendStatus
     * @return
     */
    @Update("UPDATE USER_INFO SET FRIEND_STATUS = #{friendStatus} WHERE FRIEND_ID = #{friendId};")
    int updateFriendStatus(@Param("friendId") long userId, boolean friendStatus);

}
