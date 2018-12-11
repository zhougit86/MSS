package com.meicloud.webservice;

import com.meicloud.webservice.DIServerInfo;
import com.meicloud.webservice.FaultDetails;
import com.meicloud.webservice.FolderInfo;
import com.meicloud.webservice.GetAllTaskInstancesRequest;
import com.meicloud.webservice.LoginRequest;
import com.meicloud.webservice.MetadataInterface;
import com.meicloud.webservice.RepositoryInfo;
import com.meicloud.webservice.TaskInstanceInfo;
import com.meicloud.webservice.VoidRequest;
import com.meicloud.webservice.VoidResponse;
import com.meicloud.webservice.WorkflowInfo;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Vector;
import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.encoding.SerializerFactory;
import org.apache.axis.AxisFault;
import org.apache.axis.NoEndPointException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.description.FaultDesc;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.DeserializerFactory;
import org.apache.axis.encoding.ser.ArrayDeserializerFactory;
import org.apache.axis.encoding.ser.ArraySerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.encoding.ser.EnumDeserializerFactory;
import org.apache.axis.encoding.ser.EnumSerializerFactory;
import org.apache.axis.encoding.ser.SimpleDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListSerializerFactory;
import org.apache.axis.encoding.ser.SimpleSerializerFactory;
import org.apache.axis.soap.SOAPConstants;
import org.apache.axis.utils.JavaUtils;

public class MetadataServiceSoapBindingStub extends Stub implements MetadataInterface {

   private Vector cachedSerClasses;
   private Vector cachedSerQNames;
   private Vector cachedSerFactories;
   private Vector cachedDeserFactories;
   static OperationDesc[] _operations = new OperationDesc[7];


   static {
      _initOperationDesc1();
   }

   private static void _initOperationDesc1() {
      OperationDesc oper = new OperationDesc();
      oper.setName("login");
      ParameterDesc param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "Login"), (byte)1, new QName("http://www.informatica.com/wsh", "LoginRequest"), LoginRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
      oper.setReturnClass(String.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "LoginReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[0] = oper;
      oper = new OperationDesc();
      oper.setName("logout");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "Logout"), (byte)1, new QName("http://www.informatica.com/wsh", "VoidRequest"), VoidRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "LogoutReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[1] = oper;
      oper = new OperationDesc();
      oper.setName("getAllFolders");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetAllFolders"), (byte)1, new QName("http://www.informatica.com/wsh", "VoidRequest"), VoidRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "FolderInfoArray"));
      oper.setReturnClass(FolderInfo[].class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetAllFoldersReturn"));
      param = oper.getReturnParamDesc();
      param.setItemQName(new QName("", "FolderInfo"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[2] = oper;
      oper = new OperationDesc();
      oper.setName("getAllWorkflows");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetAllWorkflows"), (byte)1, new QName("http://www.informatica.com/wsh", "FolderInfo"), FolderInfo.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "WorkflowInfoArray"));
      oper.setReturnClass(WorkflowInfo[].class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetAllWorkflowsReturn"));
      param = oper.getReturnParamDesc();
      param.setItemQName(new QName("", "WorkflowInfo"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[3] = oper;
      oper = new OperationDesc();
      oper.setName("getAllTaskInstances");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetAllTaskInstances"), (byte)1, new QName("http://www.informatica.com/wsh", "GetAllTaskInstancesRequest"), GetAllTaskInstancesRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "TaskInstanceInfoArray"));
      oper.setReturnClass(TaskInstanceInfo[].class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetAllTaskInstancesReturn"));
      param = oper.getReturnParamDesc();
      param.setItemQName(new QName("", "TaskInstanceInfo"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[4] = oper;
      oper = new OperationDesc();
      oper.setName("getAllDIServers");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetAllDIServers"), (byte)1, new QName("http://www.informatica.com/wsh", "VoidRequest"), VoidRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "DIServerInfoArray"));
      oper.setReturnClass(DIServerInfo[].class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetAllDIServersReturn"));
      param = oper.getReturnParamDesc();
      param.setItemQName(new QName("", "DIServerInfo"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[5] = oper;
      oper = new OperationDesc();
      oper.setName("getAllRepositories");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetAllRepositories"), (byte)1, new QName("http://www.informatica.com/wsh", "VoidRequest"), VoidRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "RepositoryInfoArray"));
      oper.setReturnClass(RepositoryInfo[].class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetAllRepositoriesReturn"));
      param = oper.getReturnParamDesc();
      param.setItemQName(new QName("", "RepositoryInfo"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[6] = oper;
   }

   public MetadataServiceSoapBindingStub() throws AxisFault {
      this((Service)null);
   }

   public MetadataServiceSoapBindingStub(URL endpointURL, Service service) throws AxisFault {
      this(service);
      super.cachedEndpoint = endpointURL;
   }

   public MetadataServiceSoapBindingStub(Service service) throws AxisFault {
      this.cachedSerClasses = new Vector();
      this.cachedSerQNames = new Vector();
      this.cachedSerFactories = new Vector();
      this.cachedDeserFactories = new Vector();
      if(service == null) {
         super.service = new org.apache.axis.client.Service();
      } else {
         super.service = service;
      }

      ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
      Class beansf = BeanSerializerFactory.class;
      Class beandf = BeanDeserializerFactory.class;
      Class enumsf = EnumSerializerFactory.class;
      Class enumdf = EnumDeserializerFactory.class;
      Class arraysf = ArraySerializerFactory.class;
      Class arraydf = ArrayDeserializerFactory.class;
      Class simplesf = SimpleSerializerFactory.class;
      Class simpledf = SimpleDeserializerFactory.class;
      Class simplelistsf = SimpleListSerializerFactory.class;
      Class simplelistdf = SimpleListDeserializerFactory.class;
      QName qName = new QName("http://www.informatica.com/wsh", "DIServerInfo");
      this.cachedSerQNames.add(qName);
      Class cls = DIServerInfo.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "DIServerInfoArray");
      this.cachedSerQNames.add(qName);
      cls = DIServerInfo[].class;
      this.cachedSerClasses.add(cls);
      qName = new QName("http://www.informatica.com/wsh", "DIServerInfo");
      QName qName2 = new QName("", "DIServerInfo");
      this.cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
      this.cachedDeserFactories.add(new ArrayDeserializerFactory());
      qName = new QName("http://www.informatica.com/wsh", "FaultDetails");
      this.cachedSerQNames.add(qName);
      cls = FaultDetails.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "FolderInfo");
      this.cachedSerQNames.add(qName);
      cls = FolderInfo.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "FolderInfoArray");
      this.cachedSerQNames.add(qName);
      cls = FolderInfo[].class;
      this.cachedSerClasses.add(cls);
      qName = new QName("http://www.informatica.com/wsh", "FolderInfo");
      qName2 = new QName("", "FolderInfo");
      this.cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
      this.cachedDeserFactories.add(new ArrayDeserializerFactory());
      qName = new QName("http://www.informatica.com/wsh", "GetAllTaskInstancesRequest");
      this.cachedSerQNames.add(qName);
      cls = GetAllTaskInstancesRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "LoginRequest");
      this.cachedSerQNames.add(qName);
      cls = LoginRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "RepositoryInfo");
      this.cachedSerQNames.add(qName);
      cls = RepositoryInfo.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "RepositoryInfoArray");
      this.cachedSerQNames.add(qName);
      cls = RepositoryInfo[].class;
      this.cachedSerClasses.add(cls);
      qName = new QName("http://www.informatica.com/wsh", "RepositoryInfo");
      qName2 = new QName("", "RepositoryInfo");
      this.cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
      this.cachedDeserFactories.add(new ArrayDeserializerFactory());
      qName = new QName("http://www.informatica.com/wsh", "TaskInstanceInfo");
      this.cachedSerQNames.add(qName);
      cls = TaskInstanceInfo.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "TaskInstanceInfoArray");
      this.cachedSerQNames.add(qName);
      cls = TaskInstanceInfo[].class;
      this.cachedSerClasses.add(cls);
      qName = new QName("http://www.informatica.com/wsh", "TaskInstanceInfo");
      qName2 = new QName("", "TaskInstanceInfo");
      this.cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
      this.cachedDeserFactories.add(new ArrayDeserializerFactory());
      qName = new QName("http://www.informatica.com/wsh", "VoidRequest");
      this.cachedSerQNames.add(qName);
      cls = VoidRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "VoidResponse");
      this.cachedSerQNames.add(qName);
      cls = VoidResponse.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "WorkflowInfo");
      this.cachedSerQNames.add(qName);
      cls = WorkflowInfo.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "WorkflowInfoArray");
      this.cachedSerQNames.add(qName);
      cls = WorkflowInfo[].class;
      this.cachedSerClasses.add(cls);
      qName = new QName("http://www.informatica.com/wsh", "WorkflowInfo");
      qName2 = new QName("", "WorkflowInfo");
      this.cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
      this.cachedDeserFactories.add(new ArrayDeserializerFactory());
   }

   protected Call createCall() throws RemoteException {
      try {
         Call _t = super._createCall();
         if(super.maintainSessionSet) {
            _t.setMaintainSession(super.maintainSession);
         }

         if(super.cachedUsername != null) {
            _t.setUsername(super.cachedUsername);
         }

         if(super.cachedPassword != null) {
            _t.setPassword(super.cachedPassword);
         }

         if(super.cachedEndpoint != null) {
            _t.setTargetEndpointAddress(super.cachedEndpoint);
         }

         if(super.cachedTimeout != null) {
            _t.setTimeout(super.cachedTimeout);
         }

         if(super.cachedPortName != null) {
            _t.setPortName(super.cachedPortName);
         }

         Enumeration keys = super.cachedProperties.keys();

         while(keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            _t.setProperty(key, super.cachedProperties.get(key));
         }

         synchronized(this) {
            if(this.firstCall()) {
               _t.setEncodingStyle((String)null);

               for(int i = 0; i < this.cachedSerFactories.size(); ++i) {
                  Class cls = (Class)this.cachedSerClasses.get(i);
                  QName qName = (QName)this.cachedSerQNames.get(i);
                  Object x = this.cachedSerFactories.get(i);
                  if(x instanceof Class) {
                     Class sf = (Class)this.cachedSerFactories.get(i);
                     Class df = (Class)this.cachedDeserFactories.get(i);
                     _t.registerTypeMapping(cls, qName, sf, df, false);
                  } else if(x instanceof SerializerFactory) {
                     org.apache.axis.encoding.SerializerFactory var12 = (org.apache.axis.encoding.SerializerFactory)this.cachedSerFactories.get(i);
                     DeserializerFactory var13 = (DeserializerFactory)this.cachedDeserFactories.get(i);
                     _t.registerTypeMapping(cls, qName, var12, var13, false);
                  }
               }
            }
         }

         return _t;
      } catch (Throwable var11) {
         throw new AxisFault("Failure trying to get the Call object", var11);
      }
   }

   public String login(LoginRequest param) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[0]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "login"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{param});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (String)axisFaultException;
               } catch (Exception var5) {
                  return (String)JavaUtils.convert(axisFaultException, String.class);
               }
            }
         } catch (AxisFault var6) {
            if(var6.detail != null) {
               if(var6.detail instanceof RemoteException) {
                  throw (RemoteException)var6.detail;
               }

               if(var6.detail instanceof FaultDetails) {
                  throw (FaultDetails)var6.detail;
               }
            }

            throw var6;
         }
      }
   }

   public VoidResponse logout(VoidRequest param) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[1]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "logout"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{param});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (VoidResponse)axisFaultException;
               } catch (Exception var5) {
                  return (VoidResponse)JavaUtils.convert(axisFaultException, VoidResponse.class);
               }
            }
         } catch (AxisFault var6) {
            if(var6.detail != null) {
               if(var6.detail instanceof RemoteException) {
                  throw (RemoteException)var6.detail;
               }

               if(var6.detail instanceof FaultDetails) {
                  throw (FaultDetails)var6.detail;
               }
            }

            throw var6;
         }
      }
   }

   public FolderInfo[] getAllFolders(VoidRequest param) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[2]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getAllFolders"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{param});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (FolderInfo[])axisFaultException;
               } catch (Exception var5) {
                  return (FolderInfo[])JavaUtils.convert(axisFaultException, FolderInfo[].class);
               }
            }
         } catch (AxisFault var6) {
            if(var6.detail != null) {
               if(var6.detail instanceof RemoteException) {
                  throw (RemoteException)var6.detail;
               }

               if(var6.detail instanceof FaultDetails) {
                  throw (FaultDetails)var6.detail;
               }
            }

            throw var6;
         }
      }
   }

   public WorkflowInfo[] getAllWorkflows(FolderInfo param) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[3]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getAllWorkflows"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{param});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (WorkflowInfo[])axisFaultException;
               } catch (Exception var5) {
                  return (WorkflowInfo[])JavaUtils.convert(axisFaultException, WorkflowInfo[].class);
               }
            }
         } catch (AxisFault var6) {
            if(var6.detail != null) {
               if(var6.detail instanceof RemoteException) {
                  throw (RemoteException)var6.detail;
               }

               if(var6.detail instanceof FaultDetails) {
                  throw (FaultDetails)var6.detail;
               }
            }

            throw var6;
         }
      }
   }

   public TaskInstanceInfo[] getAllTaskInstances(GetAllTaskInstancesRequest param) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[4]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getAllTaskInstances"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{param});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (TaskInstanceInfo[])axisFaultException;
               } catch (Exception var5) {
                  return (TaskInstanceInfo[])JavaUtils.convert(axisFaultException, TaskInstanceInfo[].class);
               }
            }
         } catch (AxisFault var6) {
            if(var6.detail != null) {
               if(var6.detail instanceof RemoteException) {
                  throw (RemoteException)var6.detail;
               }

               if(var6.detail instanceof FaultDetails) {
                  throw (FaultDetails)var6.detail;
               }
            }

            throw var6;
         }
      }
   }

   public DIServerInfo[] getAllDIServers(VoidRequest param) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[5]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getAllDIServers"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{param});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (DIServerInfo[])axisFaultException;
               } catch (Exception var5) {
                  return (DIServerInfo[])JavaUtils.convert(axisFaultException, DIServerInfo[].class);
               }
            }
         } catch (AxisFault var6) {
            if(var6.detail != null) {
               if(var6.detail instanceof RemoteException) {
                  throw (RemoteException)var6.detail;
               }

               if(var6.detail instanceof FaultDetails) {
                  throw (FaultDetails)var6.detail;
               }
            }

            throw var6;
         }
      }
   }

   public RepositoryInfo[] getAllRepositories(VoidRequest param) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[6]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getAllRepositories"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{param});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (RepositoryInfo[])axisFaultException;
               } catch (Exception var5) {
                  return (RepositoryInfo[])JavaUtils.convert(axisFaultException, RepositoryInfo[].class);
               }
            }
         } catch (AxisFault var6) {
            if(var6.detail != null) {
               if(var6.detail instanceof RemoteException) {
                  throw (RemoteException)var6.detail;
               }

               if(var6.detail instanceof FaultDetails) {
                  throw (FaultDetails)var6.detail;
               }
            }

            throw var6;
         }
      }
   }
}
