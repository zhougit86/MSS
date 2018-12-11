package com.meicloud.utils;

import com.meicloud.model.Queue;
import com.meicloud.model.Server;
import com.meicloud.utils.OutputConsumerThread;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class ShellUtil {

   private static Logger LOG = Logger.getLogger(ShellUtil.class);


   public void runSSH(String cmd) throws Exception {
      Process ps = null;
      BufferedReader br = null;

      try {
         LOG.info("command:" + cmd);
         ps = Runtime.getRuntime().exec(cmd);
         br = new BufferedReader(new InputStreamReader(ps.getInputStream()));

         while(true) {
            String e = br.readLine();
            if(e == null) {
               return;
            }

            LOG.info(e);
         }
      } catch (Exception var12) {
         throw var12;
      } finally {
         try {
            if(br != null) {
               br.close();
            }

            if(ps != null) {
               ps.destroy();
            }
         } catch (Exception var11) {
            LOG.error(var11);
         }

      }
   }

   public boolean processJob(String cmd, String stdLogFile, String errorLogFile, Server server, Queue queue) {
      boolean result = false;
      Process ps = null;
      InputStream stdStream = null;
      InputStream errorStream = null;

      try {
         Runtime e = Runtime.getRuntime();
         ps = e.exec(cmd);
         stdStream = ps.getInputStream();
         errorStream = ps.getErrorStream();
         OutputConsumerThread out1 = new OutputConsumerThread(stdStream, stdLogFile, cmd, server, queue);
         OutputConsumerThread out2 = new OutputConsumerThread(errorStream, errorLogFile, (String)null, server, queue);
         Thread t1 = new Thread(out1);
         Thread t2 = new Thread(out2);
         t1.start();
         t2.start();
         t1.join();
         t2.join();
         result = out1.getResult();
      } catch (Exception var23) {
         LOG.error(var23);
      } finally {
         try {
            if(stdStream != null) {
               stdStream.close();
            }

            if(errorStream != null) {
               errorStream.close();
            }

            if(ps != null) {
               ps.destroy();
            }
         } catch (Exception var22) {
            LOG.error(var22);
         }

      }

      return result;
   }
}
