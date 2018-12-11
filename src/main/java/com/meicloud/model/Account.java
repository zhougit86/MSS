package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel("账号信息")
public class Account {

   @ApiModelProperty(
      value = "用户账号",
      example = "12",
      required = true
   )
   private String account;
   @ApiModelProperty(
      value = "用户新密码",
      example = "12",
      required = true
   )
   private String password;
   @ApiModelProperty(
      value = "用户旧密码",
      example = "12"
   )
   private String oldPassword;
   @ApiModelProperty(
      value = "是否启用",
      example = "true",
      required = true
   )
   private boolean enable;
   @ApiModelProperty(
      value = "用户姓名",
      example = "邹汉标",
      required = true
   )
   private String realName;
   @ApiModelProperty(
      value = "SVN是否启用",
      example = "true",
      required = true
   )
   private boolean svnOn;
   @ApiModelProperty(
      value = "创建日期",
      hidden = true
   )
   private Date createDate;


   public String getAccount() {
      return this.account;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getOldPassword() {
      return this.oldPassword;
   }

   public void setOldPassword(String oldPassword) {
      this.oldPassword = oldPassword;
   }

   public boolean isEnable() {
      return this.enable;
   }

   public void setEnable(boolean enable) {
      this.enable = enable;
   }

   public Date getCreateDate() {
      return this.createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public String getRealName() {
      return this.realName;
   }

   public void setRealName(String realName) {
      this.realName = realName;
   }

   public boolean isSvnOn() {
      return this.svnOn;
   }

   public void setSvnOn(boolean svnOn) {
      this.svnOn = svnOn;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
