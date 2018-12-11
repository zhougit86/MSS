package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("角色JOB权限")
public class RolePerm {

   @ApiModelProperty(
      value = "角色id",
      example = "1",
      required = true
   )
   private String roleId;
   @ApiModelProperty(
      value = "菜单编码",
      example = "1",
      required = true
   )
   private String permId;
   @ApiModelProperty(
      hidden = true
   )
   private String permPath;


   public String getPermPath() {
      return this.permPath;
   }

   public void setPermPath(String permPath) {
      this.permPath = permPath;
   }

   public String getRoleId() {
      return this.roleId;
   }

   public void setRoleId(String roleId) {
      this.roleId = roleId;
   }

   public String getPermId() {
      return this.permId;
   }

   public void setPermId(String permId) {
      this.permId = permId;
   }
}
