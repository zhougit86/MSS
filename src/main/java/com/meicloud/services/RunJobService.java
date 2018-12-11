package com.meicloud.services;

import com.meicloud.model.Account;
import com.meicloud.model.Group;
import com.meicloud.model.JobLog;
import com.meicloud.model.JobPusherParam;
import com.meicloud.model.KillJobParam;
import com.meicloud.model.RunJob;
import java.util.List;

public interface RunJobService {

   List getByGroupId(int var1) throws Exception;

   List getWaitingByGroupId(int var1) throws Exception;

   List getWaitingByDateLatestSendIsNotNull() throws Exception;

   List getErrorByGroupId(int var1) throws Exception;

   List getRunJobByGroupIdAndOrderNo(RunJob var1) throws Exception;

   int getRunJobCountByServerId(int var1);

   boolean killJob(int var1, String var2) throws Exception;

   boolean killGroup(int var1, String var2) throws Exception;

   boolean reRunJob(int var1, String var2, String var3, Account var4) throws Exception;

   boolean reRunGroup(int var1, String var2, Account var3) throws Exception;

   int getRunJobUpSeconds(int var1) throws Exception;

   int getRunGroupUpSeconds(int var1) throws Exception;

   void removeGroupFromCron(Group var1) throws Exception;

   void reAddGroup2Cron(Group var1) throws Exception;

   List runConfirmList(String var1, String var2) throws Exception;

   void confirmJob(int var1) throws Exception;

   void updateJobState(RunJob var1) throws Exception;

   void copy2HisByGroupId(int var1);

   int deleteByGroupId(int var1);

   void addByGroup(JobPusherParam var1);

   void updateJobStateBySatrt(JobPusherParam var1);

   void updateJobStateBySatrtAndJobid(JobPusherParam var1);

   Integer getRunJobCountByGroupId(int var1);

   int checkRunJob(Integer var1, Integer var2);

   boolean updateState(RunJob var1, JobLog var2) throws Exception;

   boolean updateStateAfterKill(KillJobParam var1) throws Exception;

   int runningCount(int var1);

   RunJob getByRunJobId(int var1);

   RunJob getByJobId(int var1);

   boolean checkIfExists(RunJob var1);

   int getRunJobCountByStateAndQueueId(RunJob var1);

   void killYarn(int var1);

   void updateListLatestSendDateIsNull(List var1);

   void deleteByRunJobId(int var1);
}
