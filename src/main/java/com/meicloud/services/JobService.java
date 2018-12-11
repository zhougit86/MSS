package com.meicloud.services;

import com.meicloud.model.Job;
import com.meicloud.model.RunJob;
import java.util.List;

public interface JobService {

   void add(Job var1) throws Exception;

   void update(Job var1) throws Exception;

   Job getJobById(int var1) throws Exception;

   Job getJobByJobName(String var1) throws Exception;

   List getList4RunPage(int var1, String var2, String var3, boolean var4, int var5) throws Exception;

   List getList4EditPage(int var1, String var2, int var3) throws Exception;

   List getList4RunPageHeader(int var1, int var2) throws Exception;

   List getListByJobIds(String var1) throws Exception;

   int checkIfExists4New(Job var1) throws Exception;

   int checkIfExists4Update(Job var1) throws Exception;

   boolean deleteById(int var1, String var2, String var3);

   boolean deleteByGroupId(int var1);

   void setOrderNo(String var1) throws Exception;

   void updateState(int var1, String var2, String var3) throws Exception;

   void online(int var1, String var2, String var3) throws Exception;

   List getChargorList();

   void cancelOrder(int var1) throws Exception;

   List jobHisList(int var1) throws Exception;

   RunJob formRunJobByJobId(int var1) throws Exception;

   List getDummyGroupState(int var1);

   Job checkJobByName(String var1);

   List findJobByRetryRuleId(int var1);

   void addJobState(Job var1);

   int getJobStateCountByJobId(int var1);
}
