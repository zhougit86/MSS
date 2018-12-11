package com.meicloud.services;

import com.meicloud.model.ScheduleParameter;
import java.util.List;

public interface ScheduleParameterService {

   List queryParameter(String var1) throws Exception;

   void updateParameter(ScheduleParameter var1) throws Exception;

   void insertParameter(ScheduleParameter var1) throws Exception;

   void deleteParameter(int var1) throws Exception;
}
