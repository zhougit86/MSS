package com.meicloud.webservice;

import com.meicloud.webservice.Attribute;
import com.meicloud.webservice.DIServerDate;
import com.meicloud.webservice.EWorkflowRunStatus;
import com.meicloud.webservice.EWorkflowRunType;
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

public class WorkflowDetails implements Serializable {

   private String folderName;
   private String workflowName;
   private int workflowRunId;
   private String workflowRunInstanceName;
   private EWorkflowRunStatus workflowRunStatus;
   private EWorkflowRunType workflowRunType;
   private int runErrorCode;
   private String runErrorMessage;
   private DIServerDate startTime;
   private DIServerDate endTime;
   private Attribute[] attribute;
   private String userName;
   private String logFileName;
   private int logFileCodePage;
   private String OSUser;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(WorkflowDetails.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "WorkflowDetails"));
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
      elemField.setFieldName("workflowRunStatus");
      elemField.setXmlName(new QName("", "WorkflowRunStatus"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "EWorkflowRunStatus"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("workflowRunType");
      elemField.setXmlName(new QName("", "WorkflowRunType"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "EWorkflowRunType"));
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
      elemField.setFieldName("attribute");
      elemField.setXmlName(new QName("", "Attribute"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "Attribute"));
      elemField.setMinOccurs(0);
      elemField.setNillable(false);
      elemField.setMaxOccursUnbounded(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("userName");
      elemField.setXmlName(new QName("", "UserName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("logFileName");
      elemField.setXmlName(new QName("", "LogFileName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("logFileCodePage");
      elemField.setXmlName(new QName("", "LogFileCodePage"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("OSUser");
      elemField.setXmlName(new QName("", "OSUser"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
   }

   public WorkflowDetails() {}

   public WorkflowDetails(String folderName, String workflowName, int workflowRunId, String workflowRunInstanceName, EWorkflowRunStatus workflowRunStatus, EWorkflowRunType workflowRunType, int runErrorCode, String runErrorMessage, DIServerDate startTime, DIServerDate endTime, Attribute[] attribute, String userName, String logFileName, int logFileCodePage, String OSUser) {
      this.folderName = folderName;
      this.workflowName = workflowName;
      this.workflowRunId = workflowRunId;
      this.workflowRunInstanceName = workflowRunInstanceName;
      this.workflowRunStatus = workflowRunStatus;
      this.workflowRunType = workflowRunType;
      this.runErrorCode = runErrorCode;
      this.runErrorMessage = runErrorMessage;
      this.startTime = startTime;
      this.endTime = endTime;
      this.attribute = attribute;
      this.userName = userName;
      this.logFileName = logFileName;
      this.logFileCodePage = logFileCodePage;
      this.OSUser = OSUser;
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

   public EWorkflowRunStatus getWorkflowRunStatus() {
      return this.workflowRunStatus;
   }

   public void setWorkflowRunStatus(EWorkflowRunStatus workflowRunStatus) {
      this.workflowRunStatus = workflowRunStatus;
   }

   public EWorkflowRunType getWorkflowRunType() {
      return this.workflowRunType;
   }

   public void setWorkflowRunType(EWorkflowRunType workflowRunType) {
      this.workflowRunType = workflowRunType;
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

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getLogFileName() {
      return this.logFileName;
   }

   public void setLogFileName(String logFileName) {
      this.logFileName = logFileName;
   }

   public int getLogFileCodePage() {
      return this.logFileCodePage;
   }

   public void setLogFileCodePage(int logFileCodePage) {
      this.logFileCodePage = logFileCodePage;
   }

   public String getOSUser() {
      return this.OSUser;
   }

   public void setOSUser(String OSUser) {
      this.OSUser = OSUser;
   }

   public synchronized boolean equals(Object obj) {
      if(!(obj instanceof WorkflowDetails)) {
         return false;
      } else {
         WorkflowDetails other = (WorkflowDetails)obj;
         if(obj == null) {
            return false;
         } else if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.folderName == null && other.getFolderName() == null || this.folderName != null && this.folderName.equals(other.getFolderName())) && (this.workflowName == null && other.getWorkflowName() == null || this.workflowName != null && this.workflowName.equals(other.getWorkflowName())) && this.workflowRunId == other.getWorkflowRunId() && (this.workflowRunInstanceName == null && other.getWorkflowRunInstanceName() == null || this.workflowRunInstanceName != null && this.workflowRunInstanceName.equals(other.getWorkflowRunInstanceName())) && (this.workflowRunStatus == null && other.getWorkflowRunStatus() == null || this.workflowRunStatus != null && this.workflowRunStatus.equals(other.getWorkflowRunStatus())) && (this.workflowRunType == null && other.getWorkflowRunType() == null || this.workflowRunType != null && this.workflowRunType.equals(other.getWorkflowRunType())) && this.runErrorCode == other.getRunErrorCode() && (this.runErrorMessage == null && other.getRunErrorMessage() == null || this.runErrorMessage != null && this.runErrorMessage.equals(other.getRunErrorMessage())) && (this.startTime == null && other.getStartTime() == null || this.startTime != null && this.startTime.equals(other.getStartTime())) && (this.endTime == null && other.getEndTime() == null || this.endTime != null && this.endTime.equals(other.getEndTime())) && (this.attribute == null && other.getAttribute() == null || this.attribute != null && Arrays.equals(this.attribute, other.getAttribute())) && (this.userName == null && other.getUserName() == null || this.userName != null && this.userName.equals(other.getUserName())) && (this.logFileName == null && other.getLogFileName() == null || this.logFileName != null && this.logFileName.equals(other.getLogFileName())) && this.logFileCodePage == other.getLogFileCodePage() && (this.OSUser == null && other.getOSUser() == null || this.OSUser != null && this.OSUser.equals(other.getOSUser()));
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

         _hashCode += this.getWorkflowRunId();
         if(this.getWorkflowRunInstanceName() != null) {
            _hashCode += this.getWorkflowRunInstanceName().hashCode();
         }

         if(this.getWorkflowRunStatus() != null) {
            _hashCode += this.getWorkflowRunStatus().hashCode();
         }

         if(this.getWorkflowRunType() != null) {
            _hashCode += this.getWorkflowRunType().hashCode();
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

         if(this.getAttribute() != null) {
            for(int i = 0; i < Array.getLength(this.getAttribute()); ++i) {
               Object obj = Array.get(this.getAttribute(), i);
               if(obj != null && !obj.getClass().isArray()) {
                  _hashCode += obj.hashCode();
               }
            }
         }

         if(this.getUserName() != null) {
            _hashCode += this.getUserName().hashCode();
         }

         if(this.getLogFileName() != null) {
            _hashCode += this.getLogFileName().hashCode();
         }

         _hashCode += this.getLogFileCodePage();
         if(this.getOSUser() != null) {
            _hashCode += this.getOSUser().hashCode();
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
