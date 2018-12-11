package com.meicloud.dao;

import java.util.List;

public interface JobExcelMapper {

   List jobList();

   List chargorOptionList();

   List groupOptionList();

   List retryRuleOptionList();

   List checkBatchCUD(String[] var1) throws Exception;

   void addJobBatch(List var1);

   void updateJobBatch(List var1);

   void deleteJobBatch(String[] var1);

   void copy2CHLog(String[] var1);
}
