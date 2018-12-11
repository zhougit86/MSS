package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("责任人信息")
public class Chargor {

   @ApiModelProperty(
      value = "责任人ID",
      example = "12"
   )
   private Integer id;
   @ApiModelProperty(
      value = "责任人姓名",
      example = "责任人",
      required = true
   )
   private String name;
   @ApiModelProperty(
      value = "责任人电话",
      example = "18520836943"
   )
   private String phone;
   @ApiModelProperty(
      value = "责任人邮箱",
      example = "lijl38@qq.com"
   )
   private String email;
   @ApiModelProperty(
      value = "集群编码",
      example = "1",
      required = true
   )
   private String queueId;
   @ApiModelProperty(
      hidden = true
   )
   private String queueName;


   public String getQueueName() {
      return this.queueName;
   }

   public void setQueueName(String queueName) {
      this.queueName = queueName;
   }

   public String getQueueId() {
      return this.queueId;
   }

   public void setQueueId(String queueId) {
      this.queueId = queueId;
   }

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPhone() {
      return this.phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
