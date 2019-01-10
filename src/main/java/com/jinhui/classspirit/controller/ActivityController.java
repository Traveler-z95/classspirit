package com.jinhui.classspirit.controller;

import com.jinhui.classspirit.service.ActivityService;
import com.jinhui.classspirit.service.ApplyService;
import com.jinhui.classspirit.service.UserService;
import com.jinhui.classspirit.util.RequestTime;
import com.jinhui.classspirit.vo.Activity;
import com.jinhui.classspirit.vo.ActivityApply;
import com.jinhui.classspirit.vo.Apply;
import com.jinhui.classspirit.vo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    private static Log log = LogFactory.getLog(ActivityController.class);

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private UserService userService;

    /**
     * 活动列表（含详情）
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public String List(@RequestParam(defaultValue = "0") Integer currentPage, @RequestParam(defaultValue = "5") Integer pageSize, @Param("requestTime") String requestTime,
                       @Param("classId") String classId, @Param("userId") String userId, @Param("type") Integer type, @Param("sort") Integer sort, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        //查询活动列表
        List<ActivityApply> activitiesList = activityService.getActivitiesList(currentPage, pageSize, classId, userId, type, requestTime, sort);
        if (activitiesList != null) {
            for (ActivityApply activities : activitiesList) {
                //判断活动是或否截至报名
                int i = activities.getStartTime().compareTo(RequestTime.getStringDates());
                if (i > 0) {
                    activities.setActivityState(0);
                } else {
                    activities.setActivityState(1);
                }
                Map<String, Object> maps = new HashMap<String, Object>();
                List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
                //查询报名人数
                ActivityApply personCounts = applyService.getApplyCountByActivityId(activities.getActivityId());

                maps.put("activityId", activities.getActivityId());
                maps.put("activityName", activities.getActivityName());
                maps.put("activityImage", serverUrl + activityImageUrl + activities.getActivityImage());
                maps.put("activityContent", activities.getActivityContent());
                maps.put("activityState", activities.getActivityState());
                maps.put("classId", activities.getClassId());
                maps.put("className", activities.getClassName());
                maps.put("userName", activities.getUserName());
                maps.put("startTime", activities.getStartTime());
                maps.put("pubTime", activities.getPubTime());
                maps.put("personCount", personCounts.getPersonCount());

                //查询报名人信息列表
                List<ActivityApply> appliesList = applyService.getAppliesListByActivityId(activities.getActivityId());
                for (ActivityApply applies : appliesList) {
                    Map<String, Object> mapss = new HashMap<String, Object>();
                    mapss.put("userName", applies.getUserName());
                    mapss.put("userPhone", applies.getUserPhone());
                    mapss.put("userId", applies.getUserId());
                    mapss.put("userImage", serverUrl + userImageUrl + applies.getUserImage());
                    mapss.put("image", serverUrl + applyImageUrl + applies.getImage());
                    mapss.put("punchTime", applies.getPunchTime());
                    lists.add(mapss);
                }
                maps.put("personList", lists);
                list.add(maps);
            }
            map.put("activityList", list);
            map.put("result", 1);
        } else {
            map.put("result", 101);
        }

        return renderString(response, map);
    }

    /**
     * 活动详情
     * @param activityId
     * @param response
     * @return
     */
    @RequestMapping(value = "details", method = RequestMethod.POST)
    @ResponseBody
    public String Details(@Param("activityId") Integer activityId, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        ActivityApply activityInfo = activityService.getActivityByActivityId(activityId);
        if (activityInfo != null){
            User us = userService.getUserName(activityInfo.getUserId());
            map.put("activityId", activityInfo.getActivityId());
            map.put("activityName", activityInfo.getActivityName());
            map.put("activityImage", serverUrl + activityImageUrl + activityInfo.getActivityImage());
            map.put("activityContent", activityInfo.getActivityContent());

            int i = activityInfo.getStartTime().compareTo(RequestTime.getStringDates());
            if (i > 0) {
                activityInfo.setActivityState(0);
            } else {
                activityInfo.setActivityState(1);
            }
            map.put("activityState", activityInfo.getActivityState());
            map.put("classId", activityInfo.getClassId());
            map.put("className", activityInfo.getClassName());
            map.put("userName", us.getUserName());
            map.put("startTime", activityInfo.getStartTime());
            map.put("pubTime", activityInfo.getPubTime());
            map.put("personCount", activityInfo.getPersonCount());

            List<ActivityApply> applyInfo = applyService.getAppliesListByActivityId(activityId);
            for (ActivityApply a : applyInfo){
                Map<String, Object> maps = new HashMap<String, Object>();
                maps.put("userName", a.getUserName());
                maps.put("userPhone", a.getUserPhone());
                maps.put("userId", a.getUserId());
                maps.put("userImage", serverUrl + userImageUrl + a.getUserImage());
                maps.put("image", serverUrl + applyImageUrl + a.getImage());
                maps.put("punchTime", a.getPunchTime());
                list.add(maps);
            }
            map.put("personList", list);
            map.put("result", 1);
        }else {
            map.put("result", 0);
        }
        return renderString(response, map);
    }

    /**
     * 发布活动
     *
     * @param activityName
     * @param activityContent
     * @param classId
     * @param userId
     * @param startTime
     * @param activityImage
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String Add(@Param("activityName") String activityName, @Param("activityContent") String activityContent,
                      @Param("classId") String classId, @Param("userId") String userId,
                      @Param("startTime") String startTime, @Param("activityImage") MultipartFile activityImage,
                      HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();

        Activity a = new Activity();
        a.setActivityName(activityName);
        a.setActivityContent(activityContent);
        a.setUserId(userId);
        a.setClassId(classId);
        a.setStartTime(startTime);
        a.setPubTime(RequestTime.getStringDates());

        //获取文件名
        String imageName = null;
        imageName = activityImage.getOriginalFilename();
        //保存地址
        String path = request.getSession().getServletContext().getRealPath("upload/images/activity/");
        // 获取原文件名
        int startIndex = imageName.lastIndexOf(".");
        //文件类型
        String suffix = imageName.substring(startIndex);
        //重构文件名时间戳+类型
        imageName = System.currentTimeMillis() + suffix;
        //上传文件
        File targetFile = new File(path + File.separator + imageName);
        //若文件夹不存在，则创建目录
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            activityImage.transferTo(targetFile);
        } catch (Exception e) {
            //e.printStackTrace();
            map.put("result", "101");
        }
        a.setActivityImage(imageName);
        //发布活动
        activityService.insertActivity(a);

        map.put("result", "1");
        return renderString(response, map);
    }

    /**
     * 报名活动
     * @param activityId
     * @param userId
     * @param image
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "apply", method = RequestMethod.POST)
    @ResponseBody
    public String Apply(@Param("activityId") Integer activityId, @Param("userId") String userId,
                        @Param("image") MultipartFile image, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证报名时间
        Activity verifyTime = activityService.verifyTime(activityId);
        //判断活动是或否截至报名
        int i = verifyTime.getStartTime().compareTo(RequestTime.getStringDates());
        if (i > 0) {
            //验证是否报名
            Apply verifyApply = applyService.verifyApply(activityId, userId);
            if (verifyApply == null) {
                Apply a = new Apply();
                a.setActivityId(activityId);
                a.setUserId(userId);
                a.setPunchTime(RequestTime.getStringDates());

                //获取文件名
                String imageName = null;
                imageName = image.getOriginalFilename();
                //保存地址
                String path = request.getSession().getServletContext().getRealPath("upload/images/apply/");
                // 获取原文件名
                int startIndex = imageName.lastIndexOf(".");
                //文件类型
                String suffix = imageName.substring(startIndex);
                //重构文件名时间戳+类型
                imageName = System.currentTimeMillis() + suffix;
                //上传文件
                File targetFile = new File(path + File.separator + imageName);
                //若文件夹不存在，则创建目录
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                try {
                    image.transferTo(targetFile);
                } catch (Exception e) {
                    //e.printStackTrace();
                    map.put("result", "101");
                    map.put("msg", "上传图片失败");
                }
                a.setImage(imageName);
                //报名
                applyService.insertApply(a);
                map.put("result", "1");
            } else {
                map.put("result", "3");
            }
        } else {
            map.put("result", "2");
        }
        return renderString(response, map);
    }


}
