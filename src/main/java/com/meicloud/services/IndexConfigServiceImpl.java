package com.meicloud.services;

import com.meicloud.dao.ServerMapper;
import com.meicloud.model.Server;
import com.meicloud.services.IndexConfigService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("indexConfigService")
public class IndexConfigServiceImpl implements IndexConfigService {

   @Autowired
   private ServerMapper serverMapper;


   public List queryCmServerList() throws Exception {
      try {
         return this.serverMapper.queryCmServerList();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public Server queryCmServer(int serverId) throws Exception {
      try {
         return this.serverMapper.queryCmServer(serverId);
      } catch (Exception var3) {
         throw var3;
      }
   }
}
