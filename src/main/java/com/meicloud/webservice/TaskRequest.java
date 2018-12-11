package com.meicloud.webservice;

import com.meicloud.webservice.DIServiceInfo;
import com.meicloud.webservice.ETaskRunMode;
import com.meicloud.webservice.Parameter;
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

public class TaskRequest implements Serializable {

   private DIServiceInfo DIServiceInfo;
   private String folderName;
   private String workflowName;
   private Integer workflowRunId;
   private String workflowRunInstanceName;
   private String parameterFileName;
   private Parameter[] parameters;
   private ETaskRunMode requestMode;
   private String taskInstancePath;
   private boolean isAbort;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(TaskRequest.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "TaskRequest"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("DIServiceInfo");
      elemField.setXmlName(new QName("", "DIServiceInfo"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "DIServiceInfo"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
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
      elemField.setFieldName("workflowRunId");
      elemField.setXmlName(new QName("", "WorkflowRunId"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("workflowRunInstanceName");
      elemField.setXmlName(new QName("", "WorkflowRunInstanceName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("parameterFileName");
      elemField.setXmlName(new QName("", "ParameterFileName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("parameters");
      elemField.setXmlName(new QName("", "Parameters"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "Parameter"));
      elemField.setNillable(true);
      elemField.setItemQName(new QName("", "Parameters"));
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("requestMode");
      elemField.setXmlName(new QName("", "RequestMode"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "ETaskRunMode"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("taskInstancePath");
      elemField.setXmlName(new QName("", "TaskInstancePath"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("isAbort");
      elemField.setXmlName(new QName("", "IsAbort"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "boolean"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public TaskRequest() {}

   public TaskRequest(DIServiceInfo DIServiceInfo, String folderName, String workflowName, Integer workflowRunId, String workflowRunInstanceName, String parameterFileName, Parameter[] parameters, ETaskRunMode requestMode, String taskInstancePath, boolean isAbort) {
      this.DIServiceInfo = DIServiceInfo;
      this.folderName = folderName;
      this.workflowName = workflowName;
      this.workflowRunId = workflowRunId;
      this.workflowRunInstanceName = workflowRunInstanceName;
      this.parameterFileName = parameterFileName;
      this.parameters = parameters;
      this.requestMode = requestMode;
      this.taskInstancePath = taskInstancePath;
      this.isAbort = isAbort;
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

   public Integer getWorkflowRunId() {
      return this.workflowRunId;
   }

   public void setWorkflowRunId(Integer workflowRunId) {
      this.workflowRunId = workflowRunId;
   }

   public String getWorkflowRunInstanceName() {
      return this.workflowRunInstanceName;
   }

   public void setWorkflowRunInstanceName(String workflowRunInstanceName) {
      this.workflowRunInstanceName = workflowRunInstanceName;
   }

   public String getParameterFileName() {
      return this.parameterFileName;
   }

   public void setParameterFileName(String parameterFileName) {
      this.parameterFileName = parameterFileName;
   }

   public Parameter[] getParameters() {
      return this.parameters;
   }

   public void setParameters(Parameter[] parameters) {
      this.parameters = parameters;
   }

   public ETaskRunMode getRequestMode() {
      return this.requestMode;
   }

   public void setRequestMode(ETaskRunMode requestMode) {
      this.requestMode = requestMode;
   }

   public String getTaskInstancePath() {
      return this.taskInstancePath;
   }

   public void setTaskInstancePath(String taskInstancePath) {
      this.taskInstancePath = taskInstancePath;
   }

   public boolean isIsAbort() {
      return this.isAbort;
   }

   public void setIsAbort(boolean isAbort) {
      this.isAbort = isAbort;
   }

   public synchronized boolean equals(Object obj) {
      if(!(obj instanceof TaskRequest)) {
         return false;
      } else {
         TaskRequest other = (TaskRequest)obj;
         if(obj == null) {
            return false;
         } else if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.DIServiceInfo == null && other.getDIServiceInfo() == null || this.DIServiceInfo != null && this.DIServiceInfo.equals(other.getDIServiceInfo())) && (this.folderName == null && other.getFolderName() == null || this.folderName != null && this.folderName.equals(other.getFolderName())) && (this.workflowName == null && other.getWorkflowName() == null || this.workflowName != null && this.workflowName.equals(other.getWorkflowName())) && (this.workflowRunId == null && other.getWorkflowRunId() == null || this.workflowRunId != null && this.workflowRunId.equals(other.getWorkflowRunId())) && (this.workflowRunInstanceName == null && other.getWorkflowRunInstanceName() == null || this.workflowRunInstanceName != null && this.workflowRunInstanceName.equals(other.getWorkflowRunInstanceName())) && (this.parameterFileName == null && other.getParameterFileName() == null || this.parameterFileName != null && this.parameterFileName.equals(other.getParameterFileName())) && (this.parameters == null && other.getParameters() == null || this.parameters != null && Arrays.equals(this.parameters, other.getParameters())) && (this.requestMode == null && other.getRequestMode() == null || this.requestMode != null && this.requestMode.equals(other.getRequestMode())) && (this.taskInstancePath == null && other.getTaskInstancePath() == null || this.taskInstancePath != null && this.taskInstancePath.equals(other.getTaskInstancePath())) && this.isAbort == other.isIsAbort();
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

         if(this.getWorkflowRunId() != null) {
            _hashCode += this.getWorkflowRunId().hashCode();
         }

         if(this.getWorkflowRunInstanceName() != null) {
            _hashCode += this.getWorkflowRunInstanceName().hashCode();
         }

         if(this.getParameterFileName() != null) {
            _hashCode += this.getParameterFileName().hashCode();
         }

         if(this.getParameters() != null) {
            for(int i = 0; i < Array.getLength(this.getParameters()); ++i) {
               Object obj = Array.get(this.getParameters(), i);
               if(obj != null && !obj.getClass().isArray()) {
                  _hashCode += obj.hashCode();
               }
            }
         }

         if(this.getRequestMode() != null) {
            _hashCode += this.getRequestMode().hashCode();
         }

         if(this.getTaskInstancePath() != null) {
            _hashCode += this.getTaskInstancePath().hashCode();
         }

         _hashCode += (this.isIsAbort()?Boolean.TRUE:Boolean.FALSE).hashCode();
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
