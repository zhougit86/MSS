package com.meicloud.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;

public class RecursionKillJobUtil {

   private static Logger LOG = Logger.getLogger(RecursionKillJobUtil.class);


   private static Map getSubPids(Runtime rt, Set currentPids) {
      HashMap result = new HashMap();
      Iterator iter = currentPids.iterator();
      Process process = null;
      BufferedReader br = null;

      try {
         for(; iter.hasNext(); process.waitFor()) {
            String e = (String)iter.next();
            String cmd = "ps -ef|grep " + e + "|grep -v grep|awk \'{print $2\":\"$3}\'";
            LOG.info("kill job recursion cmd:" + cmd);
            process = rt.exec(new String[]{"/bin/sh", "-c", cmd});
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while(true) {
               String line = br.readLine();
               if(line == null) {
                  break;
               }

               String[] array = line.split(":");
               if(e.equals(array[1])) {
                  result.put(array[0], array[1]);
               }
            }
         }
      } catch (Exception var18) {
         LOG.error(var18.getMessage());
         LOG.error(var18);
      } finally {
         try {
            if(br != null) {
               br.close();
            }
         } catch (Exception var17) {
            LOG.error(var17.getMessage());
            LOG.error(var17);
         }

         if(process != null) {
            process.destroy();
         }

      }

      return result;
   }

   private List recursionPids(String grepPattern) {
      ArrayList result = new ArrayList();
      BufferedReader br = null;
      Process process = null;

      try {
         String e = " ps -ef" + grepPattern + "|grep -v grep|awk \'{print $2\":\"$3}\'";
         LOG.info("kill job recursionPids cmd:" + e);
         Runtime rt = Runtime.getRuntime();
         process = rt.exec(new String[]{"/bin/sh", "-c", e});
         process.waitFor();
         br = new BufferedReader(new InputStreamReader(process.getInputStream()));
         HashMap pidMapLevel0 = new HashMap();

         while(true) {
            String pidMapLevel1 = br.readLine();
            if(pidMapLevel1 == null) {
               Map pidMapLevel11 = null;
               if(pidMapLevel0 != null && !pidMapLevel0.isEmpty()) {
                  result.addAll(pidMapLevel0.keySet());
                  pidMapLevel11 = getSubPids(rt, pidMapLevel0.keySet());
               }

               Map pidMapLevel2 = null;
               if(pidMapLevel11 != null && !pidMapLevel11.isEmpty()) {
                  result.addAll(pidMapLevel11.keySet());
                  pidMapLevel2 = getSubPids(rt, pidMapLevel11.keySet());
               }

               Map pidMapLevel3 = null;
               if(pidMapLevel2 != null && !pidMapLevel2.isEmpty()) {
                  result.addAll(pidMapLevel2.keySet());
                  pidMapLevel3 = getSubPids(rt, pidMapLevel2.keySet());
               }

               if(pidMapLevel3 != null && !pidMapLevel3.isEmpty()) {
                  result.addAll(pidMapLevel3.keySet());
               }

               Collections.reverse(result);
               break;
            }

            pidMapLevel0.put(pidMapLevel1.split(":")[0], pidMapLevel1.split(":")[1]);
         }
      } catch (Exception var19) {
         LOG.error(var19.getMessage());
      } finally {
         try {
            if(br != null) {
               br.close();
            }
         } catch (Exception var18) {
            LOG.error(var18.getMessage());
         }

         if(process != null) {
            process.destroy();
         }

      }

      return result;
   }

   public int killRecursion(String grepPattern) {
      int exitValue = 0;
      Process process = null;
      if("|grep".equals(grepPattern.trim())) {
         LOG.info("no effective pattern to find job pids.");
         return 0;
      } else {
         try {
            List e = this.recursionPids(grepPattern);
            LOG.info("kill recursion pattrn:" + grepPattern);
            LOG.info("find pids of itself and all children:" + e.toString());
            if(e != null) {
               Iterator var6 = e.iterator();

               while(var6.hasNext()) {
                  String pid = (String)var6.next();

                  try {
                     String e1 = "kill -9 " + pid;
                     LOG.info("kill job cmd:" + e1);
                     process = Runtime.getRuntime().exec(e1);
                     process.waitFor();
                     exitValue = process.exitValue();
                  } catch (Exception var12) {
                     LOG.error(var12.getMessage());
                  } finally {
                     if(process != null) {
                        process.destroy();
                     }

                  }
               }
            }
         } catch (Exception var14) {
            LOG.error(var14.getMessage());
            LOG.error(var14);
         }

         return exitValue;
      }
   }
}
