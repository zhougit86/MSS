package com.meicloud.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public abstract class BaseBean implements Serializable {

   private static final long serialVersionUID = 1L;
   @ApiModelProperty(
      value = "主键",
      hidden = true
   )
   protected String id;
   @ApiModelProperty(
      value = "主键（自动生成）",
      example = "1"
   )
   private String uuid;
   @ApiModelProperty(
      value = "备注",
      hidden = true
   )
   protected String remarks;
   @ApiModelProperty(
      value = "创建者",
      hidden = true
   )
   protected String createBy;
   @ApiModelProperty(
      value = "创建日期",
      hidden = true
   )
   protected Date createDate = new Date();
   @ApiModelProperty(
      value = "更新者",
      hidden = true
   )
   protected String updateBy;
   @ApiModelProperty(
      value = "更新日期",
      hidden = true
   )
   protected Date updateDate = new Date();
   @ApiModelProperty(
      value = "删除标记（0：正常；1：删除；）",
      hidden = true
   )
   protected String delFlag = "0";


   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public String getRemarks() {
      return this.remarks;
   }

   public void setRemarks(String remarks) {
      this.remarks = remarks;
   }

   public String getCreateBy() {
      return this.createBy;
   }

   public void setCreateBy(String createBy) {
      this.createBy = createBy;
   }

   public String getUpdateBy() {
      return this.updateBy;
   }

   public void setUpdateBy(String updateBy) {
      this.updateBy = updateBy;
   }

   public Date getCreateDate() {
      return this.createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public Date getUpdateDate() {
      return this.updateDate;
   }

   public void setUpdateDate(Date updateDate) {
      this.updateDate = updateDate;
   }

   public String getDelFlag() {
      return this.delFlag;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }
}
