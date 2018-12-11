package com.meicloud.dao;

import com.meicloud.model.Server;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServerMapper {

   void add(Server var1);

   void update(Server var1);

   void delete(int var1);

   List list(int var1);

   Server getServerById(int var1);

   Server getServerByIP(@Param("ip") String var1);

   Server getServerByQueueIdAndMinJob(Server var1);

   List getServerList(Server var1);

   List getServerListByPort(Server var1);

   List getServerListByIpAndType(Server var1);

   List getServerListByTypeAndPerform(Server var1);

   List queryCmServerList();

   Server queryCmServer(int var1);

   Server getServerByMinExecuteJobCount(@Param("queueId") String var1);

   String getServerIdByMinExecuteJobCount(@Param("queueId") String var1);
}
