package com.meicloud.webservice;

import com.meicloud.webservice.ETaskRunStatus;
import com.meicloud.webservice.PerformanceCounter;
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

public class SessionPerformanceData implements Serializable {

   private String folderName;
   private String workflowName;
   private String instanceName;
   private ETaskRunStatus taskRunStatus;
   private int workflowRunId;
   private int workletRunId;
   private String workflowRunInstanceName;
   private PerformanceCounter[] performanceCounter;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(SessionPerformanceData.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "SessionPerformanceData"));
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
      elemField.setFieldName("instanceName");
      elemField.setXmlName(new QName("", "InstanceName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("taskRunStatus");
      elemField.setXmlName(new QName("", "TaskRunStatus"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "ETaskRunStatus"));
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
      elemField = new ElementDesc();
      elemField.setFieldName("workflowRunInstanceName");
      elemField.setXmlName(new QName("", "WorkflowRunInstanceName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("performanceCounter");
      elemField.setXmlName(new QName("", "PerformanceCounter"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "PerformanceCounter"));
      elemField.setMinOccurs(0);
      elemField.setNillable(false);
      elemField.setMaxOccursUnbounded(true);
      typeDesc.addFieldDesc(elemField);
   }

   public SessionPerformanceData() {}

   public SessionPerformanceData(String folderName, String workflowName, String instanceName, ETaskRunStatus taskRunStatus, int workflowRunId, int workletRunId, String workflowRunInstanceName, PerformanceCounter[] performanceCounter) {
      this.folderName = folderName;
      this.workflowName = workflowName;
      this.instanceName = instanceName;
      this.taskRunStatus = taskRunStatus;
      this.workflowRunId = workflowRunId;
      this.workletRunId = workletRunId;
      this.workflowRunInstanceName = workflowRunInstanceName;
      this.performanceCounter = performanceCounter;
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

   public String getInstanceName() {
      return this.instanceName;
   }

   public void setInstanceName(String instanceName) {
      this.instanceName = instanceName;
   }

   public ETaskRunStatus getTaskRunStatus() {
      return this.taskRunStatus;
   }

   public void setTaskRunStatus(ETaskRunStatus taskRunStatus) {
      this.taskRunStatus = taskRunStatus;
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

   public String getWorkflowRunInstanceName() {
      return this.workflowRunInstanceName;
   }

   public void setWorkflowRunInstanceName(String workflowRunInstanceName) {
      this.workflowRunInstanceName = workflowRunInstanceName;
   }

   public PerformanceCounter[] getPerformanceCounter() {
      return this.performanceCounter;
   }

   public void setPerformanceCounter(PerformanceCounter[] performanceCounter) {
      this.performanceCounter = performanceCounter;
   }

   public PerformanceCounter getPerformanceCounter(int i) {
      return this.performanceCounter[i];
   }

   public void setPerformanceCounter(int i, PerformanceCounter _value) {
      this.performanceCounter[i] = _value;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof SessionPerformanceData)) {
         return false;
      } else {
         SessionPerformanceData other = (SessionPerformanceData)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.folderName == null && other.getFolderName() == null || this.folderName != null && this.folderName.equals(other.getFolderName())) && (this.workflowName == null && other.getWorkflowName() == null || this.workflowName != null && this.workflowName.equals(other.getWorkflowName())) && (this.instanceName == null && other.getInstanceName() == null || this.instanceName != null && this.instanceName.equals(other.getInstanceName())) && (this.taskRunStatus == null && other.getTaskRunStatus() == null || this.taskRunStatus != null && this.taskRunStatus.equals(other.getTaskRunStatus())) && this.workflowRunId == other.getWorkflowRunId() && this.workletRunId == other.getWorkletRunId() && (this.workflowRunInstanceName == null && other.getWorkflowRunInstanceName() == null || this.workflowRunInstanceName != null && this.workflowRunInstanceName.equals(other.getWorkflowRunInstanceName())) && (this.performanceCounter == null && other.getPerformanceCounter() == null || this.performanceCounter != null && Arrays.equals(this.performanceCounter, other.getPerformanceCounter()));
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

         if(this.getInstanceName() != null) {
            _hashCode += this.getInstanceName().hashCode();
         }

         if(this.getTaskRunStatus() != null) {
            _hashCode += this.getTaskRunStatus().hashCode();
         }

         _hashCode += this.getWorkflowRunId();
         _hashCode += this.getWorkletRunId();
         if(this.getWorkflowRunInstanceName() != null) {
            _hashCode += this.getWorkflowRunInstanceName().hashCode();
         }

         if(this.getPerformanceCounter() != null) {
            for(int i = 0; i < Array.getLength(this.getPerformanceCounter()); ++i) {
               Object obj = Array.get(this.getPerformanceCounter(), i);
               if(obj != null && !obj.getClass().isArray()) {
                  _hashCode += obj.hashCode();
               }
            }
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
