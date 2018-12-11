package com.meicloud.dto;


public class PermInfo {

   private Integer id;
   private Integer pId;
   private String permCode;
   private String permName;
   private String permPath;
   private Boolean ifFlag;


   public Integer getpId() {
      return this.pId;
   }

   public void setpId(Integer pId) {
      this.pId = pId;
   }

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getPermName() {
      return this.permName;
   }

   public void setPermName(String permName) {
      this.permName = permName;
   }

   public String getPermCode() {
      return this.permCode;
   }

   public void setPermCode(String permCode) {
      this.permCode = permCode;
   }

   public String getPermPath() {
      return this.permPath;
   }

   public void setPermPath(String permPath) {
      this.permPath = permPath;
   }

   public Boolean getIfFlag() {
      return this.ifFlag;
   }

   public void setIfFlag(Boolean ifFlag) {
      this.ifFlag = ifFlag;
   }
}
