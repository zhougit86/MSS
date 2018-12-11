package com.meicloud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meicloud.controller.BasicController;
import com.meicloud.enums.LogDebugType;
import com.meicloud.model.Job;
import com.meicloud.model.JobLog;
import com.meicloud.model.KettleJobLog;
import com.meicloud.model.KettleJobLogDetail;
import com.meicloud.model.Server;
import com.meicloud.services.JobService;
import com.meicloud.services.KettleJobLogService;
import com.meicloud.services.LogService;
import com.meicloud.services.ServerService;
import com.meicloud.utils.HttpRequest;
import com.meicloud.utils.StringUtil;
import com.meicloud.utils.Utils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"log"})
public class JobLogController extends BasicController {

   private static Logger logger = LoggerFactory.getLogger(JobLogController.class);
   @Autowired
   private LogService logService;
   @Autowired
   private JobService jobService;
   @Autowired
   private KettleJobLogService kettleJobLogService;
   @Autowired
   private ServerService serverService;
   @Autowired
   private Environment environment;


   @ApiOperation(
      value = "job运行日志列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/jobLogList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object jobLogList(
      @ApiParam(
         value = "页数",
         required = true
      ) @RequestParam String pagenum, 
      @ApiParam(
         value = "行数",
         required = true
      ) @RequestParam String rownum, 
      @ApiParam(
         value = "作业Id",
         required = true
      ) @RequestParam Integer jobId) {
      logger.info("------------->/log/jobLogList job运行日志列表");

      try {
         PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum));
         List e = this.logService.getListByJobId(jobId.intValue());
         PageInfo page = new PageInfo(e);
         return this.outBound(this.getDataInfo(new JobLog(), page.getList(), Long.valueOf(page.getTotal())));
      } catch (Exception var6) {
         logger.error("------------->/log/jobLogList 接口异常" + var6.getMessage());
         return this.errorHandler("job运行日志列表查询异常:" + var6.getMessage());
      }
   }

   @ApiOperation(
      value = "job运行日志信息输出",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/readLog"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object readLog(int logId, 
      @ApiParam(
         value = "日志类型(输出:stdout;错误日志:error;kettle日志:kettle/)",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String type) {
      logger.info("------------->/log/readLog job运行日志信息输出查询");

      try {
         JobLog e = this.logService.getLogById(logId);
         if(e != null && e.getConsoleFile() != null && !"".equals(e.getConsoleFile().trim())) {
            Server server = this.serverService.getServerById(e.getExecuteServerId());
            if(type == null || "".equals(type.trim())) {
               type = "stdout";
            }

            String filePath = e.getConsoleFile() + "." + type;
            logger.info("日志路径：" + filePath);
            String url = "http://" + server.getIp().trim() + ":" + server.getLogAppPort() + "/" + "kettleLogs/log/detail";
            if(StringUtil.isBlank(server.getAppPort())) {
               url = "http://" + server.getIp().trim() + "/" + "kettleLogs/log/detail";
            }

            String param = "kettlePath=" + filePath;
            String reponseJson = HttpRequest.sendPost(url, param);
            if(reponseJson != null && !reponseJson.equals("")) {
               JSONObject object = new JSONObject(reponseJson);
               return object.get("__statusCode").equals("0")?this.errorHandler(object.get("__errorMessage") != null?object.get("__errorMessage").toString():"查询异常"):this.outBound(object.get("data").toString());
            } else {
               return this.outBound("");
            }
         } else {
            return this.errorHandler("Can\'t Read Log  with id:" + logId);
         }
      } catch (Exception var10) {
         logger.error("------------->/log/readLog job运行日志信息输出查询异常" + var10.getMessage());
         return this.errorHandler("job运行日志信息输出查询异常:" + var10.getMessage());
      }
   }

   @ApiOperation(
      value = "日志级别列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/logDebugType"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object logDebugType() {
      logger.info("------------->/log/logDebugType 日志级别列表");

      try {
         return this.outBound(LogDebugType.values());
      } catch (Exception var2) {
         logger.error("------------->/log/logDebugType 接口异常" + var2.getMessage());
         return this.errorHandler("日志级别列表查询异常:" + var2.getMessage());
      }
   }

   @ApiOperation(
      value = "kettle日志列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/kettleJobLogList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object kettleJobLogList(
      @ApiParam(
         value = "页数",
         required = true
      ) @RequestParam String pagenum, 
      @ApiParam(
         value = "行数",
         required = true
      ) @RequestParam String rownum, 
      @ApiParam(
         value = "作业ID",
         required = true
      ) @RequestParam Integer jobId) {
      logger.info("------------->/log/kettleJobLogList kettle日志列表");

      try {
         PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum));
         Job e = this.jobService.getJobById(jobId.intValue());
         logger.info("kettleJobLogList jobName:" + e.getJobName());
         List list = this.kettleJobLogService.getListByJobName(e.getJobName());
         PageInfo page = new PageInfo(list);
         if(!Utils.isEmpityCollection(page.getList())) {
            Iterator var8 = page.getList().iterator();

            while(var8.hasNext()) {
               KettleJobLog jobLog = (KettleJobLog)var8.next();
               jobLog.setGroupId(e.getGroupId());
            }
         }

         return this.outBound(this.getDataInfo(new KettleJobLog(), page.getList(), Long.valueOf(page.getTotal())));
      } catch (Exception var9) {
         logger.error("------------->/log/kettleJobLogList 接口异常" + var9.getMessage());
         return this.errorHandler("kettle日志列表查询异常:" + var9.getMessage());
      }
   }

   @ApiOperation(
      value = "kettle日志详情列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/kettleJobLogDetail"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object kettleJobLogDetail(
      @ApiParam(
         value = "页数",
         required = true
      ) @RequestParam String pagenum, 
      @ApiParam(
         value = "行数",
         required = true
      ) @RequestParam String rownum, int idJob, int jobId) {
      logger.info("------------->/log/kettleJobLogDetail kettle日志详情列表");

      try {
         PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum));
         Job e = this.jobService.getJobById(jobId);
         logger.info("kettleJobLogDetail jobName:" + e.getJobName());
         List list = this.kettleJobLogService.getKettleJobLogDetailListByIdBatch(idJob);
         PageInfo page = new PageInfo(list);
         if(!Utils.isEmpityCollection(page.getList())) {
            Iterator var9 = page.getList().iterator();

            while(var9.hasNext()) {
               KettleJobLogDetail jobLogDetail = (KettleJobLogDetail)var9.next();
               jobLogDetail.setJobId(jobId);
            }
         }

         return this.outBound(this.getDataInfo(new KettleJobLogDetail(), page.getList(), Long.valueOf(page.getTotal())));
      } catch (Exception var10) {
         logger.error("------------->/log/kettleJobLogDetail 接口异常" + var10.getMessage());
         return this.errorHandler("kettle日志详情列表查询异常:" + var10.getMessage());
      }
   }
}
