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
import com.meicloud.webservice.DIServerProperties;
import com.meicloud.webservice.DIServiceInfo;
import com.meicloud.webservice.DataIntegrationInterface;
import com.meicloud.webservice.DataIntegrationServiceLocator;
import com.meicloud.webservice.EPingState;
import com.meicloud.webservice.ETaskRunMode;
import com.meicloud.webservice.EWorkflowRunStatus;
import com.meicloud.webservice.GetWorkflowLogRequest;
import com.meicloud.webservice.Log;
import com.meicloud.webservice.LoginRequest;
import com.meicloud.webservice.PingDIServerRequest;
import com.meicloud.webservice.SessionHeader;
import com.meicloud.webservice.VoidRequest;
import com.meicloud.webservice.WorkflowDetails;
import com.meicloud.webservice.WorkflowRequest;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.axis.client.Stub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ExecuteWebServiceJobServiceImpl extends SessionHeader implements ExecuteJobService {

   private static Logger LOG = LoggerFactory.getLogger(ExecuteWebServiceJobServiceImpl.class);
   @Autowired
   private Environment environment;
   @Autowired
   private JobService jobService;
   @Autowired
   private ServerService serverService;
   @Autowired
   private RunJobService runJobService;
   @Autowired
   private QueueService queueService;


   public ExecuteWebServiceJobServiceImpl() {
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
      Server queryServer = new Server();
      String serverIP = this.environment.getProperty("app.server.host");
      queryServer.setIp(serverIP);
      queryServer.setType("ES");
      List servers = this.serverService.getServerList(queryServer);
      if(servers != null && servers.size() != 0) {
         Server server = (Server)servers.get(0);
         int paralle = server.getMaxParalle();
         int running = server.getCurrentExecuteJobCount();
         LOG.info("max paralle:" + paralle + "  running:" + running);
         if(running >= paralle) {
            LOG.info("CURRENT THERE IS [" + running + "] RUNNNING JOBS IN SERVER [" + this.environment.getProperty("server.ip") + "] WHICH CAN PARALLE [" + paralle + "].");
            ArrayList queue2 = new ArrayList();
            queue2.add(Integer.valueOf(runJob.getRunJobId()));
            this.runJobService.updateListLatestSendDateIsNull(queue2);
         } else if(runJob != null && runJob.validateReceive()) {
            RunJob queue = this.runJobService.getByJobId(runJob.getJobId());
            if(queue != null && queue.getRunJobId() == runJob.getRunJobId() && queue.getRunVersion() == runJob.getRunVersion()) {
               if(queue.getState() == 2) {
                  LOG.info("Job[" + runJob + "] was in running state.");
                  LOG.info("Job[" + runJob + "] prev Round is in running state. give up this round.");
               } else {
                  Queue queue1 = this.queueService.findOne(server.getQueueId());
                  Job job = this.jobService.getJobById(runJob.getJobId());
                  String logFile = runJob.getLogFile();
                  logFile = StringUtil.replaceSysdate(logFile);
                  String logFilePath = this.environment.getProperty("job.log.dist");
                  if(!logFilePath.endsWith("/")) {
                     logFilePath = logFilePath + "/";
                  }

                  logFilePath = logFilePath + "workflow/logs/";
                  if(ServerUtil.isWindows()) {
                     if(!FileUtil.createDir(logFilePath)) {
                        return;
                     }
                  } else {
                     ShellUtil runJob2DB = new ShellUtil();
                     String jobLog = "mkdir -p " + logFilePath;
                     LOG.info("mkdir:" + jobLog);
                     runJob2DB.runSSH(jobLog);
                  }

                  RunJob runJob2DB1 = new RunJob(runJob.getJobId(), runJob.getRunJobId(), 2, server.getId().intValue(), "ABOUT RUNNING WEBSERVICE WORKFLOW.");
                  JobLog jobLog1 = new JobLog(runJob.getJobId(), runJob.getRunJobId(), runJob.getRunVersion(), 2, "ABOUT RUNNING WEBSERVICE WORKFLOW.", server.getId().intValue(), logFilePath + logFile, "");
                  LOG.info("update runJob state param [" + runJob2DB1 + "]");
                  LOG.info("add jobLog [" + jobLog1 + "]");
                  this.runJobService.updateState(runJob2DB1, jobLog1);
                  int currentExecuteJobCount = server.getCurrentExecuteJobCount() + 1;
                  server.setCurrentExecuteJobCount(currentExecuteJobCount);
                  server.setUpdateDate(new Date());
                  this.serverService.update(server);
                  boolean state = true;
                  String updateMsg = "";
                  LoginRequest loginReq = this.createLoginRequest();
                  DataIntegrationServiceLocator diService = new DataIntegrationServiceLocator();
                  DataIntegrationInterface DIWSProxy = diService.getDataIntegration(new URL(this.environment.getProperty("webservices.dataIntegration.url")));
                  String sessionID = DIWSProxy.login(loginReq);
                  LOG.info("sessionID：" + sessionID);
                  ((Stub)DIWSProxy).setHeader(this.createSessionHeader(sessionID));
                  DIServiceInfo diInfo = new DIServiceInfo();
                  diInfo.setDomainName(this.environment.getProperty("webservices.DIServiceInfo.domainName"));
                  diInfo.setServiceName(this.environment.getProperty("webservices.DIServiceInfo.serviceName"));
                  PingDIServerRequest pingReq = new PingDIServerRequest();
                  pingReq.setDIServiceInfo(diInfo);
                  pingReq.setTimeOut(100);
                  EPingState eps = DIWSProxy.pingDIServer(pingReq);
                  LOG.info(eps.toString());
                  DIServerProperties dip = DIWSProxy.getDIServerProperties(diInfo);
                  LOG.info(dip.getDIServerName() + "|" + dip.getDIServerVersion() + "|" + dip.getProductName());
                  WorkflowRequest wfr = new WorkflowRequest();
                  wfr.setDIServiceInfo(diInfo);
                  wfr.setFolderName(job.getFolderName());
                  wfr.setWorkflowName(job.getSvnFile());
                  wfr.setRequestMode(ETaskRunMode.NORMAL);
                  DIWSProxy.startWorkflow(wfr);
                  LOG.info("工作流已启动。。。");

                  try {
                     DIWSProxy.waitTillWorkflowComplete(wfr);
                     LOG.info("工作流已完成。。。");
                  } catch (Exception var30) {
                     state = true;
                     LOG.error(var30.getMessage());
                  }

                  WorkflowDetails wfd = DIWSProxy.getWorkflowDetails(wfr);
                  LOG.info("WorkflowDetails:" + wfd);
                  if(wfd != null) {
                     LOG.info("工作流名称：" + wfd.getWorkflowName() + " 开始时间：" + wfd.getStartTime().getYear() + "-" + wfd.getStartTime().getMonth() + "-" + wfd.getStartTime().getDate() + " " + wfd.getStartTime().getHours() + ":" + wfd.getStartTime().getMinutes() + ":" + wfd.getStartTime().getSeconds() + " 结束时间：" + wfd.getEndTime().getYear() + "-" + wfd.getEndTime().getMonth() + "-" + wfd.getEndTime().getDate() + " " + wfd.getEndTime().getHours() + ":" + wfd.getEndTime().getMinutes() + ":" + wfd.getEndTime().getSeconds() + " 运行状态：" + wfd.getWorkflowRunStatus());
                  }

                  GetWorkflowLogRequest gwlr = new GetWorkflowLogRequest();
                  gwlr.setDIServiceInfo(diInfo);
                  gwlr.setFolderName(job.getFolderName());
                  gwlr.setWorkflowName(job.getSvnFile());
                  gwlr.setTimeout(1000);
                  Log log = DIWSProxy.getWorkflowLog(gwlr);
                  byte state1;
                  if(wfd != null && wfd.getWorkflowRunStatus().equals(EWorkflowRunStatus.SUCCEEDED)) {
                     state1 = 5;
                     updateMsg = "EXECUTE SUCCESS.";
                  } else {
                     state1 = 3;
                     updateMsg = "ERROR ACCURS WHILE EXECUTE.";
                  }

                  LOG.info("fileSize:" + log.getFileSize());
                  LOG.info(log.getBuffer());
                  String context = "执行集群[" + queue1.getQueueName() + "]\n" + "执行服务器IP[" + serverIP + "]\n" + log.getBuffer() + "\n";
                  FileUtil.writeFile(new File(logFilePath + logFile + ".stdout"), context);
                  FileUtil.writeFile(new File(logFilePath + logFile + ".kettle"), context);
                  if(wfd != null && !wfd.getWorkflowRunStatus().equals(EWorkflowRunStatus.SUCCEEDED)) {
                     FileUtil.writeFile(new File(logFilePath + logFile + ".error"), context);
                  }

                  DIWSProxy.logout((VoidRequest)null);
                  currentExecuteJobCount = server.getCurrentExecuteJobCount() - 1;
                  server.setCurrentExecuteJobCount(currentExecuteJobCount);
                  server.setUpdateDate(new Date());
                  this.serverService.update(server);
                  runJob2DB1 = new RunJob(runJob.getJobId(), runJob.getRunJobId(), state1, server.getId().intValue(), updateMsg);
                  jobLog1 = new JobLog(runJob.getJobId(), runJob.getRunJobId(), runJob.getRunVersion(), state1, updateMsg, server.getId().intValue(), logFilePath + logFile, "");
                  LOG.info("update runJob state param [" + runJob2DB1 + "]");
                  LOG.info("add jobLog [" + jobLog1 + "]");
                  this.runJobService.updateState(runJob2DB1, jobLog1);
               }
            } else {
               LOG.info("Job[" + runJob + "] was deleted from db.runtimetable .");
            }
         } else {
            LOG.info("can not receive a normal runjob from queue [" + runJob + "]");
         }
      } else {
         LOG.info("Not found server info by " + this.environment.getProperty("app.server.host") + " es or comprehensive");
      }
   }

   public void killJob(KillJobParam killJobParam) throws Exception {
      DataIntegrationServiceLocator diService = new DataIntegrationServiceLocator();
      DataIntegrationInterface DIWSProxy = diService.getDataIntegration(new URL(this.environment.getProperty("webservices.dataIntegration.url")));
      LoginRequest loginReq = this.createLoginRequest();
      String sessionID = DIWSProxy.login(loginReq);
      LOG.info("sessionID：" + sessionID);
      ((Stub)DIWSProxy).setHeader(this.createSessionHeader(sessionID));
      DIServiceInfo diInfo = new DIServiceInfo();
      diInfo.setDomainName(this.environment.getProperty("webservices.DIServiceInfo.domainName"));
      diInfo.setServiceName(this.environment.getProperty("webservices.DIServiceInfo.serviceName"));
      PingDIServerRequest pingReq = new PingDIServerRequest();
      pingReq.setDIServiceInfo(diInfo);
      pingReq.setTimeOut(100);
      EPingState eps = DIWSProxy.pingDIServer(pingReq);
      LOG.info(eps.toString());
      DIServerProperties dip = DIWSProxy.getDIServerProperties(diInfo);
      LOG.info(dip.getDIServerName() + "|" + dip.getDIServerVersion() + "|" + dip.getProductName());
      RunJob runJob = this.runJobService.getByRunJobId(killJobParam.getRunJobId());
      Job job = this.jobService.getJobById(runJob.getJobId());
      WorkflowRequest wfr = new WorkflowRequest();
      wfr.setDIServiceInfo(diInfo);
      wfr.setFolderName(job.getFolderName());
      wfr.setWorkflowName(job.getSvnFile());
      wfr.setRequestMode(ETaskRunMode.NORMAL);
      DIWSProxy.stopWorkflow(wfr);
      KillJobParam param = new KillJobParam();
      param.setRunJobId(killJobParam.getRunJobId());
      param.setState(3);
      param.setRunVersion(killJobParam.getRunVersion());
      param.setMsg(killJobParam.getMsg());
      this.runJobService.updateStateAfterKill(param);
   }
}
