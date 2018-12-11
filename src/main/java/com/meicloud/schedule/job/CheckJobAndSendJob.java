package com.meicloud.schedule.job;

import com.meicloud.schedule.Utils.SpringUtil;
import com.meicloud.services.JobCheckAndUpdateService;
import com.meicloud.services.ServerService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
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
public class CheckJobAndSendJob implements Job {

   private static Logger LOG = LoggerFactory.getLogger(CheckJobAndSendJob.class);
   @Autowired
   private JobCheckAndUpdateService jobCheckAndUpdateService;
   @Autowired
   private ServerService serverService;
   @Autowired
   private Environment environment;


   public void init(JobExecutionContext context) throws SchedulerException {
      LOG.info("start to init kettle job context.");
      ApplicationContext applicationContext = SpringUtil.getApplicationContext();
      this.jobCheckAndUpdateService = (JobCheckAndUpdateService)applicationContext.getBean("jobCheckAndUpdateService", JobCheckAndUpdateService.class);
      LOG.info("init kettle job context success.");
   }

   public void execute(JobExecutionContext context) {
      try {
         this.init(context);
         this.jobCheckAndUpdateService.doCheckAndSend();
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
         LOG.error(var3.getMessage());
      }

   }
}
