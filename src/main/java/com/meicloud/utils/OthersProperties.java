package com.meicloud.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OthersProperties {

   @Value("${group.restart.interval}")
   private String groupRestartTime;
   @Value("${job.restart.interval}")
   private String jobRestartTime;
   @Value("${kettle_suffix}")
   private String kettleSuffix;
   @Value("${sync.svn.file}")
   private String syncAPP;


   public String getGroupRestartTime() {
      return this.groupRestartTime;
   }

   public void setGroupRestartTime(String groupRestartTime) {
      this.groupRestartTime = groupRestartTime;
   }

   public String getJobRestartTime() {
      return this.jobRestartTime;
   }

   public void setJobRestartTime(String jobRestartTime) {
      this.jobRestartTime = jobRestartTime;
   }

   public String getKettleSuffix() {
      return this.kettleSuffix;
   }

   public void setKettleSuffix(String kettleSuffix) {
      this.kettleSuffix = kettleSuffix;
   }

   public String getSyncAPP() {
      return this.syncAPP;
   }

   public void setSyncAPP(String syncAPP) {
      this.syncAPP = syncAPP;
   }
}
