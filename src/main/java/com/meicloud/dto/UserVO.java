package com.meicloud.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserVO implements Serializable {

   private String userAccount;
   private String userName;
   private boolean svnOn = true;
   private boolean ifAdmin;
   private List roleList = new ArrayList();
   private List permList = new ArrayList();


   public String getUserAccount() {
      return this.userAccount;
   }

   public void setUserAccount(String userAccount) {
      this.userAccount = userAccount;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public boolean isSvnOn() {
      return this.svnOn;
   }

   public void setSvnOn(boolean svnOn) {
      this.svnOn = svnOn;
   }

   public boolean isIfAdmin() {
      return this.ifAdmin;
   }

   public void setIfAdmin(boolean ifAdmin) {
      this.ifAdmin = ifAdmin;
   }

   public List getRoleList() {
      return this.roleList;
   }

   public void setRoleList(List roleList) {
      this.roleList = roleList;
   }

   public List getPermList() {
      return this.permList;
   }

   public void setPermList(List permList) {
      this.permList = permList;
   }
}
