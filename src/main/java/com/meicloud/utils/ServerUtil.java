package com.meicloud.utils;

import com.meicloud.enums.SysPlatform;

public class ServerUtil {

   private static String OS = System.getProperty("os.name").toLowerCase();
   private static ServerUtil _instance = new ServerUtil();
   private SysPlatform platform;


   public static boolean isLinux() {
      return OS.indexOf("linux") >= 0;
   }

   public static boolean isMacOS() {
      return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") < 0;
   }

   public static boolean isMacOSX() {
      return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;
   }

   public static boolean isWindows() {
      return OS.indexOf("windows") >= 0;
   }

   public static boolean isOS2() {
      return OS.indexOf("os/2") >= 0;
   }

   public static boolean isSolaris() {
      return OS.indexOf("solaris") >= 0;
   }

   public static boolean isSunOS() {
      return OS.indexOf("sunos") >= 0;
   }

   public static boolean isMPEiX() {
      return OS.indexOf("mpe/ix") >= 0;
   }

   public static boolean isHPUX() {
      return OS.indexOf("hp-ux") >= 0;
   }

   public static boolean isAix() {
      return OS.indexOf("aix") >= 0;
   }

   public static boolean isOS390() {
      return OS.indexOf("os/390") >= 0;
   }

   public static boolean isFreeBSD() {
      return OS.indexOf("freebsd") >= 0;
   }

   public static boolean isIrix() {
      return OS.indexOf("irix") >= 0;
   }

   public static boolean isDigitalUnix() {
      return OS.indexOf("digital") >= 0 && OS.indexOf("unix") > 0;
   }

   public static boolean isNetWare() {
      return OS.indexOf("netware") >= 0;
   }

   public static boolean isOSF1() {
      return OS.indexOf("osf1") >= 0;
   }

   public static boolean isOpenVMS() {
      return OS.indexOf("openvms") >= 0;
   }

   public static SysPlatform getOSname() {
      if(isAix()) {
         _instance.platform = SysPlatform.AIX;
      } else if(isDigitalUnix()) {
         _instance.platform = SysPlatform.Digital_Unix;
      } else if(isFreeBSD()) {
         _instance.platform = SysPlatform.FreeBSD;
      } else if(isHPUX()) {
         _instance.platform = SysPlatform.HP_UX;
      } else if(isIrix()) {
         _instance.platform = SysPlatform.Irix;
      } else if(isLinux()) {
         _instance.platform = SysPlatform.Linux;
      } else if(isMacOS()) {
         _instance.platform = SysPlatform.Mac_OS;
      } else if(isMacOSX()) {
         _instance.platform = SysPlatform.Mac_OS_X;
      } else if(isMPEiX()) {
         _instance.platform = SysPlatform.MPEiX;
      } else if(isNetWare()) {
         _instance.platform = SysPlatform.NetWare_411;
      } else if(isOpenVMS()) {
         _instance.platform = SysPlatform.OpenVMS;
      } else if(isOS2()) {
         _instance.platform = SysPlatform.OS2;
      } else if(isOS390()) {
         _instance.platform = SysPlatform.OS390;
      } else if(isOSF1()) {
         _instance.platform = SysPlatform.OSF1;
      } else if(isSolaris()) {
         _instance.platform = SysPlatform.Solaris;
      } else if(isSunOS()) {
         _instance.platform = SysPlatform.SunOS;
      } else if(isWindows()) {
         _instance.platform = SysPlatform.Windows;
      } else {
         _instance.platform = SysPlatform.Others;
      }

      return _instance.platform;
   }
}
