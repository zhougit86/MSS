package com.meicloud.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequest {

   private static Logger logger = LoggerFactory.getLogger(HttpRequest.class);


   public static String sendGet(String url, String param) {
      String result = "";
      BufferedReader in = null;

      try {
         String e = url + "?" + param;
         URL realUrl = new URL(e);
         URLConnection connection = realUrl.openConnection();
         connection.setRequestProperty("accept", "*/*");
         connection.setRequestProperty("connection", "Keep-Alive");
         connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
         connection.connect();
         Map map = connection.getHeaderFields();
         Iterator var9 = map.keySet().iterator();

         String line;
         while(var9.hasNext()) {
            line = (String)var9.next();
            System.out.println(line + "--->" + map.get(line));
         }

         for(in = new BufferedReader(new InputStreamReader(connection.getInputStream())); (line = in.readLine()) != null; result = result + line) {
            ;
         }
      } catch (Exception var18) {
         logger.error("发送GET请求出现异常！" + var18.getMessage());
         logger.error(var18.getMessage());
      } finally {
         try {
            if(in != null) {
               in.close();
            }
         } catch (Exception var17) {
            logger.error(var17.getMessage());
         }

      }

      return result;
   }

   public static String sendPost(String url, String param) {
      PrintWriter out = null;
      BufferedReader in = null;
      String result = "";

      try {
         URL e = new URL(url);
         URLConnection conn = e.openConnection();
         conn.setRequestProperty("accept", "*/*");
         conn.setRequestProperty("connection", "Keep-Alive");
         conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
         conn.setDoOutput(true);
         conn.setDoInput(true);
         out = new PrintWriter(conn.getOutputStream());
         out.print(param);
         out.flush();

         String line;
         for(in = new BufferedReader(new InputStreamReader(conn.getInputStream())); (line = in.readLine()) != null; result = result + line) {
            ;
         }
      } catch (Exception var16) {
         logger.error("发送POST请求出现异常！" + var16.getMessage());
         logger.error(var16.getMessage());
      } finally {
         try {
            if(out != null) {
               out.close();
            }

            if(in != null) {
               in.close();
            }
         } catch (IOException var15) {
            logger.error(var15.getMessage());
         }

      }

      return result;
   }
}
