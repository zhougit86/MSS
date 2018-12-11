package com.meicloud.services;

import com.meicloud.dao.JobLogMapper;
import com.meicloud.model.JobLog;
import com.meicloud.services.LogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logService")
public class LogServiceImpl implements LogService {

   @Autowired
   private JobLogMapper jobLogMapper;


   public List getListByJobId(int jobId) throws Exception {
      try {
         return this.jobLogMapper.getListByJobId(jobId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public JobLog getLogById(int logId) throws Exception {
      try {
         return this.jobLogMapper.getLogById(logId);
      } catch (Exception var3) {
         throw var3;
      }
   }
}
