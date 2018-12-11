package com.meicloud.webservice;

import com.meicloud.webservice.DIServiceInfo;
import com.meicloud.webservice.EDIServerMonitorMode;
import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class MonitorDIServerRequest implements Serializable {

   private DIServiceInfo DIServiceInfo;
   private EDIServerMonitorMode monitorMode;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(MonitorDIServerRequest.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "MonitorDIServerRequest"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("DIServiceInfo");
      elemField.setXmlName(new QName("", "DIServiceInfo"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "DIServiceInfo"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("monitorMode");
      elemField.setXmlName(new QName("", "MonitorMode"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "EDIServerMonitorMode"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public MonitorDIServerRequest() {}

   public MonitorDIServerRequest(DIServiceInfo DIServiceInfo, EDIServerMonitorMode monitorMode) {
      this.DIServiceInfo = DIServiceInfo;
      this.monitorMode = monitorMode;
   }

   public DIServiceInfo getDIServiceInfo() {
      return this.DIServiceInfo;
   }

   public void setDIServiceInfo(DIServiceInfo DIServiceInfo) {
      this.DIServiceInfo = DIServiceInfo;
   }

   public EDIServerMonitorMode getMonitorMode() {
      return this.monitorMode;
   }

   public void setMonitorMode(EDIServerMonitorMode monitorMode) {
      this.monitorMode = monitorMode;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof MonitorDIServerRequest)) {
         return false;
      } else {
         MonitorDIServerRequest other = (MonitorDIServerRequest)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.DIServiceInfo == null && other.getDIServiceInfo() == null || this.DIServiceInfo != null && this.DIServiceInfo.equals(other.getDIServiceInfo())) && (this.monitorMode == null && other.getMonitorMode() == null || this.monitorMode != null && this.monitorMode.equals(other.getMonitorMode()));
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
         if(this.getDIServiceInfo() != null) {
            _hashCode += this.getDIServiceInfo().hashCode();
         }

         if(this.getMonitorMode() != null) {
            _hashCode += this.getMonitorMode().hashCode();
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
