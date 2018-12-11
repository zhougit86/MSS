package com.meicloud.datasource;

import com.meicloud.datasource.DynamicDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

   private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);
   private ConversionService conversionService = new DefaultConversionService();
   private PropertyValues dataSourcePropertyValues;
   private static final Object DATASOURCE_TYPE_DEFAULT = "com.alibaba.druid.pool.DruidDataSource";
   private Map customDataSources = new HashMap();


   public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
      HashMap targetDataSources = new HashMap();
      targetDataSources.putAll(this.customDataSources);
      GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
      beanDefinition.setBeanClass(DynamicDataSource.class);
      beanDefinition.setSynthetic(true);
      MutablePropertyValues mpv = beanDefinition.getPropertyValues();
      mpv.addPropertyValue("targetDataSources", targetDataSources);
      registry.registerBeanDefinition("dataSource", beanDefinition);
      logger.info("Dynamic DataSource Registry");
   }

   public DataSource buildDataSource(Map dsMap) {
      try {
         Object e = dsMap.get("type");
         if(e == null) {
            e = DATASOURCE_TYPE_DEFAULT;
         }

         Class dataSourceType = Class.forName((String)e);
         String driverClassName = dsMap.get("driver-class-name").toString();
         String url = dsMap.get("url").toString();
         String username = dsMap.get("username").toString();
         String password = dsMap.get("password").toString();
         DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url).username(username).password(password).type(dataSourceType);
         return factory.build();
      } catch (ClassNotFoundException var9) {
         logger.error(var9.getMessage());
         return null;
      }
   }

   public void setEnvironment(Environment env) {
      this.initCustomDataSources(env);
   }

   private void dataBinder(DataSource dataSource, Environment env) {
      RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
      dataBinder.setConversionService(this.conversionService);
      dataBinder.setIgnoreNestedProperties(false);
      dataBinder.setIgnoreInvalidFields(false);
      dataBinder.setIgnoreUnknownFields(true);
      if(this.dataSourcePropertyValues == null) {
         Map rpr = (new RelaxedPropertyResolver(env, "spring.datasource")).getSubProperties(".");
         HashMap values = new HashMap(rpr);
         values.remove("type");
         values.remove("driver-class-name");
         values.remove("url");
         values.remove("username");
         values.remove("password");
         this.dataSourcePropertyValues = new MutablePropertyValues(values);
      }

      dataBinder.bind(this.dataSourcePropertyValues);
   }

   private void initCustomDataSources(Environment env) {
      RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "custom.datasource.");
      String dsPrefixs = propertyResolver.getProperty("names");
      String[] var7;
      int var6 = (var7 = dsPrefixs.split(",")).length;

      for(int var5 = 0; var5 < var6; ++var5) {
         String dsPrefix = var7[var5];
         Map dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
         DataSource ds = this.buildDataSource(dsMap);
         this.customDataSources.put(dsPrefix, ds);
         this.dataBinder(ds, env);
      }

   }
}
