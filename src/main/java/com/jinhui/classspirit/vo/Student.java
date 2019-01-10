package com.jinhui.classspirit.vo;

import org.apache.ibatis.type.Alias;

@Alias("student")
public class Student {

  private String studentId;
  private String studentName;
  private String studentImage;
  private String studentClass;


  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }


  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }


  public String getStudentImage() {
    return studentImage;
  }

  public void setStudentImage(String studentImage) {
    this.studentImage = studentImage;
  }


  public String getStudentClass() {
    return studentClass;
  }

  public void setStudentClass(String studentClass) {
    this.studentClass = studentClass;
  }

  @Override
  public String toString() {
    return "Student{" +
            "studentId='" + studentId + '\'' +
            ", studentName='" + studentName + '\'' +
            ", studentImage='" + studentImage + '\'' +
            ", studentClass='" + studentClass + '\'' +
            '}';
  }
}
