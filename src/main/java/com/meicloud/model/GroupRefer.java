package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;
import java.util.Date;

public class GroupRefer implements Serializable {

   private static final long serialVersionUID = 1L;
   private int referId;
   private int referedGroupId;
   private int groupId;
   private Date createDate;
   private Date cDate;


   public Date getcDate() {
      return this.cDate;
   }

   public void setcDate(Date cDate) {
      this.cDate = cDate;
   }

   public int getReferId() {
      return this.referId;
   }

   public void setReferId(int referId) {
      this.referId = referId;
   }

   public int getReferedGroupId() {
      return this.referedGroupId;
   }

   public void setReferedGroupId(int referedGroupId) {
      this.referedGroupId = referedGroupId;
   }

   public int getGroupId() {
      return this.groupId;
   }

   public void setGroupId(int groupId) {
      this.groupId = groupId;
   }

   public Date getCreateDate() {
      return this.createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
