package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("角色JOB权限")
public class RoleGroup {

   @ApiModelProperty(
      value = "角色id",
      example = "1",
      required = true
   )
   private String roleId;
   @ApiModelProperty(
      value = "job组Id",
      example = "1",
      required = true
   )
   private Integer groupId;


   public String getRoleId() {
      return this.roleId;
   }

   public void setRoleId(String roleId) {
      this.roleId = roleId;
   }

   public Integer getGroupId() {
      return this.groupId;
   }

   public void setGroupId(Integer groupId) {
      this.groupId = groupId;
   }
}
