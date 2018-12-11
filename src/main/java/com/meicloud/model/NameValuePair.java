package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;

public class NameValuePair implements Serializable {

   private static final long serialVersionUID = -8516803806508041115L;
   private int id;
   private String name;
   private String value;


   public NameValuePair() {}

   public NameValuePair(String name, String value) {
      this.name = name;
      this.value = value;
   }

   public NameValuePair(int id, String value) {
      this.id = id;
      this.value = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
