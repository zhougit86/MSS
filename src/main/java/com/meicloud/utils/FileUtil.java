package com.meicloud.utils;

import com.meicloud.dto.TreeInfo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

   private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
   public static final List svnTree = new ArrayList();


   public static void getFile(String path, String replacePath, String endsWith) {
      String newPath = "";
      if(path.contains("\\")) {
         newPath = path.replace("\\", "/");
      }

      if(path.contains("/")) {
         newPath = path.replace("/", "\\");
      }

      File file = new File(path);
      if(!file.exists()) {
         file = new File(newPath);
         if(!file.exists()) {
            logger.info("文件路径不存在");
            return;
         }
      }

      File[] array = file.listFiles();
      if(array.length <= 0) {
         logger.info("文件目录为空");
      } else {
         for(int i = 0; i < array.length; ++i) {
            String filePath = array[i].getPath().replace("\\", "/");
            replacePath = replacePath.replace("\\", "/");
            String dicPath = filePath.replace(replacePath, "");
            if(!dicPath.contains("/.svn") && !dicPath.contains("/.SVN")) {
               TreeInfo treeInfo;
               if(array[i].isFile()) {
                  if(dicPath.endsWith(endsWith)) {
                     treeInfo = new TreeInfo();
                     treeInfo.setId(dicPath);
                     treeInfo.setpId(dicPath.substring(0, dicPath.lastIndexOf("/")));
                     treeInfo.setName(array[i].getName());
                     treeInfo.setType("F");
                     svnTree.add(treeInfo);
                  }
               } else if(array[i].isDirectory()) {
                  treeInfo = new TreeInfo();
                  treeInfo.setId(dicPath);
                  treeInfo.setpId(dicPath.substring(0, dicPath.lastIndexOf("/")));
                  treeInfo.setName(array[i].getName());
                  treeInfo.setType("D");
                  svnTree.add(treeInfo);
                  getFile(filePath, replacePath, endsWith);
               }
            }
         }

      }
   }

   public static boolean createDir(String destDirName) {
      File dir = new File(destDirName);
      if(dir.exists()) {
         return true;
      } else {
         if(!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
         }

         if(dir.mkdirs()) {
            logger.info("创建目录" + destDirName + "成功！");
            return true;
         } else {
            logger.info("创建目录" + destDirName + "失败！");
            return false;
         }
      }
   }

   public static boolean createFile(String destFileName) {
      File file = new File(destFileName);
      if(file.exists()) {
         logger.info("创建单个文件" + destFileName + "失败，目标文件已存在！");
         return false;
      } else if(destFileName.endsWith(File.separator)) {
         logger.info("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
         return false;
      } else {
         if(!file.getParentFile().exists()) {
            logger.info("目标文件所在目录不存在，准备创建它！");
            if(!file.getParentFile().mkdirs()) {
               logger.info("创建目标文件所在目录失败！");
               return false;
            }
         }

         try {
            if(file.createNewFile()) {
               logger.info("创建单个文件" + destFileName + "成功！");
               return true;
            } else {
               logger.info("创建单个文件" + destFileName + "失败！");
               return false;
            }
         } catch (IOException var3) {
            logger.info("创建单个文件" + destFileName + "失败！" + var3.getMessage());
            logger.error(var3.getMessage());
            return false;
         }
      }
   }

   public static String readToString(String fileName) {
      File file = new File(fileName);
      FileReader reader = null;
      BufferedReader bReader = null;
      StringBuilder sb = new StringBuilder();

      try {
         reader = new FileReader(file);
         bReader = new BufferedReader(reader);
         String e = "";

         while((e = bReader.readLine()) != null) {
            sb.append(e + "\n");
         }
      } catch (FileNotFoundException var16) {
         logger.error(var16.getMessage());
      } catch (IOException var17) {
         logger.error(var17.getMessage());
      } finally {
         try {
            if(bReader != null) {
               bReader.close();
            }

            if(reader != null) {
               reader.close();
            }
         } catch (Exception var15) {
            logger.error(var15.getMessage());
         }

      }

      return sb.toString();
   }

   public static void WriteStringToFile(String filePath, String context) {
      PrintStream ps = null;

      try {
         File e = new File(filePath);
         if(!e.getParentFile().exists()) {
            createDir(e.getPath());
         }

         if(e.exists()) {
            if(e.delete()) {
               logger.info("删除文件成功");
            } else {
               logger.info("删除文件失败");
            }
         }

         ps = new PrintStream(new FileOutputStream(e));
         ps.println(context);
         ps.flush();
      } catch (FileNotFoundException var12) {
         logger.error(var12.getMessage());
      } finally {
         try {
            if(ps != null) {
               ps.close();
            }
         } catch (Exception var11) {
            logger.error(var11.getMessage());
         }

      }

   }

   public static void writeFile(File file, String content) {
      FileOutputStream fos = null;
      BufferedWriter bWriter = null;

      try {
         if(!file.exists()) {
            File e = file.getParentFile();
            e.mkdirs();
            if(file.createNewFile()) {
               logger.info("创建成功");
            } else {
               logger.info("创建失败");
            }
         }

         fos = new FileOutputStream(file, true);
         bWriter = new BufferedWriter(new OutputStreamWriter(fos));
         bWriter.write(content);
         bWriter.write("\n");
         bWriter.flush();
      } catch (Exception var13) {
         logger.error(var13.getMessage());
      } finally {
         try {
            if(bWriter != null) {
               bWriter.close();
            }

            if(fos != null) {
               fos.close();
            }
         } catch (Exception var12) {
            logger.error(var12.getMessage());
         }

      }

   }

   public static void main(String[] args) {
      WriteStringToFile("D:\\logs\\a\\a.sh", "ccc");
   }
}
