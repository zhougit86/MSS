package com.meicloud.webservice;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class TypeStartWorkflowExResponse implements Serializable {

   private int runId;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(TypeStartWorkflowExResponse.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "TypeStartWorkflowExResponse"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("runId");
      elemField.setXmlName(new QName("", "RunId"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public TypeStartWorkflowExResponse() {}

   public TypeStartWorkflowExResponse(int runId) {
      this.runId = runId;
   }

   public int getRunId() {
      return this.runId;
   }

   public void setRunId(int runId) {
      this.runId = runId;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof TypeStartWorkflowExResponse)) {
         return false;
      } else {
         TypeStartWorkflowExResponse other = (TypeStartWorkflowExResponse)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = this.runId == other.getRunId();
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
         int _hashCode1 = _hashCode + this.getRunId();
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
