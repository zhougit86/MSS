package com.meicloud.enums;


public enum LengthLimit {

   GoupgLevel_levelName("GoupgLevel_levelName", 0, "50"),
   Account_account("Account_account", 1, "128"),
   Account_realName("Account_realName", 2, "32"),
   Topic_name("Topic_name", 3, "16"),
   Topic_desc("Topic_desc", 4, "128"),
   Chargor_name("Chargor_name", 5, "128"),
   Chargor_phone("Chargor_phone", 6, "32"),
   Chargor_email("Chargor_email", 7, "128"),
   RetryRule_retryName("RetryRule_retryName", 8, "256"),
   RetryRule_retryDesc("RetryRule_retryDesc", 9, "128"),
   Server_name("Server_name", 10, "255"),
   Server_desc("Server_desc", 11, "255"),
   Server_ip("Server_ip", 12, "50"),
   Server_appPort("Server_appPort", 13, "64"),
   Server_logAppPort("Server_logAppPort", 14, "64"),
   Server_svnLogPath("Server_svnLogPath", 15, "300"),
   Queue_queueName("Queue_queueName", 16, "100"),
   Queue_queueCode("Queue_queueCode", 17, "50"),
   Queue_svnUrl("Queue_svnUrl", 18, "300"),
   Queue_svnUserNam("Queue_svnUserNam", 19, "64"),
   Queue_svnPassWord("Queue_svnPassWord", 20, "64"),
   Group_groupName("Group_groupName", 21, "256"),
   Group_desc("Group_desc", 22, "128"),
   Group_logReson("Group_logReson", 23, ""),
   Job_jobName("Job_jobName", 24, "256"),
   Job_svnFile("Job_svnFile", 25, "256"),
   Job_appendParams("Job_appendParams", 26, "512");
   private String description;
   // $FF: synthetic field
   private static final LengthLimit[] ENUM$VALUES = new LengthLimit[]{GoupgLevel_levelName, Account_account, Account_realName, Topic_name, Topic_desc, Chargor_name, Chargor_phone, Chargor_email, RetryRule_retryName, RetryRule_retryDesc, Server_name, Server_desc, Server_ip, Server_appPort, Server_logAppPort, Server_svnLogPath, Queue_queueName, Queue_queueCode, Queue_svnUrl, Queue_svnUserNam, Queue_svnPassWord, Group_groupName, Group_desc, Group_logReson, Job_jobName, Job_svnFile, Job_appendParams};


   private LengthLimit(String var1, int var2, String desc) {
      this.description = desc;
   }

   public String toString() {
      return this.description;
   }
}
