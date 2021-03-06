package com.meicloud.webservice;

import com.meicloud.webservice.Attribute;
import com.meicloud.webservice.DIServiceInfo;
import com.meicloud.webservice.ETaskRunMode;
import com.meicloud.webservice.Key;
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

public class TypeStartWorkflowExRequest implements Serializable {

   private DIServiceInfo DIServiceInfo;
   private String folderName;
   private String workflowName;
   private String workflowRunInstanceName;
   private String reason;
   private Attribute[] attribute;
   private Key[] key;
   private String parameterFileName;
   private Parameter[] parameters;
   private ETaskRunMode requestMode;
   private String taskInstancePath;
   private String OSUser;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(TypeStartWorkflowExRequest.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "TypeStartWorkflowExRequest"));
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
      elemField.setFieldName("reason");
      elemField.setXmlName(new QName("", "Reason"));
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
      elemField.setFieldName("key");
      elemField.setXmlName(new QName("", "Key"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "Key"));
      elemField.setMinOccurs(0);
      elemField.setNillable(false);
      elemField.setMaxOccursUnbounded(true);
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
      elemField.setFieldName("OSUser");
      elemField.setXmlName(new QName("", "OSUser"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
   }

   public TypeStartWorkflowExRequest() {}

   public TypeStartWorkflowExRequest(DIServiceInfo DIServiceInfo, String folderName, String workflowName, String workflowRunInstanceName, String reason, Attribute[] attribute, Key[] key, String parameterFileName, Parameter[] parameters, ETaskRunMode requestMode, String taskInstancePath, String OSUser) {
      this.DIServiceInfo = DIServiceInfo;
      this.folderName = folderName;
      this.workflowName = workflowName;
      this.workflowRunInstanceName = workflowRunInstanceName;
      this.reason = reason;
      this.attribute = attribute;
      this.key = key;
      this.parameterFileName = parameterFileName;
      this.parameters = parameters;
      this.requestMode = requestMode;
      this.taskInstancePath = taskInstancePath;
      this.OSUser = OSUser;
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

   public String getReason() {
      return this.reason;
   }

   public void setReason(String reason) {
      this.reason = reason;
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

   public Key[] getKey() {
      return this.key;
   }

   public void setKey(Key[] key) {
      this.key = key;
   }

   public Key getKey(int i) {
      return this.key[i];
   }

   public void setKey(int i, Key _value) {
      this.key[i] = _value;
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

   public String getOSUser() {
      return this.OSUser;
   }

   public void setOSUser(String OSUser) {
      this.OSUser = OSUser;
   }

   public synchronized boolean equals(Object obj) {
      if(!(obj instanceof TypeStartWorkflowExRequest)) {
         return false;
      } else {
         TypeStartWorkflowExRequest other = (TypeStartWorkflowExRequest)obj;
         if(obj == null) {
            return false;
         } else if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.DIServiceInfo == null && other.getDIServiceInfo() == null || this.DIServiceInfo != null && this.DIServiceInfo.equals(other.getDIServiceInfo())) && (this.folderName == null && other.getFolderName() == null || this.folderName != null && this.folderName.equals(other.getFolderName())) && (this.workflowName == null && other.getWorkflowName() == null || this.workflowName != null && this.workflowName.equals(other.getWorkflowName())) && (this.workflowRunInstanceName == null && other.getWorkflowRunInstanceName() == null || this.workflowRunInstanceName != null && this.workflowRunInstanceName.equals(other.getWorkflowRunInstanceName())) && (this.reason == null && other.getReason() == null || this.reason != null && this.reason.equals(other.getReason())) && (this.attribute == null && other.getAttribute() == null || this.attribute != null && Arrays.equals(this.attribute, other.getAttribute())) && (this.key == null && other.getKey() == null || this.key != null && Arrays.equals(this.key, other.getKey())) && (this.parameterFileName == null && other.getParameterFileName() == null || this.parameterFileName != null && this.parameterFileName.equals(other.getParameterFileName())) && (this.parameters == null && other.getParameters() == null || this.parameters != null && Arrays.equals(this.parameters, other.getParameters())) && (this.requestMode == null && other.getRequestMode() == null || this.requestMode != null && this.requestMode.equals(other.getRequestMode())) && (this.taskInstancePath == null && other.getTaskInstancePath() == null || this.taskInstancePath != null && this.taskInstancePath.equals(other.getTaskInstancePath())) && (this.OSUser == null && other.getOSUser() == null || this.OSUser != null && this.OSUser.equals(other.getOSUser()));
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

         if(this.getReason() != null) {
            _hashCode += this.getReason().hashCode();
         }

         int i;
         Object obj;
         if(this.getAttribute() != null) {
            for(i = 0; i < Array.getLength(this.getAttribute()); ++i) {
               obj = Array.get(this.getAttribute(), i);
               if(obj != null && !obj.getClass().isArray()) {
                  _hashCode += obj.hashCode();
               }
            }
         }

         if(this.getKey() != null) {
            for(i = 0; i < Array.getLength(this.getKey()); ++i) {
               obj = Array.get(this.getKey(), i);
               if(obj != null && !obj.getClass().isArray()) {
                  _hashCode += obj.hashCode();
               }
            }
         }

         if(this.getParameterFileName() != null) {
            _hashCode += this.getParameterFileName().hashCode();
         }

         if(this.getParameters() != null) {
            for(i = 0; i < Array.getLength(this.getParameters()); ++i) {
               obj = Array.get(this.getParameters(), i);
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
