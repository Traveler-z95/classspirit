package com.jinhui.classspirit.mapper;

import com.jinhui.classspirit.vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getTeacherByClassId(String classId);

    User getUserInfoByUserId(String userId);

    void updateUserInfo(User user);

    User verifyUser(String userId);

    void insertUser(User user);

    User getUserName(String userId);
}
