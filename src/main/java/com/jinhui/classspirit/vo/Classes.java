package com.jinhui.classspirit.vo;

import org.apache.ibatis.type.Alias;

@Alias("classes")
public class Classes {

  private String classId;
  private String className;


  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  @Override
  public String toString() {
    return "ClassController{" +
            "classId='" + classId + '\'' +
            ", className='" + className + '\'' +
            '}';
  }
}
