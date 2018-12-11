package com.meicloud.utils;

import java.io.File;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.SubmoduleInitCommand;
import org.eclipse.jgit.api.SubmoduleUpdateCommand;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JGitUtil {

   private static Logger LOG = LoggerFactory.getLogger(JGitUtil.class);


   public static boolean cloneRepository(String url, String localPath, String username, String password) {
      try {
         LOG.info("开始下载......");
         CloneCommand e = Git.cloneRepository().setURI(url);
         UsernamePasswordCredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(username, password);
         e.setCredentialsProvider(credentialsProvider);
         e.setDirectory(new File(localPath)).call();
         LOG.info("下载完成......");
         return true;
      } catch (Exception var6) {
         LOG.error(var6.getMessage());
         return false;
      }
   }

   public static boolean cloneSubRepository(String localPath, String sub, String username, String password) {
      try {
         File e = new File(localPath + "\\.git\\modules\\" + sub);
         if(e.exists()) {
            deleteDir(e);
         }

         Git git = Git.open(new File(localPath));
         LOG.info("开始下载子仓。。。");
         SubmoduleInitCommand submoduleInit = git.submoduleInit();
         SubmoduleUpdateCommand submoduleUpdate = git.submoduleUpdate();
         UsernamePasswordCredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(username, password);
         submoduleUpdate.setCredentialsProvider(credentialsProvider);
         String[] submoduleArr = sub.split(",");
         String[] var13 = submoduleArr;
         int var12 = submoduleArr.length;

         for(int var11 = 0; var11 < var12; ++var11) {
            String s = var13[var11];
            LOG.info("准备下载子仓：" + s);
            submoduleInit.addPath(s);
            submoduleUpdate.addPath(s);
         }

         submoduleInit.call();
         submoduleUpdate.call();
         LOG.info("子仓下载完成。。。");
         return true;
      } catch (Exception var14) {
         LOG.error(var14.getMessage());
         return false;
      }
   }

   public static boolean pullRepository(String localPath, String username, String password) {
      try {
         Git e = Git.open(new File(localPath));
         PullCommand pullCommand = e.pull();
         UsernamePasswordCredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(username, password);
         pullCommand.setCredentialsProvider(credentialsProvider);
         pullCommand.call();
         return true;
      } catch (Exception var6) {
         LOG.error(var6.getMessage());
         return false;
      }
   }

   public static boolean pullSubRepository(String localPath, String sub, String username, String password) {
      try {
         String e = localPath + "\\.git\\modules";
         String[] subArr = sub.split(",");
         String[] var9 = subArr;
         int var8 = subArr.length;

         for(int var7 = 0; var7 < var8; ++var7) {
            String path = var9[var7];
            Git git = Git.open(new File(e + path));
            PullCommand pullCommand = git.pull();
            UsernamePasswordCredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(username, password);
            pullCommand.setCredentialsProvider(credentialsProvider);
            pullCommand.call();
         }

         return true;
      } catch (Exception var13) {
         LOG.error(var13.getMessage());
         return false;
      }
   }

   private static boolean deleteDir(File dir) {
      if(dir.isDirectory()) {
         String[] children = dir.list();

         for(int i = 0; i < children.length; ++i) {
            boolean success = deleteDir(new File(dir, children[i]));
            if(!success) {
               return false;
            }
         }
      }

      return dir.delete();
   }

   public static void main(String[] args) {
      String localPath = "F:\\Git";
      String url = "http://10.16.28.73/IBD/bdis.git";
      String username = "luoheng2";
      String password = "luoheng303#";
      pullRepository(localPath, username, password);
   }
}
