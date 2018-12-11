package com.meicloud.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import org.apache.log4j.Logger;

public final class PropertyUtils {

   private static Logger LOG = Logger.getLogger("PropertyUtils");
   private static final String PRO_SUFFIX = ".properties";
   private static Map propMap = new HashMap();


   public static InputStream loadResource(String resourceName) {
      try {
         File e = getConfigFile(resourceName);
         if(e == null) {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
            return is;
         } else {
            return new FileInputStream(e);
         }
      } catch (FileNotFoundException var3) {
         LOG.error(var3.getMessage());
         return null;
      }
   }

   public static void loadProperties(String resourceName) {
      try {
         if(!org.apache.commons.lang3.StringUtils.endsWith(resourceName, ".properties")) {
            resourceName = resourceName + ".properties";
         }

         Properties prop = new Properties();
         prop.load(loadResource(resourceName));
         Iterator iterator = prop.entrySet().iterator();

         while(iterator.hasNext()) {
            Entry entry = (Entry)iterator.next();
            propMap.put(resourceName + String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
         }

         propMap.put(resourceName, "true");
      } catch (IOException var4) {
         ;
      }

   }

   public static String getProperty(String resourceName, String key) {
      return getProperty(resourceName, key, (String)null);
   }

   public static String getProperty(String resourceName, String key, String defaultValue) {
      if(!org.apache.commons.lang3.StringUtils.endsWith(resourceName, ".properties")) {
         resourceName = resourceName + ".properties";
      }

      String finalKey = resourceName + key;
      if(propMap.get(resourceName) == null) {
         loadProperties(resourceName);
      }

      String value = (String)propMap.get(finalKey);
      return org.apache.commons.lang3.StringUtils.isBlank(value)?defaultValue:value;
   }

   private static File getConfigFile(String resourceName) {
      String resourcePath = System.getProperty("catalina.home") + "/conf";
      File file = new File(resourcePath, resourceName);
      if(file.exists()) {
         return file;
      } else {
         resourcePath = System.getProperty("user.dir");
         file = new File(resourcePath, resourceName);
         return file.exists()?file:null;
      }
   }
}
