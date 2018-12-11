package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;
import java.util.Date;

public class RunConfirm implements Serializable {

   private static final long serialVersionUID = 1L;
   private int confirmId;
   private int frontGroupId;
   private String frontGroupName;
   private String postGroupName;
   private String createAccount;
   private String updateAccount;
   private Date createDate;
   private Date updateDate;
   private String msg;


   public int getConfirmId() {
      return this.confirmId;
   }

   public void setConfirmId(int confirmId) {
      this.confirmId = confirmId;
   }

   public int getFrontGroupId() {
      return this.frontGroupId;
   }

   public void setFrontGroupId(int frontGroupId) {
      this.frontGroupId = frontGroupId;
   }

   public String getFrontGroupName() {
      return this.frontGroupName;
   }

   public void setFrontGroupName(String frontGroupName) {
      this.frontGroupName = frontGroupName;
   }

   public String getPostGroupName() {
      return this.postGroupName;
   }

   public void setPostGroupName(String postGroupName) {
      this.postGroupName = postGroupName;
   }

   public String getCreateAccount() {
      return this.createAccount;
   }

   public void setCreateAccount(String createAccount) {
      this.createAccount = createAccount;
   }

   public String getUpdateAccount() {
      return this.updateAccount;
   }

   public void setUpdateAccount(String updateAccount) {
      this.updateAccount = updateAccount;
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

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
