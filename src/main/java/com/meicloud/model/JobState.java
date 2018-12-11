package com.meicloud.model;

import java.util.Date;

public class JobState {

   private int jobId;
   private int groupId;
   private int state;
   private String msg;
   private Date createDate;
   private int referedGroupId;
   private boolean enable;


   public int getJobId() {
      return this.jobId;
   }

   public void setJobId(int jobId) {
      this.jobId = jobId;
   }

   public int getGroupId() {
      return this.groupId;
   }

   public void setGroupId(int groupId) {
      this.groupId = groupId;
   }

   public int getState() {
      return this.state;
   }

   public void setState(int state) {
      this.state = state;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public Date getCreateDate() {
      return this.createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public int getReferedGroupId() {
      return this.referedGroupId;
   }

   public void setReferedGroupId(int referedGroupId) {
      this.referedGroupId = referedGroupId;
   }

   public boolean isEnable() {
      return this.enable;
   }

   public void setEnable(boolean enable) {
      this.enable = enable;
   }
}
