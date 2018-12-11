package com.meicloud.schedule.listener;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class SimpleSchedulerListener implements org.quartz.SchedulerListener {

   private final Logger logger = Logger.getLogger(this.getClass());


   public void jobScheduled(Trigger trigger) {
      this.logger.info(trigger.getJobKey().getName() + " has been scheduled");
   }

   public void jobUnscheduled(TriggerKey triggerKey) {
      this.logger.info(triggerKey + " is being unscheduled");
   }

   public void triggerFinalized(Trigger trigger) {
      this.logger.info("Trigger is finished for " + trigger.getJobKey().getName());
   }

   public void triggerPaused(TriggerKey triggerKey) {
      this.logger.info(triggerKey + " is being paused");
   }

   public void triggersPaused(String triggerGroup) {
      this.logger.info("trigger group " + triggerGroup + " is being paused");
   }

   public void triggerResumed(TriggerKey triggerKey) {
      this.logger.info(triggerKey + " is being resumed");
   }

   public void triggersResumed(String triggerGroup) {
      this.logger.info("trigger group " + triggerGroup + " is being resumed");
   }

   public void jobAdded(JobDetail jobDetail) {
      this.logger.info(jobDetail.getKey() + " is added");
   }

   public void jobDeleted(JobKey jobKey) {
      this.logger.info(jobKey + " is deleted");
   }

   public void jobPaused(JobKey jobKey) {
      this.logger.info(jobKey + " is paused");
   }

   public void jobsPaused(String jobGroup) {
      this.logger.info("job group " + jobGroup + " is paused");
   }

   public void jobResumed(JobKey jobKey) {
      this.logger.info(jobKey + " is resumed");
   }

   public void jobsResumed(String jobGroup) {
      this.logger.info("job group " + jobGroup + " is resumed");
   }

   public void schedulerError(String msg, SchedulerException cause) {
      this.logger.error(msg, cause.getUnderlyingException());
   }

   public void schedulerInStandbyMode() {
      this.logger.info("scheduler is in standby mode");
   }

   public void schedulerStarted() {
      this.logger.info("scheduler has been started");
   }

   public void schedulerStarting() {
      this.logger.info("scheduler is being started");
   }

   public void schedulerShutdown() {
      this.logger.info("scheduler has been shutdown");
   }

   public void schedulerShuttingdown() {
      this.logger.info("scheduler is being shutdown");
   }

   public void schedulingDataCleared() {
      this.logger.info("scheduler has cleared all data");
   }
}
