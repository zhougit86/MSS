package com.meicloud.services;

import com.meicloud.dao.KettleJobLogDetailMapper;
import com.meicloud.dao.KettleJobLogMapper;
import com.meicloud.services.KettleJobLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("kettleJobLogService")
public class KettleJobLogServiceImpl implements KettleJobLogService {

   @Autowired
   private KettleJobLogMapper kettleJobLogMapper;
   @Autowired
   private KettleJobLogDetailMapper kettleJobLogDetailMapper;


   public List getListByJobName(String jobName) {
      try {
         return this.kettleJobLogMapper.getListByJobName(jobName);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getKettleJobLogDetailListByIdBatch(int jobId) {
      try {
         return this.kettleJobLogDetailMapper.getKettleJobLogDetailListByIdBatch(jobId);
      } catch (Exception var3) {
         throw var3;
      }
   }
}
