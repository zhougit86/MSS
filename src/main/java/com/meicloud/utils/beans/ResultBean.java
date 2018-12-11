package com.meicloud.utils.beans;


public final class ResultBean {

   private String __statusCode;
   private String __errorMessage;
   private Object data;


   public String get__statusCode() {
      return this.__statusCode;
   }

   public void set__statusCode(String __statusCode) {
      this.__statusCode = __statusCode;
   }

   public String get__errorMessage() {
      return this.__errorMessage;
   }

   public void set__errorMessage(String __errorMessage) {
      this.__errorMessage = __errorMessage;
   }

   public Object getData() {
      return this.data;
   }

   public void setData(Object data) {
      this.data = data;
   }
}
