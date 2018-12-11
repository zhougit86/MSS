package com.meicloud.webservice;

import com.meicloud.webservice.DIServiceInfo;
import com.meicloud.webservice.ETaskRunStatus;
import com.meicloud.webservice.TableStatistics;
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

public class SessionStatistics implements Serializable {

   private DIServiceInfo DIServiceInfo;
   private String folderName;
   private String workflowName;
   private int workflowRunId;
   private String workflowRunInstanceName;
   private String instanceName;
   private String mappingName;
   private ETaskRunStatus taskRunStatus;
   private int workletRunId;
   private int logFileCodePage;
   private int numSrcSuccessRows;
   private int numTgtSuccessRows;
   private int numSrcFailedRows;
   private int numTgtFailedRows;
   private int numTransErrors;
   private int numTables;
   private String logFileName;
   private int firstErrorCode;
   private String firstErrorMessage;
   private TableStatistics[] tableStatistics;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(SessionStatistics.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "SessionStatistics"));
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
      elemField.setFieldName("instanceName");
      elemField.setXmlName(new QName("", "InstanceName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("mappingName");
      elemField.setXmlName(new QName("", "MappingName"));
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
      elemField.setFieldName("workletRunId");
      elemField.setXmlName(new QName("", "WorkletRunId"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("logFileCodePage");
      elemField.setXmlName(new QName("", "LogFileCodePage"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("numSrcSuccessRows");
      elemField.setXmlName(new QName("", "NumSrcSuccessRows"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("numTgtSuccessRows");
      elemField.setXmlName(new QName("", "NumTgtSuccessRows"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("numSrcFailedRows");
      elemField.setXmlName(new QName("", "NumSrcFailedRows"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("numTgtFailedRows");
      elemField.setXmlName(new QName("", "NumTgtFailedRows"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("numTransErrors");
      elemField.setXmlName(new QName("", "NumTransErrors"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("numTables");
      elemField.setXmlName(new QName("", "NumTables"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("logFileName");
      elemField.setXmlName(new QName("", "LogFileName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("firstErrorCode");
      elemField.setXmlName(new QName("", "FirstErrorCode"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("firstErrorMessage");
      elemField.setXmlName(new QName("", "FirstErrorMessage"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("tableStatistics");
      elemField.setXmlName(new QName("", "TableStatistics"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "TableStatistics"));
      elemField.setMinOccurs(0);
      elemField.setNillable(false);
      elemField.setMaxOccursUnbounded(true);
      typeDesc.addFieldDesc(elemField);
   }

   public SessionStatistics() {}

   public SessionStatistics(DIServiceInfo DIServiceInfo, String folderName, String workflowName, int workflowRunId, String workflowRunInstanceName, String instanceName, String mappingName, ETaskRunStatus taskRunStatus, int workletRunId, int logFileCodePage, int numSrcSuccessRows, int numTgtSuccessRows, int numSrcFailedRows, int numTgtFailedRows, int numTransErrors, int numTables, String logFileName, int firstErrorCode, String firstErrorMessage, TableStatistics[] tableStatistics) {
      this.DIServiceInfo = DIServiceInfo;
      this.folderName = folderName;
      this.workflowName = workflowName;
      this.workflowRunId = workflowRunId;
      this.workflowRunInstanceName = workflowRunInstanceName;
      this.instanceName = instanceName;
      this.mappingName = mappingName;
      this.taskRunStatus = taskRunStatus;
      this.workletRunId = workletRunId;
      this.logFileCodePage = logFileCodePage;
      this.numSrcSuccessRows = numSrcSuccessRows;
      this.numTgtSuccessRows = numTgtSuccessRows;
      this.numSrcFailedRows = numSrcFailedRows;
      this.numTgtFailedRows = numTgtFailedRows;
      this.numTransErrors = numTransErrors;
      this.numTables = numTables;
      this.logFileName = logFileName;
      this.firstErrorCode = firstErrorCode;
      this.firstErrorMessage = firstErrorMessage;
      this.tableStatistics = tableStatistics;
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

   public String getInstanceName() {
      return this.instanceName;
   }

   public void setInstanceName(String instanceName) {
      this.instanceName = instanceName;
   }

   public String getMappingName() {
      return this.mappingName;
   }

   public void setMappingName(String mappingName) {
      this.mappingName = mappingName;
   }

   public ETaskRunStatus getTaskRunStatus() {
      return this.taskRunStatus;
   }

   public void setTaskRunStatus(ETaskRunStatus taskRunStatus) {
      this.taskRunStatus = taskRunStatus;
   }

   public int getWorkletRunId() {
      return this.workletRunId;
   }

   public void setWorkletRunId(int workletRunId) {
      this.workletRunId = workletRunId;
   }

   public int getLogFileCodePage() {
      return this.logFileCodePage;
   }

   public void setLogFileCodePage(int logFileCodePage) {
      this.logFileCodePage = logFileCodePage;
   }

   public int getNumSrcSuccessRows() {
      return this.numSrcSuccessRows;
   }

   public void setNumSrcSuccessRows(int numSrcSuccessRows) {
      this.numSrcSuccessRows = numSrcSuccessRows;
   }

   public int getNumTgtSuccessRows() {
      return this.numTgtSuccessRows;
   }

   public void setNumTgtSuccessRows(int numTgtSuccessRows) {
      this.numTgtSuccessRows = numTgtSuccessRows;
   }

   public int getNumSrcFailedRows() {
      return this.numSrcFailedRows;
   }

   public void setNumSrcFailedRows(int numSrcFailedRows) {
      this.numSrcFailedRows = numSrcFailedRows;
   }

   public int getNumTgtFailedRows() {
      return this.numTgtFailedRows;
   }

   public void setNumTgtFailedRows(int numTgtFailedRows) {
      this.numTgtFailedRows = numTgtFailedRows;
   }

   public int getNumTransErrors() {
      return this.numTransErrors;
   }

   public void setNumTransErrors(int numTransErrors) {
      this.numTransErrors = numTransErrors;
   }

   public int getNumTables() {
      return this.numTables;
   }

   public void setNumTables(int numTables) {
      this.numTables = numTables;
   }

   public String getLogFileName() {
      return this.logFileName;
   }

   public void setLogFileName(String logFileName) {
      this.logFileName = logFileName;
   }

   public int getFirstErrorCode() {
      return this.firstErrorCode;
   }

   public void setFirstErrorCode(int firstErrorCode) {
      this.firstErrorCode = firstErrorCode;
   }

   public String getFirstErrorMessage() {
      return this.firstErrorMessage;
   }

   public void setFirstErrorMessage(String firstErrorMessage) {
      this.firstErrorMessage = firstErrorMessage;
   }

   public TableStatistics[] getTableStatistics() {
      return this.tableStatistics;
   }

   public void setTableStatistics(TableStatistics[] tableStatistics) {
      this.tableStatistics = tableStatistics;
   }

   public TableStatistics getTableStatistics(int i) {
      return this.tableStatistics[i];
   }

   public void setTableStatistics(int i, TableStatistics _value) {
      this.tableStatistics[i] = _value;
   }

   public synchronized boolean equals(Object obj) {
      if(!(obj instanceof SessionStatistics)) {
         return false;
      } else {
         SessionStatistics other = (SessionStatistics)obj;
         if(obj == null) {
            return false;
         } else if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.DIServiceInfo == null && other.getDIServiceInfo() == null || this.DIServiceInfo != null && this.DIServiceInfo.equals(other.getDIServiceInfo())) && (this.folderName == null && other.getFolderName() == null || this.folderName != null && this.folderName.equals(other.getFolderName())) && (this.workflowName == null && other.getWorkflowName() == null || this.workflowName != null && this.workflowName.equals(other.getWorkflowName())) && this.workflowRunId == other.getWorkflowRunId() && (this.workflowRunInstanceName == null && other.getWorkflowRunInstanceName() == null || this.workflowRunInstanceName != null && this.workflowRunInstanceName.equals(other.getWorkflowRunInstanceName())) && (this.instanceName == null && other.getInstanceName() == null || this.instanceName != null && this.instanceName.equals(other.getInstanceName())) && (this.mappingName == null && other.getMappingName() == null || this.mappingName != null && this.mappingName.equals(other.getMappingName())) && (this.taskRunStatus == null && other.getTaskRunStatus() == null || this.taskRunStatus != null && this.taskRunStatus.equals(other.getTaskRunStatus())) && this.workletRunId == other.getWorkletRunId() && this.logFileCodePage == other.getLogFileCodePage() && this.numSrcSuccessRows == other.getNumSrcSuccessRows() && this.numTgtSuccessRows == other.getNumTgtSuccessRows() && this.numSrcFailedRows == other.getNumSrcFailedRows() && this.numTgtFailedRows == other.getNumTgtFailedRows() && this.numTransErrors == other.getNumTransErrors() && this.numTables == other.getNumTables() && (this.logFileName == null && other.getLogFileName() == null || this.logFileName != null && this.logFileName.equals(other.getLogFileName())) && this.firstErrorCode == other.getFirstErrorCode() && (this.firstErrorMessage == null && other.getFirstErrorMessage() == null || this.firstErrorMessage != null && this.firstErrorMessage.equals(other.getFirstErrorMessage())) && (this.tableStatistics == null && other.getTableStatistics() == null || this.tableStatistics != null && Arrays.equals(this.tableStatistics, other.getTableStatistics()));
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

         if(this.getInstanceName() != null) {
            _hashCode += this.getInstanceName().hashCode();
         }

         if(this.getMappingName() != null) {
            _hashCode += this.getMappingName().hashCode();
         }

         if(this.getTaskRunStatus() != null) {
            _hashCode += this.getTaskRunStatus().hashCode();
         }

         _hashCode += this.getWorkletRunId();
         _hashCode += this.getLogFileCodePage();
         _hashCode += this.getNumSrcSuccessRows();
         _hashCode += this.getNumTgtSuccessRows();
         _hashCode += this.getNumSrcFailedRows();
         _hashCode += this.getNumTgtFailedRows();
         _hashCode += this.getNumTransErrors();
         _hashCode += this.getNumTables();
         if(this.getLogFileName() != null) {
            _hashCode += this.getLogFileName().hashCode();
         }

         _hashCode += this.getFirstErrorCode();
         if(this.getFirstErrorMessage() != null) {
            _hashCode += this.getFirstErrorMessage().hashCode();
         }

         if(this.getTableStatistics() != null) {
            for(int i = 0; i < Array.getLength(this.getTableStatistics()); ++i) {
               Object obj = Array.get(this.getTableStatistics(), i);
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
