package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("角色用户")
public class RoleUser {

   @ApiModelProperty(
      value = "角色id",
      example = "1",
      required = true
   )
   private String roleId;
   @ApiModelProperty(
      value = "用户账号",
      example = "lijl",
      required = true
   )
   private String userNo;
   @ApiModelProperty(
      value = "用户姓名",
      hidden = true
   )
   private String userName;


   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getRoleId() {
      return this.roleId;
   }

   public void setRoleId(String roleId) {
      this.roleId = roleId;
   }

   public String getUserNo() {
      return this.userNo;
   }

   public void setUserNo(String userNo) {
      this.userNo = userNo;
   }
}
