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

public class GetWorkflowLogRequest implements Serializable {

   private DIServiceInfo DIServiceInfo;
   private String folderName;
   private String workflowName;
   private Integer workflowRunId;
   private String workflowRunInstanceName;
   private int timeout;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(GetWorkflowLogRequest.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "GetWorkflowLogRequest"));
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
      elemField.setFieldName("timeout");
      elemField.setXmlName(new QName("", "Timeout"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public GetWorkflowLogRequest() {}

   public GetWorkflowLogRequest(DIServiceInfo DIServiceInfo, String folderName, String workflowName, Integer workflowRunId, String workflowRunInstanceName, int timeout) {
      this.DIServiceInfo = DIServiceInfo;
      this.folderName = folderName;
      this.workflowName = workflowName;
      this.workflowRunId = workflowRunId;
      this.workflowRunInstanceName = workflowRunInstanceName;
      this.timeout = timeout;
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

   public int getTimeout() {
      return this.timeout;
   }

   public void setTimeout(int timeout) {
      this.timeout = timeout;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof GetWorkflowLogRequest)) {
         return false;
      } else {
         GetWorkflowLogRequest other = (GetWorkflowLogRequest)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.DIServiceInfo == null && other.getDIServiceInfo() == null || this.DIServiceInfo != null && this.DIServiceInfo.equals(other.getDIServiceInfo())) && (this.folderName == null && other.getFolderName() == null || this.folderName != null && this.folderName.equals(other.getFolderName())) && (this.workflowName == null && other.getWorkflowName() == null || this.workflowName != null && this.workflowName.equals(other.getWorkflowName())) && (this.workflowRunId == null && other.getWorkflowRunId() == null || this.workflowRunId != null && this.workflowRunId.equals(other.getWorkflowRunId())) && (this.workflowRunInstanceName == null && other.getWorkflowRunInstanceName() == null || this.workflowRunInstanceName != null && this.workflowRunInstanceName.equals(other.getWorkflowRunInstanceName())) && this.timeout == other.getTimeout();
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

         _hashCode += this.getTimeout();
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
