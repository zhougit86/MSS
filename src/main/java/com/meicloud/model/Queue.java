package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("集群信息")
public class Queue {

   @ApiModelProperty(
      value = "集群编码",
      example = "1"
   )
   private String queueId;
   @ApiModelProperty(
      value = "集群编码",
      example = "GXT_UAT",
      required = true
   )
   private String queueCode;
   @ApiModelProperty(
      value = "集群名称",
      example = "默认集群",
      required = true
   )
   private String queueName;
   @ApiModelProperty(
      value = "git地址",
      example = "http://10.16.28.73/IBD/bdis.git"
   )
   private String gitUrl;
   @ApiModelProperty(
      value = "git登录账号",
      example = "root"
   )
   private String gitUserName;
   @ApiModelProperty(
      value = "git登录密码",
      example = "123456"
   )
   private String gitPassWord;
   @ApiModelProperty(
      value = "svn地址",
      example = "https://lijl38519.cn.midea.com/svn/YONGHUI/ECI/trunk/10_DEPLOY_DEV"
   )
   private String svnUrl;
   @ApiModelProperty(
      value = "svn登录账号",
      example = "root"
   )
   private String svnUserName;
   @ApiModelProperty(
      value = "svn登录密码",
      example = "123456"
   )
   private String svnPassWord;


   public String getQueueId() {
      return this.queueId;
   }

   public void setQueueId(String queueId) {
      this.queueId = queueId;
   }

   public String getQueueCode() {
      return this.queueCode;
   }

   public void setQueueCode(String queueCode) {
      this.queueCode = queueCode;
   }

   public String getQueueName() {
      return this.queueName;
   }

   public void setQueueName(String queueName) {
      this.queueName = queueName;
   }

   public String getGitUrl() {
      return this.gitUrl;
   }

   public void setGitUrl(String gitUrl) {
      this.gitUrl = gitUrl;
   }

   public String getGitUserName() {
      return this.gitUserName;
   }

   public void setGitUserName(String gitUserName) {
      this.gitUserName = gitUserName;
   }

   public String getGitPassWord() {
      return this.gitPassWord;
   }

   public void setGitPassWord(String gitPassWord) {
      this.gitPassWord = gitPassWord;
   }

   public String getSvnUrl() {
      return this.svnUrl;
   }

   public void setSvnUrl(String svnUrl) {
      this.svnUrl = svnUrl;
   }

   public String getSvnUserName() {
      return this.svnUserName;
   }

   public void setSvnUserName(String svnUserName) {
      this.svnUserName = svnUserName;
   }

   public String getSvnPassWord() {
      return this.svnPassWord;
   }

   public void setSvnPassWord(String svnPassWord) {
      this.svnPassWord = svnPassWord;
   }
}
