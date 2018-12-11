package com.meicloud.sync;


public class RoleInfo {

   private String roleCode;
   private String roleName;
   private String roleDesc;
   private String opStatus;


   public String getRoleCode() {
      return this.roleCode;
   }

   public void setRoleCode(String roleCode) {
      this.roleCode = roleCode;
   }

   public String getRoleName() {
      return this.roleName;
   }

   public void setRoleName(String roleName) {
      this.roleName = roleName;
   }

   public String getRoleDesc() {
      return this.roleDesc;
   }

   public void setRoleDesc(String roleDesc) {
      this.roleDesc = roleDesc;
   }

   public String getOpStatus() {
      return this.opStatus;
   }

   public void setOpStatus(String opStatus) {
      this.opStatus = opStatus;
   }
}
