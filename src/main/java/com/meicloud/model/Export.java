package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;
import java.util.Date;

public class Export implements Serializable {

   private static final long serialVersionUID = 1L;
   private String topicName;
   private String groupName;
   private String jobNum;
   private String cron;
   private String groupTime;
   private Date startTime;
   private Date endTime;
   private String jobName;
   private String jobTodayTime;
   private String jobYesTime;
   private String jobDbyTime;
   private String userName;
   private String groupEnable;
   private String jobEnable;


   public String getTopicName() {
      return this.topicName;
   }

   public void setTopicName(String topicName) {
      this.topicName = topicName;
   }

   public String getGroupName() {
      return this.groupName;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }

   public String getCron() {
      return this.cron;
   }

   public void setCron(String cron) {
      this.cron = cron;
   }

   public String getJobNum() {
      return this.jobNum;
   }

   public void setJobNum(String jobNum) {
      this.jobNum = jobNum;
   }

   public String getGroupTime() {
      return this.groupTime;
   }

   public void setGroupTime(String groupTime) {
      this.groupTime = groupTime;
   }

   public Date getStartTime() {
      return this.startTime;
   }

   public void setStartTime(Date startTime) {
      this.startTime = startTime;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }

   public String getJobName() {
      return this.jobName;
   }

   public void setJobName(String jobName) {
      this.jobName = jobName;
   }

   public String getJobTodayTime() {
      return this.jobTodayTime;
   }

   public void setJobTodayTime(String jobTodayTime) {
      this.jobTodayTime = jobTodayTime;
   }

   public String getJobYesTime() {
      return this.jobYesTime;
   }

   public void setJobYesTime(String jobYesTime) {
      this.jobYesTime = jobYesTime;
   }

   public String getJobDbyTime() {
      return this.jobDbyTime;
   }

   public void setJobDbyTime(String jobDbyTime) {
      this.jobDbyTime = jobDbyTime;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getGroupEnable() {
      return this.groupEnable;
   }

   public void setGroupEnable(String groupEnable) {
      this.groupEnable = groupEnable;
   }

   public String getJobEnable() {
      return this.jobEnable;
   }

   public void setJobEnable(String jobEnable) {
      this.jobEnable = jobEnable;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
