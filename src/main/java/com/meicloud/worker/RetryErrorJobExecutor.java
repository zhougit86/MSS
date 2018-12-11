package com.meicloud.worker;

import com.meicloud.dao.RunJobMapper;
import com.meicloud.model.JobPusherParam;
import com.meicloud.model.RunJob;
import com.meicloud.mq.MQProducer;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("retryErrorJobExecutor")
public class RetryErrorJobExecutor {

   private static Logger LOG = Logger.getLogger(RetryErrorJobExecutor.class);
   @Autowired
   private RunJobMapper runJobMapper;
   @Autowired
   private MQProducer mQProducer;


   public void doRetryErrorJobs() {
      try {
         List e = this.runJobMapper.getRetryErrorCandidateJobs(3);
         if(e == null || e.size() == 0) {
            LOG.info("no error jobs to be retry this checking round.");
            return;
         }

         Iterator var3 = e.iterator();

         while(var3.hasNext()) {
            RunJob runJob = (RunJob)var3.next();
            RunJob runJobDB = new RunJob();
            runJobDB.setRunJobId(runJob.getRunJobId());
            runJobDB.setState(0);
            runJobDB.setMsg("UPDATE FOR RETRY ERROR.");
            this.runJobMapper.update4RetryError(runJobDB);
            JobPusherParam jobPusherParam = new JobPusherParam();
            jobPusherParam.setGroupId(runJob.getGroupId());
            jobPusherParam.setMsg("UPDATE TO JOB_WAITING_CHECK STATE RETRY GROUP.");
            jobPusherParam.setJobId(runJob.getJobId());
            this.runJobMapper.updateJobStateBySatrtAndJobid(jobPusherParam);
            runJob.setRunVersion(runJob.getRunVersion() + 1);
            this.mQProducer.sendMessage(runJob);
         }
      } catch (Exception var6) {
         LOG.error(var6);
      }

   }
}
