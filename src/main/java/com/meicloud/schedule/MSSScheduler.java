package com.meicloud.schedule;

import com.meicloud.schedule.Utils.SpringUtil;
import com.meicloud.schedule.job.BatchUpdateEachDayJob;
import com.meicloud.schedule.job.CheckJmsJob;
import com.meicloud.schedule.job.CheckJobAndSendJob;
import com.meicloud.schedule.job.RetryErrorJob;
import com.meicloud.schedule.job.RetryExpireJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MSSScheduler {

   @Autowired
   private Environment environment;


   public void scheduleJobs() throws SchedulerException {
      ApplicationContext annotationContext = SpringUtil.getApplicationContext();
      StdScheduler stdScheduler = (StdScheduler)annotationContext.getBean("scheduler");
      stdScheduler.start();
   }

   private void checkJobAndSendJob(Scheduler scheduler) throws SchedulerException {
      JobKey jobKey = JobKey.jobKey("checkJobAndSendob", "MSSGroup");
      if(scheduler.checkExists(jobKey)) {
         scheduler.deleteJob(jobKey);
      }

      JobDetail jobDetail = JobBuilder.newJob(CheckJobAndSendJob.class).withIdentity("checkJobAndSendob", "MSSGroup").build();
      String corn = this.environment.getProperty("MSS.CheckJobAndSendJob.corn");
      CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(corn);
      CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("triggerCheckJobAndSendob", "MSSGroup").withSchedule(scheduleBuilder).build();
      scheduler.scheduleJob(jobDetail, cronTrigger);
   }

   private void checkJmsJob(Scheduler scheduler) throws SchedulerException {
      JobKey jobKey = JobKey.jobKey("checkJmsJob", "MSSGroup");
      if(scheduler.checkExists(jobKey)) {
         scheduler.deleteJob(jobKey);
      }

      JobDetail jobDetail = JobBuilder.newJob(CheckJmsJob.class).withIdentity("checkJmsJob", "MSSGroup").build();
      String corn = this.environment.getProperty("MSS.CheckJmsJob.corn");
      CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(corn);
      CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("triggerCheckJmsJob", "MSSGroup").withSchedule(scheduleBuilder).build();
      scheduler.scheduleJob(jobDetail, cronTrigger);
   }

   private void batchUpdateEachDayJob(Scheduler scheduler) throws SchedulerException {
      JobKey jobKey = JobKey.jobKey("batchUpdateEachDayJob", "MSSGroup");
      if(scheduler.checkExists(jobKey)) {
         scheduler.deleteJob(jobKey);
      }

      JobDetail jobDetail = JobBuilder.newJob(BatchUpdateEachDayJob.class).withIdentity("batchUpdateEachDayJob", "MSSGroup").build();
      String corn = this.environment.getProperty("MSS.BatchUpdateEachDayJob.corn");
      CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(corn);
      CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("triggerBatchUpdateEachDayJob", "MSSGroup").withSchedule(scheduleBuilder).build();
      scheduler.scheduleJob(jobDetail, cronTrigger);
   }

   private void retryErrorJob(Scheduler scheduler) throws SchedulerException {
      JobKey jobKey = JobKey.jobKey("retryErrorJob", "MSSGroup");
      if(scheduler.checkExists(jobKey)) {
         scheduler.deleteJob(jobKey);
      }

      JobDetail jobDetail = JobBuilder.newJob(RetryErrorJob.class).withIdentity("retryErrorJob", "MSSGroup").build();
      String corn = this.environment.getProperty("MSS.RetryErrorJob.corn");
      CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(corn);
      CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("triggerRetryErrorJob", "MSSGroup").withSchedule(scheduleBuilder).build();
      scheduler.scheduleJob(jobDetail, cronTrigger);
   }

   private void retryExpireJob(Scheduler scheduler) throws SchedulerException {
      JobKey jobKey = JobKey.jobKey("retryExpireJob", "MSSGroup");
      if(scheduler.checkExists(jobKey)) {
         scheduler.deleteJob(jobKey);
      }

      JobDetail jobDetail = JobBuilder.newJob(RetryExpireJob.class).withIdentity("retryExpireJob", "MSSGroup").build();
      String corn = this.environment.getProperty("MSS.RetryExpireJob.corn");
      CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(corn);
      CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("triggerRetryExpireJob", "MSSGroup").withSchedule(scheduleBuilder).build();
      scheduler.scheduleJob(jobDetail, cronTrigger);
   }
}
