package com.meicloud.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.meicloud.controller.BasicController;
import com.meicloud.dao.ChargorMapper;
import com.meicloud.dao.GroupJobReferMapper;
import com.meicloud.dao.GroupLevelMapper;
import com.meicloud.dao.GroupReferMapper;
import com.meicloud.dao.JobRetryRuleMapper;
import com.meicloud.dao.TopicMapper;
import com.meicloud.datasource.CmsDataSource;
import com.meicloud.datasource.DynamicDataSourceContextHolder;
import com.meicloud.dto.TreeInfo;
import com.meicloud.model.Chargor;
import com.meicloud.model.DataSource;
import com.meicloud.model.Group;
import com.meicloud.model.GroupJobRefer;
import com.meicloud.model.GroupLevel;
import com.meicloud.model.GroupRefer;
import com.meicloud.model.Job;
import com.meicloud.model.Queue;
import com.meicloud.model.RetryRule;
import com.meicloud.model.ScheduleParameter;
import com.meicloud.model.Topic;
import com.meicloud.services.GroupService;
import com.meicloud.services.JobService;
import com.meicloud.services.QueueService;
import com.meicloud.services.ScheduleParameterService;
import com.meicloud.utils.StringUtil;
import com.meicloud.utils.beans.ResultBean;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"jobMigration"})
public class JobMigrationController extends BasicController {

   private static Logger LOG = LoggerFactory.getLogger(JobMigrationController.class);
   @Autowired
   private JobService jobService;
   @Autowired
   private GroupService groupService;
   @Autowired
   private QueueService queueService;
   @Autowired
   private GroupLevelMapper groupLevelMapper;
   @Autowired
   private TopicMapper topicMapper;
   @Autowired
   private JobRetryRuleMapper jobRetryRuleMapper;
   @Autowired
   private ChargorMapper chargorMapper;
   @Autowired
   private ScheduleParameterService scheduleParameterService;
   @Autowired
   private GroupReferMapper groupReferMapper;
   @Autowired
   private GroupJobReferMapper groupJobReferMapper;


   @ApiOperation(
      value = "JOB列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/jobList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object jobList() {
      try {
         ArrayList e = new ArrayList();
         Queue queryQueue = new Queue();
         List queues = this.queueService.findList(queryQueue);
         Iterator var5 = queues.iterator();

         while(var5.hasNext()) {
            Queue queue = (Queue)var5.next();
            TreeInfo queueTreeInfo = new TreeInfo();
            queueTreeInfo.setId("queue_" + queue.getQueueId());
            queueTreeInfo.setName(queue.getQueueName());
            queueTreeInfo.setPPId("0");
            queueTreeInfo.setpId("0");
            e.add(queueTreeInfo);
            List groups = this.groupService.getListByQuequeId(queue.getQueueId());
            Iterator var9 = groups.iterator();

            while(var9.hasNext()) {
               Group group = (Group)var9.next();
               TreeInfo groupTreeInfo = new TreeInfo();
               groupTreeInfo.setId("group_" + group.getGroupId());
               groupTreeInfo.setName(group.getGroupName());
               groupTreeInfo.setPPId(queueTreeInfo.getId());
               groupTreeInfo.setpId(queueTreeInfo.getId());
               e.add(groupTreeInfo);
               List jobs = this.jobService.getList4EditPage(group.getGroupId(), (String)null, Integer.parseInt(queue.getQueueId()));
               Iterator var13 = jobs.iterator();

               while(var13.hasNext()) {
                  Job job = (Job)var13.next();
                  TreeInfo jobTreeInfo = new TreeInfo();
                  jobTreeInfo.setId("job_" + job.getJobId());
                  jobTreeInfo.setName(job.getJobName());
                  jobTreeInfo.setPPId(groupTreeInfo.getId());
                  jobTreeInfo.setpId(groupTreeInfo.getId());
                  e.add(jobTreeInfo);
               }
            }
         }

         return this.outBound(e);
      } catch (Exception var15) {
         LOG.error(var15.getMessage());
         return this.errorHandler("获取作业列表异常:" + var15.getMessage());
      }
   }

   @ApiOperation(
      value = "JOB迁移",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/migration"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object migration(@RequestBody DataSource dataSource, List jobNames) {
      ResultBean var8;
      try {
         DruidDataSource e = CmsDataSource.getDataSources(dataSource.getBeanName());
         if(e == null) {
            HashMap jobName = new HashMap();
            jobName.put("dsname", dataSource.getBeanName());
            jobName.put("driver-class-name", dataSource.getDbType());
            jobName.put("username", dataSource.getUsername());
            jobName.put("password", dataSource.getPassword());
            jobName.put("url", dataSource.getUrl());
            jobName.put("initialSize", dataSource.getInitSize());
            jobName.put("minIdle", dataSource.getMinIdle());
            jobName.put("maxActive", dataSource.getMaxActive());
            CmsDataSource.initDataSource(jobName);
            CmsDataSource.reflash();
         }

         Iterator var5 = jobNames.iterator();

         while(var5.hasNext()) {
            String jobName1 = (String)var5.next();
            Job job = this.jobService.getJobByJobName(jobName1);
            this.migrationGroup(job.getGroupId(), dataSource.getBeanName());
         }

         var8 = this.outBound("JOB迁移成功...");
         return var8;
      } catch (Exception var11) {
         LOG.error(var11.getMessage());
         var8 = this.errorHandler("JOB迁移异常:" + var11.getMessage());
      } finally {
         DynamicDataSourceContextHolder.setDataSourceType("default");
      }

      return var8;
   }

   private int migrationGroup(int groupId, String sourceName) throws Exception {
      DynamicDataSourceContextHolder.setDataSourceType("default");
      Group group = this.groupService.getById(groupId);
      Queue queue = this.queueService.findOne(String.valueOf(group.getQueueId()));
      Topic topic = this.topicMapper.getTopic(Integer.valueOf(group.getTopicId()));
      GroupLevel groupLevel = this.groupLevelMapper.getLevelById(Integer.valueOf(group.getLevelId()));
      String referedGroupIds = group.getReferedGroupIds();
      String referedJobIds = group.getReferedJobIds();
      int topic2;
      if(StringUtil.isBlank(referedGroupIds)) {
         String[] groupLevel2;
         topic2 = (groupLevel2 = referedGroupIds.split("@")).length;

         for(int referedJobId = 0; referedJobId < topic2; ++referedJobId) {
            String group2 = groupLevel2[referedJobId];
            if(!StringUtil.isBlank(group2)) {
               int jobs = this.migrationGroup(Integer.parseInt(group2), sourceName);
               DynamicDataSourceContextHolder.setDataSourceType(sourceName);
               GroupRefer job = new GroupRefer();
               job.setGroupId(Integer.parseInt(group2));
               job.setReferedGroupId(jobs);
               job.setCreateDate(new Date());
               job.setcDate(new Date());
               this.groupReferMapper.add(job);
            }
         }
      }

      DynamicDataSourceContextHolder.setDataSourceType(sourceName);
      Group var29 = this.groupService.getByGroupName(group.getGroupName());
      Chargor chargor1;
      Chargor chargor12;
      Chargor chargor22;
      Job var37;
      if(var29 == null) {
         var29 = group;
         Queue var30 = this.queueService.findByQueueCode(queue.getQueueCode());
         if(var30 == null) {
            var30 = queue;
            this.queueService.addQueue(queue);
         }

         group.setQueueId(var30.getQueueId());
         Topic var31 = this.topicMapper.getTopicByName(topic.getName());
         if(var31 == null) {
            var31 = topic;
            this.topicMapper.addTopic(topic);
         }

         group.setTopicId(var31.getTopicId().intValue());
         GroupLevel var33 = this.groupLevelMapper.getLevelByLevelName(groupLevel.getLevelName());
         if(var33 == null) {
            var33 = groupLevel;
            this.groupLevelMapper.insert(groupLevel);
         }

         group.setLevelId(var33.getLevelId().intValue());
         this.groupService.add(group);
         DynamicDataSourceContextHolder.setDataSourceType("default");
         List var35 = this.jobService.getList4EditPage(groupId, (String)null, Integer.parseInt(group.getQueueId()));
         Iterator group3 = var35.iterator();

         while(group3.hasNext()) {
            var37 = (Job)group3.next();
            DynamicDataSourceContextHolder.setDataSourceType("default");
            RetryRule parameters = this.jobRetryRuleMapper.getById(var37.getRetryId());
            Chargor group4 = this.chargorMapper.getChargor(Integer.valueOf(var37.getChargorId()));
            Chargor retryRule = this.chargorMapper.getChargor(Integer.valueOf(var37.getChargorId2()));
            chargor1 = this.chargorMapper.getChargor(Integer.valueOf(var37.getChargorId3()));
            List chargor2 = this.scheduleParameterService.queryParameter(var37.getJobName());
            DynamicDataSourceContextHolder.setDataSourceType(sourceName);
            RetryRule chargor3 = this.jobRetryRuleMapper.getByRetryName(parameters.getRetryName());
            if(chargor3 == null) {
               chargor3 = parameters;
               this.jobRetryRuleMapper.add(parameters);
            }

            Chargor retryRule2 = this.chargorMapper.getChargorByName(group4.getName());
            if(retryRule2 == null) {
               retryRule2 = group4;
               this.chargorMapper.addChargor(group4);
            }

            chargor12 = this.chargorMapper.getChargorByName(retryRule.getName());
            if(chargor12 == null) {
               chargor12 = retryRule;
               this.chargorMapper.addChargor(retryRule);
            }

            chargor22 = this.chargorMapper.getChargorByName(chargor1.getName());
            if(chargor22 == null) {
               chargor22 = chargor1;
               this.chargorMapper.addChargor(chargor1);
            }

            Job chargor32 = this.jobService.getJobByJobName(var37.getJobName());
            if(chargor32 == null) {
               var37.setGroupId(var29.getGroupId());
               var37.setQueueId(Integer.parseInt(var30.getQueueId()));
               var37.setRetryId(chargor3.getRetryId());
               var37.setChargorId(retryRule2.getId().intValue());
               var37.setChargorId2(chargor12.getId().intValue());
               var37.setChargorId3(chargor22.getId().intValue());
               this.jobService.add(var37);
               Iterator groupJobRefer = chargor2.iterator();

               while(groupJobRefer.hasNext()) {
                  ScheduleParameter job2 = (ScheduleParameter)groupJobRefer.next();
                  this.scheduleParameterService.insertParameter(job2);
               }
            }
         }
      }

      if(StringUtil.isBlank(referedJobIds)) {
         String[] var36;
         int var34 = (var36 = referedJobIds.split("@")).length;

         for(topic2 = 0; topic2 < var34; ++topic2) {
            String var32 = var36[topic2];
            if(!StringUtil.isBlank(var32)) {
               DynamicDataSourceContextHolder.setDataSourceType("default");
               var37 = this.jobService.getJobById(Integer.parseInt(var32));
               Group var38 = this.groupService.getById(var37.getGroupId());
               List var39 = this.scheduleParameterService.queryParameter(var37.getJobName());
               DynamicDataSourceContextHolder.setDataSourceType(sourceName);
               Group var40 = this.groupService.getByGroupName(var38.getGroupName());
               if(var40 == null) {
                  this.migrationGroup(var38.getGroupId(), sourceName);
               } else {
                  DynamicDataSourceContextHolder.setDataSourceType("default");
                  RetryRule var41 = this.jobRetryRuleMapper.getById(var37.getRetryId());
                  chargor1 = this.chargorMapper.getChargor(Integer.valueOf(var37.getChargorId()));
                  Chargor var42 = this.chargorMapper.getChargor(Integer.valueOf(var37.getChargorId2()));
                  Chargor var43 = this.chargorMapper.getChargor(Integer.valueOf(var37.getChargorId3()));
                  DynamicDataSourceContextHolder.setDataSourceType(sourceName);
                  RetryRule var44 = this.jobRetryRuleMapper.getByRetryName(var41.getRetryName());
                  if(var44 == null) {
                     var44 = var41;
                     this.jobRetryRuleMapper.add(var41);
                  }

                  chargor12 = this.chargorMapper.getChargorByName(chargor1.getName());
                  if(chargor12 == null) {
                     chargor12 = chargor1;
                     this.chargorMapper.addChargor(chargor1);
                  }

                  chargor22 = this.chargorMapper.getChargorByName(var42.getName());
                  if(chargor22 == null) {
                     chargor22 = var42;
                     this.chargorMapper.addChargor(var42);
                  }

                  Chargor var45 = this.chargorMapper.getChargorByName(var43.getName());
                  if(var45 == null) {
                     var45 = var43;
                     this.chargorMapper.addChargor(var43);
                  }

                  Job var46 = this.jobService.getJobByJobName(var37.getJobName());
                  if(var46 == null) {
                     var37.setGroupId(var40.getGroupId());
                     var37.setQueueId(Integer.parseInt(var40.getQueueId()));
                     var37.setRetryId(var44.getRetryId());
                     var37.setChargorId(chargor12.getId().intValue());
                     var37.setChargorId2(chargor22.getId().intValue());
                     var37.setChargorId3(var45.getId().intValue());
                     this.jobService.add(var37);
                     Iterator var28 = var39.iterator();

                     while(var28.hasNext()) {
                        ScheduleParameter var47 = (ScheduleParameter)var28.next();
                        this.scheduleParameterService.insertParameter(var47);
                     }

                     GroupJobRefer var48 = new GroupJobRefer();
                     var48.setJobId(var37.getJobId());
                     var48.setGroupId(groupId);
                     var48.setCreateDate(new Date());
                     var48.setUpdateDate(new Date());
                     this.groupJobReferMapper.add(var48);
                  }
               }
            }
         }
      }

      DynamicDataSourceContextHolder.setDataSourceType("default");
      return var29.getGroupId();
   }
}
