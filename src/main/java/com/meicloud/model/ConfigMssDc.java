package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;

public class ConfigMssDc implements Serializable {

   private static final long serialVersionUID = 1L;
   private String queueRunJobBrokerUrlKey;
   private String queueNameKey;
   private String queueUserNameKey;
   private String queuePasswordKey;
   private String killJobQueueBrolerRrlKey;
   private String killJobQueueNameKey;
   private String killJobQueueUserNameKey;
   private String killJobQueuePasswordKey;
   private String queueRunJobBrokerUrlValue;
   private String queueNameValue;
   private String queueUserNameValue;
   private String queuePasswordValue;
   private String killJobQueueBrolerRrlValue;
   private String killJobQueueNameValue;
   private String killJobQueueUserNameValue;
   private String killJobQueuePasswordValue;
   private String type;


   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
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

   public String getKillJobQueueBrolerRrlKey() {
      return this.killJobQueueBrolerRrlKey;
   }

   public void setKillJobQueueBrolerRrlKey(String killJobQueueBrolerRrlKey) {
      this.killJobQueueBrolerRrlKey = killJobQueueBrolerRrlKey;
   }

   public String getKillJobQueueNameKey() {
      return this.killJobQueueNameKey;
   }

   public void setKillJobQueueNameKey(String killJobQueueNameKey) {
      this.killJobQueueNameKey = killJobQueueNameKey;
   }

   public String getKillJobQueueUserNameKey() {
      return this.killJobQueueUserNameKey;
   }

   public void setKillJobQueueUserNameKey(String killJobQueueUserNameKey) {
      this.killJobQueueUserNameKey = killJobQueueUserNameKey;
   }

   public String getKillJobQueuePasswordKey() {
      return this.killJobQueuePasswordKey;
   }

   public void setKillJobQueuePasswordKey(String killJobQueuePasswordKey) {
      this.killJobQueuePasswordKey = killJobQueuePasswordKey;
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

   public String getKillJobQueueBrolerRrlValue() {
      return this.killJobQueueBrolerRrlValue;
   }

   public void setKillJobQueueBrolerRrlValue(String killJobQueueBrolerRrlValue) {
      this.killJobQueueBrolerRrlValue = killJobQueueBrolerRrlValue;
   }

   public String getKillJobQueueNameValue() {
      return this.killJobQueueNameValue;
   }

   public void setKillJobQueueNameValue(String killJobQueueNameValue) {
      this.killJobQueueNameValue = killJobQueueNameValue;
   }

   public String getKillJobQueueUserNameValue() {
      return this.killJobQueueUserNameValue;
   }

   public void setKillJobQueueUserNameValue(String killJobQueueUserNameValue) {
      this.killJobQueueUserNameValue = killJobQueueUserNameValue;
   }

   public String getKillJobQueuePasswordValue() {
      return this.killJobQueuePasswordValue;
   }

   public void setKillJobQueuePasswordValue(String killJobQueuePasswordValue) {
      this.killJobQueuePasswordValue = killJobQueuePasswordValue;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
