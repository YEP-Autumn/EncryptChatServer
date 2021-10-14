package com.laplace.mapper;

import com.laplace.bean.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface UserMapper {
    @Select("SELECT * FROM USER_INFO WHERE USER_ID = #{userId};")
    User getUser(@Param("userId") int id);

    void insert(Map map);

    @Select("SELECT M_KEY FROM USER_INFO WHERE USER_ID = #{userId};")
    String getKeyByUserId(@Param("userId") int userId);

    @Delete("DELETE FROM USER_INFO WHERE M_KEY = #{mKey};")
    boolean deleteUserByKey(@Param("mKey") String mKey);


    @Delete("DELETE FROM USER_INFO WHERE USER_ID = #{userId};")
    boolean deleteUserById(@Param("userId") int userId);

    @Select("SELECT USER_ID FROM USER_INFO WHERE M_KEY = #{mKey};")
    int getUserIdByKey(@Param("mKey") String mKey);

    @Select("SELECT FRIEND_ID FROM USER_INFO WHERE M_KEY = #{mKey};")
    int getFriendIdByKey(@Param("mKey") String mKey);

    @Update("UPDATE USER_INFO SET FRIEND_STATUS=#{friendStatus} WHERE FRIEND_ID = #{friendId};")
    int updateStatus(@Param("friendId") int userId, @Param("friendStatus") boolean friendStatus);

    @Select("SELECT FRIEND_STATUS FROM USER_INFO WHERE M_KEY = #{mKey};")
    boolean getFriendStatueByKey(@Param("mKey") String mKey);


    @Select("SELECT USER_ID FROM USER_INFO WHERE FRIEND_ID = #{friendId};")
    List<Integer> getUsersIdByFriendId(@Param("friendId") int userId);

}
