package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel("主题信息")
public class Topic {

   @ApiModelProperty(
      value = "主题ID",
      example = "12"
   )
   private Integer topicId;
   @ApiModelProperty(
      value = "主题名称",
      example = "FOR_融通",
      required = true
   )
   private String name;
   @ApiModelProperty(
      value = "集群编码",
      example = "1",
      required = true
   )
   private String queueId;
   @ApiModelProperty(
      value = "主题描述",
      example = "数仓数据分发到融通"
   )
   private String desc;
   @ApiModelProperty(
      value = "创建时间",
      example = "公共补录",
      hidden = true
   )
   private Date createDate;
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

   public Integer getTopicId() {
      return this.topicId;
   }

   public void setTopicId(Integer topicId) {
      this.topicId = topicId;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDesc() {
      return this.desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }

   public Date getCreateDate() {
      return this.createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }
}
