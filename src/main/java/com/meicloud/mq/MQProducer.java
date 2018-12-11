package com.meicloud.mq;

import com.alibaba.fastjson.JSONArray;
import com.meicloud.dao.ServerMapper;
import com.meicloud.model.RunJob;
import com.meicloud.model.Server;
import com.meicloud.services.RunJobService;
import com.meicloud.utils.StringUtil;
import com.meicloud.worker.DBTool;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MQProducer {

   private static Logger LOG = LoggerFactory.getLogger(MQProducer.class);
   @Autowired
   private JmsMessagingTemplate jmsTemplate;
   @Autowired
   private Environment environment;
   @Autowired
   private DBTool dbTool;
   @Autowired
   private ServerMapper serverMapper;
   @Autowired
   private RunJobService runJobService;


   public void sendMessage(RunJob runJob) throws Exception {
      boolean b = this.isMQExist(runJob.getRunJobId(), this.environment.getProperty("spring.activemq.jmx.service"), this.environment.getProperty("spring.activemq.jmx.brokerName"));
      if(b) {
         LOG.info("MQ 存在 runjob[" + runJob.getRunJobId() + "]");
         ArrayList r1 = new ArrayList();
         r1.add(Integer.valueOf(runJob.getRunJobId()));
         if(r1 != null && r1.size() > 0) {
            this.dbTool.updateSendedList(r1);
         }

      } else {
         Thread.sleep(2000L);
         RunJob r = this.runJobService.getByRunJobId(runJob.getRunJobId());
         if(r == null) {
            LOG.info("没有找到 runjob[" + runJob.getRunJobId() + "]数据");
         } else {
            Server queryServer;
            int maxParalle;
            String msg;
            if(r.getExecuteServerId() != 0) {
               queryServer = this.serverMapper.getServerById(r.getExecuteServerId());
               if(queryServer != null) {
                  int servers = this.runJobService.getRunJobCountByServerId(r.getExecuteServerId());
                  LOG.info("指定服务器[" + queryServer.getIp() + "]当前正在执行数:" + servers);
                  LOG.info("指定服务器[" + queryServer.getIp() + "]当前最大执行数:" + queryServer.getMaxParalle());
                  if(queryServer.getMaxParalle() <= servers) {
                     LOG.info("超出服务器[" + queryServer.getIp() + "]最大执行数，拒绝发送.RunJob[" + runJob.getRunJobId() + "]");
                     return;
                  }

                  String isSend = this.environment.getProperty("spring.activemq.queue.name");
                  if(StringUtil.isBlank(isSend)) {
                     isSend = "RUN_JOB_QUEUE";
                  }

                  ActiveMQQueue count = new ActiveMQQueue(isSend);
                  String s = JSONArray.toJSONString(runJob);
                  this.jmsTemplate.convertAndSend(count, s);
                  String s1 = "send runjob [" + runJob.getRunJobId() + "] to MQ successful.";
                  LOG.info(s1);
                  ArrayList currentExecuteJobCount = new ArrayList();
                  currentExecuteJobCount.add(Integer.valueOf(runJob.getRunJobId()));
                  if(currentExecuteJobCount != null && currentExecuteJobCount.size() > 0) {
                     this.dbTool.updateSendedList(currentExecuteJobCount);
                  }
               } else {
                  Server servers1 = new Server();
                  servers1.setState(0);
                  servers1.setType("ES");
                  servers1.setQueueId(String.valueOf(runJob.getQueueId()));
                  List isSend1 = this.serverMapper.getServerList(servers1);
                  boolean count1 = false;
                  int s2 = 0;
                  if(isSend1 != null && isSend1.size() > 0) {
                     Iterator currentExecuteJobCount1 = isSend1.iterator();

                     while(currentExecuteJobCount1.hasNext()) {
                        Server s4 = (Server)currentExecuteJobCount1.next();
                        maxParalle = this.runJobService.getRunJobCountByServerId(s4.getId().intValue());
                        int queueName = s4.getMaxParalle();
                        s2 += queueName;
                        if(maxParalle < queueName) {
                           LOG.info("随机分配服务器[" + s4.getIp() + "]当前正在执行数:" + maxParalle);
                           LOG.info("随机分配服务器[" + s4.getIp() + "]当前最大执行数:" + queueName);
                           count1 = true;
                           String destination = this.environment.getProperty("spring.activemq.queue.name");
                           if(StringUtil.isBlank(destination)) {
                              destination = "RUN_JOB_QUEUE";
                           }

                           ActiveMQQueue message = new ActiveMQQueue(destination);
                           msg = JSONArray.toJSONString(runJob);
                           this.jmsTemplate.convertAndSend(message, msg);
                           String sendedIds = "send runjob [" + runJob.getRunJobId() + "] to MQ successful.";
                           LOG.info(sendedIds);
                           ArrayList sendedIds1 = new ArrayList();
                           sendedIds1.add(Integer.valueOf(runJob.getRunJobId()));
                           if(sendedIds1 != null && sendedIds1.size() > 0) {
                              this.dbTool.updateSendedList(sendedIds1);
                           }
                           break;
                        }
                     }
                  }

                  if(!count1) {
                     LOG.info("超出所有服务器设置最大执行数[" + s2 + "]，拒绝发送.RunJob[" + runJob.getRunJobId() + "]");
                     return;
                  }
               }
            } else {
               queryServer = new Server();
               queryServer.setState(0);
               queryServer.setType("ES");
               queryServer.setQueueId(String.valueOf(runJob.getQueueId()));
               List servers2 = this.serverMapper.getServerList(queryServer);
               boolean isSend2 = false;
               int count2 = 0;
               if(servers2 != null && servers2.size() > 0) {
                  Iterator s5 = servers2.iterator();

                  while(s5.hasNext()) {
                     Server s3 = (Server)s5.next();
                     int currentExecuteJobCount2 = this.runJobService.getRunJobCountByServerId(s3.getId().intValue());
                     maxParalle = s3.getMaxParalle();
                     count2 += maxParalle;
                     if(currentExecuteJobCount2 < maxParalle) {
                        LOG.info("随机分配服务器[" + s3.getIp() + "]当前正在执行数:" + currentExecuteJobCount2);
                        LOG.info("随机分配服务器[" + s3.getIp() + "]当前最大执行数:" + maxParalle);
                        isSend2 = true;
                        String queueName1 = this.environment.getProperty("spring.activemq.queue.name");
                        if(StringUtil.isBlank(queueName1)) {
                           queueName1 = "RUN_JOB_QUEUE";
                        }

                        ActiveMQQueue destination1 = new ActiveMQQueue(queueName1);
                        String message1 = JSONArray.toJSONString(runJob);
                        this.jmsTemplate.convertAndSend(destination1, message1);
                        msg = "send runjob [" + runJob.getRunJobId() + "] to MQ successful.";
                        LOG.info(msg);
                        ArrayList sendedIds2 = new ArrayList();
                        sendedIds2.add(Integer.valueOf(runJob.getRunJobId()));
                        if(sendedIds2 != null && sendedIds2.size() > 0) {
                           this.dbTool.updateSendedList(sendedIds2);
                        }
                        break;
                     }
                  }
               }

               if(!isSend2) {
                  LOG.info("超出所有服务器设置最大执行数[" + count2 + "]，拒绝发送.RunJob[" + runJob.getRunJobId() + "]");
                  return;
               }
            }

         }
      }
   }

   public boolean isMQExist(int runJobId, String service, String brokerName) {
      boolean exist = false;

      try {
         JMXServiceURL e = new JMXServiceURL(service);
         JMXConnector connector = JMXConnectorFactory.connect(e, (Map)null);
         connector.connect();
         MBeanServerConnection connection = connector.getMBeanServerConnection();
         ObjectName mbeanName = new ObjectName(brokerName);
         BrokerViewMBean mBean = (BrokerViewMBean)MBeanServerInvocationHandler.newProxyInstance(connection, mbeanName, BrokerViewMBean.class, true);
         ObjectName[] var13;
         int var12 = (var13 = mBean.getQueues()).length;

         for(int var11 = 0; var11 < var12; ++var11) {
            ObjectName queueName = var13[var11];
            QueueViewMBean queueMBean = (QueueViewMBean)MBeanServerInvocationHandler.newProxyInstance(connection, queueName, QueueViewMBean.class, true);
            TabularData tabularData = queueMBean.browseAsTable();
            Set keySet = tabularData.keySet();
            Iterator it = keySet.iterator();

            while(it.hasNext()) {
               Object object = it.next();
               String messageid = object.toString().replace("[", "").replace("]", "");
               CompositeData compositeData = queueMBean.getMessage(messageid);
               if(compositeData.get("Text") != null) {
                  String text = (String)compositeData.get("Text");
                  RunJob runJob = (RunJob)JSONArray.parseObject(text, RunJob.class);
                  if(runJob.getRunJobId() == runJobId) {
                     exist = true;
                     LOG.info("MQ存在【" + runJob.getRunJobId() + "】数据");
                     break;
                  }
               }
            }
         }

         connector.close();
      } catch (Exception var23) {
         LOG.error(var23.getMessage());
      }

      return exist;
   }

   public static void main(String[] args) {
      try {
         JMXServiceURL e = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1616/jmxrmi");
         JMXConnector connector = JMXConnectorFactory.connect(e, (Map)null);
         connector.connect();
         MBeanServerConnection connection = connector.getMBeanServerConnection();
         LOG.info("org.apache.activemq:brokerName=localhost,type=Broker：---------------");
         ObjectName mbeanName = new ObjectName("org.apache.activemq:brokerName=localhost,type=Broker");
         BrokerViewMBean mBean = (BrokerViewMBean)MBeanServerInvocationHandler.newProxyInstance(connection, mbeanName, BrokerViewMBean.class, true);
         ObjectName[] var9;
         int var8 = (var9 = mBean.getQueues()).length;

         for(int var7 = 0; var7 < var8; ++var7) {
            ObjectName queueName = var9[var7];
            QueueViewMBean queueMBean = (QueueViewMBean)MBeanServerInvocationHandler.newProxyInstance(connection, queueName, QueueViewMBean.class, true);
            LOG.info("\n------------------------------\n");
            LOG.info("States for queue --- " + queueMBean.getName());
            LOG.info("Size --- " + queueMBean.getQueueSize());
            LOG.info("Number of consumers --- " + queueMBean.getConsumerCount());
            LOG.info("Number of dequeue ---" + queueMBean.getDequeueCount());
            TabularData tabularData = queueMBean.browseAsTable();
            Set keySet = tabularData.keySet();
            Iterator it = keySet.iterator();

            while(it.hasNext()) {
               Object object = it.next();
               String messageid = object.toString().replace("[", "").replace("]", "");
               CompositeData compositeData = queueMBean.getMessage(messageid);
               String text = (String)compositeData.get("Text");
               RunJob runJob2 = (RunJob)JSONArray.parseObject(text, RunJob.class);
               LOG.info("" + runJob2.getRunJobId());
            }
         }

         connector.close();
      } catch (Exception var19) {
         LOG.error(var19.getMessage());
      }

   }
}
