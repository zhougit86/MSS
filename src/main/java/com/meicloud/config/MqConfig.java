package com.meicloud.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@Configuration
@EnableJms
public class MqConfig {

   @Autowired
   private ActiveMQConnectionFactory connectionFactory;


   @Bean
   public JmsListenerContainerFactory jmsListenerContainerTopic() {
      DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
      bean.setPubSubDomain(Boolean.valueOf(true));
      this.connectionFactory.setRedeliveryPolicy(this.redeliveryPolicy());
      bean.setConnectionFactory(this.connectionFactory);
      return bean;
   }

   @Bean
   public RedeliveryPolicy redeliveryPolicy() {
      RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
      redeliveryPolicy.setUseExponentialBackOff(true);
      redeliveryPolicy.setMaximumRedeliveries(10);
      redeliveryPolicy.setInitialRedeliveryDelay(1L);
      redeliveryPolicy.setBackOffMultiplier(2.0D);
      redeliveryPolicy.setUseCollisionAvoidance(false);
      redeliveryPolicy.setMaximumRedeliveryDelay(-1L);
      return redeliveryPolicy;
   }

   @Bean
   public JmsListenerContainerFactory jmsListenerContainerQueue() {
      DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
      this.connectionFactory.setRedeliveryPolicy(this.redeliveryPolicy());
      bean.setConcurrency("1");
      bean.setRecoveryInterval(Long.valueOf(1000L));
      bean.setSessionAcknowledgeMode(Integer.valueOf(4));
      bean.setConnectionFactory(this.connectionFactory);
      return bean;
   }
}
