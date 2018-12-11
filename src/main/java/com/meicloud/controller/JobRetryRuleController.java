package com.meicloud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meicloud.controller.BasicController;
import com.meicloud.dto.DicInfo;
import com.meicloud.enums.LengthLimit;
import com.meicloud.model.Job;
import com.meicloud.model.RetryRule;
import com.meicloud.services.JobRetryRuleService;
import com.meicloud.services.JobService;
import com.meicloud.utils.Utils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"retryRule"})
public class JobRetryRuleController extends BasicController {

   private static Logger logger = LoggerFactory.getLogger(JobRetryRuleController.class);
   @Autowired
   private JobRetryRuleService jobRetryRuleService;
   @Autowired
   private JobService jobService;


   @ApiOperation(
      value = "跑数规则列表",
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
      ) @RequestParam String rownum) {
      logger.info("------------->/retryRule/list 跑数规则列表");

      try {
         PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"25":rownum));
         List e = this.jobRetryRuleService.list();
         PageInfo page = new PageInfo(e);
         return this.outBound(this.getDataInfo(new RetryRule(), page.getList(), Long.valueOf(page.getTotal())));
      } catch (Exception var5) {
         logger.error("------------->/retryRule/list 接口异常" + var5.getMessage());
         return this.errorHandler("跑数规则列表查询异常:" + var5.getMessage());
      }
   }

   @ApiOperation(
      value = "跑数规则下拉列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/optionList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object optionList() {
      logger.info("------------->/retryRule/optionList 跑数规则下拉列表");
      ArrayList dicInfoList = new ArrayList();

      try {
         List e = this.jobRetryRuleService.queryList();
         if(!Utils.isEmpityCollection(e)) {
            Iterator var4 = e.iterator();

            while(var4.hasNext()) {
               RetryRule retryRule = (RetryRule)var4.next();
               DicInfo info = new DicInfo();
               info.setKey(Integer.valueOf(retryRule.getRetryId()));
               info.setName(retryRule.getRetryName());
               dicInfoList.add(info);
            }
         }

         return this.outBound(dicInfoList);
      } catch (Exception var6) {
         logger.error("------------->/retryRule/optionList 接口异常" + var6.getMessage());
         return this.errorHandler("跑数规则下拉列表查询异常:" + var6.getMessage());
      }
   }

   @RequestMapping(
      value = {"/save"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "跑数规则新增/修改",
      notes = "返回json"
   )
   @ResponseBody
   public Object save(@RequestBody @Validated RetryRule retryRule) {
      logger.info("------------->/retryRule/save 跑数规则新增/修改");

      try {
         if(retryRule.getRetryName().length() > Integer.parseInt(LengthLimit.RetryRule_retryName.toString())) {
            return this.errorHandler("跑数规则名称过长！");
         } else {
            String e = retryRule.getRetryDesc();
            if(e != null && !"".equals(e) && e.length() > Integer.parseInt(LengthLimit.RetryRule_retryDesc.toString())) {
               return this.errorHandler("跑数规则描述过长！");
            } else {
               boolean exists;
               if(retryRule.getRetryId() == 0) {
                  exists = this.jobRetryRuleService.checkIfExists4New(retryRule);
                  if(exists) {
                     return this.errorHandler("名称为[" + retryRule.getRetryName() + "]的规则已存在！");
                  } else {
                     this.jobRetryRuleService.add(retryRule);
                     return this.outBound("跑数规则新增成功");
                  }
               } else {
                  exists = this.jobRetryRuleService.checkIfExists4Update(retryRule);
                  if(exists) {
                     return this.errorHandler("名称为[" + retryRule.getRetryName() + "]的规则已存在！");
                  } else {
                     this.jobRetryRuleService.update(retryRule);
                     return this.outBound("跑数规则修改成功");
                  }
               }
            }
         }
      } catch (Exception var4) {
         logger.error("------------->/retryRule/save 接口异常" + var4.getMessage());
         return this.errorHandler("跑数规则保存异常:" + var4.getMessage());
      }
   }

   @RequestMapping(
      value = {"/delete"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "跑数规则删除",
      notes = "返回json"
   )
   @ResponseBody
   public Object delete(int retryId) {
      logger.info("------------->/retryRule/delete 跑数规则删除");

      try {
         if(retryId == 0) {
            return this.errorHandler("默认跑数规则不允许删除");
         } else {
            List e = this.jobService.findJobByRetryRuleId(retryId);
            Iterator var4 = e.iterator();

            while(var4.hasNext()) {
               Job job = (Job)var4.next();
               job.setRetryId(0);
               this.jobService.update(job);
            }

            this.jobRetryRuleService.delete(retryId);
            return this.outBound("跑数规则删除成功");
         }
      } catch (Exception var5) {
         logger.error("------------->/retryRule/delete 接口异常" + var5.getMessage());
         return this.errorHandler("跑数规则删除异常:" + var5.getMessage());
      }
   }

   @RequestMapping(
      value = {"/getById"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "跑数规则信息获取",
      notes = "返回json"
   )
   @ResponseBody
   public Object getById(int retryId) {
      logger.info("------------->/retryRule/getById 跑数规则信息获取");

      try {
         RetryRule e = this.jobRetryRuleService.getById(retryId);
         return this.outBound(e);
      } catch (Exception var3) {
         logger.error("------------->/retryRule/getById 接口异常" + var3.getMessage());
         return this.errorHandler("跑数规则信息获取异常:" + var3.getMessage());
      }
   }

   private void reInit(RetryRule retryRule) {
      if(retryRule.getErrorIntervalNum() > 0) {
         retryRule.setError(true);
      }

      if(retryRule.getTimeIntervalNum() > 0) {
         retryRule.setTime(true);
      }

   }
}
