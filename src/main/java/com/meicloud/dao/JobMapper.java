package com.meicloud.dao;

import com.meicloud.model.Job;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobMapper {

   void add(Job var1);

   void addJobState(Job var1);

   int checkIfExists4New(Job var1);

   int checkIfExists4Update(Job var1);

   void update(Job var1);

   void updateOrder(Job var1);

   void updateStateByGroupId(Job var1);

   void delete(int var1);

   void deleteByGroupId(int var1);

   Job getById(int var1);

   Job getByJobName(String var1);

   List getListCurrentWindow(@Param("groupId") int var1, @Param("jobName") String var2, @Param("state") String var3, @Param("online") boolean var4, @Param("queueId") int var5);

   List getList4EditPage(@Param("groupId") int var1, @Param("jobName") String var2, @Param("queueId") int var3);

   List getListRunPageHeader_disable(@Param("queueId") int var1);

   List getListRunPageHeader_0_1(@Param("state") int var1, @Param("queueId") int var2);

   List getListRunPageHeader_2(@Param("queueId") int var1);

   List getListRunPageHeader_3_4_5(@Param("state") int var1, @Param("queueId") int var2);

   List getListRunPageHeader_6(@Param("queueId") int var1);

   List getListRunPageHeader_7(@Param("queueId") int var1);

   List getListByJobIds(@Param("jobIds") String var1);

   int deleteById(int var1);

   void deleteStateByJobId(@Param("jobId") int var1);

   void deleteStateByGroupId(@Param("groupId") int var1);

   List getListByGroupIdAndWhere(Job var1);

   void cancelOrder(int var1);

   List jobHisList(@Param("runGroupId") int var1);

   void addChlog(Job var1);

   List getDummyGroupState(int var1);

   Job checkJobByName(String var1);

   List findJobByRetryRuleId(@Param("retryId") int var1);

   int getJobStateCountByJobId(@Param("jobId") int var1);
}
