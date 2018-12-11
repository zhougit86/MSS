package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;
import java.util.Date;

public class GroupJobRefer implements Serializable {

   private static final long serialVersionUID = 1L;
   private int referId;
   private int jobId;
   private String jobName;
   private int groupId;
   private Date createDate;
   private Date updateDate;
   private String createUser;


   public GroupJobRefer() {}

   public String getJobName() {
      return this.jobName;
   }

   public void setJobName(String jobName) {
      this.jobName = jobName;
   }

   public GroupJobRefer(String jobId, int groupId) {
      this.jobId = Integer.parseInt(jobId);
      this.groupId = groupId;
   }

   public int getReferId() {
      return this.referId;
   }

   public void setReferId(int referId) {
      this.referId = referId;
   }

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

   public Date getCreateDate() {
      return this.createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public Date getUpdateDate() {
      return this.updateDate;
   }

   public void setUpdateDate(Date updateDate) {
      this.updateDate = updateDate;
   }

   public String getCreateUser() {
      return this.createUser;
   }

   public void setCreateUser(String createUser) {
      this.createUser = createUser;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
