package com.meicloud.dao;

import com.meicloud.model.JobPusherParam;
import com.meicloud.model.KillJobParam;
import com.meicloud.model.QueryParam;
import com.meicloud.model.RunConfirm;
import com.meicloud.model.RunJob;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunJobMapper {

   List getByGroupId(int var1);

   List getWaitingByGroupId(int var1);

   List getWaitingByDateLatestSendIsNotNull();

   List getErrorByGroupId(int var1);

   int getRunJobCountByServerId(@Param("serverId") int var1);

   List getRetryErrorCandidateJobs(int var1);

   List getExpireCandidateJobs(int var1);

   void update4RetryError(RunJob var1);

   void update4RetryExpire(RunJob var1);

   int getDummyGroupUnFinishedSuccessJobCount(int var1);

   void updateJobStateBatch();

   void cleanUpJobsState();

   List getRunJobByGroupIdAndOrderNo(RunJob var1);

   List getWaitingRunJobList(QueryParam var1);

   List getGroupParalle(QueryParam var1);

   void updateListLatestSendDate(@Param("ids") List var1);

   void updateListLatestSendDateIsNull(@Param("ids") List var1);

   List getCompleteRunJobListByGroupId(@Param("groupId") Integer var1);

   RunJob getCompleteRunJobListByJobId(@Param("jobId") Integer var1);

   void updateRejectListLatestSendDate(@Param("ids") List var1);

   void updateState(RunJob var1);

   void updateStateAfterKill(KillJobParam var1);

   int getRunJobCountByStateAndQueueId(RunJob var1);

   Integer pushGroupRefer2RunTime(JobPusherParam var1);

   Integer pushJob2RunTime(JobPusherParam var1);

   Integer pushJob2RunTimeHandle(JobPusherParam var1);

   boolean hasRunningJobsInRunTime(JobPusherParam var1);

   RunJob getRunJobByJobId(int var1);

   RunJob formRunJobByJobId(int var1);

   List getJobsByGroupId(JobPusherParam var1);

   Integer getRunGroupId(int var1);

   void add(RunJob var1);

   Integer getRunJobUpSeconds(int var1);

   Integer getRunGroupUpSeconds(int var1);

   void updateJobState(RunJob var1);

   void updateJobGroupState(RunJob var1);

   void updateRunJobState2ErrorFromWaitingOrRunning(JobPusherParam var1);

   void updateRunJobListState2ErrorFromWaitingOrRunning(JobPusherParam var1);

   void updateRunJobState2CancelFromError(JobPusherParam var1);

   void updateRunJobListState2CancelFromError(JobPusherParam var1);

   void updateDateLatestSend(int var1);

   void updateRunVersionByGroup(int var1);

   void updateRunVersionByJob(int var1);

   int delete(int var1);

   int deleteByGroupId(int var1);

   void copy2His(int var1);

   void copy2HisByGroupId(int var1);

   void addConfirm(RunConfirm var1);

   void addConfirmSQL(RunConfirm var1);

   void addByGroup(JobPusherParam var1);

   void updateJobStateBySatrt(JobPusherParam var1);

   void updateJobStateBySatrtAndJobid(JobPusherParam var1);

   Integer getRunJobCountByGroupId(int var1);

   void updateConfirm();

   List runConfirmList(@Param("account") String var1, @Param("filter") String var2);

   void confirmJob(@Param("confirmId") int var1);

   int checkRunJob(@Param("groupId") Integer var1, @Param("oldGroupId") Integer var2);

   int runningCount(int var1);

   RunJob getByRunJobId(int var1);

   RunJob getByJobId(int var1);

   boolean checkIfExists(RunJob var1);

   List getExpireCandidateJobsBySysStart(@Param("serverId") Integer var1);

   void deleteGarbageRunJob(@Param("jobId") Integer var1);

   void deleteByRunJobId(@Param("runJobId") Integer var1);

   List getGarbageRunJob();
}
