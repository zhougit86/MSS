package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("角色主题权限")
public class RoleTopic {

   @ApiModelProperty(
      value = "角色id",
      example = "1",
      required = true
   )
   private String roleId;
   @ApiModelProperty(
      value = "主题编码",
      example = "1",
      required = true
   )
   private Integer topicId;


   public String getRoleId() {
      return this.roleId;
   }

   public void setRoleId(String roleId) {
      this.roleId = roleId;
   }

   public Integer getTopicId() {
      return this.topicId;
   }

   public void setTopicId(Integer topicId) {
      this.topicId = topicId;
   }
}
