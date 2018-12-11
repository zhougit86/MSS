package com.meicloud.dto;


public class GroupInfo {

   private Integer groupId;
   private String groupName;
   private String queueId;
   private Integer topicId;
   private Boolean ifFlag;


   public Integer getTopicId() {
      return this.topicId;
   }

   public void setTopicId(Integer topicId) {
      this.topicId = topicId;
   }

   public String getQueueId() {
      return this.queueId;
   }

   public void setQueueId(String queueId) {
      this.queueId = queueId;
   }

   public Boolean getIfFlag() {
      return this.ifFlag;
   }

   public void setIfFlag(Boolean ifFlag) {
      this.ifFlag = ifFlag;
   }

   public Integer getGroupId() {
      return this.groupId;
   }

   public void setGroupId(Integer groupId) {
      this.groupId = groupId;
   }

   public String getGroupName() {
      return this.groupName;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }
}
