package com.jinhui.classspirit.service;

import com.jinhui.classspirit.mapper.ActivityMapper;
import com.jinhui.classspirit.vo.Activity;
import com.jinhui.classspirit.vo.ActivityApply;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 获取活动列表
     * @param currentPage   当前页
     * @param pageSize  页码
     * @param classId   班级Id
     * @param userId    用户Id
     * @param type      类型
     * @return
     */
    public List<ActivityApply> getActivitiesList(@Param("currentPage")Integer currentPage, @Param("pageSize")Integer pageSize, @Param("classId")String classId, @Param("userId")String userId, @Param("type")Integer type, @Param("requestTime")String requestTime, @Param("sort") Integer sort) {
        int offset = (currentPage) * pageSize;
        if (type != null && type == 2){
            return activityMapper.getActivitiesLists(offset,pageSize,classId,userId,type,requestTime,sort);
        }
        return activityMapper.getActivitiesList(offset,pageSize,classId,userId,type,requestTime,sort);
    }

    /**
     * 发布活动
     * @param a 活动实体类
     */
    public void insertActivity(Activity a) {
        activityMapper.insertActivity(a);
    }

    /**
     * 验证活动报名是否过期
     * @param activityId
     * @return
     */
    public Activity verifyTime(Integer activityId) {
        return activityMapper.verifyTime(activityId);
    }

    /**
     * 活动信息
     * @param activityId
     * @return
     */
    public ActivityApply getActivityByActivityId(Integer activityId) {
        return activityMapper.getActivityByActivityId(activityId);
    }
}
