package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;
import java.util.Date;

public class KettleJobLog implements Serializable {

   private static final long serialVersionUID = 1L;
   private int idJob;
   private String channelId;
   private String jobName;
   private String status;
   private int linesRead;
   private int linesWritten;
   private int linesUpdate;
   private int linesInput;
   private int linesOutput;
   private int linesRejected;
   private int errors;
   private Date startDate;
   private Date endDate;
   private Date logDate;
   private Date depDate;
   private Date replayDate;
   private int groupId;
   private String groupName;


   public int getGroupId() {
      return this.groupId;
   }

   public void setGroupId(int groupId) {
      this.groupId = groupId;
   }

   public int getIdJob() {
      return this.idJob;
   }

   public void setIdJob(int idJob) {
      this.idJob = idJob;
   }

   public String getChannelId() {
      return this.channelId;
   }

   public void setChannelId(String channelId) {
      this.channelId = channelId;
   }

   public String getJobName() {
      return this.jobName;
   }

   public void setJobName(String jobName) {
      this.jobName = jobName;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public int getLinesRead() {
      return this.linesRead;
   }

   public void setLinesRead(int linesRead) {
      this.linesRead = linesRead;
   }

   public int getLinesWritten() {
      return this.linesWritten;
   }

   public void setLinesWritten(int linesWritten) {
      this.linesWritten = linesWritten;
   }

   public int getLinesUpdate() {
      return this.linesUpdate;
   }

   public void setLinesUpdate(int linesUpdate) {
      this.linesUpdate = linesUpdate;
   }

   public int getLinesInput() {
      return this.linesInput;
   }

   public void setLinesInput(int linesInput) {
      this.linesInput = linesInput;
   }

   public int getLinesOutput() {
      return this.linesOutput;
   }

   public void setLinesOutput(int linesOutput) {
      this.linesOutput = linesOutput;
   }

   public int getLinesRejected() {
      return this.linesRejected;
   }

   public void setLinesRejected(int linesRejected) {
      this.linesRejected = linesRejected;
   }

   public int getErrors() {
      return this.errors;
   }

   public void setErrors(int errors) {
      this.errors = errors;
   }

   public Date getStartDate() {
      return this.startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public Date getEndDate() {
      return this.endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public Date getLogDate() {
      return this.logDate;
   }

   public void setLogDate(Date logDate) {
      this.logDate = logDate;
   }

   public Date getDepDate() {
      return this.depDate;
   }

   public void setDepDate(Date depDate) {
      this.depDate = depDate;
   }

   public Date getReplayDate() {
      return this.replayDate;
   }

   public void setReplayDate(Date replayDate) {
      this.replayDate = replayDate;
   }

   public String getGroupName() {
      return this.groupName;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
