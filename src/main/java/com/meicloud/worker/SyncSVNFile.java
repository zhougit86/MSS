package com.meicloud.worker;

import com.meicloud.model.Queue;
import com.meicloud.model.Server;
import com.meicloud.model.SvnVersion;
import com.meicloud.services.QueueService;
import com.meicloud.services.ServerService;
import com.meicloud.services.SvnVersionService;
import com.meicloud.utils.SVNUtil;
import com.meicloud.utils.StringUtil;
import com.meicloud.utils.Utils;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.tmatesoft.svn.core.wc.SVNClientManager;

@Component("syncSVNFile")
public class SyncSVNFile {

   private static Logger logger = LoggerFactory.getLogger(SyncSVNFile.class);
   @Autowired
   private QueueService queueService;
   @Autowired
   private ServerService serverService;
   @Autowired
   private Environment environment;
   @Autowired
   private SvnVersionService svnVersionService;


   public boolean doCheckOutAndUpdate() throws Exception {
      logger.info("------------->>>>>>>>>>>>>开始从svn上同步执行脚同步");
      Long version = Long.valueOf(0L);
      String serverIp = this.environment.getProperty("app.server.host");
      Server server = this.serverService.getServerByIP(serverIp);
      if(server == null) {
         logger.info("------------->>>>>>>>>>>>>没有找到服务器信息，请配置");
         return false;
      } else {
         Queue queue = this.queueService.findOne(server.getQueueId());
         String svnUserName = queue.getSvnUserName();
         String svnPassWord = queue.getSvnPassWord();
         String svnUrl = queue.getSvnUrl();
         if(StringUtil.isBlank(svnUrl)) {
            logger.info("------------->>>>>>>>>>>>>没有找到SVN配置信息，请配置");
            return false;
         } else {
            String syncSvnFile = null;
            if(!Utils.isEmptyStr(server.getSvnLogPath())) {
               syncSvnFile = server.getSvnLogPath() + "/" + queue.getQueueCode();
            } else {
               syncSvnFile = System.getProperty("user.dir") + "/" + queue.getQueueCode();
               logger.info("------------->>>>>>>>>>>>>获取项目地址：" + syncSvnFile);
            }

            try {
               SVNClientManager e = SVNUtil.getSVNClientManager(svnUserName, svnPassWord);
               File wcDir = new File(syncSvnFile);
               if(!wcDir.exists()) {
                  logger.info("------------->>>>>>>>>>>>>需要checkOut svn ：下载地址：" + syncSvnFile);
                  SVNUtil.doCheckOut(svnUrl, syncSvnFile, e);
                  logger.info("------------->>>>>>>>>>>>>SVN checkOut结束");
                  return true;
               } else {
                  logger.info("------------->>>>>>>>>>>>>开始比较服务器svn版本和本地svn版本..");
                  version = SVNUtil.getLastVersionNo(svnUrl, e);
                  SvnVersion svnVersion = this.svnVersionService.getByIpNum(serverIp);
                  if(svnVersion == null) {
                     SvnVersion sv = new SvnVersion();
                     sv.setIpNum(serverIp);
                     sv.setVersion(version);
                     this.svnVersionService.add(sv);
                     SVNUtil.doUpdate(syncSvnFile, e);
                     return true;
                  } else if(version.longValue() > svnVersion.getVersion().longValue()) {
                     logger.info("------------->>>>>>>>>>>>>本地svn版本小于服务器svn版本，需要做svn更新..");
                     SVNUtil.doUpdate(syncSvnFile, e);
                     logger.info("------------->>>>>>>>>>>>>svn更新完成");
                     svnVersion.setVersion(version);
                     this.svnVersionService.update(svnVersion);
                     logger.info("------------->>>>>>>>>>>>>数据库版本号更新完成");
                     return true;
                  } else {
                     logger.info("------------->>>>>>>>>>>>>本地svn版本和服务器svn版本一致，不需要做svn更新..");
                     return false;
                  }
               }
            } catch (Exception var13) {
               logger.error(var13.getMessage());
               logger.error("------------->>>>>>>>>>>>>从svn上同步执行脚同步至(" + svnUrl + ")失败,原因:" + var13.getMessage());
               throw var13;
            }
         }
      }
   }
}
