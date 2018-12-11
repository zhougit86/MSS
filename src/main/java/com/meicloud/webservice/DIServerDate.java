package com.meicloud.webservice;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class DIServerDate implements Serializable {

   private int date;
   private int nanoSeconds;
   private int seconds;
   private int minutes;
   private int hours;
   private int month;
   private int year;
   private int UTCTime;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(DIServerDate.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "DIServerDate"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("date");
      elemField.setXmlName(new QName("", "Date"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("nanoSeconds");
      elemField.setXmlName(new QName("", "NanoSeconds"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("seconds");
      elemField.setXmlName(new QName("", "Seconds"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("minutes");
      elemField.setXmlName(new QName("", "Minutes"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("hours");
      elemField.setXmlName(new QName("", "Hours"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("month");
      elemField.setXmlName(new QName("", "Month"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("year");
      elemField.setXmlName(new QName("", "Year"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("UTCTime");
      elemField.setXmlName(new QName("", "UTCTime"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public DIServerDate() {}

   public DIServerDate(int date, int nanoSeconds, int seconds, int minutes, int hours, int month, int year, int UTCTime) {
      this.date = date;
      this.nanoSeconds = nanoSeconds;
      this.seconds = seconds;
      this.minutes = minutes;
      this.hours = hours;
      this.month = month;
      this.year = year;
      this.UTCTime = UTCTime;
   }

   public int getDate() {
      return this.date;
   }

   public void setDate(int date) {
      this.date = date;
   }

   public int getNanoSeconds() {
      return this.nanoSeconds;
   }

   public void setNanoSeconds(int nanoSeconds) {
      this.nanoSeconds = nanoSeconds;
   }

   public int getSeconds() {
      return this.seconds;
   }

   public void setSeconds(int seconds) {
      this.seconds = seconds;
   }

   public int getMinutes() {
      return this.minutes;
   }

   public void setMinutes(int minutes) {
      this.minutes = minutes;
   }

   public int getHours() {
      return this.hours;
   }

   public void setHours(int hours) {
      this.hours = hours;
   }

   public int getMonth() {
      return this.month;
   }

   public void setMonth(int month) {
      this.month = month;
   }

   public int getYear() {
      return this.year;
   }

   public void setYear(int year) {
      this.year = year;
   }

   public int getUTCTime() {
      return this.UTCTime;
   }

   public void setUTCTime(int UTCTime) {
      this.UTCTime = UTCTime;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof DIServerDate)) {
         return false;
      } else {
         DIServerDate other = (DIServerDate)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = this.date == other.getDate() && this.nanoSeconds == other.getNanoSeconds() && this.seconds == other.getSeconds() && this.minutes == other.getMinutes() && this.hours == other.getHours() && this.month == other.getMonth() && this.year == other.getYear() && this.UTCTime == other.getUTCTime();
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
         int _hashCode1 = _hashCode + this.getDate();
         _hashCode1 += this.getNanoSeconds();
         _hashCode1 += this.getSeconds();
         _hashCode1 += this.getMinutes();
         _hashCode1 += this.getHours();
         _hashCode1 += this.getMonth();
         _hashCode1 += this.getYear();
         _hashCode1 += this.getUTCTime();
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
