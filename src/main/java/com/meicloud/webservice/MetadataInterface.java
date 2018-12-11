package com.meicloud.webservice;

import com.meicloud.webservice.DIServerInfo;
import com.meicloud.webservice.FaultDetails;
import com.meicloud.webservice.FolderInfo;
import com.meicloud.webservice.GetAllTaskInstancesRequest;
import com.meicloud.webservice.LoginRequest;
import com.meicloud.webservice.RepositoryInfo;
import com.meicloud.webservice.TaskInstanceInfo;
import com.meicloud.webservice.VoidRequest;
import com.meicloud.webservice.VoidResponse;
import com.meicloud.webservice.WorkflowInfo;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MetadataInterface extends Remote {

   String login(LoginRequest var1) throws RemoteException, FaultDetails;

   VoidResponse logout(VoidRequest var1) throws RemoteException, FaultDetails;

   FolderInfo[] getAllFolders(VoidRequest var1) throws RemoteException, FaultDetails;

   WorkflowInfo[] getAllWorkflows(FolderInfo var1) throws RemoteException, FaultDetails;

   TaskInstanceInfo[] getAllTaskInstances(GetAllTaskInstancesRequest var1) throws RemoteException, FaultDetails;

   DIServerInfo[] getAllDIServers(VoidRequest var1) throws RemoteException, FaultDetails;

   RepositoryInfo[] getAllRepositories(VoidRequest var1) throws RemoteException, FaultDetails;
}
