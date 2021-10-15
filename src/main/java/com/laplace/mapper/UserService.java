//package com.laplace.mapper;
//
//import com.laplace.bean.pojo.User;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.sql.Timestamp;
//import java.util.List;
//
///**
// * @Author: YEP
// * @CreateDate: 2021/10/15 11:14
// * @Info:
// * @Email:
// */
//@Component
//public class UserService {
//
//    @Resource
//    UserMapper userMapper;
//
//
//    public void insert(User user) {
//        userMapper.insert(user);
//    }
//
//    public User getUserById(long userId) {
//        return userMapper.getUserById(userId);
//    }
//
//    public User getUserByKey(String mKey) {
//        return userMapper.getUserByKey(mKey);
//    }
//
//    public List<User> getUsersByFriendId(long friendId) {
//        return userMapper.getUsersByFriendId(friendId);
//    }
//
//    public int deleteUserById(long userId) {
//        return userMapper.deleteUserById(userId);
//    }
//
//    public int deleteUserByKey(String mKey) {
//        return userMapper.deleteUserByKey(mKey);
//    }
//
//    public int updateFriendStatus(long friendId, boolean friendStatus) {
//        return updateFriendStatus(friendId, friendStatus);
//    }
//
//    public String getUserKeyById(long userId) {
//        User user = getUserById(userId);
//        return user.getMKey();
//    }
//
//    public long getUserFriendIdById(long userId) {
//        User user = getUserById(userId);
//        return user.getFriendId();
//    }
//
//    public Class<T> getUserFriendStatusById(long userId,Class<?> c){
//        User user = getUserById(userId);
//        return user.isFriendStatus();
//    }
//
//    public Timestamp getUserConnectTimeById(long userId){
//
//    }
//
//
//
////    public S getUserByKey(String mKey) {
////        return userMapper.getUserByKey(mKey);
////    }
//}
