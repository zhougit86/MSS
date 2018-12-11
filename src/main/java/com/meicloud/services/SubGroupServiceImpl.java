package com.meicloud.services;

import com.meicloud.dao.GroupMapper;
import com.meicloud.dao.RunJobMapper;
import com.meicloud.dao.SubGroupMapper;
import com.meicloud.model.QueryParam;
import com.meicloud.model.SubGroup;
import com.meicloud.services.SubGroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubGroupServiceImpl implements SubGroupService {

   @Autowired
   private SubGroupMapper subGroupMapper;
   @Autowired
   private RunJobMapper runJobMapper;
   @Autowired
   private GroupMapper groupMapper;


   public List getCandidateList(SubGroup query, boolean hasGroupRefer, boolean hasJobRefer, boolean sordInGroup) throws Exception {
      List result = null;

      try {
         if(!hasGroupRefer && !hasJobRefer && !sordInGroup) {
            result = this.subGroupMapper.noGroupReferNoJobReferNoOrderSubGroupCandidateList(query);
         } else if(!hasGroupRefer && !hasJobRefer && sordInGroup) {
            result = this.subGroupMapper.noGroupReferNoJobReferOrderSubGroupCandidateList(query);
         } else if(hasGroupRefer && !hasJobRefer && !sordInGroup) {
            result = this.subGroupMapper.groupReferNoJobReferNoOrderSubGroupCandidateList(query);
         } else if(hasGroupRefer && !hasJobRefer && sordInGroup) {
            result = this.subGroupMapper.groupReferNoJobReferOrderSubGroupCandidateList(query);
         } else if(!hasGroupRefer && hasJobRefer && !sordInGroup) {
            result = this.subGroupMapper.noGroupReferJobReferNoOrderSubGroupCandidateList(query);
         } else if(!hasGroupRefer && hasJobRefer && sordInGroup) {
            result = this.subGroupMapper.noGroupReferJobReferOrderSubGroupCandidateList(query);
         } else if(hasGroupRefer && hasJobRefer && !sordInGroup) {
            result = this.subGroupMapper.groupReferJobReferNoOrderSubGroupCandidateList(query);
         } else if(hasGroupRefer && hasJobRefer && sordInGroup) {
            result = this.subGroupMapper.groupReferJobReferOrderSubGroupCandidateList(query);
         }

         return result;
      } catch (Exception var7) {
         throw var7;
      }
   }

   public List getCandidateList(SubGroup query) throws Exception {
      List result = null;

      try {
         result = this.subGroupMapper.getCandidateList(query);
         return result;
      } catch (Exception var4) {
         throw var4;
      }
   }

   public boolean checkIfReferedJobsFinish(QueryParam query) throws Exception {
      try {
         int e = this.subGroupMapper.prevActiveJobCount(query.getGroupId());
         int count2 = this.subGroupMapper.succssOrCancelJobCount(query);
         return e <= count2;
      } catch (Exception var4) {
         throw var4;
      }
   }

   public int updateReferPassSubGroup(SubGroup query) throws Exception {
      boolean result = false;

      try {
         int result1 = this.subGroupMapper.updateReferPassSubGroup(query);
         this.subGroupMapper.updateLatestOrderNo(query);
         return result1;
      } catch (Exception var4) {
         throw var4;
      }
   }

   public boolean checkIfDummyGroupAllJobsSuccess(int groupId) throws Exception {
      try {
         return this.runJobMapper.getDummyGroupUnFinishedSuccessJobCount(groupId) < 1;
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void deleteGarbageRunJob(int jobId) throws Exception {
      this.runJobMapper.deleteGarbageRunJob(Integer.valueOf(jobId));
   }

   public List getGarbageRunJob() throws Exception {
      return this.runJobMapper.getGarbageRunJob();
   }

   public List getReferedGroupJobStateList() {
      return this.subGroupMapper.getReferedGroupJobStateList();
   }
}
