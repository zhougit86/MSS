package com.meicloud.webservice;

import com.meicloud.webservice.DIServiceInfo;
import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class TypeGetTaskDetailsExRequest implements Serializable {

   private DIServiceInfo DIServiceInfo;
   private String folderName;
   private String workflowName;
   private String workflowRunInstanceName;
   private String taskInstancePath;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(TypeGetTaskDetailsExRequest.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "TypeGetTaskDetailsExRequest"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("DIServiceInfo");
      elemField.setXmlName(new QName("", "DIServiceInfo"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "DIServiceInfo"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("folderName");
      elemField.setXmlName(new QName("", "FolderName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("workflowName");
      elemField.setXmlName(new QName("", "WorkflowName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("workflowRunInstanceName");
      elemField.setXmlName(new QName("", "WorkflowRunInstanceName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("taskInstancePath");
      elemField.setXmlName(new QName("", "TaskInstancePath"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public TypeGetTaskDetailsExRequest() {}

   public TypeGetTaskDetailsExRequest(DIServiceInfo DIServiceInfo, String folderName, String workflowName, String workflowRunInstanceName, String taskInstancePath) {
      this.DIServiceInfo = DIServiceInfo;
      this.folderName = folderName;
      this.workflowName = workflowName;
      this.workflowRunInstanceName = workflowRunInstanceName;
      this.taskInstancePath = taskInstancePath;
   }

   public DIServiceInfo getDIServiceInfo() {
      return this.DIServiceInfo;
   }

   public void setDIServiceInfo(DIServiceInfo DIServiceInfo) {
      this.DIServiceInfo = DIServiceInfo;
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

   public String getWorkflowRunInstanceName() {
      return this.workflowRunInstanceName;
   }

   public void setWorkflowRunInstanceName(String workflowRunInstanceName) {
      this.workflowRunInstanceName = workflowRunInstanceName;
   }

   public String getTaskInstancePath() {
      return this.taskInstancePath;
   }

   public void setTaskInstancePath(String taskInstancePath) {
      this.taskInstancePath = taskInstancePath;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof TypeGetTaskDetailsExRequest)) {
         return false;
      } else {
         TypeGetTaskDetailsExRequest other = (TypeGetTaskDetailsExRequest)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.DIServiceInfo == null && other.getDIServiceInfo() == null || this.DIServiceInfo != null && this.DIServiceInfo.equals(other.getDIServiceInfo())) && (this.folderName == null && other.getFolderName() == null || this.folderName != null && this.folderName.equals(other.getFolderName())) && (this.workflowName == null && other.getWorkflowName() == null || this.workflowName != null && this.workflowName.equals(other.getWorkflowName())) && (this.workflowRunInstanceName == null && other.getWorkflowRunInstanceName() == null || this.workflowRunInstanceName != null && this.workflowRunInstanceName.equals(other.getWorkflowRunInstanceName())) && (this.taskInstancePath == null && other.getTaskInstancePath() == null || this.taskInstancePath != null && this.taskInstancePath.equals(other.getTaskInstancePath()));
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
         if(this.getDIServiceInfo() != null) {
            _hashCode += this.getDIServiceInfo().hashCode();
         }

         if(this.getFolderName() != null) {
            _hashCode += this.getFolderName().hashCode();
         }

         if(this.getWorkflowName() != null) {
            _hashCode += this.getWorkflowName().hashCode();
         }

         if(this.getWorkflowRunInstanceName() != null) {
            _hashCode += this.getWorkflowRunInstanceName().hashCode();
         }

         if(this.getTaskInstancePath() != null) {
            _hashCode += this.getTaskInstancePath().hashCode();
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
