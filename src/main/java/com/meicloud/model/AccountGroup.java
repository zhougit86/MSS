package com.meicloud.model;

import java.io.Serializable;

public class AccountGroup implements Serializable {

   private static final long serialVersionUID = 1L;
   private String account;
   private Integer groupId;


   public String getAccount() {
      return this.account;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public Integer getGroupId() {
      return this.groupId;
   }

   public void setGroupId(Integer groupId) {
      this.groupId = groupId;
   }
}
