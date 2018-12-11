package com.meicloud.services;

import com.meicloud.dao.ServerMapper;
import com.meicloud.dao.SvnVersionMapper;
import com.meicloud.model.Server;
import com.meicloud.model.SvnVersion;
import com.meicloud.services.ServerService;
import com.meicloud.utils.StringUtil;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("serverService")
public class ServerServiceImpl implements ServerService {

   private static Logger LOG = Logger.getLogger(ServerServiceImpl.class);
   @Autowired
   private ServerMapper serverMapper;
   @Autowired
   private SvnVersionMapper svnVersionMapper;


   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void add(Server server) {
      server.setUpdateDate(new Date());
      server.setCreateDate(new Date());
      this.serverMapper.add(server);
      this.checkIfExistServerSvnVersion(server);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void update(Server server) {
      server.setUpdateDate(new Date());
      this.serverMapper.update(server);
      this.checkIfExistServerSvnVersion(server);
   }

   public void delete(int serverId) {
      this.serverMapper.delete(serverId);
   }

   public List list(int runningState) {
      return this.serverMapper.list(runningState);
   }

   public Server getServerById(int serverId) {
      return this.serverMapper.getServerById(serverId);
   }

   public Server getServerByIP(String ip) {
      return this.serverMapper.getServerByIP(ip);
   }

   public Server getServerByQueueIdAndMinJob(Server server) {
      return this.serverMapper.getServerByQueueIdAndMinJob(server);
   }

   public List getServerList(Server server) {
      return this.serverMapper.getServerList(server);
   }

   public List getServerListByPort(Server server) {
      return this.serverMapper.getServerListByPort(server);
   }

   public List getServerListByIpAndType(Server server) {
      return this.serverMapper.getServerListByIpAndType(server);
   }

   public List getServerListByTypeAndPerform(Server server) {
      return this.serverMapper.getServerListByTypeAndPerform(server);
   }

   public List queryCmServerList() {
      return this.serverMapper.queryCmServerList();
   }

   public Server queryCmServer(int serverId) {
      return this.serverMapper.queryCmServer(serverId);
   }

   public Server getServerByMinExecuteJobCount(String queueId) {
      return this.serverMapper.getServerByMinExecuteJobCount(queueId);
   }

   private void checkIfExistServerSvnVersion(Server server) {
      SvnVersion svnVersion = this.svnVersionMapper.getByIpNum(server.getIp());
      if(svnVersion == null) {
         SvnVersion sv = new SvnVersion();
         sv.setIpNum(server.getIp());
         sv.setVersion(Long.valueOf(0L));
         this.svnVersionMapper.add(sv);
      }

   }

   public int getServerIdByMinExecuteJobCount(String queueId) {
      String count = this.serverMapper.getServerIdByMinExecuteJobCount(queueId);
      return StringUtil.isBlank(count)?0:Integer.parseInt(count);
   }
}
