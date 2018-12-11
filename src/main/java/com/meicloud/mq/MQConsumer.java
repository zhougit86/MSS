package com.meicloud.mq;

import com.alibaba.fastjson.JSONArray;
import com.meicloud.dao.ServerMapper;
import com.meicloud.model.RunJob;
import com.meicloud.model.Server;
import com.meicloud.services.RunJobService;
import com.meicloud.utils.HttpUtil;
import com.meicloud.utils.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MQConsumer {

   private static Logger LOG = LoggerFactory.getLogger(MQConsumer.class);
   @Autowired
   private ServerMapper serverMapper;
   @Autowired
   private RunJobService runJobService;
   @Autowired
   private Environment environment;


   @JmsListener(
      destination = "${spring.activemq.queue.name}",
      containerFactory = "jmsListenerContainerQueue"
   )
   public void receiveQueue(TextMessage text, Session session) throws JMSException {
      RunJob runJob = new RunJob();

      String msg;
      try {
         LOG.info("start to receive one runjob from queue:" + text.getText());
         runJob = (RunJob)JSONArray.parseObject(text.getText(), RunJob.class);
         text.acknowledge();
         boolean e = false;
         if(runJob.getExecuteServerId() != 0) {
            Server msg1 = this.serverMapper.getServerById(runJob.getExecuteServerId());
            e = this.sendJob(msg1, runJob);
         } else {
            msg = this.serverMapper.getServerIdByMinExecuteJobCount(runJob.getQueueId());
            if(!StringUtil.isBlank(msg)) {
               Server server = this.serverMapper.getServerById(Integer.parseInt(msg));
               e = this.sendJob(server, runJob);
            } else {
               e = this.sendJob((Server)null, runJob);
            }
         }

         if(e) {
            msg = "send runjob [" + runJob.getRunJobId() + "] to es server successful.";
            LOG.info(msg);
         } else {
            msg = "send runjob [" + runJob.getRunJobId() + "] to es server failure.";
            LOG.info(msg);
         }
      } catch (Exception var7) {
         LOG.error(var7.getMessage());
         msg = "send runjob [" + runJob.getRunJobId() + "] to es server failure:" + var7.getMessage();
         LOG.info(msg);
      }

   }

   private boolean sendJob(Server server, RunJob runJob) throws Exception {
      String json = JSONArray.toJSONString(runJob);
      LOG.info("send RunJob:[" + json + "]");
      HttpUtil httpUtil = new HttpUtil();
      boolean sendResult = false;
      if(server != null && server.getState() == 0) {
         RunJob queryServer = this.runJobService.getByRunJobId(runJob.getRunJobId());
         if(queryServer == null) {
            return false;
         }

         LOG.info("当前RunJob[" + runJob.getRunJobId() + "]数据库状态:" + queryServer.getState());
         if(queryServer.getState() < 2) {
            LOG.info(JSONArray.toJSONString(server));
            int servers = this.runJobService.getRunJobCountByServerId(server.getId().intValue());
            int list = server.getMaxParalle();
            if(servers < list) {
               String url = "http://" + server.getIp() + ":" + server.getAppPort() + "/MSS/executeJob";
               if(StringUtil.isBlank(server.getAppPort())) {
                  url = "http://" + server.getIp() + "/MSS/executeJob";
               }

               sendResult = httpUtil.sendByPostRe(url, json);
               LOG.info("send RUNJOB[" + runJob.getRunJobId() + "] by server " + server.getIp() + " " + sendResult);
            }
         } else {
            LOG.info("RunJob[" + queryServer.getRunJobId() + "]已发送至[" + queryServer.getExecuteServerId() + "]服务器执行");
         }
      }

      if(!sendResult) {
         Server queryServer1 = new Server();
         queryServer1.setState(0);
         queryServer1.setType("ES");
         queryServer1.setQueueId(String.valueOf(runJob.getQueueId()));
         List servers1 = this.serverMapper.getServerList(queryServer1);
         if(servers1 != null && servers1.size() > 0) {
            Iterator url2 = servers1.iterator();

            while(url2.hasNext()) {
               Server list2 = (Server)url2.next();
               if(sendResult) {
                  break;
               }

               if(server == null || !list2.getId().equals(server.getId())) {
                  RunJob r = this.runJobService.getByRunJobId(runJob.getRunJobId());
                  LOG.info("当前RunJob[" + runJob.getRunJobId() + "]数据库状态:" + r.getState());
                  if(r.getState() == 2) {
                     LOG.info("RunJob[" + r.getRunJobId() + "]已发送至[" + r.getExecuteServerId() + "]服务器执行");
                     break;
                  }

                  if(r.getState() == 0) {
                     int currentExecuteJobCount = this.runJobService.getRunJobCountByServerId(list2.getId().intValue());
                     int maxParalle = list2.getMaxParalle();
                     if(currentExecuteJobCount < maxParalle) {
                        String url1 = "http://" + list2.getIp() + ":" + list2.getAppPort() + "/MSS/executeJob";
                        if(StringUtil.isBlank(server.getAppPort())) {
                           url1 = "http://" + server.getIp() + "/MSS/executeJob";
                        }

                        sendResult = httpUtil.sendByPostRe(url1, json);
                        LOG.info("send RUNJOB[" + runJob.getRunJobId() + "] by server " + list2.getIp() + " " + sendResult);
                        if(sendResult) {
                           break;
                        }
                     }
                  }
               }
            }
         } else {
            ArrayList list1 = new ArrayList();
            list1.add(Integer.valueOf(runJob.getRunJobId()));
            this.runJobService.updateListLatestSendDateIsNull(list1);
         }
      }

      return sendResult;
   }
}
