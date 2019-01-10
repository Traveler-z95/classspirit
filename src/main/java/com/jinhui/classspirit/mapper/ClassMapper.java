package com.jinhui.classspirit.mapper;

import com.jinhui.classspirit.vo.Classes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {
    List<Classes> getClassList();

    Classes getClassById(String classId);
}
