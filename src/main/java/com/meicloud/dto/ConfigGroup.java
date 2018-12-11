package com.meicloud.dto;


public class ConfigGroup {

   private Integer TOTAL_JOB_COUNT_KEY;
   private Integer ENABLE_JOB_COUNT_KEY;
   private Integer DISABLE_JOB_COUNT_KEY;
   private Integer TOPIC_COUNT_KEY;


   public Integer getTOTAL_JOB_COUNT_KEY() {
      return this.TOTAL_JOB_COUNT_KEY;
   }

   public void setTOTAL_JOB_COUNT_KEY(Integer TOTAL_JOB_COUNT_KEY) {
      this.TOTAL_JOB_COUNT_KEY = TOTAL_JOB_COUNT_KEY;
   }

   public Integer getENABLE_JOB_COUNT_KEY() {
      return this.ENABLE_JOB_COUNT_KEY;
   }

   public void setENABLE_JOB_COUNT_KEY(Integer ENABLE_JOB_COUNT_KEY) {
      this.ENABLE_JOB_COUNT_KEY = ENABLE_JOB_COUNT_KEY;
   }

   public Integer getDISABLE_JOB_COUNT_KEY() {
      return this.DISABLE_JOB_COUNT_KEY;
   }

   public void setDISABLE_JOB_COUNT_KEY(Integer DISABLE_JOB_COUNT_KEY) {
      this.DISABLE_JOB_COUNT_KEY = DISABLE_JOB_COUNT_KEY;
   }

   public Integer getTOPIC_COUNT_KEY() {
      return this.TOPIC_COUNT_KEY;
   }

   public void setTOPIC_COUNT_KEY(Integer TOPIC_COUNT_KEY) {
      this.TOPIC_COUNT_KEY = TOPIC_COUNT_KEY;
   }
}
