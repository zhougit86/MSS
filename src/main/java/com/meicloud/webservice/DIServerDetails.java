package com.meicloud.webservice;

import com.meicloud.webservice.DIServerDate;
import com.meicloud.webservice.EDIServerRunStatus;
import com.meicloud.webservice.LinkDetails;
import com.meicloud.webservice.TaskDetails;
import com.meicloud.webservice.WorkflowDetails;
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

public class DIServerDetails implements Serializable {

   private EDIServerRunStatus DIServerStatus;
   private DIServerDate currentTime;
   private DIServerDate startupTime;
   private DIServerDate referenceTime;
   private WorkflowDetails[] workflowDetails;
   private LinkDetails[] linkDetails;
   private TaskDetails[] taskDetails;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(DIServerDetails.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "DIServerDetails"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("DIServerStatus");
      elemField.setXmlName(new QName("", "DIServerStatus"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "EDIServerRunStatus"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("currentTime");
      elemField.setXmlName(new QName("", "CurrentTime"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "DIServerDate"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("startupTime");
      elemField.setXmlName(new QName("", "StartupTime"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "DIServerDate"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("referenceTime");
      elemField.setXmlName(new QName("", "ReferenceTime"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "DIServerDate"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("workflowDetails");
      elemField.setXmlName(new QName("", "WorkflowDetails"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "WorkflowDetails"));
      elemField.setMinOccurs(0);
      elemField.setNillable(false);
      elemField.setMaxOccursUnbounded(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("linkDetails");
      elemField.setXmlName(new QName("", "LinkDetails"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "LinkDetails"));
      elemField.setMinOccurs(0);
      elemField.setNillable(false);
      elemField.setMaxOccursUnbounded(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("taskDetails");
      elemField.setXmlName(new QName("", "TaskDetails"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "TaskDetails"));
      elemField.setMinOccurs(0);
      elemField.setNillable(false);
      elemField.setMaxOccursUnbounded(true);
      typeDesc.addFieldDesc(elemField);
   }

   public DIServerDetails() {}

   public DIServerDetails(EDIServerRunStatus DIServerStatus, DIServerDate currentTime, DIServerDate startupTime, DIServerDate referenceTime, WorkflowDetails[] workflowDetails, LinkDetails[] linkDetails, TaskDetails[] taskDetails) {
      this.DIServerStatus = DIServerStatus;
      this.currentTime = currentTime;
      this.startupTime = startupTime;
      this.referenceTime = referenceTime;
      this.workflowDetails = workflowDetails;
      this.linkDetails = linkDetails;
      this.taskDetails = taskDetails;
   }

   public EDIServerRunStatus getDIServerStatus() {
      return this.DIServerStatus;
   }

   public void setDIServerStatus(EDIServerRunStatus DIServerStatus) {
      this.DIServerStatus = DIServerStatus;
   }

   public DIServerDate getCurrentTime() {
      return this.currentTime;
   }

   public void setCurrentTime(DIServerDate currentTime) {
      this.currentTime = currentTime;
   }

   public DIServerDate getStartupTime() {
      return this.startupTime;
   }

   public void setStartupTime(DIServerDate startupTime) {
      this.startupTime = startupTime;
   }

   public DIServerDate getReferenceTime() {
      return this.referenceTime;
   }

   public void setReferenceTime(DIServerDate referenceTime) {
      this.referenceTime = referenceTime;
   }

   public WorkflowDetails[] getWorkflowDetails() {
      return this.workflowDetails;
   }

   public void setWorkflowDetails(WorkflowDetails[] workflowDetails) {
      this.workflowDetails = workflowDetails;
   }

   public WorkflowDetails getWorkflowDetails(int i) {
      return this.workflowDetails[i];
   }

   public void setWorkflowDetails(int i, WorkflowDetails _value) {
      this.workflowDetails[i] = _value;
   }

   public LinkDetails[] getLinkDetails() {
      return this.linkDetails;
   }

   public void setLinkDetails(LinkDetails[] linkDetails) {
      this.linkDetails = linkDetails;
   }

   public LinkDetails getLinkDetails(int i) {
      return this.linkDetails[i];
   }

   public void setLinkDetails(int i, LinkDetails _value) {
      this.linkDetails[i] = _value;
   }

   public TaskDetails[] getTaskDetails() {
      return this.taskDetails;
   }

   public void setTaskDetails(TaskDetails[] taskDetails) {
      this.taskDetails = taskDetails;
   }

   public TaskDetails getTaskDetails(int i) {
      return this.taskDetails[i];
   }

   public void setTaskDetails(int i, TaskDetails _value) {
      this.taskDetails[i] = _value;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof DIServerDetails)) {
         return false;
      } else {
         DIServerDetails other = (DIServerDetails)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.DIServerStatus == null && other.getDIServerStatus() == null || this.DIServerStatus != null && this.DIServerStatus.equals(other.getDIServerStatus())) && (this.currentTime == null && other.getCurrentTime() == null || this.currentTime != null && this.currentTime.equals(other.getCurrentTime())) && (this.startupTime == null && other.getStartupTime() == null || this.startupTime != null && this.startupTime.equals(other.getStartupTime())) && (this.referenceTime == null && other.getReferenceTime() == null || this.referenceTime != null && this.referenceTime.equals(other.getReferenceTime())) && (this.workflowDetails == null && other.getWorkflowDetails() == null || this.workflowDetails != null && Arrays.equals(this.workflowDetails, other.getWorkflowDetails())) && (this.linkDetails == null && other.getLinkDetails() == null || this.linkDetails != null && Arrays.equals(this.linkDetails, other.getLinkDetails())) && (this.taskDetails == null && other.getTaskDetails() == null || this.taskDetails != null && Arrays.equals(this.taskDetails, other.getTaskDetails()));
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
         if(this.getDIServerStatus() != null) {
            _hashCode += this.getDIServerStatus().hashCode();
         }

         if(this.getCurrentTime() != null) {
            _hashCode += this.getCurrentTime().hashCode();
         }

         if(this.getStartupTime() != null) {
            _hashCode += this.getStartupTime().hashCode();
         }

         if(this.getReferenceTime() != null) {
            _hashCode += this.getReferenceTime().hashCode();
         }

         int i;
         Object obj;
         if(this.getWorkflowDetails() != null) {
            for(i = 0; i < Array.getLength(this.getWorkflowDetails()); ++i) {
               obj = Array.get(this.getWorkflowDetails(), i);
               if(obj != null && !obj.getClass().isArray()) {
                  _hashCode += obj.hashCode();
               }
            }
         }

         if(this.getLinkDetails() != null) {
            for(i = 0; i < Array.getLength(this.getLinkDetails()); ++i) {
               obj = Array.get(this.getLinkDetails(), i);
               if(obj != null && !obj.getClass().isArray()) {
                  _hashCode += obj.hashCode();
               }
            }
         }

         if(this.getTaskDetails() != null) {
            for(i = 0; i < Array.getLength(this.getTaskDetails()); ++i) {
               obj = Array.get(this.getTaskDetails(), i);
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
