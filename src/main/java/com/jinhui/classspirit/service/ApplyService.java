package com.jinhui.classspirit.service;

import com.jinhui.classspirit.mapper.ApplyMapper;
import com.jinhui.classspirit.vo.ActivityApply;
import com.jinhui.classspirit.vo.Apply;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyService {
    @Autowired
    private ApplyMapper applyMapper;

    /**
     * 根据活动Id查询报名人数
     * @param activityId
     * @return
     */
    public ActivityApply getApplyCountByActivityId(long activityId) {
        return applyMapper.getApplyCountByActivityId(activityId);
    }

    /**
     * 根据活动Id查询报名人信息列表
     * @param activityId
     * @return
     */
    public List<ActivityApply> getAppliesListByActivityId(long activityId) {
        return applyMapper.getAppliesListByActivityId(activityId);
    }

    /**
     * 验证是否已报名
     * @param activityId
     * @param userId
     * @return
     */
    public Apply verifyApply(@Param("activityId") Integer activityId, @Param("userId") String userId) {
        return applyMapper.verifyApply(activityId,userId);
    }

    /**
     * 报名参加活动
     * @param a
     */
    public void insertApply(Apply a) {
        applyMapper.insertApply(a);
    }

}
