package com.meicloud.schedule.job;

import com.meicloud.schedule.Utils.SpringUtil;
import com.meicloud.services.ServerService;
import com.meicloud.worker.RetryErrorJobExecutor;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class RetryErrorJob implements Job {

   private static Logger LOG = LoggerFactory.getLogger(RetryErrorJob.class);
   @Autowired
   RetryErrorJobExecutor retryErrorJobExecutor;
   @Autowired
   private Environment environment;
   @Autowired
   private ServerService serverService;


   public void init(JobExecutionContext context) throws SchedulerException {
      LOG.info("start to init kettle job context.");
      ApplicationContext applicationContext = SpringUtil.getApplicationContext();
      this.retryErrorJobExecutor = (RetryErrorJobExecutor)applicationContext.getBean("retryErrorJobExecutor", RetryErrorJobExecutor.class);
      LOG.info("init kettle job context success.");
   }

   public void execute(JobExecutionContext context) throws JobExecutionException {
      try {
         LOG.info("start to check and update retryerror jobs.");
         this.init(context);
         this.retryErrorJobExecutor.doRetryErrorJobs();
         LOG.info("finish to check and update retryerror jobs.");
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
      }

   }
}
