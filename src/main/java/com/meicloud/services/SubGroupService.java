package com.meicloud.services;

import com.meicloud.model.QueryParam;
import com.meicloud.model.SubGroup;
import java.util.List;

public interface SubGroupService {

   List getCandidateList(SubGroup var1) throws Exception;

   List getCandidateList(SubGroup var1, boolean var2, boolean var3, boolean var4) throws Exception;

   boolean checkIfReferedJobsFinish(QueryParam var1) throws Exception;

   int updateReferPassSubGroup(SubGroup var1) throws Exception;

   boolean checkIfDummyGroupAllJobsSuccess(int var1) throws Exception;

   void deleteGarbageRunJob(int var1) throws Exception;

   List getGarbageRunJob() throws Exception;

   List getReferedGroupJobStateList();
}
