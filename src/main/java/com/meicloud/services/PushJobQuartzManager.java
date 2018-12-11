package com.meicloud.services;

import com.meicloud.model.Group;
import com.meicloud.schedule.job.KettleJob;
import java.util.Date;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PushJobQuartzManager {

   private static Logger LOG = Logger.getLogger(PushJobQuartzManager.class);
   @Autowired
   private Scheduler scheduler;
   private String jobName;
   private String groupName;


   public void startScheduler() {
      try {
         if(!this.scheduler.isStarted()) {
            this.scheduler.start();
         }

         LOG.info("scheduler started..");
      } catch (Exception var2) {
         LOG.error(var2);
      }

   }

   public void shutdownScheduler() {
      try {
         this.scheduler.clear();
         this.scheduler.shutdown(true);
      } catch (Exception var2) {
         LOG.error(var2);
      }

   }

   public void pauseJob(Group group) throws Exception {
      try {
         this.setJobNameGroupName(group);
         JobKey e = JobKey.jobKey(this.jobName, this.groupName);
         if(this.scheduler.checkExists(e)) {
            this.scheduler.pauseJob(e);
         }

      } catch (Exception var3) {
         LOG.error(var3);
         throw var3;
      }
   }

   public void resumeJob(Group group) throws Exception {
      try {
         this.setJobNameGroupName(group);
         JobKey e = JobKey.jobKey(this.jobName, this.groupName);
         if(this.scheduler.checkExists(e)) {
            this.scheduler.resumeJob(e);
         }

      } catch (Exception var3) {
         LOG.error(var3);
         throw var3;
      }
   }

   public boolean existsJob(Group group) throws Exception {
      try {
         this.setJobNameGroupName(group);
         JobKey e = JobKey.jobKey(this.jobName, this.groupName);
         return this.scheduler.checkExists(e);
      } catch (Exception var3) {
         LOG.error(var3);
         throw var3;
      }
   }

   public void clearJobs() throws Exception {
      try {
         this.scheduler.clear();
      } catch (Exception var2) {
         LOG.error(var2);
         throw var2;
      }
   }

   public void addCronJob(Group group) throws Exception {
      try {
         LOG.info("start to add cron trigger job [" + group + "]");
         this.setJobNameGroupName(group);
         String e = this.jobName;
         String cronExpression = group.getCron();
         JobKey jobKey = JobKey.jobKey(this.jobName, this.groupName);
         if(this.scheduler.checkExists(jobKey)) {
            String jobClazz = "Job with JobKey:" + this.jobName + "." + this.groupName + " already exists. will delete it first.";
            LOG.info(jobClazz);
            this.scheduler.deleteJob(jobKey);
         }

         Class jobClazz1 = KettleJob.class;
         JobDetail jobDetail = JobBuilder.newJob(jobClazz1).withIdentity(this.jobName, this.groupName).build();
         CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity(e, this.groupName).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
         jobDetail.getJobDataMap().put("groupId", group.getGroupId());
         this.scheduler.scheduleJob(jobDetail, trigger);
         if(!this.scheduler.isStarted()) {
            this.scheduler.start();
         }

         LOG.info("finish add cron trigger job [" + group + "]");
      } catch (Exception var8) {
         LOG.error("exception accurs while add cron job [" + group + "]", var8);
         throw var8;
      }
   }

   public void addSimpleJob(Group group) throws Exception {
      try {
         LOG.info("start to add simple trigger job[" + group + "]");
         this.setJobNameGroupName(group);
         String e = this.jobName;
         Class jobClazz = KettleJob.class;
         Date startAt = DateBuilder.nextGivenSecondDate((Date)null, 10);
         JobDetail jobDetail = JobBuilder.newJob(jobClazz).withIdentity(this.jobName, this.groupName).build();
         SimpleTrigger simpleTrigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity(e, this.groupName).startAt(startAt).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(0)).build();
         jobDetail.getJobDataMap().put("groupId", group.getGroupId());
         jobDetail.getJobDataMap().put("continueFlag", true);
         TriggerKey triggerKey = TriggerKey.triggerKey(e, this.groupName);
         if(this.scheduler.checkExists(triggerKey)) {
            this.scheduler.rescheduleJob(triggerKey, simpleTrigger);
         } else {
            this.scheduler.scheduleJob(jobDetail, simpleTrigger);
         }

         if(!this.scheduler.isStarted()) {
            this.scheduler.start();
         }

         LOG.info("finish add simple trigger job[" + group + "]");
      } catch (Exception var8) {
         LOG.error("exception accurs while adding simple trigger job [" + group + "]", var8);
         throw var8;
      }
   }

   public void removeJob(Group group) throws Exception {
      try {
         LOG.info("start to remove job [" + group + "]");
         this.setJobNameGroupName(group);
         JobKey e = JobKey.jobKey(this.jobName, this.groupName);
         if(this.scheduler.checkExists(e)) {
            this.scheduler.deleteJob(e);
         }

         LOG.info("finish remove job[" + group + "]");
      } catch (Exception var3) {
         LOG.error("exception accurs while remove job[" + group + "]", var3);
         throw var3;
      }
   }

   private void setJobNameGroupName(Group group) {
      this.jobName = String.valueOf(group.getGroupId());
      this.groupName = group.getGroupName();
   }
}
