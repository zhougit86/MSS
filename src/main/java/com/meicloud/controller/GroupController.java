package com.meicloud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meicloud.controller.BasicController;
import com.meicloud.dto.ConfigGroup;
import com.meicloud.dto.DicInfo;
import com.meicloud.dto.TreeInfo;
import com.meicloud.enums.LengthLimit;
import com.meicloud.model.Account;
import com.meicloud.model.Group;
import com.meicloud.model.GroupJobRefer;
import com.meicloud.model.GroupJobReferOption;
import com.meicloud.model.GroupLevel;
import com.meicloud.model.Job;
import com.meicloud.model.NameValuePair;
import com.meicloud.model.Proportion;
import com.meicloud.model.Queue;
import com.meicloud.model.Tags;
import com.meicloud.model.Topic;
import com.meicloud.services.GroupService;
import com.meicloud.services.IndexService;
import com.meicloud.services.JobService;
import com.meicloud.services.QueueService;
import com.meicloud.services.TagsService;
import com.meicloud.services.TopicListService;
import com.meicloud.utils.StringUtil;
import com.meicloud.utils.Utils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"group"})
@Scope("prototype")
public class GroupController extends BasicController {

   private static Logger logger = LoggerFactory.getLogger(GroupController.class);
   @Autowired
   private GroupService groupService;
   @Autowired
   private IndexService indexService;
   @Autowired
   private JobService jobService;
   @Autowired
   private QueueService queueService;
   @Autowired
   private TopicListService topicListService;
   @Autowired
   private TagsService tagsService;
   private Set keyText = new HashSet();
   private Set fromTo = new HashSet();
   private Set ganttKeyText = new TreeSet();
   private Set ganttFromTo = new HashSet();
   private static final int parentIdAdd = 80000000;


   @ApiOperation(
      value = "组配置列表",
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
         value = "组名称",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String groupName, 
      @ApiParam(
         value = "集群编码",
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
         value = "标记名称",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String tagName, HttpServletRequest request) {
      logger.info("------------->/group/list 组配置列表");

      try {
         List e = this.topicListService.findListByRoles((String)null, request);
         ArrayList topics = new ArrayList();
         Iterator groupList;
         if(!Utils.isEmpityCollection(e)) {
            groupList = e.iterator();

            while(groupList.hasNext()) {
               Topic groupIds = (Topic)groupList.next();
               topics.add(groupIds.getTopicId());
            }
         }

         Object groupIds1 = new ArrayList();
         if(!Utils.isEmptyStr(tagName) && !Utils.isEmptyStr(queueId)) {
            Tags groupList1 = new Tags();
            groupList1.setQueueId(queueId);
            groupList1.setTagName(tagName);
            groupIds1 = this.tagsService.getByNameAndQueueId(groupList1);
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

         List groupList2 = this.groupService.getList(groupName, queueId, Utils.isEmptyStr((Object)topicId)?(new Integer(0)).intValue():topicId.intValue(), Utils.isEmptyStr((Object)levelId)?(new Integer(0)).intValue():levelId.intValue(), false, topics, (List)groupIds1);
         PageInfo page1 = new PageInfo(groupList2);
         this.groupService.setNextFireDateAndQueueName(page1.getList(), request);
         return this.outBound(this.getDataInfo(new Group(), page1.getList(), Long.valueOf(page1.getTotal())));
      } catch (Exception var16) {
         logger.error("------------->/group/list 接口异常" + var16.getMessage());
         return this.errorHandler("组配置列表查询异常:" + var16.getMessage());
      }
   }

   @ApiOperation(
      value = "组配置下拉列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/optionList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object optionList() {
      logger.info("------------->/group/optionList 组配置下拉列表");
      ArrayList dicInfoList = new ArrayList();

      try {
         List e = this.groupService.getAllGroup();
         if(!Utils.isEmpityCollection(e)) {
            Iterator var4 = e.iterator();

            while(var4.hasNext()) {
               Group group = (Group)var4.next();
               DicInfo info = new DicInfo();
               info.setKey(Integer.valueOf(group.getGroupId()));
               info.setName(group.getGroupName());
               dicInfoList.add(info);
            }
         }

         return this.outBound(dicInfoList);
      } catch (Exception var6) {
         logger.error("------------->/group/optionList 接口异常" + var6.getMessage());
         return this.errorHandler("组配置下拉列表查询异常:" + var6.getMessage());
      }
   }

   @ApiOperation(
      value = "作业组详情",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/detail"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object detail(Integer groupId) {
      logger.info("------------->/group/detail 作业组详情");

      try {
         Group e = this.groupService.getById(groupId.intValue());
         List refGroupList = this.groupService.getRefGroupById(groupId.intValue());
         StringBuilder jobIds;
         if(!Utils.isEmpityCollection(refGroupList)) {
            StringBuilder groupJobReferList = new StringBuilder();
            jobIds = new StringBuilder();

            for(int jobNames = 0; jobNames < refGroupList.size(); ++jobNames) {
               Group i = (Group)refGroupList.get(jobNames);
               if(!Utils.isEmptyStr((Object)i)) {
                  groupJobReferList.append(i.getGroupId());
                  jobIds.append(i.getGroupName());
                  if(jobNames != refGroupList.size() - 1) {
                     groupJobReferList.append("@");
                     jobIds.append("@");
                  }
               }
            }

            e.setReferedGroupIds(groupJobReferList.toString());
            e.setReferedGroupNames(jobIds.toString());
         }

         List var10 = this.groupService.getGroupJobReferList(groupId.intValue());
         if(!Utils.isEmpityCollection(var10)) {
            jobIds = new StringBuilder();
            StringBuilder var11 = new StringBuilder();

            for(int var12 = 0; var12 < var10.size(); ++var12) {
               GroupJobRefer g = (GroupJobRefer)var10.get(var12);
               jobIds.append(g.getJobId());
               var11.append(g.getJobName());
               if(var12 != var10.size() - 1) {
                  jobIds.append("@");
                  var11.append("@");
               }
            }

            e.setReferedJobIds(jobIds.toString());
            e.setReferedJobNames(var11.toString());
         }

         return this.outBound(e);
      } catch (Exception var9) {
         logger.error("------------->/group/detail 接口异常" + var9.getMessage());
         return this.errorHandler("作业组详情查询异常:" + var9.getMessage());
      }
   }

   @ApiOperation(
      value = "作业组统计数据(主题，已配置作业,启用作业，禁用作业)",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/configGroup"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object configGroup(String queueId) {
      logger.info("------------->/group/configGroup 作业组分组统计数据(主题，已配置作业,启用作业，禁用作业)");

      try {
         boolean e = true;
         ConfigGroup configGroup = new ConfigGroup();
         List headerCount = this.groupService.getHeaderCount(e, queueId);
         Iterator var6 = headerCount.iterator();

         while(var6.hasNext()) {
            NameValuePair nvp = (NameValuePair)var6.next();
            String key = nvp.getName();
            int count = nvp.getId();
            if("主题数".equals(key)) {
               configGroup.setTOPIC_COUNT_KEY(Integer.valueOf(count));
            }

            if("总数量".equals(key)) {
               configGroup.setTOTAL_JOB_COUNT_KEY(Integer.valueOf(count));
            }

            if("启用数".equals(key)) {
               configGroup.setENABLE_JOB_COUNT_KEY(Integer.valueOf(count));
            }

            if("禁用数".equals(key)) {
               configGroup.setDISABLE_JOB_COUNT_KEY(Integer.valueOf(count));
            }
         }

         return this.outBound(configGroup);
      } catch (Exception var9) {
         logger.error("------------->/group/configGroup 接口异常" + var9.getMessage());
         return this.errorHandler("作业组分组统计数据(主题，已配置作业,启用作业，禁用作业)查询异常:" + var9.getMessage());
      }
   }

   @RequestMapping(
      value = {"/save"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "job组新增/修改",
      notes = "返回json"
   )
   @ResponseBody
   public Object save(HttpServletRequest request, @RequestBody Group group) {
      logger.info("------------->/group/save job组新增/修改");

      try {
         if(group.getGroupName().length() > Integer.parseInt(LengthLimit.Group_groupName.toString())) {
            return this.errorHandler("组名过长!");
         } else {
            String e = group.getDesc();
            if(e != null && !"".equals(e) && group.getDesc().length() > Integer.parseInt(LengthLimit.Group_desc.toString())) {
               return this.errorHandler("描述过长!");
            } else {
               String cron = group.getCron();
               if(!"noneed2addcron".equals(cron) && !CronExpression.isValidExpression(cron)) {
                  return this.errorHandler("Cron表达式：" + cron + " 格式不正确!");
               } else {
                  byte wait = 3;
                  Date now = new Date(System.currentTimeMillis());
                  CronExpression ce = new CronExpression(cron);
                  Date cd = null;

                  try {
                     cd = ce.getNextValidTimeAfter(now);
                  } catch (Exception var13) {
                     logger.error(var13.getMessage());
                     return this.errorHandler(var13.getMessage());
                  }

                  if(cd != null) {
                     long account = now.getTime() + (long)(wait * 1000 * 60);
                     long b = cd.getTime();
                     if(account > b) {
                        return this.errorHandler("Cron表达式[" + cron + "]下一个调起时间离现在至少[" + wait + "]分钟!");
                     } else {
                        if(group.getGroupName() != null) {
                           group.setGroupName(group.getGroupName().trim());
                        }

                        Account account1 = (Account)request.getSession().getAttribute("user");
                        group.setuUser(account1.getAccount());
                        if(group.getGroupId() != 0) {
                           if(this.groupService.checkIfExist(group, false)) {
                              return this.errorHandler("GroupName " + group.getGroupName() + " already exists.");
                           } else {
                              group.setCron(cron.trim());
                              this.groupService.update(group);
                              return this.outBound("修改成功!");
                           }
                        } else if(this.groupService.checkIfExist(group, true)) {
                           return this.errorHandler("GroupName " + group.getGroupName() + " already exists.");
                        } else {
                           group.setcUser(account1.getAccount());
                           this.groupService.add(group);
                           return this.outBound("新增成功!");
                        }
                     }
                  } else {
                     return this.errorHandler("Cron表达式[" + cron + "]将导致作业永远不会被调起!");
                  }
               }
            }
         }
      } catch (Exception var14) {
         logger.error("------------->/group/save 接口异常" + var14.getMessage());
         return this.errorHandler("job组新增/修改异常:" + var14.getMessage());
      }
   }

   @RequestMapping(
      value = {"/delete"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "job组删除",
      notes = "返回json"
   )
   @ResponseBody
   public Object delete(HttpServletRequest request, int groupId, 
      @ApiParam(
         value = "删除原因",
         required = true
      ) @RequestParam String delRemark) {
      logger.info("------------->/group/delete job组删除");

      try {
         if(!Utils.isEmptyStr(delRemark)) {
            delRemark = new String(delRemark.getBytes("iso8859-1"), "utf-8");
         }

         Account e = (Account)request.getSession().getAttribute("user");
         boolean delete = this.groupService.delete(groupId, delRemark, e.getAccount());
         return !delete?this.errorHandler("删除失败!"):this.outBound("删除成功!");
      } catch (Exception var6) {
         logger.error("------------->/group/delete 接口异常" + var6.getMessage());
         return this.errorHandler("job组删除异常:" + var6.getMessage());
      }
   }

   @RequestMapping(
      value = {"/updateState"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "job组状态更新",
      notes = "返回json"
   )
   @ResponseBody
   public Object updateState(HttpServletRequest request, int groupId, 
      @ApiParam(
         value = "删除原因",
         required = true
      ) @RequestParam String delRemark) {
      logger.info("------------->/group/updateState job组状态更新");

      try {
         if(!Utils.isEmptyStr(delRemark)) {
            delRemark = new String(delRemark.getBytes("iso8859-1"), "utf-8");
         }

         Account e = (Account)request.getSession().getAttribute("user");
         this.groupService.updateState(groupId, e.getAccount(), delRemark);
         return this.outBound("job组状态更新成功!");
      } catch (Exception var5) {
         logger.error("------------->/group/updateState 接口异常" + var5.getMessage());
         return this.errorHandler("job组状态更新异常:" + var5.getMessage());
      }
   }

   @ApiOperation(
      value = "配置依赖组树信息",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/groupListByQueue"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object groupListByQueue(
      @ApiParam(
         value = "集群ID",
         required = true
      ) @RequestParam String queueId, Integer groupId, Integer pid, Integer nodeId, String nodeType) {
      logger.info("------------->/group/groupListByQueue 配置依赖组树信息");

      try {
         ArrayList e = new ArrayList();
         this.groupTree(e, groupId, queueId, nodeType, pid, nodeId);
         return this.outBound(e);
      } catch (Exception var7) {
         logger.error("------------->/group/groupListByQueue 接口异常" + var7.getMessage());
         return this.errorHandler("配置依赖组树信息异常:" + var7.getMessage());
      }
   }

   @ApiOperation(
      value = "配置依赖作业树信息",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/jobListByQueue"},
      method = {RequestMethod.POST},
      consumes = {"application/json"}
   )
   @ResponseBody
   public Object jobListByQueue(
      @ApiParam(
         value = "集群ID",
         required = true
      ) @RequestParam String queueId, Integer groupId, Integer nodeId) {
      logger.info("------------->/group/jobListByQueue 配置依赖作业树信息");

      try {
         ArrayList e = new ArrayList();
         this.groupJobTree(e, groupId, queueId, nodeId);
         return this.outBound(e);
      } catch (Exception var5) {
         logger.error("------------->/group/jobListByQueue 接口异常" + var5.getMessage());
         return this.errorHandler("配置依赖作业树信息异常:" + var5.getMessage());
      }
   }

   @ApiOperation(
      value = "首页集群-->主题-->层级树",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/groupLeft"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object groupLeft(HttpServletRequest request) {
      logger.info("------------->/group/groupLeft 首页集群-->主题-->层级树");
      ArrayList list = new ArrayList();

      try {
         List e = this.queueService.findListByRoles(request);
         if(Utils.isEmpityCollection(e)) {
            return this.outBound(list);
         } else {
            ArrayList queues = new ArrayList();
            Iterator var6 = e.iterator();

            while(var6.hasNext()) {
               Queue queue = (Queue)var6.next();
               queues.add(queue.getQueueId());
               TreeInfo dto = new TreeInfo();
               dto.setId("X_" + queue.getQueueId());
               dto.setName(queue.getQueueName());
               dto.setpId("0");
               dto.setChecked(Boolean.TRUE.booleanValue());
               dto.setType("X");
               dto.setExport1(queue.getQueueId());
               list.add(dto);
            }

            this.getGroupInfo(queues, list);
            return this.outBound(list);
         }
      } catch (Exception var8) {
         logger.error("------------->/group/groupLeft 接口异常" + var8.getMessage());
         return this.errorHandler("首页集群-->主题-->层级树:" + var8.getMessage());
      }
   }

   private void getGroupInfo(List queues, List list) throws Exception {
      try {
         List e = this.groupService.getNavCheckList();
         HashMap checkMap = new HashMap();
         Iterator groupLevelList = e.iterator();

         while(groupLevelList.hasNext()) {
            Group topicList = (Group)groupLevelList.next();
            String topic = topicList.getTopicId() + "@" + topicList.getLevelId();
            checkMap.put(topic, Integer.valueOf(topicList.getGroupId()));
         }

         List topicList1 = this.indexService.topicListByQueueIds(queues);
         if(!Utils.isEmpityCollection(topicList1)) {
            List groupLevelList1 = this.indexService.getLevelNot0ByQueueIds(queues);
            if(!Utils.isEmpityCollection(groupLevelList1)) {
               Iterator var8 = topicList1.iterator();

               while(var8.hasNext()) {
                  Topic topic1 = (Topic)var8.next();
                  TreeInfo dto = new TreeInfo();
                  dto.setId("M_" + topic1.getTopicId());
                  dto.setName(topic1.getName());
                  dto.setpId("X_" + topic1.getQueueId());
                  dto.setChecked(Boolean.TRUE.booleanValue());
                  dto.setType("M");
                  dto.setExport1(topic1.getQueueId());
                  list.add(dto);
                  Iterator var11 = groupLevelList1.iterator();

                  while(var11.hasNext()) {
                     GroupLevel gl = (GroupLevel)var11.next();
                     String key = topic1.getTopicId() + "@" + gl.getLevelId();
                     if(checkMap.containsKey(key)) {
                        TreeInfo dto1 = new TreeInfo();
                        dto1.setId("N_" + gl.getLevelId());
                        dto1.setName(gl.getLevelName());
                        dto1.setpId("M_" + topic1.getTopicId());
                        dto1.setChecked(Boolean.TRUE.booleanValue());
                        dto1.setType("N");
                        dto1.setExport1(topic1.getQueueId());
                        list.add(dto1);
                     }
                  }
               }

            }
         }
      } catch (Exception var14) {
         throw var14;
      }
   }

   @ApiOperation(
      value = "生成组依赖图",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/relationGraph"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object relationGraph(int groupId) {
      logger.info("------------->/group/relationGraph 生成组依赖图");

      try {
         this.keyText.clear();
         this.fromTo.clear();
         Group e = this.groupService.getById(groupId);
         List refGroupList = this.groupService.getRefGroupById(groupId);
         List groupRefList = this.groupService.getGroupRefById(groupId);
         this.setKeyText(e, (String)null);
         this.relationPre(refGroupList, groupId);
         this.relationNext(groupRefList, groupId);
         return this.outBound(this.keyText.toString() + "#" + this.fromTo.toString());
      } catch (Exception var5) {
         logger.error("------------->/group/relationGraph 接口异常" + var5.getMessage());
         return this.errorHandler("生成组依赖图:" + var5.getMessage());
      }
   }

   @ApiOperation(
      value = "获取甘特图",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/ganttGraph"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object ganttGraph(int groupId) {
      logger.info("------------->/group/ganttGraph 获取甘特图");

      try {
         this.ganttKeyText.clear();
         this.ganttFromTo.clear();
         Group e = this.groupService.getById(groupId);
         Group gTime = this.groupService.groupExectime(groupId);
         if(gTime != null) {
            e.setExecTime(gTime.getExecTime());
            e.setEndDate(gTime.getEndDate());
            e.setStartDate(gTime.getStartDate());
         }

         List refGroupList = this.groupService.getRefGroupById(groupId);
         this.setGanttKey(e);
         this.ganttPre(refGroupList, groupId);
         return this.outBound(this.ganttKeyText.toString() + "#" + this.ganttFromTo.toString());
      } catch (Exception var5) {
         logger.error("------------->/group/ganttGraph 接口异常" + var5.getMessage());
         return this.errorHandler("获取甘特图:" + var5.getMessage());
      }
   }

   @ApiOperation(
      value = "查询前置依赖组",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/showRefGroup"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object showRefGroup(int groupId) {
      logger.info("------------->/group/showRefGroup 查询前置依赖组");
      String str = "";

      try {
         List e = this.groupService.getRefGroupById(groupId);
         ArrayList groupIds = new ArrayList();
         Iterator var6 = e.iterator();

         while(var6.hasNext()) {
            Group propList = (Group)var6.next();
            groupIds.add(Integer.valueOf(propList.getGroupId()));
         }

         List propList1 = this.indexService.proportionsLatestRound((List)groupIds, false);
         str = e.toString() + "#" + propList1.toString();
         return this.outBound(str);
      } catch (Exception var7) {
         logger.error("------------->/group/showRefGroup 接口异常" + var7.getMessage());
         return this.errorHandler("查询前置依赖组:" + var7.getMessage());
      }
   }

   private void groupTree(List list, Integer groupId, String queueId) throws Exception {
      try {
         List e = this.indexService.topicListByQueueId(queueId);
         List levelList = this.indexService.getLevelNot0ByQueueId(queueId);
         HashMap groupIdMap = new HashMap();
         List refGroupList = null;
         HashMap topicMap = new HashMap();
         HashMap levelMap = new HashMap();
         HashMap groupMap = new HashMap();
         Iterator var12;
         if(groupId != null) {
            refGroupList = this.groupService.getRefGroupById(groupId.intValue());
            if(!Utils.isEmpityCollection(refGroupList)) {
               var12 = refGroupList.iterator();

               while(var12.hasNext()) {
                  Group topic = (Group)var12.next();
                  if(!topicMap.containsKey(Integer.valueOf(topic.getTopicId()))) {
                     topicMap.put(Integer.valueOf(topic.getTopicId()), Integer.valueOf(topic.getTopicId()));
                  }

                  if(!levelMap.containsKey(Integer.valueOf(topic.getLevelId()))) {
                     levelMap.put(Integer.valueOf(topic.getLevelId()), Integer.valueOf(topic.getLevelId()));
                  }

                  if(!groupMap.containsKey(Integer.valueOf(topic.getGroupId()))) {
                     groupMap.put(Integer.valueOf(topic.getGroupId()), Integer.valueOf(topic.getGroupId()));
                  }
               }
            }
         }

         var12 = e.iterator();

         while(var12.hasNext()) {
            Topic topic1 = (Topic)var12.next();
            TreeInfo dto = new TreeInfo();
            dto.setId("M_" + topic1.getTopicId());
            dto.setName(topic1.getName());
            dto.setpId("0");
            if(topicMap.containsKey(topic1.getTopicId())) {
               dto.setChecked(Boolean.TRUE.booleanValue());
            } else {
               dto.setChecked(Boolean.FALSE.booleanValue());
            }

            dto.setType("M");
            list.add(dto);
            Iterator var15 = levelList.iterator();

            while(var15.hasNext()) {
               GroupLevel gl = (GroupLevel)var15.next();
               List groupList = this.indexService.getGroupByLeverAndRefer(gl.getLevelId().intValue(), topic1.getTopicId().intValue());
               if(!Utils.isEmpityCollection(groupList)) {
                  TreeInfo dto1 = new TreeInfo();
                  dto1.setId("N_" + gl.getLevelId());
                  dto1.setName(gl.getLevelName());
                  dto1.setpId("M_" + topic1.getTopicId());
                  if(levelMap.containsKey(gl.getLevelId())) {
                     dto1.setChecked(Boolean.TRUE.booleanValue());
                  } else {
                     dto1.setChecked(Boolean.FALSE.booleanValue());
                  }

                  dto1.setType("N");
                  list.add(dto1);
                  Iterator var19 = groupList.iterator();

                  while(var19.hasNext()) {
                     Group group = (Group)var19.next();
                     TreeInfo dto2 = new TreeInfo();
                     dto2.setId("Y_" + group.getGroupId());
                     dto2.setName(group.getGroupName());
                     dto2.setpId("N_" + gl.getLevelId());
                     dto2.setPPId("M_" + topic1.getTopicId());
                     dto2.setChecked(Boolean.FALSE.booleanValue());
                     if(groupMap.containsKey(Integer.valueOf(group.getGroupId()))) {
                        dto2.setChecked(Boolean.TRUE.booleanValue());
                     } else {
                        dto2.setChecked(Boolean.FALSE.booleanValue());
                     }

                     dto2.setType("Y");
                     list.add(dto2);
                     if(!groupIdMap.containsKey(Integer.valueOf(group.getGroupId()))) {
                        groupIdMap.put(Integer.valueOf(group.getGroupId()), Integer.valueOf(group.getGroupId()));
                     }
                  }
               }
            }
         }

      } catch (Exception var21) {
         throw var21;
      }
   }

   private void groupTree(List list, Integer groupId, String queueId, String nodeType, Integer pid, Integer nodeId) throws Exception {
      HashSet topicSet = new HashSet();
      HashSet levelSet = new HashSet();
      HashSet groupSet = new HashSet();
      List topicList;
      Iterator var12;
      if(groupId != null) {
         topicList = this.groupService.getRefGroupById(groupId.intValue());
         var12 = topicList.iterator();

         while(var12.hasNext()) {
            Group topic = (Group)var12.next();
            topicSet.add(Integer.valueOf(topic.getTopicId()));
            levelSet.add(Integer.valueOf(topic.getLevelId()));
            groupSet.add(Integer.valueOf(topic.getGroupId()));
         }
      }

      if("M".equals(nodeType)) {
         this.setLevelAndGruopByTopicId(list, levelSet, groupSet, pid);
      } else if("N".equals(nodeType)) {
         this.setGroupByLId_TId(list, groupSet, pid, nodeId);
      } else {
         topicList = this.indexService.topicListByQueueId(queueId);
         var12 = topicList.iterator();

         while(var12.hasNext()) {
            Topic topic1 = (Topic)var12.next();
            TreeInfo dto = new TreeInfo();
            dto.setId("M_" + topic1.getTopicId());
            dto.setName(topic1.getName());
            dto.setpId("0");
            dto.setChecked(topicSet.contains(topic1.getTopicId()));
            dto.setType("M");
            list.add(dto);
            if(topicSet.contains(topic1.getTopicId())) {
               this.setLevelAndGruopByTopicId(list, levelSet, groupSet, topic1.getTopicId());
            }
         }
      }

   }

   private void setLevelAndGruopByTopicId(List list, Set levelSet, Set groupSet, Integer topicId) throws Exception {
      List groupLevels = this.indexService.selectLevelByTopicId(topicId);
      Iterator var7 = groupLevels.iterator();

      while(var7.hasNext()) {
         GroupLevel groupLevel = (GroupLevel)var7.next();
         TreeInfo dto1 = new TreeInfo();
         dto1.setId("N_" + groupLevel.getLevelId());
         dto1.setName(groupLevel.getLevelName());
         dto1.setpId("M_" + topicId);
         dto1.setChecked(levelSet.contains(groupLevel.getLevelId()));
         list.add(dto1);
         if(levelSet.contains(groupLevel.getLevelId())) {
            Integer levelId = groupLevel.getLevelId();
            this.setGroupByLId_TId(list, groupSet, topicId, levelId);
         }
      }

   }

   private void setGroupByLId_TId(List list, Set groupSet, Integer topicId, Integer levelId) throws Exception {
      List groupList = this.indexService.getGroupByLeverAndRefer(levelId.intValue(), topicId.intValue());
      Iterator var7 = groupList.iterator();

      while(var7.hasNext()) {
         Group group = (Group)var7.next();
         TreeInfo dto2 = new TreeInfo();
         dto2.setId("Y_" + group.getGroupId());
         dto2.setName(group.getGroupName());
         dto2.setpId("N_" + levelId);
         dto2.setPPId("M_" + topicId);
         dto2.setChecked(Boolean.FALSE.booleanValue());
         dto2.setChecked(groupSet.contains(Integer.valueOf(group.getGroupId())));
         dto2.setType("Y");
         list.add(dto2);
      }

   }

   private void groupJobTree(List list, Integer groupId, String queueId, Integer nodeId) throws Exception {
      HashSet groupSet = new HashSet();
      HashSet jobSet = new HashSet();
      List groupList;
      Iterator var9;
      if(groupId != null) {
         groupList = this.groupService.getGroupJobReferList(groupId.intValue());
         var9 = groupList.iterator();

         while(var9.hasNext()) {
            GroupJobRefer group = (GroupJobRefer)var9.next();
            groupSet.add(Integer.valueOf(group.getGroupId()));
            jobSet.add(Integer.valueOf(group.getJobId()));
         }
      }

      TreeInfo dto;
      if(nodeId != null) {
         groupList = this.groupService.getGroupEditJobReferOption(nodeId.intValue());
         var9 = groupList.iterator();

         while(var9.hasNext()) {
            GroupJobReferOption group1 = (GroupJobReferOption)var9.next();
            dto = new TreeInfo();
            dto.setId("Z_" + group1.getJobId());
            dto.setName(group1.getJobName());
            dto.setpId("Y_" + group1.getGroupId());
            dto.setChecked(jobSet.contains(Integer.valueOf(group1.getJobId())));
            dto.setType("Z");
            list.add(dto);
         }
      } else {
         groupList = this.groupService.getListByQuequeId(queueId);
         var9 = groupList.iterator();

         while(var9.hasNext()) {
            Group group2 = (Group)var9.next();
            dto = new TreeInfo();
            dto.setId("Y_" + group2.getGroupId());
            dto.setName(group2.getGroupName());
            dto.setpId("0");
            dto.setChecked(Boolean.FALSE.booleanValue());
            dto.setChecked(groupSet.contains(Integer.valueOf(group2.getGroupId())));
            list.add(dto);
            if(groupSet.contains(Integer.valueOf(group2.getGroupId()))) {
               List options = this.groupService.getGroupEditJobReferOption(group2.getGroupId());
               Iterator var13 = options.iterator();

               while(var13.hasNext()) {
                  GroupJobReferOption option = (GroupJobReferOption)var13.next();
                  TreeInfo dto2 = new TreeInfo();
                  dto2.setId("Z_" + option.getJobId());
                  dto2.setName(option.getJobName());
                  dto2.setpId("Y_" + option.getGroupId());
                  dto2.setChecked(jobSet.contains(Integer.valueOf(option.getJobId())));
                  dto2.setType("Z");
                  list.add(dto2);
               }
            }
         }
      }

   }

   public void setKeyText(Group group, String defaultCategory) {
      String category = "forbidden";
      if(defaultCategory != null) {
         category = defaultCategory;
      }

      try {
         Group e = this.groupService.getById(group.getGroupId());
         Proportion proportion = this.indexService.proportionsLatestRound(Integer.valueOf(group.getGroupId()), false);
         if(proportion != null) {
            if(defaultCategory == null) {
               if(proportion.getTotal() == proportion.getForbidden()) {
                  category = "forbidden";
               } else if(proportion.getError() > 0) {
                  category = "error";
               } else if(proportion.getRunning() > 0) {
                  category = "run";
               } else if(proportion.getWaiting() > 0) {
                  category = "wait";
               } else {
                  String success = this.groupService.getGroupByState(group.getGroupId(), 5);
                  if(success != null) {
                     category = "success";
                  }
               }
            }

            this.keyText.add("{\"key\":" + group.getGroupId() + ",\"queueId\":\"" + e.getQueueId() + "\",\"type\":\"group\", \"text\":\"" + group.getGroupName().trim() + "\\n 调度时间：(" + group.getCron() + ") \\n" + "成功:" + proportion.getSuccess() + "  " + "失败:" + proportion.getError() + "\\n" + "等待:" + proportion.getWaiting() + "  " + "运行:" + proportion.getRunning() + "  " + "不跑:" + proportion.getForbidden() + " " + "\" , \"category\":\"" + category + "\"}");
         } else {
            this.keyText.add("{\"key\":" + group.getGroupId() + ",\"queueId\":\"" + e.getQueueId() + "\",\"type\":\"group\", \"text\":\"" + group.getGroupName() + "\\n 调度时间：(" + group.getCron() + ") \\n" + "成功:0  失败:0 \\n等待:0  运行数:0  不跑:0\" , \"category\":\"" + category + "\"}");
         }
      } catch (Exception var7) {
         logger.error(var7.getMessage());
      }

      this.setDummy(group);
   }

   public void relationPre(List groupList, int childId) throws Exception {
      Iterator var4 = groupList.iterator();

      while(var4.hasNext()) {
         Group group = (Group)var4.next();
         String line = "{\"from\":" + group.getGroupId() + ", \"to\":" + childId + "}";
         if(!this.fromTo.contains(line)) {
            this.setKeyText(group, (String)null);
            this.fromTo.add(line);
            this.relationPre(this.groupService.getRefGroupById(group.getGroupId()), group.getGroupId());
         }
      }

   }

   public void relationNext(List groupList, int parentId) throws Exception {
      Iterator var4 = groupList.iterator();

      while(var4.hasNext()) {
         Group group = (Group)var4.next();
         if(group != null) {
            String line = "{\"from\":" + parentId + ", \"to\":" + group.getGroupId() + "}";
            if(!this.fromTo.contains(line)) {
               this.setKeyText(group, (String)null);
               this.fromTo.add(line);
               this.relationNext(this.groupService.getGroupRefById(group.getGroupId()), group.getGroupId());
            }
         }
      }

   }

   private void setDummy(Group group) {
      int groupId = group.getGroupId();
      List jobList = null;

      try {
         jobList = this.jobService.getDummyGroupState(groupId);
      } catch (Exception var15) {
         logger.error(var15.getMessage());
         logger.error(var15.getMessage());
         throw var15;
      }

      if(jobList != null && jobList.size() != 0) {
         String line = "{\"from\":" + (80000000 + groupId) + ", \"to\":" + groupId + "}";
         if(!this.fromTo.contains(line)) {
            this.fromTo.add(line);
            String category = "forbidden";
            int success = 0;
            int forbidden = 0;
            int error = 0;
            int run = 0;
            int wait = 0;
            Iterator var12 = jobList.iterator();

            while(var12.hasNext()) {
               Job size = (Job)var12.next();
               String state = size.getExecState();
               switch(state.hashCode()) {
               case 48:
                  if(state.equals("0")) {
                     if(!size.isEnable()) {
                        ++forbidden;
                     } else {
                        ++wait;
                     }
                  }
                  break;
               case 49:
                  if(state.equals("1")) {
                     ++wait;
                  }
                  break;
               case 50:
                  if(state.equals("2")) {
                     ++run;
                  }
                  break;
               case 51:
                  if(state.equals("3")) {
                     ++error;
                  }
                  break;
               case 52:
                  if(state.equals("4")) {
                     ++error;
                  }
                  break;
               case 53:
                  if(state.equals("5")) {
                     ++success;
                  }
               }
            }

            if(forbidden > 0) {
               category = "forbidden";
            } else if(error > 0) {
               category = "error";
            } else if(run > 0) {
               category = "run";
            } else if(wait > 0) {
               category = "wait";
            } else {
               int var16 = jobList.size();
               if(var16 == success) {
                  category = "success";
               }
            }

            this.keyText.add("{\"key\":" + (80000000 + groupId) + ", \"text\":\"" + group.getGroupName().trim() + "_dummy\\n" + "调度时间：(虚拟组无法确定)\\n" + "成功：" + success + "  " + "失败：" + error + " \\n" + "等待：" + wait + "  " + "运行：" + run + "  " + "禁用：" + forbidden + " " + "\" , \"category\":\"" + category + "\"}");
         }
      }
   }

   public void setGanttKey(Group group) {
      try {
         SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
         Proportion proportion = this.indexService.proportionsLatestRound(Integer.valueOf(group.getGroupId()), false);
         Double success = Double.valueOf((double)proportion.getSuccess());
         Double total = Double.valueOf((double)(proportion.getTotal() - proportion.getForbidden()));
         NumberFormat df = NumberFormat.getPercentInstance();
         df.setMinimumFractionDigits(1);
         String per = "";
         if(total.doubleValue() != 0.0D) {
            per = df.format(success.doubleValue() / total.doubleValue()).replace("%", "");
         }

         String startDate = group.getStartDate() == null?sdf1.format(new Date()):e.format(group.getStartDate());
         String endDate = group.getEndDate() == null?sdf1.format(new Date()):e.format(group.getEndDate());
         this.ganttKeyText.add(group.getLevelOrderNo() + "*" + group.getGroupName() + "*" + group.getGroupId() + "*" + startDate + "*" + endDate + "*" + per + "*" + "S：" + proportion.getSuccess() + " E：" + proportion.getError() + " R：" + proportion.getRunning() + " W：" + proportion.getWaiting() + " F：" + proportion.getForbidden());
      } catch (Exception var11) {
         logger.error(var11.getMessage());
      }

   }

   public void ganttPre(List groupList, int childId) throws Exception {
      Iterator var4 = groupList.iterator();

      while(var4.hasNext()) {
         Group group = (Group)var4.next();
         Group gTime = this.groupService.groupExectime(group.getGroupId());
         if(gTime != null) {
            group.setExecTime(gTime.getExecTime());
            group.setEndDate(gTime.getEndDate());
            group.setStartDate(gTime.getStartDate());
         }

         this.setGanttKey(group);
         this.ganttFromTo.add(group.getGroupId() + "*" + childId);
         this.ganttPre(this.groupService.getRefGroupById(group.getGroupId()), group.getGroupId());
      }

   }
}
