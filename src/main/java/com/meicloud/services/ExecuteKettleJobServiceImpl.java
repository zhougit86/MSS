package com.meicloud.services;

import com.meicloud.dao.ServerMapper;
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
import com.meicloud.utils.KillJobUtil;
import com.meicloud.utils.ServerUtil;
import com.meicloud.utils.ShellUtil;
import com.meicloud.utils.StringUtil;
import com.meicloud.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ExecuteKettleJobServiceImpl implements ExecuteJobService {

   private static Logger LOG = LoggerFactory.getLogger(ExecuteKettleJobServiceImpl.class);
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
   @Autowired
   private ServerMapper serverMapper;


   public ExecuteKettleJobServiceImpl() {
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

   public void submit(RunJob runJob) {
      Object fw = null;
      String logFile = runJob.getLogFile();
      logFile = StringUtil.replaceSysdate(logFile);
      String updateMsg = "";
      boolean state = false;
      String command = "";
      ShellUtil shellUtil = new ShellUtil();
      Server queryServer = new Server();
      String serverIP = this.environment.getProperty("app.server.host");
      queryServer.setIp(serverIP);
      queryServer.setType("ES");
      List servers = this.serverService.getServerList(queryServer);
      if(servers != null && servers.size() != 0) {
         Server server1 = (Server)servers.get(0);
         if(server1 == null) {
            ArrayList logFilePath1 = new ArrayList();
            logFilePath1.add(Integer.valueOf(runJob.getRunJobId()));
            this.runJobService.updateListLatestSendDateIsNull(logFilePath1);
            LOG.info("Not found server info by " + this.environment.getProperty("app.server.host"));
         } else {
            LOG.info("查询到RunJob[" + runJob.getRunJobId() + "]主机[" + server1.getIp() + "]...");
            String logFilePath = this.environment.getProperty("job.log.dist");

            byte state1;
            try {
               Queue runJob2 = this.queueService.findOne(server1.getQueueId());
               LOG.info("查询到RunJob[" + runJob.getRunJobId() + "]集群[" + server1.getQueueId() + "]...");
               String e = "";
               if(!Utils.isEmptyStr(server1.getSvnLogPath())) {
                  e = server1.getSvnLogPath() + "/" + runJob2.getQueueCode();
               } else {
                  e = System.getProperty("user.dir") + "/" + runJob2.getQueueCode();
                  LOG.info("获取项目地址：" + e);
               }

               Job jobLog = this.jobService.getJobById(runJob.getJobId());
               LOG.info("查询到RunJob[" + runJob.getRunJobId() + "]作业[" + jobLog.getJobId() + "]...");
               String updated = jobLog.getSvnFile().substring(0, jobLog.getSvnFile().lastIndexOf("/"));
               String kettleFilePath = e + jobLog.getSvnFile();
               if(!jobLog.getSvnFile().startsWith("/")) {
                  kettleFilePath = e + "/" + jobLog.getSvnFile();
               }

               if(logFilePath.endsWith("/")) {
                  logFilePath = logFilePath.substring(0, logFilePath.length() - 1) + updated + "/logs/";
               } else {
                  logFilePath = logFilePath + updated + "/logs/";
               }

               String level = runJob.getDebugLevel();
               String kettleLog = logFilePath + logFile + ".kettle";
               String stdLog = logFilePath + logFile + ".stdout";
               String errorLog = logFilePath + logFile + ".error";
               String appendParams = runJob.getAppendParams();
               LOG.info("拼接RunJob[" + runJob.getRunJobId() + "]日志路径成功：" + kettleLog);
               if(runJob.getAppendParams() != null && !runJob.getAppendParams().equals("")) {
                  appendParams = runJob.getAppendParams() + " -param:MSS_JOB_NAME=" + jobLog.getJobName();
               } else {
                  appendParams = " -param:MSS_JOB_NAME=" + jobLog.getJobName();
               }

               String commandKettlePath = this.environment.getProperty("command.kettle.path");
               if(ServerUtil.isWindows()) {
                  if(commandKettlePath.endsWith("/")) {
                     commandKettlePath = commandKettlePath + "kitchen.bat";
                  } else {
                     commandKettlePath = commandKettlePath + "/" + "kitchen.bat";
                  }
               } else if(commandKettlePath.endsWith("/")) {
                  commandKettlePath = commandKettlePath + "kitchen.sh";
               } else {
                  commandKettlePath = commandKettlePath + "/" + "kitchen.sh";
               }

               if(level != null && !"".equals(level.trim())) {
                  command = commandKettlePath + " -file=" + kettleFilePath + " -logfile=" + kettleLog + " -level=" + level;
               } else {
                  command = commandKettlePath + " -file=" + kettleFilePath + " -logfile=" + kettleLog;
               }

               if(appendParams != null && !"".equals(appendParams.trim())) {
                  command = command + " " + appendParams;
               }

               int stateCount = this.jobService.getJobStateCountByJobId(jobLog.getJobId());
               if(stateCount <= 0) {
                  this.jobService.addJobState(jobLog);
                  LOG.info("添加状态记录成功:jobId[" + jobLog.getJobId() + "]缺失状态记录");
               }

               int sleep = (int)(Math.random() * 1000.0D);
               LOG.info("Runjob[" + runJob.getRunJobId() + "] sleep " + sleep + " msecs wait and check if the same job in running state.");
               Thread.sleep((long)sleep);
               RunJob runJob21 = this.runJobService.getByRunJobId(runJob.getRunJobId());
               if(runJob21.getState() == 2) {
                  LOG.info("runJob[" + runJob.getRunJobId() + "]已在[" + runJob21.getExecuteServerId() + "]服务器执行,本次执行终止");
                  return;
               }

               updateMsg = "ABOUT RUNNING KETTLE SHELL.";
               state1 = 2;
               RunJob runJob2DB = new RunJob(runJob.getJobId(), runJob.getRunJobId(), state1, server1.getId().intValue(), updateMsg);
               JobLog jobLog1 = new JobLog(runJob.getJobId(), runJob.getRunJobId(), runJob.getRunVersion(), state1, updateMsg, server1.getId().intValue(), logFilePath + logFile, command);
               LOG.info("update runJob state param [" + runJob2DB + "]");
               LOG.info("add jobLog [" + jobLog1 + "]");
               boolean updated1 = this.runJobService.updateState(runJob2DB, jobLog1);
               if(updated1) {
                  updateMsg = "update runJob state param [" + runJob2DB + "] success.";
               } else {
                  updateMsg = "update runJob state param [" + runJob2DB + "] failure.";
               }

               LOG.info(updateMsg);
               if(ServerUtil.isWindows()) {
                  if(!FileUtil.createDir(logFilePath)) {
                     return;
                  }
               } else {
                  String success = "mkdir -p " + logFilePath;
                  LOG.info("mkdir:" + success);
                  LOG.info("command:" + command);
                  shellUtil.runSSH(success);
               }

               LOG.info("开始执行RunJob[" + runJob.getRunJobId() + "]");
               boolean success1 = shellUtil.processJob(command, stdLog, errorLog, server1, runJob2);
               if(success1) {
                  state1 = 5;
                  updateMsg = "EXECUTE SUCCESS.";
               } else {
                  state1 = 3;
                  updateMsg = "ERROR ACCURS WHILE EXECUTE.";
               }

               LOG.info("RunJob[" + runJob.getRunJobId() + "]执行结束");
            } catch (Exception var32) {
               LOG.error(var32.getMessage());
               updateMsg = var32.getMessage();
               state1 = 3;
               LOG.error(var32.getMessage());
            }

            RunJob runJob22 = this.runJobService.getByRunJobId(runJob.getRunJobId());
            if(runJob22.getState() == 2) {
               try {
                  Thread.sleep(1000L);
                  RunJob e1 = new RunJob(runJob.getJobId(), runJob.getRunJobId(), state1, server1.getId().intValue(), updateMsg);
                  JobLog jobLog2 = new JobLog(runJob.getJobId(), runJob.getRunJobId(), runJob.getRunVersion(), state1, updateMsg, server1.getId().intValue(), logFilePath + logFile, command);
                  LOG.info("update runJob state param [" + e1 + "]");
                  LOG.info("add jobLog [" + jobLog2 + "]");
                  boolean updated2 = this.runJobService.updateState(e1, jobLog2);
                  if(updated2) {
                     updateMsg = "update runJob state param [" + e1 + "] success.";
                  } else {
                     updateMsg = "update runJob state param [" + e1 + "] failure.";
                  }

                  LOG.info(updateMsg);
               } catch (Exception var31) {
                  LOG.error(var31.getMessage());
               }

            }
         }
      } else {
         ArrayList server = new ArrayList();
         server.add(Integer.valueOf(runJob.getRunJobId()));
         this.runJobService.updateListLatestSendDateIsNull(server);
         LOG.info("Not found server info by " + this.environment.getProperty("app.server.host") + " es or comprehensive");
      }
   }

   public void executeJob(RunJob runJob) throws Exception {
      if(runJob != null && runJob.validateReceive()) {
         RunJob runJobInDB = this.runJobService.getByJobId(runJob.getJobId());
         if(runJobInDB == null) {
            LOG.info("Job[" + runJob + "] was deleted from db.runtimetable .");
         } else if(runJobInDB.getState() == 2) {
            LOG.info("Job[" + runJob + "] was in running state.");
            LOG.info("Job[" + runJob + "] prev Round is in running state. give up this round.");
         } else if(runJobInDB.getRunJobId() == runJob.getRunJobId() && runJobInDB.getRunVersion() == runJob.getRunVersion()) {
            this.submit(runJob);
         } else {
            ArrayList sendedIds = new ArrayList();
            sendedIds.add(Integer.valueOf(runJob.getRunJobId()));
            this.runJobService.updateListLatestSendDateIsNull(sendedIds);
            LOG.info("RunJob[" + runJob.getRunJobId() + "]运行版本与数据库不一致...");
         }
      } else {
         LOG.info("can not receive a normal runjob from queue [" + runJob + "]");
      }

   }

   public void killJob(KillJobParam killJobParam) throws Exception {
      String pidPattern = killJobParam.getServerFile();
      String appendParams = killJobParam.getAppendParams();
      if(pidPattern != null && !"".equals(pidPattern.trim())) {
         KillJobUtil killJobTool = new KillJobUtil();
         killJobTool.killJobRecusion(pidPattern, appendParams);
         long l = 600L;
         Thread.sleep(l);
         RunJob runJob = this.runJobService.getByRunJobId(killJobParam.getRunJobId());
         KillJobParam param = new KillJobParam();
         param.setRunJobId(killJobParam.getRunJobId());
         param.setState(3);
         param.setRunVersion(killJobParam.getRunVersion());
         param.setMsg(killJobParam.getMsg());
         param.setErrorRetryedCount(runJob.getrErrorIntervalNum());
         this.runJobService.updateStateAfterKill(param);
         this.runJobService.killYarn(runJob.getJobId());
      } else {
         LOG.info("invalidate kill job pidPattern.");
      }

   }
}
