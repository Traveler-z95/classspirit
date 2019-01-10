package com.jinhui.classspirit.controller;

import com.jinhui.classspirit.service.ClassService;
import com.jinhui.classspirit.service.StudentService;
import com.jinhui.classspirit.service.UserService;
import com.jinhui.classspirit.vo.Classes;
import com.jinhui.classspirit.vo.Student;
import com.jinhui.classspirit.vo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/class")
public class ClassController extends BaseController {

    private static Log log = LogFactory.getLog(ClassController.class);

    @Autowired
    ClassService classService;
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;

    /**
     * 班级列表
     * @param response
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.POST)
    @ResponseBody
    public String List(HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
            List<Classes> list = classService.getClassList();
            if (list != null){
                for (Classes classes : list){
                    Map<String, Object> maps = new HashMap<String, Object>();
                    maps.put("classId", classes.getClassId());
                    maps.put("className", classes.getClassName());
                    lists.add(maps);
                }
                map.put("classList", lists);
                map.put("result", 1);
            }else {
                map.put("result", 101);
            }
        } catch (Exception e) {
            map.put("result", 0);
            //e.printStackTrace();
        }

        return renderString(response,map);
    }

    /**
     * 班级详情
     * @param classId 班级Id
     * @param response
     * @return
     */
    @RequestMapping(value = "details",method = RequestMethod.POST)
    @ResponseBody
    public String Details(@Param("classId") String classId, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();    //一级
        Map<String, Object> maps = new HashMap<String, Object>();   //二级

        try {
            if (classId != null) {
                //获取班级信息
                Classes classes = classService.getClassById(classId);
                maps.put("classId", classes.getClassId());
                maps.put("className", classes.getClassName());

                //获取教师信息
                List<Map<String, Object>> teacherList = new ArrayList<Map<String, Object>>();
                List<User> teachers = userService.getTeacherByClassId(classId);
                for (User teacher : teachers){
                    Map<String, Object> teacherMap = new HashMap<String, Object>();
                    teacherMap.put("userName", teacher.getUserName());
                    teacherMap.put("userPhone", teacher.getUserPhone());
                    teacherMap.put("userType", teacher.getUserType());
                    teacherMap.put("userImage", serverUrl + userImageUrl + teacher.getUserImage());
                    teacherList.add(teacherMap);
                }
                maps.put("teacherList", teacherList);

                //获取学生信息
                List<Map<String, Object>> studentList = new ArrayList<Map<String, Object>>();
                List<Student> students = studentService.getStudentByClassId(classId);
                for (Student student : students){
                    Map<String, Object> studentMap = new HashMap<String, Object>();
                    studentMap.put("studentId", student.getStudentId());
                    studentMap.put("studentName", student.getStudentName());
                    studentMap.put("studentClass", student.getStudentClass());
                    studentMap.put("studentImage", serverUrl + studentImageUrl + student.getStudentImage());
                    studentList.add(studentMap);
                }
                maps.put("studentList", studentList);

                map.put("classDetail", maps);
                map.put("result", 1);
            } else {
                map.put("result", 101);
            }
        } catch (Exception e) {
            map.put("result", 0);
            //e.printStackTrace();
        }

        return renderString(response, map);
    }
}
