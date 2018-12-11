package com.meicloud.services;

import com.meicloud.dao.GroupLevelMapper;
import com.meicloud.dao.GroupMapper;
import com.meicloud.dao.JobExecStatusMapper;
import com.meicloud.dao.TopicMapper;
import com.meicloud.model.Group;
import com.meicloud.model.JobsStatByGroup;
import com.meicloud.model.Proportion;
import com.meicloud.services.IndexService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("indexService")
public class IndexServiceImpl implements IndexService {

   @Autowired
   private GroupMapper groupMapper;
   @Autowired
   private GroupLevelMapper groupLevelMapper;
   @Autowired
   private JobExecStatusMapper jobExecStatusMapper;
   @Autowired
   private TopicMapper topicMapper;


   public List getTopicList() throws Exception {
      try {
         return this.topicMapper.topicList();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List topicListByQueueId(String queueId) throws Exception {
      try {
         return this.topicMapper.topicListByQueueId(queueId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List topicListByQueueIds(List queueIds) throws Exception {
      return this.topicMapper.topicListByQueueIds(queueIds);
   }

   public List getLevelNot0() throws Exception {
      try {
         return this.groupLevelMapper.getLevelNot0();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List getLevelNot0ByQueueId(String queueId) throws Exception {
      try {
         return this.groupLevelMapper.getGroupLevelListByQueueId(queueId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getLevelNot0ByQueueIds(List queueIds) throws Exception {
      return this.groupLevelMapper.getLevelNot0ByQueueIds(queueIds);
   }

   public List getGroupByLeverAndRefer(int levelId, int topicId) throws Exception {
      try {
         Group e = new Group();
         e.setLevelId(levelId);
         e.setTopicId(topicId);
         return this.groupMapper.getGroupByLeverAndRefer(e);
      } catch (Exception var4) {
         throw var4;
      }
   }

   public Map listToMap(List groupStatus) {
      LinkedHashMap groupStatusMap = new LinkedHashMap();
      int flag = -1;
      ArrayList list = new ArrayList();

      JobsStatByGroup jobsStatByGroup;
      for(Iterator var6 = groupStatus.iterator(); var6.hasNext(); flag = jobsStatByGroup.getGroupId()) {
         jobsStatByGroup = (JobsStatByGroup)var6.next();
         if(flag != jobsStatByGroup.getGroupId()) {
            list = new ArrayList();
            groupStatusMap.put(Integer.valueOf(jobsStatByGroup.getGroupId()), list);
         }

         list.add(jobsStatByGroup);
      }

      return groupStatusMap;
   }

   public List proportionsLatestRound(List groupIdList, boolean isHistory) throws Exception {
      ArrayList result = new ArrayList();

      try {
         String e = groupIdList.toString().substring(1, groupIdList.toString().length() - 1);
         String[] groupIdArray = e.split(",");
         Object groupStatus = new ArrayList();
         if(isHistory && e.length() > 0) {
            groupStatus = this.jobExecStatusMapper.getStatOfLatestRoundHis(groupIdArray);
         } else if(e.length() > 0) {
            groupStatus = this.jobExecStatusMapper.getJobCountOfEachStateByGroup4Today(groupIdArray);
         }

         Map groupStatusMap = this.listToMap((List)groupStatus);
         Iterator var9 = groupStatusMap.entrySet().iterator();

         while(var9.hasNext()) {
            Entry entry = (Entry)var9.next();
            int total = 0;
            int groupId = ((Integer)entry.getKey()).intValue();
            Proportion prop = new Proportion();
            prop.setGroupId(groupId);

            int jobCount;
            for(Iterator var14 = ((List)entry.getValue()).iterator(); var14.hasNext(); total += jobCount) {
               JobsStatByGroup jobsStatByGroup = (JobsStatByGroup)var14.next();
               int state = jobsStatByGroup.getState();
               jobCount = jobsStatByGroup.getJobCount();
               if(state != 0 && 1 != state) {
                  if(2 == state) {
                     prop.setRunning(jobCount);
                  } else if(3 == state) {
                     prop.setError(jobCount);
                  } else if(5 == state) {
                     prop.setSuccess(jobCount);
                  } else if(-1 == state || 4 == state) {
                     prop.setForbidden(prop.getForbidden() + jobCount);
                  }
               } else {
                  prop.setWaiting(prop.getWaiting() + jobCount);
               }
            }

            prop.setTotal(total);
            result.add(prop);
         }

         return result;
      } catch (Exception var17) {
         throw var17;
      }
   }

   public Proportion proportionsLatestRound(Integer groupId, boolean isHistory) throws Exception {
      ArrayList groupIdList = new ArrayList();
      groupIdList.add(groupId);
      List list = this.proportionsLatestRound((List)groupIdList, isHistory);
      return list != null && list.size() > 0?(Proportion)list.get(0):null;
   }

   public int getRunServers(String queueId) throws Exception {
      try {
         return this.jobExecStatusMapper.getRunServers(queueId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getJobStateCount() throws Exception {
      try {
         return this.jobExecStatusMapper.getJobStateCount();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List selectLevelByTopicId(Integer topicId) {
      return this.groupLevelMapper.selectByTopicId(topicId);
   }
}
