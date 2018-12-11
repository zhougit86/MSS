package com.meicloud.utils;

import com.meicloud.model.Queue;
import com.meicloud.model.Server;
import com.meicloud.utils.IPUtil;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class OutputConsumerThread implements Runnable {

   private static Logger LOG = Logger.getLogger(OutputConsumerThread.class);
   private static final String FINISH_PATTERN = "- Kitchen - Finished!";
   private static final String TRUE_PATTERN = "(result=[true])";
   private static final String FALSE_PATTERN = "(result=[false])";
   private static final String SUCCESS_PATTERN = "- Finished job entry [SUCCESS] (result=[true])";
   private static final String SUCCESS_PATTERN_UP = "- Finished job entry [SUCCESS] (result=[true])".toUpperCase();
   private static final String ERROR_PATTERN = "- Kitchen - ERROR";
   private Server server;
   private Queue queue;
   private InputStream is;
   private String logFile;
   private String cmd;
   private boolean result = false;


   public OutputConsumerThread() {}

   public OutputConsumerThread(InputStream is, String logFile, String cmd, Server server, Queue queue) {
      this.is = is;
      this.logFile = logFile;
      this.cmd = cmd;
      this.server = server;
      this.queue = queue;
   }

   public void run() {
      if(this.cmd != null && !"".equals(this.cmd)) {
         this.processStdout();
      } else {
         this.processError();
      }

   }

   private void processStdout() {
      int falseSteps = 0;
      int trueSteps = 0;
      boolean finishFlag = false;
      boolean successFlag = false;
      boolean errorFlag = false;
      BufferedReader br = null;
      InputStreamReader ir = null;
      FileWriter fw = null;
      String line = null;

      try {
         ir = new InputStreamReader(this.is);
         br = new BufferedReader(ir);
         fw = new FileWriter(this.logFile, true);
         String e = "";
         if(this.queue != null) {
            e = e + "执行集群[" + this.queue.getQueueName() + "]\n";
         }

         String ip = IPUtil.getRealIp();
         if(this.server != null && this.server.getIp() != null && !this.server.getIp().equals("")) {
            ip = this.server.getIp();
         }

         e = e + "执行服务器IP[" + ip + "]\n";
         fw.write(e);
         fw.write("执行命令:[" + this.cmd + "]\n\n");
         fw.flush();

         while((line = br.readLine()) != null) {
            if(line.contains("(result=[false])")) {
               ++falseSteps;
               finishFlag = false;
            }

            if(line.contains("(result=[true])")) {
               ++trueSteps;
               finishFlag = false;
            }

            if(line.contains("- Kitchen - Finished!")) {
               finishFlag = true;
            }

            if(line.toUpperCase().contains(SUCCESS_PATTERN_UP)) {
               successFlag = true;
            }

            if(line.toUpperCase().contains("- Kitchen - ERROR")) {
               errorFlag = true;
            }

            LOG.info(line);
            fw.write(line + "\n");
         }
      } catch (Exception var76) {
         LOG.error(var76);
      } finally {
         this.result = trueSteps > 0 && falseSteps == 0 && finishFlag || successFlag && !errorFlag;
         String cataLog = this.cataLog(trueSteps, falseSteps, finishFlag, successFlag, errorFlag, this.result);

         try {
            if(fw == null) {
               fw = new FileWriter(this.logFile, true);
            }

            fw.write(cataLog);
            fw.flush();
         } catch (Exception var74) {
            LOG.error(var74);
         } finally {
            try {
               if(fw != null) {
                  fw.close();
               }

               if(br != null) {
                  br.close();
               }

               if(ir != null) {
                  ir.close();
               }

               if(this.is != null) {
                  this.is.close();
               }
            } catch (Exception var73) {
               LOG.error(var73);
            }

         }

      }

   }

   private void processError() {
      BufferedReader br = null;
      InputStreamReader ir = null;
      FileWriter fw = null;
      String line = null;

      try {
         ir = new InputStreamReader(this.is);
         br = new BufferedReader(ir);
         fw = new FileWriter(this.logFile, true);
         String e = IPUtil.getRealIp();
         if(this.server != null && this.server.getIp() != null && !this.server.getIp().equals("")) {
            e = this.server.getIp();
         }

         String tmp = "执行服务器IP[" + e + "]\n";
         fw.write(tmp);

         while((line = br.readLine()) != null) {
            LOG.info(line);
            fw.write(line + "\n");
            fw.flush();
         }
      } catch (Exception var15) {
         LOG.error(var15);
      } finally {
         try {
            if(fw != null) {
               fw.close();
            }

            if(br != null) {
               br.close();
            }

            if(ir != null) {
               ir.close();
            }

            if(this.is != null) {
               this.is.close();
            }
         } catch (Exception var14) {
            LOG.error(var14);
         }

      }

   }

   private String cataLog(int trueSteps, int falseSteps, boolean finishFlag, boolean successFlag, boolean errorFlag, boolean result) {
      String ip = "";

      try {
         ip = IPUtil.getRealIp();
      } catch (Exception var9) {
         LOG.error(var9);
      }

      StringBuffer sb = new StringBuffer();
      sb.append("\n\n***************************************************************\n\n");
      sb.append("\nFalse Pattern [(result=[false])]");
      sb.append("\nTrue Pattern [(result=[true])]");
      sb.append("\nSUCCESS Pattern [- Finished job entry [SUCCESS] (result=[true])]");
      sb.append("\nFinish Pattern [- Kitchen - Finished!]");
      sb.append("\nExecute Server IP [" + ip + "]");
      sb.append("\nTrueSteps [" + trueSteps + "]");
      sb.append("\nFalseSteps [" + falseSteps + "]");
      sb.append("\nFinishFlag [" + finishFlag + "]");
      sb.append("\nsuccessFlag [" + successFlag + "]");
      sb.append("\nerrorFlag [" + errorFlag + "]");
      sb.append("\n作业执行结果 [" + result + "]");
      sb.append("\n当且仅当满足以下条件时,作业才算成功：");
      sb.append("\n(trueSteps > 0 && falseSteps == 0 && finishFlag) || (successFlag && !errorFlag)");
      sb.append("\n\n\n**************************************************************\n\n");
      return sb.toString();
   }

   public boolean getResult() {
      return this.result;
   }
}
