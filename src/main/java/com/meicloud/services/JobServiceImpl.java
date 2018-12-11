package com.meicloud.services;

import com.meicloud.dao.ChargorMapper;
import com.meicloud.dao.GroupJobReferMapper;
import com.meicloud.dao.GroupMapper;
import com.meicloud.dao.JobMapper;
import com.meicloud.dao.QueueMapper;
import com.meicloud.dao.RunJobMapper;
import com.meicloud.dao.ServerMapper;
import com.meicloud.model.Job;
import com.meicloud.model.JobPusherParam;
import com.meicloud.model.Queue;
import com.meicloud.model.RunJob;
import com.meicloud.model.Server;
import com.meicloud.services.JobService;
import com.meicloud.utils.FileUtil;
import com.meicloud.utils.Utils;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jobService")
public class JobServiceImpl implements JobService {

   private static Logger LOG = Logger.getLogger("JobServiceImpl");
   @Autowired
   private JobMapper jobMapper;
   @Autowired
   private GroupMapper groupMapper;
   @Autowired
   private ChargorMapper chargorMapper;
   @Autowired
   private RunJobMapper runJobMapper;
   @Autowired
   private GroupJobReferMapper groupJobReferMapper;
   @Autowired
   private Environment environment;
   @Autowired
   private ServerMapper serverMapper;
   @Autowired
   private QueueMapper queueMapper;


   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void add(Job job) throws Exception {
      try {
         if(!Utils.isEmptyStr(job.getJobName())) {
            job.setLogFile(job.getJobName() + "_%sysdate%.log");
         }

         Server e = this.serverMapper.getServerByIP(this.environment.getProperty("app.server.host"));
         Queue queue = this.queueMapper.findOne(e.getQueueId());
         String syncSvnFile = null;
         if(!Utils.isEmptyStr(e.getSvnLogPath())) {
            syncSvnFile = e.getSvnLogPath() + "/" + queue.getQueueCode();
         } else {
            syncSvnFile = System.getProperty("user.dir") + "/" + queue.getQueueCode();
         }

         if(job.getJobType().equalsIgnoreCase("informatica")) {
            job.setServerFile(job.getFolderName() + "/" + job.getSvnFile());
         } else {
            String filePath;
            if(job.getJobType().equalsIgnoreCase("shell")) {
               filePath = syncSvnFile + "/shell/" + job.getJobName() + ".sh";
               job.setSvnFile(filePath);
               job.setServerFile(filePath);
               FileUtil.WriteStringToFile(filePath, job.getContext().trim());
            } else if(job.getJobType().equalsIgnoreCase("python")) {
               filePath = syncSvnFile + "/python/" + job.getJobName() + ".py";
               job.setSvnFile(filePath);
               job.setServerFile(filePath);
               FileUtil.WriteStringToFile(filePath, job.getContext().trim());
            } else if(!Utils.isEmptyStr(job.getSvnFile())) {
               job.setServerFile(job.getSvnFile().substring(job.getSvnFile().lastIndexOf("/") + 1));
            }
         }

         this.jobMapper.add(job);
         this.jobMapper.addJobState(job);
         if(job.getOrderNo() != 0) {
            this.groupMapper.update2SordInGroup(job.getJobId());
         }

      } catch (Exception var6) {
         LOG.error(var6.getMessage());
         throw var6;
      }
   }

   public void addJobState(Job job) {
      this.jobMapper.addJobState(job);
   }

   public int getJobStateCountByJobId(int jobId) {
      return this.jobMapper.getJobStateCountByJobId(jobId);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void update(Job job) throws Exception {
      try {
         if(!Utils.isEmptyStr(job.getJobName())) {
            job.setLogFile(job.getJobName() + "_%sysdate%.log");
         }

         Server e = this.serverMapper.getServerByIP(this.environment.getProperty("app.server.host"));
         Queue queue = this.queueMapper.findOne(e.getQueueId());
         String syncSvnFile = null;
         if(!Utils.isEmptyStr(e.getSvnLogPath())) {
            syncSvnFile = e.getSvnLogPath() + "/" + queue.getQueueCode();
         } else {
            syncSvnFile = System.getProperty("user.dir") + "/" + queue.getQueueCode();
         }

         if(job.getJobType().equalsIgnoreCase("informatica")) {
            job.setServerFile(job.getFolderName() + "/" + job.getSvnFile());
         } else {
            String runJob;
            if(job.getJobType().equalsIgnoreCase("shell")) {
               runJob = syncSvnFile + "/shell/" + job.getJobName() + ".sh";
               job.setServerFile(runJob);
               job.setSvnFile(runJob);
               FileUtil.WriteStringToFile(runJob, job.getContext().trim());
            } else if(job.getJobType().equalsIgnoreCase("python")) {
               runJob = syncSvnFile + "/python/" + job.getJobName() + ".py";
               job.setSvnFile(runJob);
               job.setServerFile(runJob);
               FileUtil.WriteStringToFile(runJob, job.getContext().trim());
            } else if(!Utils.isEmptyStr(job.getSvnFile())) {
               job.setServerFile(job.getSvnFile().substring(job.getSvnFile().lastIndexOf("/") + 1));
            }
         }

         this.jobMapper.addChlog(job);
         this.jobMapper.update(job);
         if(job.getOrderNo() != 0) {
            this.groupMapper.update2SordInGroup(job.getJobId());
         }

         if(!job.isEnable()) {
            JobPusherParam runJob1 = new JobPusherParam();
            runJob1.setJobId(job.getJobId());
            this.runJobMapper.updateRunJobState2CancelFromError(runJob1);
         }

         RunJob runJob2 = new RunJob();
         runJob2.setJobId(job.getJobId());
         runJob2.setGroupId(job.getGroupId());
         this.runJobMapper.updateJobGroupState(runJob2);
      } catch (Exception var6) {
         LOG.error(var6.getMessage());
         throw var6;
      }
   }

   public Job getJobById(int jobId) throws Exception {
      try {
         return this.jobMapper.getById(jobId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public Job getJobByJobName(String jobName) throws Exception {
      try {
         return this.jobMapper.getByJobName(jobName);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getList4RunPage(int groupId, String queryKey, String state, boolean online, int queueId) throws Exception {
      try {
         return this.jobMapper.getListCurrentWindow(groupId, queryKey, state, online, queueId);
      } catch (Exception var7) {
         throw var7;
      }
   }

   public List getList4EditPage(int groupId, String queryKey, int queueId) throws Exception {
      try {
         return this.jobMapper.getList4EditPage(groupId, queryKey, queueId);
      } catch (Exception var5) {
         throw var5;
      }
   }

   public List getList4RunPageHeader(int vState, int queueId) throws Exception {
      List result = null;

      try {
         String e = "0";
         if(vState == -1) {
            result = this.jobMapper.getListRunPageHeader_disable(queueId);
            e = "4";
         } else if(vState != 0 && vState != 1) {
            if(vState == 2) {
               result = this.jobMapper.getListRunPageHeader_2(queueId);
               e = "2";
            } else if(vState == 3) {
               result = this.jobMapper.getListRunPageHeader_3_4_5(vState, queueId);
               e = "3";
            } else if(vState == 4) {
               result = this.jobMapper.getListRunPageHeader_3_4_5(vState, queueId);
               e = "4";
            } else if(vState == 5) {
               result = this.jobMapper.getListRunPageHeader_3_4_5(vState, queueId);
               e = "5";
            } else if(vState == 6) {
               result = this.jobMapper.getListRunPageHeader_6(queueId);
               e = "2";
            } else {
               result = this.jobMapper.getListRunPageHeader_7(queueId);
               e = "1";
            }
         } else {
            result = this.jobMapper.getListRunPageHeader_0_1(vState, queueId);
            e = "0";
         }

         if(result != null && result.size() > 0) {
            Iterator var6 = result.iterator();

            while(var6.hasNext()) {
               Job job = (Job)var6.next();
               job.setExecState(e);
            }
         }

         return result;
      } catch (Exception var7) {
         throw var7;
      }
   }

   public List getListByJobIds(String jobIds) throws Exception {
      try {
         return this.jobMapper.getListByJobIds(jobIds);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public int checkIfExists4New(Job job) throws Exception {
      try {
         return this.jobMapper.checkIfExists4New(job);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public int checkIfExists4Update(Job job) throws Exception {
      try {
         return this.jobMapper.checkIfExists4Update(job);
      } catch (Exception var3) {
         throw var3;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public boolean deleteById(int jobId, String logReson, String account) {
      try {
         Job e = new Job();
         e.setJobId(jobId);
         e.setuUser(account);
         e.setLogReson(logReson);
         this.jobMapper.addChlog(e);
         this.runJobMapper.delete(jobId);
         this.groupJobReferMapper.deleteByJobId(jobId);
         this.jobMapper.deleteStateByJobId(jobId);
         return this.jobMapper.deleteById(jobId) >= 0;
      } catch (Exception var5) {
         LOG.error(var5.getMessage());
         throw var5;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public boolean deleteByGroupId(int groupId) {
      try {
         this.jobMapper.deleteStateByGroupId(groupId);
         this.groupJobReferMapper.deleteByGroupId(groupId);
         this.runJobMapper.deleteByGroupId(groupId);
         this.jobMapper.deleteByGroupId(groupId);
         return true;
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
         throw var3;
      }
   }

   public void setOrderNo(String orderNo) {
      try {
         String[] e = orderNo.split(",");
         Job job = new Job();

         int jobId;
         for(jobId = 1; jobId <= e.length; ++jobId) {
            job.setJobId(Integer.parseInt(e[jobId - 1]));
            job.setOrderNo(jobId);
            this.jobMapper.updateOrder(job);
         }

         jobId = job.getJobId();
         this.groupMapper.update2SordInGroup(jobId);
      } catch (Exception var5) {
         throw var5;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateState(int jobId, String account, String logReson) throws Exception {
      try {
         Job e = this.jobMapper.getById(jobId);
         e.setuUser(account);
         e.setLogReson(logReson);
         this.jobMapper.addChlog(e);
         if(e.isEnable()) {
            e.setEnable(false);
            JobPusherParam jobPusherParam = new JobPusherParam();
            jobPusherParam.setJobId(jobId);
            this.runJobMapper.updateRunJobState2CancelFromError(jobPusherParam);
         } else {
            e.setEnable(true);
         }

         this.jobMapper.update(e);
      } catch (Exception var6) {
         LOG.error(var6.getMessage());
         throw var6;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void online(int jobId, String account, String logReson) throws Exception {
      try {
         Job e = this.jobMapper.getById(jobId);
         e.setuUser(account);
         e.setLogReson(logReson);
         this.jobMapper.addChlog(e);
         if(e.isOnline()) {
            e.setOnline(false);
            if(e.isEnable()) {
               e.setEnable(false);
               JobPusherParam jobPusherParam = new JobPusherParam();
               jobPusherParam.setJobId(jobId);
               this.runJobMapper.updateRunJobState2CancelFromError(jobPusherParam);
            }
         } else {
            e.setOnline(true);
            if(!e.isEnable()) {
               e.setEnable(true);
            }
         }

         this.jobMapper.update(e);
      } catch (Exception var6) {
         LOG.error(var6.getMessage());
         throw var6;
      }
   }

   public List getChargorList() {
      return this.chargorMapper.getChargorList();
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void cancelOrder(int groupId) throws Exception {
      try {
         this.jobMapper.cancelOrder(groupId);
         this.groupMapper.update2UnSordInGroup(groupId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List jobHisList(int runGroupId) throws Exception {
      try {
         return this.jobMapper.jobHisList(runGroupId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public RunJob formRunJobByJobId(int jobId) throws Exception {
      try {
         return this.runJobMapper.formRunJobByJobId(jobId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List getDummyGroupState(int groupId) {
      return this.jobMapper.getDummyGroupState(groupId);
   }

   public Job checkJobByName(String jobName) {
      try {
         return this.jobMapper.checkJobByName(jobName);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List findJobByRetryRuleId(int retryId) {
      return this.jobMapper.findJobByRetryRuleId(retryId);
   }
}
