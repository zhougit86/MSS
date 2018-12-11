package com.meicloud.model;

import java.util.Date;

public class RunGroup {

   private int runGroupId;
   private int groupId;
   private Date createDate;
   private Date updateDate;
   private String referedGroupIds;
   private boolean sordInGroup;
   private int parallelLimit;
   private int orderNoLatestUpdate;
   private int pushType;
   private String referedJobIds;


   public int getRunGroupId() {
      return this.runGroupId;
   }

   public void setRunGroupId(int runGroupId) {
      this.runGroupId = runGroupId;
   }

   public int getGroupId() {
      return this.groupId;
   }

   public void setGroupId(int groupId) {
      this.groupId = groupId;
   }

   public Date getCreateDate() {
      return this.createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public Date getUpdateDate() {
      return this.updateDate;
   }

   public void setUpdateDate(Date updateDate) {
      this.updateDate = updateDate;
   }

   public String getReferedGroupIds() {
      return this.referedGroupIds;
   }

   public void setReferedGroupIds(String referedGroupIds) {
      this.referedGroupIds = referedGroupIds;
   }

   public boolean isSordInGroup() {
      return this.sordInGroup;
   }

   public void setSordInGroup(boolean sordInGroup) {
      this.sordInGroup = sordInGroup;
   }

   public int getParallelLimit() {
      return this.parallelLimit;
   }

   public void setParallelLimit(int parallelLimit) {
      this.parallelLimit = parallelLimit;
   }

   public int getOrderNoLatestUpdate() {
      return this.orderNoLatestUpdate;
   }

   public void setOrderNoLatestUpdate(int orderNoLatestUpdate) {
      this.orderNoLatestUpdate = orderNoLatestUpdate;
   }

   public int getPushType() {
      return this.pushType;
   }

   public void setPushType(int pushType) {
      this.pushType = pushType;
   }

   public String getReferedJobIds() {
      return this.referedJobIds;
   }

   public void setReferedJobIds(String referedJobIds) {
      this.referedJobIds = referedJobIds;
   }
}
