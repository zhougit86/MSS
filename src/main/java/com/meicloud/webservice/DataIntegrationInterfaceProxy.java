package com.meicloud.webservice;

import com.meicloud.webservice.DIServerDetails;
import com.meicloud.webservice.DIServerProperties;
import com.meicloud.webservice.DIServiceInfo;
import com.meicloud.webservice.DataIntegrationInterface;
import com.meicloud.webservice.DataIntegrationServiceLocator;
import com.meicloud.webservice.EPingState;
import com.meicloud.webservice.FaultDetails;
import com.meicloud.webservice.GetNextLogSegmentRequest;
import com.meicloud.webservice.GetSessionLogRequest;
import com.meicloud.webservice.GetSessionPerformanceDataRequest;
import com.meicloud.webservice.GetSessionStatisticsRequest;
import com.meicloud.webservice.GetWorkflowLogRequest;
import com.meicloud.webservice.InitializeDIServerConnectionRequest;
import com.meicloud.webservice.Log;
import com.meicloud.webservice.LogSegment;
import com.meicloud.webservice.LoginRequest;
import com.meicloud.webservice.MonitorDIServerRequest;
import com.meicloud.webservice.PingDIServerRequest;
import com.meicloud.webservice.SessionPerformanceData;
import com.meicloud.webservice.SessionStatistics;
import com.meicloud.webservice.StartSessionLogFetchRequest;
import com.meicloud.webservice.StartWorkflowLogFetchRequest;
import com.meicloud.webservice.TaskDetails;
import com.meicloud.webservice.TaskRequest;
import com.meicloud.webservice.TypeGetTaskDetailsExRequest;
import com.meicloud.webservice.TypeGetWorkflowDetailsExRequest;
import com.meicloud.webservice.TypeStartWorkflowExRequest;
import com.meicloud.webservice.TypeStartWorkflowExResponse;
import com.meicloud.webservice.VoidRequest;
import com.meicloud.webservice.VoidResponse;
import com.meicloud.webservice.WorkflowDetails;
import com.meicloud.webservice.WorkflowRequest;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;

public class DataIntegrationInterfaceProxy implements DataIntegrationInterface {

   private String _endpoint = null;
   private DataIntegrationInterface dataIntegrationInterface = null;


   public DataIntegrationInterfaceProxy() {
      this._initDataIntegrationInterfaceProxy();
   }

   public DataIntegrationInterfaceProxy(String endpoint) {
      this._endpoint = endpoint;
      this._initDataIntegrationInterfaceProxy();
   }

   private void _initDataIntegrationInterfaceProxy() {
      try {
         this.dataIntegrationInterface = (new DataIntegrationServiceLocator()).getDataIntegration();
         if(this.dataIntegrationInterface != null) {
            if(this._endpoint != null) {
               ((Stub)this.dataIntegrationInterface)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
            } else {
               this._endpoint = (String)((Stub)this.dataIntegrationInterface)._getProperty("javax.xml.rpc.service.endpoint.address");
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
      if(this.dataIntegrationInterface != null) {
         ((Stub)this.dataIntegrationInterface)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
      }

   }

   public DataIntegrationInterface getDataIntegrationInterface() {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface;
   }

   public String login(LoginRequest param) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.login(param);
   }

   public VoidResponse logout(VoidRequest param) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.logout(param);
   }

   public VoidResponse initializeDIServerConnection(InitializeDIServerConnectionRequest param) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.initializeDIServerConnection(param);
   }

   public VoidResponse deinitializeDIServerConnection(VoidRequest param) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.deinitializeDIServerConnection(param);
   }

   public int startWorkflowLogFetch(StartWorkflowLogFetchRequest param) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.startWorkflowLogFetch(param);
   }

   public int startSessionLogFetch(StartSessionLogFetchRequest param) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.startSessionLogFetch(param);
   }

   public LogSegment getNextLogSegment(GetNextLogSegmentRequest param) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.getNextLogSegment(param);
   }

   public EPingState pingDIServer(PingDIServerRequest pingDIServer) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.pingDIServer(pingDIServer);
   }

   public DIServerProperties getDIServerProperties(DIServiceInfo getDIServerProperties) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.getDIServerProperties(getDIServerProperties);
   }

   public VoidResponse startWorkflow(WorkflowRequest startWorkflow) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.startWorkflow(startWorkflow);
   }

   public TypeStartWorkflowExResponse startWorkflowEx(TypeStartWorkflowExRequest startWorkflowEx) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.startWorkflowEx(startWorkflowEx);
   }

   public VoidResponse startWorkflowFromTask(WorkflowRequest startWorkflowFromTask) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.startWorkflowFromTask(startWorkflowFromTask);
   }

   public VoidResponse stopWorkflow(WorkflowRequest stopWorkflow) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.stopWorkflow(stopWorkflow);
   }

   public VoidResponse scheduleWorkflow(WorkflowRequest scheduleWorkflow) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.scheduleWorkflow(scheduleWorkflow);
   }

   public VoidResponse unscheduleWorkflow(WorkflowRequest unscheduleWorkflow) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.unscheduleWorkflow(unscheduleWorkflow);
   }

   public VoidResponse waitTillWorkflowComplete(WorkflowRequest waitTillWorkflowComplete) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.waitTillWorkflowComplete(waitTillWorkflowComplete);
   }

   public VoidResponse resumeWorkflow(WorkflowRequest resumeWorkflow) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.resumeWorkflow(resumeWorkflow);
   }

   public VoidResponse startTask(TaskRequest startTask) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.startTask(startTask);
   }

   public VoidResponse stopTask(TaskRequest stopTask) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.stopTask(stopTask);
   }

   public VoidResponse waitTillTaskComplete(TaskRequest waitTillTaskComplete) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.waitTillTaskComplete(waitTillTaskComplete);
   }

   public VoidResponse recoverWorkflow(WorkflowRequest recoverWorkflow) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.recoverWorkflow(recoverWorkflow);
   }

   public DIServerDetails monitorDIServer(MonitorDIServerRequest monitorDIServer) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.monitorDIServer(monitorDIServer);
   }

   public WorkflowDetails getWorkflowDetails(WorkflowRequest getWorkflowDetails) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.getWorkflowDetails(getWorkflowDetails);
   }

   public DIServerDetails getWorkflowDetailsEx(TypeGetWorkflowDetailsExRequest getWorkflowDetailsEx) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.getWorkflowDetailsEx(getWorkflowDetailsEx);
   }

   public TaskDetails getTaskDetails(TaskRequest getTaskDetails) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.getTaskDetails(getTaskDetails);
   }

   public DIServerDetails getTaskDetailsEx(TypeGetTaskDetailsExRequest getTaskDetailsEx) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.getTaskDetailsEx(getTaskDetailsEx);
   }

   public SessionStatistics getSessionStatistics(GetSessionStatisticsRequest getSessionStatistics) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.getSessionStatistics(getSessionStatistics);
   }

   public SessionPerformanceData getSessionPerformanceData(GetSessionPerformanceDataRequest getSessionPerformanceData) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.getSessionPerformanceData(getSessionPerformanceData);
   }

   public Log getWorkflowLog(GetWorkflowLogRequest getWorkflowLog) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.getWorkflowLog(getWorkflowLog);
   }

   public Log getSessionLog(GetSessionLogRequest getSessionLog) throws RemoteException, FaultDetails {
      if(this.dataIntegrationInterface == null) {
         this._initDataIntegrationInterfaceProxy();
      }

      return this.dataIntegrationInterface.getSessionLog(getSessionLog);
   }
}
