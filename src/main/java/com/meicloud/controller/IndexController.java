package com.meicloud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meicloud.controller.BasicController;
import com.meicloud.dto.JobConutInfo;
import com.meicloud.model.Group;
import com.meicloud.model.Proportion;
import com.meicloud.model.Tags;
import com.meicloud.model.Topic;
import com.meicloud.services.GroupService;
import com.meicloud.services.IndexService;
import com.meicloud.services.JobService;
import com.meicloud.services.TagsService;
import com.meicloud.services.TopicListService;
import com.meicloud.utils.StringUtil;
import com.meicloud.utils.Utils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.net.URLDecoder;
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
@RequestMapping({"index"})
public class IndexController extends BasicController {

   private static Logger LOG = LoggerFactory.getLogger(IndexController.class);
   @Autowired
   private IndexService indexService;
   @Autowired
   private GroupService groupService;
   @Autowired
   private TopicListService topicListService;
   @Autowired
   private TagsService tagsService;
   @Autowired
   private JobService jobService;


   @ApiOperation(
      value = "调度监控job组列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/index"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object index(
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
         value = "组名称",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String groupName, 
      @ApiParam(
         value = "集群ID",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String queueId, 
      @ApiParam(
         value = "主题ID",
         required = false
      ) 
      @RequestParam(
         required = false
      ) Integer topicId, 
      @ApiParam(
         value = "层级ID",
         required = false
      ) 
      @RequestParam(
         required = false
      ) Integer levelId, 
      @ApiParam(
         value = "状态码(成功：5，运行中:2, 等待：0，禁止：-1，失败：3)",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String state, 
      @ApiParam(
         value = "标记名称",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String tagName, HttpServletRequest request) {
      LOG.info("------------->/index/index 调度监控job组列表");

      try {
         if(groupName != null) {
            groupName = URLDecoder.decode(groupName, "UTF-8");
         }

         List e = this.topicListService.findListByRoles((String)null, request);
         ArrayList topics = new ArrayList();
         Iterator groupList;
         if(!Utils.isEmpityCollection(e)) {
            groupList = e.iterator();

            while(groupList.hasNext()) {
               Topic groupTagesIds = (Topic)groupList.next();
               topics.add(groupTagesIds.getTopicId());
            }
         }

         Object groupTagesIds1 = new ArrayList();
         if(!Utils.isEmptyStr(tagName) && !Utils.isEmptyStr(queueId)) {
            Tags groupList1 = new Tags();
            groupList1.setQueueId(queueId);
            groupList1.setTagName(tagName);
            groupTagesIds1 = this.tagsService.getByNameAndQueueId(groupList1);
         }

         groupList = null;
         colName = (new Group()).getColName(colName);
         if(!StringUtil.isBlank(colName)) {
            String page = colName + " ";
            if(!StringUtil.isBlank(formValue)) {
               page = page + formValue;
            } else {
               page = page + "desc";
            }

            PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum), page);
         } else {
            PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum));
         }

         List groupList2 = this.groupService.getListByAllParameters(state, groupName, queueId, Utils.isEmptyStr((Object)topicId)?0:topicId.intValue(), Utils.isEmptyStr((Object)levelId)?0:levelId.intValue(), false, topics, (List)groupTagesIds1);
         PageInfo page1 = new PageInfo(groupList2);
         this.groupService.setNextFireDateAndQueueName(groupList2, request);
         ArrayList groupIds = new ArrayList();
         Iterator propList = groupList2.iterator();

         while(propList.hasNext()) {
            Group proportionMap = (Group)propList.next();
            if(proportionMap != null) {
               groupIds.add(Integer.valueOf(proportionMap.getGroupId()));
            }
         }

         if(groupIds != null && groupIds.size() > 0) {
            HashMap proportionMap1 = new HashMap();
            List propList1 = this.indexService.proportionsLatestRound((List)groupIds, false);
            Iterator var21;
            if(!Utils.isEmpityCollection(propList1)) {
               var21 = propList1.iterator();

               while(var21.hasNext()) {
                  Proportion g = (Proportion)var21.next();
                  proportionMap1.put(Integer.valueOf(g.getGroupId()), g);
               }
            }

            var21 = groupList2.iterator();

            while(var21.hasNext()) {
               Group g1 = (Group)var21.next();
               if(g1 != null && proportionMap1.containsKey(Integer.valueOf(g1.getGroupId()))) {
                  g1.setProportion((Proportion)proportionMap1.get(Integer.valueOf(g1.getGroupId())));
               }
            }
         }

         return this.outBound(this.getDataInfo(new Group(), page1.getList(), Long.valueOf(page1.getTotal())));
      } catch (Exception var22) {
         LOG.error("------------->/index/index 调度监控job组列表 接口异常" + var22.getMessage());
         return this.errorHandler("调度监控job组列表查询异常:" + var22.getMessage());
      }
   }

   @ApiOperation(
      value = "job组状态的数量统计",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/jobConut"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object jobConut(String queueId) {
      LOG.info("------------->/index/jobConut job组状态的数量统计 queueId:" + queueId);

      try {
         return this.outBound(this.setHeaderMsg(queueId));
      } catch (Exception var3) {
         LOG.error("------------->/index/jobConut job组状态的数量统计 接口异常" + var3.getMessage());
         return this.errorHandler("job组状态的数量统计异常:" + var3.getMessage());
      }
   }

   private JobConutInfo setHeaderMsg(String queueId) throws Exception {
      JobConutInfo info = new JobConutInfo();

      try {
         byte e = 0;
         List jobList = this.jobService.getList4RunPageHeader(0, Utils.isEmptyStr(queueId)?0:Integer.parseInt(queueId));
         info.setRunTimeWaitJobCount(jobList.size());
         int e1 = e + jobList.size();
         jobList = this.jobService.getList4RunPageHeader(2, Utils.isEmptyStr(queueId)?0:Integer.parseInt(queueId));
         info.setRunningJobCount(jobList.size());
         e1 += jobList.size();
         jobList = this.jobService.getList4RunPageHeader(6, Utils.isEmptyStr(queueId)?0:Integer.parseInt(queueId));
         info.setExpireJobCount(jobList.size());
         e1 += jobList.size();
         jobList = this.jobService.getList4RunPageHeader(3, Utils.isEmptyStr(queueId)?0:Integer.parseInt(queueId));
         info.setErrorJobCount(jobList.size());
         e1 += jobList.size();
         jobList = this.jobService.getList4RunPageHeader(5, Utils.isEmptyStr(queueId)?0:Integer.parseInt(queueId));
         info.setSuccessJobCount(jobList.size());
         e1 += jobList.size();
         info.setTotalJobCount(e1);
         info.setRunServers(this.indexService.getRunServers(queueId));
         return info;
      } catch (Exception var5) {
         throw var5;
      }
   }
}
