package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;

public class Proportion implements Serializable {

   private static final long serialVersionUID = 1L;
   private int groupId;
   private int total;
   private int forbidden;
   private int waiting;
   private int start;
   private int running;
   private int success;
   private int error;
   private String execState;
   private String forbiddenIds;
   private String waitingIds;
   private String startIds;
   private String runningIds;
   private String successIds;
   private String errorsIds;


   public Proportion() {}

   public Proportion(int groupId, int total, int success, int running, int waiting, int error, int forbidden) {
      this.groupId = groupId;
      this.total = total;
      this.success = success;
      this.running = running;
      this.waiting = waiting;
      this.error = error;
      this.forbidden = forbidden;
   }

   public int getGroupId() {
      return this.groupId;
   }

   public void setGroupId(int groupId) {
      this.groupId = groupId;
   }

   public int getTotal() {
      return this.total;
   }

   public void setTotal(int total) {
      this.total = total;
   }

   public int getForbidden() {
      return this.forbidden;
   }

   public void setForbidden(int forbidden) {
      this.forbidden = forbidden;
   }

   public int getWaiting() {
      return this.waiting;
   }

   public void setWaiting(int waiting) {
      this.waiting = waiting;
   }

   public int getStart() {
      return this.start;
   }

   public void setStart(int start) {
      this.start = start;
   }

   public int getRunning() {
      return this.running;
   }

   public void setRunning(int running) {
      this.running = running;
   }

   public int getSuccess() {
      return this.success;
   }

   public void setSuccess(int success) {
      this.success = success;
   }

   public int getError() {
      return this.error;
   }

   public void setError(int error) {
      this.error = error;
   }

   public String getExecState() {
      return this.execState;
   }

   public void setExecState(String execState) {
      this.execState = execState;
   }

   public String getForbiddenIds() {
      return this.forbiddenIds;
   }

   public void setForbiddenIds(String forbiddenIds) {
      this.forbiddenIds = forbiddenIds;
   }

   public String getWaitingIds() {
      return this.waitingIds;
   }

   public void setWaitingIds(String waitingIds) {
      this.waitingIds = waitingIds;
   }

   public String getStartIds() {
      return this.startIds;
   }

   public void setStartIds(String startIds) {
      this.startIds = startIds;
   }

   public String getRunningIds() {
      return this.runningIds;
   }

   public void setRunningIds(String runningIds) {
      this.runningIds = runningIds;
   }

   public String getSuccessIds() {
      return this.successIds;
   }

   public void setSuccessIds(String successIds) {
      this.successIds = successIds;
   }

   public String getErrorsIds() {
      return this.errorsIds;
   }

   public void setErrorsIds(String errorsIds) {
      this.errorsIds = errorsIds;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
