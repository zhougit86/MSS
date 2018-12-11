package com.meicloud.webservice;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class GetNextLogSegmentRequest implements Serializable {

   private int logHandle;
   private int timeOut;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(GetNextLogSegmentRequest.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "GetNextLogSegmentRequest"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("logHandle");
      elemField.setXmlName(new QName("", "LogHandle"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("timeOut");
      elemField.setXmlName(new QName("", "TimeOut"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public GetNextLogSegmentRequest() {}

   public GetNextLogSegmentRequest(int logHandle, int timeOut) {
      this.logHandle = logHandle;
      this.timeOut = timeOut;
   }

   public int getLogHandle() {
      return this.logHandle;
   }

   public void setLogHandle(int logHandle) {
      this.logHandle = logHandle;
   }

   public int getTimeOut() {
      return this.timeOut;
   }

   public void setTimeOut(int timeOut) {
      this.timeOut = timeOut;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof GetNextLogSegmentRequest)) {
         return false;
      } else {
         GetNextLogSegmentRequest other = (GetNextLogSegmentRequest)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = this.logHandle == other.getLogHandle() && this.timeOut == other.getTimeOut();
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
         int _hashCode1 = _hashCode + this.getLogHandle();
         _hashCode1 += this.getTimeOut();
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
