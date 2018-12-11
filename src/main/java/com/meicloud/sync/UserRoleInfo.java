package com.meicloud.sync;

import java.util.ArrayList;
import java.util.List;

public class UserRoleInfo {

   private String roleId;
   private String roleCode;
   List userCodes = new ArrayList();


   public String getRoleId() {
      return this.roleId;
   }

   public void setRoleId(String roleId) {
      this.roleId = roleId;
   }

   public String getRoleCode() {
      return this.roleCode;
   }

   public void setRoleCode(String roleCode) {
      this.roleCode = roleCode;
   }

   public List getUserCodes() {
      return this.userCodes;
   }

   public void setUserCodes(List userCodes) {
      this.userCodes = userCodes;
   }
}
