package com.meicloud.services;

import com.meicloud.model.JobState;
import com.meicloud.model.RunJob;
import com.meicloud.model.SubGroup;
import com.meicloud.services.JobCheckAndUpdateService;
import com.meicloud.services.RunJobService;
import com.meicloud.services.SendJobService;
import com.meicloud.services.SubGroupService;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jobCheckAndUpdateService")
public class JobCheckAndUpdateServiceImpl implements JobCheckAndUpdateService {

   private static Logger LOG = LoggerFactory.getLogger(JobCheckAndUpdateServiceImpl.class);
   @Autowired
   private SubGroupService subGroupService;
   @Autowired
   private SendJobService sendJobService;
   @Autowired
   private RunJobService runJobService;
   private static List jobStates;
   private Integer groupId;


   public boolean doCheckAndUpdateByGroupId(int groupId) {
      try {
         String e = "start to fetch and update UnSordedGroupReferJobRefer jobs.";
         LOG.info(e);
         boolean finish = this.sendJobService.completeGroup(groupId) && this.sendJobService.completeJob(groupId);
         if(finish) {
            this.sendJobService.send(groupId);
            return true;
         } else {
            e = "REFERED JOBS UNFINISH OF GROUP[" + groupId + "]";
            LOG.info(e);
            return false;
         }
      } catch (Exception var4) {
         LOG.error(var4.getMessage());
         return false;
      }
   }

   public void doCheckAndSend() {
      try {
         long e = System.currentTimeMillis();
         String msg = "start to fetch and update JobCheckAndUpdateExecutor jobs.";
         LOG.info(msg);
         List runJobs = this.subGroupService.getGarbageRunJob();
         Iterator candidateList = runJobs.iterator();

         RunJob query;
         while(candidateList.hasNext()) {
            query = (RunJob)candidateList.next();
            this.subGroupService.deleteGarbageRunJob(query.getJobId());
         }

         runJobs = this.subGroupService.getGarbageRunJob();
         candidateList = runJobs.iterator();

         while(candidateList.hasNext()) {
            query = (RunJob)candidateList.next();
            this.runJobService.deleteByRunJobId(query.getRunJobId());
         }

         SubGroup query1 = new SubGroup();
         List candidateList1 = this.subGroupService.getCandidateList(query1);
         if(candidateList1 == null || candidateList1.size() == 0) {
            msg = "no candidateList of checkAndUpdate jobs fetched from DB.";
            LOG.info(msg);
            return;
         }

         jobStates = this.subGroupService.getReferedGroupJobStateList();
         Iterator var8 = candidateList1.iterator();

         while(var8.hasNext()) {
            SubGroup endTime = (SubGroup)var8.next();
            if(endTime != null) {
               int s = endTime.getGroupId();
               this.groupId = Integer.valueOf(s);
               LOG.info("开始校验[" + s + "]组依赖...");
               boolean finish = this.completeGroup(s) && this.sendJobService.completeJob(s);
               LOG.info("校验[" + s + "]组依赖完成...");
               if(finish) {
                  this.sendJobService.send(s);
               } else {
                  msg = "REFERED JOBS UNFINISH OF GROUP[" + s + "]";
                  LOG.info(msg);
               }
            }
         }

         long endTime1 = System.currentTimeMillis();
         String s1 = String.valueOf((endTime1 - e) / 1000L);
         LOG.info("finish to fetch and update JobCheckAndUpdateExecutor jobs. times[" + s1 + "s]");
      } catch (Exception var11) {
         LOG.error(var11.getMessage());
      }

   }

   public boolean completeGroup(int groupId) {
      if(jobStates != null && jobStates.size() > 0) {
         Iterator var3 = jobStates.iterator();

         while(var3.hasNext()) {
            JobState jobState = (JobState)var3.next();
            if(jobState.getGroupId() == groupId) {
               if(!this.groupId.equals(Integer.valueOf(groupId)) && jobState.isEnable() && jobState.getState() != 5 && jobState.getState() != 4) {
                  LOG.info("依赖组[" + jobState.getGroupId() + "]里的job[" + jobState.getJobId() + "]状态为[" + jobState.getState() + "],该依赖未完成，[" + this.groupId + "]组拒绝发送...");
                  return false;
               }

               boolean b = this.completeGroup(jobState.getReferedGroupId());
               if(!b) {
                  return false;
               }
            }
         }
      }

      return true;
   }
}
