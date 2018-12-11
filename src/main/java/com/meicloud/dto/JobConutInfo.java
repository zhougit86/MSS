package com.meicloud.dto;


public class JobConutInfo {

   private int unPushJobCount = 0;
   private int runTimeWaitJobCount = 0;
   private int queueJobCount = 0;
   private int runningJobCount = 0;
   private int expireJobCount = 0;
   private int errorJobCount = 0;
   private int cancelJobCount = 0;
   private int successJobCount = 0;
   private int totalJobCount = 0;
   private int runServers = 0;


   public int getRunServers() {
      return this.runServers;
   }

   public void setRunServers(int runServers) {
      this.runServers = runServers;
   }

   public int getUnPushJobCount() {
      return this.unPushJobCount;
   }

   public void setUnPushJobCount(int unPushJobCount) {
      this.unPushJobCount = unPushJobCount;
   }

   public int getRunTimeWaitJobCount() {
      return this.runTimeWaitJobCount;
   }

   public void setRunTimeWaitJobCount(int runTimeWaitJobCount) {
      this.runTimeWaitJobCount = runTimeWaitJobCount;
   }

   public int getQueueJobCount() {
      return this.queueJobCount;
   }

   public void setQueueJobCount(int queueJobCount) {
      this.queueJobCount = queueJobCount;
   }

   public int getRunningJobCount() {
      return this.runningJobCount;
   }

   public void setRunningJobCount(int runningJobCount) {
      this.runningJobCount = runningJobCount;
   }

   public int getExpireJobCount() {
      return this.expireJobCount;
   }

   public void setExpireJobCount(int expireJobCount) {
      this.expireJobCount = expireJobCount;
   }

   public int getErrorJobCount() {
      return this.errorJobCount;
   }

   public void setErrorJobCount(int errorJobCount) {
      this.errorJobCount = errorJobCount;
   }

   public int getCancelJobCount() {
      return this.cancelJobCount;
   }

   public void setCancelJobCount(int cancelJobCount) {
      this.cancelJobCount = cancelJobCount;
   }

   public int getSuccessJobCount() {
      return this.successJobCount;
   }

   public void setSuccessJobCount(int successJobCount) {
      this.successJobCount = successJobCount;
   }

   public int getTotalJobCount() {
      return this.totalJobCount;
   }

   public void setTotalJobCount(int totalJobCount) {
      this.totalJobCount = totalJobCount;
   }
}
