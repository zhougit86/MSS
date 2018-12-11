package com.meicloud.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Utils {

   private static Logger LOG = Logger.getLogger("Utils");


   public static boolean isEmptyStr(String str) {
      if(str == null) {
         return true;
      } else {
         str = str.trim();
         return str.isEmpty();
      }
   }

   public static List removeDuplicate(List arlList) {
      if(isEmpityCollection(arlList)) {
         return new ArrayList();
      } else {
         HashSet h = new HashSet(arlList);
         arlList.clear();
         arlList.addAll(h);
         return arlList;
      }
   }

   public static boolean isNotNull(Object obj) {
      return obj != null;
   }

   public static boolean isEmpityCollection(Collection c) {
      return c == null || c.isEmpty();
   }

   public static boolean isEmptyStr(Object s) {
      return s == null || s.toString().trim().length() == 0;
   }

   public static boolean isEmptyString(Object s) {
      return s == null || s.toString().trim().length() == 0 || s.toString().trim().equalsIgnoreCase("null") || s.toString().trim().equalsIgnoreCase("<null>");
   }

   public static boolean isEmpityMap(Map map) {
      return map == null?true:map.isEmpty();
   }

   public static String filterNullValue(String v) {
      return isEmptyString(v)?"":v;
   }

   public static boolean saveFileToDisk(String imageInf, String path) {
      FileOutputStream out = null;

      try {
         if(imageInf == null) {
            return false;
         }

         byte[] e = Base64.decodeBase64(imageInf);

         for(int i = 0; i < e.length; ++i) {
            if(e[i] < 0) {
               e[i] = (byte)(e[i] + 256);
            }
         }

         out = new FileOutputStream(path);
         out.write(e);
         out.flush();
         return true;
      } catch (IOException var14) {
         LOG.error(var14.getMessage());
      } finally {
         try {
            if(out != null) {
               out.close();
            }
         } catch (Exception var13) {
            LOG.error(var13.getMessage());
         }

      }

      return false;
   }

   public static Boolean CheckParamValidity(String objValue, String tip) {
      if(isEmptyString(objValue)) {
         return Boolean.valueOf(false);
      } else {
         String regExp = "^((13[0-9])|(15[^4,\\D])|(17[^4,\\D])|(18[0,5-9]))\\d{8}$";
         if("1".equals(tip)) {
            regExp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
         } else if("2".equals(tip)) {
            regExp = "[1-9]\\d{5}(?!\\d)";
         }

         Pattern p = Pattern.compile(regExp);
         Matcher m = p.matcher(filterNullValue(objValue));
         return Boolean.valueOf(m.matches());
      }
   }

   public static String getRemoteIp() {
      HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
      return request.getHeader("x-forwarded-for") == null?request.getRemoteAddr():request.getHeader("x-forwarded-for");
   }

   public static void main(String[] str) {
      System.out.print("11");
   }
}
