package com.jinhui.classspirit.vo;

import org.apache.ibatis.type.Alias;

@Alias("activityApply")
public class ActivityApply {
    private long activityId;
    private String activityName;
    private String activityImage;
    private String activityContent;
    private Integer activityState;
    private String classId;
    private String className;
    private String userId;
    private String userPhone;
    private String userName;
    private String userImage;
    private String startTime;
    private String pubTime;
    private String image;
    private Integer personCount;
    private String punchTime;

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

    public Integer getActivityState() {
        return activityState;
    }

    public void setActivityState(Integer activityState) {
        this.activityState = activityState;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public String getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(String punchTime) {
        this.punchTime = punchTime;
    }

    @Override
    public String toString() {
        return "ActivityApply{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", activityImage='" + activityImage + '\'' +
                ", activityContent='" + activityContent + '\'' +
                ", activityState=" + activityState +
                ", classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", userId='" + userId + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userName='" + userName + '\'' +
                ", userImage='" + userImage + '\'' +
                ", startTime='" + startTime + '\'' +
                ", pubTime='" + pubTime + '\'' +
                ", image='" + image + '\'' +
                ", personCount=" + personCount +
                ", punchTime='" + punchTime + '\'' +
                '}';
    }
}
