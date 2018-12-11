package com.meicloud.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public class StringUtil {

   private static Logger LOG = Logger.getLogger(StringUtil.class);
   private static Pattern numericPattern = Pattern.compile("[0-9]{1,}");


   public static String replaceSysdate(String input) {
      try {
         if(input == null || "".equals(input.trim()) || !input.replaceAll(" ", "").toLowerCase().contains("%sysdate%")) {
            return input;
         }

         SimpleDateFormat e = new SimpleDateFormat("yyyyMMddHHmmss");
         String replace = e.format(new Date());
         input = input.replaceAll(" ", "").replaceAll("%sysdate%", replace).replaceAll("%SYSDATE%", replace);
      } catch (Exception var3) {
         LOG.error(var3);
      }

      return input;
   }

   public static String formatGroupFireTime(Date fireTime) {
      if(fireTime == null) {
         return "";
      } else {
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         return dateFormat.format(fireTime);
      }
   }

   public static String replaceByScheduleConfig(String input) {
      try {
         if(input == null || "".equals(input.trim()) || !input.contains("%")) {
            return input;
         }

         HashMap e = new HashMap();
         String tmp = input.substring(input.indexOf("%") + 1);

         for(int i = 0; tmp != null && tmp.contains("%"); ++i) {
            String key = "";
            if(i % 2 == 0) {
               key = tmp.substring(0, tmp.indexOf("%"));
               e.put(key.trim(), "%" + key + "%");
            }

            tmp = tmp.substring(tmp.indexOf("%") + 1);
         }
      } catch (Exception var5) {
         LOG.error(var5);
      }

      return input;
   }

   public static String toMD5(String input) {
      MessageDigest md5 = null;

      try {
         md5 = MessageDigest.getInstance("MD5");
      } catch (Exception var8) {
         return "";
      }

      char[] charArray = input.toCharArray();
      byte[] byteArray = new byte[charArray.length];

      for(int md5Bytes = 0; md5Bytes < charArray.length; ++md5Bytes) {
         byteArray[md5Bytes] = (byte)charArray[md5Bytes];
      }

      byte[] var9 = md5.digest(byteArray);
      StringBuffer hexValue = new StringBuffer();

      for(int i = 0; i < var9.length; ++i) {
         int val = var9[i] & 255;
         if(val < 16) {
            hexValue.append("0");
         }

         hexValue.append(Integer.toHexString(val));
      }

      return hexValue.toString();
   }

   public static boolean isNumeric(String input) {
      return numericPattern.matcher(input).matches();
   }

   public static String todayYYYYMMdd() {
      String result = "20150623";

      try {
         SimpleDateFormat e = new SimpleDateFormat("yyyyMMdd");
         result = e.format(new Date());
      } catch (Exception var2) {
         LOG.error(var2);
      }

      return result;
   }

   public static boolean isBlank(String input) {
      if(input == null) {
         return true;
      } else {
         input = input.trim();
         return "".equals(input);
      }
   }

   public static String formJobName(String serverFileName) {
      String result = "";
      if(serverFileName != null && !"".equals(serverFileName.trim())) {
         if(serverFileName.indexOf("/") == -1) {
            return serverFileName;
         } else {
            result = serverFileName.substring(serverFileName.lastIndexOf("/") + 1);
            if(result.indexOf(".kjb") != -1) {
               result = result.substring(0, result.indexOf(".kjb"));
            }

            return result;
         }
      } else {
         return result;
      }
   }
}
