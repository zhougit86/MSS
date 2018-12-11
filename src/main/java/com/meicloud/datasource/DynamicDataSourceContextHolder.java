package com.meicloud.datasource;


public class DynamicDataSourceContextHolder {

   private static final ThreadLocal contextHolder = new ThreadLocal();


   public static void setDataSourceType(String dataSourceType) {
      contextHolder.set(dataSourceType);
   }

   public static String getDataSourceType() {
      return (String)contextHolder.get();
   }

   public static void clearDataSourceType() {
      contextHolder.remove();
   }
}
