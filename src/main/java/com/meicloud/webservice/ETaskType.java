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

public class ETaskType implements Serializable {

   private Token _value_;
   private static HashMap _table_ = new HashMap();
   public static final Token _COMMAND_TASK = new Token("COMMAND_TASK");
   public static final Token _DECISION_TASK = new Token("DECISION_TASK");
   public static final Token _EVENTWAIT_TASK = new Token("EVENTWAIT_TASK");
   public static final Token _EVENTRAISE_TASK = new Token("EVENTRAISE_TASK");
   public static final Token _STARTWORKFLOW_TASK = new Token("STARTWORKFLOW_TASK");
   public static final Token _ABORTWORKFLOW_TASK = new Token("ABORTWORKFLOW_TASK");
   public static final Token _STOPWORKFLOW_TASK = new Token("STOPWORKFLOW_TASK");
   public static final Token _EMAIL_TASK = new Token("EMAIL_TASK");
   public static final Token _TIMER_TASK = new Token("TIMER_TASK");
   public static final Token _ASSIGNMENT_TASK = new Token("ASSIGNMENT_TASK");
   public static final Token _SESSION_TASK = new Token("SESSION_TASK");
   public static final Token _WORKLET_TASK = new Token("WORKLET_TASK");
   public static final ETaskType COMMAND_TASK = new ETaskType(_COMMAND_TASK);
   public static final ETaskType DECISION_TASK = new ETaskType(_DECISION_TASK);
   public static final ETaskType EVENTWAIT_TASK = new ETaskType(_EVENTWAIT_TASK);
   public static final ETaskType EVENTRAISE_TASK = new ETaskType(_EVENTRAISE_TASK);
   public static final ETaskType STARTWORKFLOW_TASK = new ETaskType(_STARTWORKFLOW_TASK);
   public static final ETaskType ABORTWORKFLOW_TASK = new ETaskType(_ABORTWORKFLOW_TASK);
   public static final ETaskType STOPWORKFLOW_TASK = new ETaskType(_STOPWORKFLOW_TASK);
   public static final ETaskType EMAIL_TASK = new ETaskType(_EMAIL_TASK);
   public static final ETaskType TIMER_TASK = new ETaskType(_TIMER_TASK);
   public static final ETaskType ASSIGNMENT_TASK = new ETaskType(_ASSIGNMENT_TASK);
   public static final ETaskType SESSION_TASK = new ETaskType(_SESSION_TASK);
   public static final ETaskType WORKLET_TASK = new ETaskType(_WORKLET_TASK);
   private static TypeDesc typeDesc = new TypeDesc(ETaskType.class);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "ETaskType"));
   }

   protected ETaskType(Token value) {
      this._value_ = value;
      _table_.put(this._value_, this);
   }

   public Token getValue() {
      return this._value_;
   }

   public static ETaskType fromValue(Token value) throws IllegalArgumentException {
      ETaskType enumeration = (ETaskType)_table_.get(value);
      if(enumeration == null) {
         throw new IllegalArgumentException();
      } else {
         return enumeration;
      }
   }

   public static ETaskType fromString(String value) throws IllegalArgumentException {
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
