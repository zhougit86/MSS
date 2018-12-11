package com.meicloud.services;

import com.meicloud.dao.JobExcelMapper;
import com.meicloud.model.Job;
import com.meicloud.services.JobExcelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jobExcelService")
public class JobExcelServiceImpl implements JobExcelService {

   @Autowired
   private JobExcelMapper jobExcelMapper;


   public List jobList() throws Exception {
      try {
         return this.jobExcelMapper.jobList();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List chargorOptionList() throws Exception {
      try {
         return this.jobExcelMapper.chargorOptionList();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List groupOptionList() throws Exception {
      try {
         return this.jobExcelMapper.groupOptionList();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List retryRuleOptionList() throws Exception {
      try {
         return this.jobExcelMapper.retryRuleOptionList();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List checkBatchCUD(String[] jobNameArray) throws Exception {
      try {
         return this.jobExcelMapper.checkBatchCUD(jobNameArray);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void addJobBatch(List jobList) throws Exception {
      try {
         this.jobExcelMapper.addJobBatch(jobList);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void updateJobBatch(List jobList) throws Exception {
      try {
         StringBuffer e = new StringBuffer();

         for(int jobNameList = 0; jobNameList < jobList.size(); ++jobNameList) {
            e.append(((Job)jobList.get(jobNameList)).getJobName());
            e.append(",");
         }

         String[] var5 = e.substring(0, e.length() - 1).split(",");
         this.jobExcelMapper.copy2CHLog(var5);
         this.jobExcelMapper.updateJobBatch(jobList);
      } catch (Exception var4) {
         throw var4;
      }
   }

   public void deleteJobBatch(String[] jobNameList) throws Exception {
      try {
         this.jobExcelMapper.copy2CHLog(jobNameList);
         this.jobExcelMapper.deleteJobBatch(jobNameList);
      } catch (Exception var3) {
         throw var3;
      }
   }
}
