package com.meicloud.utils;

import com.meicloud.model.MonitorInfoBean;
import com.sun.management.OperatingSystemMXBean;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;

public class SystemInfo {

   public static MonitorInfoBean getMonitorInfoBean() throws Exception {
      short kb = 1024;
      long totalMemory = Runtime.getRuntime().totalMemory() / (long)kb;
      long freeMemory = Runtime.getRuntime().freeMemory() / (long)kb;
      long maxMemory = Runtime.getRuntime().maxMemory() / (long)kb;
      OperatingSystemMXBean osmxb = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
      String osName = System.getProperty("os.name");
      long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / (long)kb;
      long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize() / (long)kb;
      long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / (long)kb;
      long totalvirtualMemory = osmxb.getTotalSwapSpaceSize() / (long)kb;
      System.out.println("freePhysicalMemorySize------" + freePhysicalMemorySize);
      System.out.println("totalvirtualMemory------" + totalvirtualMemory);
      Double usage = Double.valueOf(0.0D);
      if(totalvirtualMemory > 0L) {
         usage = Double.valueOf(Double.valueOf(1.0D - (double)freePhysicalMemorySize * 1.0D / (double)totalvirtualMemory).doubleValue() * 100.0D);
      }

      BigDecimal b1 = new BigDecimal(usage.doubleValue());
      double memoryUsage = 0.0D;
      if(usage.doubleValue() > 0.0D) {
         memoryUsage = b1.setScale(2, 4).doubleValue();
      }

      ThreadGroup parentThread;
      for(parentThread = Thread.currentThread().getThreadGroup(); parentThread.getParent() != null; parentThread = parentThread.getParent()) {
         ;
      }

      int totalThread = parentThread.activeCount();
      MonitorInfoBean infoBean = new MonitorInfoBean();
      infoBean.setFreeMemory(freeMemory);
      infoBean.setFreePhysicalMemorySize(freePhysicalMemorySize);
      infoBean.setMaxMemory(maxMemory);
      infoBean.setOsName(osName);
      infoBean.setTotalMemory(totalMemory);
      infoBean.setTotalMemorySize(totalMemorySize);
      infoBean.setTotalThread(totalThread);
      infoBean.setUsedMemory(usedMemory);
      infoBean.setMemoryUsage(memoryUsage);
      infoBean.setDiskUsage(getDiskUsage());
      return infoBean;
   }

   private static double getDiskUsage() throws Exception {
      double totalHD = 0.0D;
      double usedHD = 0.0D;
      String osName = System.getProperty("os.name");
      if(!osName.toLowerCase().contains("windows") && !osName.toLowerCase().contains("win")) {
         Runtime var20 = Runtime.getRuntime();
         Process p = var20.exec("df -hl");
         BufferedReader var21 = null;

         try {
            var21 = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String precent = null;
            String[] var25 = null;

            while((precent = var21.readLine()) != null) {
               int var24 = 0;
               var25 = precent.split(" ");
               String[] var30 = var25;
               int var13 = var25.length;

               for(int var29 = 0; var29 < var13; ++var29) {
                  String var28 = var30[var29];
                  if(var28.trim().length() != 0) {
                     ++var24;
                     if(var28.indexOf("G") != -1) {
                        if(var28.indexOf("Gi") != -1) {
                           if(var24 == 2 && !var28.equals("") && !var28.equals("0")) {
                              totalHD += Double.parseDouble(var28.substring(0, var28.length() - 2)) * 1024.0D;
                           }

                           if(var24 == 3 && !var28.equals("none") && !var28.equals("0")) {
                              usedHD += Double.parseDouble(var28.substring(0, var28.length() - 2)) * 1024.0D;
                           }
                        } else {
                           if(var24 == 2 && !var28.equals("") && !var28.equals("0")) {
                              totalHD += Double.parseDouble(var28.substring(0, var28.length() - 1)) * 1024.0D;
                           }

                           if(var24 == 3 && !var28.equals("none") && !var28.equals("0")) {
                              usedHD += Double.parseDouble(var28.substring(0, var28.length() - 1)) * 1024.0D;
                           }
                        }
                     }

                     if(var28.indexOf("M") != -1) {
                        if(var24 == 2 && !var28.equals("") && !var28.equals("0")) {
                           totalHD += Double.parseDouble(var28.substring(0, var28.length() - 1));
                        }

                        if(var24 == 3 && !var28.equals("none") && !var28.equals("0")) {
                           usedHD += Double.parseDouble(var28.substring(0, var28.length() - 1));
                        }
                     }
                  }
               }
            }
         } catch (Exception var18) {
            throw var18;
         } finally {
            var21.close();
         }

         double var22 = 0.0D;
         if(totalHD != 0.0D) {
            var22 = usedHD / totalHD * 100.0D;
         }

         BigDecimal var26 = BigDecimal.valueOf(var22);
         var22 = var26.setScale(2, 4).doubleValue();
         return var22;
      } else {
         long rt = 0L;
         long in = 0L;

         for(char strArray = 65; strArray <= 90; ++strArray) {
            String b1 = strArray + ":/";
            File tmp = new File(b1);
            if(tmp.exists()) {
               long total = tmp.getTotalSpace();
               long free = tmp.getFreeSpace();
               rt += total;
               in += free;
            }
         }

         double var23 = 0.0D;
         if(rt != 0L) {
            var23 = Double.valueOf(1.0D - (double)in * 1.0D / (double)rt).doubleValue() * 100.0D;
         }

         BigDecimal var27 = BigDecimal.valueOf(var23);
         var23 = var27.setScale(2, 4).doubleValue();
         return var23;
      }
   }
}
