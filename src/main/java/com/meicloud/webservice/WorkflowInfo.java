package com.meicloud.webservice;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class WorkflowInfo implements Serializable {

   private String name;
   private boolean isValid;
   private String folderName;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(WorkflowInfo.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "WorkflowInfo"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("name");
      elemField.setXmlName(new QName("", "Name"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("isValid");
      elemField.setXmlName(new QName("", "IsValid"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "boolean"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("folderName");
      elemField.setXmlName(new QName("", "FolderName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public WorkflowInfo() {}

   public WorkflowInfo(String name, boolean isValid, String folderName) {
      this.name = name;
      this.isValid = isValid;
      this.folderName = folderName;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public boolean isIsValid() {
      return this.isValid;
   }

   public void setIsValid(boolean isValid) {
      this.isValid = isValid;
   }

   public String getFolderName() {
      return this.folderName;
   }

   public void setFolderName(String folderName) {
      this.folderName = folderName;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof WorkflowInfo)) {
         return false;
      } else {
         WorkflowInfo other = (WorkflowInfo)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.name == null && other.getName() == null || this.name != null && this.name.equals(other.getName())) && this.isValid == other.isIsValid() && (this.folderName == null && other.getFolderName() == null || this.folderName != null && this.folderName.equals(other.getFolderName()));
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

         _hashCode += (this.isIsValid()?Boolean.TRUE:Boolean.FALSE).hashCode();
         if(this.getFolderName() != null) {
            _hashCode += this.getFolderName().hashCode();
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
