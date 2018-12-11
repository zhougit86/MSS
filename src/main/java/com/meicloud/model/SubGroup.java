package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;

public class SubGroup implements Serializable {

   private static final long serialVersionUID = 1L;
   private int groupId;
   private int orderNo;
   private String msg;
   private boolean sordInGroup;
   private String referedGroupIds;
   private String referedJobIds;
   private int runJobCount;
   private static int watingCheckState = 0;
   private static int watingState = 1;
   private static int runningState = 2;
   private static int errorState = 3;


   public int getGroupId() {
      return this.groupId;
   }

   public void setGroupId(int groupId) {
      this.groupId = groupId;
   }

   public int getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(int orderNo) {
      this.orderNo = orderNo;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public boolean isSordInGroup() {
      return this.sordInGroup;
   }

   public void setSordInGroup(boolean sordInGroup) {
      this.sordInGroup = sordInGroup;
   }

   public String getReferedGroupIds() {
      return this.referedGroupIds;
   }

   public void setReferedGroupIds(String referedGroupIds) {
      this.referedGroupIds = referedGroupIds;
   }

   public String getReferedJobIds() {
      return this.referedJobIds;
   }

   public void setReferedJobIds(String referedJobIds) {
      this.referedJobIds = referedJobIds;
   }

   public int getRunJobCount() {
      return this.runJobCount;
   }

   public void setRunJobCount(int runJobCount) {
      this.runJobCount = runJobCount;
   }

   public int getWatingCheckState() {
      return watingCheckState;
   }

   public int getWatingState() {
      return watingState;
   }

   public static int getRunningState() {
      return runningState;
   }

   public static int getErrorState() {
      return errorState;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
