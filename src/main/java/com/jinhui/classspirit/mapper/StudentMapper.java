package com.jinhui.classspirit.mapper;

import com.jinhui.classspirit.vo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> getStudentByClassId(String classId);
}
