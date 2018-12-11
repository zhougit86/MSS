package com.meicloud.webservice;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class DIServiceInfo implements Serializable {

   private String domainName;
   private String serviceName;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(DIServiceInfo.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "DIServiceInfo"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("domainName");
      elemField.setXmlName(new QName("", "DomainName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("serviceName");
      elemField.setXmlName(new QName("", "ServiceName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public DIServiceInfo() {}

   public DIServiceInfo(String domainName, String serviceName) {
      this.domainName = domainName;
      this.serviceName = serviceName;
   }

   public String getDomainName() {
      return this.domainName;
   }

   public void setDomainName(String domainName) {
      this.domainName = domainName;
   }

   public String getServiceName() {
      return this.serviceName;
   }

   public void setServiceName(String serviceName) {
      this.serviceName = serviceName;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof DIServiceInfo)) {
         return false;
      } else {
         DIServiceInfo other = (DIServiceInfo)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.domainName == null && other.getDomainName() == null || this.domainName != null && this.domainName.equals(other.getDomainName())) && (this.serviceName == null && other.getServiceName() == null || this.serviceName != null && this.serviceName.equals(other.getServiceName()));
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
         if(this.getDomainName() != null) {
            _hashCode += this.getDomainName().hashCode();
         }

         if(this.getServiceName() != null) {
            _hashCode += this.getServiceName().hashCode();
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
