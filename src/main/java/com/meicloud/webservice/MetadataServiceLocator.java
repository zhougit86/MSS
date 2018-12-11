package com.meicloud.webservice;

import com.meicloud.webservice.MetadataInterface;
import com.meicloud.webservice.MetadataService;
import com.meicloud.webservice.MetadataServiceSoapBindingStub;
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

public class MetadataServiceLocator extends Service implements MetadataService {

   private String Metadata_address = "http://10.16.66.78:7333/wsh/services/BatchServices/Metadata";
   private String MetadataWSDDServiceName = "Metadata";
   private HashSet ports = null;


   public MetadataServiceLocator() {}

   public MetadataServiceLocator(EngineConfiguration config) {
      super(config);
   }

   public MetadataServiceLocator(String wsdlLoc, QName sName) throws ServiceException {
      super(wsdlLoc, sName);
   }

   public String getMetadataAddress() {
      return this.Metadata_address;
   }

   public String getMetadataWSDDServiceName() {
      return this.MetadataWSDDServiceName;
   }

   public void setMetadataWSDDServiceName(String name) {
      this.MetadataWSDDServiceName = name;
   }

   public MetadataInterface getMetadata() throws ServiceException {
      URL endpoint;
      try {
         endpoint = new URL(this.Metadata_address);
      } catch (MalformedURLException var3) {
         throw new ServiceException(var3);
      }

      return this.getMetadata(endpoint);
   }

   public MetadataInterface getMetadata(URL portAddress) throws ServiceException {
      try {
         MetadataServiceSoapBindingStub e = new MetadataServiceSoapBindingStub(portAddress, this);
         e.setPortName(this.getMetadataWSDDServiceName());
         return e;
      } catch (AxisFault var3) {
         return null;
      }
   }

   public void setMetadataEndpointAddress(String address) {
      this.Metadata_address = address;
   }

   public Remote getPort(Class serviceEndpointInterface) throws ServiceException {
      try {
         if(MetadataInterface.class.isAssignableFrom(serviceEndpointInterface)) {
            MetadataServiceSoapBindingStub t = new MetadataServiceSoapBindingStub(new URL(this.Metadata_address), this);
            t.setPortName(this.getMetadataWSDDServiceName());
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
         if("Metadata".equals(inputPortName)) {
            return this.getMetadata();
         } else {
            Remote _stub = this.getPort(serviceEndpointInterface);
            ((Stub)_stub).setPortName(portName);
            return _stub;
         }
      }
   }

   public QName getServiceName() {
      return new QName("http://www.informatica.com/wsh", "MetadataService");
   }

   public Iterator getPorts() {
      if(this.ports == null) {
         this.ports = new HashSet();
         this.ports.add(new QName("http://www.informatica.com/wsh", "Metadata"));
      }

      return this.ports.iterator();
   }

   public void setEndpointAddress(String portName, String address) throws ServiceException {
      if("Metadata".equals(portName)) {
         this.setMetadataEndpointAddress(address);
      } else {
         throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
      }
   }

   public void setEndpointAddress(QName portName, String address) throws ServiceException {
      this.setEndpointAddress(portName.getLocalPart(), address);
   }
}
