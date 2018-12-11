package com.meicloud.utils;

import com.meicloud.utils.FormatUtil;
import com.meicloud.utils.StringUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

   private static Logger LOG = Logger.getLogger("DateUtils");
   private static String[] parsePatterns = new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
   public static final String DATA_FORMAT = "yyyy-MM-dd HH:mm:ss SSS";


   public static String getDate() {
      return getDate("yyyy-MM-dd");
   }

   public static String getDate(String pattern) {
      return DateFormatUtils.format(new Date(), pattern);
   }

   public static String formatDate(Date date, Object ... pattern) {
      if(date == null) {
         return null;
      } else {
         String formatDate = null;
         if(pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
         } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
         }

         return formatDate;
      }
   }

   public static String formatDateTime(Date date) {
      return formatDate(date, "yyyy-MM-dd HH:mm:ss");
   }

   public static String getTime() {
      return formatDate(new Date(), "HH:mm:ss");
   }

   public static String getDateTime() {
      return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
   }

   public static String getYear() {
      return formatDate(new Date(), "yyyy");
   }

   public static String getMonth() {
      return formatDate(new Date(), "MM");
   }

   public static String getDay() {
      return formatDate(new Date(), "dd");
   }

   public static String getWeek() {
      return formatDate(new Date(), "E");
   }

   public static Date parseDate(Object str) {
      if(str == null) {
         return null;
      } else {
         try {
            return parseDate(str.toString(), parsePatterns);
         } catch (ParseException var2) {
            return null;
         }
      }
   }

   public static long pastDays(Date date) {
      long t = (new Date()).getTime() - date.getTime();
      return t / 86400000L;
   }

   public static long pastHour(Date date) {
      long t = (new Date()).getTime() - date.getTime();
      return t / 3600000L;
   }

   public static long pastMinutes(Date date) {
      long t = (new Date()).getTime() - date.getTime();
      return t / 60000L;
   }

   public static long pastSeconds(Date date) {
      long t = (new Date()).getTime() - date.getTime();
      return t / 1000L;
   }

   public static String formatDateTime(long timeMillis) {
      long day = timeMillis / 86400000L;
      long hour = timeMillis / 3600000L - day * 24L;
      long min = timeMillis / 60000L - day * 24L * 60L - hour * 60L;
      long s = timeMillis / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
      long sss = timeMillis - day * 24L * 60L * 60L * 1000L - hour * 60L * 60L * 1000L - min * 60L * 1000L - s * 1000L;
      return (day > 0L?day + ",":"") + hour + ":" + min + ":" + s + "." + sss;
   }

   public static String formatDate(DateFormat dateFormat, Date date) {
      return dateFormat != null && date != null?dateFormat.format(date):null;
   }

   public static Date getStartDateOfMonth(Date dt) {
      GregorianCalendar gcal = new GregorianCalendar();
      gcal.setTime(dt);
      gcal.set(5, 1);
      return gcal.getTime();
   }

   public static Date getEndDateOfMonth(Date dt) {
      GregorianCalendar gcal = new GregorianCalendar();
      gcal.setTime(dt);
      gcal.set(5, gcal.getActualMaximum(5));
      return gcal.getTime();
   }

   public static Date getStartDateOfNextMonth(Date dt) {
      GregorianCalendar c = new GregorianCalendar();
      c.setTime(dt);
      c.add(2, 1);
      c.set(5, c.getActualMinimum(5));
      return c.getTime();
   }

   public static Date getEndDateOfNextMonth(Date dt) {
      GregorianCalendar c = new GregorianCalendar();
      c.setTime(dt);
      c.add(2, 1);
      c.set(5, c.getActualMaximum(5));
      return c.getTime();
   }

   public static Date getEndDateOfSeason(Date dt) {
      GregorianCalendar gcal = new GregorianCalendar();
      gcal.setTime(dt);
      int mon = gcal.get(2);
      int add = (mon + 1) % 3 == 0?0:3 - (mon + 1) % 3;
      gcal.add(2, add);
      gcal.set(5, gcal.getActualMaximum(5));
      return gcal.getTime();
   }

   public static Date getStartDateOfSeason(Date dt) {
      GregorianCalendar gcal = new GregorianCalendar();
      gcal.setTime(dt);
      int mon = gcal.get(2);
      int add = -(mon % 3);
      gcal.add(2, add);
      gcal.set(5, 1);
      return gcal.getTime();
   }

   public static Date getEndDateOfYear(Date dt) {
      GregorianCalendar gcal = new GregorianCalendar();
      gcal.setTime(dt);
      gcal.set(2, 11);
      gcal.set(5, 31);
      return gcal.getTime();
   }

   public static Date getStartDateOfYear(Date dt) {
      GregorianCalendar gcal = new GregorianCalendar();
      gcal.setTime(dt);
      gcal.set(2, 0);
      gcal.set(5, 1);
      return gcal.getTime();
   }

   public static Date getPastDate(int pass, String type) {
      return getPastDate(pass, false, (Date)null, type);
   }

   public static Date getPastDate(int pass, boolean clearTime, Date date, String type) {
      GregorianCalendar gcal = new GregorianCalendar();
      if(date != null) {
         gcal.setTime(date);
      } else {
         gcal.setTime(new Date());
      }

      if(StringUtils.equalsIgnoreCase(type, "YEAR")) {
         gcal.add(1, pass * -1);
      } else if(StringUtils.equalsIgnoreCase(type, "MONTH")) {
         gcal.add(2, pass * -1);
      } else {
         gcal.add(5, pass * -1);
      }

      if(clearTime) {
         gcal.set(11, 0);
         gcal.set(12, 0);
         gcal.set(13, 0);
      }

      return gcal.getTime();
   }

   public static Date getPastDate(int pass, boolean clearTime, String type) {
      return getPastDate(pass, clearTime, (Date)null, type);
   }

   public static Date getDateTime(Date date, String time) {
      GregorianCalendar gcal = new GregorianCalendar();
      gcal.setTime(date);
      String[] t = time.split(":");
      gcal.set(11, t.length > 0?NumberUtils.toInt(t[0], 0):0);
      gcal.set(12, t.length > 1?NumberUtils.toInt(t[1], 0):0);
      gcal.set(13, t.length > 2?NumberUtils.toInt(t[2], 0):0);
      return gcal.getTime();
   }

   public static int compareDay(Date date) {
      int today = Integer.parseInt(FormatUtil.formatDate(new Date(), "yyyyMMdd"));
      int idate = Integer.parseInt(FormatUtil.formatDate(date, "yyyyMMdd"));
      return today - idate;
   }

   public static Date getDateStart(String dateStr) {
      Date date = FormatUtil.parseDate(dateStr);
      if(date != null) {
         GregorianCalendar gcal = new GregorianCalendar();
         gcal.setTime(date);
         gcal.set(11, 0);
         gcal.set(12, 0);
         gcal.set(13, 0);
         gcal.set(14, 0);
         date = gcal.getTime();
      }

      return date;
   }

   public static Long getDateStartLong(String dateStr) {
      Date date = getDateStart(dateStr);
      return date == null?null:new Long(date.getTime());
   }

   public static Date getDateEnd(String dateStr) {
      Date date = FormatUtil.parseDate(dateStr);
      if(date != null) {
         GregorianCalendar gcal = new GregorianCalendar();
         gcal.setTime(date);
         gcal.set(11, 23);
         gcal.set(12, 59);
         gcal.set(13, 59);
         gcal.set(14, 999);
         date = gcal.getTime();
      }

      return date;
   }

   public static Long getDateEndLong(String dateStr) {
      Date date = getDateEnd(dateStr);
      return date == null?null:new Long(date.getTime());
   }

   public static String getFormatTime(long time, String format) {
      SimpleDateFormat s = new SimpleDateFormat(format);
      return s.format(new Date(time));
   }

   public static String formatDate(Date date, String pattern) {
      return FormatUtil.formatDate(date, pattern);
   }

   public static long spendNt(long preTime) {
      return System.nanoTime() - preTime;
   }

   public static long spendMs(long preTime) {
      return System.currentTimeMillis() - preTime;
   }

   public static int daysBetween(Date early, Date late) {
      Calendar calst = Calendar.getInstance();
      Calendar caled = Calendar.getInstance();
      calst.setTime(early);
      caled.setTime(late);
      setTimeZERO(calst);
      setTimeZERO(caled);
      int days = ((int)(caled.getTime().getTime() / 1000L) - (int)(calst.getTime().getTime() / 1000L)) / 3600 / 24;
      return days;
   }

   public static int hoursBetween(Date early, Date late) {
      Calendar calst = Calendar.getInstance();
      Calendar caled = Calendar.getInstance();
      calst.setTime(early);
      caled.setTime(late);
      int hours = ((int)(caled.getTime().getTime() / 1000L) - (int)(calst.getTime().getTime() / 1000L)) / 3600;
      return hours;
   }

   public static int minutesBetween(Date early, Date late) {
      Calendar calst = Calendar.getInstance();
      Calendar caled = Calendar.getInstance();
      calst.setTime(early);
      caled.setTime(late);
      int minutes = ((int)(caled.getTime().getTime() / 1000L) - (int)(calst.getTime().getTime() / 1000L)) / 60;
      return minutes;
   }

   public static void setTimeZERO(Calendar cal) {
      cal.set(11, 0);
      cal.set(12, 0);
      cal.set(13, 0);
   }

   private static int getMondayPlus() {
      Calendar cd = Calendar.getInstance();
      int dayOfWeek = cd.get(7) - 1;
      return dayOfWeek == 1?0:1 - dayOfWeek;
   }

   public static Date getMondayOFWeek() {
      return getMondayOFWeek((Date)null);
   }

   public static Date getMondayOFWeek(Date date) {
      int mondayPlus = getMondayPlus();
      GregorianCalendar currentDate = new GregorianCalendar();
      if(date != null) {
         currentDate.setTime(date);
      }

      currentDate.add(5, mondayPlus);
      return currentDate.getTime();
   }

   public static Date getFirstDayOfMonth() {
      return getFirstDayOfMonth((Date)null);
   }

   public static Date getFirstDayOfMonth(Date date) {
      Calendar lastDate = Calendar.getInstance();
      if(date != null) {
         lastDate.setTime(date);
      }

      lastDate.set(5, 1);
      return lastDate.getTime();
   }

   public static Date getWeekDay(Date date, int firstDay, int weekAmount, int weekDay) {
      Calendar ca = Calendar.getInstance();
      if(date != null) {
         ca.setTime(date);
      }

      ca.setFirstDayOfWeek(firstDay);
      ca.add(4, weekAmount);
      ca.set(7, weekDay);
      return ca.getTime();
   }

   public static Date getMonthDay(Date date, int monthAmount, int monthDay) {
      Calendar ca = Calendar.getInstance();
      if(date != null) {
         ca.setTime(date);
      }

      ca.add(2, monthAmount);
      ca.set(5, monthDay);
      return ca.getTime();
   }

   public static Date DateSubtraction(Date date, int minutes) throws Exception {
      Calendar ca = Calendar.getInstance();
      if(date != null) {
         ca.setTime(date);
      }

      ca.add(12, -minutes);
      return ca.getTime();
   }

   public static Date getBeginOfDate(Date date) {
      if(date == null) {
         date = new Date();
      }

      try {
         Date e = parseDate(formatDate(date, "yyyyMMdd") + " 00:00:00", new String[]{"yyyyMMdd HH:mm:ss"});
         return e;
      } catch (ParseException var2) {
         LOG.error(var2.getMessage());
         return new Date();
      }
   }

   public static Date getEndOfDate(Date date) {
      if(date == null) {
         date = new Date();
      }

      try {
         Date e = parseDate(formatDate(date, "yyyyMMdd") + " 23:59:59", new String[]{"yyyyMMdd HH:mm:ss"});
         return e;
      } catch (ParseException var2) {
         LOG.error(var2.getMessage());
         return new Date();
      }
   }

   public static Date newDate() {
      return new Date();
   }

   public static Date parseDateStr(String s, String formateStr) {
      SimpleDateFormat formatter = new SimpleDateFormat(formateStr);

      try {
         Date date = formatter.parse(s);
         return date;
      } catch (ParseException var5) {
         LOG.error(var5.getMessage());
         return null;
      }
   }

   public static String parseDate(Long lDate) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date dt = new Date(lDate.longValue());
      String sDateTime = sdf.format(dt);
      return sDateTime;
   }

   public static void main(String[] args) throws ParseException {}
}
