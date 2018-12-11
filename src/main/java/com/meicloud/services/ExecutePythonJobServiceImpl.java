package com.meicloud.services;

import com.meicloud.model.Job;
import com.meicloud.model.JobLog;
import com.meicloud.model.KillJobParam;
import com.meicloud.model.Queue;
import com.meicloud.model.RunJob;
import com.meicloud.model.Server;
import com.meicloud.schedule.Utils.SpringUtil;
import com.meicloud.services.ExecuteJobService;
import com.meicloud.services.JobService;
import com.meicloud.services.QueueService;
import com.meicloud.services.RunJobService;
import com.meicloud.services.ServerService;
import com.meicloud.utils.FileUtil;
import com.meicloud.utils.ServerUtil;
import com.meicloud.utils.ShellUtil;
import com.meicloud.utils.StringUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

public class ExecutePythonJobServiceImpl implements ExecuteJobService {

   private static Logger LOG = LoggerFactory.getLogger(ExecutePythonJobServiceImpl.class);
   @Autowired
   private ServerService serverService;
   @Autowired
   private RunJobService runJobService;
   @Autowired
   private Environment environment;
   @Autowired
   private QueueService queueService;
   @Autowired
   private JobService jobService;


   public ExecutePythonJobServiceImpl() {
      ApplicationContext applicationContext = SpringUtil.getApplicationContext();
      if(this.serverService == null) {
         this.serverService = (ServerService)applicationContext.getBean("serverService", ServerService.class);
      }

      if(this.runJobService == null) {
         this.runJobService = (RunJobService)applicationContext.getBean("runJobService", RunJobService.class);
      }

      if(this.environment == null) {
         this.environment = (Environment)applicationContext.getBean("environment", Environment.class);
      }

      if(this.queueService == null) {
         this.queueService = (QueueService)applicationContext.getBean("queueService", QueueService.class);
      }

      if(this.jobService == null) {
         this.jobService = (JobService)applicationContext.getBean("jobService", JobService.class);
      }

   }

   public void executeJob(RunJob runJob) throws Exception {
      Server server = this.serverService.getServerByIP(this.environment.getProperty("app.server.host"));
      Queue queue = this.queueService.findOne(server.getQueueId());
      int paralle = server.getMaxParalle();
      int running = server.getCurrentExecuteJobCount();
      LOG.info("max paralle:" + paralle + "  running:" + running);
      if(running >= paralle) {
         LOG.info("CURRENT THERE IS [" + running + "] RUNNNING JOBS IN SERVER [" + this.environment.getProperty("server.ip") + "] WHICH CAN PARALLE [" + paralle + "].");
         ArrayList logFile1 = new ArrayList();
         logFile1.add(Integer.valueOf(runJob.getRunJobId()));
         this.runJobService.updateListLatestSendDateIsNull(logFile1);
      } else {
         String logFile = runJob.getLogFile();
         logFile = StringUtil.replaceSysdate(logFile);
         String logFilePath = this.environment.getProperty("job.log.dist");
         if(!logFilePath.endsWith("/")) {
            logFilePath = logFilePath + "/";
         }

         logFilePath = logFilePath + "python/logs/";
         RunJob runJob2DB = new RunJob(runJob.getJobId(), runJob.getRunJobId(), 2, server.getId().intValue(), "ABOUT RUNNING SHELL.");
         JobLog jobLog = new JobLog(runJob.getJobId(), runJob.getRunJobId(), runJob.getRunVersion(), 2, "ABOUT RUNNING SHELL.", server.getId().intValue(), logFilePath + logFile, "");
         LOG.info("update runJob state param [" + runJob2DB + "]");
         LOG.info("add jobLog [" + jobLog + "]");
         this.runJobService.updateState(runJob2DB, jobLog);
         int currentExecuteJobCount = server.getCurrentExecuteJobCount() + 1;
         server.setCurrentExecuteJobCount(currentExecuteJobCount);
         server.setUpdateDate(new Date());
         this.serverService.update(server);
         boolean state = true;
         String updateMsg = "";
         Process ps = null;
         BufferedReader br = null;
         Object fwStdout = null;
         Object fwError = null;
         Object fwKettle = null;

         byte state1;
         try {
            Job e = this.jobService.getJobById(runJob.getJobId());
            String cmd = "python " + e.getSvnFile();
            ps = Runtime.getRuntime().exec(cmd);
            int runningStatus = ps.waitFor();
            br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            if(ServerUtil.isWindows()) {
               if(!FileUtil.createDir(logFilePath)) {
                  return;
               }
            } else {
               String fwStdoutContext = "mkdir -p " + logFilePath;
               LOG.info("mkdir:" + fwStdoutContext);
               ShellUtil fwErrorContext = new ShellUtil();
               fwErrorContext.runSSH(fwStdoutContext);
            }

            StringBuilder fwStdoutContext1 = new StringBuilder();
            fwStdoutContext1.append("执行集群[" + queue.getQueueName() + "]\n" + "执行服务器IP[" + server.getIp() + "]\n" + "执行命令:[" + cmd + "]\n\n");
            StringBuilder fwErrorContext1 = new StringBuilder();
            fwErrorContext1.append("执行集群[" + queue.getQueueName() + "]\n" + "执行服务器IP[" + server.getIp() + "]\n" + "执行命令:[" + cmd + "]\n\n");
            String line = null;

            while((line = br.readLine()) != null) {
               LOG.info(line);
               fwStdoutContext1.append(line);
               if(runningStatus != 0) {
                  fwErrorContext1.append(line);
               }
            }

            FileUtil.writeFile(new File(logFilePath + logFile + ".stdout"), fwStdoutContext1.toString());
            FileUtil.writeFile(new File(logFilePath + logFile + ".kettle"), fwStdoutContext1.toString());
            FileUtil.writeFile(new File(logFilePath + logFile + ".error"), fwErrorContext1.toString());
            if(runningStatus != 0) {
               state1 = 3;
               updateMsg = "ERROR ACCURS WHILE EXECUTE.";
            } else {
               state1 = 5;
               updateMsg = "EXECUTE SUCCESS.";
            }
         } catch (Exception var33) {
            state1 = 3;
            updateMsg = "ERROR ACCURS WHILE EXECUTE.";
            LOG.error(var33.getMessage());
         } finally {
            try {
               if(fwKettle != null) {
                  ((FileWriter)fwError).close();
               }

               if(fwKettle != null) {
                  ((FileWriter)fwKettle).close();
               }

               if(fwStdout != null) {
                  ((FileWriter)fwStdout).close();
               }

               if(br != null) {
                  br.close();
               }

               if(ps != null) {
                  ps.destroy();
               }
            } catch (Exception var32) {
               LOG.error(var32.getMessage());
            }

            currentExecuteJobCount = server.getCurrentExecuteJobCount() - 1;
            server.setCurrentExecuteJobCount(currentExecuteJobCount);
            server.setUpdateDate(new Date());
            this.serverService.update(server);
         }

         runJob2DB = new RunJob(runJob.getJobId(), runJob.getRunJobId(), state1, server.getId().intValue(), updateMsg);
         jobLog = new JobLog(runJob.getJobId(), runJob.getRunJobId(), runJob.getRunVersion(), state1, updateMsg, server.getId().intValue(), logFilePath + logFile, "");
         LOG.info("update runJob state param [" + runJob2DB + "]");
         LOG.info("add jobLog [" + jobLog + "]");
         this.runJobService.updateState(runJob2DB, jobLog);
      }
   }

   public void killJob(KillJobParam killJobParam) throws Exception {}
}
