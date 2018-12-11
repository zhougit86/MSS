package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;

public class Tool implements Serializable {

   private static final long serialVersionUID = 1L;
   private int toolId;
   private String toolName;
   private String toolUrl;
   private String desc;


   public String getToolName() {
      return this.toolName;
   }

   public int getToolId() {
      return this.toolId;
   }

   public void setToolId(int toolId) {
      this.toolId = toolId;
   }

   public void setToolName(String toolName) {
      this.toolName = toolName;
   }

   public String getToolUrl() {
      return this.toolUrl;
   }

   public void setToolUrl(String toolUrl) {
      this.toolUrl = toolUrl;
   }

   public String getDesc() {
      return this.desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
