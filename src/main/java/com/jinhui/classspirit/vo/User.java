package com.jinhui.classspirit.vo;

import org.apache.ibatis.type.Alias;

@Alias("user")
public class User {

  private String userId;
  private String userPhone;
  private String userImage;
  private Integer userType;
  private String userName;
  private String studentId;
  private String classId;

  private String studentName;
  private String className;


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }


  public String getUserImage() {
    return userImage;
  }

  public void setUserImage(String userImage) {
    this.userImage = userImage;
  }

  public Integer getUserType() {
    return userType;
  }

  public void setUserType(Integer userType) {
    this.userType = userType;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }


  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  @Override
  public String toString() {
    return "User{" +
            "userId='" + userId + '\'' +
            ", userPhone='" + userPhone + '\'' +
            ", userImage='" + userImage + '\'' +
            ", userType=" + userType +
            ", userName='" + userName + '\'' +
            ", studentId='" + studentId + '\'' +
            ", classId='" + classId + '\'' +
            '}';
  }
}
