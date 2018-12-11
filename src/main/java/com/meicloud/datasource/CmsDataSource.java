package com.meicloud.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.meicloud.datasource.DynamicDataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.sql.DataSource;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.core.env.Environment;

public class CmsDataSource extends DynamicDataSource {

   private static CmsDataSource cmsDataSource;
   private static Map dataSources = new HashMap();


   public CmsDataSource(Environment env) throws Exception {
      cmsDataSource = this;
      initCustomDataSources(env);
   }

   public static DataSource initDataSource(Map dsMap) throws Exception {
      DruidDataSource dataSource = new DruidDataSource();
      dataSource.setDriverClassName((String)dsMap.get("driver-class-name"));
      dataSource.setUsername((String)dsMap.get("username"));
      dataSource.setPassword((String)dsMap.get("password"));
      dataSource.setUrl((String)dsMap.get("url"));
      dataSource.setInitialSize(dsMap.get("initialSize") != null?Integer.parseInt(dsMap.get("initialSize").toString()):5);
      dataSource.setMinIdle(dsMap.get("minIdle") != null?Integer.parseInt(dsMap.get("minIdle").toString()):1);
      dataSource.setMaxActive(dsMap.get("maxActive") != null?Integer.parseInt(dsMap.get("maxActive").toString()):50);
      dataSource.setDefaultAutoCommit(false);
      if(dsMap.get("dsname") != null && !dsMap.get("dsname").equals("")) {
         dataSources.put(dsMap.get("dsname"), dataSource);
      }

      dataSource.setFilters("stat");
      dataSource.addFilters("wall");
      return dataSource;
   }

   private static void initCustomDataSources(Environment env) throws Exception {
      RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "custom.datasource.");
      String dsPrefixs = propertyResolver.getProperty("names");
      String[] var6;
      int var5 = (var6 = dsPrefixs.split(",")).length;

      for(int var4 = 0; var4 < var5; ++var4) {
         String dsPrefix = var6[var4];
         Map dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
         DataSource ds = initDataSource(dsMap);
         getDataSources().put(dsPrefix, ds);
      }

      cmsDataSource.setTargetDataSources(getDataSources(), (DataSource)getDataSources().get("default"));
      cmsDataSource.init();
   }

   public void afterPropertiesSet() {}

   public void init() {
      super.afterPropertiesSet();
   }

   public void put(Object name, Object dataSource) {
      dataSources.put(name, dataSource);
   }

   public void remove(Object name) {
      dataSources.remove(name);
   }

   public static Map getDataSources() {
      return dataSources;
   }

   public static DruidDataSource getDataSources(String dsname) {
      Iterator var2 = dataSources.entrySet().iterator();

      while(var2.hasNext()) {
         Entry entry = (Entry)var2.next();
         DruidDataSource ds = (DruidDataSource)entry.getValue();
         if(entry.getKey().equals(dsname)) {
            return ds;
         }
      }

      return null;
   }

   public static void reflash() {
      cmsDataSource.init();
   }
}
