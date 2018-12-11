package com.meicloud.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

public class PropertiesUtil {

   private static Logger LOG = Logger.getLogger(PropertiesUtil.class);
   private static String CONF_FILE = "config.properties";
   private static Properties queueProp;
   private static Properties defaultProp;
   private static String ETL_FILE_PATH_MID;
   private static String ETL_FILE_PATH_SERVER_PREFIX;
   private static String JOB_LOG_FILE_PREFIX;
   protected static final Map CANCEL_RUN_JOBS = new HashMap();
   private static String HADOOP_URL;


   public static String getLogFilePrefix() throws Exception {
      try {
         if(JOB_LOG_FILE_PREFIX == null) {
            defaultProp = getDefaultProperties();
            JOB_LOG_FILE_PREFIX = defaultProp.getProperty("job.log.dist");
         }
      } catch (Exception var1) {
         throw var1;
      }

      return JOB_LOG_FILE_PREFIX;
   }

   public static String getHadoopURL() {
      try {
         if(HADOOP_URL == null) {
            defaultProp = getDefaultProperties();
            HADOOP_URL = defaultProp.getProperty("hadoop_url");
         }
      } catch (Exception var1) {
         LOG.error(var1.getMessage());
      }

      return HADOOP_URL;
   }

   public static Properties getPropertiesByFileName(String fileName) {
      InputStream in = null;
      Properties props = null;

      try {
         in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
         props = new Properties();
         props.load(in);
      } catch (Exception var12) {
         LOG.error(var12);
      } finally {
         if(in != null) {
            try {
               in.close();
            } catch (Exception var11) {
               LOG.error(var11.getMessage());
               LOG.error(var11);
            }
         }

      }

      return props;
   }

   public static String getEtlFilePathMid() throws Exception {
      try {
         if(ETL_FILE_PATH_MID == null) {
            defaultProp = getDefaultProperties();
            ETL_FILE_PATH_MID = defaultProp.getProperty("project.svn.dist.mid");
         }
      } catch (Exception var1) {
         throw var1;
      }

      return ETL_FILE_PATH_MID;
   }

   public static String getEtlFilePathServerPrefix() throws Exception {
      try {
         if(ETL_FILE_PATH_SERVER_PREFIX == null) {
            defaultProp = getDefaultProperties();
            ETL_FILE_PATH_SERVER_PREFIX = defaultProp.getProperty("project.svn.dist");
         }
      } catch (Exception var1) {
         throw var1;
      }

      return ETL_FILE_PATH_SERVER_PREFIX;
   }

   public static Properties getPropertiesByFilePath(String filePath) {
      Properties properties = new Properties();
      FileInputStream in = null;

      try {
         in = new FileInputStream(new File(filePath));
         properties.load(in);
      } catch (Exception var12) {
         LOG.error(var12);
      } finally {
         if(in != null) {
            try {
               in.close();
               in = null;
            } catch (IOException var11) {
               LOG.error(var11);
            }
         }

      }

      return properties;
   }

   public static Properties getDefaultProperties() {
      if(defaultProp == null) {
         defaultProp = getPropertiesByFileName(CONF_FILE);
      }

      return queueProp;
   }

   public void clear() {
      queueProp = null;
      defaultProp = null;
   }
}
