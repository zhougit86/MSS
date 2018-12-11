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

public class EWorkflowRunStatus implements Serializable {

   private Token _value_;
   private static HashMap _table_ = new HashMap();
   public static final Token _SUCCEEDED = new Token("SUCCEEDED");
   public static final Token _FAILED = new Token("FAILED");
   public static final Token _STOPPED = new Token("STOPPED");
   public static final Token _ABORTED = new Token("ABORTED");
   public static final Token _RUNNING = new Token("RUNNING");
   public static final Token _SUSPENDING = new Token("SUSPENDING");
   public static final Token _SUSPENDED = new Token("SUSPENDED");
   public static final Token _STOPPING = new Token("STOPPING");
   public static final Token _ABORTING = new Token("ABORTING");
   public static final Token _WAITING = new Token("WAITING");
   public static final Token _SCHEDULED = new Token("SCHEDULED");
   public static final Token _UNSCHEDULED = new Token("UNSCHEDULED");
   public static final Token _UNKNOWN = new Token("UNKNOWN");
   public static final Token _TERMINATED = new Token("TERMINATED");
   public static final EWorkflowRunStatus SUCCEEDED = new EWorkflowRunStatus(_SUCCEEDED);
   public static final EWorkflowRunStatus FAILED = new EWorkflowRunStatus(_FAILED);
   public static final EWorkflowRunStatus STOPPED = new EWorkflowRunStatus(_STOPPED);
   public static final EWorkflowRunStatus ABORTED = new EWorkflowRunStatus(_ABORTED);
   public static final EWorkflowRunStatus RUNNING = new EWorkflowRunStatus(_RUNNING);
   public static final EWorkflowRunStatus SUSPENDING = new EWorkflowRunStatus(_SUSPENDING);
   public static final EWorkflowRunStatus SUSPENDED = new EWorkflowRunStatus(_SUSPENDED);
   public static final EWorkflowRunStatus STOPPING = new EWorkflowRunStatus(_STOPPING);
   public static final EWorkflowRunStatus ABORTING = new EWorkflowRunStatus(_ABORTING);
   public static final EWorkflowRunStatus WAITING = new EWorkflowRunStatus(_WAITING);
   public static final EWorkflowRunStatus SCHEDULED = new EWorkflowRunStatus(_SCHEDULED);
   public static final EWorkflowRunStatus UNSCHEDULED = new EWorkflowRunStatus(_UNSCHEDULED);
   public static final EWorkflowRunStatus UNKNOWN = new EWorkflowRunStatus(_UNKNOWN);
   public static final EWorkflowRunStatus TERMINATED = new EWorkflowRunStatus(_TERMINATED);
   private static TypeDesc typeDesc = new TypeDesc(EWorkflowRunStatus.class);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "EWorkflowRunStatus"));
   }

   protected EWorkflowRunStatus(Token value) {
      this._value_ = value;
      _table_.put(this._value_, this);
   }

   public Token getValue() {
      return this._value_;
   }

   public static EWorkflowRunStatus fromValue(Token value) throws IllegalArgumentException {
      EWorkflowRunStatus enumeration = (EWorkflowRunStatus)_table_.get(value);
      if(enumeration == null) {
         throw new IllegalArgumentException();
      } else {
         return enumeration;
      }
   }

   public static EWorkflowRunStatus fromString(String value) throws IllegalArgumentException {
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
