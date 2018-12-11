package com.meicloud.controller;

import com.meicloud.controller.BasicController;
import com.meicloud.model.Account;
import com.meicloud.model.Group;
import com.meicloud.model.Job;
import com.meicloud.services.GroupService;
import com.meicloud.services.JobService;
import com.meicloud.services.RunJobService;
import com.meicloud.services.SendJobService;
import com.meicloud.utils.OthersProperties;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"runJob"})
public class RunJobController extends BasicController {

   private static Logger logger = LoggerFactory.getLogger(RunJobController.class);
   @Autowired
   private RunJobService runJobService;
   @Autowired
   private GroupService groupService;
   @Autowired
   private JobService jobService;
   @Autowired
   private OthersProperties othersProperties;
   @Autowired
   private SendJobService sendJobService;


   @RequestMapping(
      value = {"/killGroup"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "组~杀进程",
      notes = "返回json"
   )
   @ResponseBody
   public Object killGroup(HttpServletRequest request, int groupId) {
      logger.info("------------->/runJob/killGroup 主题下拉列表");
      Account account = (Account)request.getSession().getAttribute("user");

      try {
         boolean e = this.runJobService.killGroup(groupId, account.getAccount());
         return e?this.outBound("杀组下作业进程已被发送至执行队列"):this.errorHandler("杀组下作业进程失败");
      } catch (Exception var5) {
         logger.error("------------->/runJob/killGroup 接口异常" + var5.getMessage());
         return this.errorHandler("组~杀进程异常:" + var5.getMessage());
      }
   }

   @RequestMapping(
      value = {"/reRunGroup"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "组~重释",
      notes = "返回json"
   )
   @ResponseBody
   public Object reRunGroup(int groupId, HttpServletRequest request) {
      logger.info("------------->/runJob/reRunGroup 组~重释");

      try {
         int e = Integer.parseInt(this.othersProperties.getGroupRestartTime());
         boolean upSeconds = false;

         int upSeconds1;
         try {
            upSeconds1 = this.runJobService.getRunGroupUpSeconds(groupId);
         } catch (Exception var9) {
            logger.error(var9.getMessage());
            upSeconds1 = 0;
         }

         String groupName = "";

         try {
            Group account = this.groupService.getById(groupId);
            if(account == null) {
               return this.errorHandler("组[" + groupId + "]已经不存在!");
            }

            if(!account.isEnable()) {
               return this.errorHandler("组[" + account.getGroupName() + "]已经被禁用，请先启用然后再触发重跑!");
            }

            groupName = account.getGroupName();
         } catch (Exception var8) {
            logger.error(var8.getMessage());
         }

         if(upSeconds1 > 0 && upSeconds1 < e) {
            return this.errorHandler("组[" + groupId + "]最后更新时间离现在不足[" + e + "]秒，请请在[" + (e - upSeconds1) + "]秒后再试.");
         } else {
            Account account1 = (Account)request.getSession().getAttribute("user");
            boolean re = this.runJobService.reRunGroup(groupId, groupName, account1);
            return re?this.outBound("组重跑请求已被发送至执行队列!"):this.errorHandler("触发组重跑失败");
         }
      } catch (Exception var10) {
         logger.error("------------->/runJob/reRunGroup 接口异常" + var10.getMessage());
         return this.errorHandler("组~重释异常:" + var10.getMessage());
      }
   }

   @RequestMapping(
      value = {"/killJob"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "作业~杀进程",
      notes = "返回json"
   )
   @ResponseBody
   public Object killJob(HttpServletRequest request, int jobId) {
      logger.info("------------->/runJob/killJob 作业~杀进程");

      try {
         Account e = (Account)request.getSession().getAttribute("user");
         boolean killed = this.runJobService.killJob(jobId, e.getAccount());
         return killed?this.outBound("杀作业进程请求已发送至执行队列."):this.errorHandler("杀作业进程失败");
      } catch (Exception var5) {
         logger.error("------------->/runJob/killJob 接口异常" + var5.getMessage());
         return this.errorHandler("作业~杀进程:" + var5.getMessage());
      }
   }

   @RequestMapping(
      value = {"/checkRefersByRunGroup"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "检测作业组依赖",
      notes = "返回json"
   )
   @ResponseBody
   public Object checkRefersByRunGroup(int groupId, HttpServletRequest request) {
      try {
         logger.info("------------->/runJob/checkRefersByRunGroup 检测作业组依赖");
         int e = Integer.parseInt(this.othersProperties.getGroupRestartTime());
         int upSeconds = this.runJobService.getRunGroupUpSeconds(groupId);
         if(upSeconds > 0 && upSeconds < e) {
            return this.errorHandler("组[" + groupId + "]最后更新时间离现在不足[" + e + "]秒，请请在[" + (e - upSeconds) + "]秒后再试.");
         } else {
            boolean finish = this.sendJobService.completeGroup(groupId) && this.sendJobService.completeJob(groupId);
            if(finish) {
               String groupName = "";
               Group groupDB = this.groupService.getById(groupId);
               if(groupDB == null) {
                  return this.errorHandler("组[" + groupId + "]已经不存在!");
               } else if(!groupDB.isEnable()) {
                  return this.errorHandler("组[" + groupDB.getGroupName() + "]已经被禁用，请先启用然后再触发重跑!");
               } else {
                  groupName = groupDB.getGroupName();
                  Account account = (Account)request.getSession().getAttribute("user");
                  boolean re = this.runJobService.reRunGroup(groupId, groupName, account);
                  return re?this.outBound("组重跑请求已被发送至执行队列!"):this.errorHandler("触发组重跑失败");
               }
            } else {
               return this.outBound(Boolean.valueOf(false));
            }
         }
      } catch (Exception var10) {
         logger.error("------------->/runJob/checkRefersByRunGroup 接口异常" + var10.getMessage());
         return this.errorHandler("检测作业组依赖失败:" + var10.getMessage());
      }
   }

   @RequestMapping(
      value = {"/checkRefersByRunJob"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "检测作业依赖",
      notes = "返回json"
   )
   @ResponseBody
   public Object checkRefersByRunJob(int jobId, HttpServletRequest request) {
      try {
         logger.info("------------->/runJob/checkRefersByRunJob 检测作业依赖");
         int e = Integer.parseInt(this.othersProperties.getJobRestartTime());
         int upSeconds = this.runJobService.getRunJobUpSeconds(jobId);
         if(upSeconds > 0 && upSeconds < e) {
            return this.errorHandler("作业[" + jobId + "]最后更新时间离现在不足[" + e + "]秒，请在[" + (e - upSeconds) + "]秒后再试.");
         } else {
            Job job = this.jobService.getJobById(jobId);
            if(job == null) {
               return this.errorHandler("作业已不存在！");
            } else if(job.getServerFile() != null && job.getLogFile() != null) {
               if(!job.isOnline()) {
                  return this.errorHandler("作业已下线,不能跑数.");
               } else {
                  boolean finish = this.sendJobService.completeGroup(job.getGroupId()) && this.sendJobService.completeJob(job.getGroupId());
                  if(finish) {
                     Account account = (Account)request.getSession().getAttribute("user");
                     this.runJobService.killJob(jobId, account.getAccount());
                     boolean re = this.runJobService.reRunJob(jobId, job.getGroupName(), job.getJobName(), account);
                     return re?this.outBound("重跑作业请求已发送至执行队列!"):this.errorHandler("触发作业重跑失败");
                  } else {
                     return this.outBound(Boolean.valueOf(false));
                  }
               }
            } else {
               return this.errorHandler("作业信息不满足跑数条件.");
            }
         }
      } catch (Exception var9) {
         logger.error("------------->/runJob/checkRefersByRunJob 接口异常" + var9.getMessage());
         return this.errorHandler("检测作业依赖失败:" + var9.getMessage());
      }
   }

   @RequestMapping(
      value = {"/reRunJob"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "作业~重释",
      notes = "返回json"
   )
   @ResponseBody
   public Object reRunJob(int jobId, HttpServletRequest request) {
      logger.info("------------->/runJob/reRunJob 作业~重释");
      Account account = (Account)request.getSession().getAttribute("user");

      try {
         int e = Integer.parseInt(this.othersProperties.getJobRestartTime());
         int upSeconds = this.runJobService.getRunJobUpSeconds(jobId);
         if(upSeconds > 0 && upSeconds < e) {
            return this.errorHandler("作业[" + jobId + "]最后更新时间离现在不足[" + e + "]秒，请在[" + (e - upSeconds) + "]秒后再试.");
         } else {
            Job job = this.jobService.getJobById(jobId);
            if(job == null) {
               return this.errorHandler("作业已不存在！");
            } else if(job.getServerFile() != null && job.getLogFile() != null) {
               if(!job.isOnline()) {
                  return this.errorHandler("作业已下线,不能跑数.");
               } else {
                  this.runJobService.killJob(jobId, account.getAccount());
                  boolean re = this.runJobService.reRunJob(jobId, job.getGroupName(), job.getJobName(), account);
                  return re?this.outBound("重跑作业请求已发送至执行队列!"):this.errorHandler("触发作业重跑失败");
               }
            } else {
               return this.errorHandler("作业信息不满足跑数条件.");
            }
         }
      } catch (Exception var8) {
         logger.error("------------->/runJob/reRunJob 接口异常" + var8.getMessage());
         return this.errorHandler("作业~重试:" + var8.getMessage());
      }
   }
}
