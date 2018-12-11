package com.meicloud.webservice;

import com.meicloud.webservice.WorkflowInfo;
import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class GetAllTaskInstancesRequest implements Serializable {

   private WorkflowInfo workflowInfo;
   private int depth;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(GetAllTaskInstancesRequest.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "GetAllTaskInstancesRequest"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("workflowInfo");
      elemField.setXmlName(new QName("", "WorkflowInfo"));
      elemField.setXmlType(new QName("http://www.informatica.com/wsh", "WorkflowInfo"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("depth");
      elemField.setXmlName(new QName("", "Depth"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
   }

   public GetAllTaskInstancesRequest() {}

   public GetAllTaskInstancesRequest(WorkflowInfo workflowInfo, int depth) {
      this.workflowInfo = workflowInfo;
      this.depth = depth;
   }

   public WorkflowInfo getWorkflowInfo() {
      return this.workflowInfo;
   }

   public void setWorkflowInfo(WorkflowInfo workflowInfo) {
      this.workflowInfo = workflowInfo;
   }

   public int getDepth() {
      return this.depth;
   }

   public void setDepth(int depth) {
      this.depth = depth;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof GetAllTaskInstancesRequest)) {
         return false;
      } else {
         GetAllTaskInstancesRequest other = (GetAllTaskInstancesRequest)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.workflowInfo == null && other.getWorkflowInfo() == null || this.workflowInfo != null && this.workflowInfo.equals(other.getWorkflowInfo())) && this.depth == other.getDepth();
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
         if(this.getWorkflowInfo() != null) {
            _hashCode += this.getWorkflowInfo().hashCode();
         }

         _hashCode += this.getDepth();
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
