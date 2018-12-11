package com.meicloud.dto;


public class TreeInfo {

   private String id;
   private String name;
   private String type;
   private String pId;
   private String PPId;
   private boolean isChecked = false;
   private boolean isOpen = false;
   private String code;
   private String export;
   private String export1;


   public String getExport1() {
      return this.export1;
   }

   public void setExport1(String export1) {
      this.export1 = export1;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getpId() {
      return this.pId;
   }

   public void setpId(String pId) {
      this.pId = pId;
   }

   public boolean isChecked() {
      return this.isChecked;
   }

   public void setChecked(boolean isChecked) {
      this.isChecked = isChecked;
   }

   public boolean isOpen() {
      return this.isOpen;
   }

   public void setOpen(boolean isOpen) {
      this.isOpen = isOpen;
   }

   public String getExport() {
      return this.export;
   }

   public void setExport(String export) {
      this.export = export;
   }

   public String getPPId() {
      return this.PPId;
   }

   public void setPPId(String pPId) {
      this.PPId = pPId;
   }
}
