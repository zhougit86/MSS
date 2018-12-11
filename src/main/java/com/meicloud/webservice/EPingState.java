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

public class EPingState implements Serializable {

   private Token _value_;
   private static HashMap _table_ = new HashMap();
   public static final Token _ALIVE = new Token("ALIVE");
   public static final Token _FAIL = new Token("FAIL");
   public static final EPingState ALIVE = new EPingState(_ALIVE);
   public static final EPingState FAIL = new EPingState(_FAIL);
   private static TypeDesc typeDesc = new TypeDesc(EPingState.class);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "EPingState"));
   }

   protected EPingState(Token value) {
      this._value_ = value;
      _table_.put(this._value_, this);
   }

   public Token getValue() {
      return this._value_;
   }

   public static EPingState fromValue(Token value) throws IllegalArgumentException {
      EPingState enumeration = (EPingState)_table_.get(value);
      if(enumeration == null) {
         throw new IllegalArgumentException();
      } else {
         return enumeration;
      }
   }

   public static EPingState fromString(String value) throws IllegalArgumentException {
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
