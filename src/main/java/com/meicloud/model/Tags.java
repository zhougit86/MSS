package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("标记信息")
public class Tags {

   @ApiModelProperty(
      value = "标记ID",
      example = "12"
   )
   private int tagId;
   @ApiModelProperty(
      value = "标记名称",
      example = "12"
   )
   private String tagName;
   @ApiModelProperty(
      hidden = true
   )
   private int tagNum;
   @ApiModelProperty("组ID")
   private int groupId;
   @ApiModelProperty(
      hidden = true
   )
   private String queueId;
   @ApiModelProperty(
      hidden = true
   )
   private int referId;


   public String getQueueId() {
      return this.queueId;
   }

   public void setQueueId(String queueId) {
      this.queueId = queueId;
   }

   public int getTagId() {
      return this.tagId;
   }

   public void setTagId(int tagId) {
      this.tagId = tagId;
   }

   public String getTagName() {
      return this.tagName;
   }

   public void setTagName(String tagName) {
      this.tagName = tagName;
   }

   public int getTagNum() {
      return this.tagNum;
   }

   public void setTagNum(int tagNum) {
      this.tagNum = tagNum;
   }

   public int getGroupId() {
      return this.groupId;
   }

   public void setGroupId(int groupId) {
      this.groupId = groupId;
   }

   public int getReferId() {
      return this.referId;
   }

   public void setReferId(int referId) {
      this.referId = referId;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
