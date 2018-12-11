package com.meicloud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meicloud.controller.BasicController;
import com.meicloud.dto.DicVO;
import com.meicloud.enums.LengthLimit;
import com.meicloud.model.Queue;
import com.meicloud.services.QueueService;
import com.meicloud.utils.Utils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.Iterator;
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
@RequestMapping({"queue"})
public class QueueController extends BasicController {

   private static Logger logger = LoggerFactory.getLogger(QueueController.class);
   @Autowired
   private QueueService queueService;


   @ApiOperation(
      value = "集群列表",
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
      logger.info("------------->/queue/list 集群列表");

      try {
         PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum));
         List e = this.queueService.findList(new Queue());
         if(!Utils.isEmpityCollection(e)) {
            Iterator var5 = e.iterator();

            while(var5.hasNext()) {
               Queue page = (Queue)var5.next();
               page.setSvnPassWord("******");
            }
         }

         PageInfo page1 = new PageInfo(e);
         return this.outBound(this.getDataInfo(new Queue(), page1.getList(), Long.valueOf(page1.getTotal())));
      } catch (Exception var6) {
         logger.error("------------->/queue/list 接口异常" + var6.getMessage());
         return this.errorHandler("集群列表查询异常:" + var6.getMessage());
      }
   }

   @RequestMapping(
      value = {"/detail"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "集群详情信息",
      notes = "返回json"
   )
   @ResponseBody
   public Object detail(String queueId) {
      logger.info("------------->/queue/delete 主题删除");

      try {
         Queue e = this.queueService.findOne(queueId);
         return this.outBound(e);
      } catch (Exception var3) {
         logger.error("------------->/queue/detail 接口异常" + var3.getMessage());
         return this.errorHandler("集群详情信息查询异常:" + var3.getMessage());
      }
   }

   @RequestMapping(
      value = {"/save"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "集群新增/修改",
      notes = "返回json"
   )
   @ResponseBody
   public Object save(@RequestBody Queue queue) {
      logger.info("------------->/queue/save 集群保存");

      try {
         if(queue.getQueueName().length() > Integer.parseInt(LengthLimit.Queue_queueName.toString())) {
            return this.errorHandler("集群名称过长！");
         } else if(queue.getQueueCode().length() > Integer.parseInt(LengthLimit.Queue_queueCode.toString())) {
            return this.errorHandler("集群编码过长！");
         } else if(queue.getSvnUrl().length() > Integer.parseInt(LengthLimit.Queue_svnUrl.toString())) {
            return this.errorHandler("SVN地址过长！");
         } else if(queue.getSvnUserName().length() > Integer.parseInt(LengthLimit.Queue_svnUserNam.toString())) {
            return this.errorHandler("SVN账号过长！");
         } else if(queue.getSvnPassWord().length() > Integer.parseInt(LengthLimit.Queue_svnPassWord.toString())) {
            return this.errorHandler("SVN密码过长！");
         } else {
            if(Utils.isEmptyStr(queue.getQueueId())) {
               this.queueService.addQueue(queue);
            } else {
               this.queueService.updateQueue(queue);
            }

            return this.outBound("集群保存成功");
         }
      } catch (Exception var3) {
         logger.error("------------->/queue/save 接口异常" + var3.getMessage());
         return this.errorHandler("集群保存异常:" + var3.getMessage());
      }
   }

   @RequestMapping(
      value = {"/delete"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "删除集群",
      notes = "返回json"
   )
   @ResponseBody
   public Object delete(String queueId) {
      logger.info("------------->/queue/delete 主题删除");

      try {
         this.queueService.delQueue(queueId);
         return this.outBound("集群删除成功");
      } catch (Exception var3) {
         logger.error("------------->/queue/delete 接口异常" + var3.getMessage());
         return this.errorHandler("集群删除异常:" + var3.getMessage());
      }
   }

   @ApiOperation(
      value = "集群下拉列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/optionList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object optionList(HttpServletRequest request) {
      logger.info("------------->/queue/list 集群下拉列表");

      try {
         List e = this.queueService.findListByRoles(request);
         ArrayList dicVOList = new ArrayList();
         if(!Utils.isEmpityCollection(e)) {
            Iterator var5 = e.iterator();

            while(var5.hasNext()) {
               Queue queue = (Queue)var5.next();
               DicVO vo = new DicVO();
               vo.setKey(queue.getQueueId());
               vo.setName(queue.getQueueName());
               dicVOList.add(vo);
            }
         }

         return this.outBound(dicVOList);
      } catch (Exception var7) {
         logger.error("------------->/queue/list 接口异常" + var7.getMessage());
         return this.errorHandler("集群列表查询异常:" + var7.getMessage());
      }
   }
}
