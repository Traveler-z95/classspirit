package com.jinhui.classspirit.vo;

import org.apache.ibatis.type.Alias;

@Alias("activity")
public class Activity {

  private long activityId;
  private String activityName;
  private String activityImage;
  private String activityContent;
  private String userId;
  private String classId;
  private String startTime;
  private String pubTime;


  public long getActivityId() {
    return activityId;
  }

  public void setActivityId(long activityId) {
    this.activityId = activityId;
  }


  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }


  public String getActivityImage() {
    return activityImage;
  }

  public void setActivityImage(String activityImage) {
    this.activityImage = activityImage;
  }


  public String getActivityContent() {
    return activityContent;
  }

  public void setActivityContent(String activityContent) {
    this.activityContent = activityContent;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getPubTime() {
    return pubTime;
  }

  public void setPubTime(String pubTime) {
    this.pubTime = pubTime;
  }

  @Override
  public String toString() {
    return "Activity{" +
            "activityId=" + activityId +
            ", activityName='" + activityName + '\'' +
            ", activityImage='" + activityImage + '\'' +
            ", activityContent='" + activityContent + '\'' +
            ", userId='" + userId + '\'' +
            ", classId='" + classId + '\'' +
            ", startTime='" + startTime + '\'' +
            ", pubTime='" + pubTime + '\'' +
            '}';
  }
}
