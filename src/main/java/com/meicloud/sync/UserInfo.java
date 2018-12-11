package com.meicloud.sync;


public class UserInfo {

   private String userId;
   private String userAccount;
   private String userName;
   private String userEmail;
   private String effStartDate;
   private String effEndDate;
   private String userMobileNo;
   private String positionName;
   private String opStatus;


   public String getUserId() {
      return this.userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getOpStatus() {
      return this.opStatus;
   }

   public void setOpStatus(String opStatus) {
      this.opStatus = opStatus;
   }

   public String getUserAccount() {
      return this.userAccount;
   }

   public void setUserAccount(String userAccount) {
      this.userAccount = userAccount;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getUserEmail() {
      return this.userEmail;
   }

   public void setUserEmail(String userEmail) {
      this.userEmail = userEmail;
   }

   public String getEffStartDate() {
      return this.effStartDate;
   }

   public void setEffStartDate(String effStartDate) {
      this.effStartDate = effStartDate;
   }

   public String getEffEndDate() {
      return this.effEndDate;
   }

   public void setEffEndDate(String effEndDate) {
      this.effEndDate = effEndDate;
   }

   public String getUserMobileNo() {
      return this.userMobileNo;
   }

   public void setUserMobileNo(String userMobileNo) {
      this.userMobileNo = userMobileNo;
   }

   public String getPositionName() {
      return this.positionName;
   }

   public void setPositionName(String positionName) {
      this.positionName = positionName;
   }
}
