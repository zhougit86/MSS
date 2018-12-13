package com.meicloud.schedule.config;

import com.meicloud.schedule.config.JobFactory;
import java.util.Properties;

import groovy.lang.Singleton;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@EnableScheduling
public class SchedulerConfig {

   @Autowired
   private JobFactory jobFactory;
   @Autowired
   private Environment environment;


   @Bean({"scheduler"})
   public SchedulerFactoryBean schedulerFactoryBean() throws Exception {
      SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
      schedulerFactory.setOverwriteExistingJobs(true);
      schedulerFactory.setStartupDelay(20);
      schedulerFactory.setQuartzProperties(this.quartzProperties());
      schedulerFactory.setJobFactory(this.jobFactory);
      return schedulerFactory;
   }

   public Properties quartzProperties() {
      Properties prop = new Properties();
      prop.put("org.quartz.scheduler.instanceName", this.environment.getProperty("org.quartz.scheduler.instanceName"));
      prop.put("org.quartz.scheduler.instanceId", this.environment.getProperty("org.quartz.scheduler.instanceId"));
      prop.put("org.quartz.scheduler.skipUpdateCheck", this.environment.getProperty("org.quartz.scheduler.skipUpdateCheck"));
      prop.put("org.quartz.scheduler.jobFactory.class", this.environment.getProperty("org.quartz.scheduler.jobFactory.class"));
      prop.put("org.quartz.threadPool.class", this.environment.getProperty("org.quartz.threadPool.class"));
      prop.put("org.quartz.threadPool.threadCount", this.environment.getProperty("org.quartz.threadPool.threadCount"));
      prop.put("org.quartz.threadPool.threadPriority", this.environment.getProperty("org.quartz.threadPool.threadPriority"));
      prop.put("org.quartz.jobStore.misfireThreshold", this.environment.getProperty("org.quartz.jobStore.misfireThreshold"));
      prop.put("org.quartz.jobStore.class", this.environment.getProperty("org.quartz.jobStore.class"));
      prop.put("org.quartz.jobStore.driverDelegateClass", this.environment.getProperty("org.quartz.jobStore.driverDelegateClass"));
      prop.put("org.quartz.jobStore.useProperties", this.environment.getProperty("org.quartz.jobStore.useProperties"));
      prop.put("org.quartz.jobStore.dataSource", this.environment.getProperty("org.quartz.jobStore.dataSource"));
      prop.put("org.quartz.jobStore.tablePrefix", this.environment.getProperty("org.quartz.jobStore.tablePrefix"));
      prop.put("org.quartz.jobStore.isClustered", this.environment.getProperty("org.quartz.jobStore.isClustered"));
      prop.put("org.quartz.jobStore.clusterCheckinInterval", this.environment.getProperty("org.quartz.jobStore.clusterCheckinInterval"));
      prop.put("org.quartz.dataSource.myDS.driver", this.environment.getProperty("org.quartz.dataSource.myDS.driver"));
      prop.put("org.quartz.dataSource.myDS.URL", this.environment.getProperty("org.quartz.dataSource.myDS.URL"));
      prop.put("org.quartz.dataSource.myDS.user", this.environment.getProperty("org.quartz.dataSource.myDS.user"));
      prop.put("org.quartz.dataSource.myDS.password", this.environment.getProperty("org.quartz.dataSource.myDS.password"));
      prop.put("org.quartz.dataSource.myDS.maxConnections", this.environment.getProperty("org.quartz.dataSource.myDS.maxConnections"));
      prop.put("org.quartz.dataSource.myDS.validationQuery", this.environment.getProperty("org.quartz.dataSource.myDS.validationQuery"));
      return prop;
   }

   @Bean
   public QuartzInitializerListener executorListener() {
      return new QuartzInitializerListener();
   }
}
