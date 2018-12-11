package com.meicloud.schedule.listener;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class SimpleJobListener implements JobListener {

   private final Logger logger = Logger.getLogger(this.getClass());


   public String getName() {
      String name = this.getClass().getSimpleName();
      this.logger.info(" listener name is:" + name);
      return name;
   }

   public void jobToBeExecuted(JobExecutionContext context) {
      String jobName = context.getJobDetail().getKey().getName();
      this.logger.info(jobName + " is going to be executed");
   }

   public void jobExecutionVetoed(JobExecutionContext context) {
      String jobName = context.getJobDetail().getKey().getName();
      this.logger.info(jobName + " was vetoed and not executed");
   }

   public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
      String jobName = context.getJobDetail().getKey().getName();
      this.logger.info(jobName + " was executed");
   }
}
