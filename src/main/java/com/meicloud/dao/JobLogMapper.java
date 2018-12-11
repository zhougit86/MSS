package com.meicloud.dao;

import com.meicloud.model.JobLog;
import com.meicloud.model.JobPusherParam;
import com.meicloud.model.KillJobParam;
import java.util.List;

public interface JobLogMapper {

   void addLogAfterKill(KillJobParam var1);

   JobLog getLatestLog(KillJobParam var1);

   void add(JobLog var1);

   List getListByJobId(int var1);

   JobLog getLogById(int var1);

   void deleteByJobId(int var1);

   void deleteGroupId(int var1);

   void updateLog2ErrorFromWaitingOrRunning(JobPusherParam var1);

   void updateLog2ErrorFromWaitingOrRunningByGroup(JobPusherParam var1);
}
