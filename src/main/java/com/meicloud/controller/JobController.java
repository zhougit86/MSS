package com.meicloud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meicloud.controller.BasicController;
import com.meicloud.dto.DicVO;
import com.meicloud.enums.LengthLimit;
import com.meicloud.model.Account;
import com.meicloud.model.Group;
import com.meicloud.model.Job;
import com.meicloud.model.Queue;
import com.meicloud.model.ScheduleParameter;
import com.meicloud.model.Server;
import com.meicloud.services.GroupService;
import com.meicloud.services.JobService;
import com.meicloud.services.QueueService;
import com.meicloud.services.RoleGroupService;
import com.meicloud.services.RunJobService;
import com.meicloud.services.ScheduleParameterService;
import com.meicloud.services.ServerService;
import com.meicloud.utils.StringUtil;
import com.meicloud.utils.Utils;
import com.meicloud.utils.beans.ResultBean;
import com.meicloud.worker.SyncSVNFile;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"job"})
public class JobController extends BasicController {

   private static Logger logger = LoggerFactory.getLogger(JobController.class);
   @Autowired
   private JobService jobService;
   @Autowired
   private RoleGroupService roleGroupService;
   @Autowired
   private GroupService groupService;
   @Autowired
   private QueueService queueService;
   @Autowired
   private RunJobService runJobService;
   @Autowired
   private ScheduleParameterService scheduleParameterService;
   @Resource
   private Environment environment;
   @Resource
   private ServerService serverService;
   @Resource
   private SyncSVNFile syncSVNFile;
   private Set keyText = new HashSet();
   private Set fromTo = new HashSet();


   @ApiOperation(
      value = "标记信息树",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/jobType"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object jobType() {
      logger.info("------------->/job/jobType 加载job类型信息");

      try {
         ArrayList e = new ArrayList();
         DicVO dicVO1 = new DicVO();
         dicVO1.setKey("kettle");
         dicVO1.setName("kettle");
         e.add(dicVO1);
         DicVO dicVO2 = new DicVO();
         dicVO2.setKey("informatica");
         dicVO2.setName("informatica");
         e.add(dicVO2);
         DicVO dicVO3 = new DicVO();
         dicVO3.setKey("shell");
         dicVO3.setName("shell");
         e.add(dicVO3);
         DicVO dicVO4 = new DicVO();
         dicVO4.setKey("python");
         dicVO4.setName("python");
         e.add(dicVO4);
         return this.outBound(e);
      } catch (Exception var6) {
         logger.error("------------->/job/jobType 接口异常" + var6.getMessage());
         return this.errorHandler("加载job类型信息异常:" + var6.getMessage());
      }
   }

   @ApiOperation(
      value = "作业列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/list"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object list(
      @ApiParam(
         value = "页数",
         required = true
      ) @RequestParam String pagenum, 
      @ApiParam(
         value = "行数",
         required = true
      ) @RequestParam String rownum, 
      @ApiParam(
         value = "排序字段",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String colName, 
      @ApiParam(
         value = "排序字段值(asc、desc)",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String formValue, 
      @ApiParam(
         value = "查询条件(组名/作业名/责任人)",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String collection, 
      @ApiParam(
         value = "组id",
         required = false
      ) 
      @RequestParam(
         required = false
      ) Integer groupId, 
      @ApiParam(
         value = "集群id",
         required = false
      ) 
      @RequestParam(
         required = false
      ) Integer queueId, HttpServletRequest request) {
      logger.info("------------->/job/list 作业列表");

      try {
         if(collection != null) {
            collection = URLDecoder.decode(collection, "UTF-8");
         }

         colName = (new Job()).getColName(colName);
         if(!StringUtil.isBlank(colName)) {
            String e = colName + " ";
            if(!StringUtil.isBlank(formValue)) {
               e = e + formValue;
            } else {
               e = e + "desc";
            }

            PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum), e);
         } else {
            PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum), "ORDER_NO DESC, RUN_PRIORITY DESC");
         }

         List e1 = this.jobService.getList4EditPage(Utils.isEmptyStr((Object)groupId)?0:groupId.intValue(), collection, Utils.isEmptyStr((Object)queueId)?0:queueId.intValue());
         PageInfo page = new PageInfo(e1);
         Map groupMap = this.roleGroupService.findHandleList((String)null, request);
         Iterator var13 = e1.iterator();

         while(var13.hasNext()) {
            Job job = (Job)var13.next();
            if(((Boolean)request.getSession().getAttribute("ifAdmin")).booleanValue()) {
               job.setHandStatus("1");
            } else if(groupMap.containsKey(Integer.valueOf(job.getGroupId()))) {
               job.setHandStatus("1");
            } else {
               job.setHandStatus("0");
            }
         }

         return this.outBound(this.getDataInfo(new Job(), page.getList(), Long.valueOf(page.getTotal())));
      } catch (Exception var14) {
         logger.error("------------->/job/list 接口异常" + var14.getMessage());
         return this.errorHandler("作业列表查询异常:" + var14.getMessage());
      }
   }

   @RequestMapping(
      value = {"/detail"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "作业详情",
      notes = "返回json"
   )
   @ResponseBody
   public Object detail(
      @ApiParam(
         value = "作业Id",
         required = true
      ) @RequestParam int jobId) {
      logger.info("------------->/job/detail 作业详情");

      try {
         Job e = this.jobService.getJobById(jobId);
         if(e.getJobType().equalsIgnoreCase("shell") || e.getJobType().equalsIgnoreCase("python")) {
            String path = e.getServerFile();
            File file = new File(path);
            if(file.exists()) {
               String context = FileUtil.readAsString(file);
               e.setContext(context.trim());
            }
         }

         return this.outBound(e);
      } catch (Exception var6) {
         logger.error("------------->/job/detail 接口异常" + var6.getMessage());
         return this.errorHandler("作业详情查询异常:" + var6.getMessage());
      }
   }

   @RequestMapping(
      value = {"/delete"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "删除作业",
      notes = "返回json"
   )
   @ResponseBody
   public Object delete(HttpServletRequest request, 
      @ApiParam(
         value = "作业Id",
         required = true
      ) @RequestParam int jobId, 
      @ApiParam(
         value = "删除原因",
         required = true
      ) @RequestParam String remark) {
      logger.info("------------->/job/delete 删除作业");
      Account account = (Account)request.getSession().getAttribute("user");

      try {
         boolean e = this.jobService.deleteById(jobId, remark, account.getAccount());
         return e?this.outBound("删除作业成功"):this.errorHandler("删除作业失败");
      } catch (Exception var6) {
         logger.error("------------->/job/delete 接口异常" + var6.getMessage());
         return this.errorHandler("删除作业异常:" + var6.getMessage());
      }
   }

   @RequestMapping(
      value = {"/deleteByJobIds"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "删除作业",
      notes = "返回json"
   )
   @ResponseBody
   public Object deleteByJobIds(HttpServletRequest request, 
      @ApiParam(
         value = "作业Id",
         required = true
      ) @RequestParam String jobIds, 
      @ApiParam(
         value = "删除原因",
         required = true
      ) @RequestParam String remark) {
      logger.info("------------->/job/deleteByJobIds 删除作业");
      Account account = (Account)request.getSession().getAttribute("user");

      try {
         if(StringUtil.isBlank(jobIds)) {
            return this.errorHandler("删除作业失败,请选择作业!");
         } else {
            String[] var8;
            int var7 = (var8 = jobIds.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
               String e = var8[var6];
               this.jobService.deleteById(Integer.parseInt(e), remark, account.getAccount());
            }

            return this.outBound("删除作业成功");
         }
      } catch (Exception var9) {
         logger.error("------------->/job/deleteByJobIds 接口异常" + var9.getMessage());
         return this.errorHandler("删除作业异常:" + var9.getMessage());
      }
   }

   @RequestMapping(
      value = {"/updateState"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "修改作业状态",
      notes = "返回json"
   )
   @ResponseBody
   public Object updateState(HttpServletRequest request, 
      @ApiParam(
         value = "作业Id",
         required = true
      ) @RequestParam int jobId, 
      @ApiParam(
         value = "修改原因",
         required = true
      ) @RequestParam String remark) {
      logger.info("------------->/job/updateState 删除作业");
      Account account = (Account)request.getSession().getAttribute("user");

      try {
         if(remark != null) {
            remark = new String(remark.getBytes("iso8859-1"), "utf-8");
         }

         this.jobService.updateState(jobId, account.getAccount(), remark);
         return this.outBound("修改作业状态成功");
      } catch (Exception var6) {
         logger.error("------------->/job/updateState 接口异常" + var6.getMessage());
         return this.errorHandler("修改作业状态异常:" + var6.getMessage());
      }
   }

   @RequestMapping(
      value = {"/online"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "作业上/下线",
      notes = "返回json"
   )
   @ResponseBody
   public Object online(HttpServletRequest request, 
      @ApiParam(
         value = "作业Id",
         required = true
      ) @RequestParam int jobId, 
      @ApiParam(
         value = "修改原因",
         required = true
      ) @RequestParam String remark) {
      logger.info("------------->/job/online 作业上/下线");
      Account account = (Account)request.getSession().getAttribute("user");

      try {
         if(remark != null) {
            remark = new String(remark.getBytes("iso8859-1"), "utf-8");
         }

         this.jobService.online(jobId, account.getAccount(), remark);
         return this.outBound("作业上/下线成功");
      } catch (Exception var6) {
         logger.error("------------->/job/online 接口异常" + var6.getMessage());
         return this.errorHandler("作业上/下线异常:" + var6.getMessage());
      }
   }

   @RequestMapping(
      value = {"/downloadETLFile"},
      method = {RequestMethod.GET}
   )
   @ApiOperation(
      value = "ETL文件下载",
      notes = "返回json"
   )
   @ResponseBody
   public Object downloadKettleFile(HttpServletResponse response, int jobId, HttpServletRequest request) {
      logger.info("------------->/job/downloadETLFile ETL文件下载");
      Server server = this.serverService.getServerByIP(this.environment.getProperty("app.server.host"));
      Queue queue2 = this.queueService.findOne(server.getQueueId());
      String syncSvnFile = null;
      if(!Utils.isEmptyStr(server.getSvnLogPath())) {
         syncSvnFile = server.getSvnLogPath() + "/" + queue2.getQueueCode();
      } else {
         syncSvnFile = System.getProperty("user.dir") + "/" + queue2.getQueueCode();
         logger.info("获取项目地址：" + syncSvnFile);
      }

      FileInputStream fileInputStream = null;
      ServletOutputStream outputStream = null;

      ResultBean var16;
      try {
         Job e1 = this.jobService.getJobById(jobId);
         String filePath = e1.getSvnFile();
         String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
         response.addHeader("content-disposition", "attachment; filename=" + fileName);
         response.setContentType("application/octet-stream");
         outputStream = response.getOutputStream();
         File svnFile = new File(syncSvnFile + filePath);
         if(svnFile.exists()) {
            fileInputStream = new FileInputStream(svnFile);
            outputStream = response.getOutputStream();
            boolean len = false;
            byte[] buff = new byte[1024];

            int len1;
            while((len1 = fileInputStream.read(buff)) > 0) {
               outputStream.write(buff, 0, len1);
            }

            var16 = this.outBound("ETL文件下载成功");
            return var16;
         }

         var16 = this.outBound("ETL文件路径错误！");
      } catch (Exception var30) {
         logger.error("------------->/job/downloadETLFile 接口异常" + var30.getMessage());
         var16 = this.errorHandler("ETL文件下载异常:" + var30.getMessage());
         return var16;
      } finally {
         if(fileInputStream != null) {
            try {
               fileInputStream.close();
            } catch (Exception var29) {
               logger.error("------------->/job/downloadETLFile 接口异常" + var29.getMessage());
            }
         }

         if(outputStream != null) {
            try {
               outputStream.close();
            } catch (IOException var28) {
               logger.error("------------->/job/downloadETLFile 接口异常" + var28.getMessage());
            }
         }

      }

      return var16;
   }

   @RequestMapping(
      value = {"/add"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "作业新增",
      notes = "返回json"
   )
   @ResponseBody
   public Object add(HttpServletRequest request, @RequestBody Job job) {
      logger.info("------------->/job/add 作业新增作业");
      Account account = (Account)request.getSession().getAttribute("user");

      try {
         if(job.getJobName().length() > Integer.parseInt(LengthLimit.Job_jobName.toString())) {
            return this.errorHandler("作业名过长！");
         } else if(job.getSvnFile().length() > Integer.parseInt(LengthLimit.Job_svnFile.toString())) {
            return this.errorHandler("ETL文件路径过长！");
         } else {
            String e = job.getAppendParams();
            if(e != null && !"".equals(e) && e.length() > Integer.parseInt(LengthLimit.Job_appendParams.toString())) {
               return this.errorHandler("追加参数过长！");
            } else {
               boolean exists = this.jobService.checkIfExists4New(job) > 0;
               if(exists) {
                  return this.errorHandler("作业名:（" + job.getJobName() + " ）已经存在！");
               } else {
                  job.setcUser(account.getAccount());
                  job.setuUser(account.getAccount());
                  this.jobService.add(job);
                  return this.outBound("作业新增成功");
               }
            }
         }
      } catch (Exception var6) {
         logger.error("------------->/job/add 接口异常" + var6.getMessage());
         return this.errorHandler("作业新增异常:" + var6.getMessage());
      }
   }

   @RequestMapping(
      value = {"/update"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "作业修改",
      notes = "返回json"
   )
   @ResponseBody
   public Object update(HttpServletRequest request, @RequestBody Job job) {
      logger.info("------------->/job/update 作业修改");
      Account account = (Account)request.getSession().getAttribute("user");

      try {
         if(job.getJobName().length() > Integer.parseInt(LengthLimit.Job_jobName.toString())) {
            return this.errorHandler("作业名过长！");
         } else if(job.getSvnFile().length() > Integer.parseInt(LengthLimit.Job_svnFile.toString())) {
            return this.errorHandler("ETL文件路径过长！");
         } else {
            String e = job.getAppendParams();
            if(e != null && !"".equals(e) && e.length() > Integer.parseInt(LengthLimit.Job_appendParams.toString())) {
               return this.errorHandler("追加参数过长！");
            } else {
               boolean exists = this.jobService.checkIfExists4Update(job) > 0;
               if(exists) {
                  return this.errorHandler("Job With jobName:" + job.getJobName() + " already exists.");
               } else {
                  int counts = this.runJobService.checkRunJob(Integer.valueOf(job.getGroupId()), Integer.valueOf(job.getOldGroupId()));
                  if(counts > 0) {
                     return this.errorHandler("不允许修改job组(说明：当前job组和待分配的job组有存在状态为运行中(等待中,运行中)的job,需要在这两组job组的job都运行完成后，才能修改job组！");
                  } else {
                     job.setJobName(job.getJobName().trim());
                     job.setuUser(account.getAccount());
                     this.jobService.update(job);
                     return this.outBound("作业修改成功");
                  }
               }
            }
         }
      } catch (Exception var7) {
         logger.error("------------->/job/update 接口异常" + var7.getMessage());
         return this.errorHandler("作业修改异常:" + var7.getMessage());
      }
   }

   @RequestMapping(
      value = {"/batchAddSave"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "作业批量新增",
      notes = "返回json"
   )
   @ResponseBody
   public Object batchAddSave(HttpServletRequest request, @RequestBody Job job) {
      logger.info("------------->/job/batchAddSave 作业批量新增");
      Account account = (Account)request.getSession().getAttribute("user");
      job.setJobType("kettle");

      try {
         String[] e = job.getSvnFile().split(",");
         if(!"".equals(e[0]) && e[0] != null) {
            for(int i = 0; i < e.length; ++i) {
               Job newJob = new Job();
               newJob.setRunPriority(100);
               newJob.setGroupId(job.getGroupId());
               newJob.setJobName(e[i].substring(e[i].lastIndexOf("/") + 1, e[i].lastIndexOf(".")));
               newJob.setJobName(newJob.getJobName().trim());
               newJob.setOrderNo(job.getOrderNo());
               newJob.setSvnFile(e[i]);
               newJob.setJobType(job.getJobType());
               if(!Utils.isEmptyStr(newJob.getJobName())) {
                  newJob.setLogFile(newJob.getJobName() + "_%sysdate%.log");
               }

               if(!StringUtil.isBlank(job.getJobType()) && job.getJobType().equalsIgnoreCase("informatica")) {
                  newJob.setServerFile(job.getFolderName() + "/" + e[i]);
                  newJob.setFolderName(job.getFolderName());
               } else if(!Utils.isEmptyStr(e[i])) {
                  newJob.setServerFile(e[i].substring(e[i].lastIndexOf("/") + 1));
               }

               newJob.setEnable(job.isEnable());
               newJob.setGroupName(job.getGroupName());
               newJob.setChargorId(job.getChargorId());
               newJob.setChargorId2(job.getChargorId2());
               newJob.setChargorId3(job.getChargorId3());
               newJob.setEnable(true);
               newJob.setRetryId(job.getRetryId());
               newJob.setDebugLevel(job.getDebugLevel());
               newJob.setOnline(false);
               newJob.setcUser(account.getAccount());
               newJob.setuUser(account.getAccount());
               boolean exists = this.jobService.checkIfExists4New(newJob) > 0;
               if(exists) {
                  return this.errorHandler("Job With jobName:" + newJob.getJobName() + " already exists.");
               }

               this.jobService.add(newJob);
            }
         }

         return this.outBound("作业批量新增成功");
      } catch (Exception var8) {
         logger.error("------------->/job/batchAddSave 接口异常" + var8.getMessage());
         return this.errorHandler("作业批量新增异常:" + var8.getMessage());
      }
   }

   @RequestMapping(
      value = {"/deleteByGroupId"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "作业批量删除",
      notes = "返回json"
   )
   @ResponseBody
   public Object deleteByGroupId(HttpServletRequest request, 
      @ApiParam(
         value = "组ID",
         required = true
      ) @RequestParam int groupId, 
      @ApiParam(
         value = "删除原因",
         required = true
      ) @RequestParam String remark, 
      @ApiParam(
         value = "集群ID",
         required = false
      ) @RequestParam int queueId) {
      logger.info("------------->/job/deleteByGroupId 作业批量删除");
      Account account = (Account)request.getSession().getAttribute("user");

      try {
         if(remark != null) {
            remark = new String(remark.getBytes("iso8859-1"), "utf-8");
         }

         List e = this.jobService.getList4EditPage(groupId, (String)null, queueId);
         Iterator var8 = e.iterator();

         while(var8.hasNext()) {
            Job job = (Job)var8.next();
            this.jobService.deleteById(job.getJobId(), remark, account.getAccount());
         }

         return this.outBound("作业批量删除成功");
      } catch (Exception var9) {
         logger.error("------------->/job/deleteByGroupId 接口异常" + var9.getMessage());
         return this.errorHandler("作业批量删除异常:" + var9.getMessage());
      }
   }

   @RequestMapping(
      value = {"/relationGraph"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "生成作业依赖图",
      notes = "返回json"
   )
   @ResponseBody
   public Object relationGraph(int groupId) {
      logger.info("------------->/job/relationGraph 生成作业依赖图");

      try {
         this.keyText.clear();
         this.fromTo.clear();
         byte e = 0;
         List jobList = this.jobService.getList4RunPage(groupId, (String)null, (String)null, false, e);
         SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
         StringBuffer sb = new StringBuffer();
         List result = this.groupByOrder(jobList);

         for(int i = 0; i < result.size(); ++i) {
            Iterator var9 = ((List)result.get(i)).iterator();

            Job job;
            while(var9.hasNext()) {
               job = (Job)var9.next();
               sb.append("\"key\":" + job.getJobId() + ",");
               sb.append("\"text\":\"" + job.getJobName());
               if(job.getStartDate() != null) {
                  sb.append("\\n 开始时间：" + sdf.format(job.getStartDate()));
               }

               if(job.getEndDate() != null) {
                  sb.append("\\n 结束时间：" + sdf.format(job.getEndDate()));
               }

               if(job.getExecState() != null) {
                  Integer state = Integer.valueOf(Integer.parseInt(job.getExecState()));
                  if(-1 == state.intValue()) {
                     sb.append("\\n状态：禁用 \", \"category\":\"forbidden\"");
                  } else if(3 == state.intValue()) {
                     sb.append("\\n状态：失败 \", \"category\":\"error\"");
                  } else if(2 == state.intValue()) {
                     sb.append("\\n状态：运行 \", \"category\":\"run\"");
                  } else if(state.intValue() == 0) {
                     sb.append("\\n状态：等待 \", \"category\":\"wait\"");
                  } else if(5 == state.intValue()) {
                     sb.append("\\n状态：成功 \", \"category\":\"success\"");
                  } else {
                     sb.append("\\n状态：禁用 \", \"category\":\"forbidden\"");
                  }
               }

               this.keyText.add("{" + sb + "}");
               sb.setLength(0);
            }

            if(i < result.size() - 1) {
               if(((List)result.get(i + 1)).size() > 1 && ((List)result.get(i)).size() > 1) {
                  this.keyText.add("{\"key\":\"next_" + i + "\",\"text\":\"\", \"category\":\"cen\"}");
                  var9 = ((List)result.get(i)).iterator();

                  while(var9.hasNext()) {
                     job = (Job)var9.next();
                     this.fromTo.add("{\"from\":" + job.getJobId() + ", \"to\":\"next_" + i + "\"}");
                  }

                  var9 = ((List)result.get(i + 1)).iterator();

                  while(var9.hasNext()) {
                     job = (Job)var9.next();
                     this.fromTo.add("{\"from\":\"next_" + i + "\", \"to\":" + job.getJobId() + "}");
                  }
               }

               if(((List)result.get(i + 1)).size() == 1 && ((List)result.get(i)).size() > 1) {
                  var9 = ((List)result.get(i)).iterator();

                  while(var9.hasNext()) {
                     job = (Job)var9.next();
                     this.fromTo.add("{\"from\":" + job.getJobId() + ", \"to\":" + ((Job)((List)result.get(i + 1)).get(0)).getJobId() + "}");
                  }
               }

               if(((List)result.get(i + 1)).size() > 1 && ((List)result.get(i)).size() == 1) {
                  var9 = ((List)result.get(i + 1)).iterator();

                  while(var9.hasNext()) {
                     job = (Job)var9.next();
                     this.fromTo.add("{\"from\":" + ((Job)((List)result.get(i)).get(0)).getJobId() + ", \"to\":" + job.getJobId() + "}");
                  }
               }

               if(((List)result.get(i + 1)).size() == 1 && ((List)result.get(i)).size() == 1) {
                  this.fromTo.add("{\"from\":" + ((Job)((List)result.get(i)).get(0)).getJobId() + ", \"to\":" + ((Job)((List)result.get(i + 1)).get(0)).getJobId() + "}");
               }
            }
         }

         return this.outBound(this.keyText.toString() + "#" + this.fromTo.toString());
      } catch (Exception var11) {
         logger.error("------------->/job/relationGraph 接口异常" + var11.getMessage());
         return this.errorHandler("生成作业依赖图异常:" + var11.getMessage());
      }
   }

   @RequestMapping(
      value = {"/cancelOrder"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "取消作业排序",
      notes = "返回json"
   )
   @ResponseBody
   public Object cancelOrder(int groupId) {
      logger.info("------------->/job/cancelOrder 取消作业排序");

      try {
         this.jobService.cancelOrder(groupId);
         return this.outBound("取消作业排序成功");
      } catch (Exception var3) {
         logger.error("------------->/job/cancelOrder 接口异常" + var3.getMessage());
         return this.errorHandler("取消作业排序:" + var3.getMessage());
      }
   }

   @RequestMapping(
      value = {"/list4RunPage"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "调度监控作业列表",
      notes = "返回json"
   )
   @ResponseBody
   public Object list4RunPage(@ApiParam("页数") @RequestParam String pagenum, @ApiParam("每页的行数") @RequestParam String rownum, 
      @ApiParam(
         value = "排序字段",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String colName, 
      @ApiParam(
         value = "排序字段值(asc、desc)",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String formValue, 
      @ApiParam(
         value = "执行状态（正在运行 2，今日超时 6，今日失败 3，今日成功 5，运行等待 0）",
         required = false
      ) 
      @RequestParam(
         required = false
      ) Integer status, 
      @ApiParam(
         value = "状态码（成功5，运行2，等待0，禁止-1，失败3）",
         required = false
      ) 
      @RequestParam(
         required = false
      ) Integer state, 
      @ApiParam(
         value = "组ID",
         required = false
      ) 
      @RequestParam(
         required = false
      ) Integer groupId, 
      @ApiParam(
         value = "集群ID",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String queueId, 
      @ApiParam(
         value = "作业名称查询",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String queryKey, HttpServletRequest request) {
      logger.info("------------->/job/list4RunPage 调度监控作业列表");

      try {
         if(queryKey != null) {
            queryKey = URLDecoder.decode(queryKey, "UTF-8");
         }

         Group e;
         if(!Utils.isEmptyStr((Object)groupId)) {
            e = this.groupService.getById(groupId.intValue());
            if(e == null) {
               return this.errorHandler("作业所属的组不存在!");
            }

            if(Utils.isEmptyStr(queueId)) {
               queueId = e.getQueueId();
            }
         }

         e = null;
         colName = (new Job()).getColName(colName);
         if(!StringUtil.isBlank(colName)) {
            String page = colName + " ";
            if(!StringUtil.isBlank(formValue)) {
               page = page + formValue;
            } else {
               page = page + "desc";
            }

            PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum), page);
         } else {
            PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum), "ORDER_NO DESC, RUN_PRIORITY DESC");
         }

         List e1;
         if(!Utils.isEmptyStr((Object)status) && !StringUtil.isBlank(queueId)) {
            e1 = this.jobService.getList4RunPageHeader(status.intValue(), Utils.isEmptyStr(queueId)?0:Integer.parseInt(queueId));
         } else {
            e1 = this.jobService.getList4RunPage(Utils.isEmptyStr((Object)groupId)?0:groupId.intValue(), queryKey, !Utils.isEmptyStr((Object)status)?String.valueOf(status):null, true, Utils.isEmptyStr(queueId)?0:Integer.parseInt(queueId));
         }

         PageInfo page1 = new PageInfo(e1);
         Map groupMap = this.roleGroupService.findHandleList((String)null, request);
         Iterator var15 = e1.iterator();

         while(var15.hasNext()) {
            Job job = (Job)var15.next();
            if(((Boolean)request.getSession().getAttribute("ifAdmin")).booleanValue()) {
               job.setHandStatus("1");
            } else if(groupMap.containsKey(Integer.valueOf(job.getGroupId()))) {
               job.setHandStatus("1");
            } else {
               job.setHandStatus("0");
            }
         }

         return this.outBound(this.getDataInfo(new Job(), page1.getList(), Long.valueOf(page1.getTotal())));
      } catch (Exception var16) {
         logger.error("------------->/job/list4RunPag 接口异常" + var16.getMessage());
         return this.errorHandler("调度监控作业列表:" + var16.getMessage());
      }
   }

   @RequestMapping(
      value = {"/queryParameter"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "ETL作业参数列表接口",
      notes = "返回json"
   )
   @ResponseBody
   public Object queryParameter(
      @ApiParam(
         value = "页数",
         required = true
      ) @RequestParam String pagenum, 
      @ApiParam(
         value = "行数",
         required = true
      ) @RequestParam String rownum, int jobId) {
      logger.info("------------->/job/queryParameter ETL作业参数列表接口");

      try {
         PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum));
         Object e = new ArrayList();
         String jobName = null;
         Job job = this.jobService.getJobById(jobId);
         if(job != null) {
            jobName = job.getJobName();
            e = this.scheduleParameterService.queryParameter(jobName);
         }

         PageInfo page = new PageInfo((List)e);
         return this.outBound(this.getDataInfo(new ScheduleParameter(), page.getList(), Long.valueOf(page.getTotal())));
      } catch (Exception var8) {
         logger.error("------------->/job/queryParameter 接口异常" + var8.getMessage());
         return this.errorHandler("ETL作业参数列表接口:" + var8.getMessage());
      }
   }

   @RequestMapping(
      value = {"/insertParameter"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "ETL作业参数新增接口",
      notes = "返回json"
   )
   @ResponseBody
   public String insertParameter(HttpServletRequest request, @RequestBody ScheduleParameter scheduleParameter) {
      String msg = "success";

      try {
         if(StringUtil.isBlank(scheduleParameter.getWorkflowName()) || StringUtil.isBlank(scheduleParameter.getParameterName()) || StringUtil.isBlank(scheduleParameter.getParameterValue())) {
            msg = "作业名称、参数名称或参数值为空，保存不成功";
            return msg;
         }

         Account e = (Account)request.getSession().getAttribute("user");
         String formatMask = scheduleParameter.getFormatMask();
         if("YYYY-MM-DD".equalsIgnoreCase(scheduleParameter.getFormatMask())) {
            scheduleParameter.setFrequency("DD");
            formatMask = "yyyy-MM-dd";
         } else if("YYYYMMDD".equalsIgnoreCase(scheduleParameter.getFormatMask())) {
            scheduleParameter.setFrequency("DD");
            formatMask = "yyyy-MM-dd";
         } else if("YYYY-MM".equalsIgnoreCase(scheduleParameter.getFormatMask())) {
            scheduleParameter.setFrequency("MM");
            formatMask = "yyyy-MM";
         } else if("YYYY".equalsIgnoreCase(scheduleParameter.getFormatMask())) {
            scheduleParameter.setFrequency("YYYY");
            formatMask = "yyyy";
         } else if("String".equalsIgnoreCase(scheduleParameter.getFormatMask())) {
            scheduleParameter.setFrequency("String");
         }

         Job job = this.jobService.getJobByJobName(scheduleParameter.getWorkflowName());
         Group dateFormat;
         if(job != null) {
            dateFormat = this.groupService.getById(job.getGroupId());
            scheduleParameter.setSubjectName(dateFormat.getSubject());
         }

         dateFormat = null;
         SimpleDateFormat dateFormat1;
         if(!StringUtil.isBlank(scheduleParameter.getParameterValue()) && !"String".equalsIgnoreCase(scheduleParameter.getFormatMask())) {
            dateFormat1 = new SimpleDateFormat(formatMask);
            Date date = dateFormat1.parse(scheduleParameter.getParameterValue());
            scheduleParameter.setParameterValue(dateFormat1.format(date));
         }

         dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         scheduleParameter.setStartDateActive(new Date());
         scheduleParameter.setEndDateActive(dateFormat1.parse("2099-12-31 23:59:59"));
         scheduleParameter.setCreatedBy(e.getAccount());
         scheduleParameter.setCreationDate(new Date());
         scheduleParameter.setLastUpdatedBy(e.getAccount());
         scheduleParameter.setLastUpdateDate(new Date());
         scheduleParameter.setMappingName(scheduleParameter.getWorkflowName());
         scheduleParameter.setSessionName(scheduleParameter.getWorkflowName());
         scheduleParameter.setParaOffset(Integer.valueOf(0));
         scheduleParameter.setEnableFlag("Y");
         this.scheduleParameterService.insertParameter(scheduleParameter);
         logger.info(e.getAccount() + "保存【" + scheduleParameter.getWorkflowName() + "】参数表，值为 " + scheduleParameter.getParameterValue());
      } catch (Exception var9) {
         msg = var9.getMessage();
         logger.error(msg);
      }

      return msg;
   }

   @RequestMapping(
      value = {"/updateParameter"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "ETL作业参数修改接口",
      notes = "返回json"
   )
   @ResponseBody
   public Object updateParameter(HttpServletRequest request, @RequestBody ScheduleParameter scheduleParameter) {
      logger.info("------------->/job/updateParameter ETL作业参数修改接口");

      try {
         Account e = (Account)request.getSession().getAttribute("user");
         if(!StringUtil.isBlank(scheduleParameter.getWorkflowName()) && !StringUtil.isBlank(scheduleParameter.getParameterName()) && !StringUtil.isBlank(scheduleParameter.getParameterValue())) {
            String formatMask = scheduleParameter.getFormatMask();
            if("YYYY-MM-DD".equals(scheduleParameter.getFormatMask())) {
               scheduleParameter.setFrequency("DD");
               formatMask = "yyyy-MM-dd";
            } else if("YYYYMMDD".equals(scheduleParameter.getFormatMask())) {
               scheduleParameter.setFrequency("DD");
               formatMask = "yyyy-MM-dd";
            } else if("YYYY-MM".equals(scheduleParameter.getFormatMask())) {
               scheduleParameter.setFrequency("MM");
               formatMask = "yyyy-MM";
            } else if("YYYY".equals(scheduleParameter.getFormatMask())) {
               scheduleParameter.setFrequency("YYYY");
               formatMask = "yyyy";
            } else if("String".equalsIgnoreCase(scheduleParameter.getFormatMask())) {
               scheduleParameter.setFrequency("String");
            }

            Job job = this.jobService.getJobByJobName(scheduleParameter.getWorkflowName());
            Group dateFormat;
            if(job != null) {
               dateFormat = this.groupService.getById(job.getGroupId());
               scheduleParameter.setSubjectName(dateFormat.getSubject());
            }

            dateFormat = null;
            SimpleDateFormat dateFormat1;
            if(!StringUtil.isBlank(scheduleParameter.getParameterValue()) && !"String".equalsIgnoreCase(scheduleParameter.getFormatMask())) {
               dateFormat1 = new SimpleDateFormat(formatMask);
               Date date = dateFormat1.parse(scheduleParameter.getParameterValue());
               scheduleParameter.setParameterValue(dateFormat1.format(date));
            }

            dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            scheduleParameter.setStartDateActive(new Date());
            scheduleParameter.setEndDateActive(dateFormat1.parse("2099-12-31 23:59:59"));
            scheduleParameter.setLastUpdatedBy(e.getAccount());
            scheduleParameter.setLastUpdateDate(new Date());
            scheduleParameter.setMappingName(scheduleParameter.getWorkflowName());
            scheduleParameter.setSessionName(scheduleParameter.getWorkflowName());
            scheduleParameter.setParaOffset(Integer.valueOf(0));
            scheduleParameter.setEnableFlag("Y");
            this.scheduleParameterService.updateParameter(scheduleParameter);
            logger.info(e.getAccount() + "修改【" + scheduleParameter.getWorkflowName() + "】参数表，值为 " + scheduleParameter.getParameterValue());
            return this.outBound("修改成功!");
         } else {
            return this.errorHandler("作业名称、参数名称或参数值为空，保存不成功");
         }
      } catch (Exception var8) {
         logger.error("------------->/job/updateParameter 接口异常" + var8.getMessage());
         return this.errorHandler("ETL作业参数修改异常:" + var8.getMessage());
      }
   }

   @RequestMapping(
      value = {"/insertAndupdateParameter"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "ETL作业参数修改接口",
      notes = "返回json"
   )
   @ResponseBody
   public Object insertAndupdateParameter(HttpServletRequest request, @RequestBody List scheduleParameters) {
      try {
         Iterator var4 = scheduleParameters.iterator();

         while(var4.hasNext()) {
            ScheduleParameter e = (ScheduleParameter)var4.next();
            Account account = (Account)request.getSession().getAttribute("user");
            if(StringUtil.isBlank(e.getWorkflowName()) || StringUtil.isBlank(e.getParameterName()) || StringUtil.isBlank(e.getParameterValue())) {
               return this.errorHandler("作业名称、参数名称或参数值为空，保存不成功");
            }

            String formatMask = e.getFormatMask();
            if("YYYY-MM-DD".equals(e.getFormatMask())) {
               e.setFrequency("DD");
               formatMask = "yyyy-MM-dd";
            } else if("YYYYMMDD".equals(e.getFormatMask())) {
               e.setFrequency("DD");
               formatMask = "yyyy-MM-dd";
            } else if("YYYY-MM".equals(e.getFormatMask())) {
               e.setFrequency("MM");
               formatMask = "yyyy-MM";
            } else if("YYYY".equals(e.getFormatMask())) {
               e.setFrequency("YYYY");
               formatMask = "yyyy";
            } else if("String".equalsIgnoreCase(e.getFormatMask())) {
               e.setFrequency("String");
            }

            Job job = this.jobService.getJobByJobName(e.getWorkflowName());
            Group dateFormat;
            if(job != null) {
               dateFormat = this.groupService.getById(job.getGroupId());
               e.setSubjectName(dateFormat.getSubject());
            }

            dateFormat = null;
            SimpleDateFormat dateFormat1;
            if(!StringUtil.isBlank(e.getParameterValue()) && !"String".equalsIgnoreCase(e.getFormatMask())) {
               dateFormat1 = new SimpleDateFormat(formatMask);
               Date date = dateFormat1.parse(e.getParameterValue());
               e.setParameterValue(dateFormat1.format(date));
            }

            dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            e.setStartDateActive(new Date());
            e.setEndDateActive(dateFormat1.parse("2099-12-31 23:59:59"));
            e.setLastUpdatedBy(account.getAccount());
            e.setLastUpdateDate(new Date());
            e.setMappingName(e.getWorkflowName());
            e.setSessionName(e.getWorkflowName());
            e.setParaOffset(Integer.valueOf(0));
            e.setEnableFlag("Y");
            if(e.getScheduleParaId() != null && e.getScheduleParaId().longValue() != 0L) {
               this.scheduleParameterService.updateParameter(e);
            } else {
               e.setCreatedBy(account.getAccount());
               e.setCreationDate(new Date());
               this.scheduleParameterService.insertParameter(e);
            }
         }

         return this.outBound("修改成功!");
      } catch (Exception var10) {
         logger.error("------------->/job/insertAndupdateParameter 接口异常" + var10.getMessage());
         return this.errorHandler("ETL作业参数修改异常:" + var10.getMessage());
      }
   }

   @RequestMapping(
      value = {"/deleteParameter"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "ETL作业参数修改接口",
      notes = "返回json"
   )
   @ResponseBody
   public Object deleteParameter(
      @ApiParam(
         value = "参数Id",
         required = true
      ) @RequestParam int scheduleParaId) {
      try {
         if(scheduleParaId != 0) {
            this.scheduleParameterService.deleteParameter(scheduleParaId);
         }

         return this.outBound("删除成功!");
      } catch (Exception var3) {
         logger.error("------------->/job/deleteParameter 接口异常" + var3.getMessage());
         return this.errorHandler("ETL作业参数删除异常:" + var3.getMessage());
      }
   }

   public List groupByOrder(List jobList) {
      ArrayList result = new ArrayList();
      int next = -1;
      ArrayList list = null;
      Iterator var6 = jobList.iterator();

      while(var6.hasNext()) {
         Job job = (Job)var6.next();
         if(next != job.getOrderNo()) {
            if(list != null) {
               result.add(list);
            }

            list = new ArrayList();
            next = job.getOrderNo();
         }

         if(next == job.getOrderNo()) {
            if(list == null) {
               list = new ArrayList();
            }

            list.add(job);
         }
      }

      if(list != null) {
         result.add(list);
      }

      return result;
   }
}
