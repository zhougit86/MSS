package com.meicloud.services;

import com.meicloud.dao.GroupMapper;
import com.meicloud.model.Group;
import com.meicloud.model.GroupJobRefer;
import com.meicloud.model.GroupParalle;
import com.meicloud.model.GroupRefer;
import com.meicloud.model.QueryParam;
import com.meicloud.model.RunJob;
import com.meicloud.mq.MQProducer;
import com.meicloud.services.RunJobService;
import com.meicloud.services.SendJobService;
import com.meicloud.worker.DBTool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sendJobService")
public class SendJobServiceImpl implements SendJobService {

   private static Logger LOG = LoggerFactory.getLogger(SendJobServiceImpl.class);
   @Autowired
   private DBTool dbTool;
   @Autowired
   private RunJobService runJobService;
   @Autowired
   private GroupMapper groupMapper;
   @Autowired
   private MQProducer mQProducer;


   public void send(int groupId) {
      try {
         String e = "";
         List list = this.runJobService.getWaitingByGroupId(groupId);
         if(list == null || list.size() == 0) {
            e = "no available waiting runjobs to send to queue.";
            LOG.info(e);
            return;
         }

         Group group = this.groupMapper.getById(groupId);
         ArrayList validateList = new ArrayList();
         ArrayList unvalidateList = new ArrayList();
         Iterator sb = list.iterator();

         while(sb.hasNext()) {
            RunJob runJobId = (RunJob)sb.next();
            //验证是否能发送
            boolean runJob = runJobId != null && runJobId.validateSend();
            if(runJob) {
               validateList.add(runJobId);
            } else {
               LOG.info("[" + runJobId.getRunJobId() + "]数据错误,拒绝发送");
               unvalidateList.add(Integer.valueOf(runJobId.getRunJobId()));
            }
         }

         RunJob runJob2;
         if(validateList.size() > 0) {
            HashMap runJobId1 = new HashMap();
            StringBuffer sb1 = new StringBuffer();
            Iterator parmRunJob = validateList.iterator();

            //建了一个groupid到job list得哈希
            while(parmRunJob.hasNext()) {
               runJob2 = (RunJob)parmRunJob.next();
               List paralleMap = (List)runJobId1.get(Integer.valueOf(runJob2.getGroupId()));
               if(paralleMap == null) {
                  sb1.append(",");
                  sb1.append(groupId);
                  ArrayList paralleMap1 = new ArrayList();
                  paralleMap1.add(runJob2);
                  runJobId1.put(Integer.valueOf(groupId), paralleMap1);
               } else {
                  paralleMap.add(runJob2);
               }
            }

            String runJob3 = "";
            List parmRunJob1 = null;
            if(sb1.length() > 0) {
               runJob3 = sb1.substring(1);
               QueryParam paralleMap2 = new QueryParam();
               paralleMap2.setMsg(runJob3);
               parmRunJob1 = this.dbTool.getGroupParalle(paralleMap2);
            }

            HashMap paralleMap3 = new HashMap();
            Iterator iter;
            if(parmRunJob1 != null) {
               iter = parmRunJob1.iterator();

               while(iter.hasNext()) {
                  GroupParalle sendList = (GroupParalle)iter.next();
                  paralleMap3.put(Integer.valueOf(sendList.getGroupId()), sendList);
               }
            }

            ArrayList sendList1 = new ArrayList();
            iter = runJobId1.keySet().iterator();

            List queryRunJob;
            while(iter.hasNext()) {
               int runJob1 = ((Integer)iter.next()).intValue();
               GroupParalle groupParalle = (GroupParalle)paralleMap3.get(Integer.valueOf(runJob1));
               queryRunJob = (List)runJobId1.get(Integer.valueOf(runJob1));
               if(groupParalle != null && groupParalle.getParalleLimit() > 0) {
                  int runJobs = groupParalle.getParalleLimit() - groupParalle.getJobNumInQueueOrRunning();
                  LOG.info("[" + groupParalle.getGroupId() + "]组内并发数:[" + groupParalle.getParalleLimit() + "],组内正在运行JOB数:[" + groupParalle.getJobNumInQueueOrRunning() + "],组内等待运行数:[" + queryRunJob.size() + "]");
                  if(runJobs >= queryRunJob.size()) {
                     sendList1.addAll(queryRunJob);
                  } else if(runJobs > 0) {
                     List subList = queryRunJob.subList(0, runJobs);
                     sendList1.addAll(subList);
                  }
               } else {
                  LOG.info("[" + groupId + "]组内无并发... 推送RunJob数:[" + queryRunJob.size() + "]");
                  sendList1.addAll(queryRunJob);
               }
            }

            if(sendList1.size() > 0) {
               LOG.info("send size " + sendList1.size());
               Iterator groupParalle1 = sendList1.iterator();

               while(groupParalle1.hasNext()) {
                  RunJob runJob4 = (RunJob)groupParalle1.next();
                  if(!group.isErrorJobContinueRun()) {
                     queryRunJob = this.runJobService.getErrorByGroupId(groupId);
                     if(queryRunJob != null && queryRunJob.size() > 0) {
                        LOG.info("组内JOB出错,runJob[" + runJob4.getRunJobId() + "] 拒绝发送.");
                        continue;
                     }
                  }

                  if(group.isSordInGroup()) {
                     RunJob queryRunJob1 = new RunJob();
                     queryRunJob1.setGroupId(groupId);
                     queryRunJob1.setOrderNo(runJob4.getOrderNo());
                     List runJobs1 = this.runJobService.getRunJobByGroupIdAndOrderNo(runJob4);
                     if(runJobs1 == null || runJobs1.size() <= 0) {
                        LOG.info("send mq: runJobId[" + runJob4.getRunJobId() + "]");
                        this.mQProducer.sendMessage(runJob4);
                     }
                  } else {
                     LOG.info("send mq order by runJobId[" + runJob4.getRunJobId() + "]");
                     this.mQProducer.sendMessage(runJob4);
                  }
               }
            } else {
               e = "no runjobs send to queue since there is no left paralle space for the [" + validateList.size() + "] waiting runjobs";
            }

            LOG.info(e);
         }

         if(unvalidateList.size() > 0) {
            this.dbTool.updateUnSendedList(unvalidateList);
            sb = unvalidateList.iterator();

            while(sb.hasNext()) {
               Integer runJobId2 = (Integer)sb.next();
               runJob2 = this.runJobService.getByRunJobId(runJobId2.intValue());
               RunJob parmRunJob2 = new RunJob();
               parmRunJob2.setJobId(runJob2.getJobId());
               parmRunJob2.setState(4);
               parmRunJob2.setMsg("kettle file is null or log file is null");
               this.runJobService.updateJobState(parmRunJob2);
            }

            e = "reject [" + unvalidateList.size() + "] waiting runjobs which ids are [" + unvalidateList.toString() + "]";
            LOG.info(e);
         }
      } catch (Exception var19) {
         LOG.error(var19.getMessage());
         LOG.error(var19.getMessage());
      }

   }

   public boolean completeGroup(int groupId) throws Exception {
      boolean complete = true;
      GroupRefer groupRefer = new GroupRefer();
      groupRefer.setGroupId(groupId);
      List groupRefers = this.dbTool.getGroupReferByGroupId(groupRefer);
      if(groupRefers != null && groupRefers.size() > 0) {
         Iterator var6 = groupRefers.iterator();

         while(var6.hasNext()) {
            GroupRefer refer = (GroupRefer)var6.next();
            boolean b = this.completeGroup(refer.getReferedGroupId());
            if(!b) {
               LOG.info("依赖组[" + refer.getReferedGroupId() + "]未完成...");
               return false;
            }
         }
      }

      complete = this.dbTool.completeByGroupId(Integer.valueOf(groupId));
      return complete;
   }

   public boolean completeJob(int groupId) throws Exception {
      boolean complete = true;
      List groupJobRefers = this.dbTool.getJobReferByGroupId(groupId);
      Iterator var5 = groupJobRefers.iterator();

      while(var5.hasNext()) {
         GroupJobRefer groupJobRefer = (GroupJobRefer)var5.next();
         boolean b = this.dbTool.completeByJobId(Integer.valueOf(groupJobRefer.getJobId()));
         if(!b) {
            LOG.info("依赖作业[" + groupJobRefer.getJobId() + "]未完成...");
            return false;
         }
      }

      return complete;
   }
}
