package com.meicloud.schedule.job;

import com.meicloud.schedule.Utils.SpringUtil;
import com.meicloud.services.ServerService;
import com.meicloud.worker.BatchUpdateEachDayJobExecutor;
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
import org.springframework.transaction.annotation.Transactional;

@Component
@EnableScheduling
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class BatchUpdateEachDayJob implements Job {

   private static Logger LOG = LoggerFactory.getLogger(BatchUpdateEachDayJob.class);
   @Autowired
   private BatchUpdateEachDayJobExecutor batchUpdateEachDayJobExecutor;
   @Autowired
   private Environment environment;
   @Autowired
   private ServerService serverService;


   public void init(JobExecutionContext context) throws SchedulerException {
      LOG.info("start to init kettle job context.");
      ApplicationContext applicationContext = SpringUtil.getApplicationContext();
      this.batchUpdateEachDayJobExecutor = (BatchUpdateEachDayJobExecutor)applicationContext.getBean("batchUpdateEachDayJobExecutor", BatchUpdateEachDayJobExecutor.class);
      LOG.info("init kettle job context success.");
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void execute(JobExecutionContext context) throws JobExecutionException {
      try {
         LOG.info("start to check and update jobs batch.");
         this.init(context);
         this.batchUpdateEachDayJobExecutor.doUpdate();
         LOG.info("finish to check and update jobs batch.");
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
      }

   }
}
