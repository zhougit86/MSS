package com.meicloud.worker;

import com.meicloud.dao.GroupJobReferMapper;
import com.meicloud.dao.GroupMapper;
import com.meicloud.dao.GroupReferMapper;
import com.meicloud.dao.RunJobMapper;
import com.meicloud.model.Group;
import com.meicloud.model.GroupRefer;
import com.meicloud.model.QueryParam;
import com.meicloud.model.RunJob;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBTool {

   @Autowired
   private RunJobMapper runJobMapper;
   @Autowired
   private GroupReferMapper groupReferMapper;
   @Autowired
   private GroupMapper groupMapper;
   @Autowired
   private GroupJobReferMapper groupJobReferMapper;


   public List canditateWaitingList() throws Exception {
      List result = null;

      try {
         QueryParam e = new QueryParam();
         e.setState(1);
         result = this.runJobMapper.getWaitingRunJobList(e);
         return result;
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getGroupParalle(QueryParam queryParam) throws Exception {
      try {
         return this.runJobMapper.getGroupParalle(queryParam);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getGroupReferByGroupId(GroupRefer groupRefer) throws Exception {
      try {
         return this.groupReferMapper.getGroupReferByGroupId(groupRefer);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getJobReferByGroupId(int groupId) throws Exception {
      try {
         return this.groupJobReferMapper.getByGroupId(groupId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public boolean completeByGroupId(Integer groupId) {
      List runJobs = this.runJobMapper.getCompleteRunJobListByGroupId(groupId);
      Group group = this.groupMapper.getById(groupId.intValue());
      if(group.isEnable() && runJobs != null && runJobs.size() > 0) {
         Iterator var5 = runJobs.iterator();

         while(var5.hasNext()) {
            RunJob runJob = (RunJob)var5.next();
            if(runJob.getState() != 5 && runJob.getState() != 4) {
               return false;
            }
         }
      }

      return true;
   }

   public boolean completeByJobId(Integer jobId) {
      RunJob runJob = this.runJobMapper.getCompleteRunJobListByJobId(jobId);
      return runJob == null || runJob.getState() == 5 || runJob.getState() == 4;
   }

   public void updateSendedList(List runJobIds) throws Exception {
      try {
         this.runJobMapper.updateListLatestSendDate(runJobIds);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public Group getById(int groupId) throws Exception {
      try {
         return this.groupMapper.getById(groupId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void updateUnSendedList(List runJobIds) throws Exception {
      try {
         this.runJobMapper.updateRejectListLatestSendDate(runJobIds);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void updateListLatestSendDateIsNull(List runJobIds) throws Exception {
      try {
         this.runJobMapper.updateListLatestSendDateIsNull(runJobIds);
      } catch (Exception var3) {
         throw var3;
      }
   }
}
