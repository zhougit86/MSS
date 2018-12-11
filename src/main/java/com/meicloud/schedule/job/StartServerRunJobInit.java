package com.meicloud.schedule.job;

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
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class StartServerRunJobInit implements CommandLineRunner {

   private static final Logger LOG = LoggerFactory.getLogger(StartServerRunJobInit.class);
   @Autowired
   private Environment environment;
   @Autowired
   private RunJobMapper runJobMapper;
   @Autowired
   private ServerMapper serverMapper;
   @Autowired
   private MQProducer mQProducer;


   public void run(String ... args) throws Exception {
      Server queryServer = new Server();
      String serverIP = this.environment.getProperty("app.server.host");
      queryServer.setIp(serverIP);
      List servers = this.serverMapper.getServerList(queryServer);
      Iterator var6 = servers.iterator();

      while(var6.hasNext()) {
         Server server = (Server)var6.next();
         List originalTimeOutList = this.runJobMapper.getExpireCandidateJobsBySysStart(server.getId());
         Iterator var9 = originalTimeOutList.iterator();

         while(var9.hasNext()) {
            RunJob runJob = (RunJob)var9.next();
            KillJobParam killJobParam = new KillJobParam();
            killJobParam.setIp(runJob.getIp());
            killJobParam.setRunJobId(runJob.getRunJobId());
            killJobParam.setRunVersion(runJob.getRunVersion());
            killJobParam.setServerFile(runJob.getServerFile());
            killJobParam.setAppendParams(runJob.getAppendParams());
            killJobParam.setMsg("因超时重试杀原超时进程.");
            killJobParam.setState(2);
            HttpUtil httpUtil = new HttpUtil();
            String url = "http://" + server.getIp() + ":" + server.getAppPort() + "/MSS/killJob";
            httpUtil.sendByPost(url, JSONArray.toJSONString(killJobParam));

            try {
               Thread.sleep(3000L);
            } catch (Exception var15) {
               LOG.error(var15.getMessage());
               LOG.error(var15.getMessage());
            }

            RunJob updateRunJob = new RunJob();
            updateRunJob.setRunJobId(runJob.getRunJobId());
            updateRunJob.setState(0);
            updateRunJob.setRunVersion(runJob.getRunVersion() + 1);
            updateRunJob.setMsg("RunJob[" + runJob.getRunJobId() + "] retry of timeout [" + (runJob.getTimeOutRetryedNum() + 1) + "]");
            this.runJobMapper.update4RetryExpire(updateRunJob);
            LOG.info("RunJob[" + runJob.getRunJobId() + "] retry timeout runjob for not reach it retry limit [" + StringUtil.formJobName(runJob.getServerFile()) + "]");
            JobPusherParam jobPusherParam = new JobPusherParam();
            jobPusherParam.setGroupId(runJob.getGroupId());
            jobPusherParam.setMsg("START SERVER UPDATE TO RunJob[" + runJob.getRunJobId() + "] JOB_WAITING_CHECK STATE RETRY TIMEOUT GROUP.");
            jobPusherParam.setJobId(runJob.getJobId());
            this.runJobMapper.updateJobStateBySatrtAndJobid(jobPusherParam);
            runJob.setRunVersion(runJob.getRunVersion() + 1);
            this.mQProducer.sendMessage(runJob);
         }
      }

   }
}
