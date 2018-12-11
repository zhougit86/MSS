package com.meicloud.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

   private static Logger LOG = Logger.getLogger("StringUtils");
   private static final char SEPARATOR = '_';
   private static final String CHARSET_NAME = "UTF-8";


   public static byte[] getBytes(String str) {
      if(str != null) {
         try {
            return str.getBytes("UTF-8");
         } catch (UnsupportedEncodingException var2) {
            return null;
         }
      } else {
         return null;
      }
   }

   public static String toString(byte[] bytes) {
      try {
         return new String(bytes, "UTF-8");
      } catch (UnsupportedEncodingException var2) {
         return "";
      }
   }

   public static boolean inString(String str, String ... strs) {
      if(str != null) {
         String[] var5 = strs;
         int var4 = strs.length;

         for(int var3 = 0; var3 < var4; ++var3) {
            String s = var5[var3];
            if(str.equals(trim(s))) {
               return true;
            }
         }
      }

      return false;
   }

   public static String replaceHtml(String html) {
      if(isBlank(html)) {
         return "";
      } else {
         String regEx = "<.+?>";
         Pattern p = Pattern.compile(regEx);
         Matcher m = p.matcher(html);
         String s = m.replaceAll("");
         return s;
      }
   }

   public static String escapeHtml(String str) {
      if(isBlank(str)) {
         return "";
      } else {
         str = str.replace("&", "&amp;");
         str = str.replace(" ", "&nbsp;");
         str = str.replace("<", "&lt;");
         str = str.replace(">", "&gt;");
         str = str.replace("\"", "&quot;");
         return str;
      }
   }

   public static String replaceMobileHtml(String html) {
      return html == null?"":html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
   }

   public static String specialCharFilter(String str) {
      String regEx = "[`~!@#$%^&*()+=|{}\':;\',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
      Pattern p = Pattern.compile(regEx);
      Matcher m = p.matcher(str);
      return m.replaceAll("").trim();
   }

   public static String abbr(String str, int length) {
      if(str == null) {
         return "";
      } else {
         try {
            StringBuilder e = new StringBuilder();
            int currentLength = 0;
            char[] var7;
            int var6 = (var7 = replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()).length;

            for(int var5 = 0; var5 < var6; ++var5) {
               char c = var7[var5];
               currentLength += String.valueOf(c).getBytes("GBK").length;
               if(currentLength > length - 3) {
                  e.append("...");
                  break;
               }

               e.append(c);
            }

            return e.toString();
         } catch (UnsupportedEncodingException var8) {
            LOG.error(var8.getMessage());
            return "";
         }
      }
   }

   public static Double toDouble(Object val) {
      if(val == null) {
         return Double.valueOf(0.0D);
      } else {
         try {
            return Double.valueOf(trim(val.toString()));
         } catch (Exception var2) {
            return Double.valueOf(0.0D);
         }
      }
   }

   public static Float toFloat(Object val) {
      return Float.valueOf(toDouble(val).floatValue());
   }

   public static Long toLong(Object val) {
      return Long.valueOf(toDouble(val).longValue());
   }

   public static Integer toInteger(Object val) {
      return Integer.valueOf(toLong(val).intValue());
   }

   public static String toCamelCase(String s) {
      if(s == null) {
         return null;
      } else {
         s = s.toLowerCase();
         StringBuilder sb = new StringBuilder(s.length());
         boolean upperCase = false;

         for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if(c == 95) {
               upperCase = true;
            } else if(upperCase) {
               sb.append(Character.toUpperCase(c));
               upperCase = false;
            } else {
               sb.append(c);
            }
         }

         return sb.toString();
      }
   }

   public static String toCapitalizeCamelCase(String s) {
      if(s == null) {
         return null;
      } else {
         s = toCamelCase(s);
         return s.substring(0, 1).toUpperCase() + s.substring(1);
      }
   }

   public static String toUnderScoreCase(String s) {
      if(s == null) {
         return null;
      } else {
         StringBuilder sb = new StringBuilder();
         boolean upperCase = false;

         for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            boolean nextUpperCase = true;
            if(i < s.length() - 1) {
               nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if(i > 0 && Character.isUpperCase(c)) {
               if(!upperCase || !nextUpperCase) {
                  sb.append('_');
               }

               upperCase = true;
            } else {
               upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
         }

         return sb.toString();
      }
   }

   public static String jsGetVal(String objectString) {
      StringBuilder result = new StringBuilder();
      StringBuilder val = new StringBuilder();
      String[] vals = split(objectString, ".");

      for(int i = 0; i < vals.length; ++i) {
         val.append("." + vals[i]);
         result.append("!" + val.substring(1) + "?\'\':");
      }

      result.append(val.substring(1));
      return result.toString();
   }

   public static List splitIds(String ids) {
      if(isBlank(ids)) {
         return null;
      } else {
         ArrayList list = new ArrayList();
         String[] var5;
         int var4 = (var5 = ids.split(",")).length;

         for(int var3 = 0; var3 < var4; ++var3) {
            String s = var5[var3];
            if(isNotBlank(s)) {
               list.add(s);
            }
         }

         if(list.size() == 0) {
            return null;
         } else {
            return list;
         }
      }
   }
}
