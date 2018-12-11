package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import com.meicloud.utils.StringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel("job信息")
public class Job {

   @ApiModelProperty(
      value = "作业id",
      example = "1"
   )
   private int jobId;
   @ApiModelProperty(
      value = "作业组id",
      example = "1"
   )
   private int groupId;
   @ApiModelProperty(
      value = "跑数规则ID",
      example = "1"
   )
   private int retryId;
   @ApiModelProperty(
      value = "责任人账号1",
      example = "1"
   )
   private String chargorName;
   @ApiModelProperty(
      value = "责任人账号2",
      example = "1"
   )
   private String chargorName2;
   @ApiModelProperty(
      value = "责任人账号3",
      example = "1"
   )
   private String chargorName3;
   @ApiModelProperty(
      value = "作业名称",
      example = "1"
   )
   private String jobName;
   @ApiModelProperty(
      value = "执行顺序号",
      example = "1"
   )
   private int orderNo;
   @ApiModelProperty(
      value = "JOB执行主机",
      example = "1001"
   )
   private int executeServerId;
   @ApiModelProperty(
      value = "JOB类型",
      example = "kettle"
   )
   private String jobType;
   private String folderName;
   private String logFile;
   private String serverFile;
   private String svnFile;
   private String execState;
   private int execTime;
   private String debugLevel;
   private boolean online;
   private boolean enable;
   private String context;
   private Date createDate;
   private Date updateDate;
   private String groupName;
   private Date startDate;
   private Date endDate;
   private String cUser;
   private String uUser;
   private String logReson;
   private String appendParams;
   private int runPriority;
   private int oldGroupId;
   private String handStatus;
   private int chargorId;
   private int chargorId2;
   private int chargorId3;
   @ApiModelProperty(
      value = "集群ID",
      example = "1"
   )
   private int queueId;


   public String getHandStatus() {
      return this.handStatus;
   }

   public void setHandStatus(String handStatus) {
      this.handStatus = handStatus;
   }

   public int getOldGroupId() {
      return this.oldGroupId;
   }

   public void setOldGroupId(int oldGroupId) {
      this.oldGroupId = oldGroupId;
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

   public int getRetryId() {
      return this.retryId;
   }

   public void setRetryId(int retryId) {
      this.retryId = retryId;
   }

   public String getJobName() {
      return this.jobName;
   }

   public void setJobName(String jobName) {
      this.jobName = jobName;
   }

   public int getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(int orderNo) {
      this.orderNo = orderNo;
   }

   public String getLogFile() {
      return this.logFile;
   }

   public void setLogFile(String logFile) {
      this.logFile = logFile;
   }

   public String getServerFile() {
      return this.serverFile;
   }

   public void setServerFile(String serverFile) {
      this.serverFile = serverFile;
   }

   public String getSvnFile() {
      return this.svnFile;
   }

   public void setSvnFile(String svnFile) {
      this.svnFile = svnFile;
   }

   public boolean isEnable() {
      return this.enable;
   }

   public void setEnable(boolean enable) {
      this.enable = enable;
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

   public String getGroupName() {
      return this.groupName;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
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

   public String getExecState() {
      return this.execState;
   }

   public void setExecState(String execState) {
      this.execState = execState;
   }

   public int getExecTime() {
      return this.execTime;
   }

   public void setExecTime(int execTime) {
      this.execTime = execTime;
   }

   public int getChargorId() {
      return this.chargorId;
   }

   public void setChargorId(int chargorId) {
      this.chargorId = chargorId;
   }

   public int getChargorId2() {
      return this.chargorId2;
   }

   public void setChargorId2(int chargorId2) {
      this.chargorId2 = chargorId2;
   }

   public int getChargorId3() {
      return this.chargorId3;
   }

   public void setChargorId3(int chargorId3) {
      this.chargorId3 = chargorId3;
   }

   public String getChargorName() {
      return this.chargorName;
   }

   public void setChargorName(String chargorName) {
      this.chargorName = chargorName;
   }

   public String getChargorName2() {
      return this.chargorName2;
   }

   public void setChargorName2(String chargorName2) {
      this.chargorName2 = chargorName2;
   }

   public String getChargorName3() {
      return this.chargorName3;
   }

   public void setChargorName3(String chargorName3) {
      this.chargorName3 = chargorName3;
   }

   public String getcUser() {
      return this.cUser;
   }

   public void setcUser(String cUser) {
      this.cUser = cUser;
   }

   public String getuUser() {
      return this.uUser;
   }

   public void setuUser(String uUser) {
      this.uUser = uUser;
   }

   public String getDebugLevel() {
      return this.debugLevel;
   }

   public void setDebugLevel(String debugLevel) {
      this.debugLevel = debugLevel;
   }

   public String getLogReson() {
      return this.logReson;
   }

   public boolean isOnline() {
      return this.online;
   }

   public void setOnline(boolean online) {
      this.online = online;
   }

   public void setLogReson(String logReson) {
      this.logReson = logReson;
   }

   public String getAppendParams() {
      return this.appendParams;
   }

   public void setAppendParams(String appendParams) {
      this.appendParams = appendParams;
   }

   public int getRunPriority() {
      return this.runPriority;
   }

   public void setRunPriority(int runPriority) {
      this.runPriority = runPriority;
   }

   public int getQueueId() {
      return this.queueId;
   }

   public void setQueueId(int queueId) {
      this.queueId = queueId;
   }

   public String getJobType() {
      return this.jobType;
   }

   public void setJobType(String jobType) {
      this.jobType = jobType;
   }

   public String getFolderName() {
      return this.folderName;
   }

   public void setFolderName(String folderName) {
      this.folderName = folderName;
   }

   public String getContext() {
      return this.context;
   }

   public void setContext(String context) {
      this.context = context;
   }

   public String getColName(String colName) {
      return StringUtil.isBlank(colName)?"":(colName.equalsIgnoreCase("jobId")?"JOB_ID":(colName.equalsIgnoreCase("orderNo")?"ORDER_NO":(colName.equalsIgnoreCase("runPriority")?"RUN_PRIORITY":(colName.equalsIgnoreCase("groupId")?"GROUP_ID":(colName.equalsIgnoreCase("chargorId")?"CHARGOR_ID":(colName.equalsIgnoreCase("chargorId2")?"CHARGOR_ID2":(colName.equalsIgnoreCase("chargorId3")?"CHARGOR_ID3":(colName.equalsIgnoreCase("jobName")?"JOB_NAME":(colName.equalsIgnoreCase("logFile")?"LOG_FILE":(colName.equalsIgnoreCase("serverFile")?"SERVER_FILE":(colName.equalsIgnoreCase("svnFile")?"SVN_FILE":(colName.equalsIgnoreCase("enable")?"IS_ENABLE":(colName.equalsIgnoreCase("createDate")?"C_DATE":(colName.equalsIgnoreCase("updateDate")?"U_DATE":(colName.equalsIgnoreCase("groupName")?"G_NAME":(colName.equalsIgnoreCase("execTime")?"EXEC_TIME":(colName.equalsIgnoreCase("chargorName")?"NAME":(colName.equalsIgnoreCase("chargorName2")?"NAME2":(colName.equalsIgnoreCase("chargorName3")?"NAME3":(colName.equalsIgnoreCase("execState")?"EXEC_STATE":(colName.equalsIgnoreCase("startDate")?"START_DATE":(colName.equalsIgnoreCase("endDate")?"END_DATE":(colName.equalsIgnoreCase("retryId")?"RETRY_ID":(colName.equalsIgnoreCase("cUser")?"C_USER":(colName.equalsIgnoreCase("uUser")?"U_USER":(colName.equalsIgnoreCase("debugLevel")?"DEBUG_LEVEL":(colName.equalsIgnoreCase("online")?"IS_ONLINE":(colName.equalsIgnoreCase("appendParams")?"APPEND_PARAMS":(colName.equalsIgnoreCase("executeServerId")?"EXECUTE_SERVER_ID":(colName.equalsIgnoreCase("jobType")?"JOB_TYPE":(colName.equalsIgnoreCase("folderName")?"FOLDER_NAME":(colName.equalsIgnoreCase("queueId")?"QUEUE_ID":""))))))))))))))))))))))))))))))));
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
