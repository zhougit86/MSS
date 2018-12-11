package com.meicloud.webservice;

import com.meicloud.webservice.TaskDetails;
import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class LinkDetails implements Serializable {

   private String folderName;
   private String workflowName;
   private String workletInstanceName;
   private TaskDetails fromTaskInstanceDetails;
   private TaskDetails toTaskInstanceDetails;
   private int workflowRunId;
   private int workletRunId;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(LinkDetails.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "LinkDetails"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("folderName");
      elemField.setXmlName(new QName("", "FolderName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("workflowName");
      elemField.setXmlName(new QName("", "WorkflowName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("workletInstanceName");
      elemField.setXmlName(new QName("", "WorkletInstanceName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("fromTaskInstanceDetails");
      elemField.setXmlName(new QName("", "FromTaskInstanceDetails"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "TaskDetails"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("toTaskInstanceDetails");
      elemField.setXmlName(new QName("", "ToTaskInstanceDetails"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "TaskDetails"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("workflowRunId");
      elemField.setXmlName(new QName("", "WorkflowRunId"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("workletRunId");
      elemField.setXmlName(new QName("", "WorkletRunId"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public LinkDetails() {}

   public LinkDetails(String folderName, String workflowName, String workletInstanceName, TaskDetails fromTaskInstanceDetails, TaskDetails toTaskInstanceDetails, int workflowRunId, int workletRunId) {
      this.folderName = folderName;
      this.workflowName = workflowName;
      this.workletInstanceName = workletInstanceName;
      this.fromTaskInstanceDetails = fromTaskInstanceDetails;
      this.toTaskInstanceDetails = toTaskInstanceDetails;
      this.workflowRunId = workflowRunId;
      this.workletRunId = workletRunId;
   }

   public String getFolderName() {
      return this.folderName;
   }

   public void setFolderName(String folderName) {
      this.folderName = folderName;
   }

   public String getWorkflowName() {
      return this.workflowName;
   }

   public void setWorkflowName(String workflowName) {
      this.workflowName = workflowName;
   }

   public String getWorkletInstanceName() {
      return this.workletInstanceName;
   }

   public void setWorkletInstanceName(String workletInstanceName) {
      this.workletInstanceName = workletInstanceName;
   }

   public TaskDetails getFromTaskInstanceDetails() {
      return this.fromTaskInstanceDetails;
   }

   public void setFromTaskInstanceDetails(TaskDetails fromTaskInstanceDetails) {
      this.fromTaskInstanceDetails = fromTaskInstanceDetails;
   }

   public TaskDetails getToTaskInstanceDetails() {
      return this.toTaskInstanceDetails;
   }

   public void setToTaskInstanceDetails(TaskDetails toTaskInstanceDetails) {
      this.toTaskInstanceDetails = toTaskInstanceDetails;
   }

   public int getWorkflowRunId() {
      return this.workflowRunId;
   }

   public void setWorkflowRunId(int workflowRunId) {
      this.workflowRunId = workflowRunId;
   }

   public int getWorkletRunId() {
      return this.workletRunId;
   }

   public void setWorkletRunId(int workletRunId) {
      this.workletRunId = workletRunId;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof LinkDetails)) {
         return false;
      } else {
         LinkDetails other = (LinkDetails)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.folderName == null && other.getFolderName() == null || this.folderName != null && this.folderName.equals(other.getFolderName())) && (this.workflowName == null && other.getWorkflowName() == null || this.workflowName != null && this.workflowName.equals(other.getWorkflowName())) && (this.workletInstanceName == null && other.getWorkletInstanceName() == null || this.workletInstanceName != null && this.workletInstanceName.equals(other.getWorkletInstanceName())) && (this.fromTaskInstanceDetails == null && other.getFromTaskInstanceDetails() == null || this.fromTaskInstanceDetails != null && this.fromTaskInstanceDetails.equals(other.getFromTaskInstanceDetails())) && (this.toTaskInstanceDetails == null && other.getToTaskInstanceDetails() == null || this.toTaskInstanceDetails != null && this.toTaskInstanceDetails.equals(other.getToTaskInstanceDetails())) && this.workflowRunId == other.getWorkflowRunId() && this.workletRunId == other.getWorkletRunId();
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
         if(this.getFolderName() != null) {
            _hashCode += this.getFolderName().hashCode();
         }

         if(this.getWorkflowName() != null) {
            _hashCode += this.getWorkflowName().hashCode();
         }

         if(this.getWorkletInstanceName() != null) {
            _hashCode += this.getWorkletInstanceName().hashCode();
         }

         if(this.getFromTaskInstanceDetails() != null) {
            _hashCode += this.getFromTaskInstanceDetails().hashCode();
         }

         if(this.getToTaskInstanceDetails() != null) {
            _hashCode += this.getToTaskInstanceDetails().hashCode();
         }

         _hashCode += this.getWorkflowRunId();
         _hashCode += this.getWorkletRunId();
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
