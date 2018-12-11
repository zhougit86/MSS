package com.meicloud.services;

import java.util.List;

public interface JobExcelService {

   List jobList() throws Exception;

   List chargorOptionList() throws Exception;

   List groupOptionList() throws Exception;

   List retryRuleOptionList() throws Exception;

   List checkBatchCUD(String[] var1) throws Exception;

   void addJobBatch(List var1) throws Exception;

   void updateJobBatch(List var1) throws Exception;

   void deleteJobBatch(String[] var1) throws Exception;
}
