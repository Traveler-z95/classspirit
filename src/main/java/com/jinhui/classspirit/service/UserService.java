package com.jinhui.classspirit.service;

import com.jinhui.classspirit.mapper.UserMapper;
import com.jinhui.classspirit.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据班级Id获取教师信息
     * @param classId
     * @return
     */
    public List<User> getTeacherByClassId(String classId) {
        return userMapper.getTeacherByClassId(classId);
    }

    /**
     * 获取用户信息（根据userId）
     * @param userId
     * @return
     */
    public User getUserInfoByUserId(String userId) {
        return userMapper.getUserInfoByUserId(userId);
    }

    /**
     * 用户信息修改
     * @param user
     * @return
     */
    public void updateUserInfo(User user) {
        userMapper.updateUserInfo(user);
    }

    /**
     * 验证用户是否存在
     * @param userId
     * @return
     */
    public User verifyUser(String userId) {
        return userMapper.verifyUser(userId);
    }

    /**
     * 添加用户信息
     * @param user
     */
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public User getUserName(String userId) {
        return userMapper.getUserName(userId);
    }
}
