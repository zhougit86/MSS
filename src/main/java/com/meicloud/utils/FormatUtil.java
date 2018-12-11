package com.meicloud.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.time.DateFormatUtils;

public class FormatUtil extends DateFormatUtils {

   public static final Object EMPTY = "";
   private static Pattern ColumnPattern = Pattern.compile("_\\w");


   public static String formatDate(Date date, String pattern) {
      if(date == null) {
         return "";
      } else {
         if(pattern == null || "".equals(pattern.trim())) {
            pattern = "yyyy-MM-dd";
         }

         SimpleDateFormat sdf = new SimpleDateFormat(pattern);
         return sdf.format(date);
      }
   }

   public static String formatMonth(Date date) {
      return formatDate(date, "yyyy-MM");
   }

   public static String formatShortMonth(Date date) {
      return formatDate(date, "yyyyMM");
   }

   public static String formatShortDate(Date date) {
      return formatDate(date, "yyyyMMdd");
   }

   public static String formatDate(Date date) {
      return formatDate(date, "yyyy-MM-dd");
   }

   public static String formatTime(Date date) {
      return formatDate(date, "HH:mm:ss");
   }

   public static String formatDateTime(Date date) {
      return formatDate(date, "yyyy-MM-dd HH:mm:ss");
   }

   public static String formatTimestamp(Date date) {
      return formatDate(date, "yyyy-MM-dd HH:mm:ss.SSS");
   }

   public static Date parseDate(String date, String pattern) {
      if(date != null && !date.trim().equals("")) {
         SimpleDateFormat formatter = new SimpleDateFormat(pattern);
         formatter.setLenient(false);

         try {
            return formatter.parse(date);
         } catch (ParseException var4) {
            return null;
         }
      } else {
         return null;
      }
   }

   public static Date parseShortDate(String date) {
      return parseDate(date, "yyyyMMdd");
   }

   public static Date parseDate(String date) {
      return parseDate(date, "yyyy-MM-dd");
   }

   public static Date parseDateTime(String datetime) {
      return parseDate(datetime, "yyyy-MM-dd HH:mm:ss");
   }

   public static Date parseTimestamp(String timestamp) {
      return parseDate(timestamp, "yyyy-MM-dd HH:mm:ss.SSS");
   }

   public static String format(Long times) {
      Date date = times == null?null:new Date(times.longValue());
      return formatDateTime(date);
   }

   public static String format(Long times, String pattern) {
      Date date = times == null?null:new Date(times.longValue());
      return formatDate(date, pattern);
   }

   public static String formatDate(Long times) {
      return format(times, "yyyy-MM-dd");
   }

   public static String formatTime(Long times) {
      return format(times, "HH:mm:ss");
   }

   public static String formatDateTime(Long times) {
      return format(times, "yyyy-MM-dd HH:mm:ss");
   }

   public static String formatDateTimeMin(Long times) {
      return format(times, "yyyy-MM-dd HH:mm");
   }

   public static Date parse(Long times) {
      return times == null?null:new Date(times.longValue());
   }

   public static String formatMoney(double menoy) {
      return formatNumber(menoy, 2);
   }

   public static String formatNumber(double decimal, int fraction) {
      NumberFormat nf = NumberFormat.getInstance();
      nf.setMaximumFractionDigits(fraction);
      nf.setMinimumFractionDigits(fraction);
      return nf.format(decimal);
   }

   public static String formatNumber(double decimal) {
      return formatNumber(decimal, 0);
   }

   public static String formatNumber(Number number, int maximumFractionDigits, int minimumFractionDigits) {
      if(number == null) {
         return "0";
      } else {
         NumberFormat nf = NumberFormat.getInstance();
         nf.setMaximumFractionDigits(maximumFractionDigits);
         nf.setMinimumFractionDigits(minimumFractionDigits);
         return nf.format(number);
      }
   }

   public static String toProperty(String column) {
      String s = column.toLowerCase();
      Matcher m = ColumnPattern.matcher(column);
      if(!m.find()) {
         return s;
      } else {
         StringBuffer sb = new StringBuffer(100);

         int index;
         for(index = 0; m.find(index); index = m.end()) {
            sb.append(s.substring(index, m.start()));
            sb.append(m.group().substring(1).toUpperCase());
         }

         sb.append(s.substring(index));
         return sb.toString();
      }
   }

   public static String escapeSQLChar(String str) {
      return str != null && !"".equals(str)?str.replaceAll("/", "//").replaceAll("_", "/_").replaceAll("%", "/%"):str;
   }
}
