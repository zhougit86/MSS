package com.meicloud.services;

import com.meicloud.model.Server;
import java.util.List;

public interface ServerService {

   void add(Server var1);

   void update(Server var1);

   void delete(int var1);

   List list(int var1);

   Server getServerById(int var1);

   Server getServerByQueueIdAndMinJob(Server var1);

   Server getServerByIP(String var1);

   List getServerList(Server var1);

   List getServerListByPort(Server var1);

   List getServerListByIpAndType(Server var1);

   List getServerListByTypeAndPerform(Server var1);

   List queryCmServerList();

   Server queryCmServer(int var1);

   Server getServerByMinExecuteJobCount(String var1);

   int getServerIdByMinExecuteJobCount(String var1);
}
