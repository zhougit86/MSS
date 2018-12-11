package com.meicloud.utils;

import com.meicloud.utils.RecursionKillJobUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;

public class KillJobUtil {

   private static Logger LOG = Logger.getLogger(KillJobUtil.class);


   public int killJob(String jobServerFile, String appendParams) throws Exception {
      int exitCode = 0;
      String[] cmdArray;
      if(appendParams != null && appendParams.trim().length() > 0) {
         try {
            StringBuffer e = new StringBuffer("ps -ef| grep kitchen");
            cmdArray = appendParams.split(" ");
            String[] psSub = cmdArray;
            int processBuilderSub = cmdArray.length;

            String processBuilder;
            for(int ps = 0; ps < processBuilderSub; ++ps) {
               processBuilder = psSub[ps];
               processBuilder = processBuilder.trim();
               if(processBuilder.startsWith("-")) {
                  processBuilder = processBuilder.substring(1, processBuilder.length());
               }

               e.append("|grep ");
               e.append(processBuilder);
            }

            e.append("|grep -v grep|awk \'{print $2}\'|xargs kill -s 9");
            processBuilder = e.toString();
            LOG.info("kill killSubJobPattern:" + processBuilder);
            String[] var14 = new String[]{"/bin/sh", "-c", processBuilder};
            ProcessBuilder var16 = new ProcessBuilder(var14);
            Process var17 = var16.start();
            exitCode = var17.waitFor();
            LOG.info("cmdArraySub:" + var14[2] + "  exitCodeSub:" + exitCode);
         } catch (Exception var11) {
            LOG.error(var11);
         }
      }

      try {
         String var12 = " ps -ef| grep kitchen | grep " + jobServerFile + " |grep -v grep|awk \'{print $2}\'|xargs kill -s 9";
         LOG.info("kill pattern:" + var12);
         cmdArray = new String[]{"/bin/sh", "-c", var12};
         ProcessBuilder var13 = new ProcessBuilder(cmdArray);
         Process var15 = var13.start();
         exitCode = var15.waitFor();
         LOG.info("cmdarray:" + cmdArray[2] + "  exitCode:" + exitCode);
      } catch (Exception var10) {
         LOG.error(var10);
      }

      return exitCode;
   }

   public int killJobRecusion(String jobServerFile, String appendParams) throws Exception {
      int exitCode = 0;
      RecursionKillJobUtil recursionKillJobUtil = new RecursionKillJobUtil();
      if(appendParams != null && appendParams.trim().length() > 0) {
         try {
            StringBuffer e = new StringBuffer();
            String[] grepArray = appendParams.split(" ");
            String[] var10 = grepArray;
            int var9 = grepArray.length;

            for(int var8 = 0; var8 < var9; ++var8) {
               String grep = var10[var8];
               grep = grep.trim();
               if(grep.startsWith("-")) {
                  grep = grep.substring(1, grep.length());
               }

               if(!"".equals(grep)) {
                  e.append("|grep ");
                  e.append(grep);
               }
            }

            exitCode = recursionKillJobUtil.killRecursion(e.toString());
         } catch (Exception var12) {
            LOG.error(var12);
         }
      }

      try {
         String var13 = "|grep " + jobServerFile.trim();
         exitCode = recursionKillJobUtil.killRecursion(var13.toString());
      } catch (Exception var11) {
         LOG.error(var11);
      }

      return exitCode;
   }

   public int getStatuCode(HttpResponse response) {
      boolean statuCode = false;
      int statuCode1;
      if(response != null && response.getStatusLine() != null) {
         statuCode1 = response.getStatusLine().getStatusCode();
      } else {
         statuCode1 = -1;
      }

      return statuCode1;
   }

   public String getContent(HttpResponse response) {
      String result = "";
      BufferedReader br = null;

      try {
         if(response == null || response.getEntity() == null || response.getEntity().getContent() == null) {
            return "";
         }

         br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
         StringBuffer e = new StringBuffer();
         String line = null;

         while((line = br.readLine()) != null) {
            e.append(line);
            e.append("\r\n");
         }

         result = new String(e.toString().getBytes(), "UTF-8");
      } catch (IOException var15) {
         LOG.error(var15.getMessage());
      } finally {
         if(br != null) {
            try {
               br.close();
            } catch (IOException var14) {
               LOG.error(var14.getMessage());
            }
         }

      }

      return result;
   }
}
