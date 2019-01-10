package com.jinhui.classspirit.controller;

import com.jinhui.classspirit.service.UserService;
import com.jinhui.classspirit.util.HttpRequest;
import com.jinhui.classspirit.vo.User;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController extends BaseController {

    private static Log log = LogFactory.getLog(LoginController.class);

    @Autowired
    UserService userService;

    /**
     * 通过code获取openId
     * @param code 客户端上传的code参数
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getOpenId", method = RequestMethod.POST)
    @ResponseBody
    public String getOpenId(String code, HttpServletResponse response) throws Exception{

        Map<String, Object> map = new HashMap<String, Object>();

        // 登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return renderString(response, map);
        }

        // 小程序唯一标识 (在微信小程序管理后台获取)
        String appid = "wxcf3f24b6252bc5c1";
        // 小程序的 app secret (在微信小程序管理后台获取)
        String secret = "3b2aedd5e69e3f1577f667ab79e6d994";
        // 授权（必填）
        String grant_type = "authorization_code";

        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        // 请求参数
        String params = "appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type="
                + grant_type;
        // 发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);

        // 解析相应内容（转换成json对象）
        JSONObject json = new JSONObject(sr);
        String session_key = null;
        String openid = null;
        String errcode = null;

        //判断json对象中是否存在"openid"字段
        if (json.has("openid")) {
            // 用户的唯一标识（openid）
            openid = (String) json.get("openid");
            map.put("result", 1);
            map.put("openid", openid);
        } else {
            // 获取错误码（errcode）
            errcode = json.get("errcode").toString();
            map.put("result", errcode);
        }
        //判断json对象中是否存在"session_key"字段
        if (json.has("session_key")) {
            // 获取会话密钥（session_key）
            session_key = json.get("session_key").toString();
        }
        return renderString(response, map);
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String Login(@Param("userId") String userId, @Param("userName") String userName,
                        @Param("userType") Integer userType, @Param("userImage") String userImage,
                        HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        User user = new User();
        User verify = userService.verifyUser(userId);
        if (verify == null){
            user.setUserId(userId);
            user.setUserName(userName);
            if (userType == null){
                user.setUserType(-1);
            }else if (userType.equals(0)){
                user.setUserType(0);
            }else if (userType.equals(1)){
                user.setUserType(1);
            }else if (userType.equals(2)){
                user.setUserType(2);
            }

            //根据userImage地址下载图片到服务器，将保存路径存入数据库
            String imageName = System.currentTimeMillis()+".png";   //定义图片保存名称
            String savePath = request.getSession().getServletContext().getRealPath("upload/images/user/");   //保存路径
            try {
                URL httpUrl = new URL(userImage);
                HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
                //设置超时间为3秒
                conn.setConnectTimeout(3*1000);
                //防止屏蔽程序抓取而返回403错误
                conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

                //文件保存位置
                File saveDir = new File(savePath);
                if(!saveDir.exists()){
                    saveDir.mkdirs();
                }
                File file = new File(saveDir+ File.separator+imageName);
                //拷贝url到文件
                FileUtils.copyURLToFile(httpUrl, file);
            } catch (Exception e) {
                //e.printStackTrace();
                map.put("result","101");
                map.put("msg","没有获取到微信头像地址！");
            }
            user.setUserImage(imageName);
            userService.insertUser(user);
            map.put("result", 1);
        }else {
            map.put("result", 0);
        }
        return renderString(response, map);
    }
}
