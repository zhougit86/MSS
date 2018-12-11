package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;

public class GroupParalle implements Serializable {

   private static final long serialVersionUID = 1L;
   private int groupId;
   private int jobNumInQueueOrRunning;
   private int paralleLimit;


   public int getGroupId() {
      return this.groupId;
   }

   public void setGroupId(int groupId) {
      this.groupId = groupId;
   }

   public int getJobNumInQueueOrRunning() {
      return this.jobNumInQueueOrRunning;
   }

   public void setJobNumInQueueOrRunning(int jobNumInQueueOrRunning) {
      this.jobNumInQueueOrRunning = jobNumInQueueOrRunning;
   }

   public int getParalleLimit() {
      return this.paralleLimit;
   }

   public void setParalleLimit(int paralleLimit) {
      this.paralleLimit = paralleLimit;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
