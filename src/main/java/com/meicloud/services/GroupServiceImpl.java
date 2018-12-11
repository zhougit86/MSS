package com.meicloud.services;

import com.meicloud.dao.GroupJobReferMapper;
import com.meicloud.dao.GroupLevelMapper;
import com.meicloud.dao.GroupMapper;
import com.meicloud.dao.GroupReferMapper;
import com.meicloud.dao.JobLogMapper;
import com.meicloud.dao.RunGroupMapper;
import com.meicloud.dao.RunJobMapper;
import com.meicloud.dto.CommonVO;
import com.meicloud.model.Group;
import com.meicloud.model.GroupJobRefer;
import com.meicloud.model.GroupLevel;
import com.meicloud.model.GroupRefer;
import com.meicloud.services.GroupService;
import com.meicloud.services.JobService;
import com.meicloud.services.PushJobQuartzManager;
import com.meicloud.services.RoleGroupService;
import com.meicloud.utils.StringUtil;
import com.meicloud.utils.Utils;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

   private static Logger LOG = Logger.getLogger("GroupServiceImpl");
   @Autowired
   private GroupMapper groupMapper;
   @Autowired
   private GroupLevelMapper groupLevelMapper;
   @Autowired
   private GroupReferMapper groupReferMapper;
   @Autowired
   private GroupJobReferMapper groupJobReferMapper;
   @Autowired
   private PushJobQuartzManager pushJobQuartzManager;
   @Autowired
   private RoleGroupService roleGroupService;
   @Autowired
   private RunJobMapper runJobMapper;
   @Autowired
   private RunGroupMapper runGroupMapper;
   @Autowired
   private JobLogMapper jobLogMapper;
   @Autowired
   private JobService jobService;


   public boolean checkIfExist(Group group, boolean isNew) throws Exception {
      boolean exists = false;

      try {
         if(isNew) {
            exists = this.groupMapper.checkIfExists4New(group) > 0;
         } else {
            exists = this.groupMapper.checkIfExists4Update(group) > 0;
         }

         return exists;
      } catch (Exception var5) {
         throw var5;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void add(Group group) throws Exception {
      try {
         this.groupMapper.add(group);
         String e = group.getReferedGroupIds();
         if(!StringUtil.isBlank(e)) {
            String[] referedJobIds = e.split("@");
            String[] var7 = referedJobIds;
            int jobId = referedJobIds.length;

            for(int ids = 0; ids < jobId; ++ids) {
               String groupId = var7[ids];
               if(StringUtil.isNumeric(groupId)) {
                  GroupRefer groupRefer = new GroupRefer();
                  groupRefer.setGroupId(group.getGroupId());
                  groupRefer.setReferedGroupId(Integer.parseInt(groupId));
                  this.groupReferMapper.add(groupRefer);
               }
            }
         }

         String var12 = group.getReferedJobIds();
         int var13 = group.getGroupId();
         if(!StringUtil.isBlank(var12)) {
            String[] var14 = var12.split("@");
            String[] var9 = var14;
            int var17 = var14.length;

            for(int var16 = 0; var16 < var17; ++var16) {
               String var15 = var9[var16];
               if(StringUtil.isNumeric(var15)) {
                  GroupJobRefer groupJobRefer = new GroupJobRefer(var15, var13);
                  this.groupJobReferMapper.add(groupJobRefer);
               }
            }
         }

         this.pushJobQuartzManager.addCronJob(group);
      } catch (Exception var11) {
         LOG.error(var11.getMessage());
         throw var11;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void update(Group group) throws Exception {
      try {
         Group e = this.groupMapper.getById(group.getGroupId());
         this.pushJobQuartzManager.removeJob(e);
         this.groupMapper.addChlog(group);
         this.groupMapper.update(group);
         this.groupReferMapper.deleteByGroupId(group.getGroupId());
         String referedGroupIds = group.getReferedGroupIds();
         String referedJobIds;
         if(referedGroupIds != null && !"".equals(referedGroupIds.trim())) {
            String[] groupId = referedGroupIds.split("@");
            String[] var8 = groupId;
            int jobId = groupId.length;

            for(int ids = 0; ids < jobId; ++ids) {
               referedJobIds = var8[ids];
               if(StringUtil.isNumeric(referedJobIds)) {
                  GroupRefer groupRefer = new GroupRefer();
                  groupRefer.setGroupId(group.getGroupId());
                  groupRefer.setReferedGroupId(Integer.parseInt(referedJobIds));
                  this.groupReferMapper.add(groupRefer);
               }
            }
         }

         int var13 = group.getGroupId();
         this.groupJobReferMapper.deleteByGroupId(var13);
         referedJobIds = group.getReferedJobIds();
         if(!StringUtil.isBlank(referedJobIds)) {
            String[] var14 = referedJobIds.split("@");
            String[] var10 = var14;
            int var17 = var14.length;

            for(int var16 = 0; var16 < var17; ++var16) {
               String var15 = var10[var16];
               if(StringUtil.isNumeric(var15)) {
                  GroupJobRefer groupJobRefer = new GroupJobRefer(var15, var13);
                  this.groupJobReferMapper.add(groupJobRefer);
               }
            }
         }

         this.pushJobQuartzManager.addCronJob(group);
      } catch (Exception var12) {
         throw var12;
      }
   }

   public List getGroupEditJobReferOption(int groupId) throws Exception {
      try {
         return this.groupJobReferMapper.getGroupEditReferOption(groupId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateState(int groupId, String account, String logReson) throws Exception {
      try {
         Group e = this.groupMapper.getById(groupId);
         if(e == null) {
            System.out.println("组已经不存在了.");
         } else {
            e.setEnable(!e.isEnable());
            e.setuUser(account);
            e.setLogReson(logReson);
            this.groupMapper.addChlog(e);
            this.groupMapper.update(e);
         }
      } catch (Exception var5) {
         LOG.error(var5.getMessage());
         throw var5;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public boolean delete(int groupId, String logReson, String account) throws Exception {
      try {
         Group e = this.groupMapper.getById(groupId);
         if(e != null) {
            this.pushJobQuartzManager.removeJob(e);
         } else {
            e = new Group();
         }

         e.setLogReson(logReson);
         e.setuUser(account);
         this.groupMapper.addChlog(e);
         this.groupMapper.deleteGroupReferByGroupId(groupId);
         this.groupReferMapper.deleteByGroupId(groupId);
         this.groupReferMapper.deleteByReferedGroupId(groupId);
         this.groupMapper.delete(groupId);
         this.runGroupMapper.getRunGroupByGroupId(groupId);
         this.jobService.deleteByGroupId(groupId);
         return true;
      } catch (Exception var5) {
         LOG.error(var5.getMessage());
         throw var5;
      }
   }

   public Group getById(int groupId) throws Exception {
      try {
         return this.groupMapper.getById(groupId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public Group getByGroupName(String groupName) {
      return this.groupMapper.getByGroupName(groupName);
   }

   public List getList(String groupName, String queueId, int topicId, int levelId, boolean online, List topics, List groupIds) throws Exception {
      List result = null;

      try {
         Group e = new Group();
         e.setGroupName(!Utils.isEmptyStr(groupName)?groupName:null);
         e.setQueueId(!Utils.isEmptyStr(queueId)?queueId:null);
         e.setTopicId(topicId);
         e.setLevelId(levelId);
         e.setOnline(online);
         e.setTopicList(topics);
         e.setGroupIds(groupIds);
         result = this.groupMapper.getList(e);
         return result;
      } catch (Exception var10) {
         throw var10;
      }
   }

   public List getListByQuequeId(String queueId) throws Exception {
      Group group = new Group();
      group.setQueueId(queueId);
      return this.groupMapper.getList(group);
   }

   public List getList(int tagsId) throws Exception {
      List result = null;

      try {
         result = this.groupMapper.getListByTagsId(tagsId);
         return result;
      } catch (Exception var4) {
         throw var4;
      }
   }

   public List getNavCheckList() throws Exception {
      List result = null;

      try {
         result = this.groupMapper.getNavCheckList();
         return result;
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getGroupLevelList() throws Exception {
      try {
         return this.groupLevelMapper.getList();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public GroupLevel getGroupLevelByGroupId(Integer levelId) throws Exception {
      return this.groupLevelMapper.getLevelById(levelId);
   }

   public List getGroupLevelListByQueueId(String queueId) throws Exception {
      try {
         return this.groupLevelMapper.getGroupLevelListByQueueId(queueId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List findListByGroupIds(CommonVO commonVO) {
      return this.groupMapper.findListByGroupIds(commonVO);
   }

   public List getGroupJobReferList(int groupId) throws Exception {
      try {
         return this.groupJobReferMapper.getByGroupId(groupId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getGroupReferOptions(Group group) throws Exception {
      try {
         return this.groupMapper.getGroupReferOptions(group);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getRefGroupById(int groupId) {
      try {
         return this.groupMapper.getRefGroupById(groupId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getGroupRefById(int groupId) {
      try {
         return this.groupMapper.getGroupRefById(groupId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getHistoryList(int groupId) throws Exception {
      try {
         return this.groupMapper.getHistoryList(groupId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getGroupsByState(int state, String queueId) throws Exception {
      try {
         return this.groupMapper.getGroupsByState(state, !Utils.isEmptyStr(queueId)?queueId:null);
      } catch (Exception var4) {
         throw var4;
      }
   }

   public String getGroupByState(int groupId, int state) throws Exception {
      try {
         return this.groupMapper.getGroupByState(groupId, state);
      } catch (Exception var4) {
         throw var4;
      }
   }

   public Group groupExectime(int groupId) throws Exception {
      try {
         return this.groupMapper.groupExectime(groupId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getAllGroup() throws Exception {
      try {
         return this.groupMapper.getAllGroup();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List getAllEnableGroup() throws Exception {
      try {
         return this.groupMapper.getAllEnableGroup();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List getAllEnableGroupByCron(String cron) throws Exception {
      try {
         return this.groupMapper.getAllEnableGroupByCron(cron);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public int getEPCount() throws Exception {
      try {
         return this.groupMapper.getTopicCount();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List getHeaderCount(boolean isEditPage, String queueId) throws Exception {
      try {
         return isEditPage?this.groupMapper.getHeaderCountEditPage(queueId):this.groupMapper.getHeaderCountRunPage(queueId);
      } catch (Exception var4) {
         throw var4;
      }
   }

   public void setNextFireDateAndQueueName(List groupList, HttpServletRequest request) {
      Map groupMap = this.roleGroupService.findHandleList((String)null, request);
      Date now = new Date(System.currentTimeMillis());
      Iterator var6 = groupList.iterator();

      while(var6.hasNext()) {
         Group g = (Group)var6.next();
         if(g != null) {
            if(groupMap.containsKey(Integer.valueOf(g.getGroupId()))) {
               g.setHandStatus("1");
            } else {
               g.setHandStatus("0");
            }

            try {
               if(g.getNextFireTime() == null) {
                  String e = g.getCron();
                  if(!StringUtil.isBlank(e)) {
                     CronExpression cronExpression = new CronExpression(e);
                     Date fireTime = cronExpression.getNextValidTimeAfter(now);
                     g.setNextFireTime(StringUtil.formatGroupFireTime(fireTime));
                  }
               }
            } catch (Exception var10) {
               LOG.error(var10.getMessage());
            }
         }
      }

   }

   public GroupLevel getGroupLevelById(Integer levelId) throws Exception {
      try {
         return this.groupLevelMapper.getLevelById(levelId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void insertGroupLevel(GroupLevel groupLevel) throws Exception {
      try {
         this.groupLevelMapper.insert(groupLevel);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void updateGroupLevel(GroupLevel groupLevel) throws Exception {
      try {
         this.groupLevelMapper.update(groupLevel);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void deleteGroupLevel(Integer levelId) throws Exception {
      try {
         this.groupLevelMapper.delete(levelId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public boolean checkLevelNameExists(GroupLevel groupLevel) throws Exception {
      boolean b = false;

      try {
         if(groupLevel.getLevelId() != null) {
            GroupLevel e = this.getGroupLevelById(groupLevel.getLevelId());
            if(groupLevel.getLevelName().equals(e.getLevelName())) {
               return b;
            }
         }

         int e1 = this.groupLevelMapper.checkLevelNameExists(groupLevel);
         if(e1 > 0) {
            b = true;
         }

         return b;
      } catch (Exception var4) {
         throw var4;
      }
   }

   public List getListByAllParameters(String state, String groupName, String queueId, int topicId, int levelId, boolean online, List topics, List groupIds) {
      List result = null;

      try {
         Group e = new Group();
         e.setGroupName(!Utils.isEmptyStr(groupName)?groupName:null);
         e.setQueueId(!Utils.isEmptyStr(queueId)?queueId:null);
         e.setTopicId(topicId);
         e.setLevelId(levelId);
         e.setOnline(online);
         e.setTopicList(topics);
         e.setState(!Utils.isEmptyStr(state)?state:null);
         e.setGroupIds(groupIds);
         result = this.groupMapper.getListByAllParameters(e);
         return result;
      } catch (Exception var11) {
         throw var11;
      }
   }

   public void deleteGroupReferByGroupId(int groupId) {
      this.groupMapper.deleteGroupReferByGroupId(groupId);
   }

   public void delete(int groupId) {
      this.groupMapper.delete(groupId);
   }
}
