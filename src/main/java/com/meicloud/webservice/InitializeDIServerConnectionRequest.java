package com.meicloud.webservice;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class InitializeDIServerConnectionRequest implements Serializable {

   private String loginHandle;
   private String DIServerName;
   private String DIServerDomain;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(InitializeDIServerConnectionRequest.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "InitializeDIServerConnectionRequest"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("loginHandle");
      elemField.setXmlName(new QName("", "LoginHandle"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("DIServerName");
      elemField.setXmlName(new QName("", "DIServerName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("DIServerDomain");
      elemField.setXmlName(new QName("", "DIServerDomain"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
   }

   public InitializeDIServerConnectionRequest() {}

   public InitializeDIServerConnectionRequest(String loginHandle, String DIServerName, String DIServerDomain) {
      this.loginHandle = loginHandle;
      this.DIServerName = DIServerName;
      this.DIServerDomain = DIServerDomain;
   }

   public String getLoginHandle() {
      return this.loginHandle;
   }

   public void setLoginHandle(String loginHandle) {
      this.loginHandle = loginHandle;
   }

   public String getDIServerName() {
      return this.DIServerName;
   }

   public void setDIServerName(String DIServerName) {
      this.DIServerName = DIServerName;
   }

   public String getDIServerDomain() {
      return this.DIServerDomain;
   }

   public void setDIServerDomain(String DIServerDomain) {
      this.DIServerDomain = DIServerDomain;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof InitializeDIServerConnectionRequest)) {
         return false;
      } else {
         InitializeDIServerConnectionRequest other = (InitializeDIServerConnectionRequest)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.loginHandle == null && other.getLoginHandle() == null || this.loginHandle != null && this.loginHandle.equals(other.getLoginHandle())) && (this.DIServerName == null && other.getDIServerName() == null || this.DIServerName != null && this.DIServerName.equals(other.getDIServerName())) && (this.DIServerDomain == null && other.getDIServerDomain() == null || this.DIServerDomain != null && this.DIServerDomain.equals(other.getDIServerDomain()));
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
         if(this.getLoginHandle() != null) {
            _hashCode += this.getLoginHandle().hashCode();
         }

         if(this.getDIServerName() != null) {
            _hashCode += this.getDIServerName().hashCode();
         }

         if(this.getDIServerDomain() != null) {
            _hashCode += this.getDIServerDomain().hashCode();
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
