package com.meicloud.services;

import com.meicloud.dao.ScheduleParameterMapper;
import com.meicloud.model.ScheduleParameter;
import com.meicloud.services.ScheduleParameterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scheduleParameterService")
public class ScheduleParameterServiceImpl implements ScheduleParameterService {

   @Autowired
   private ScheduleParameterMapper scheduleParameterMapper;


   public List queryParameter(String workflowName) throws Exception {
      try {
         return this.scheduleParameterMapper.queryParameter(workflowName);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void updateParameter(ScheduleParameter scheduleParameter) throws Exception {
      try {
         this.scheduleParameterMapper.updateParameter(scheduleParameter);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void insertParameter(ScheduleParameter scheduleParameter) throws Exception {
      try {
         this.scheduleParameterMapper.insertParameter(scheduleParameter);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void deleteParameter(int scheduleParaId) throws Exception {
      this.scheduleParameterMapper.deleteParameter(scheduleParaId);
   }
}
