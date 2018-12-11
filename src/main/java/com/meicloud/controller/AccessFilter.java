package com.meicloud.controller;

import com.meicloud.model.Account;
import com.meicloud.utils.IPUtil;
import com.meicloud.utils.PermisionMap;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class AccessFilter implements Filter {

   private static Logger LOG = Logger.getLogger("AccessFilter");


   public void init(FilterConfig filterConfig) throws ServletException {}

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      try {
         HttpServletRequest e = (HttpServletRequest)request;
         HttpServletResponse servletResponse = (HttpServletResponse)response;
         HttpSession session = e.getSession();
         String path = e.getRequestURI();
         String queryParam = e.getQueryString();
         String host = IPUtil.getClientIpAddress(e);
         if(IPUtil.getPort() == 0) {
            IPUtil.setPort(request);
         }

         if(path.indexOf("/tool/tool.do") > -1 || path.indexOf("/static/") > -1 || path.indexOf("login") > -1 || path.contains("/config/")) {
            chain.doFilter(e, servletResponse);
            return;
         }

         if(path.indexOf("de/updateStateAfterKill") > -1 || path.indexOf("de/updateRunJobState") > -1) {
            chain.doFilter(e, servletResponse);
            return;
         }

         Object accountObj = session.getAttribute("user");
         String requestType;
         if(accountObj == null) {
            requestType = path.substring(0, path.indexOf("/MSS_DE/")) + "/MSS_DE/index/tologin.do";
            servletResponse.sendRedirect(requestType);
            return;
         }

         this.accessLog((Account)accountObj, host, path, queryParam);
         requestType = e.getHeader("X-Requested-With");
         if(requestType != null && requestType.equals("XMLHttpRequest")) {
            chain.doFilter(e, servletResponse);
            return;
         }

         chain.doFilter(e, servletResponse);
      } catch (Exception var12) {
         LOG.error(var12.getMessage());
      }

   }

   public void destroy() {}

   private void accessLog(Account account, String host, String path, String queryString) {
      if(path != null && !path.contains("/static/") && !path.contains("/bootstrap/") && !path.contains("/highcharts/")) {
         String msg = " [" + account.getRealName() + "-" + account.getAccount() + "] from [" + host + "] visit " + path;
         if(queryString != null) {
            msg = msg + "?" + queryString;
         }

         LOG.info(msg);
      }

   }

   private boolean checkPass(String uri, String userPerms) {
      try {
         String e = PermisionMap.URL2PERM(uri);
         if(e == null) {
            return true;
         }

         if(userPerms != null && userPerms.indexOf(e) > -1) {
            return true;
         }
      } catch (Exception var4) {
         LOG.error(var4);
      }

      return false;
   }
}
