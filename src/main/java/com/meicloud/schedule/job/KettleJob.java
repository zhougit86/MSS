package com.meicloud.schedule.job;

import com.meicloud.model.Group;
import com.meicloud.model.RunJob;
import com.meicloud.services.GroupService;
import com.meicloud.services.PushJobService;
import com.meicloud.services.RunJobService;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@EnableScheduling
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class KettleJob implements Job {

   private static Logger LOG = Logger.getLogger(KettleJob.class);
   @Autowired
   private PushJobService pushJobService;
   @Autowired
   private GroupService groupService;
   @Autowired
   private RunJobService runJobService;


   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void execute(JobExecutionContext context) throws JobExecutionException {
      LOG.info("start to push kettle jobs to RunTime.");

      try {
         JobDataMap e = context.getJobDetail().getJobDataMap();
         Object o = e.get("groupId");
         if(o == null) {
            LOG.info("Cannot get input param groupId.");
            return;
         }

         int groupId = ((Integer)o).intValue();
         if(groupId == 0) {
            LOG.info("no kettle jobs to be pushed to runtime of groupId is " + groupId);
            return;
         }

         Group group = this.groupService.getById(groupId);
         if(group == null || !group.isEnable() || !group.isTimeTrigger()) {
            LOG.info("no need to push to runtime by quartz. " + groupId);
            return;
         }

         List list = this.runJobService.getByGroupId(groupId);
         Iterator var8 = list.iterator();

         while(var8.hasNext()) {
            RunJob pushType = (RunJob)var8.next();
            if(pushType.getState() == 0 || pushType.getState() == 1 || pushType.getState() == 2) {
               LOG.info("[" + groupId + "]组上次未执行完，终止本次调起... ");
               return;
            }
         }

         byte pushType1 = 0;
         this.pushJobService.pushJob(groupId, pushType1);
      } catch (Exception var9) {
         LOG.error(var9.getMessage());
         LOG.info(var9);
      }

      LOG.info("end execute kettle job.");
   }
}
