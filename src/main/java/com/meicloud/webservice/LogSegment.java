package com.meicloud.webservice;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class LogSegment implements Serializable {

   private int fileSize;
   private String buffer;
   private boolean endOfLog;
   private int codePage;
   private int bufferSize;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(LogSegment.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "LogSegment"));
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
      elemField.setFieldName("endOfLog");
      elemField.setXmlName(new QName("", "EndOfLog"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "boolean"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("codePage");
      elemField.setXmlName(new QName("", "CodePage"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("bufferSize");
      elemField.setXmlName(new QName("", "BufferSize"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public LogSegment() {}

   public LogSegment(int fileSize, String buffer, boolean endOfLog, int codePage, int bufferSize) {
      this.fileSize = fileSize;
      this.buffer = buffer;
      this.endOfLog = endOfLog;
      this.codePage = codePage;
      this.bufferSize = bufferSize;
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

   public boolean isEndOfLog() {
      return this.endOfLog;
   }

   public void setEndOfLog(boolean endOfLog) {
      this.endOfLog = endOfLog;
   }

   public int getCodePage() {
      return this.codePage;
   }

   public void setCodePage(int codePage) {
      this.codePage = codePage;
   }

   public int getBufferSize() {
      return this.bufferSize;
   }

   public void setBufferSize(int bufferSize) {
      this.bufferSize = bufferSize;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof LogSegment)) {
         return false;
      } else {
         LogSegment other = (LogSegment)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = this.fileSize == other.getFileSize() && (this.buffer == null && other.getBuffer() == null || this.buffer != null && this.buffer.equals(other.getBuffer())) && this.endOfLog == other.isEndOfLog() && this.codePage == other.getCodePage() && this.bufferSize == other.getBufferSize();
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

         _hashCode1 += (this.isEndOfLog()?Boolean.TRUE:Boolean.FALSE).hashCode();
         _hashCode1 += this.getCodePage();
         _hashCode1 += this.getBufferSize();
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
