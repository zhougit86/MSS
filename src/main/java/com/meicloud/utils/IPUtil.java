package com.meicloud.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class IPUtil {

   private static InetAddress inetAddress;
   private static int port;


   public static int getPort() {
      return port;
   }

   public static void setPort(ServletRequest request) {
      if(port == 0) {
         port = request.getLocalPort();
      }

   }

   public static InetAddress getInetAddress() {
      try {
         inetAddress = InetAddress.getLocalHost();
         return inetAddress;
      } catch (UnknownHostException var1) {
         System.out.println("unknown host!");
         return null;
      }
   }

   public static String getHostIp() {
      getInetAddress();
      if(inetAddress == null) {
         return null;
      } else {
         String ip = inetAddress.getHostAddress();
         return ip;
      }
   }

   public static String getHostName() {
      getInetAddress();
      if(inetAddress == null) {
         return null;
      } else {
         String name = inetAddress.getHostName();
         return name;
      }
   }

   public static String getRealIp() throws SocketException {
      String localip = null;
      String netip = null;
      Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
      InetAddress ip = null;
      boolean finded = false;

      while(netInterfaces.hasMoreElements() && !finded) {
         NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
         Enumeration address = ni.getInetAddresses();

         while(address.hasMoreElements()) {
            ip = (InetAddress)address.nextElement();
            if(!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
               netip = ip.getHostAddress();
               finded = true;
               break;
            }

            if(ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
               localip = ip.getHostAddress();
            }
         }
      }

      return netip != null && !"".equals(netip)?netip:localip;
   }

   public static String getClientIpAddress(HttpServletRequest request) {
      String ip = request.getHeader("X-Forwarded-For");
      if(ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
         if(ip.length() > 15) {
            String[] ips = ip.split(",");

            for(int index = 0; index < ips.length; ++index) {
               String strIp = ips[index];
               if(!"unknown".equalsIgnoreCase(strIp)) {
                  ip = strIp;
                  break;
               }
            }
         }
      } else {
         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
         }

         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
         }

         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
         }

         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
         }

         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
         }
      }

      return ip;
   }

   public static void main(String[] args) {
      HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
      System.err.println(request.getLocalPort());
   }
}
