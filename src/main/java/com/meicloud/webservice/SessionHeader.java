package com.meicloud.webservice;

import com.meicloud.schedule.Utils.SpringUtil;
import com.meicloud.webservice.LoginRequest;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import org.apache.axis.message.SOAPHeaderElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SessionHeader {

   @Autowired
   private Environment environment;


   public void init() {
      if(this.environment == null) {
         ApplicationContext applicationContext = SpringUtil.getApplicationContext();
         this.environment = (Environment)applicationContext.getBean("environment", Environment.class);
      }

   }

   public SOAPHeaderElement createSessionHeader(String sessID) throws SOAPException {
      this.init();
      String WSSE_NS = this.environment.getProperty("webservices.SOAPFactory.WSSE_NS");
      String WSSE_PREFIX = this.environment.getProperty("webservices.SOAPFactory.WSSE_PREFIX");
      Name hdrname = SOAPFactory.newInstance().createName("Context", WSSE_PREFIX, WSSE_NS);
      SOAPHeaderElement header = new SOAPHeaderElement(hdrname);
      SOAPElement token = header.addChildElement("SessionId");
      token.addTextNode(sessID);
      return header;
   }

   public LoginRequest createLoginRequest() {
      this.init();
      LoginRequest loginReq = new LoginRequest();
      loginReq.setRepositoryDomainName(this.environment.getProperty("webservices.login.request.repository.domainName"));
      loginReq.setRepositoryName(this.environment.getProperty("webservices.login.request.repository.name"));
      loginReq.setUserName(this.environment.getProperty("webservices.login.request.username"));
      loginReq.setPassword(this.environment.getProperty("webservices.login.request.password"));
      return loginReq;
   }
}
