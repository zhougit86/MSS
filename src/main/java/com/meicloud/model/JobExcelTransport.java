package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import com.meicloud.model.Job;
import com.meicloud.model.NameValuePair;
import com.meicloud.utils.PropertiesUtil;
import java.io.Serializable;
import java.util.Map;

public class JobExcelTransport implements Serializable {

   private static final long serialVersionUID = 1L;
   private int rowNum;
   private String groupName;
   private String jobName;
   private String svnFile;
   private String retryName;
   private String debugLevel;
   private String appendParams;
   private int orderNo;
   private int runPriority;
   private boolean enable;
   private boolean online;
   private String chargorName;
   private String crudFlag;


   public Job formJob(String account, Map groupMap, Map retryRuleMap, Map chargorMap) throws Exception {
      if(!groupMap.containsKey(this.groupName)) {
         throw new Exception("导入文件中存在系统未配置组[" + this.groupName + "]");
      } else if(!retryRuleMap.containsKey(this.retryName)) {
         throw new Exception("导入文件中存在系统未配置跑数规则[" + this.retryName + "]");
      } else if(!chargorMap.containsKey(this.chargorName)) {
         throw new Exception("导入文件中存在系统未配置作业责任人[" + this.chargorName + "]");
      } else {
         String serverFile = "";
         String logFile = "";

         try {
            this.svnFile = PropertiesUtil.getEtlFilePathMid() + this.svnFile;
            serverFile = PropertiesUtil.getEtlFilePathServerPrefix();
            serverFile = serverFile + this.svnFile;
            logFile = serverFile.replace(".kjb", "_%sysdate%.log");
         } catch (Exception var8) {
            throw var8;
         }

         Job job = new Job();
         job.setGroupId(((NameValuePair)groupMap.get(this.groupName)).getId());
         job.setChargorId(((NameValuePair)chargorMap.get(this.chargorName)).getId());
         job.setJobName(this.jobName);
         job.setOrderNo(this.orderNo);
         job.setLogFile(logFile);
         job.setServerFile(serverFile);
         job.setSvnFile(this.svnFile);
         job.setEnable(this.enable);
         job.setRetryId(((NameValuePair)retryRuleMap.get(this.retryName)).getId());
         job.setcUser(account);
         job.setuUser(account);
         job.setDebugLevel(this.debugLevel);
         job.setOnline(this.online);
         job.setAppendParams(this.appendParams);
         job.setRunPriority(this.runPriority);
         return job;
      }
   }

   public int getRowNum() {
      return this.rowNum;
   }

   public void setRowNum(int rowNum) {
      this.rowNum = rowNum;
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

   public String getSvnFile() {
      return this.svnFile;
   }

   public void setSvnFile(String svnFile) {
      this.svnFile = svnFile;
   }

   public String getRetryName() {
      return this.retryName;
   }

   public void setRetryName(String retryName) {
      this.retryName = retryName;
   }

   public String getDebugLevel() {
      return this.debugLevel;
   }

   public void setDebugLevel(String debugLevel) {
      this.debugLevel = debugLevel;
   }

   public String getAppendParams() {
      return this.appendParams;
   }

   public void setAppendParams(String appendParams) {
      this.appendParams = appendParams;
   }

   public int getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(int orderNo) {
      this.orderNo = orderNo;
   }

   public int getRunPriority() {
      return this.runPriority;
   }

   public void setRunPriority(int runPriority) {
      this.runPriority = runPriority;
   }

   public boolean isEnable() {
      return this.enable;
   }

   public void setEnable(boolean enable) {
      this.enable = enable;
   }

   public boolean isOnline() {
      return this.online;
   }

   public void setOnline(boolean online) {
      this.online = online;
   }

   public String getChargorName() {
      return this.chargorName;
   }

   public void setChargorName(String chargorName) {
      this.chargorName = chargorName;
   }

   public String getCrudFlag() {
      return this.crudFlag;
   }

   public void setCrudFlag(String crudFlag) {
      this.crudFlag = crudFlag;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
