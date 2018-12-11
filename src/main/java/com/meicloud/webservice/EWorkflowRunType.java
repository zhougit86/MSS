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

public class EWorkflowRunType implements Serializable {

   private Token _value_;
   private static HashMap _table_ = new HashMap();
   public static final Token _SCHEDULE = new Token("SCHEDULE");
   public static final Token _USER_REQUEST = new Token("USER_REQUEST");
   public static final Token _DEBUG_SESSION = new Token("DEBUG_SESSION");
   public static final Token _SERVER_INIT = new Token("SERVER_INIT");
   public static final EWorkflowRunType SCHEDULE = new EWorkflowRunType(_SCHEDULE);
   public static final EWorkflowRunType USER_REQUEST = new EWorkflowRunType(_USER_REQUEST);
   public static final EWorkflowRunType DEBUG_SESSION = new EWorkflowRunType(_DEBUG_SESSION);
   public static final EWorkflowRunType SERVER_INIT = new EWorkflowRunType(_SERVER_INIT);
   private static TypeDesc typeDesc = new TypeDesc(EWorkflowRunType.class);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "EWorkflowRunType"));
   }

   protected EWorkflowRunType(Token value) {
      this._value_ = value;
      _table_.put(this._value_, this);
   }

   public Token getValue() {
      return this._value_;
   }

   public static EWorkflowRunType fromValue(Token value) throws IllegalArgumentException {
      EWorkflowRunType enumeration = (EWorkflowRunType)_table_.get(value);
      if(enumeration == null) {
         throw new IllegalArgumentException();
      } else {
         return enumeration;
      }
   }

   public static EWorkflowRunType fromString(String value) throws IllegalArgumentException {
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
