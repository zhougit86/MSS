package com.meicloud.webservice;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.HashMap;
import javax.xml.namespace.QName;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.EnumDeserializer;
import org.apache.axis.encoding.ser.EnumSerializer;
import org.apache.axis.types.Token;

public class EPerformanceCounterType implements Serializable {

   private Token _value_;
   private static HashMap _table_ = new HashMap();
   public static final Token _INTEGER = new Token("INTEGER");
   public static final Token _STRING = new Token("STRING");
   public static final EPerformanceCounterType INTEGER = new EPerformanceCounterType(_INTEGER);
   public static final EPerformanceCounterType STRING = new EPerformanceCounterType(_STRING);
   private static TypeDesc typeDesc = new TypeDesc(EPerformanceCounterType.class);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "EPerformanceCounterType"));
   }

   protected EPerformanceCounterType(Token value) {
      this._value_ = value;
      _table_.put(this._value_, this);
   }

   public Token getValue() {
      return this._value_;
   }

   public static EPerformanceCounterType fromValue(Token value) throws IllegalArgumentException {
      EPerformanceCounterType enumeration = (EPerformanceCounterType)_table_.get(value);
      if(enumeration == null) {
         throw new IllegalArgumentException();
      } else {
         return enumeration;
      }
   }

   public static EPerformanceCounterType fromString(String value) throws IllegalArgumentException {
      try {
         return fromValue(new Token(value));
      } catch (Exception var2) {
         throw new IllegalArgumentException();
      }
   }

   public boolean equals(Object obj) {
      return obj == this;
   }

   public int hashCode() {
      return this.toString().hashCode();
   }

   public String toString() {
      return this._value_.toString();
   }

   public Object readResolve() throws ObjectStreamException {
      return fromValue(this._value_);
   }

   public static Serializer getSerializer(String mechType, Class _javaType, QName _xmlType) {
      return new EnumSerializer(_javaType, _xmlType);
   }

   public static Deserializer getDeserializer(String mechType, Class _javaType, QName _xmlType) {
      return new EnumDeserializer(_javaType, _xmlType);
   }

   public static TypeDesc getTypeDesc() {
      return typeDesc;
   }
}
