package com.jinhui.classspirit.service;

import com.jinhui.classspirit.mapper.StudentMapper;
import com.jinhui.classspirit.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 根据班级Id获取学生信息
     * @param classId 班级Id
     * @return
     */
    public List<Student> getStudentByClassId(String classId) {
        return studentMapper.getStudentByClassId(classId);
    }
}
