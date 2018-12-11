package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("跑数规则信息")
public class RetryRule {

   @ApiModelProperty(
      value = "跑数规则ID",
      example = "1"
   )
   private int retryId;
   @ApiModelProperty(
      value = "规则名称",
      example = "1",
      required = true
   )
   private String retryName;
   @ApiModelProperty(
      value = "规则描述",
      example = "1",
      required = true
   )
   private String retryDesc;
   @ApiModelProperty(
      value = "是否重试",
      example = "true",
      required = true
   )
   private boolean isTime;
   @ApiModelProperty(
      value = "超时时间S",
      example = "1",
      required = true
   )
   private int timeOut = 0;
   @ApiModelProperty(
      value = "报错是否重试",
      example = "true",
      required = true
   )
   private boolean isError;
   @ApiModelProperty(
      value = "间隔时间S",
      example = "1",
      required = true
   )
   private int intervalTime = 0;
   @ApiModelProperty(
      value = "错误最多重试次数",
      example = "2",
      required = true
   )
   private int errorIntervalNum = 0;
   @ApiModelProperty(
      value = "超时规则重试次数",
      example = "1",
      required = true
   )
   private int timeIntervalNum = 0;


   public int getRetryId() {
      return this.retryId;
   }

   public void setRetryId(int retryId) {
      this.retryId = retryId;
   }

   public String getRetryName() {
      return this.retryName;
   }

   public void setRetryName(String retryName) {
      this.retryName = retryName;
   }

   public String getRetryDesc() {
      return this.retryDesc;
   }

   public void setRetryDesc(String retryDesc) {
      this.retryDesc = retryDesc;
   }

   public boolean isTime() {
      return this.isTime;
   }

   public void setTime(boolean isTime) {
      this.isTime = isTime;
   }

   public int getTimeOut() {
      return this.timeOut;
   }

   public void setTimeOut(int timeOut) {
      this.timeOut = timeOut;
   }

   public boolean isError() {
      return this.isError;
   }

   public void setError(boolean isError) {
      this.isError = isError;
   }

   public int getIntervalTime() {
      return this.intervalTime;
   }

   public void setIntervalTime(int intervalTime) {
      this.intervalTime = intervalTime;
   }

   public int getErrorIntervalNum() {
      return this.errorIntervalNum;
   }

   public void setErrorIntervalNum(int errorIntervalNum) {
      this.errorIntervalNum = errorIntervalNum;
   }

   public int getTimeIntervalNum() {
      return this.timeIntervalNum;
   }

   public void setTimeIntervalNum(int timeIntervalNum) {
      this.timeIntervalNum = timeIntervalNum;
   }
}
