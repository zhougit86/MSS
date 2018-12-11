package com.meicloud.webservice;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class Log implements Serializable {

   private int fileSize;
   private String buffer;
   private int codePage;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(Log.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "Log"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("fileSize");
      elemField.setXmlName(new QName("", "FileSize"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("buffer");
      elemField.setXmlName(new QName("", "Buffer"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("codePage");
      elemField.setXmlName(new QName("", "CodePage"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public Log() {}

   public Log(int fileSize, String buffer, int codePage) {
      this.fileSize = fileSize;
      this.buffer = buffer;
      this.codePage = codePage;
   }

   public int getFileSize() {
      return this.fileSize;
   }

   public void setFileSize(int fileSize) {
      this.fileSize = fileSize;
   }

   public String getBuffer() {
      return this.buffer;
   }

   public void setBuffer(String buffer) {
      this.buffer = buffer;
   }

   public int getCodePage() {
      return this.codePage;
   }

   public void setCodePage(int codePage) {
      this.codePage = codePage;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof Log)) {
         return false;
      } else {
         Log other = (Log)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = this.fileSize == other.getFileSize() && (this.buffer == null && other.getBuffer() == null || this.buffer != null && this.buffer.equals(other.getBuffer())) && this.codePage == other.getCodePage();
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
         byte _hashCode = 1;
         int _hashCode1 = _hashCode + this.getFileSize();
         if(this.getBuffer() != null) {
            _hashCode1 += this.getBuffer().hashCode();
         }

         _hashCode1 += this.getCodePage();
         this.__hashCodeCalc = false;
         return _hashCode1;
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
