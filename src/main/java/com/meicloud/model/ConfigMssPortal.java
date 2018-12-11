package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;

public class ConfigMssPortal implements Serializable {

   private static final long serialVersionUID = 1L;
   private String defaultPageSizeKey;
   private String jobBrokerUrlKey;
   private String queueNameKey;
   private String queueUserNameKey;
   private String queuePasswordKey;
   private String brolerRrlKey;
   private String jobQueueNameKey;
   private String jobQueueUserNameKey;
   private String jobQueuePasswordKey;
   private String jobRestartIntervalKey;
   private String groupRestartIntervalKey;
   private String clusterSuffixKey;
   private int defaultPageSizeValue;
   private String jobBrokerUrlValue;
   private String queueNameValue;
   private String queueUserNameValue;
   private String queuePasswordValue;
   private String brolerRrlValue;
   private String jobQueueNameValue;
   private String jobQueueUserNameValue;
   private String jobQueuePasswordValue;
   private int jobRestartIntervalValue;
   private int groupRestartIntervalValue;
   private String clusterSuffixValue;
   private String type;


   public String getDefaultPageSizeKey() {
      return this.defaultPageSizeKey;
   }

   public void setDefaultPageSizeKey(String defaultPageSizeKey) {
      this.defaultPageSizeKey = defaultPageSizeKey;
   }

   public String getJobBrokerUrlKey() {
      return this.jobBrokerUrlKey;
   }

   public void setJobBrokerUrlKey(String jobBrokerUrlKey) {
      this.jobBrokerUrlKey = jobBrokerUrlKey;
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

   public String getBrolerRrlKey() {
      return this.brolerRrlKey;
   }

   public void setBrolerRrlKey(String brolerRrlKey) {
      this.brolerRrlKey = brolerRrlKey;
   }

   public String getJobQueueNameKey() {
      return this.jobQueueNameKey;
   }

   public void setJobQueueNameKey(String jobQueueNameKey) {
      this.jobQueueNameKey = jobQueueNameKey;
   }

   public String getJobQueueUserNameKey() {
      return this.jobQueueUserNameKey;
   }

   public void setJobQueueUserNameKey(String jobQueueUserNameKey) {
      this.jobQueueUserNameKey = jobQueueUserNameKey;
   }

   public String getJobQueuePasswordKey() {
      return this.jobQueuePasswordKey;
   }

   public void setJobQueuePasswordKey(String jobQueuePasswordKey) {
      this.jobQueuePasswordKey = jobQueuePasswordKey;
   }

   public String getJobRestartIntervalKey() {
      return this.jobRestartIntervalKey;
   }

   public void setJobRestartIntervalKey(String jobRestartIntervalKey) {
      this.jobRestartIntervalKey = jobRestartIntervalKey;
   }

   public String getGroupRestartIntervalKey() {
      return this.groupRestartIntervalKey;
   }

   public void setGroupRestartIntervalKey(String groupRestartIntervalKey) {
      this.groupRestartIntervalKey = groupRestartIntervalKey;
   }

   public String getClusterSuffixKey() {
      return this.clusterSuffixKey;
   }

   public void setClusterSuffixKey(String clusterSuffixKey) {
      this.clusterSuffixKey = clusterSuffixKey;
   }

   public int getDefaultPageSizeValue() {
      return this.defaultPageSizeValue;
   }

   public void setDefaultPageSizeValue(int defaultPageSizeValue) {
      this.defaultPageSizeValue = defaultPageSizeValue;
   }

   public String getJobBrokerUrlValue() {
      return this.jobBrokerUrlValue;
   }

   public void setJobBrokerUrlValue(String jobBrokerUrlValue) {
      this.jobBrokerUrlValue = jobBrokerUrlValue;
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

   public String getBrolerRrlValue() {
      return this.brolerRrlValue;
   }

   public void setBrolerRrlValue(String brolerRrlValue) {
      this.brolerRrlValue = brolerRrlValue;
   }

   public String getJobQueueNameValue() {
      return this.jobQueueNameValue;
   }

   public void setJobQueueNameValue(String jobQueueNameValue) {
      this.jobQueueNameValue = jobQueueNameValue;
   }

   public String getJobQueueUserNameValue() {
      return this.jobQueueUserNameValue;
   }

   public void setJobQueueUserNameValue(String jobQueueUserNameValue) {
      this.jobQueueUserNameValue = jobQueueUserNameValue;
   }

   public String getJobQueuePasswordValue() {
      return this.jobQueuePasswordValue;
   }

   public void setJobQueuePasswordValue(String jobQueuePasswordValue) {
      this.jobQueuePasswordValue = jobQueuePasswordValue;
   }

   public int getJobRestartIntervalValue() {
      return this.jobRestartIntervalValue;
   }

   public void setJobRestartIntervalValue(int jobRestartIntervalValue) {
      this.jobRestartIntervalValue = jobRestartIntervalValue;
   }

   public int getGroupRestartIntervalValue() {
      return this.groupRestartIntervalValue;
   }

   public void setGroupRestartIntervalValue(int groupRestartIntervalValue) {
      this.groupRestartIntervalValue = groupRestartIntervalValue;
   }

   public String getClusterSuffixValue() {
      return this.clusterSuffixValue;
   }

   public void setClusterSuffixValue(String clusterSuffixValue) {
      this.clusterSuffixValue = clusterSuffixValue;
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
