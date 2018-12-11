package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;

public class KillJobParam {

   private int runJobId;
   private int runVersion;
   private int state;
   private String ip;
   private String serverFile;
   private String appendParams;
   private String msg;
   private int errorRetryedCount;
   private static int waitingState = 1;
   private static int runningState = 2;
   private static int errorState = 3;
   private static int cancelState = 4;
   private static int successState = 5;


   public String getAppendParams() {
      return this.appendParams;
   }

   public void setAppendParams(String appendParams) {
      this.appendParams = appendParams;
   }

   public int getRunJobId() {
      return this.runJobId;
   }

   public void setRunJobId(int runJobId) {
      this.runJobId = runJobId;
   }

   public int getRunVersion() {
      return this.runVersion;
   }

   public void setRunVersion(int runVersion) {
      this.runVersion = runVersion;
   }

   public int getState() {
      return this.state;
   }

   public void setState(int state) {
      this.state = state;
   }

   public String getIp() {
      return this.ip;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }

   public String getServerFile() {
      return this.serverFile;
   }

   public void setServerFile(String serverFile) {
      this.serverFile = serverFile;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public static int getWaitingState() {
      return waitingState;
   }

   public static void setWaitingState(int waitingState) {
      waitingState = waitingState;
   }

   public static int getRunningState() {
      return runningState;
   }

   public static void setRunningState(int runningState) {
      runningState = runningState;
   }

   public static int getErrorState() {
      return errorState;
   }

   public static void setErrorState(int errorState) {
      errorState = errorState;
   }

   public static int getCancelState() {
      return cancelState;
   }

   public static void setCancelState(int cancelState) {
      cancelState = cancelState;
   }

   public static int getSuccessState() {
      return successState;
   }

   public static void setSuccessState(int successState) {
      successState = successState;
   }

   public int getErrorRetryedCount() {
      return this.errorRetryedCount;
   }

   public void setErrorRetryedCount(int errorRetryedCount) {
      this.errorRetryedCount = errorRetryedCount;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
