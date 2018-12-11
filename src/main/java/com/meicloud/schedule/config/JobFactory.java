package com.meicloud.schedule.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

@Component
public class JobFactory extends SpringBeanJobFactory {

   @Autowired
   private AutowireCapableBeanFactory autowireCapableBeanFactory;


   protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
      Object jobInstance = super.createJobInstance(bundle);
      this.autowireCapableBeanFactory.autowireBean(jobInstance);
      return jobInstance;
   }
}
