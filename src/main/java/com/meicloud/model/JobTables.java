package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;
import java.util.Date;

public class JobTables implements Serializable {

   private static final long serialVersionUID = 1L;
   private int id;
   private int jobId;
   private String databaseName;
   private String tableName;
   private int version;
   private String desc;
   private boolean enable;
   private Date createDate;
   private Date updateDate;
   private String createUser;
   private String updateUser;
   private int type;


   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getJobId() {
      return this.jobId;
   }

   public void setJobId(int jobId) {
      this.jobId = jobId;
   }

   public String getDatabaseName() {
      return this.databaseName;
   }

   public void setDatabaseName(String databaseName) {
      this.databaseName = databaseName;
   }

   public String getTableName() {
      return this.tableName;
   }

   public void setTableName(String tableName) {
      this.tableName = tableName;
   }

   public int getVersion() {
      return this.version;
   }

   public void setVersion(int version) {
      this.version = version;
   }

   public String getDesc() {
      return this.desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
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

   public String getCreateUser() {
      return this.createUser;
   }

   public void setCreateUser(String createUser) {
      this.createUser = createUser;
   }

   public String getUpdateUser() {
      return this.updateUser;
   }

   public void setUpdateUser(String updateUser) {
      this.updateUser = updateUser;
   }

   public int getType() {
      return this.type;
   }

   public void setType(int type) {
      this.type = type;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
