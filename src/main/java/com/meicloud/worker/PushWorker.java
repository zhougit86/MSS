package com.meicloud.worker;

import com.meicloud.model.Group;
import com.meicloud.model.GroupParalle;
import com.meicloud.model.GroupRefer;
import com.meicloud.model.QueryParam;
import com.meicloud.model.RunJob;
import com.meicloud.worker.DBTool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("pushWorker")
public class PushWorker {

   private static Logger LOG = Logger.getLogger(PushWorker.class);
   @Autowired
   private DBTool dbTool;


   public void doPush() {
      try {
         String e = "";
         List list = this.dbTool.canditateWaitingList();
         if(list == null || list.size() == 0) {
            e = "no available waiting runjobs to send to queue.";
            LOG.info(e);
            return;
         }

         ArrayList validateList = new ArrayList();
         ArrayList unvalidateList = new ArrayList();
         Iterator sb = list.iterator();

         while(sb.hasNext()) {
            RunJob splitMap = (RunJob)sb.next();
            boolean groupIds = splitMap != null && splitMap.validataSend();
            if(groupIds) {
               if(this.complete(splitMap.getGroupId())) {
                  RunJob groupParalles = new RunJob();
                  groupParalles.setRunJobId(splitMap.getRunJobId());
                  groupParalles.setJobId(splitMap.getJobId());
                  groupParalles.setGroupId(splitMap.getGroupId());
                  groupParalles.setServerFile(splitMap.getServerFile());
                  groupParalles.setLogFile(splitMap.getLogFile());
                  groupParalles.setDebugLevel(splitMap.getDebugLevel());
                  groupParalles.setRunVersion(splitMap.getRunVersion());
                  groupParalles.setAppendParams(splitMap.getAppendParams());
                  groupParalles.setQueueId(splitMap.getQueueId());
                  validateList.add(groupParalles);
               }
            } else if(splitMap.getRunJobId() != 0) {
               unvalidateList.add(Integer.valueOf(splitMap.getRunJobId()));
            }
         }

         if(validateList.size() > 0) {
            HashMap splitMap1 = new HashMap();
            StringBuffer sb1 = new StringBuffer();
            Iterator groupParalles1 = validateList.iterator();

            ArrayList sendList1;
            while(groupParalles1.hasNext()) {
               RunJob groupIds1 = (RunJob)groupParalles1.next();
               Integer paralleMap = Integer.valueOf(groupIds1.getGroupId());
               List sendList = (List)splitMap1.get(paralleMap);
               if(sendList == null) {
                  sb1.append(",");
                  sb1.append(paralleMap);
                  sendList1 = new ArrayList();
                  sendList1.add(groupIds1);
                  splitMap1.put(paralleMap, sendList1);
               } else {
                  sendList.add(groupIds1);
               }
            }

            String groupIds2 = "";
            List groupParalles2 = null;
            if(sb1.length() > 0) {
               groupIds2 = sb1.substring(1);
               QueryParam paralleMap1 = new QueryParam();
               paralleMap1.setMsg(groupIds2);
               groupParalles2 = this.dbTool.getGroupParalle(paralleMap1);
            }

            HashMap paralleMap2 = new HashMap();
            Iterator iter;
            if(groupParalles2 != null) {
               iter = groupParalles2.iterator();

               while(iter.hasNext()) {
                  GroupParalle sendList2 = (GroupParalle)iter.next();
                  paralleMap2.put(Integer.valueOf(sendList2.getGroupId()), sendList2);
               }
            }

            sendList1 = new ArrayList();
            iter = splitMap1.keySet().iterator();

            while(iter.hasNext()) {
               Integer groupId = (Integer)iter.next();
               GroupParalle groupParalle = (GroupParalle)paralleMap2.get(groupId);
               List runJobList = (List)splitMap1.get(groupId);
               if(groupParalle != null && groupParalle.getParalleLimit() > 0) {
                  int left = groupParalle.getParalleLimit() - groupParalle.getJobNumInQueueOrRunning();
                  if(left >= runJobList.size()) {
                     sendList1.addAll(runJobList);
                  } else if(left > 0) {
                     List subList = runJobList.subList(0, left);
                     sendList1.addAll(subList);
                  }
               } else {
                  sendList1.addAll(runJobList);
               }
            }

            LOG.info(e);
         }

         if(unvalidateList.size() > 0) {
            this.dbTool.updateUnSendedList(unvalidateList);
            e = "reject [" + unvalidateList.size() + "] waiting runjobs which ids are [" + unvalidateList.toString() + "]";
            LOG.info(e);
         }
      } catch (Exception var17) {
         LOG.error(var17);
      }

   }

   private boolean complete(int groupId) throws Exception {
      boolean complete = true;
      Group group = this.dbTool.getById(groupId);
      if(group.isTimeTrigger()) {
         return complete;
      } else {
         GroupRefer groupRefer = new GroupRefer();
         groupRefer.setGroupId(groupId);
         List groupRefers = this.dbTool.getGroupReferByGroupId(groupRefer);
         if(groupRefers != null && groupRefers.size() > 0) {
            Iterator var7 = groupRefers.iterator();

            while(var7.hasNext()) {
               GroupRefer refer = (GroupRefer)var7.next();
               boolean b = this.complete(refer.getReferedGroupId());
               if(!b) {
                  return false;
               }
            }
         }

         complete = this.dbTool.completeByGroupId(Integer.valueOf(groupId));
         return complete;
      }
   }
}
