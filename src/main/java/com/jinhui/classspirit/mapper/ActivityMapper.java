package com.jinhui.classspirit.mapper;

import com.jinhui.classspirit.vo.Activity;
import com.jinhui.classspirit.vo.ActivityApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityMapper {
    List<ActivityApply> getActivitiesList(@Param("offset") int offset, @Param("pageSize") Integer pageSize, @Param("classId") String classId, @Param("userId") String userId, @Param("type") Integer type, @Param("requestTime") String requestTime, @Param("sort") Integer sort);

    List<ActivityApply> getActivitiesLists(@Param("offset") int offset, @Param("pageSize") Integer pageSize, @Param("classId") String classId, @Param("userId") String userId, @Param("type") Integer type, @Param("requestTime") String requestTime, @Param("sort") Integer sort);

    void insertActivity(Activity a);

    Activity verifyTime(Integer activityId);

    ActivityApply getActivityByActivityId(Integer activityId);
}
