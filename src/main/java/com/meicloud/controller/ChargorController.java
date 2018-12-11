package com.meicloud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meicloud.controller.BasicController;
import com.meicloud.enums.LengthLimit;
import com.meicloud.model.Chargor;
import com.meicloud.services.ChargorListService;
import com.meicloud.utils.Utils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"chargor"})
public class ChargorController extends BasicController {

   private static Logger logger = LoggerFactory.getLogger(ChargorController.class);
   @Autowired
   private ChargorListService chargorListService;


   @ApiOperation(
      value = "责任人列表",
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
      logger.info("------------->/chargor/list 责任人列表");

      try {
         PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum));
         List e = this.chargorListService.getChargorList();
         PageInfo page = new PageInfo(e);
         return this.outBound(this.getDataInfo(new Chargor(), page.getList(), Long.valueOf(page.getTotal())));
      } catch (Exception var5) {
         logger.error("------------->/chargor/list 接口异常" + var5.getMessage());
         return this.errorHandler("责任人列表查询异常:" + var5.getMessage());
      }
   }

   @ApiOperation(
      value = "责任人下拉列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/optionList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object optionList(
      @ApiParam(
         value = "页数",
         required = true
      ) @RequestParam String queueId, HttpServletRequest request) {
      logger.info("------------->/chargor/optionList 责任人列表");

      try {
         List e = this.chargorListService.getGroupLevelListByQueueId(queueId, request);
         return this.outBound(e);
      } catch (Exception var4) {
         logger.error("------------->/chargor/optionList 接口异常" + var4.getMessage());
         return this.errorHandler("责任人下拉列表查询异常:" + var4.getMessage());
      }
   }

   @ApiOperation(
      value = "责任人详情",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/detail"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object detail(
      @ApiParam(
         value = "责任人ID",
         required = true
      ) @RequestParam Integer id) {
      logger.info("------------->/chargor/detail 责任人详情");

      try {
         Chargor e = this.chargorListService.getChargor(id);
         return this.outBound(e);
      } catch (Exception var3) {
         logger.error("------------->/chargor/detail 接口异常" + var3.getMessage());
         return this.errorHandler("责任人详情查询异常:" + var3.getMessage());
      }
   }

   @RequestMapping(
      value = {"/save"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "责任人新增/修改",
      notes = "返回json"
   )
   @ResponseBody
   public Object save(@RequestBody Chargor chargor) {
      logger.info("------------->/chargor/save 责任人保存");

      try {
         if(chargor.getName().length() > Integer.parseInt(LengthLimit.Chargor_name.toString())) {
            return this.errorHandler("责任人姓名过长！");
         } else if(chargor.getPhone().length() > Integer.parseInt(LengthLimit.Chargor_phone.toString())) {
            return this.errorHandler("责任人电话过长！");
         } else if(chargor.getEmail().length() > Integer.parseInt(LengthLimit.Chargor_email.toString())) {
            return this.errorHandler("责任人邮箱过长！");
         } else {
            if(Utils.isEmptyStr((Object)chargor.getId())) {
               boolean e = this.chargorListService.checkIfExists4New(chargor);
               if(e) {
                  return this.errorHandler("名称为[" + chargor.getName() + "]的责任人已存在！");
               }

               this.chargorListService.addChargor(chargor);
            } else {
               Chargor e1 = this.chargorListService.getChargor(chargor.getId());
               if(!e1.getName().equals(chargor.getName())) {
                  boolean exists = this.chargorListService.checkIfExists4New(chargor);
                  if(exists) {
                     return this.errorHandler("名称为[" + chargor.getName() + "]的责任人已存在！");
                  }
               }

               this.chargorListService.updateChargor(chargor);
            }

            return this.outBound("责任人保存成功");
         }
      } catch (Exception var4) {
         logger.error("------------->/chargor/save 接口异常" + var4.getMessage());
         return this.errorHandler("责任人保存异常:" + var4.getMessage());
      }
   }

   @RequestMapping(
      value = {"/delete"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "删除责任人",
      notes = "返回json"
   )
   @ResponseBody
   public Object delete(String id) {
      logger.info("------------->/chargor/delete 删除责任人");

      try {
         this.chargorListService.deleteChargor(Integer.parseInt(id));
         return this.outBound("删除责任人成功");
      } catch (Exception var3) {
         logger.error("------------->/chargor/delete 接口异常" + var3.getMessage());
         return this.errorHandler("删除责任人异常:" + var3.getMessage());
      }
   }
}
