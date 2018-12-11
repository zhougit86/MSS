package com.meicloud.dto;


public class QueueInfo {

   private String queueId;
   private String queueName;
   private Boolean ifFlag;


   public String getQueueId() {
      return this.queueId;
   }

   public void setQueueId(String queueId) {
      this.queueId = queueId;
   }

   public String getQueueName() {
      return this.queueName;
   }

   public void setQueueName(String queueName) {
      this.queueName = queueName;
   }

   public Boolean getIfFlag() {
      return this.ifFlag;
   }

   public void setIfFlag(Boolean ifFlag) {
      this.ifFlag = ifFlag;
   }
}
