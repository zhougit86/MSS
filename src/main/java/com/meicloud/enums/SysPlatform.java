package com.meicloud.enums;


public enum SysPlatform {

   Any("Any", 0, "any"),
   Linux("Linux", 1, "Linux"),
   Mac_OS("Mac_OS", 2, "Mac OS"),
   Mac_OS_X("Mac_OS_X", 3, "Mac OS X"),
   Windows("Windows", 4, "Windows"),
   OS2("OS2", 5, "OS/2"),
   Solaris("Solaris", 6, "Solaris"),
   SunOS("SunOS", 7, "SunOS"),
   MPEiX("MPEiX", 8, "MPE/iX"),
   HP_UX("HP_UX", 9, "HP-UX"),
   AIX("AIX", 10, "AIX"),
   OS390("OS390", 11, "OS/390"),
   FreeBSD("FreeBSD", 12, "FreeBSD"),
   Irix("Irix", 13, "Irix"),
   Digital_Unix("Digital_Unix", 14, "Digital Unix"),
   NetWare_411("NetWare_411", 15, "NetWare"),
   OSF1("OSF1", 16, "OSF1"),
   OpenVMS("OpenVMS", 17, "OpenVMS"),
   Others("Others", 18, "Others");
   private String description;
   // $FF: synthetic field
   private static final SysPlatform[] ENUM$VALUES = new SysPlatform[]{Any, Linux, Mac_OS, Mac_OS_X, Windows, OS2, Solaris, SunOS, MPEiX, HP_UX, AIX, OS390, FreeBSD, Irix, Digital_Unix, NetWare_411, OSF1, OpenVMS, Others};


   private SysPlatform(String var1, int var2, String desc) {
      this.description = desc;
   }

   public String toString() {
      return this.description;
   }
}
