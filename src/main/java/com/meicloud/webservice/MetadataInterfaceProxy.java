package com.meicloud.webservice;

import com.meicloud.webservice.DIServerInfo;
import com.meicloud.webservice.FaultDetails;
import com.meicloud.webservice.FolderInfo;
import com.meicloud.webservice.GetAllTaskInstancesRequest;
import com.meicloud.webservice.LoginRequest;
import com.meicloud.webservice.MetadataInterface;
import com.meicloud.webservice.MetadataServiceLocator;
import com.meicloud.webservice.RepositoryInfo;
import com.meicloud.webservice.TaskInstanceInfo;
import com.meicloud.webservice.VoidRequest;
import com.meicloud.webservice.VoidResponse;
import com.meicloud.webservice.WorkflowInfo;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;

public class MetadataInterfaceProxy implements MetadataInterface {

   private String _endpoint = null;
   private MetadataInterface metadataInterface = null;


   public MetadataInterfaceProxy() {
      this._initMetadataInterfaceProxy();
   }

   public MetadataInterfaceProxy(String endpoint) {
      this._endpoint = endpoint;
      this._initMetadataInterfaceProxy();
   }

   private void _initMetadataInterfaceProxy() {
      try {
         this.metadataInterface = (new MetadataServiceLocator()).getMetadata();
         if(this.metadataInterface != null) {
            if(this._endpoint != null) {
               ((Stub)this.metadataInterface)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
            } else {
               this._endpoint = (String)((Stub)this.metadataInterface)._getProperty("javax.xml.rpc.service.endpoint.address");
            }
         }
      } catch (ServiceException var2) {
         ;
      }

   }

   public String getEndpoint() {
      return this._endpoint;
   }

   public void setEndpoint(String endpoint) {
      this._endpoint = endpoint;
      if(this.metadataInterface != null) {
         ((Stub)this.metadataInterface)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
      }

   }

   public MetadataInterface getMetadataInterface() {
      if(this.metadataInterface == null) {
         this._initMetadataInterfaceProxy();
      }

      return this.metadataInterface;
   }

   public String login(LoginRequest param) throws RemoteException, FaultDetails {
      if(this.metadataInterface == null) {
         this._initMetadataInterfaceProxy();
      }

      return this.metadataInterface.login(param);
   }

   public VoidResponse logout(VoidRequest param) throws RemoteException, FaultDetails {
      if(this.metadataInterface == null) {
         this._initMetadataInterfaceProxy();
      }

      return this.metadataInterface.logout(param);
   }

   public FolderInfo[] getAllFolders(VoidRequest param) throws RemoteException, FaultDetails {
      if(this.metadataInterface == null) {
         this._initMetadataInterfaceProxy();
      }

      return this.metadataInterface.getAllFolders(param);
   }

   public WorkflowInfo[] getAllWorkflows(FolderInfo param) throws RemoteException, FaultDetails {
      if(this.metadataInterface == null) {
         this._initMetadataInterfaceProxy();
      }

      return this.metadataInterface.getAllWorkflows(param);
   }

   public TaskInstanceInfo[] getAllTaskInstances(GetAllTaskInstancesRequest param) throws RemoteException, FaultDetails {
      if(this.metadataInterface == null) {
         this._initMetadataInterfaceProxy();
      }

      return this.metadataInterface.getAllTaskInstances(param);
   }

   public DIServerInfo[] getAllDIServers(VoidRequest param) throws RemoteException, FaultDetails {
      if(this.metadataInterface == null) {
         this._initMetadataInterfaceProxy();
      }

      return this.metadataInterface.getAllDIServers(param);
   }

   public RepositoryInfo[] getAllRepositories(VoidRequest param) throws RemoteException, FaultDetails {
      if(this.metadataInterface == null) {
         this._initMetadataInterfaceProxy();
      }

      return this.metadataInterface.getAllRepositories(param);
   }
}
