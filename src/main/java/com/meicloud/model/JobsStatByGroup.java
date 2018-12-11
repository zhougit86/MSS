package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;

public class JobsStatByGroup implements Serializable {

   private static final long serialVersionUID = -1227637792034685169L;
   private int groupId;
   private int state;
   private int jobCount;
   private int jobId;


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

   public int getJobCount() {
      return this.jobCount;
   }

   public void setJobCount(int jobCount) {
      this.jobCount = jobCount;
   }

   public int getJobId() {
      return this.jobId;
   }

   public void setJobId(int jobId) {
      this.jobId = jobId;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
