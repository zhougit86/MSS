package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;

public class ConfigMssEs implements Serializable {

   private static final long serialVersionUID = 1L;
   private String serverConfFileKey;
   private String serverIdKey;
   private String queueRunJobBrokerUrlKey;
   private String queueNameKey;
   private String queueUserNameKey;
   private String queuePasswordKey;
   private String parallelJobCountKey;
   private String queueFetchSleepIntervalKey;
   private String runJobStateUpdateUrlKey;
   private String runJobStateUpdateAfterKillUrlKey;
   private String runningJobCountKey;
   private String mqFetchThreadCountKey;
   private String clusterNameKey;
   private String serverConfFileValue;
   private String serverIdValue;
   private String queueRunJobBrokerUrlValue;
   private String queueNameValue;
   private String queueUserNameValue;
   private String queuePasswordValue;
   private int parallelJobCountValue;
   private int queueFetchSleepIntervalValue;
   private String runJobStateUpdateUrlValue;
   private String runJobStateUpdateAfterKillUrlValue;
   private int runningJobCountValue;
   private int mqFetchThreadCountValue;
   private String clusterNameValue;
   private String type;


   public String getServerConfFileKey() {
      return this.serverConfFileKey;
   }

   public void setServerConfFileKey(String serverConfFileKey) {
      this.serverConfFileKey = serverConfFileKey;
   }

   public String getServerIdKey() {
      return this.serverIdKey;
   }

   public void setServerIdKey(String serverIdKey) {
      this.serverIdKey = serverIdKey;
   }

   public String getQueueRunJobBrokerUrlKey() {
      return this.queueRunJobBrokerUrlKey;
   }

   public void setQueueRunJobBrokerUrlKey(String queueRunJobBrokerUrlKey) {
      this.queueRunJobBrokerUrlKey = queueRunJobBrokerUrlKey;
   }

   public String getQueueNameKey() {
      return this.queueNameKey;
   }

   public void setQueueNameKey(String queueNameKey) {
      this.queueNameKey = queueNameKey;
   }

   public String getQueueUserNameKey() {
      return this.queueUserNameKey;
   }

   public void setQueueUserNameKey(String queueUserNameKey) {
      this.queueUserNameKey = queueUserNameKey;
   }

   public String getQueuePasswordKey() {
      return this.queuePasswordKey;
   }

   public void setQueuePasswordKey(String queuePasswordKey) {
      this.queuePasswordKey = queuePasswordKey;
   }

   public String getParallelJobCountKey() {
      return this.parallelJobCountKey;
   }

   public void setParallelJobCountKey(String parallelJobCountKey) {
      this.parallelJobCountKey = parallelJobCountKey;
   }

   public String getQueueFetchSleepIntervalKey() {
      return this.queueFetchSleepIntervalKey;
   }

   public void setQueueFetchSleepIntervalKey(String queueFetchSleepIntervalKey) {
      this.queueFetchSleepIntervalKey = queueFetchSleepIntervalKey;
   }

   public String getRunJobStateUpdateUrlKey() {
      return this.runJobStateUpdateUrlKey;
   }

   public void setRunJobStateUpdateUrlKey(String runJobStateUpdateUrlKey) {
      this.runJobStateUpdateUrlKey = runJobStateUpdateUrlKey;
   }

   public String getRunJobStateUpdateAfterKillUrlKey() {
      return this.runJobStateUpdateAfterKillUrlKey;
   }

   public void setRunJobStateUpdateAfterKillUrlKey(String runJobStateUpdateAfterKillUrlKey) {
      this.runJobStateUpdateAfterKillUrlKey = runJobStateUpdateAfterKillUrlKey;
   }

   public String getRunningJobCountKey() {
      return this.runningJobCountKey;
   }

   public void setRunningJobCountKey(String runningJobCountKey) {
      this.runningJobCountKey = runningJobCountKey;
   }

   public String getMqFetchThreadCountKey() {
      return this.mqFetchThreadCountKey;
   }

   public void setMqFetchThreadCountKey(String mqFetchThreadCountKey) {
      this.mqFetchThreadCountKey = mqFetchThreadCountKey;
   }

   public String getClusterNameKey() {
      return this.clusterNameKey;
   }

   public void setClusterNameKey(String clusterNameKey) {
      this.clusterNameKey = clusterNameKey;
   }

   public String getServerConfFileValue() {
      return this.serverConfFileValue;
   }

   public void setServerConfFileValue(String serverConfFileValue) {
      this.serverConfFileValue = serverConfFileValue;
   }

   public String getServerIdValue() {
      return this.serverIdValue;
   }

   public void setServerIdValue(String serverIdValue) {
      this.serverIdValue = serverIdValue;
   }

   public String getQueueRunJobBrokerUrlValue() {
      return this.queueRunJobBrokerUrlValue;
   }

   public void setQueueRunJobBrokerUrlValue(String queueRunJobBrokerUrlValue) {
      this.queueRunJobBrokerUrlValue = queueRunJobBrokerUrlValue;
   }

   public String getQueueNameValue() {
      return this.queueNameValue;
   }

   public void setQueueNameValue(String queueNameValue) {
      this.queueNameValue = queueNameValue;
   }

   public String getQueueUserNameValue() {
      return this.queueUserNameValue;
   }

   public void setQueueUserNameValue(String queueUserNameValue) {
      this.queueUserNameValue = queueUserNameValue;
   }

   public String getQueuePasswordValue() {
      return this.queuePasswordValue;
   }

   public void setQueuePasswordValue(String queuePasswordValue) {
      this.queuePasswordValue = queuePasswordValue;
   }

   public int getParallelJobCountValue() {
      return this.parallelJobCountValue;
   }

   public void setParallelJobCountValue(int parallelJobCountValue) {
      this.parallelJobCountValue = parallelJobCountValue;
   }

   public int getQueueFetchSleepIntervalValue() {
      return this.queueFetchSleepIntervalValue;
   }

   public void setQueueFetchSleepIntervalValue(int queueFetchSleepIntervalValue) {
      this.queueFetchSleepIntervalValue = queueFetchSleepIntervalValue;
   }

   public String getRunJobStateUpdateUrlValue() {
      return this.runJobStateUpdateUrlValue;
   }

   public void setRunJobStateUpdateUrlValue(String runJobStateUpdateUrlValue) {
      this.runJobStateUpdateUrlValue = runJobStateUpdateUrlValue;
   }

   public String getRunJobStateUpdateAfterKillUrlValue() {
      return this.runJobStateUpdateAfterKillUrlValue;
   }

   public void setRunJobStateUpdateAfterKillUrlValue(String runJobStateUpdateAfterKillUrlValue) {
      this.runJobStateUpdateAfterKillUrlValue = runJobStateUpdateAfterKillUrlValue;
   }

   public int getRunningJobCountValue() {
      return this.runningJobCountValue;
   }

   public void setRunningJobCountValue(int runningJobCountValue) {
      this.runningJobCountValue = runningJobCountValue;
   }

   public int getMqFetchThreadCountValue() {
      return this.mqFetchThreadCountValue;
   }

   public void setMqFetchThreadCountValue(int mqFetchThreadCountValue) {
      this.mqFetchThreadCountValue = mqFetchThreadCountValue;
   }

   public String getClusterNameValue() {
      return this.clusterNameValue;
   }

   public void setClusterNameValue(String clusterNameValue) {
      this.clusterNameValue = clusterNameValue;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
