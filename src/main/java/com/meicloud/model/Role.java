package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiModel("角色信息")
public class Role {

   @ApiModelProperty(
      value = "角色id",
      example = "3"
   )
   private String roleId;
   @ApiModelProperty(
      value = "角色编码",
      example = "ADMIN"
   )
   private String roleCode;
   @ApiModelProperty(
      value = "角色名称",
      example = "系统管理员"
   )
   private String roleName;
   @ApiModelProperty(
      value = "角色备注",
      example = "负责系统运维"
   )
   private String remark;
   @ApiModelProperty(
      value = "菜单权限编码集合",
      example = ""
   )
   private List permList = new ArrayList();
   @ApiModelProperty(
      value = "集群编码集合",
      example = ""
   )
   private List queueList = new ArrayList();
   @ApiModelProperty(
      value = "job组权限编码集合",
      example = ""
   )
   private List groupList = new ArrayList();
   @ApiModelProperty(
      value = "用户编码集合",
      example = ""
   )
   private List userList = new ArrayList();
   @ApiModelProperty(
      value = "主题权限集合",
      example = ""
   )
   private List topicList = new ArrayList();
   @ApiModelProperty(
      value = "用户权限键值队列",
      hidden = true
   )
   private Map userMap = new HashMap();


   public Map getUserMap() {
      return this.userMap;
   }

   public void setUserMap(Map userMap) {
      this.userMap = userMap;
   }

   public List getTopicList() {
      return this.topicList;
   }

   public void setTopicList(List topicList) {
      this.topicList = topicList;
   }

   public List getQueueList() {
      return this.queueList;
   }

   public void setQueueList(List queueList) {
      this.queueList = queueList;
   }

   public List getUserList() {
      return this.userList;
   }

   public void setUserList(List userList) {
      this.userList = userList;
   }

   public List getPermList() {
      return this.permList;
   }

   public void setPermList(List permList) {
      this.permList = permList;
   }

   public List getGroupList() {
      return this.groupList;
   }

   public void setGroupList(List groupList) {
      this.groupList = groupList;
   }

   public String getRoleId() {
      return this.roleId;
   }

   public void setRoleId(String roleId) {
      this.roleId = roleId;
   }

   public String getRoleCode() {
      return this.roleCode;
   }

   public void setRoleCode(String roleCode) {
      this.roleCode = roleCode;
   }

   public String getRoleName() {
      return this.roleName;
   }

   public void setRoleName(String roleName) {
      this.roleName = roleName;
   }

   public String getRemark() {
      return this.remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }
}
