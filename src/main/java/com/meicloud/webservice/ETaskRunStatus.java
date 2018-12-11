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

public class ETaskRunStatus implements Serializable {

   private Token _value_;
   private static HashMap _table_ = new HashMap();
   public static final Token _SUCCEEDED = new Token("SUCCEEDED");
   public static final Token _DISABLED = new Token("DISABLED");
   public static final Token _FAILED = new Token("FAILED");
   public static final Token _STOPPED = new Token("STOPPED");
   public static final Token _ABORTED = new Token("ABORTED");
   public static final Token _RUNNING = new Token("RUNNING");
   public static final Token _SUSPENDING = new Token("SUSPENDING");
   public static final Token _SUSPENDED = new Token("SUSPENDED");
   public static final Token _STOPPING = new Token("STOPPING");
   public static final Token _ABORTING = new Token("ABORTING");
   public static final Token _WAITING = new Token("WAITING");
   public static final Token _UNKNOWN = new Token("UNKNOWN");
   public static final Token _TERMINATED = new Token("TERMINATED");
   public static final ETaskRunStatus SUCCEEDED = new ETaskRunStatus(_SUCCEEDED);
   public static final ETaskRunStatus DISABLED = new ETaskRunStatus(_DISABLED);
   public static final ETaskRunStatus FAILED = new ETaskRunStatus(_FAILED);
   public static final ETaskRunStatus STOPPED = new ETaskRunStatus(_STOPPED);
   public static final ETaskRunStatus ABORTED = new ETaskRunStatus(_ABORTED);
   public static final ETaskRunStatus RUNNING = new ETaskRunStatus(_RUNNING);
   public static final ETaskRunStatus SUSPENDING = new ETaskRunStatus(_SUSPENDING);
   public static final ETaskRunStatus SUSPENDED = new ETaskRunStatus(_SUSPENDED);
   public static final ETaskRunStatus STOPPING = new ETaskRunStatus(_STOPPING);
   public static final ETaskRunStatus ABORTING = new ETaskRunStatus(_ABORTING);
   public static final ETaskRunStatus WAITING = new ETaskRunStatus(_WAITING);
   public static final ETaskRunStatus UNKNOWN = new ETaskRunStatus(_UNKNOWN);
   public static final ETaskRunStatus TERMINATED = new ETaskRunStatus(_TERMINATED);
   private static TypeDesc typeDesc = new TypeDesc(ETaskRunStatus.class);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "ETaskRunStatus"));
   }

   protected ETaskRunStatus(Token value) {
      this._value_ = value;
      _table_.put(this._value_, this);
   }

   public Token getValue() {
      return this._value_;
   }

   public static ETaskRunStatus fromValue(Token value) throws IllegalArgumentException {
      ETaskRunStatus enumeration = (ETaskRunStatus)_table_.get(value);
      if(enumeration == null) {
         throw new IllegalArgumentException();
      } else {
         return enumeration;
      }
   }

   public static ETaskRunStatus fromString(String value) throws IllegalArgumentException {
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
