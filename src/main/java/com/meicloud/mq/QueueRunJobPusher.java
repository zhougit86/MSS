package com.meicloud.mq;

import com.alibaba.fastjson.JSONArray;
import com.meicloud.model.RunJob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

public class QueueRunJobPusher {

   private static Logger LOG = Logger.getLogger(QueueRunJobPusher.class);
   private String url;
   private String queueName;
   private String userName;
   private String password;
   private ActiveMQConnectionFactory factory = null;
   private Connection connection = null;
   private Session session = null;
   private Destination destination = null;
   private MessageProducer producer = null;


   public QueueRunJobPusher() {
      try {
         this.factory = new ActiveMQConnectionFactory(this.url);
         this.connection = this.factory.createConnection(this.userName, this.password);
         this.connection.start();
         this.session = this.connection.createSession(Boolean.FALSE.booleanValue(), 1);
         this.destination = this.session.createQueue(this.queueName);
         this.producer = this.session.createProducer(this.destination);
      } catch (Exception var2) {
         LOG.error(var2);
      }

   }

   public List send(List runJobList) throws Exception {
      ArrayList sendedIds = new ArrayList();

      try {
         Iterator var4 = runJobList.iterator();

         while(var4.hasNext()) {
            RunJob e = (RunJob)var4.next();
            String text = JSONArray.toJSONString(e);
            TextMessage message = this.session.createTextMessage(text);
            this.producer.send(message);
            String msg = "push runJob [" + text + "] to queue [" + this.queueName + "] ";
            LOG.info(msg);
            sendedIds.add(Integer.valueOf(e.getRunJobId()));
         }
      } catch (Exception var11) {
         throw var11;
      } finally {
         this.release();
      }

      return sendedIds;
   }

   public void release() {
      try {
         if(this.producer != null) {
            this.producer.close();
         }

         if(this.session != null) {
            this.session.close();
         }

         if(this.connection != null) {
            this.connection.close();
         }
      } catch (Exception var2) {
         LOG.error(var2);
      }

   }
}
