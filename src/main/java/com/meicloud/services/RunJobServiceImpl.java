package com.meicloud.services;

import com.alibaba.fastjson.JSONArray;
import com.meicloud.dao.GroupJobReferMapper;
import com.meicloud.dao.GroupMapper;
import com.meicloud.dao.GroupReferMapper;
import com.meicloud.dao.JobLogMapper;
import com.meicloud.dao.JobMapper;
import com.meicloud.dao.RunGroupMapper;
import com.meicloud.dao.RunJobMapper;
import com.meicloud.dao.ServerMapper;
import com.meicloud.model.Account;
import com.meicloud.model.Group;
import com.meicloud.model.GroupJobRefer;
import com.meicloud.model.GroupRefer;
import com.meicloud.model.Job;
import com.meicloud.model.JobLog;
import com.meicloud.model.JobPusherParam;
import com.meicloud.model.KillJobParam;
import com.meicloud.model.RunConfirm;
import com.meicloud.model.RunGroup;
import com.meicloud.model.RunJob;
import com.meicloud.model.Server;
import com.meicloud.mq.MQProducer;
import com.meicloud.services.RunJobService;
import com.meicloud.utils.HttpUtil;
import com.meicloud.utils.StringUtil;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("runJobService")
public class RunJobServiceImpl implements RunJobService {

   private static Logger LOG = Logger.getLogger(RunJobServiceImpl.class);
   @Autowired
   private RunJobMapper runJobMapper;
   @Autowired
   private JobLogMapper jobLogMapper;
   @Autowired
   private GroupReferMapper groupReferMapper;
   @Autowired
   private JobMapper jobMapper;
   @Autowired
   private GroupMapper groupMapper;
   @Autowired
   private GroupJobReferMapper groupJobReferMapper;
   @Autowired
   private RunGroupMapper runGroupMapper;
   @Autowired
   private ServerMapper serverMapper;
   @Autowired
   private Environment environment;
   @Autowired
   private MQProducer mQProducer;


   public boolean killJob(int jobId, String account) throws Exception {
      boolean result = false;
      LOG.info("kill job of jobId:" + jobId);

      try {
         RunJob e = this.runJobMapper.getRunJobByJobId(jobId);
         if(e != null) {
            this.runJobMapper.updateRunVersionByJob(e.getRunJobId());
            KillJobParam param;
            if(2 == e.getState()) {
               param = new KillJobParam();
               param.setIp(e.getIp());
               param.setRunJobId(e.getRunJobId());
               param.setRunVersion(e.getRunVersion());
               param.setServerFile(e.getServerFile());
               param.setAppendParams(e.getAppendParams());
               param.setMsg("CANCELED BY　" + account + " KILL　SIGLE JOB.AND SET STATE To " + 3);
               param.setState(3);
               HttpUtil httpUtil = new HttpUtil();
               Server server = this.serverMapper.getServerByIP(e.getIp());
               String url = "http://" + server.getIp() + ":" + server.getAppPort() + "/MSS/killJob";
               String json = JSONArray.toJSONString(param);
               httpUtil.sendByPostRe(url, json);
            } else if(e.getState() == 0 || 1 == e.getState()) {
               param = new KillJobParam();
               param.setRunJobId(e.getRunJobId());
               param.setState(3);
               param.setRunVersion(e.getRunVersion());
               param.setMsg("CANCELED BY　" + account + " KILL　SIGLE JOB.AND SET STATE To " + 3);
               param.setErrorRetryedCount(e.getrErrorIntervalNum());
               this.runJobMapper.updateStateAfterKill(param);
            }
         }

         result = true;
      } catch (Exception var10) {
         LOG.error(var10);
      }

      return result;
   }

   public void killYarn(int jobId) {
      try {
         if(this.environment.getProperty("hadoop_url") != null && !this.environment.getProperty("hadoop_url").equals("") && jobId != 0) {
            HttpUtil e = new HttpUtil();
            e.sendByGet(this.environment.getProperty("hadoop_url") + "/ws/v1/cluster/apps?state=RUNNING,ACCEPTED");
            JSONObject obj = new JSONObject(e.getContent());
            LOG.info("obj：" + obj);
            Object o = obj.get("apps");
            if(o != null && !"null".equals(o.toString())) {
               JSONObject appsObj = obj.getJSONObject("apps");
               LOG.info("apps：" + appsObj);
               org.json.JSONArray appArray = appsObj.getJSONArray("app");
               if(appArray != null && appArray.length() > 0) {
                  Job job = this.jobMapper.getById(jobId);
                  String jobname = job.getJobName();
                  String appendParams = job.getAppendParams();
                  if(appendParams != null && !appendParams.equals("")) {
                     String[] i = appendParams.split(" ");
                     String[] url = i;
                     int name = i.length;

                     for(int id = 0; id < name; ++id) {
                        String appObj = url[id];
                        if(appObj.indexOf("=") > 0) {
                           String params = appObj.substring(appObj.indexOf("=") + 1, appObj.length());
                           if(params != null && !"".equals(params)) {
                              Job stateObj = this.jobMapper.checkJobByName(params);
                              if(stateObj != null) {
                                 jobname = params;
                                 break;
                              }
                           }
                        }
                     }
                  }

                  LOG.info("jobname***************  " + jobname);

                  for(int var18 = 0; var18 < appArray.length(); ++var18) {
                     JSONObject var19 = (JSONObject)appArray.get(var18);
                     String var20 = var19.getString("id");
                     String var21 = var19.getString("name");
                     LOG.info("id" + var18 + ":" + var20 + ",name" + var18 + ":" + var21);
                     if(var21.indexOf(jobname) != -1) {
                        String var22 = this.environment.getProperty("hadoop_url") + "/ws/v1/cluster/apps/" + var20 + "/state";
                        JSONObject var23 = new JSONObject();
                        var23.put("state", "KILLED");
                        e.sendByPut(var22, var23.toString());
                        JSONObject var24 = new JSONObject(e.getContent());
                        LOG.info("url---------:" + var22);
                        LOG.info("stateObj---------:" + var24);
                     }
                  }
               }
            }
         }
      } catch (Exception var17) {
         LOG.error(var17.getMessage());
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public boolean killGroup(int groupId, String account) throws Exception {
      boolean result = false;
      LOG.info("kill group of groupId:" + groupId);

      try {
         LOG.info("start to kill jobs of group:" + groupId);
         JobPusherParam e = new JobPusherParam();
         e.setState(2);
         e.setGroupId(groupId);
         List runJobList = this.runJobMapper.getJobsByGroupId(e);
         if(runJobList != null && runJobList.size() > 0) {
            int runGroupId = ((RunJob)runJobList.get(0)).getRunGroupId();
            this.runJobMapper.updateRunVersionByGroup(runGroupId);

            try {
               Thread.sleep(1000L);
            } catch (Exception var9) {
               LOG.error(var9);
            }

            e.setMsg("UPDATE TO Error STATE BY [" + account + "]  KIIL GROUP.");
            Iterator var8 = runJobList.iterator();

            while(var8.hasNext()) {
               RunJob runJob = (RunJob)var8.next();
               this.killJob(runJob.getJobId(), account);
            }
         }

         LOG.info("finish kill jobs of group:" + groupId);
         result = true;
      } catch (Exception var10) {
         LOG.error(var10.getMessage());
         LOG.error(var10);
      }

      return result;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public boolean reRunJob(int jobId, String groupName, String jobName, Account account) throws Exception {
      boolean result = false;
      boolean deleted = false;

      try {
         this.runJobMapper.copy2His(jobId);
         LOG.info("start delete from cm_run_job job_id:" + jobId);
         deleted = this.runJobMapper.delete(jobId) > 0;
         LOG.info("finish delete from cm_run_job job_id:" + jobId + "  deleted:" + deleted);
      } catch (Exception var15) {
         LOG.error(var15);
      }

      try {
         RunJob e = this.runJobMapper.formRunJobByJobId(jobId);
         Integer rgId = null;
         if(deleted) {
            rgId = this.runJobMapper.getRunGroupId(e.getGroupId());
         } else {
            JobPusherParam runGroupId = new JobPusherParam();
            runGroupId.setGroupId(e.getGroupId());
            runGroupId.setReferedGroupIds("");
            runGroupId.setReferedJobIds("");
            runGroupId.setPushType(2);
            this.runGroupMapper.copy2His(e.getGroupId());
            this.runGroupMapper.delete(e.getGroupId());
            this.runGroupMapper.addByGroup(runGroupId);
            RunGroup runJob2DB = this.runGroupMapper.getRunGroupByGroupId(e.getGroupId());
            rgId = Integer.valueOf(runGroupId.getRunGroupId() == 0?runJob2DB.getRunGroupId():runGroupId.getRunGroupId());
         }

         int runGroupId1 = rgId == null?0:rgId.intValue();
         if(runGroupId1 == 0) {
            result = false;
            return result;
         } else {
            RunJob runJob2DB1 = new RunJob();
            runJob2DB1.setRunGroupId(runGroupId1);
            runJob2DB1.setJobId(e.getJobId());
            runJob2DB1.setGroupId(e.getGroupId());
            runJob2DB1.setState(0);
            runJob2DB1.setServerFile(e.getServerFile());
            runJob2DB1.setLogFile(e.getLogFile());
            runJob2DB1.setOrderNo(0);
            runJob2DB1.setDebugLevel(e.getDebugLevel());
            runJob2DB1.setrIsTime(e.isrIsTime());
            runJob2DB1.setrTimeout(e.getrTimeout());
            runJob2DB1.setrIsError(e.isrIsError());
            runJob2DB1.setrIntervalTime(e.getrIntervalTime());
            runJob2DB1.setrErrorIntervalNum(e.getrErrorIntervalNum());
            runJob2DB1.setExecuteServerId(e.getExecuteServerId());
            runJob2DB1.setrTimeoutIntervalNum(e.getrTimeoutIntervalNum());
            runJob2DB1.setErrorRetryedCount(0);
            runJob2DB1.setTimeoutRetryedCount(0);
            runJob2DB1.setMsg("ADD BY [" + account.getRealName() + "] reRunJob.");
            runJob2DB1.setAppendParams(e.getAppendParams());
            runJob2DB1.setRunVersion(1);
            runJob2DB1.setQueueId(e.getQueueId());
            this.runJobMapper.add(runJob2DB1);
            this.runJobMapper.updateJobState(runJob2DB1);
            List postList = this.groupReferMapper.getPostList(e.getGroupId());
            if(postList != null && postList.size() > 0) {
               RunConfirm runConfirm = new RunConfirm();
               runConfirm.setFrontGroupId(e.getGroupId());
               runConfirm.setFrontGroupName(groupName);
               runConfirm.setCreateAccount(account.getRealName());
               String inMsg = "[" + account.getRealName() + "]手动跑前置组[" + groupName + "]里的作业[" + jobName + "]";
               runConfirm.setMsg(inMsg);
               this.runJobMapper.addConfirmSQL(runConfirm);
            }

            LOG.info("success to reRunJob [" + StringUtil.formJobName(runJob2DB1.getServerFile()) + "]");
            result = true;
            return result;
         }
      } catch (Exception var14) {
         throw var14;
      }
   }

   public void runGroupCron(int groupId, int pushType) {
      try {
         int jobPusherParam = (int)(Math.random() * 100.0D);
         Thread.sleep((long)jobPusherParam);
      } catch (Exception var10) {
         LOG.info(var10);
      }

      JobPusherParam jobPusherParam1 = new JobPusherParam();
      jobPusherParam1.setGroupId(groupId);
      if(pushType != 2) {
         boolean referedGroupIds = this.runJobMapper.hasRunningJobsInRunTime(jobPusherParam1);
         if(referedGroupIds) {
            LOG.info("there is some jobs in running state of the group[" + groupId + "] so dont need to push job to runtime this round.");
            return;
         }
      }

      String referedGroupIds1 = "";
      String referedJobIds = "";
      if(pushType != 2) {
         referedGroupIds1 = this.getDirectReferedGroupIds(groupId);
         referedJobIds = this.getReferedJobIds(groupId);
      }

      jobPusherParam1.setReferedGroupIds(referedGroupIds1);
      jobPusherParam1.setReferedJobIds(referedJobIds);
      jobPusherParam1.setPushType(pushType);
      Integer rgId = this.runJobMapper.pushGroupRefer2RunTime(jobPusherParam1);
      int runGroupId = rgId == null?0:rgId.intValue();
      if(runGroupId == 0) {
         LOG.info("push group [" + groupId + "] to runtime failure.");
      } else {
         LOG.info("finish push group [" + groupId + "] rungroupId [" + runGroupId + "] and about to push job.");
         jobPusherParam1.setRunGroupId(runGroupId);
         if(pushType == 0) {
            jobPusherParam1.setMsg("PUSH FROM CM_JOB BY SYSTEM CRON.");
         } else if(pushType == 1) {
            jobPusherParam1.setMsg("PUSH FROM CM_JOB BY EvenTrigger.");
         } else {
            jobPusherParam1.setMsg("PUSH FROM CM_JOB BY Handle.");
         }

         LOG.info("jobPusherParam:" + jobPusherParam1);
         int pushJobCount = this.runJobMapper.pushJob2RunTime(jobPusherParam1).intValue();
         String msg = "finish push GROUP groupId:[" + groupId + "] runGroupId:[" + runGroupId + "] referedGroupIds:[" + referedGroupIds1 + "] pushJobCount:[" + pushJobCount + "] to runtime pushType [" + pushType + "]";
         LOG.info(msg);
      }
   }

   public String getDirectReferedGroupIds(int groupId) {
      List groupRefers = this.groupReferMapper.getReferedList(groupId);
      char splitChar = 44;
      StringBuffer sb = new StringBuffer();
      int size = groupRefers.size();

      for(int i = 0; i < size; ++i) {
         sb.append(((GroupRefer)groupRefers.get(i)).getReferedGroupId());
         if(i != size - 1) {
            sb.append(splitChar);
         }
      }

      return sb.toString();
   }

   private String getReferedJobIds(int groupId) {
      String result = "";
      List jobRefers = this.groupJobReferMapper.getByGroupId(groupId);
      if(jobRefers != null && jobRefers.size() > 0) {
         StringBuffer sb = new StringBuffer();
         int size = jobRefers.size();

         for(int i = 0; i < size; ++i) {
            sb.append(((GroupJobRefer)jobRefers.get(i)).getJobId());
            if(i != size - 1) {
               sb.append(',');
            }
         }

         result = sb.toString();
      }

      return result;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public boolean reRunGroup(int groupId, String groupName, Account account) throws Exception {
      boolean result = false;

      try {
         JobPusherParam e = new JobPusherParam();
         e.setGroupId(groupId);
         List runJobList = this.runJobMapper.getJobsByGroupId(e);
         if(runJobList != null && runJobList.size() > 0) {
            int runGroup = ((RunJob)runJobList.get(0)).getRunGroupId();
            this.runJobMapper.updateRunVersionByGroup(runGroup);

            try {
               Thread.sleep(1000L);
            } catch (Exception var17) {
               LOG.error(var17);
            }

            e.setMsg("UPDATE TO Error STATE BY [" + account.getRealName() + "]  KIIL GROUP.");
            Iterator pushJobCount = runJobList.iterator();

            while(pushJobCount.hasNext()) {
               RunJob runGroupId = (RunJob)pushJobCount.next();
               if(runGroupId.getState() == 2) {
                  this.killJob(runGroupId.getJobId(), account.getRealName());
               }
            }
         }

         e.setPushType(2);
         this.runGroupMapper.copy2His(groupId);
         this.runGroupMapper.delete(groupId);
         this.runGroupMapper.addByGroup(e);
         RunGroup runGroup1 = this.runGroupMapper.getRunGroupByGroupId(groupId);
         int runGroupId1 = e.getRunGroupId() == 0?runGroup1.getRunGroupId():e.getRunGroupId();
         if(runGroupId1 == 0) {
            LOG.info("push group [" + groupId + "] to runtime failure of rerun.");
            return false;
         } else {
            e.setRunGroupId(runGroupId1);
            e.setMsg("PUSH FROM CM_JOB BY [" + account.getRealName() + "] RERUN GROUP HANDLE.");
            this.runJobMapper.copy2HisByGroupId(runGroup1.getGroupId());
            this.runJobMapper.deleteByGroupId(runGroup1.getGroupId());
            this.runJobMapper.addByGroup(e);
            this.runJobMapper.updateJobStateBySatrt(e);
            int pushJobCount1 = this.runJobMapper.getRunJobCountByGroupId(runGroup1.getGroupId()).intValue();
            String msg = "push GROUP groupId:[" + groupId + "] runGroupId:[" + runGroupId1 + "]  pushJobCount:[" + pushJobCount1 + "]";
            LOG.info(msg);
            List postList = this.groupReferMapper.getPostList(groupId);
            if(postList != null && postList.size() > 0) {
               RunConfirm runConfirm = new RunConfirm();
               runConfirm.setFrontGroupId(groupId);
               runConfirm.setFrontGroupName(groupName);
               runConfirm.setCreateAccount(account.getRealName());
               String inMsg = "[" + account.getRealName() + "]手动跑前置组[" + groupName + "]";
               runConfirm.setMsg(inMsg);
               this.runJobMapper.addConfirmSQL(runConfirm);
               Iterator var15 = postList.iterator();

               while(var15.hasNext()) {
                  GroupRefer groupRefer = (GroupRefer)var15.next();
                  Group groupDB = this.groupMapper.getById(groupRefer.getGroupId());
                  if(groupDB != null && !groupDB.isTimeTrigger()) {
                     this.reRunGroup(groupDB.getGroupId(), groupDB.getGroupName(), account);
                  }
               }
            }

            result = true;
            return result;
         }
      } catch (Exception var18) {
         LOG.error(var18.getMessage());
         throw var18;
      }
   }

   public int getRunJobUpSeconds(int jobId) throws Exception {
      try {
         Integer e = this.runJobMapper.getRunJobUpSeconds(jobId);
         return e == null?0:e.intValue();
      } catch (Exception var3) {
         LOG.error(var3);
         throw var3;
      }
   }

   public int getRunGroupUpSeconds(int groupId) throws Exception {
      try {
         Integer e = this.runJobMapper.getRunGroupUpSeconds(groupId);
         return e == null?0:e.intValue();
      } catch (Exception var3) {
         LOG.error(var3);
         throw var3;
      }
   }

   public void removeGroupFromCron(Group group) throws Exception {}

   public void reAddGroup2Cron(Group group) throws Exception {}

   public List runConfirmList(String account, String filter) throws Exception {
      try {
         return this.runJobMapper.runConfirmList(account, filter);
      } catch (Exception var4) {
         throw var4;
      }
   }

   public void confirmJob(int confirmId) throws Exception {
      try {
         this.runJobMapper.confirmJob(confirmId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void updateJobState(RunJob job) throws Exception {
      try {
         this.runJobMapper.updateJobState(job);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public int checkRunJob(Integer groupId, Integer oldGroupId) {
      return this.runJobMapper.checkRunJob(groupId, oldGroupId);
   }

   public boolean updateState(RunJob runJob, JobLog jobLog) throws Exception {
      boolean result = false;

      try {
         this.runJobMapper.updateState(runJob);
         this.jobLogMapper.add(jobLog);
         result = true;
         return result;
      } catch (Exception var5) {
         throw var5;
      }
   }

   public boolean updateStateAfterKill(KillJobParam killJobParam) throws Exception {
      boolean result = false;

      try {
         JobLog e = this.jobLogMapper.getLatestLog(killJobParam);
         if(e != null && e.getState() <= 2) {
            this.jobLogMapper.addLogAfterKill(killJobParam);
         } else {
            LOG.info("No need to add jobLog:" + killJobParam);
         }

         this.runJobMapper.updateStateAfterKill(killJobParam);
         result = true;
         return result;
      } catch (Exception var4) {
         throw var4;
      }
   }

   public int runningCount(int jobId) {
      return this.runJobMapper.runningCount(jobId);
   }

   public RunJob getByRunJobId(int runJobId) {
      return this.runJobMapper.getByRunJobId(runJobId);
   }

   public RunJob getByJobId(int jobId) {
      return this.runJobMapper.getByJobId(jobId);
   }

   public boolean checkIfExists(RunJob runJob) {
      return this.runJobMapper.checkIfExists(runJob);
   }

   public void copy2HisByGroupId(int groupId) {
      this.runJobMapper.copy2HisByGroupId(groupId);
   }

   public int deleteByGroupId(int groupId) {
      return this.runJobMapper.deleteByGroupId(groupId);
   }

   public void addByGroup(JobPusherParam jobPusherParam) {
      this.runJobMapper.addByGroup(jobPusherParam);
   }

   public void updateJobStateBySatrt(JobPusherParam jobPusherParam) {
      this.runJobMapper.updateJobStateBySatrt(jobPusherParam);
   }

   public void updateJobStateBySatrtAndJobid(JobPusherParam jobPusherParam) {
      this.runJobMapper.updateJobStateBySatrtAndJobid(jobPusherParam);
   }

   public Integer getRunJobCountByGroupId(int groupId) {
      return this.runJobMapper.getRunJobCountByGroupId(groupId);
   }

   public List getByGroupId(int groupId) throws Exception {
      return this.runJobMapper.getByGroupId(groupId);
   }

   public List getWaitingByGroupId(int groupId) throws Exception {
      return this.runJobMapper.getWaitingByGroupId(groupId);
   }

   public List getWaitingByDateLatestSendIsNotNull() throws Exception {
      return this.runJobMapper.getWaitingByDateLatestSendIsNotNull();
   }

   public List getErrorByGroupId(int groupId) throws Exception {
      return this.runJobMapper.getErrorByGroupId(groupId);
   }

   public List getRunJobByGroupIdAndOrderNo(RunJob runJob) throws Exception {
      return this.runJobMapper.getRunJobByGroupIdAndOrderNo(runJob);
   }

   public int getRunJobCountByStateAndQueueId(RunJob runJob) {
      return this.runJobMapper.getRunJobCountByStateAndQueueId(runJob);
   }

   public void updateListLatestSendDateIsNull(List runJobIds) {
      try {
         this.runJobMapper.updateListLatestSendDateIsNull(runJobIds);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public int getRunJobCountByServerId(int serverId) {
      return this.runJobMapper.getRunJobCountByServerId(serverId);
   }

   public void deleteByRunJobId(int runJobId) {
      this.runJobMapper.deleteByRunJobId(Integer.valueOf(runJobId));
   }
}
