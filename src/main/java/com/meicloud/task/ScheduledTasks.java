package com.meicloud.task;

import com.meicloud.model.Queue;
import com.meicloud.model.Server;
import com.meicloud.services.QueueService;
import com.meicloud.services.ServerService;
import com.meicloud.utils.DateUtils;
import com.meicloud.utils.FileUtil;
import com.meicloud.utils.Utils;
import com.meicloud.worker.SyncGitFile;
import com.meicloud.worker.SyncSVNFile;
import java.util.Date;
import javax.annotation.Resource;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ScheduledTasks {

   private static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
   @Resource
   private SyncSVNFile syncSVNFile;
   @Resource
   private SyncGitFile syncGitFile;
   @Resource
   private Environment environment;
   @Resource
   private ServerService serverService;
   @Resource
   private QueueService queueService;


   @Scheduled(
      cron = "${sync.svn.file}"
   )
   public void execute() throws Exception {
      logger.info("Svn 下载,更新定时器执行开始:" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));

      try {
         FileUtil.svnTree.clear();
         logger.info("开始更新svn文件 时间:" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
         boolean e = this.syncSVNFile.doCheckOutAndUpdate();
         e = this.syncGitFile.doCheckOutAndUpdate();
         if(e) {
            this.setPath();
         } else if(FileUtil.svnTree.size() == 0) {
            this.setPath();
         }

         logger.info("Svn 下载,更新定时器执行结束:" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
      } catch (Exception var2) {
         logger.error("SVN 下载，更新执行失败:" + var2.getMessage());
         throw new Exception("SVN 下载，更新执行失败");
      }
   }

   private void setPath() {
      String endsWith = this.environment.getProperty("kettle_suffix");
      String serverIp = this.environment.getProperty("app.server.host");
      Server server = this.serverService.getServerByIP(serverIp);
      if(server == null) {
         logger.info("主机不存在：" + serverIp);
      } else {
         Queue queue = this.queueService.findOne(server.getQueueId());
         String syncSvnFile = null;
         if(!Utils.isEmptyStr(server.getSvnLogPath())) {
            syncSvnFile = server.getSvnLogPath() + "/" + queue.getQueueCode();
         } else {
            syncSvnFile = System.getProperty("user.dir") + "/" + queue.getQueueCode();
            logger.info("获取项目地址：" + syncSvnFile);
         }

         logger.info("------------->>>>>>>>>>>>>开始将下载的svn执行脚步加载至缓存 ，目录为：" + syncSvnFile);
         FileUtil.getFile(syncSvnFile, syncSvnFile, endsWith);
         logger.info("------------->>>>>>>>>>>>>完成将下载的svn执行脚步加载至缓存 ，目录为：" + syncSvnFile);
      }
   }

   private void setGitPath() {
      String endsWith = this.environment.getProperty("kettle_suffix");
      String serverIp = this.environment.getProperty("app.server.host");
      Server server = this.serverService.getServerByIP(serverIp);
      if(server == null) {
         logger.info("主机不存在：" + serverIp);
      } else {
         Queue queue = this.queueService.findOne(server.getQueueId());
         String gitSvnFile = null;
         if(!Utils.isEmptyStr(server.getSvnLogPath())) {
            gitSvnFile = server.getSvnLogPath() + "/" + queue.getQueueCode() + "/mssgit";
         } else {
            gitSvnFile = System.getProperty("user.dir") + "/" + queue.getQueueCode() + "/mssgit";
            logger.info("获取项目地址：" + gitSvnFile);
         }

         logger.info("------------->>>>>>>>>>>>>开始将下载的svn执行脚步加载至缓存 ，目录为：" + gitSvnFile);
         FileUtil.getFile(gitSvnFile, gitSvnFile, endsWith);
         logger.info("------------->>>>>>>>>>>>>完成将下载的svn执行脚步加载至缓存 ，目录为：" + gitSvnFile);
      }
   }
}
