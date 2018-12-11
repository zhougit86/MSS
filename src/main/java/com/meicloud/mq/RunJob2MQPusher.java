package com.meicloud.mq;

import com.alibaba.fastjson.JSONArray;
import com.meicloud.model.RunJob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class RunJob2MQPusher {

   private static Logger LOG = Logger.getLogger(RunJob2MQPusher.class);
   private String user;
   private String password;
   private String url;
   private String destination;


   public RunJob2MQPusher() {
      this.init();
   }

   public void init() {}

   public void send(RunJob runJob) throws Exception {
      ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(this.url);
      Connection connection = factory.createConnection(this.user, this.password);
      connection.start();
      Session session = connection.createSession(false, 1);
      ActiveMQQueue dest = new ActiveMQQueue(this.destination + "_" + runJob.getQueueId());
      MessageProducer producer = session.createProducer(dest);

      try {
         String e = JSONArray.toJSONString(runJob);
         TextMessage msg = session.createTextMessage(e);
         producer.send(msg);
         LOG.info("send:" + msg.getText());
      } catch (Exception var12) {
         LOG.error(var12);
         throw var12;
      } finally {
         connection.close();
      }

   }

   public List send(List runJobList) throws Exception {
      ArrayList sendedIds = new ArrayList();
      LinkedHashMap splitMap = new LinkedHashMap();
      Iterator factory = runJobList.iterator();

      while(factory.hasNext()) {
         RunJob keys = (RunJob)factory.next();
         String connection = keys.getQueueId();
         if(splitMap.containsKey(connection)) {
            ((List)splitMap.get(connection)).add(keys);
         } else {
            ArrayList session = new ArrayList();
            session.add(keys);
            splitMap.put(connection, session);
         }
      }

      Iterator keys1 = splitMap.keySet().iterator();
      ActiveMQConnectionFactory factory1 = new ActiveMQConnectionFactory(this.url);
      Connection connection1 = factory1.createConnection(this.user, this.password);
      connection1.start();
      Session session1 = connection1.createSession(false, 1);

      try {
         while(keys1.hasNext()) {
            String e = (String)keys1.next();
            ActiveMQQueue dest = new ActiveMQQueue(this.destination + "_" + e);
            MessageProducer producer = session1.createProducer(dest);
            List subList = (List)splitMap.get(e);

            try {
               Iterator var13 = subList.iterator();

               while(var13.hasNext()) {
                  RunJob e1 = (RunJob)var13.next();
                  String text = JSONArray.toJSONString(e1);
                  TextMessage msg = session1.createTextMessage(text);
                  producer.send(msg);
                  sendedIds.add(Integer.valueOf(e1.getRunJobId()));
                  LOG.info("push runJob [" + text + "] to queue ]");
               }
            } catch (Exception var20) {
               LOG.error(var20);
               throw var20;
            }
         }
      } catch (Exception var21) {
         LOG.error(var21);
         throw var21;
      } finally {
         connection1.close();
      }

      return sendedIds;
   }
}
