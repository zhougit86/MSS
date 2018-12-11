package com.meicloud.utils;


public class Constant {

   public static final int JOB_DE_WAITING_CHECK = 0;
   public static final int JOB_DE_WAITING = 1;
   public static final int JOB_DE_RUNNING = 2;
   public static final int JOB_DE_ERROR = 3;
   public static final int JOB_DE_CANCEL = 4;
   public static final int JOB_DE_SUCCESS = 5;
   public static final int JOB_DE_REJECTED = 6;
   public static final int JOB_ES_NOT_REACH = 0;
   public static final int JOB_ES_ACCEPTED = 1;
   public static final int JOB_ES_RUNNING = 2;
   public static final int JOB_ES_ERROR = 3;
   public static final int JOB_ES_CANCEL = 4;
   public static final int JOB_ES_SUCCESS = 5;
   public static final int JOB_ES_REJECTED = 6;
   public static final int JOB_ES_FORBIDDEN = -1;
   public static final int SERVER_FREE = 0;
   public static final int SERVER_BUSY = 1;
   public static final int SERVER_DOWN = 2;
   public static final String MSG_KEY = "msg";
   public static final String RESULT_KEY = "result";
   public static final String STATUS_KEY = "status";
   public static final int SIMPLE_JOB_TRIGGER_DELAY = 3;
   public static final char GROUP_IDS_SPLIT_CHAR = ',';
   public static final int SEND_JOB_INTERVAL_IN_SECONDS = 90;
   public static final int MAX_RUNNING_JOB_COUNT_PERSERVER = 10;


}
