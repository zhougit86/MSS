package com.meicloud.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.meicloud.datasource.CmsDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationConfig {

   @Autowired
   private Environment env;
   private static Logger LOG = Logger.getLogger("ApplicationConfig");


   @Bean
   public CmsDataSource dataSource() {
      CmsDataSource bean = null;

      try {
         bean = new CmsDataSource(this.env);
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
      }

      return bean;
   }

   @Bean
   public ServletRegistrationBean DruidStatViewServle2() {
      ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), new String[]{"/druid2/*"});
      servletRegistrationBean.addInitParameter("loginUsername", "admin");
      servletRegistrationBean.addInitParameter("loginPassword", "admin");
      servletRegistrationBean.addInitParameter("resetEnable", "false");
      return servletRegistrationBean;
   }

   @Bean
   public FilterRegistrationBean druidStatFilter2() {
      FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter(), new ServletRegistrationBean[0]);
      filterRegistrationBean.addUrlPatterns(new String[]{"/*"});
      filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
      return filterRegistrationBean;
   }
}
