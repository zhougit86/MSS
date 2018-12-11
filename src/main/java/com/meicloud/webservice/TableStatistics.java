package com.meicloud.webservice;

import com.meicloud.webservice.DIServerDate;
import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class TableStatistics implements Serializable {

   private String widgetName;
   private String widgetType;
   private String widgetInstanceName;
   private int lastErrorCode;
   private String lastErrorMessage;
   private DIServerDate startTime;
   private DIServerDate endTime;
   private int numAppliedRows;
   private int numAffectedRows;
   private int numRejectedRows;
   private int throughput;
   private String partitionName;
   private String groupName;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(TableStatistics.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "TableStatistics"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("widgetName");
      elemField.setXmlName(new QName("", "WidgetName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("widgetType");
      elemField.setXmlName(new QName("", "WidgetType"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("widgetInstanceName");
      elemField.setXmlName(new QName("", "WidgetInstanceName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("lastErrorCode");
      elemField.setXmlName(new QName("", "LastErrorCode"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("lastErrorMessage");
      elemField.setXmlName(new QName("", "LastErrorMessage"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("startTime");
      elemField.setXmlName(new QName("", "StartTime"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "DIServerDate"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("endTime");
      elemField.setXmlName(new QName("", "EndTime"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "DIServerDate"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("numAppliedRows");
      elemField.setXmlName(new QName("", "NumAppliedRows"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("numAffectedRows");
      elemField.setXmlName(new QName("", "NumAffectedRows"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("numRejectedRows");
      elemField.setXmlName(new QName("", "NumRejectedRows"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("throughput");
      elemField.setXmlName(new QName("", "Throughput"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("partitionName");
      elemField.setXmlName(new QName("", "PartitionName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("groupName");
      elemField.setXmlName(new QName("", "GroupName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
   }

   public TableStatistics() {}

   public TableStatistics(String widgetName, String widgetType, String widgetInstanceName, int lastErrorCode, String lastErrorMessage, DIServerDate startTime, DIServerDate endTime, int numAppliedRows, int numAffectedRows, int numRejectedRows, int throughput, String partitionName, String groupName) {
      this.widgetName = widgetName;
      this.widgetType = widgetType;
      this.widgetInstanceName = widgetInstanceName;
      this.lastErrorCode = lastErrorCode;
      this.lastErrorMessage = lastErrorMessage;
      this.startTime = startTime;
      this.endTime = endTime;
      this.numAppliedRows = numAppliedRows;
      this.numAffectedRows = numAffectedRows;
      this.numRejectedRows = numRejectedRows;
      this.throughput = throughput;
      this.partitionName = partitionName;
      this.groupName = groupName;
   }

   public String getWidgetName() {
      return this.widgetName;
   }

   public void setWidgetName(String widgetName) {
      this.widgetName = widgetName;
   }

   public String getWidgetType() {
      return this.widgetType;
   }

   public void setWidgetType(String widgetType) {
      this.widgetType = widgetType;
   }

   public String getWidgetInstanceName() {
      return this.widgetInstanceName;
   }

   public void setWidgetInstanceName(String widgetInstanceName) {
      this.widgetInstanceName = widgetInstanceName;
   }

   public int getLastErrorCode() {
      return this.lastErrorCode;
   }

   public void setLastErrorCode(int lastErrorCode) {
      this.lastErrorCode = lastErrorCode;
   }

   public String getLastErrorMessage() {
      return this.lastErrorMessage;
   }

   public void setLastErrorMessage(String lastErrorMessage) {
      this.lastErrorMessage = lastErrorMessage;
   }

   public DIServerDate getStartTime() {
      return this.startTime;
   }

   public void setStartTime(DIServerDate startTime) {
      this.startTime = startTime;
   }

   public DIServerDate getEndTime() {
      return this.endTime;
   }

   public void setEndTime(DIServerDate endTime) {
      this.endTime = endTime;
   }

   public int getNumAppliedRows() {
      return this.numAppliedRows;
   }

   public void setNumAppliedRows(int numAppliedRows) {
      this.numAppliedRows = numAppliedRows;
   }

   public int getNumAffectedRows() {
      return this.numAffectedRows;
   }

   public void setNumAffectedRows(int numAffectedRows) {
      this.numAffectedRows = numAffectedRows;
   }

   public int getNumRejectedRows() {
      return this.numRejectedRows;
   }

   public void setNumRejectedRows(int numRejectedRows) {
      this.numRejectedRows = numRejectedRows;
   }

   public int getThroughput() {
      return this.throughput;
   }

   public void setThroughput(int throughput) {
      this.throughput = throughput;
   }

   public String getPartitionName() {
      return this.partitionName;
   }

   public void setPartitionName(String partitionName) {
      this.partitionName = partitionName;
   }

   public String getGroupName() {
      return this.groupName;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }

   public synchronized boolean equals(Object obj) {
      if(!(obj instanceof TableStatistics)) {
         return false;
      } else {
         TableStatistics other = (TableStatistics)obj;
         if(obj == null) {
            return false;
         } else if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.widgetName == null && other.getWidgetName() == null || this.widgetName != null && this.widgetName.equals(other.getWidgetName())) && (this.widgetType == null && other.getWidgetType() == null || this.widgetType != null && this.widgetType.equals(other.getWidgetType())) && (this.widgetInstanceName == null && other.getWidgetInstanceName() == null || this.widgetInstanceName != null && this.widgetInstanceName.equals(other.getWidgetInstanceName())) && this.lastErrorCode == other.getLastErrorCode() && (this.lastErrorMessage == null && other.getLastErrorMessage() == null || this.lastErrorMessage != null && this.lastErrorMessage.equals(other.getLastErrorMessage())) && (this.startTime == null && other.getStartTime() == null || this.startTime != null && this.startTime.equals(other.getStartTime())) && (this.endTime == null && other.getEndTime() == null || this.endTime != null && this.endTime.equals(other.getEndTime())) && this.numAppliedRows == other.getNumAppliedRows() && this.numAffectedRows == other.getNumAffectedRows() && this.numRejectedRows == other.getNumRejectedRows() && this.throughput == other.getThroughput() && (this.partitionName == null && other.getPartitionName() == null || this.partitionName != null && this.partitionName.equals(other.getPartitionName())) && (this.groupName == null && other.getGroupName() == null || this.groupName != null && this.groupName.equals(other.getGroupName()));
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
         if(this.getWidgetName() != null) {
            _hashCode += this.getWidgetName().hashCode();
         }

         if(this.getWidgetType() != null) {
            _hashCode += this.getWidgetType().hashCode();
         }

         if(this.getWidgetInstanceName() != null) {
            _hashCode += this.getWidgetInstanceName().hashCode();
         }

         _hashCode += this.getLastErrorCode();
         if(this.getLastErrorMessage() != null) {
            _hashCode += this.getLastErrorMessage().hashCode();
         }

         if(this.getStartTime() != null) {
            _hashCode += this.getStartTime().hashCode();
         }

         if(this.getEndTime() != null) {
            _hashCode += this.getEndTime().hashCode();
         }

         _hashCode += this.getNumAppliedRows();
         _hashCode += this.getNumAffectedRows();
         _hashCode += this.getNumRejectedRows();
         _hashCode += this.getThroughput();
         if(this.getPartitionName() != null) {
            _hashCode += this.getPartitionName().hashCode();
         }

         if(this.getGroupName() != null) {
            _hashCode += this.getGroupName().hashCode();
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
