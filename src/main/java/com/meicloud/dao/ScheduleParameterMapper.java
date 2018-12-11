package com.meicloud.dao;

import com.meicloud.model.ScheduleParameter;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScheduleParameterMapper {

   List queryParameter(@Param("workflowName") String var1);

   void updateParameter(ScheduleParameter var1);

   void insertParameter(ScheduleParameter var1);

   void deleteParameter(@Param("scheduleParaId") int var1);
}
