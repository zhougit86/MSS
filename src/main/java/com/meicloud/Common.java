package com.meicloud;

import java.util.HashMap;
import java.util.Map;

public class Common {

   public static final String REMARK_ADD = "ADD";
   public static final String REMARK_UPDATE = "UPDATE";
   public static final String REMARK_DELETE = "DELETE";
   public static final String SERVER_X = "PORTAL";
   public static final String SERVER_Y = "ES";
   public static final String SERVER_XY = "comprehensive".toUpperCase();
   public static final String SESSION_USER = "user";
   public static final String SESSION_ROLES = "roles";
   public static final String SESSION_PERMS = "perms";
   public static final String SESSION_IFADMIN = "ifAdmin";
   public static final String dataSource_default = "default";
   public static final String USER_SYSTEM = "SYSTEM";
   public static final String ROLE_ADMIN = "ADMIN";
   public static final String ROLE_MANAGER = "MANAGER";
   public static final String ROLE_COMMON = "COMMON";
   public static final String IS_YES = "1";
   public static final String IS_NO = "0";
   public static final String PERM_MENU = "X";
   public static final String PERM_OPERATION = "Y";
   public static final String TOTAL_JOB_COUNT_KEY = "总数量";
   public static final String ENABLE_JOB_COUNT_KEY = "启用数";
   public static final String DISABLE_JOB_COUNT_KEY = "禁用数";
   public static final String TOPIC_COUNT_KEY = "主题数";
   public static final Map allParmMap = new HashMap();
   public static final String TYPE_TOPIC = "M";
   public static final String TYPE_LEVEL = "N";
   public static final String TYPE_QUEUE = "X";
   public static final String TYPE_GROUP = "Y";
   public static final String TYPE_JOB = "Z";
   public static final String TYPE_TAGS = "F";
   public static final int JOB_WAITING_CHECK = 0;
   public static final int JOB_WAITING = 1;
   public static final int JOB_RUNNING = 2;
   public static final int JOB_ERROR = 3;
   public static final int JOB_CANCEL = 4;
   public static final int JOB_SUCCESS = 5;
   public static final int JOB_REJECTED = 6;
   public static final int JOB_FORBIDDEN = -1;
   public static final String KETTLE_LOG_SERVER = "kettleLogs/log/detail";
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
   public static final int WAIT_RUNTIME_TODAY = 0;
   public static final int WAIT_QUEUE_TODAY = 1;
   public static final int RUNING_COMMON_TODAY = 2;
   public static final int ERROR_TODAY = 3;
   public static final int CANCEL_TODAY = 4;
   public static final int SUCCESS_TODAY = 5;
   public static final int RUNNING_EXPIRE_TODAY = 6;


}
