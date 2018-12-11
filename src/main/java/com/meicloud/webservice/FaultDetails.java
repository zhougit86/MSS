package com.meicloud.webservice;

import java.io.IOException;
import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.AxisFault;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.SerializationContext;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;
import org.xml.sax.Attributes;

public class FaultDetails extends AxisFault implements Serializable {

   private String errorCode;
   private String extendedDetails;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(FaultDetails.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "FaultDetails"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("errorCode");
      elemField.setXmlName(new QName("", "ErrorCode"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("extendedDetails");
      elemField.setXmlName(new QName("", "ExtendedDetails"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public FaultDetails() {}

   public FaultDetails(String errorCode, String extendedDetails) {
      this.errorCode = errorCode;
      this.extendedDetails = extendedDetails;
   }

   public String getErrorCode() {
      return this.errorCode;
   }

   public void setErrorCode(String errorCode) {
      this.errorCode = errorCode;
   }

   public String getExtendedDetails() {
      return this.extendedDetails;
   }

   public void setExtendedDetails(String extendedDetails) {
      this.extendedDetails = extendedDetails;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof FaultDetails)) {
         return false;
      } else {
         FaultDetails other = (FaultDetails)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.errorCode == null && other.getErrorCode() == null || this.errorCode != null && this.errorCode.equals(other.getErrorCode())) && (this.extendedDetails == null && other.getExtendedDetails() == null || this.extendedDetails != null && this.extendedDetails.equals(other.getExtendedDetails()));
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
         if(this.getErrorCode() != null) {
            _hashCode += this.getErrorCode().hashCode();
         }

         if(this.getExtendedDetails() != null) {
            _hashCode += this.getExtendedDetails().hashCode();
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

   public void writeDetails(QName qname, SerializationContext context) throws IOException {
      context.serialize(qname, (Attributes)null, this);
   }
}
