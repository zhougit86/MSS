package com.meicloud.worker;

import com.alibaba.fastjson.JSONArray;
import com.meicloud.dao.RunJobMapper;
import com.meicloud.dao.ServerMapper;
import com.meicloud.model.JobPusherParam;
import com.meicloud.model.KillJobParam;
import com.meicloud.model.RunJob;
import com.meicloud.model.Server;
import com.meicloud.mq.MQProducer;
import com.meicloud.utils.HttpUtil;
import com.meicloud.utils.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("retryExpireJobExecutor")
public class RetryExpireJobExecutor {

   private static Logger LOG = Logger.getLogger(RetryExpireJobExecutor.class);
   @Autowired
   private RunJobMapper runJobMapper;
   @Autowired
   private ServerMapper serverMapper;
   @Autowired
   private MQProducer mQProducer;


   public void doRetryExpireJob() {
      try {
         List e = this.runJobMapper.getExpireCandidateJobs(2);
         if(e == null || e.size() == 0) {
            LOG.info("no timeOut jobs to retry this round.");
            return;
         }

         ArrayList retryList = new ArrayList();
         ArrayList runOutList = new ArrayList();
         Iterator var5 = e.iterator();

         RunJob runJob;
         while(var5.hasNext()) {
            runJob = (RunJob)var5.next();
            int jobPusherParam = runJob.getExecuteServerId();
            Server server = this.serverMapper.getServerById(jobPusherParam);
            if(server != null) {
               KillJobParam send = new KillJobParam();
               send.setIp(runJob.getIp());
               send.setRunJobId(runJob.getRunJobId());
               send.setRunVersion(runJob.getRunVersion());
               send.setServerFile(runJob.getServerFile());
               send.setAppendParams(runJob.getAppendParams());
               send.setMsg("因超时重试杀原超时进程.");
               send.setState(2);
               HttpUtil httpUtil = new HttpUtil();
               String url = "http://" + server.getIp() + ":" + server.getAppPort() + "/MSS/killJob";
               httpUtil.sendByPost(url, JSONArray.toJSONString(send));

               try {
                  long e1 = 3000L;
                  Thread.sleep(e1);
               } catch (Exception var13) {
                  LOG.error(var13);
               }
            }

            if(runJob.getTimeOutIntervalNum() > runJob.getTimeOutRetryedNum()) {
               RunJob send1 = new RunJob();
               send1.setRunJobId(runJob.getRunJobId());
               send1.setJobId(runJob.getJobId());
               send1.setLogFile(runJob.getLogFile());
               send1.setServerFile(runJob.getServerFile());
               send1.setDebugLevel(runJob.getDebugLevel());
               send1.setRunVersion(runJob.getRunVersion() + 1);
               send1.setAppendParams(runJob.getAppendParams());
               send1.setQueueId(runJob.getQueueId());
               retryList.add(send1);
            } else {
               runOutList.add(runJob);
            }
         }

         if(retryList != null && retryList.size() > 0) {
            var5 = retryList.iterator();

            while(var5.hasNext()) {
               runJob = (RunJob)var5.next();
               runJob.setState(0);
               runJob.setMsg("retry of timeout [" + (runJob.getTimeOutRetryedNum() + 1) + "]");
               this.runJobMapper.update4RetryExpire(runJob);
               LOG.info("retry timeout runjob for not reach it retry limit [" + StringUtil.formJobName(runJob.getServerFile()) + "]");
               JobPusherParam jobPusherParam1 = new JobPusherParam();
               jobPusherParam1.setGroupId(runJob.getGroupId());
               jobPusherParam1.setMsg("UPDATE TO JOB_WAITING_CHECK STATE RETRY TIMEOUT GROUP.");
               jobPusherParam1.setJobId(runJob.getJobId());
               this.runJobMapper.updateJobStateBySatrtAndJobid(jobPusherParam1);
               runJob.setRunVersion(runJob.getRunVersion() + 1);
               this.mQProducer.sendMessage(runJob);
            }
         }

         if(runOutList != null && runOutList.size() > 0) {
            var5 = runOutList.iterator();

            while(var5.hasNext()) {
               runJob = (RunJob)var5.next();
               runJob.setState(3);
               runJob.setMsg("overload retry limit of timeout [" + (runJob.getTimeOutRetryedNum() + 1) + "]");
               this.runJobMapper.update4RetryExpire(runJob);
               LOG.info("runjob overload of its retry limit [" + StringUtil.formJobName(runJob.getServerFile()) + "]");
            }
         }
      } catch (Exception var14) {
         LOG.error(var14);
      }

   }
}
