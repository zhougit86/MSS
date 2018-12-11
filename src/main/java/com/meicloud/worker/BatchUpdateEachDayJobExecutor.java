package com.meicloud.worker;

import com.meicloud.dao.RunJobMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("batchUpdateEachDayJobExecutor")
public class BatchUpdateEachDayJobExecutor {

   private static Logger LOG = Logger.getLogger(BatchUpdateEachDayJobExecutor.class);
   @Autowired
   private RunJobMapper runJobMapper;


   public void doUpdate() {
      try {
         this.runJobMapper.cleanUpJobsState();
         this.runJobMapper.updateJobStateBatch();
      } catch (Exception var2) {
         LOG.error(var2);
      }

   }
}
