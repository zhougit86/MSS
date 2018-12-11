package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("角色集群控制")
public class RoleQueue {

   @ApiModelProperty(
      value = "角色id",
      example = "1",
      required = true
   )
   private String roleId;
   @ApiModelProperty(
      value = "集群Id",
      example = "1",
      required = true
   )
   private Integer queueId;


   public String getRoleId() {
      return this.roleId;
   }

   public void setRoleId(String roleId) {
      this.roleId = roleId;
   }

   public Integer getQueueId() {
      return this.queueId;
   }

   public void setQueueId(Integer queueId) {
      this.queueId = queueId;
   }
}
