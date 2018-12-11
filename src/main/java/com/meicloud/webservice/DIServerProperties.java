package com.meicloud.webservice;

import com.meicloud.webservice.DIServerDate;
import com.meicloud.webservice.EDIServerMode;
import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class DIServerProperties implements Serializable {

   private String DIServerName;
   private String repositoryname;
   private boolean canInfaServerDebugMapping;
   private EDIServerMode DIServerMode;
   private String DIServerVersion;
   private DIServerDate currentTime;
   private DIServerDate startupTime;
   private String productName;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(DIServerProperties.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "DIServerProperties"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("DIServerName");
      elemField.setXmlName(new QName("", "DIServerName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("repositoryname");
      elemField.setXmlName(new QName("", "Repositoryname"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("canInfaServerDebugMapping");
      elemField.setXmlName(new QName("", "CanInfaServerDebugMapping"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "boolean"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("DIServerMode");
      elemField.setXmlName(new QName("", "DIServerMode"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "EDIServerMode"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("DIServerVersion");
      elemField.setXmlName(new QName("", "DIServerVersion"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("currentTime");
      elemField.setXmlName(new QName("", "CurrentTime"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "DIServerDate"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("startupTime");
      elemField.setXmlName(new QName("", "StartupTime"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "DIServerDate"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("productName");
      elemField.setXmlName(new QName("", "ProductName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
   }

   public DIServerProperties() {}

   public DIServerProperties(String DIServerName, String repositoryname, boolean canInfaServerDebugMapping, EDIServerMode DIServerMode, String DIServerVersion, DIServerDate currentTime, DIServerDate startupTime, String productName) {
      this.DIServerName = DIServerName;
      this.repositoryname = repositoryname;
      this.canInfaServerDebugMapping = canInfaServerDebugMapping;
      this.DIServerMode = DIServerMode;
      this.DIServerVersion = DIServerVersion;
      this.currentTime = currentTime;
      this.startupTime = startupTime;
      this.productName = productName;
   }

   public String getDIServerName() {
      return this.DIServerName;
   }

   public void setDIServerName(String DIServerName) {
      this.DIServerName = DIServerName;
   }

   public String getRepositoryname() {
      return this.repositoryname;
   }

   public void setRepositoryname(String repositoryname) {
      this.repositoryname = repositoryname;
   }

   public boolean isCanInfaServerDebugMapping() {
      return this.canInfaServerDebugMapping;
   }

   public void setCanInfaServerDebugMapping(boolean canInfaServerDebugMapping) {
      this.canInfaServerDebugMapping = canInfaServerDebugMapping;
   }

   public EDIServerMode getDIServerMode() {
      return this.DIServerMode;
   }

   public void setDIServerMode(EDIServerMode DIServerMode) {
      this.DIServerMode = DIServerMode;
   }

   public String getDIServerVersion() {
      return this.DIServerVersion;
   }

   public void setDIServerVersion(String DIServerVersion) {
      this.DIServerVersion = DIServerVersion;
   }

   public DIServerDate getCurrentTime() {
      return this.currentTime;
   }

   public void setCurrentTime(DIServerDate currentTime) {
      this.currentTime = currentTime;
   }

   public DIServerDate getStartupTime() {
      return this.startupTime;
   }

   public void setStartupTime(DIServerDate startupTime) {
      this.startupTime = startupTime;
   }

   public String getProductName() {
      return this.productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof DIServerProperties)) {
         return false;
      } else {
         DIServerProperties other = (DIServerProperties)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.DIServerName == null && other.getDIServerName() == null || this.DIServerName != null && this.DIServerName.equals(other.getDIServerName())) && (this.repositoryname == null && other.getRepositoryname() == null || this.repositoryname != null && this.repositoryname.equals(other.getRepositoryname())) && this.canInfaServerDebugMapping == other.isCanInfaServerDebugMapping() && (this.DIServerMode == null && other.getDIServerMode() == null || this.DIServerMode != null && this.DIServerMode.equals(other.getDIServerMode())) && (this.DIServerVersion == null && other.getDIServerVersion() == null || this.DIServerVersion != null && this.DIServerVersion.equals(other.getDIServerVersion())) && (this.currentTime == null && other.getCurrentTime() == null || this.currentTime != null && this.currentTime.equals(other.getCurrentTime())) && (this.startupTime == null && other.getStartupTime() == null || this.startupTime != null && this.startupTime.equals(other.getStartupTime())) && (this.productName == null && other.getProductName() == null || this.productName != null && this.productName.equals(other.getProductName()));
            this.__equalsCalc = null;
            return _equals;
         }
      }
   }

   public synchronized int hashCode() {
      if(this.__hashCodeCalc) {
         return 0;
      } else {
         this.__hashCodeCalc = true;
         int _hashCode = 1;
         if(this.getDIServerName() != null) {
            _hashCode += this.getDIServerName().hashCode();
         }

         if(this.getRepositoryname() != null) {
            _hashCode += this.getRepositoryname().hashCode();
         }

         _hashCode += (this.isCanInfaServerDebugMapping()?Boolean.TRUE:Boolean.FALSE).hashCode();
         if(this.getDIServerMode() != null) {
            _hashCode += this.getDIServerMode().hashCode();
         }

         if(this.getDIServerVersion() != null) {
            _hashCode += this.getDIServerVersion().hashCode();
         }

         if(this.getCurrentTime() != null) {
            _hashCode += this.getCurrentTime().hashCode();
         }

         if(this.getStartupTime() != null) {
            _hashCode += this.getStartupTime().hashCode();
         }

         if(this.getProductName() != null) {
            _hashCode += this.getProductName().hashCode();
         }

         this.__hashCodeCalc = false;
         return _hashCode;
      }
   }

   public static TypeDesc getTypeDesc() {
      return typeDesc;
   }

   public static Serializer getSerializer(String mechType, Class _javaType, QName _xmlType) {
      return new BeanSerializer(_javaType, _xmlType, typeDesc);
   }

   public static Deserializer getDeserializer(String mechType, Class _javaType, QName _xmlType) {
      return new BeanDeserializer(_javaType, _xmlType, typeDesc);
   }
}
