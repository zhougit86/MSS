package com.meicloud.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletOutputStream;
import org.apache.log4j.Logger;

public class IOstreamUtil {

   private static Logger LOG = Logger.getLogger("IOstreamUtil");
   private static BufferedInputStream bis = null;
   private static BufferedOutputStream bos = null;


   public static void openExcel(InputStream is, ServletOutputStream out) throws IOException {
      try {
         bis = new BufferedInputStream(is);
         bos = new BufferedOutputStream(out);
         byte[] e = new byte[2048];

         int bytesRead;
         while(-1 != (bytesRead = bis.read(e, 0, e.length))) {
            bos.write(e, 0, bytesRead);
         }
      } catch (IOException var7) {
         LOG.error(var7.getMessage());
      } finally {
         if(bis != null) {
            bis.close();
         }

         if(bos != null) {
            bos.close();
         }

      }

   }
}
