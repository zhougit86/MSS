package com.meicloud.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

   private static DateHelper dateHelper;


   public static DateHelper getInstance() {
      if(dateHelper == null) {
         dateHelper = new DateHelper();
      }

      return dateHelper;
   }

   public static String formatDate(Date date, String format) {
      SimpleDateFormat formatt = new SimpleDateFormat(format);
      return formatt.format(date);
   }

   public static Date parseDate(String date, String format) throws ParseException {
      SimpleDateFormat formatt = new SimpleDateFormat(format);
      return formatt.parse(date);
   }

   public static Date getYesterday() {
      Calendar cal = Calendar.getInstance();
      cal.add(6, -3);
      Date d = cal.getTime();
      return d;
   }

   public static void main(String[] args) {
      System.out.println(formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
   }
}
