package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import com.meicloud.model.Proportion;
import com.meicloud.utils.StringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel("job组信息")
public class Group {

   @ApiModelProperty(
      value = "组id",
      example = "1"
   )
   private int groupId;
   @ApiModelProperty(
      value = "组名称",
      example = "TEST组"
   )
   private String groupName;
   @ApiModelProperty(
      value = "调度表达式",
      example = "0 0 9 1/1 * ? *"
   )
   private String cron;
   @ApiModelProperty(
      value = "是否依赖触发",
      example = "true"
   )
   private boolean timeTrigger;
   @ApiModelProperty(
      value = "集群id",
      example = "1"
   )
   private String queueId;
   @ApiModelProperty(
      hidden = true
   )
   private String queueName;
   @ApiModelProperty(
      value = "是否组内排序",
      example = "true"
   )
   private boolean sordInGroup;
   @ApiModelProperty(
      value = "状态(已启用/已禁止)",
      example = "true"
   )
   private boolean enable;
   private boolean errorJobContinueRun;
   @ApiModelProperty(
      value = "层级ID",
      example = "10"
   )
   private int levelId;
   @ApiModelProperty(
      hidden = true
   )
   private String levelName;
   @ApiModelProperty(
      value = "修改原因",
      example = "删除测试"
   )
   private String logReson;
   @ApiModelProperty(
      value = "描述",
      example = "测试测试"
   )
   private String desc;
   @ApiModelProperty(
      value = "并发数",
      example = "5"
   )
   private int parallelLimit;
   @ApiModelProperty(
      value = "主题ID",
      example = "10"
   )
   private int topicId;
   @ApiModelProperty(
      value = "依赖组集合@分割",
      example = "11@6"
   )
   private String referedGroupIds;
   @ApiModelProperty(
      value = "依赖组名称集合集合@分割",
      hidden = true
   )
   private String referedGroupNames;
   @ApiModelProperty(
      value = "依赖的作业集合以@分割",
      example = "10"
   )
   private String referedJobIds;
   @ApiModelProperty(
      value = "依赖的作业集合以@分割",
      example = "10"
   )
   private String referedJobNames;
   @ApiModelProperty(
      hidden = true
   )
   private String subject;
   @ApiModelProperty(
      hidden = true
   )
   private String nextFireTime;
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
   private int levelOrderNo;
   @ApiModelProperty(
      hidden = true
   )
   private int runGroupId;
   @ApiModelProperty(
      hidden = true
   )
   private String cUser;
   @ApiModelProperty(
      hidden = true
   )
   private String uUser;
   @ApiModelProperty(
      hidden = true
   )
   private String execTime;
   @ApiModelProperty(
      hidden = true
   )
   private Date startDate;
   @ApiModelProperty(
      hidden = true
   )
   private Date endDate;
   @ApiModelProperty(
      hidden = true
   )
   private boolean online;
   @ApiModelProperty(
      hidden = true
   )
   private String handStatus;
   @ApiModelProperty(
      hidden = true
   )
   private List topicList = new ArrayList();
   @ApiModelProperty(
      hidden = true
   )
   private Proportion proportion = new Proportion();
   @ApiModelProperty(
      hidden = true
   )
   private List groupIds = new ArrayList();
   @ApiModelProperty(
      hidden = true
   )
   private String state;


   public List getGroupIds() {
      return this.groupIds;
   }

   public void setGroupIds(List groupIds) {
      this.groupIds = groupIds;
   }

   public String getReferedGroupNames() {
      return this.referedGroupNames;
   }

   public void setReferedGroupNames(String referedGroupNames) {
      this.referedGroupNames = referedGroupNames;
   }

   public String getReferedJobNames() {
      return this.referedJobNames;
   }

   public void setReferedJobNames(String referedJobNames) {
      this.referedJobNames = referedJobNames;
   }

   public Proportion getProportion() {
      return this.proportion;
   }

   public void setProportion(Proportion proportion) {
      this.proportion = proportion;
   }

   public List getTopicList() {
      return this.topicList;
   }

   public void setTopicList(List topicList) {
      this.topicList = topicList;
   }

   public String getHandStatus() {
      return this.handStatus;
   }

   public void setHandStatus(String handStatus) {
      this.handStatus = handStatus;
   }

   public int getGroupId() {
      return this.groupId;
   }

   public void setGroupId(int groupId) {
      this.groupId = groupId;
   }

   public int getLevelId() {
      return this.levelId;
   }

   public void setLevelId(int levelId) {
      this.levelId = levelId;
   }

   public String getGroupName() {
      return this.groupName;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }

   public boolean isErrorJobContinueRun() {
      return this.errorJobContinueRun;
   }

   public void setErrorJobContinueRun(boolean errorJobContinueRun) {
      this.errorJobContinueRun = errorJobContinueRun;
   }

   public boolean isEnable() {
      return this.enable;
   }

   public void setEnable(boolean enable) {
      this.enable = enable;
   }

   public String getCron() {
      return this.cron;
   }

   public void setCron(String cron) {
      this.cron = cron;
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

   public String getLevelName() {
      return this.levelName;
   }

   public void setLevelName(String levelName) {
      this.levelName = levelName;
   }

   public String getReferedGroupIds() {
      return this.referedGroupIds;
   }

   public void setReferedGroupIds(String referedGroupIds) {
      this.referedGroupIds = referedGroupIds;
   }

   public String getReferedJobIds() {
      return this.referedJobIds;
   }

   public void setReferedJobIds(String referedJobIds) {
      this.referedJobIds = referedJobIds;
   }

   public int getLevelOrderNo() {
      return this.levelOrderNo;
   }

   public void setLevelOrderNo(int levelOrderNo) {
      this.levelOrderNo = levelOrderNo;
   }

   public int getTopicId() {
      return this.topicId;
   }

   public void setTopicId(int topicId) {
      this.topicId = topicId;
   }

   public String getSubject() {
      return this.subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public int getRunGroupId() {
      return this.runGroupId;
   }

   public void setRunGroupId(int runGroupId) {
      this.runGroupId = runGroupId;
   }

   public boolean isSordInGroup() {
      return this.sordInGroup;
   }

   public void setSordInGroup(boolean sordInGroup) {
      this.sordInGroup = sordInGroup;
   }

   public String getDesc() {
      return this.desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
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

   public String getLogReson() {
      return this.logReson;
   }

   public void setLogReson(String logReson) {
      this.logReson = logReson;
   }

   public int getParallelLimit() {
      return this.parallelLimit;
   }

   public void setParallelLimit(int parallelLimit) {
      this.parallelLimit = parallelLimit;
   }

   public String getExecTime() {
      return this.execTime;
   }

   public void setExecTime(String execTime) {
      this.execTime = execTime;
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

   public String getNextFireTime() {
      return this.nextFireTime;
   }

   public void setNextFireTime(String nextFireTime) {
      this.nextFireTime = nextFireTime;
   }

   public boolean isOnline() {
      return this.online;
   }

   public void setOnline(boolean online) {
      this.online = online;
   }

   public boolean isTimeTrigger() {
      return this.timeTrigger;
   }

   public void setTimeTrigger(boolean timeTrigger) {
      this.timeTrigger = timeTrigger;
   }

   public String getQueueId() {
      return this.queueId;
   }

   public void setQueueId(String queueId) {
      this.queueId = queueId;
   }

   public String getQueueName() {
      return this.queueName;
   }

   public void setQueueName(String queueName) {
      this.queueName = queueName;
   }

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getColName(String colName) {
      return StringUtil.isBlank(colName)?"":(colName.equalsIgnoreCase("groupId")?"GROUP_ID":(colName.equalsIgnoreCase("levelId")?"LEVEL_ID":(colName.equalsIgnoreCase("levelName")?"LEVEL_NAME":(colName.equalsIgnoreCase("subject")?"P_G_NAME":(colName.equalsIgnoreCase("groupName")?"G_NAME":(colName.equalsIgnoreCase("topicId")?"TOPIC_ID":(colName.equalsIgnoreCase("enable")?"IS_ENABLE":(colName.equalsIgnoreCase("timeTrigger")?"IS_TIME_TRIGGER":(colName.equalsIgnoreCase("cron")?"CRON":(colName.equalsIgnoreCase("createDate")?"C_DATE":(colName.equalsIgnoreCase("updateDate")?"U_DATE":(colName.equalsIgnoreCase("desc")?"DESC":(colName.equalsIgnoreCase("levelOrderNo")?"LEVEL_ORDER_NO":(colName.equalsIgnoreCase("referedGroupIds")?"REFERED_GROUP_IDS":(colName.equalsIgnoreCase("runGroupId")?"RUN_GROUP_ID":(colName.equalsIgnoreCase("sordInGroup")?"IS_SORT_IN_GROUP":(colName.equalsIgnoreCase("cUser")?"C_USER":(colName.equalsIgnoreCase("uUser")?"U_USER":(colName.equalsIgnoreCase("parallelLimit")?"PARALLEL_LIMIT":(colName.equalsIgnoreCase("execTime")?"EXEC_TIME":(colName.equalsIgnoreCase("startDate")?"START_DATE":(colName.equalsIgnoreCase("endDate")?"END_DATE":(colName.equalsIgnoreCase("queueId")?"QUEUE_ID":(colName.equalsIgnoreCase("queueName")?"QUEUE_NAME":(colName.equalsIgnoreCase("nextFireTime")?"NEXT_FIRE_TIME":"")))))))))))))))))))))))));
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
