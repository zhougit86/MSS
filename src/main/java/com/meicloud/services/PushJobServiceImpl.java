package com.meicloud.services;

import com.meicloud.dao.GroupJobReferMapper;
import com.meicloud.dao.GroupMapper;
import com.meicloud.dao.GroupReferMapper;
import com.meicloud.dao.RunGroupMapper;
import com.meicloud.dao.RunJobMapper;
import com.meicloud.model.Group;
import com.meicloud.model.GroupJobRefer;
import com.meicloud.model.GroupRefer;
import com.meicloud.model.JobPusherParam;
import com.meicloud.model.RunGroup;
import com.meicloud.services.JobCheckAndUpdateService;
import com.meicloud.services.PushJobService;
import com.meicloud.services.ServerService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service("pushJobService")
@Scope("prototype")
public class PushJobServiceImpl implements PushJobService {

   private static Logger LOG = Logger.getLogger(PushJobServiceImpl.class);
   @Autowired
   private GroupReferMapper groupReferMapper;
   @Autowired
   private RunJobMapper runJobMapper;
   @Autowired
   private GroupMapper groupMapper;
   @Autowired
   private GroupJobReferMapper groupJobReferMapper;
   @Autowired
   private JobCheckAndUpdateService jobCheckAndUpdateService;
   @Autowired
   private RunGroupMapper runGroupMapper;
   @Autowired
   private Environment environment;
   @Autowired
   private ServerService serverService;


   public Set computeRecusive(int endPointGroupId, List allGroupReferList) {
      HashSet resultSet = new HashSet();
      ArrayList endPointLineList = new ArrayList();
      Iterator var6 = allGroupReferList.iterator();

      GroupRefer gr;
      while(var6.hasNext()) {
         gr = (GroupRefer)var6.next();
         if(gr.getGroupId() == endPointGroupId) {
            endPointLineList.add(gr);
            resultSet.add(Integer.valueOf(gr.getReferedGroupId()));
         }
      }

      var6 = endPointLineList.iterator();

      while(var6.hasNext()) {
         gr = (GroupRefer)var6.next();
         int newEndPointGroupId = gr.getReferedGroupId();
         Set childResultSet = this.computeRecusive(newEndPointGroupId, allGroupReferList);
         resultSet.addAll(childResultSet);
      }

      return resultSet;
   }

   public String getReferedGroupIds(int endPointGroupId) throws Exception {
      String result = "";

      try {
         List e = this.groupReferMapper.getAll();
         Set referedGroupIdSet = this.computeRecusive(endPointGroupId, e);
         char splitChar = 44;
         StringBuffer sb = new StringBuffer();
         Iterator lastIndex = referedGroupIdSet.iterator();

         while(lastIndex.hasNext()) {
            Integer len = (Integer)lastIndex.next();
            sb.append(len);
            sb.append(splitChar);
         }

         int len1 = sb.length();
         int lastIndex1 = sb.lastIndexOf(String.valueOf(splitChar));
         if(len1 > 0 && len1 == lastIndex1 + 1) {
            result = sb.toString().substring(0, lastIndex1);
         } else {
            result = sb.toString();
         }

         return result;
      } catch (Exception var9) {
         LOG.error(var9.getMessage());
         throw var9;
      }
   }

   public String getDirectReferedGroupIds(int groupId) {
      List groupRefers = this.groupReferMapper.getReferedList(groupId);
      char splitChar = 44;
      StringBuffer sb = new StringBuffer();
      int size = groupRefers.size();

      for(int i = 0; i < size; ++i) {
         sb.append(((GroupRefer)groupRefers.get(i)).getReferedGroupId());
         if(i != size - 1) {
            sb.append(splitChar);
         }
      }

      return sb.toString();
   }

   private String getReferedJobIds(int groupId) {
      String result = "";
      List jobRefers = this.groupJobReferMapper.getByGroupId(groupId);
      if(jobRefers != null && jobRefers.size() > 0) {
         StringBuffer sb = new StringBuffer();
         int size = jobRefers.size();

         for(int i = 0; i < size; ++i) {
            sb.append(((GroupJobRefer)jobRefers.get(i)).getJobId());
            if(i != size - 1) {
               sb.append(',');
            }
         }

         result = sb.toString();
      }

      return result;
   }

   public void pushJob(int groupId, int pushType) throws Exception {
      try {
         if(pushType == 0) {
            //把自己这个任务的所有job放到running job map当中等待调度
            this.pushCurrentGroup(groupId, 0);
            //通过深度优先的方式递归找出所有要被调度起来的任务
            List e = this.complete(groupId, new ArrayList());
            LOG.info("forwardActiveAndEvenTriggerGroupIds:" + e);
            int successForwardGroupCount = 0;
            if(e != null && e.size() > 0) {
               for(Iterator var6 = e.iterator(); var6.hasNext(); ++successForwardGroupCount) {
                  int currentGroupId = ((Integer)var6.next()).intValue();

                  try {
                     this.pushCurrentGroup(currentGroupId, 1);
                  } catch (Exception var8) {
                     LOG.error(var8);
                  }
               }
            }

            LOG.info("Cron GroupId [" + groupId + "] forwardActiveAndEvenTrigger GroupIds " + e.toString() + " Success Count [" + successForwardGroupCount + "]");
         } else {
            this.pushCurrentGroup(groupId, 2);
         }
      } catch (Exception var9) {
         LOG.error(var9);
      }

   }

   private List complete(int groupId, List forwardActiveAndEvenTriggerGroupIds) throws Exception {
      GroupRefer groupRefer = new GroupRefer();
      groupRefer.setGroupId(groupId);
      //找到依赖我这个人的列表
      List groupRefers = this.groupReferMapper.getPostList(groupId);
      if(groupRefers != null && groupRefers.size() > 0) {
         Iterator var6 = groupRefers.iterator();

         while(var6.hasNext()) {
            GroupRefer refer = (GroupRefer)var6.next();
            //找到所有依赖我的group
            Group groupDB = this.groupMapper.getById(refer.getGroupId());
            //是否依赖触发
            if(groupDB != null && !groupDB.isTimeTrigger()) {
               if(forwardActiveAndEvenTriggerGroupIds == null) {
                  forwardActiveAndEvenTriggerGroupIds = new ArrayList();
               }

               ((List)forwardActiveAndEvenTriggerGroupIds).add(Integer.valueOf(groupDB.getGroupId()));
               this.complete(groupDB.getGroupId(), (List)forwardActiveAndEvenTriggerGroupIds);
            }
         }
      }

      return (List)forwardActiveAndEvenTriggerGroupIds;
   }

   private void pushCurrentGroup(int groupId, int pushType) {
      JobPusherParam jobPusherParam = new JobPusherParam();
      jobPusherParam.setGroupId(groupId);
      //去表里查，当前如果有这个group的job正在运行则不调度这次任务
      //如果说是手动调起来的话就不用care当前是否有job在run，直接顶掉
      if(pushType != 2) {
         boolean referedGroupIds = this.runJobMapper.hasRunningJobsInRunTime(jobPusherParam);
         if(referedGroupIds) {
            LOG.info("there is some jobs in running state of the group[" + groupId + "] so dont need to push job to runtime this round.");
            return;
         }
      }

      try {
         int referedGroupIds1 = (int)(Math.random() * 100.0D);
         Thread.sleep((long)referedGroupIds1);
      } catch (Exception var10) {
         LOG.info(var10);
      }

      String referedGroupIds2 = "";
      String referedJobIds = "";
      //这个2到底代表什么意思，这两个函数就是把依赖的groupId和jobId取出来，然后用,分割
      //如果手动调也不会去看我依赖的某些任务
      if(pushType != 2) {
         referedGroupIds2 = this.getDirectReferedGroupIds(groupId);
         referedJobIds = this.getReferedJobIds(groupId);
      }

      jobPusherParam.setReferedGroupIds(referedGroupIds2);
      jobPusherParam.setReferedJobIds(referedJobIds);
      jobPusherParam.setPushType(pushType);
      //把当前运行的这个job放到history当中去
      this.runGroupMapper.copy2His(groupId);
      //把原来的running job删除掉
      this.runGroupMapper.delete(groupId);
      //把job从cm_group放到cm_run_group里面
      this.runGroupMapper.addByGroup(jobPusherParam);
      RunGroup runGroup = this.runGroupMapper.getRunGroupByGroupId(groupId);
      int runGroupId = runGroup == null?0:runGroup.getRunGroupId();
      if(runGroupId == 0) {
         LOG.info("push group [" + groupId + "] to runtime failure.");
      } else {
         LOG.info("finish push group [" + groupId + "] rungroupId [" + runGroupId + "] and about to push job.");
         jobPusherParam.setRunGroupId(runGroupId);
         //三种任务触发方式，0：cron，1：依赖触发 2：手工触发
         if(pushType == 0) {
            jobPusherParam.setMsg("PUSH FROM CM_JOB BY SYSTEM CRON.");
         } else if(pushType == 1) {
            jobPusherParam.setMsg("PUSH FROM CM_JOB BY EvenTrigger.");
         } else {
            jobPusherParam.setMsg("PUSH FROM CM_JOB BY Handle.");
         }

         LOG.info("jobPusherParam:" + jobPusherParam);
         this.runJobMapper.copy2HisByGroupId(runGroup.getGroupId());
         this.runJobMapper.deleteByGroupId(runGroup.getGroupId());
         this.runJobMapper.addByGroup(jobPusherParam);
         //将Group相关的Job状态都刷新
         this.runJobMapper.updateJobStateBySatrt(jobPusherParam);
         int pushJobCount = this.runJobMapper.getRunJobCountByGroupId(runGroupId).intValue();
         String msg = "finish push GROUP groupId:[" + groupId + "] runGroupId:[" + runGroupId + "] referedGroupIds:[" + referedGroupIds2 + "] pushJobCount:[" + pushJobCount + "] to runtime pushType [" + pushType + "]";
         LOG.info(msg);
      }
   }
}
