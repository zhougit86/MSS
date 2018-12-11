package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel("组织层级配置")
public class GroupLevel {

   @ApiModelProperty(
      value = "组织层级ID",
      example = "10"
   )
   private Integer levelId;
   @ApiModelProperty(
      value = "组织层析排序号",
      example = "10"
   )
   private Integer levelOrderNo;
   @ApiModelProperty(
      value = "组织层级名称",
      example = "YH_DATA_TEST",
      required = true
   )
   private String levelName;
   @ApiModelProperty(
      value = "集群编码",
      example = "1",
      required = true
   )
   private String queueId;
   @ApiModelProperty(
      value = "组织列表",
      hidden = true
   )
   private List groupList;
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

   public Integer getLevelId() {
      return this.levelId;
   }

   public void setLevelId(Integer levelId) {
      this.levelId = levelId;
   }

   public Integer getLevelOrderNo() {
      return this.levelOrderNo;
   }

   public void setLevelOrderNo(Integer levelOrderNo) {
      this.levelOrderNo = levelOrderNo;
   }

   public String getLevelName() {
      return this.levelName;
   }

   public void setLevelName(String levelName) {
      this.levelName = levelName;
   }

   public List getGroupList() {
      return this.groupList;
   }

   public void setGroupList(List groupList) {
      this.groupList = groupList;
   }
}
