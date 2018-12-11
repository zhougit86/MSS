package com.meicloud.services;

import com.meicloud.dao.JobTablesMapper;
import com.meicloud.model.JobTables;
import com.meicloud.services.JobTablesService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jobTablesService")
public class JobTablesServiceImpl implements JobTablesService {

   private static Logger LOG = Logger.getLogger(JobTablesServiceImpl.class);
   @Autowired
   private JobTablesMapper jobTablesMapper;


   public void add(JobTables jobTables) {
      try {
         this.jobTablesMapper.add(jobTables);
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
         LOG.error(var3);
      }

   }

   public void update(JobTables jobTables) {
      try {
         this.jobTablesMapper.update(jobTables);
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
         LOG.error(var3);
      }

   }

   public void delete(int id) {
      try {
         this.jobTablesMapper.delete(id);
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
         LOG.error(var3);
      }

   }

   public JobTables getById(int id) {
      JobTables result = null;

      try {
         result = this.jobTablesMapper.getById(id);
      } catch (Exception var4) {
         LOG.error(var4.getMessage());
         LOG.error(var4);
      }

      return result;
   }

   public List listByJobId(int jobId) {
      List result = null;

      try {
         result = this.jobTablesMapper.getByJobId(jobId);
      } catch (Exception var4) {
         LOG.error(var4.getMessage());
         LOG.error(var4);
      }

      return result;
   }

   public void deleteByJobId(int jobId) {
      try {
         this.jobTablesMapper.deleteByJobId(jobId);
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
         LOG.error(var3);
      }

   }

   public boolean checkIfExist(JobTables jobTable, boolean isNew) {
      boolean exists = false;

      try {
         if(isNew) {
            exists = this.jobTablesMapper.checkIfExists4New(jobTable) > 0;
         } else {
            exists = this.jobTablesMapper.checkIfExists4Update(jobTable) > 0;
         }
      } catch (Exception var5) {
         LOG.error(var5);
      }

      return exists;
   }
}
