package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;
import java.util.Date;

public class RunJob implements Serializable {

   private static final long serialVersionUID = 1L;
   private int runJobId;
   private int runGroupId;
   private int jobId;
   private int groupId;
   private int executeServerId;
   private String ip;
   private int state;
   private String serverFile;
   private String logFile;
   private int orderNo;
   private String debugLevel;
   private Date createDate;
   private Date updateDate;
   private boolean rIsTime;
   private int rTimeout;
   private boolean rIsError;
   private int rIntervalTime;
   private int rErrorIntervalNum;
   private int rTimeoutIntervalNum;
   private Date dataLatestSend;
   private int errorRetryedCount;
   private int timeoutRetryedCount;
   private String msg;
   private int runVersion;
   private String appendParams;
   private String queueId;
   private int timeOutIntervalNum;
   private int timeOutRetryedNum;


   public RunJob() {}

   public boolean validataSend() {
      boolean result = true;
      if(this.getRunJobId() == 0 || this.getJobId() == 0) {
         result = false;
      }

      if(this.getServerFile() == null || "".equals(this.getServerFile())) {
         result = false;
      }

      if(this.getLogFile() == null || "".equals(this.getLogFile())) {
         result = false;
      }

      return result;
   }

   public int getTimeOutIntervalNum() {
      return this.timeOutIntervalNum;
   }

   public void setTimeOutIntervalNum(int timeOutIntervalNum) {
      this.timeOutIntervalNum = timeOutIntervalNum;
   }

   public int getTimeOutRetryedNum() {
      return this.timeOutRetryedNum;
   }

   public void setTimeOutRetryedNum(int timeOutRetryedNum) {
      this.timeOutRetryedNum = timeOutRetryedNum;
   }

   public RunJob(int jobId, int runJobId, int state, int executeServerId, String msg) {
      this.jobId = jobId;
      this.runJobId = runJobId;
      this.state = state;
      this.executeServerId = executeServerId;
      this.msg = msg;
   }

   public int getRunJobId() {
      return this.runJobId;
   }

   public void setRunJobId(int runJobId) {
      this.runJobId = runJobId;
   }

   public int getRunGroupId() {
      return this.runGroupId;
   }

   public void setRunGroupId(int runGroupId) {
      this.runGroupId = runGroupId;
   }

   public int getJobId() {
      return this.jobId;
   }

   public void setJobId(int jobId) {
      this.jobId = jobId;
   }

   public int getGroupId() {
      return this.groupId;
   }

   public void setGroupId(int groupId) {
      this.groupId = groupId;
   }

   public int getExecuteServerId() {
      return this.executeServerId;
   }

   public void setExecuteServerId(int executeServerId) {
      this.executeServerId = executeServerId;
   }

   public String getIp() {
      return this.ip;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }

   public int getState() {
      return this.state;
   }

   public void setState(int state) {
      this.state = state;
   }

   public String getServerFile() {
      return this.serverFile;
   }

   public void setServerFile(String serverFile) {
      this.serverFile = serverFile;
   }

   public String getLogFile() {
      return this.logFile;
   }

   public void setLogFile(String logFile) {
      this.logFile = logFile;
   }

   public int getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(int orderNo) {
      this.orderNo = orderNo;
   }

   public String getDebugLevel() {
      return this.debugLevel;
   }

   public void setDebugLevel(String debugLevel) {
      this.debugLevel = debugLevel;
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

   public boolean isrIsTime() {
      return this.rIsTime;
   }

   public void setrIsTime(boolean rIsTime) {
      this.rIsTime = rIsTime;
   }

   public int getrTimeout() {
      return this.rTimeout;
   }

   public void setrTimeout(int rTimeout) {
      this.rTimeout = rTimeout;
   }

   public boolean isrIsError() {
      return this.rIsError;
   }

   public void setrIsError(boolean rIsError) {
      this.rIsError = rIsError;
   }

   public int getrIntervalTime() {
      return this.rIntervalTime;
   }

   public void setrIntervalTime(int rIntervalTime) {
      this.rIntervalTime = rIntervalTime;
   }

   public int getrErrorIntervalNum() {
      return this.rErrorIntervalNum;
   }

   public void setrErrorIntervalNum(int rErrorIntervalNum) {
      this.rErrorIntervalNum = rErrorIntervalNum;
   }

   public int getrTimeoutIntervalNum() {
      return this.rTimeoutIntervalNum;
   }

   public void setrTimeoutIntervalNum(int rTimeoutIntervalNum) {
      this.rTimeoutIntervalNum = rTimeoutIntervalNum;
   }

   public Date getDataLatestSend() {
      return this.dataLatestSend;
   }

   public void setDataLatestSend(Date dataLatestSend) {
      this.dataLatestSend = dataLatestSend;
   }

   public int getErrorRetryedCount() {
      return this.errorRetryedCount;
   }

   public void setErrorRetryedCount(int errorRetryedCount) {
      this.errorRetryedCount = errorRetryedCount;
   }

   public int getTimeoutRetryedCount() {
      return this.timeoutRetryedCount;
   }

   public void setTimeoutRetryedCount(int timeoutRetryedCount) {
      this.timeoutRetryedCount = timeoutRetryedCount;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public int getRunVersion() {
      return this.runVersion;
   }

   public void setRunVersion(int runVersion) {
      this.runVersion = runVersion;
   }

   public String getAppendParams() {
      return this.appendParams;
   }

   public void setAppendParams(String appendParams) {
      this.appendParams = appendParams;
   }

   public String getQueueId() {
      return this.queueId;
   }

   public void setQueueId(String queueId) {
      this.queueId = queueId;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }

   public boolean validateKill() {
      boolean result = true;
      if(this.getRunJobId() == 0) {
         result = false;
      }

      if(this.getServerFile() == null || "".equals(this.getServerFile().trim())) {
         result = false;
      }

      return result;
   }

   public boolean validateSend() {
      boolean result = true;
      if(this.getRunJobId() == 0 || this.getJobId() == 0) {
         result = false;
      }

      if(this.getServerFile() == null || "".equals(this.getServerFile())) {
         result = false;
      }

      if(this.getLogFile() == null || "".equals(this.getLogFile())) {
         result = false;
      }

      return result;
   }

   public boolean validateReceive() {
      boolean result = true;
      if(this.getRunJobId() == 0 || this.getJobId() == 0) {
         result = false;
      }

      if(this.getServerFile() == null || "".equals(this.getServerFile())) {
         result = false;
      }

      if(this.getLogFile() == null || "".equals(this.getLogFile())) {
         result = false;
      }

      if(this.getRunVersion() == 0) {
         result = false;
      }

      return result;
   }
}
