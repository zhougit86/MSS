package com.meicloud.dto;


public class TopicInfo {

   private Integer topicId;
   private String topicName;
   private String queueId;
   private Boolean ifFlag;


   public String getQueueId() {
      return this.queueId;
   }

   public void setQueueId(String queueId) {
      this.queueId = queueId;
   }

   public Integer getTopicId() {
      return this.topicId;
   }

   public void setTopicId(Integer topicId) {
      this.topicId = topicId;
   }

   public String getTopicName() {
      return this.topicName;
   }

   public void setTopicName(String topicName) {
      this.topicName = topicName;
   }

   public Boolean getIfFlag() {
      return this.ifFlag;
   }

   public void setIfFlag(Boolean ifFlag) {
      this.ifFlag = ifFlag;
   }
}
