package com.meicloud.webservice;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class TaskInstanceInfo implements Serializable {

   private String name;
   private String type;
   private TaskInstanceInfo[] childTask;
   private boolean isValid;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(TaskInstanceInfo.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "TaskInstanceInfo"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("name");
      elemField.setXmlName(new QName("", "Name"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("type");
      elemField.setXmlName(new QName("", "Type"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("childTask");
      elemField.setXmlName(new QName("", "ChildTask"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "TaskInstanceInfo"));
      elemField.setMinOccurs(0);
      elemField.setNillable(false);
      elemField.setMaxOccursUnbounded(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("isValid");
      elemField.setXmlName(new QName("", "IsValid"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "boolean"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public TaskInstanceInfo() {}

   public TaskInstanceInfo(String name, String type, TaskInstanceInfo[] childTask, boolean isValid) {
      this.name = name;
      this.type = type;
      this.childTask = childTask;
      this.isValid = isValid;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public TaskInstanceInfo[] getChildTask() {
      return this.childTask;
   }

   public void setChildTask(TaskInstanceInfo[] childTask) {
      this.childTask = childTask;
   }

   public TaskInstanceInfo getChildTask(int i) {
      return this.childTask[i];
   }

   public void setChildTask(int i, TaskInstanceInfo _value) {
      this.childTask[i] = _value;
   }

   public boolean isIsValid() {
      return this.isValid;
   }

   public void setIsValid(boolean isValid) {
      this.isValid = isValid;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof TaskInstanceInfo)) {
         return false;
      } else {
         TaskInstanceInfo other = (TaskInstanceInfo)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.name == null && other.getName() == null || this.name != null && this.name.equals(other.getName())) && (this.type == null && other.getType() == null || this.type != null && this.type.equals(other.getType())) && (this.childTask == null && other.getChildTask() == null || this.childTask != null && Arrays.equals(this.childTask, other.getChildTask())) && this.isValid == other.isIsValid();
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
         if(this.getName() != null) {
            _hashCode += this.getName().hashCode();
         }

         if(this.getType() != null) {
            _hashCode += this.getType().hashCode();
         }

         if(this.getChildTask() != null) {
            for(int i = 0; i < Array.getLength(this.getChildTask()); ++i) {
               Object obj = Array.get(this.getChildTask(), i);
               if(obj != null && !obj.getClass().isArray()) {
                  _hashCode += obj.hashCode();
               }
            }
         }

         _hashCode += (this.isIsValid()?Boolean.TRUE:Boolean.FALSE).hashCode();
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
