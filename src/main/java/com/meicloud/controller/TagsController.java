package com.meicloud.controller;

import com.meicloud.controller.BasicController;
import com.meicloud.dto.QueueInfo;
import com.meicloud.dto.TreeInfo;
import com.meicloud.model.Role;
import com.meicloud.model.Tags;
import com.meicloud.services.RoleQueueService;
import com.meicloud.services.TagsService;
import com.meicloud.utils.Utils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"tags"})
public class TagsController extends BasicController {

   private static Logger logger = LoggerFactory.getLogger(TagsController.class);
   @Autowired
   private TagsService tagsService;
   @Autowired
   private RoleQueueService roleQueueService;


   @ApiOperation(
      value = "标记信息树",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/tree"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object tree(HttpServletRequest request) {
      logger.info("------------->/tags/tree 标记信息树");

      try {
         ArrayList e = new ArrayList();
         TreeInfo dto = new TreeInfo();
         dto.setId("T");
         dto.setName("标记管理");
         dto.setpId("0");
         dto.setType("0");
         dto.setExport("0");
         e.add(dto);
         List roleList = (List)request.getSession().getAttribute("roles");
         if(Utils.isEmpityCollection(roleList)) {
            return this.outBound(e);
         } else {
            ArrayList queueIds = new ArrayList();
            HashMap queueMap = new HashMap();
            Iterator info = roleList.iterator();

            while(info.hasNext()) {
               Role tagsList = (Role)info.next();
               List queueInfoList = this.roleQueueService.findGroupList(tagsList.getRoleId());
               if(!Utils.isEmpityCollection(queueInfoList)) {
                  Iterator var11 = queueInfoList.iterator();

                  while(var11.hasNext()) {
                     QueueInfo vo = (QueueInfo)var11.next();
                     if(!queueMap.containsKey(vo.getQueueId())) {
                        queueMap.put(vo.getQueueId(), vo.getQueueId());
                        TreeInfo vo1 = new TreeInfo();
                        vo1.setId("X_" + vo.getQueueId());
                        vo1.setName(vo.getQueueName());
                        vo1.setpId("T");
                        vo1.setType("X");
                        vo1.setExport("0");
                        e.add(vo1);
                        queueIds.add(vo.getQueueId());
                     }
                  }
               }
            }

            List tagsList1 = this.tagsService.findByqueueIds(queueIds);
            if(!Utils.isEmpityCollection(tagsList1)) {
               Iterator queueInfoList1 = tagsList1.iterator();

               while(queueInfoList1.hasNext()) {
                  Tags info1 = (Tags)queueInfoList1.next();
                  TreeInfo vo2 = new TreeInfo();
                  vo2.setId("F_" + info1.getTagId());
                  vo2.setName(info1.getTagName());
                  vo2.setType("F");
                  vo2.setpId("X_" + info1.getQueueId());
                  vo2.setCode(String.valueOf(info1.getTagId()));
                  vo2.setExport(String.valueOf(info1.getTagNum()));
                  e.add(vo2);
               }
            }

            return this.outBound(e);
         }
      } catch (Exception var13) {
         logger.error("------------->/tags/tree 接口异常" + var13.getMessage());
         return this.errorHandler("标记信息树查询异常:" + var13.getMessage());
      }
   }

   @RequestMapping(
      value = {"/add"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "\t新增标记",
      notes = "返回json"
   )
   @ResponseBody
   public Object add(
      @ApiParam(
         value = "group组ID",
         required = true
      ) @RequestParam int groupId, 
      @ApiParam(
         value = "标记名称",
         required = true
      ) @RequestParam String tagName) {
      logger.info("------------->/tags/add 新增标记");

      try {
         logger.info("groupId:" + groupId + "  tagName:" + tagName);
         byte e = 3;
         boolean success = this.tagsService.add(groupId, tagName, e);
         return success?this.outBound("关联标签[ " + tagName + " ]成功"):this.errorHandler("关联标签[ " + tagName + " ]失败");
      } catch (Exception var5) {
         logger.error("------------->/tags/add 接口异常" + var5.getMessage());
         return this.errorHandler("新增标记异常:" + var5.getMessage());
      }
   }

   @RequestMapping(
      value = {"/delete"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "\t新增标记",
      notes = "返回json"
   )
   @ResponseBody
   public Object delete(
      @ApiParam(
         value = "group组ID",
         required = true
      ) @RequestParam int tid) {
      logger.info("------------->/tags/delete 新增标记");

      try {
         boolean e = this.tagsService.delete(tid);
         return e?this.outBound("删除标记成功"):this.errorHandler("删除标记失败");
      } catch (Exception var3) {
         logger.error("------------->/tags/add 接口异常" + var3.getMessage());
         return this.errorHandler("新增标记异常:" + var3.getMessage());
      }
   }
}
