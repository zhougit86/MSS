package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;
import java.util.Date;

public class JobLog implements Serializable {

   private int logId;
   private int jobId;
   private int groupId;
   private Date startDate;
   private Date endDate;
   private String fileName;
   private String groupName;
   private String jobName;
   private int execTime;
   private String ipAddress;
   private int executeServerId;
   private int runJobId;
   private int runVersion;
   private int state;
   private String msg;
   private Date createDate;
   private String cmd;
   private static final long serialVersionUID = 1L;
   private String execState;
   private String consoleFile;


   public JobLog() {}

   public JobLog(int jobId, int runJobId, int runVersion, int state, String msg, int executeServerId, String consoleFile, String cmd) {
      this.jobId = jobId;
      this.runJobId = runJobId;
      this.runVersion = runVersion;
      this.state = state;
      this.msg = msg;
      this.executeServerId = executeServerId;
      this.consoleFile = consoleFile;
      this.cmd = cmd;
   }

   public int getGroupId() {
      return this.groupId;
   }

   public void setGroupId(int groupId) {
      this.groupId = groupId;
   }

   public int getLogId() {
      return this.logId;
   }

   public void setLogId(int logId) {
      this.logId = logId;
   }

   public int getJobId() {
      return this.jobId;
   }

   public void setJobId(int jobId) {
      this.jobId = jobId;
   }

   public String getExecState() {
      return this.execState;
   }

   public void setExecState(String execState) {
      this.execState = execState;
   }

   public String getConsoleFile() {
      return this.consoleFile;
   }

   public void setConsoleFile(String consoleFile) {
      this.consoleFile = consoleFile;
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

   public String getGroupName() {
      return this.groupName;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }

   public String getJobName() {
      return this.jobName;
   }

   public void setJobName(String jobName) {
      this.jobName = jobName;
   }

   public int getExecTime() {
      return this.execTime;
   }

   public void setExecTime(int execTime) {
      this.execTime = execTime;
   }

   public String getFileName() {
      return this.fileName;
   }

   public void setFileName(String fileName) {
      this.fileName = fileName;
   }

   public int getExecuteServerId() {
      return this.executeServerId;
   }

   public void setExecuteServerId(int executeServerId) {
      this.executeServerId = executeServerId;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }

   public String getIpAddress() {
      return this.ipAddress;
   }

   public void setIpAddress(String ipAddress) {
      this.ipAddress = ipAddress;
   }

   public int getRunJobId() {
      return this.runJobId;
   }

   public void setRunJobId(int runJobId) {
      this.runJobId = runJobId;
   }

   public int getRunVersion() {
      return this.runVersion;
   }

   public void setRunVersion(int runVersion) {
      this.runVersion = runVersion;
   }

   public int getState() {
      return this.state;
   }

   public void setState(int state) {
      this.state = state;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public Date getCreateDate() {
      return this.createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public String getCmd() {
      return this.cmd;
   }

   public void setCmd(String cmd) {
      this.cmd = cmd;
   }
}
