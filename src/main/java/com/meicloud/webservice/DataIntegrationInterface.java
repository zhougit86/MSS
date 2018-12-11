package com.meicloud.webservice;

import com.meicloud.webservice.DIServerDetails;
import com.meicloud.webservice.DIServerProperties;
import com.meicloud.webservice.DIServiceInfo;
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
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataIntegrationInterface extends Remote {

   String login(LoginRequest var1) throws RemoteException, FaultDetails;

   VoidResponse logout(VoidRequest var1) throws RemoteException, FaultDetails;

   VoidResponse initializeDIServerConnection(InitializeDIServerConnectionRequest var1) throws RemoteException, FaultDetails;

   VoidResponse deinitializeDIServerConnection(VoidRequest var1) throws RemoteException, FaultDetails;

   int startWorkflowLogFetch(StartWorkflowLogFetchRequest var1) throws RemoteException, FaultDetails;

   int startSessionLogFetch(StartSessionLogFetchRequest var1) throws RemoteException, FaultDetails;

   LogSegment getNextLogSegment(GetNextLogSegmentRequest var1) throws RemoteException, FaultDetails;

   EPingState pingDIServer(PingDIServerRequest var1) throws RemoteException, FaultDetails;

   DIServerProperties getDIServerProperties(DIServiceInfo var1) throws RemoteException, FaultDetails;

   VoidResponse startWorkflow(WorkflowRequest var1) throws RemoteException, FaultDetails;

   TypeStartWorkflowExResponse startWorkflowEx(TypeStartWorkflowExRequest var1) throws RemoteException, FaultDetails;

   VoidResponse startWorkflowFromTask(WorkflowRequest var1) throws RemoteException, FaultDetails;

   VoidResponse stopWorkflow(WorkflowRequest var1) throws RemoteException, FaultDetails;

   VoidResponse scheduleWorkflow(WorkflowRequest var1) throws RemoteException, FaultDetails;

   VoidResponse unscheduleWorkflow(WorkflowRequest var1) throws RemoteException, FaultDetails;

   VoidResponse waitTillWorkflowComplete(WorkflowRequest var1) throws RemoteException, FaultDetails;

   VoidResponse resumeWorkflow(WorkflowRequest var1) throws RemoteException, FaultDetails;

   VoidResponse startTask(TaskRequest var1) throws RemoteException, FaultDetails;

   VoidResponse stopTask(TaskRequest var1) throws RemoteException, FaultDetails;

   VoidResponse waitTillTaskComplete(TaskRequest var1) throws RemoteException, FaultDetails;

   VoidResponse recoverWorkflow(WorkflowRequest var1) throws RemoteException, FaultDetails;

   DIServerDetails monitorDIServer(MonitorDIServerRequest var1) throws RemoteException, FaultDetails;

   WorkflowDetails getWorkflowDetails(WorkflowRequest var1) throws RemoteException, FaultDetails;

   DIServerDetails getWorkflowDetailsEx(TypeGetWorkflowDetailsExRequest var1) throws RemoteException, FaultDetails;

   TaskDetails getTaskDetails(TaskRequest var1) throws RemoteException, FaultDetails;

   DIServerDetails getTaskDetailsEx(TypeGetTaskDetailsExRequest var1) throws RemoteException, FaultDetails;

   SessionStatistics getSessionStatistics(GetSessionStatisticsRequest var1) throws RemoteException, FaultDetails;

   SessionPerformanceData getSessionPerformanceData(GetSessionPerformanceDataRequest var1) throws RemoteException, FaultDetails;

   Log getWorkflowLog(GetWorkflowLogRequest var1) throws RemoteException, FaultDetails;

   Log getSessionLog(GetSessionLogRequest var1) throws RemoteException, FaultDetails;
}
