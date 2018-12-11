package com.meicloud.schedule.job;

import com.meicloud.model.RunJob;
import com.meicloud.mq.MQProducer;
import com.meicloud.schedule.Utils.SpringUtil;
import com.meicloud.services.RunJobService;
import com.meicloud.services.ServerService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class CheckJmsJob implements Job {

   private static Logger LOG = LoggerFactory.getLogger(CheckJmsJob.class);
   @Autowired
   private MQProducer mQProducer;
   @Autowired
   private RunJobService runJobService;
   @Autowired
   private Environment environment;
   @Autowired
   private ServerService serverService;
   private static Map waitingRunJob = new HashMap();


   public void init(JobExecutionContext context) throws SchedulerException {
      LOG.info("start to init kettle job context.");
      ApplicationContext applicationContext = SpringUtil.getApplicationContext();
      if(this.mQProducer == null) {
         this.mQProducer = (MQProducer)applicationContext.getBean("mQProducer", MQProducer.class);
      }

      if(this.runJobService == null) {
         this.runJobService = (RunJobService)applicationContext.getBean("runJobService", RunJobService.class);
      }

      LOG.info("init kettle job context success.");
   }

   public void execute(JobExecutionContext context) {
      try {
         this.init(context);
         List e = this.runJobService.getWaitingByDateLatestSendIsNotNull();
         LOG.info("runJob size[" + e.size() + "]");
         ArrayList runJobIds = new ArrayList();
         Iterator var5 = e.iterator();

         while(var5.hasNext()) {
            RunJob runJob = (RunJob)var5.next();
            LOG.info("开始查找MQ是否存在RunJob[" + runJob.getRunJobId() + "]数据");
            boolean b = this.mQProducer.isMQExist(runJob.getRunJobId(), this.environment.getProperty("spring.activemq.jmx.service"), this.environment.getProperty("spring.activemq.jmx.brokerName"));
            if(!b) {
               LOG.info("RunJob[" + runJob.getRunJobId() + "]数据不存在MQ...");
               runJobIds.add(Integer.valueOf(runJob.getRunJobId()));
               waitingRunJob.put(Integer.valueOf(runJob.getRunJobId()), runJob);
            }
         }

         if(runJobIds != null && runJobIds.size() > 0) {
            this.runJobService.updateListLatestSendDateIsNull(runJobIds);
         }
      } catch (Exception var7) {
         LOG.error(var7.getMessage());
         LOG.error(var7.getMessage());
      }

   }
}
