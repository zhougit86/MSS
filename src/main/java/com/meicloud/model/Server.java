package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

@ApiModel("服务器主机信息")
public class Server {

   @ApiModelProperty(
      value = "主机ID",
      example = "12"
   )
   private Integer id;
   @ApiModelProperty(
      value = "主机状态（0:表上线 ， 2:表下线）",
      example = "0"
   )
   private int state;
   @ApiModelProperty("IP")
   private String ip;
   @ApiModelProperty(
      value = "app端口",
      example = "8080"
   )
   private String appPort;
   @ApiModelProperty(
      value = "日志服务器端口",
      example = "8084"
   )
   private String logAppPort;
   @ApiModelProperty(
      value = "主机描述",
      example = "执行节点"
   )
   private String desc;
   @ApiModelProperty(
      value = "服务器部署项目名",
      example = "MSS"
   )
   private String name;
   @ApiModelProperty(
      value = "服务器类型",
      example = "ES"
   )
   private String type;
   @ApiModelProperty(
      hidden = true
   )
   private String type_desc;
   @ApiModelProperty(
      value = "svn上kettler的执行脚本的下载存放路径",
      example = "D:/logs//home/hive/mss/console_files"
   )
   private String svnLogPath;
   @ApiModelProperty(
      hidden = true
   )
   private Date createDate;
   @ApiModelProperty(
      hidden = true
   )
   private Date updateDate;
   @ApiModelProperty(
      hidden = true
   )
   private String sshUser;
   @ApiModelProperty(
      hidden = true
   )
   private String sshHost;
   @ApiModelProperty(
      hidden = true
   )
   private String sshPort;
   @ApiModelProperty(
      hidden = true
   )
   private String sshPassword;
   @ApiModelProperty(
      hidden = true
   )
   private String addJobServiceUrl;
   @ApiModelProperty(
      hidden = true
   )
   private String testAliveUrl;
   @ApiModelProperty(
      hidden = true
   )
   private String retryExpireJobServiveUrl;
   @ApiModelProperty(
      hidden = true
   )
   private String retryErrorJobServiceUrl;
   @ApiModelProperty(
      hidden = true
   )
   private int runJobCount;
   @ApiModelProperty(
      hidden = true
   )
   private boolean perform;
   @ApiModelProperty(
      hidden = true
   )
   private boolean available;
   @ApiModelProperty(
      hidden = true
   )
   private List list;
   @ApiModelProperty(
      hidden = true
   )
   private int currentExecuteJobCount;
   @ApiModelProperty(
      hidden = true
   )
   private String queueId;
   @ApiModelProperty(
      hidden = true
   )
   private String queueName;
   @ApiModelProperty(
      hidden = true
   )
   private String jobSum;
   @ApiModelProperty(
      value = "最大执行数",
      example = "15"
   )
   private int maxParalle;


   public String getType_desc() {
      return this.type_desc;
   }

   public void setType_desc(String type_desc) {
      this.type_desc = type_desc;
   }

   public String getLogAppPort() {
      return this.logAppPort;
   }

   public void setLogAppPort(String logAppPort) {
      this.logAppPort = logAppPort;
   }

   public String getSvnLogPath() {
      return this.svnLogPath;
   }

   public void setSvnLogPath(String svnLogPath) {
      this.svnLogPath = svnLogPath;
   }

   public String getSshUser() {
      return this.sshUser;
   }

   public void setSshUser(String sshUser) {
      this.sshUser = sshUser;
   }

   public String getSshHost() {
      return this.sshHost;
   }

   public void setSshHost(String sshHost) {
      this.sshHost = sshHost;
   }

   public String getSshPort() {
      return this.sshPort;
   }

   public void setSshPort(String sshPort) {
      this.sshPort = sshPort;
   }

   public String getSshPassword() {
      return this.sshPassword;
   }

   public void setSshPassword(String sshPassword) {
      this.sshPassword = sshPassword;
   }

   public String getAddJobServiceUrl() {
      return this.addJobServiceUrl;
   }

   public void setAddJobServiceUrl(String addJobServiceUrl) {
      this.addJobServiceUrl = addJobServiceUrl;
   }

   public String getTestAliveUrl() {
      return this.testAliveUrl;
   }

   public void setTestAliveUrl(String testAliveUrl) {
      this.testAliveUrl = testAliveUrl;
   }

   public String getRetryExpireJobServiveUrl() {
      return this.retryExpireJobServiveUrl;
   }

   public void setRetryExpireJobServiveUrl(String retryExpireJobServiveUrl) {
      this.retryExpireJobServiveUrl = retryExpireJobServiveUrl;
   }

   public String getRetryErrorJobServiceUrl() {
      return this.retryErrorJobServiceUrl;
   }

   public void setRetryErrorJobServiceUrl(String retryErrorJobServiceUrl) {
      this.retryErrorJobServiceUrl = retryErrorJobServiceUrl;
   }

   public String getQueueId() {
      return this.queueId;
   }

   public String getQueueName() {
      return this.queueName;
   }

   public void setQueueName(String queueName) {
      this.queueName = queueName;
   }

   public void setQueueId(String queueId) {
      this.queueId = queueId;
   }

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }

   public String getAppPort() {
      return this.appPort;
   }

   public void setAppPort(String appPort) {
      this.appPort = appPort;
   }

   public String getDesc() {
      return this.desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public int getState() {
      return this.state;
   }

   public void setState(int state) {
      this.state = state;
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

   public String getIp() {
      return this.ip;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }

   public int getRunJobCount() {
      return this.runJobCount;
   }

   public void setRunJobCount(int runJobCount) {
      this.runJobCount = runJobCount;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public boolean isPerform() {
      return this.perform;
   }

   public void setPerform(boolean perform) {
      this.perform = perform;
   }

   public boolean isAvailable() {
      return this.available;
   }

   public void setAvailable(boolean available) {
      this.available = available;
   }

   public int getCurrentExecuteJobCount() {
      return this.currentExecuteJobCount;
   }

   public void setCurrentExecuteJobCount(int currentExecuteJobCount) {
      this.currentExecuteJobCount = currentExecuteJobCount;
   }

   public String getJobSum() {
      return this.jobSum;
   }

   public void setJobSum(String jobSum) {
      this.jobSum = jobSum;
   }

   public int getMaxParalle() {
      return this.maxParalle;
   }

   public void setMaxParalle(int maxParalle) {
      this.maxParalle = maxParalle;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
