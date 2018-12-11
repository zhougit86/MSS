package com.meicloud.dto;


public class UserInfo {

   private String userNo;
   private String userName;
   private Boolean ifFlag;


   public String getUserNo() {
      return this.userNo;
   }

   public void setUserNo(String userNo) {
      this.userNo = userNo;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public Boolean getIfFlag() {
      return this.ifFlag;
   }

   public void setIfFlag(Boolean ifFlag) {
      this.ifFlag = ifFlag;
   }
}
