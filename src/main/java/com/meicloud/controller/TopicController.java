package com.meicloud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meicloud.controller.BasicController;
import com.meicloud.enums.LengthLimit;
import com.meicloud.model.Topic;
import com.meicloud.services.TopicListService;
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
@RequestMapping({"topic"})
public class TopicController extends BasicController {

   private static Logger logger = LoggerFactory.getLogger(TopicController.class);
   @Autowired
   private TopicListService topicListService;


   @ApiOperation(
      value = "主题列表",
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
      logger.info("------------->/topic/list 主题列表");

      try {
         PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum));
         List e = this.topicListService.getTopicList();
         PageInfo page = new PageInfo(e);
         return this.outBound(this.getDataInfo(new Topic(), page.getList(), Long.valueOf(page.getTotal())));
      } catch (Exception var5) {
         logger.error("------------->/topic/list 接口异常" + var5.getMessage());
         return this.errorHandler("主题列表查询异常:" + var5.getMessage());
      }
   }

   @ApiOperation(
      value = "主题详情",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/detail"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object detail(Integer topicId) {
      logger.info("------------->/topic/detail 主题详情");

      try {
         Topic e = this.topicListService.getTopic(topicId);
         return this.outBound(e);
      } catch (Exception var3) {
         logger.error("------------->/topic/detail 接口异常" + var3.getMessage());
         return this.errorHandler("主题详情查询异常:" + var3.getMessage());
      }
   }

   @ApiOperation(
      value = "主题下拉列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/optionList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object optionList(
      @ApiParam(
         value = "集群ID",
         required = true
      ) @RequestParam String queueId, HttpServletRequest request) {
      logger.info("------------->/topic/optionList 主题下拉列表");

      try {
         List e = this.topicListService.findListByRoles(queueId, request);
         return this.outBound(e);
      } catch (Exception var4) {
         logger.error("------------->/topic/optionList 接口异常" + var4.getMessage());
         return this.errorHandler("主题下拉列表查询异常:" + var4.getMessage());
      }
   }

   @RequestMapping(
      value = {"/save"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "主题新增/修改",
      notes = "返回json"
   )
   @ResponseBody
   public Object save(@RequestBody Topic topic) {
      logger.info("------------->/topic/save 主题保存");

      try {
         if(topic.getName().length() > Integer.parseInt(LengthLimit.Topic_name.toString())) {
            return this.errorHandler("主题名称过长！");
         } else {
            String e = topic.getDesc();
            if(e != null && !"".equals(e) && e.length() > Integer.parseInt(LengthLimit.Topic_desc.toString())) {
               return this.errorHandler("主题描述过长！");
            } else {
               boolean exists;
               if(Utils.isEmptyStr((Object)topic.getTopicId())) {
                  exists = this.topicListService.checkIfExists4New(topic);
                  if(exists) {
                     return this.errorHandler("名称为[" + topic.getName() + "]的主题已存在！");
                  }

                  this.topicListService.addTopic(topic);
               } else {
                  exists = this.topicListService.checkIfExists4Update(topic);
                  if(exists) {
                     return this.errorHandler("名称为[" + topic.getName() + "]的主题已存在！");
                  }

                  this.topicListService.updateTopic(topic);
               }

               return this.outBound("主题保存成功");
            }
         }
      } catch (Exception var4) {
         logger.error("------------->/topic/save 接口异常" + var4.getMessage());
         return this.errorHandler("主题列表保存异常:" + var4.getMessage());
      }
   }

   @RequestMapping(
      value = {"/delete"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "删除主题",
      notes = "返回json"
   )
   @ResponseBody
   public Object delete(String topicId) {
      logger.info("------------->/topic/delete 主题删除");

      try {
         this.topicListService.deleteTopic(Integer.parseInt(topicId));
         return this.outBound("主题删除成功");
      } catch (Exception var3) {
         logger.error("------------->/topic/delete 接口异常" + var3.getMessage());
         return this.errorHandler("主题列表删除异常:" + var3.getMessage());
      }
   }
}
