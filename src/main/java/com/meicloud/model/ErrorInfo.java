package com.meicloud.model;


public class ErrorInfo {

   public static final Integer OK = Integer.valueOf(0);
   public static final Integer ERROR = Integer.valueOf(100);
   private Integer code;
   private String message;
   private String url;


   public static Integer getOK() {
      return OK;
   }

   public static Integer getERROR() {
      return ERROR;
   }

   public Integer getCode() {
      return this.code;
   }

   public void setCode(Integer code) {
      this.code = code;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String url) {
      this.url = url;
   }
}
