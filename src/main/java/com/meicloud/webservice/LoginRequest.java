package com.meicloud.webservice;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class LoginRequest implements Serializable {

   private String repositoryDomainName;
   private String repositoryName;
   private String userName;
   private String password;
   private String userNameSpace;
   private Object __equalsCalc = null;
   private boolean __hashCodeCalc = false;
   private static TypeDesc typeDesc = new TypeDesc(LoginRequest.class, true);


   static {
      typeDesc.setXmlType(new QName("http://www.informatica.com/wsh", "LoginRequest"));
      ElementDesc elemField = new ElementDesc();
      elemField.setFieldName("repositoryDomainName");
      elemField.setXmlName(new QName("", "RepositoryDomainName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("repositoryName");
      elemField.setXmlName(new QName("", "RepositoryName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("userName");
      elemField.setXmlName(new QName("", "UserName"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("password");
      elemField.setXmlName(new QName("", "Password"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(false);
      typeDesc.addFieldDesc(elemField);
      elemField = new ElementDesc();
      elemField.setFieldName("userNameSpace");
      elemField.setXmlName(new QName("", "UserNameSpace"));
      elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      elemField.setNillable(true);
      typeDesc.addFieldDesc(elemField);
   }

   public LoginRequest() {}

   public LoginRequest(String repositoryDomainName, String repositoryName, String userName, String password, String userNameSpace) {
      this.repositoryDomainName = repositoryDomainName;
      this.repositoryName = repositoryName;
      this.userName = userName;
      this.password = password;
      this.userNameSpace = userNameSpace;
   }

   public String getRepositoryDomainName() {
      return this.repositoryDomainName;
   }

   public void setRepositoryDomainName(String repositoryDomainName) {
      this.repositoryDomainName = repositoryDomainName;
   }

   public String getRepositoryName() {
      return this.repositoryName;
   }

   public void setRepositoryName(String repositoryName) {
      this.repositoryName = repositoryName;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getUserNameSpace() {
      return this.userNameSpace;
   }

   public void setUserNameSpace(String userNameSpace) {
      this.userNameSpace = userNameSpace;
   }

   public synchronized boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(!(obj instanceof LoginRequest)) {
         return false;
      } else {
         LoginRequest other = (LoginRequest)obj;
         if(this == obj) {
            return true;
         } else if(this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
         } else {
            this.__equalsCalc = obj;
            boolean _equals = (this.repositoryDomainName == null && other.getRepositoryDomainName() == null || this.repositoryDomainName != null && this.repositoryDomainName.equals(other.getRepositoryDomainName())) && (this.repositoryName == null && other.getRepositoryName() == null || this.repositoryName != null && this.repositoryName.equals(other.getRepositoryName())) && (this.userName == null && other.getUserName() == null || this.userName != null && this.userName.equals(other.getUserName())) && (this.password == null && other.getPassword() == null || this.password != null && this.password.equals(other.getPassword())) && (this.userNameSpace == null && other.getUserNameSpace() == null || this.userNameSpace != null && this.userNameSpace.equals(other.getUserNameSpace()));
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
         if(this.getRepositoryDomainName() != null) {
            _hashCode += this.getRepositoryDomainName().hashCode();
         }

         if(this.getRepositoryName() != null) {
            _hashCode += this.getRepositoryName().hashCode();
         }

         if(this.getUserName() != null) {
            _hashCode += this.getUserName().hashCode();
         }

         if(this.getPassword() != null) {
            _hashCode += this.getPassword().hashCode();
         }

         if(this.getUserNameSpace() != null) {
            _hashCode += this.getUserNameSpace().hashCode();
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
