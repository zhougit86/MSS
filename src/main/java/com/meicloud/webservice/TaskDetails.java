package com.meicloud.webservice;

import com.meicloud.webservice.Attribute;
import com.meicloud.webservice.DIServerDate;
import com.meicloud.webservice.DIServiceInfo;
import com.meicloud.webservice.ETaskRunStatus;
import com.meicloud.webservice.ETaskType;
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

public class TaskDetails implements Serializable {

   private DIServiceInfo DIServiceInfo;
   private String folderName;
   private String workflowName;
   private int workflowRunId;
   private String workflowRunInstanceName;
   private ETaskRunStatus taskRunStatus;
   private ETaskType taskType;
   private int runErrorCode;
   private String runErrorMessage;
   private DIServerDate startTime;
   private DIServerDate endTime;
   private String workletInstanceName;
   private Attribute[] attribute;
   private int childRunId;
   private String instanceName;
   private boolean isSessionTask;
   private int workletRunId;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(TaskDetails.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "TaskDetails"));
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
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("workflowRunInstanceName");
      elemField.setXmlName(new QName("", "WorkflowRunInstanceName"));
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
      elemField.setFieldName("taskType");
      elemField.setXmlName(new QName("", "TaskType"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "ETaskType"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("runErrorCode");
      elemField.setXmlName(new QName("", "RunErrorCode"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("runErrorMessage");
      elemField.setXmlName(new QName("", "RunErrorMessage"));
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
      elemField.setFieldName("workletInstanceName");
      elemField.setXmlName(new QName("", "WorkletInstanceName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("attribute");
      elemField.setXmlName(new QName("", "Attribute"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "Attribute"));
      elemField.setMinOccurs(0);
      elemField.setNillable(false);
      elemField.setMaxOccursUnbounded(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("childRunId");
      elemField.setXmlName(new QName("", "ChildRunId"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("instanceName");
      elemField.setXmlName(new QName("", "InstanceName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("isSessionTask");
      elemField.setXmlName(new QName("", "IsSessionTask"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "boolean"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("workletRunId");
      elemField.setXmlName(new QName("", "WorkletRunId"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public TaskDetails() {}

   public TaskDetails(DIServiceInfo DIServiceInfo, String folderName, String workflowName, int workflowRunId, String workflowRunInstanceName, ETaskRunStatus taskRunStatus, ETaskType taskType, int runErrorCode, String runErrorMessage, DIServerDate startTime, DIServerDate endTime, String workletInstanceName, Attribute[] attribute, int childRunId, String instanceName, boolean isSessionTask, int workletRunId) {
      this.DIServiceInfo = DIServiceInfo;
      this.folderName = folderName;
      this.workflowName = workflowName;
      this.workflowRunId = workflowRunId;
      this.workflowRunInstanceName = workflowRunInstanceName;
      this.taskRunStatus = taskRunStatus;
      this.taskType = taskType;
      this.runErrorCode = runErrorCode;
      this.runErrorMessage = runErrorMessage;
      this.startTime = startTime;
      this.endTime = endTime;
      this.workletInstanceName = workletInstanceName;
      this.attribute = attribute;
      this.childRunId = childRunId;
      this.instanceName = instanceName;
      this.isSessionTask = isSessionTask;
      this.workletRunId = workletRunId;
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

   public int getWorkflowRunId() {
      return this.workflowRunId;
   }

   public void setWorkflowRunId(int workflowRunId) {
      this.workflowRunId = workflowRunId;
   }

   public String getWorkflowRunInstanceName() {
      return this.workflowRunInstanceName;
   }

   public void setWorkflowRunInstanceName(String workflowRunInstanceName) {
      this.workflowRunInstanceName = workflowRunInstanceName;
   }

   public ETaskRunStatus getTaskRunStatus() {
      return this.taskRunStatus;
   }

   public void setTaskRunStatus(ETaskRunStatus taskRunStatus) {
      this.taskRunStatus = taskRunStatus;
   }

   public ETaskType getTaskType() {
      return this.taskType;
   }

   public void setTaskType(ETaskType taskType) {
      this.taskType = taskType;
   }

   public int getRunErrorCode() {
      return this.runErrorCode;
   }

   public void setRunErrorCode(int runErrorCode) {
      this.runErrorCode = runErrorCode;
   }

   public String getRunErrorMessage() {
      return this.runErrorMessage;
   }

   public void setRunErrorMessage(String runErrorMessage) {
      this.runErrorMessage = runErrorMessage;
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

   public String getWorkletInstanceName() {
      return this.workletInstanceName;
   }

   public void setWorkletInstanceName(String workletInstanceName) {
      this.workletInstanceName = workletInstanceName;
   }

   public Attribute[] getAttribute() {
      return this.attribute;
   }

   public void setAttribute(Attribute[] attribute) {
      this.attribute = attribute;
   }

   public Attribute getAttribute(int i) {
      return this.attribute[i];
   }

   public void setAttribute(int i, Attribute _value) {
      this.attribute[i] = _value;
   }

   public int getChildRunId() {
      return this.childRunId;
   }

   public void setChildRunId(int childRunId) {
      this.childRunId = childRunId;
   }

   public String getInstanceName() {
      return this.instanceName;
   }

   public void setInstanceName(String instanceName) {
      this.instanceName = instanceName;
   }

   public boolean isIsSessionTask() {
      return this.isSessionTask;
   }

   public void setIsSessionTask(boolean isSessionTask) {
      this.isSessionTask = isSessionTask;
   }

   public int getWorkletRunId() {
      return this.workletRunId;
   }

   public void setWorkletRunId(int workletRunId) {
      this.workletRunId = workletRunId;
   }

   public synchronized boolean equals(Object obj) {
      if(!(obj instanceof TaskDetails)) {
         return false;
      } else {
         TaskDetails other = (TaskDetails)obj;
         if(obj == null) {
            return false;
         } else if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.DIServiceInfo == null && other.getDIServiceInfo() == null || this.DIServiceInfo != null && this.DIServiceInfo.equals(other.getDIServiceInfo())) && (this.folderName == null && other.getFolderName() == null || this.folderName != null && this.folderName.equals(other.getFolderName())) && (this.workflowName == null && other.getWorkflowName() == null || this.workflowName != null && this.workflowName.equals(other.getWorkflowName())) && this.workflowRunId == other.getWorkflowRunId() && (this.workflowRunInstanceName == null && other.getWorkflowRunInstanceName() == null || this.workflowRunInstanceName != null && this.workflowRunInstanceName.equals(other.getWorkflowRunInstanceName())) && (this.taskRunStatus == null && other.getTaskRunStatus() == null || this.taskRunStatus != null && this.taskRunStatus.equals(other.getTaskRunStatus())) && (this.taskType == null && other.getTaskType() == null || this.taskType != null && this.taskType.equals(other.getTaskType())) && this.runErrorCode == other.getRunErrorCode() && (this.runErrorMessage == null && other.getRunErrorMessage() == null || this.runErrorMessage != null && this.runErrorMessage.equals(other.getRunErrorMessage())) && (this.startTime == null && other.getStartTime() == null || this.startTime != null && this.startTime.equals(other.getStartTime())) && (this.endTime == null && other.getEndTime() == null || this.endTime != null && this.endTime.equals(other.getEndTime())) && (this.workletInstanceName == null && other.getWorkletInstanceName() == null || this.workletInstanceName != null && this.workletInstanceName.equals(other.getWorkletInstanceName())) && (this.attribute == null && other.getAttribute() == null || this.attribute != null && Arrays.equals(this.attribute, other.getAttribute())) && this.childRunId == other.getChildRunId() && (this.instanceName == null && other.getInstanceName() == null || this.instanceName != null && this.instanceName.equals(other.getInstanceName())) && this.isSessionTask == other.isIsSessionTask() && this.workletRunId == other.getWorkletRunId();
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

         _hashCode += this.getWorkflowRunId();
         if(this.getWorkflowRunInstanceName() != null) {
            _hashCode += this.getWorkflowRunInstanceName().hashCode();
         }

         if(this.getTaskRunStatus() != null) {
            _hashCode += this.getTaskRunStatus().hashCode();
         }

         if(this.getTaskType() != null) {
            _hashCode += this.getTaskType().hashCode();
         }

         _hashCode += this.getRunErrorCode();
         if(this.getRunErrorMessage() != null) {
            _hashCode += this.getRunErrorMessage().hashCode();
         }

         if(this.getStartTime() != null) {
            _hashCode += this.getStartTime().hashCode();
         }

         if(this.getEndTime() != null) {
            _hashCode += this.getEndTime().hashCode();
         }

         if(this.getWorkletInstanceName() != null) {
            _hashCode += this.getWorkletInstanceName().hashCode();
         }

         if(this.getAttribute() != null) {
            for(int i = 0; i < Array.getLength(this.getAttribute()); ++i) {
               Object obj = Array.get(this.getAttribute(), i);
               if(obj != null && !obj.getClass().isArray()) {
                  _hashCode += obj.hashCode();
               }
            }
         }

         _hashCode += this.getChildRunId();
         if(this.getInstanceName() != null) {
            _hashCode += this.getInstanceName().hashCode();
         }

         _hashCode += (this.isIsSessionTask()?Boolean.TRUE:Boolean.FALSE).hashCode();
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
