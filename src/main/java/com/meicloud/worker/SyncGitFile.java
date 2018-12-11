package com.meicloud.worker;

import com.meicloud.model.Queue;
import com.meicloud.model.Server;
import com.meicloud.services.QueueService;
import com.meicloud.services.ServerService;
import com.meicloud.utils.JGitUtil;
import com.meicloud.utils.StringUtil;
import com.meicloud.utils.Utils;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("syncGitFile")
public class SyncGitFile {

   private static Logger logger = LoggerFactory.getLogger(SyncGitFile.class);
   @Autowired
   private QueueService queueService;
   @Autowired
   private ServerService serverService;
   @Autowired
   private Environment environment;


   public boolean doCheckOutAndUpdate() {
      logger.info("------------->>>>>>>>>>>>>开始从svn上同步执行脚同步");
      String serverIp = this.environment.getProperty("app.server.host");
      Server server = this.serverService.getServerByIP(serverIp);
      if(server == null) {
         logger.info("------------->>>>>>>>>>>>>没有找到服务器信息，请配置");
         return false;
      } else {
         Queue queue = this.queueService.findOne(server.getQueueId());
         String gitUserName = queue.getGitUserName();
         String gitPassWord = queue.getGitPassWord();
         String gitUrl = queue.getGitUrl();
         if(StringUtil.isBlank(gitUrl)) {
            logger.info("------------->>>>>>>>>>>>>没有找到GIT配置信息，请配置");
            return false;
         } else {
            String syncGitFile = null;
            if(!Utils.isEmptyStr(server.getSvnLogPath())) {
               syncGitFile = server.getSvnLogPath() + "/" + queue.getQueueCode() + "/mssgit";
            } else {
               syncGitFile = System.getProperty("user.dir") + "/" + queue.getQueueCode() + "/mssgit";
               logger.info("------------->>>>>>>>>>>>>获取项目地址：" + syncGitFile);
            }

            boolean syncFalg = false;

            try {
               File e = new File(syncGitFile);
               if(!e.exists()) {
                  logger.info("------------->>>>>>>>>>>>>checkOut git ：下载地址：" + syncGitFile);
                  syncFalg = JGitUtil.cloneRepository(gitUrl, syncGitFile, gitUserName, gitPassWord);
                  logger.info("------------->>>>>>>>>>>>>git checkOut结束");
               } else {
                  logger.info("开始更新git仓库");
                  syncFalg = JGitUtil.pullRepository(syncGitFile, gitUserName, gitPassWord);
                  logger.info("更新git仓库完成");
               }
            } catch (Exception var10) {
               logger.error("------------->>>>>>>>>>>>>从git上同步执行脚同步至(" + gitUrl + ")失败,原因:" + var10.getMessage());
               syncFalg = false;
            }

            return syncFalg;
         }
      }
   }
}
