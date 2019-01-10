package com.jinhui.classspirit.service;

import com.jinhui.classspirit.mapper.ClassMapper;
import com.jinhui.classspirit.vo.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    @Autowired
    private ClassMapper classMapper;

    /**
     * 获取班级列表
     * @return
     */
    public List<Classes> getClassList() {
        return classMapper.getClassList();
    }

    /**
     * 根据班级Id获取班级详情
     * @param classId
     * @return
     */
    public Classes getClassById(String classId) {
        return classMapper.getClassById(classId);
    }
}
