package com.meicloud.model;

import com.meicloud.model.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel("用户信息")
public class User extends BaseBean implements Serializable {

   private static final long serialVersionUID = 1L;
   @ApiModelProperty(
      value = "登录账号",
      example = "lijl"
   )
   private String userAccount;
   @ApiModelProperty(
      value = "用户名称",
      example = "罗焕妍"
   )
   private String userName;
   @ApiModelProperty(
      value = "用户邮箱",
      example = "luoh@midea.com"
   )
   private String userEmail;
   @ApiModelProperty(
      value = "有效开始时间",
      example = "2017-09-07"
   )
   private String effStartDate;
   @ApiModelProperty(
      value = "有效结束时间",
      example = "9999-12-12"
   )
   private String effEndDate;
   @ApiModelProperty(
      value = "用户号码",
      example = "12345678998"
   )
   private String userMobileNo;
   @ApiModelProperty(
      value = "岗位",
      example = "单证员"
   )
   private String positionName;


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
