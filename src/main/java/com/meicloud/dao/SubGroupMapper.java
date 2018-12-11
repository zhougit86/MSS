package com.meicloud.dao;

import com.meicloud.model.QueryParam;
import com.meicloud.model.SubGroup;
import java.util.List;

public interface SubGroupMapper {

   List getCandidateList(SubGroup var1);

   List noGroupReferNoJobReferNoOrderSubGroupCandidateList(SubGroup var1);

   List noGroupReferNoJobReferOrderSubGroupCandidateList(SubGroup var1);

   List groupReferNoJobReferNoOrderSubGroupCandidateList(SubGroup var1);

   List groupReferNoJobReferOrderSubGroupCandidateList(SubGroup var1);

   List noGroupReferJobReferNoOrderSubGroupCandidateList(SubGroup var1);

   List noGroupReferJobReferOrderSubGroupCandidateList(SubGroup var1);

   List groupReferJobReferNoOrderSubGroupCandidateList(SubGroup var1);

   List groupReferJobReferOrderSubGroupCandidateList(SubGroup var1);

   int prevActiveJobCount(int var1);

   int succssOrCancelJobCount(QueryParam var1);

   int updateReferPassSubGroup(SubGroup var1);

   int updateLatestOrderNo(SubGroup var1);

   List getReferedGroupJobStateList();
}
