package com.meicloud.controller;

import com.alibaba.fastjson.JSONArray;
import com.meicloud.controller.BasicController;
import com.meicloud.dao.ServerMapper;
import com.meicloud.model.Job;
import com.meicloud.model.KillJobParam;
import com.meicloud.model.RunJob;
import com.meicloud.model.Server;
import com.meicloud.services.ExecuteJobService;
import com.meicloud.services.ExecuteKettleJobServiceImpl;
import com.meicloud.services.ExecutePythonJobServiceImpl;
import com.meicloud.services.ExecuteShellJobServiceImpl;
import com.meicloud.services.ExecuteWebServiceJobServiceImpl;
import com.meicloud.services.JobService;
import com.meicloud.services.RunJobService;
import com.meicloud.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExecuteJobController extends BasicController {

   private static Logger LOG = Logger.getLogger("ExecuteJobController");
   @Autowired
   private RunJobService runJobService;
   @Autowired
   private ServerMapper serverMapper;
   @Autowired
   private JobService jobService;
   @Autowired
   private Environment environment;


   @ApiOperation(
      value = "定时任务JOB执行接口",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"executeJob"},
      method = {RequestMethod.POST, RequestMethod.GET}
   )
   @ResponseBody
   public Object executeJob(@RequestBody RunJob runJob) {
      LOG.info("接收到RunJob[" + runJob.getRunJobId() + "]");
      HashMap resultMap = new HashMap();
      Server server = this.serverMapper.getServerByIP(this.environment.getProperty("app.server.host"));

      RunJob runJob2;
      try {
         Job e = this.jobService.getJobById(runJob.getJobId());
         runJob2 = null;
         Object runJob21;
         if(!StringUtil.isBlank(e.getJobType()) && e.getJobType().equalsIgnoreCase("informatica")) {
            runJob21 = new ExecuteWebServiceJobServiceImpl();
         } else if(!StringUtil.isBlank(e.getJobType()) && e.getJobType().equalsIgnoreCase("shell")) {
            runJob21 = new ExecuteShellJobServiceImpl();
         } else if(!StringUtil.isBlank(e.getJobType()) && e.getJobType().equalsIgnoreCase("python")) {
            runJob21 = new ExecutePythonJobServiceImpl();
         } else {
            runJob21 = new ExecuteKettleJobServiceImpl();
         }

         ((ExecuteJobService)runJob21).executeJob(runJob);
         resultMap.put("resultCode", "1");
         resultMap.put("resultMsg", "【" + server.getIp() + "服务器】执行JOB【" + runJob.getJobId() + "】成功");
      } catch (Exception var7) {
         runJob2 = this.runJobService.getByRunJobId(runJob.getRunJobId());
         if(runJob2.getState() < 2) {
            ArrayList sendedIds = new ArrayList();
            sendedIds.add(Integer.valueOf(runJob.getRunJobId()));
            this.runJobService.updateListLatestSendDateIsNull(sendedIds);
         }

         resultMap.put("resultCode", "0");
         resultMap.put("resultMsg", "【" + server.getIp() + "服务器】执行JOB【" + runJob.getJobId() + "】失败");
         LOG.error(var7.getMessage());
         LOG.error(var7.getMessage());
      }

      return JSONArray.toJSONString(resultMap);
   }

   @ApiOperation(
      value = "杀死JOB接口",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"killJob"},
      method = {RequestMethod.POST, RequestMethod.GET}
   )
   @ResponseBody
   public Object killJob(@RequestBody KillJobParam killJobParam) {
      HashMap resultMap = new HashMap();
      String ip = this.environment.getProperty("app.server.host");

      try {
         if(killJobParam != null && ip.equals(killJobParam.getIp())) {
            RunJob e = this.runJobService.getByRunJobId(killJobParam.getRunJobId());
            Job job = this.jobService.getJobById(e.getJobId());
            Object executeJobService = null;
            if(!StringUtil.isBlank(job.getJobType()) && job.getJobType().equalsIgnoreCase("informatica")) {
               executeJobService = new ExecuteWebServiceJobServiceImpl();
            } else {
               executeJobService = new ExecuteKettleJobServiceImpl();
            }

            ((ExecuteJobService)executeJobService).killJob(killJobParam);
            resultMap.put("resultCode", "1");
            resultMap.put("resultMsg", "【" + ip + "服务器】杀死RUNJOB【" + killJobParam.getRunJobId() + "】成功");
         } else {
            LOG.info("[" + killJobParam + "] not in this server.");
            resultMap.put("resultCode", "0");
            resultMap.put("resultMsg", "【" + ip + "服务器】杀死失败");
         }
      } catch (Exception var7) {
         resultMap.put("resultCode", "0");
         resultMap.put("resultMsg", "【" + ip + "服务器】杀死失败");
         LOG.error(var7.getMessage());
      }

      return JSONArray.toJSONString(resultMap);
   }
}
