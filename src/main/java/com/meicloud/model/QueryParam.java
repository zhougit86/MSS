package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;

public class QueryParam implements Serializable {

   private static final long serialVersionUID = 1L;
   private int unReachState = 0;
   private int acceptState = 1;
   private int runningState = 2;
   private int errorState = 3;
   private int cancelState = 4;
   private int successState = 5;
   private int rejectedState = 6;
   private int watingState = 1;
   private int watingCheckState = 0;
   private int state;
   private int oldState;
   private int jobId;
   private int groupId;
   private int runGroupId;
   private String referedGroupIds;
   private String msg;
   private int executeServerId;
   private int limit;
   private int sendIntervalInSeconds;


   public int getOldState() {
      return this.oldState;
   }

   public void setOldState(int oldState) {
      this.oldState = oldState;
   }

   public int getUnReachState() {
      return this.unReachState;
   }

   public void setUnReachState(int unReachState) {
      this.unReachState = unReachState;
   }

   public int getAcceptState() {
      return this.acceptState;
   }

   public void setAcceptState(int acceptState) {
      this.acceptState = acceptState;
   }

   public int getRunningState() {
      return this.runningState;
   }

   public void setRunningState(int runningState) {
      this.runningState = runningState;
   }

   public int getErrorState() {
      return this.errorState;
   }

   public void setErrorState(int errorState) {
      this.errorState = errorState;
   }

   public int getCancelState() {
      return this.cancelState;
   }

   public void setCancelState(int cancelState) {
      this.cancelState = cancelState;
   }

   public int getSuccessState() {
      return this.successState;
   }

   public void setSuccessState(int successState) {
      this.successState = successState;
   }

   public int getRejectedState() {
      return this.rejectedState;
   }

   public void setRejectedState(int rejectedState) {
      this.rejectedState = rejectedState;
   }

   public int getWatingState() {
      return this.watingState;
   }

   public void setWatingState(int watingState) {
      this.watingState = watingState;
   }

   public int getWatingCheckState() {
      return this.watingCheckState;
   }

   public void setWatingCheckState(int watingCheckState) {
      this.watingCheckState = watingCheckState;
   }

   public int getState() {
      return this.state;
   }

   public void setState(int state) {
      this.state = state;
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

   public int getRunGroupId() {
      return this.runGroupId;
   }

   public void setRunGroupId(int runGroupId) {
      this.runGroupId = runGroupId;
   }

   public String getReferedGroupIds() {
      return this.referedGroupIds;
   }

   public void setReferedGroupIds(String referedGroupIds) {
      this.referedGroupIds = referedGroupIds;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public int getExecuteServerId() {
      return this.executeServerId;
   }

   public void setExecuteServerId(int executeServerId) {
      this.executeServerId = executeServerId;
   }

   public int getLimit() {
      return this.limit;
   }

   public void setLimit(int limit) {
      this.limit = limit;
   }

   public int getSendIntervalInSeconds() {
      return this.sendIntervalInSeconds;
   }

   public void setSendIntervalInSeconds(int sendIntervalInSeconds) {
      this.sendIntervalInSeconds = sendIntervalInSeconds;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
