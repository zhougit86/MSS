package com.meicloud.utils;

import java.util.HashMap;
import java.util.Map;

public class PermisionMap {

   protected static final Map kvmap = new HashMap();


   public PermisionMap() {
      if(kvmap.isEmpty()) {
         init();
      }

   }

   public static void init() {
      kvmap.put("/MSS_DE/job/list.do", "@JOB_VIEW@");
      kvmap.put("/MSS_DE/job/batchAdd.do", "@JOB_ADD@");
      kvmap.put("/MSS_DE/job/add.do", "@JOB_ADD@");
      kvmap.put("/MSS_DE/job/edit.do", "@JOB_EDIT@");
      kvmap.put("/MSS_DE/job/update.do", "@JOB_EDIT@");
      kvmap.put("/MSS_DE/job/deleteByGroupId.do", "@JOB_DELETE@");
      kvmap.put("/MSS_DE/job/delete.do", "@JOB_DELETE@");
      kvmap.put("/MSS_DE/runJob/reRunJob.do", "@JOB_RUN@");
      kvmap.put("/MSS_DE/runJob/killJob.do", "@JOB_STOP@");
      kvmap.put("/MSS_DE/runJob/reRunGroup.do", "@GROUP_RUN@");
      kvmap.put("/MSS_DE/runJob/killGroup.do", "@GROUP_STOP@");
      kvmap.put("/MSS_DE/job/updateState.do", "@JOB_STATE@");
      kvmap.put("/MSS_DE/job/jobStateList.do", "@JOB_VIEW@");
      kvmap.put("/MSS_DE/index/index.do", "@GROUP_VIEW@");
      kvmap.put("/MSS_DE/group/list.do", "@GROUP_VIEW@");
      kvmap.put("/MSS_DE/group/add.do", "@GROUP_ADD@");
      kvmap.put("/MSS_DE/group/edit.do", "@GROUP_EDIT@");
      kvmap.put("/MSS_DE/group/delete.do", "@GROUP_DELETE@");
      kvmap.put("/MSS_DE/group/updateState.do", "@GROUP_STATE@");
      kvmap.put("/MSS_DE/log/kettleJobLogList.do", "@LOG_VIEW@");
      kvmap.put("/MSS_DE/tags/add.do", "@TAGS_ADD@");
      kvmap.put("/MSS_DE/tags/delete.do", "@TAGS_DELETE@");
      kvmap.put("/MSS_DE/job/online.do", "@JOB_ONLINE@");
      kvmap.put("/MSS_DE/jobTables/list.do", "@JOB_TABLES@");
      kvmap.put("/MSS_DE/server/serverList.do", "@NODE_MONITOR@");
      kvmap.put("/MSS_DE/jobExcel/import.do", "@JOB_IMPORT@");
      kvmap.put("/MSS_DE/jobExcel/export.do", "@JOB_EXPORT@");
      kvmap.put("/MSS_DE/account/edit.do", "@ACCOUNT_MGR@");
      kvmap.put("/MSS_DE/account/updateState.do", "@ACCOUNT_MGR@");
   }

   public static String URL2PERM(String url) {
      if(kvmap.isEmpty()) {
         init();
      }

      return kvmap.containsKey(url)?(String)kvmap.get(url):null;
   }
}
