package com.jinhui.classspirit.vo;

import org.apache.ibatis.type.Alias;

@Alias("apply")
public class Apply {

  private long applyId;
  private Integer activityId;
  private String userId;
  private String image;
  private String punchTime;

  public long getApplyId() {
    return applyId;
  }

  public void setApplyId(long applyId) {
    this.applyId = applyId;
  }

  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(Integer activityId) {
    this.activityId = activityId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getPunchTime() {
    return punchTime;
  }

  public void setPunchTime(String punchTime) {
    this.punchTime = punchTime;
  }

  @Override
  public String toString() {
    return "Apply{" +
            "applyId=" + applyId +
            ", activityId=" + activityId +
            ", userId='" + userId + '\'' +
            ", image='" + image + '\'' +
            ", punchTime='" + punchTime + '\'' +
            '}';
  }
}
