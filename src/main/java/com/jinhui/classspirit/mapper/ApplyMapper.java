package com.jinhui.classspirit.mapper;

import com.jinhui.classspirit.vo.ActivityApply;
import com.jinhui.classspirit.vo.Apply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApplyMapper {

    ActivityApply getApplyCountByActivityId(long activityId);

    List<ActivityApply> getAppliesListByActivityId(long activityId);

    Apply verifyApply(@Param("activityId") Integer activityId, @Param("userId") String userId);

    void insertApply(Apply a);

    Apply verifyTime(String activityId);
}
