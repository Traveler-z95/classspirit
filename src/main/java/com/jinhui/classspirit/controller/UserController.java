package com.jinhui.classspirit.controller;

import com.jinhui.classspirit.service.UserService;
import com.jinhui.classspirit.vo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static Log log = LogFactory.getLog(UserController.class);

    @Autowired
    UserService userService;

    /**
     * 获取用户信息
     *
     * @param userId
     * @param response
     * @return
     */
    @RequestMapping(value = "getInfo", method = RequestMethod.POST)
    @ResponseBody
    public String GetInfo(@Param("userId") String userId, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();


        if (userId != null) {
            User user = userService.getUserInfoByUserId(userId);
            if (user != null) {
                map.put("userId", user.getUserId());
                map.put("userPhone", user.getUserPhone());
                map.put("userImage", serverUrl + userImageUrl + user.getUserImage());
                map.put("userType", user.getUserType());
                map.put("userName", user.getUserName());
                map.put("studentName", user.getStudentName());
                map.put("className", user.getClassName());

                map.put("result", 1);
            } else {
                map.put("result", 999);
            }
        } else {
            map.put("result", 101);
        }


        return renderString(response, map);
    }

    /**
     * 用户信息修改
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    @ResponseBody
    public String UpdateInfo(@Param("userId") String userId, @Param("userPhone") String userPhone,
                             @Param("userImage") MultipartFile userImage, @Param("userType") Integer userType,
                             @Param("userName") String userName, @Param("studentId") String studentId,
                             @Param("classId") String classId,
                             HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();


        //验证用户类型
        User verifyType = userService.verifyUser(userId);

        User user = new User();
        user.setUserId(userId);
        user.setUserPhone(userPhone);
        if (userImage != null) {

            //获取文件名
            String imageName = null;
            imageName = userImage.getOriginalFilename();
            //保存地址
            String path = request.getSession().getServletContext().getRealPath("upload/images/user/");
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
                userImage.transferTo(targetFile);
            } catch (Exception e) {
                //e.printStackTrace();
                map.put("result", "101");
            }

            user.setUserImage(imageName);

            //删除修改前保存的图片
            deleteServerFile(path + File.separator + verifyType.getUserImage());
        }
        if (verifyType.getUserType() == -1) {
            user.setUserType(userType);
        }
        user.setUserName(userName);
        if (userType == 2) {
            user.setStudentId(studentId);
        }
        if (userType == 0 || userType == 1) {
            user.setClassId(classId);
        }
        //更新用户信息
        userService.updateUserInfo(user);

        map.put("result", 1);


        return renderString(response, map);
    }


}
