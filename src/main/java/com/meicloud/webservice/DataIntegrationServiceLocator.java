package com.meicloud.webservice;

import com.meicloud.webservice.DataIntegrationInterface;
import com.meicloud.webservice.DataIntegrationService;
import com.meicloud.webservice.DataIntegrationServiceSoapBindingStub;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.HashSet;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import org.apache.axis.AxisFault;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;

public class DataIntegrationServiceLocator extends Service implements DataIntegrationService {

   private String DataIntegration_address = "http://10.16.66.78:7333/wsh/services/BatchServices/DataIntegration";
   private String DataIntegrationWSDDServiceName = "DataIntegration";
   private HashSet ports = null;


   public DataIntegrationServiceLocator() {}

   public DataIntegrationServiceLocator(EngineConfiguration config) {
      super(config);
   }

   public DataIntegrationServiceLocator(String wsdlLoc, QName sName) throws ServiceException {
      super(wsdlLoc, sName);
   }

   public String getDataIntegrationAddress() {
      return this.DataIntegration_address;
   }

   public String getDataIntegrationWSDDServiceName() {
      return this.DataIntegrationWSDDServiceName;
   }

   public void setDataIntegrationWSDDServiceName(String name) {
      this.DataIntegrationWSDDServiceName = name;
   }

   public DataIntegrationInterface getDataIntegration() throws ServiceException {
      URL endpoint;
      try {
         endpoint = new URL(this.DataIntegration_address);
      } catch (MalformedURLException var3) {
         throw new ServiceException(var3);
      }

      return this.getDataIntegration(endpoint);
   }

   public DataIntegrationInterface getDataIntegration(URL portAddress) throws ServiceException {
      try {
         DataIntegrationServiceSoapBindingStub e = new DataIntegrationServiceSoapBindingStub(portAddress, this);
         e.setPortName(this.getDataIntegrationWSDDServiceName());
         return e;
      } catch (AxisFault var3) {
         return null;
      }
   }

   public void setDataIntegrationEndpointAddress(String address) {
      this.DataIntegration_address = address;
   }

   public Remote getPort(Class serviceEndpointInterface) throws ServiceException {
      try {
         if(DataIntegrationInterface.class.isAssignableFrom(serviceEndpointInterface)) {
            DataIntegrationServiceSoapBindingStub t = new DataIntegrationServiceSoapBindingStub(new URL(this.DataIntegration_address), this);
            t.setPortName(this.getDataIntegrationWSDDServiceName());
            return t;
         }
      } catch (Throwable var3) {
         throw new ServiceException(var3);
      }

      throw new ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null?"null":serviceEndpointInterface.getName()));
   }

   public Remote getPort(QName portName, Class serviceEndpointInterface) throws ServiceException {
      if(portName == null) {
         return this.getPort(serviceEndpointInterface);
      } else {
         String inputPortName = portName.getLocalPart();
         if("DataIntegration".equals(inputPortName)) {
            return this.getDataIntegration();
         } else {
            Remote _stub = this.getPort(serviceEndpointInterface);
            ((Stub)_stub).setPortName(portName);
            return _stub;
         }
      }
   }

   public QName getServiceName() {
      return new QName("http://www.informatica.com/wsh", "DataIntegrationService");
   }

   public Iterator getPorts() {
      if(this.ports == null) {
         this.ports = new HashSet();
         this.ports.add(new QName("http://www.informatica.com/wsh", "DataIntegration"));
      }

      return this.ports.iterator();
   }

   public void setEndpointAddress(String portName, String address) throws ServiceException {
      if("DataIntegration".equals(portName)) {
         this.setDataIntegrationEndpointAddress(address);
      } else {
         throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
      }
   }

   public void setEndpointAddress(QName portName, String address) throws ServiceException {
      this.setEndpointAddress(portName.getLocalPart(), address);
   }
}
