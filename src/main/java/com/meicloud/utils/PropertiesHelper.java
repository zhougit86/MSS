package com.meicloud.utils;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesHelper {

   private static Map cacheMap = new Hashtable();
   private static PropertiesHelper loadProperty = null;
   private static Logger LOGGER = LoggerFactory.getLogger(PropertiesHelper.class);


   public static PropertiesHelper getInstance() {
      if(loadProperty == null) {
         loadProperty = new PropertiesHelper();
      }

      return loadProperty;
   }

   public Properties springUtil(String fileName) {
      Properties props = new Properties();

      try {
         props = PropertiesLoaderUtils.loadAllProperties(fileName);
      } catch (IOException var4) {
         LOGGER.error(var4.getMessage());
      }

      return props;
   }

   public static Properties getProperties(String path) {
      path = path.trim();
      PropertiesHelper.CacheBean cb = (PropertiesHelper.CacheBean)cacheMap.get(path);
      return cb == null?loadIntoCache(path).content:cb.content;
   }

   private static PropertiesHelper.CacheBean loadIntoCache(String path) {
      PropertiesHelper.CacheBean cb = new PropertiesHelper.CacheBean((PropertiesHelper.CacheBean)null);
      new Properties();

      try {
         ClassPathResource e = new ClassPathResource(path);
         EncodedResource encRes = new EncodedResource(e, "UTF-8");
         Properties props = PropertiesLoaderUtils.loadProperties(encRes);
         cb.content = props;
      } catch (IOException var5) {
         LOGGER.error("读取文件日期失败", var5);
         cb.lastUpdateTime = new Date(0L);
         cb.isNeedCheck = false;
      }

      cacheMap.put(path, cb);
      return cb;
   }

   private static class CacheBean {

      private boolean isNeedCheck;
      private Date lastUpdateTime;
      private Properties content;


      private CacheBean() {}

      // $FF: synthetic method
      CacheBean(PropertiesHelper.CacheBean var1) {
         this();
      }
   }
}
