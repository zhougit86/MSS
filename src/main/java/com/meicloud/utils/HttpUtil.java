package com.meicloud.utils;

import com.meicloud.model.Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtil {

   private static Logger LOG = Logger.getLogger(HttpUtil.class);
   private HttpResponse response;
   private int statuCode;


   public int getStatuCode() {
      if(this.response != null && this.response.getStatusLine() != null) {
         this.statuCode = this.response.getStatusLine().getStatusCode();
      } else {
         this.statuCode = -1;
      }

      return this.statuCode;
   }

   public String getContent() {
      String result = "";
      BufferedReader br = null;

      try {
         if(this.response != null && this.response.getEntity() != null && this.response.getEntity().getContent() != null) {
            br = new BufferedReader(new InputStreamReader(this.response.getEntity().getContent()));
            StringBuffer e = new StringBuffer();
            String line = null;

            while((line = br.readLine()) != null) {
               e.append(line);
               e.append("\r\n");
            }

            result = e.toString();
            return result;
         }
      } catch (IOException var14) {
         LOG.error(var14.getMessage());
         return result;
      } finally {
         if(br != null) {
            try {
               br.close();
            } catch (IOException var13) {
               LOG.error(var13.getMessage());
            }
         }

      }

      return "";
   }

   public String sendByPut(String url, String json) {
      CloseableHttpClient client = null;

      try {
         LOG.info("url:" + url);
         client = HttpClients.createDefault();
         HttpPut e = new HttpPut(url);
         RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout('\uea60').setConnectTimeout('\uea60').build();
         e.setConfig(requestConfig);
         StringEntity s = new StringEntity(json.toString());
         s.setContentEncoding("UTF-8");
         s.setContentType("application/json");
         e.setEntity(s);
         this.response = client.execute(e);
      } catch (Exception var7) {
         LOG.error(var7.getMessage());
      }

      return "";
   }

   public static String getForm(String url, Map paramMap) throws Exception {
      try {
         CloseableHttpClient e = HttpClients.createDefault();
         HttpGet httpget = new HttpGet(url);
         CloseableHttpResponse response = e.execute(httpget);
         HttpEntity entity = response.getEntity();
         if(entity != null) {
            String responseStr = EntityUtils.toString(entity, "UTF-8");
            return responseStr;
         }
      } catch (Exception var7) {
         LOG.error(var7.getMessage());
      }

      return null;
   }

   public void sendByGet(String url) {
      CloseableHttpClient client = null;

      try {
         LOG.info("url:" + url);
         client = HttpClients.createDefault();
         HttpGet e = new HttpGet(url);
         RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout('\uea60').setConnectTimeout('\uea60').build();
         e.setConfig(requestConfig);
         this.response = client.execute(e);
      } catch (Exception var5) {
         LOG.error(var5.getMessage());
      }

   }

   public void sendByPost(String url) {
      CloseableHttpClient client = null;

      try {
         LOG.info("url:" + url);
         client = HttpClients.createDefault();
         HttpPost e = new HttpPost(url);
         RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout('\uea60').setConnectTimeout('\uea60').build();
         e.setConfig(requestConfig);
         this.response = client.execute(e);
      } catch (Exception var5) {
         LOG.error(var5.getMessage());
      }

   }

   public boolean sendByPost(String url, String json) {
      CloseableHttpClient client = null;

      try {
         LOG.info("url:" + url);
         client = HttpClients.createDefault();
         HttpPost e = new HttpPost(url);
         RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout('\uea60').build();
         e.setConfig(requestConfig);
         StringEntity s = new StringEntity(json.toString());
         s.setContentEncoding("UTF-8");
         s.setContentType("application/json");
         e.setEntity(s);
         this.response = client.execute(e);
         if(this.response != null && this.response.getStatusLine() != null && this.response.getStatusLine().getStatusCode() == 200) {
            return true;
         }
      } catch (Exception var7) {
         LOG.error(var7);
      }

      return false;
   }

   public boolean sendByPostRe(String url, String json) {
      CloseableHttpClient client = null;

      try {
         LOG.info("url:" + url);
         client = HttpClients.createDefault();
         HttpPost e = new HttpPost(url);
         RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
         e.setConfig(requestConfig);
         StringEntity s = new StringEntity(json.toString());
         s.setContentEncoding("UTF-8");
         s.setContentType("application/json");
         e.setEntity(s);
         this.response = client.execute(e);
         if(this.response != null && this.response.getStatusLine() != null && this.response.getStatusLine().getStatusCode() == 200) {
            return true;
         }
      } catch (Exception var7) {
         LOG.error(var7);
      }

      return false;
   }

   public boolean sendByPostRe(String url) {
      CloseableHttpClient client = null;

      try {
         LOG.info("url:" + url);
         client = HttpClients.createDefault();
         HttpPost e = new HttpPost(url);
         RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout('\uea60').setConnectTimeout('\uea60').build();
         e.setConfig(requestConfig);
         this.response = client.execute(e);
         if(this.response != null && this.response.getStatusLine() != null && this.response.getStatusLine().getStatusCode() == 200) {
            return true;
         }
      } catch (Exception var5) {
         LOG.error(var5.getMessage());
      }

      return false;
   }

   public boolean testESAlive(Server server) {
      boolean alive = false;
      String url = "";
      CloseableHttpClient httpClient = null;

      try {
         url = server.getTestAliveUrl();
         httpClient = HttpClients.createDefault();
         HttpPost e = new HttpPost(url);
         RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1500).setConnectTimeout(1500).build();
         e.setConfig(requestConfig);
         CloseableHttpResponse response = httpClient.execute(e);
         if(response != null && response.getStatusLine() != null && response.getStatusLine().getStatusCode() == 200) {
            alive = true;
         } else {
            alive = false;
         }
      } catch (Exception var16) {
         LOG.error(var16);
      } finally {
         if(httpClient != null) {
            try {
               httpClient.close();
            } catch (IOException var15) {
               LOG.error(var15.getMessage());
            }
         }

      }

      LOG.info("url:" + url + "   alive:" + alive);
      return alive;
   }

   public static boolean checkConnection(String url) {
      try {
         CloseableHttpClient e = HttpClients.createDefault();
         HttpPost post = new HttpPost(url);
         RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1500).setConnectTimeout(1500).build();
         post.setConfig(requestConfig);
         CloseableHttpResponse response = e.execute(post);
         if(response != null && response.getStatusLine() != null && response.getStatusLine().getStatusCode() == 200) {
            return true;
         }
      } catch (Exception var5) {
         LOG.error(var5.getMessage());
      }

      return false;
   }

   public static void main(String[] args) {
      Server server = new Server();
      server.setTestAliveUrl("http://10.16.68.211:8020/MSS_ES/");
      HttpUtil httpUtil = new HttpUtil();
      boolean alive = httpUtil.testESAlive(server);
      System.out.println("alive:" + alive);
   }
}
