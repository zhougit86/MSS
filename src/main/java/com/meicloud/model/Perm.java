package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel("菜单权限信息")
public class Perm {

   @ApiModelProperty(
      value = "菜单权限id",
      example = "1"
   )
   private String permId;
   @ApiModelProperty(
      value = "父菜单权限编号",
      example = "0"
   )
   private String permParentId;
   @ApiModelProperty(
      value = "菜单权限编码",
      example = "0.1"
   )
   private String permCode;
   @ApiModelProperty(
      value = "菜单权限名称",
      example = "测试菜单"
   )
   private String permName;
   @ApiModelProperty(
      value = "权限跳转路径",
      example = "/test/list"
   )
   private String permPath;
   @ApiModelProperty(
      value = "菜单类型",
      example = "X"
   )
   private String permType;
   @ApiModelProperty(
      value = "菜单排序",
      hidden = true
   )
   private int sort = 0;
   @ApiModelProperty(
      value = "创建时间",
      hidden = true
   )
   private Date createDate;


   public String getPermId() {
      return this.permId;
   }

   public void setPermId(String permId) {
      this.permId = permId;
   }

   public String getPermParentId() {
      return this.permParentId;
   }

   public void setPermParentId(String permParentId) {
      this.permParentId = permParentId;
   }

   public String getPermCode() {
      return this.permCode;
   }

   public void setPermCode(String permCode) {
      this.permCode = permCode;
   }

   public String getPermName() {
      return this.permName;
   }

   public void setPermName(String permName) {
      this.permName = permName;
   }

   public String getPermPath() {
      return this.permPath;
   }

   public void setPermPath(String permPath) {
      this.permPath = permPath;
   }

   public String getPermType() {
      return this.permType;
   }

   public void setPermType(String permType) {
      this.permType = permType;
   }

   public int getSort() {
      return this.sort;
   }

   public void setSort(int sort) {
      this.sort = sort;
   }

   public Date getCreateDate() {
      return this.createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }
}
