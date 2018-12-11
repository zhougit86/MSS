package com.meicloud.webservice;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class FolderInfo implements Serializable {

   private String name;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(FolderInfo.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "FolderInfo"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("name");
      elemField.setXmlName(new QName("", "Name"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public FolderInfo() {}

   public FolderInfo(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof FolderInfo)) {
         return false;
      } else {
         FolderInfo other = (FolderInfo)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = this.name == null && other.getName() == null || this.name != null && this.name.equals(other.getName());
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
         if(this.getName() != null) {
            _hashCode += this.getName().hashCode();
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
