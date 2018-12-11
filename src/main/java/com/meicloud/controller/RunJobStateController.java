package com.meicloud.controller;

import com.meicloud.model.JobLog;
import com.meicloud.model.KillJobParam;
import com.meicloud.model.RunJob;
import com.meicloud.services.RunJobService;
import com.meicloud.utils.WebUtil;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RunJobStateController {

   private static Logger LOG = Logger.getLogger(RunJobStateController.class);
   @Autowired
   private RunJobService runJobService;


   @RequestMapping({"de/updateRunJobState"})
   public String updateRunJobState(HttpServletResponse response, int jobId, int runJobId, int runVersion, int state, int executeServerId, String consoleFile, String updateMsg, String cmd) throws Exception {
      String msg = "";

      try {
         msg = this.validata(jobId, runJobId, runVersion, state, executeServerId, consoleFile, updateMsg, cmd);
         if(msg.length() > 0) {
            WebUtil.writeJson(response, msg);
            return null;
         }

         msg = "更新作业运行状态的请求已接收.";
         WebUtil.writeJson(response, msg);
         RunJob e = new RunJob(jobId, runJobId, state, executeServerId, updateMsg);
         JobLog jobLog = new JobLog(jobId, runJobId, runVersion, state, updateMsg, executeServerId, consoleFile, cmd);
         LOG.info("update runJob state param [" + e + "]");
         LOG.info("add jobLog [" + jobLog + "]");
         boolean updated = this.runJobService.updateState(e, jobLog);
         if(updated) {
            msg = "update runJob state param [" + e + "] success.";
         } else {
            msg = "update runJob state param [" + e + "] failure.";
         }

         LOG.info(msg);
         WebUtil.writeJson(response, msg);
      } catch (Exception var14) {
         LOG.error(var14);
         WebUtil.writeJson(response, var14.getMessage());
      }

      return null;
   }

   @RequestMapping({"de/updateStateAfterKill"})
   public String updateRunJobStateAfterKill(HttpServletResponse response, int runJobId, int runVersion) throws Exception {
      String msg = "";

      try {
         msg = "更新作业运行状态的请求已接收.";
         WebUtil.writeJson(response, msg);
         KillJobParam e = new KillJobParam();
         e.setRunJobId(runJobId);
         e.setState(3);
         e.setRunVersion(runVersion);
         e.setMsg("JOB KILLED.");
         boolean updated = this.runJobService.updateStateAfterKill(e);
         if(updated) {
            msg = "update state after kill job param [" + e + "] success.";
         } else {
            msg = "update state after kill job param [" + e + "] failure.";
         }

         LOG.info(msg);
      } catch (Exception var7) {
         LOG.error(var7);
      }

      return null;
   }

   private String validata(int jobId, int runJobId, int runVersion, int state, int executeServerId, String consoleFile, String updateMsg, String cmd) {
      String msg = "";
      if(jobId <= 0) {
         msg = "invalidate jobId[" + jobId + "]";
      } else if(runJobId <= 0) {
         msg = "invalidate runJobId[" + runJobId + "]";
      } else if(state >= 0 && state <= 6) {
         if(executeServerId < 1) {
            msg = "invalidate executeServerId [" + executeServerId + "]";
         } else if(consoleFile != null && !"".equals(consoleFile.trim())) {
            if(cmd == null || "".equals(cmd.trim())) {
               msg = "invalidate cmd [" + cmd + "]";
            }
         } else {
            msg = "invalidate consoleFile [" + consoleFile + "]";
         }
      } else {
         msg = "invalidate state [" + state + "]";
      }

      return msg;
   }
}
