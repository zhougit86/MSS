package com.meicloud.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class ScheduleParameter implements Serializable {

   private static final long serialVersionUID = 5253760668558734247L;
   @ApiModelProperty("参数ID")
   private Long scheduleParaId;
   @ApiModelProperty(
      value = "参数名称",
      required = true
   )
   private String parameterName;
   @ApiModelProperty(
      value = "参数排序",
      required = true,
      example = "0"
   )
   private Integer parameterSort;
   @ApiModelProperty(
      value = "主题名称",
      hidden = true
   )
   private String subjectName;
   @ApiModelProperty(
      value = "JOB名称",
      hidden = true
   )
   private String mappingName;
   @ApiModelProperty(
      value = "JOB名称",
      hidden = true
   )
   private String sessionName;
   @ApiModelProperty(
      value = "JOB名称",
      required = true
   )
   private String workflowName;
   @ApiModelProperty(
      value = "参数值",
      required = true
   )
   private String parameterValue;
   @ApiModelProperty(
      value = "时间格式化类型 YYYY-MM-DD",
      required = true,
      example = "YYYY-MM-DD"
   )
   private String formatMask;
   @ApiModelProperty(
      hidden = true
   )
   private Integer paraOffset;
   @ApiModelProperty(
      hidden = true
   )
   private String frequency;
   @ApiModelProperty(
      hidden = true
   )
   private String enableFlag;
   @ApiModelProperty(
      value = "有效开始时间",
      hidden = true
   )
   private Date startDateActive;
   @ApiModelProperty(
      value = "有效结束时间",
      hidden = true
   )
   private Date endDateActive;
   @ApiModelProperty(
      hidden = true
   )
   private Date creationDate;
   @ApiModelProperty(
      hidden = true
   )
   private String createdBy;
   @ApiModelProperty(
      hidden = true
   )
   private String lastUpdatedBy;
   @ApiModelProperty(
      hidden = true
   )
   private Date lastUpdateDate;
   @ApiModelProperty(
      hidden = true
   )
   private Integer lastUpdateLogin;
   @ApiModelProperty(
      hidden = true
   )
   private String parameterDesc;
   @ApiModelProperty(
      hidden = true
   )
   private String parameterValueIni;


   public Long getScheduleParaId() {
      return this.scheduleParaId;
   }

   public void setScheduleParaId(Long scheduleParaId) {
      this.scheduleParaId = scheduleParaId;
   }

   public String getParameterName() {
      return this.parameterName;
   }

   public void setParameterName(String parameterName) {
      this.parameterName = parameterName;
   }

   public Integer getParameterSort() {
      return this.parameterSort;
   }

   public void setParameterSort(Integer parameterSort) {
      this.parameterSort = parameterSort;
   }

   public String getSubjectName() {
      return this.subjectName;
   }

   public void setSubjectName(String subjectName) {
      this.subjectName = subjectName;
   }

   public String getMappingName() {
      return this.mappingName;
   }

   public void setMappingName(String mappingName) {
      this.mappingName = mappingName;
   }

   public String getSessionName() {
      return this.sessionName;
   }

   public void setSessionName(String sessionName) {
      this.sessionName = sessionName;
   }

   public String getWorkflowName() {
      return this.workflowName;
   }

   public void setWorkflowName(String workflowName) {
      this.workflowName = workflowName;
   }

   public String getParameterValue() {
      return this.parameterValue;
   }

   public void setParameterValue(String parameterValue) {
      this.parameterValue = parameterValue;
   }

   public String getFormatMask() {
      return this.formatMask;
   }

   public void setFormatMask(String formatMask) {
      this.formatMask = formatMask;
   }

   public Integer getParaOffset() {
      return this.paraOffset;
   }

   public void setParaOffset(Integer paraOffset) {
      this.paraOffset = paraOffset;
   }

   public String getFrequency() {
      return this.frequency;
   }

   public void setFrequency(String frequency) {
      this.frequency = frequency;
   }

   public String getEnableFlag() {
      return this.enableFlag;
   }

   public void setEnableFlag(String enableFlag) {
      this.enableFlag = enableFlag;
   }

   public Date getStartDateActive() {
      return this.startDateActive;
   }

   public void setStartDateActive(Date startDateActive) {
      this.startDateActive = startDateActive;
   }

   public Date getEndDateActive() {
      return this.endDateActive;
   }

   public void setEndDateActive(Date endDateActive) {
      this.endDateActive = endDateActive;
   }

   public Date getCreationDate() {
      return this.creationDate;
   }

   public void setCreationDate(Date creationDate) {
      this.creationDate = creationDate;
   }

   public String getCreatedBy() {
      return this.createdBy;
   }

   public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
   }

   public String getLastUpdatedBy() {
      return this.lastUpdatedBy;
   }

   public void setLastUpdatedBy(String lastUpdatedBy) {
      this.lastUpdatedBy = lastUpdatedBy;
   }

   public Date getLastUpdateDate() {
      return this.lastUpdateDate;
   }

   public void setLastUpdateDate(Date lastUpdateDate) {
      this.lastUpdateDate = lastUpdateDate;
   }

   public Integer getLastUpdateLogin() {
      return this.lastUpdateLogin;
   }

   public void setLastUpdateLogin(Integer lastUpdateLogin) {
      this.lastUpdateLogin = lastUpdateLogin;
   }

   public String getParameterDesc() {
      return this.parameterDesc;
   }

   public void setParameterDesc(String parameterDesc) {
      this.parameterDesc = parameterDesc;
   }

   public String getParameterValueIni() {
      return this.parameterValueIni;
   }

   public void setParameterValueIni(String parameterValueIni) {
      this.parameterValueIni = parameterValueIni;
   }

   public static long getSerialversionuid() {
      return 5253760668558734247L;
   }
}
