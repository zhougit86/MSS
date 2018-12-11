package com.meicloud.utils;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class WebUtil {

   private static Logger LOG = Logger.getLogger(WebUtil.class);


   public static void writeJson(HttpServletResponse response, String msg) {
      try {
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter e = response.getWriter();
         e.write(msg);
         response.flushBuffer();
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
      }

   }
}
