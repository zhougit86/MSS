package com.meicloud.webservice;

import com.meicloud.webservice.EPerformanceCounterType;
import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class PerformanceCounter implements Serializable {

   private String counterName;
   private int counterValue;
   private String widgetName;
   private String counterStringValue;
   private EPerformanceCounterType counterType;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(PerformanceCounter.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "PerformanceCounter"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("counterName");
      elemField.setXmlName(new QName("", "CounterName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("counterValue");
      elemField.setXmlName(new QName("", "CounterValue"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("widgetName");
      elemField.setXmlName(new QName("", "WidgetName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("counterStringValue");
      elemField.setXmlName(new QName("", "CounterStringValue"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("counterType");
      elemField.setXmlName(new QName("", "CounterType"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "EPerformanceCounterType"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public PerformanceCounter() {}

   public PerformanceCounter(String counterName, int counterValue, String widgetName, String counterStringValue, EPerformanceCounterType counterType) {
      this.counterName = counterName;
      this.counterValue = counterValue;
      this.widgetName = widgetName;
      this.counterStringValue = counterStringValue;
      this.counterType = counterType;
   }

   public String getCounterName() {
      return this.counterName;
   }

   public void setCounterName(String counterName) {
      this.counterName = counterName;
   }

   public int getCounterValue() {
      return this.counterValue;
   }

   public void setCounterValue(int counterValue) {
      this.counterValue = counterValue;
   }

   public String getWidgetName() {
      return this.widgetName;
   }

   public void setWidgetName(String widgetName) {
      this.widgetName = widgetName;
   }

   public String getCounterStringValue() {
      return this.counterStringValue;
   }

   public void setCounterStringValue(String counterStringValue) {
      this.counterStringValue = counterStringValue;
   }

   public EPerformanceCounterType getCounterType() {
      return this.counterType;
   }

   public void setCounterType(EPerformanceCounterType counterType) {
      this.counterType = counterType;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof PerformanceCounter)) {
         return false;
      } else {
         PerformanceCounter other = (PerformanceCounter)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.counterName == null && other.getCounterName() == null || this.counterName != null && this.counterName.equals(other.getCounterName())) && this.counterValue == other.getCounterValue() && (this.widgetName == null && other.getWidgetName() == null || this.widgetName != null && this.widgetName.equals(other.getWidgetName())) && (this.counterStringValue == null && other.getCounterStringValue() == null || this.counterStringValue != null && this.counterStringValue.equals(other.getCounterStringValue())) && (this.counterType == null && other.getCounterType() == null || this.counterType != null && this.counterType.equals(other.getCounterType()));
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
         if(this.getCounterName() != null) {
            _hashCode += this.getCounterName().hashCode();
         }

         _hashCode += this.getCounterValue();
         if(this.getWidgetName() != null) {
            _hashCode += this.getWidgetName().hashCode();
         }

         if(this.getCounterStringValue() != null) {
            _hashCode += this.getCounterStringValue().hashCode();
         }

         if(this.getCounterType() != null) {
            _hashCode += this.getCounterType().hashCode();
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
