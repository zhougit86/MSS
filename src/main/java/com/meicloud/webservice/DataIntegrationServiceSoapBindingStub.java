package com.meicloud.webservice;

import com.meicloud.webservice.Attribute;
import com.meicloud.webservice.DIServerDate;
import com.meicloud.webservice.DIServerDetails;
import com.meicloud.webservice.DIServerProperties;
import com.meicloud.webservice.DIServiceInfo;
import com.meicloud.webservice.DataIntegrationInterface;
import com.meicloud.webservice.EDIServerMode;
import com.meicloud.webservice.EDIServerMonitorMode;
import com.meicloud.webservice.EDIServerRunStatus;
import com.meicloud.webservice.EPerformanceCounterType;
import com.meicloud.webservice.EPingState;
import com.meicloud.webservice.ETaskRunMode;
import com.meicloud.webservice.ETaskRunStatus;
import com.meicloud.webservice.ETaskType;
import com.meicloud.webservice.EWorkflowRunStatus;
import com.meicloud.webservice.EWorkflowRunType;
import com.meicloud.webservice.FaultDetails;
import com.meicloud.webservice.GetNextLogSegmentRequest;
import com.meicloud.webservice.GetSessionLogRequest;
import com.meicloud.webservice.GetSessionPerformanceDataRequest;
import com.meicloud.webservice.GetSessionStatisticsRequest;
import com.meicloud.webservice.GetWorkflowLogRequest;
import com.meicloud.webservice.InitializeDIServerConnectionRequest;
import com.meicloud.webservice.Key;
import com.meicloud.webservice.LinkDetails;
import com.meicloud.webservice.Log;
import com.meicloud.webservice.LogSegment;
import com.meicloud.webservice.LoginRequest;
import com.meicloud.webservice.MonitorDIServerRequest;
import com.meicloud.webservice.Parameter;
import com.meicloud.webservice.PerformanceCounter;
import com.meicloud.webservice.PingDIServerRequest;
import com.meicloud.webservice.SessionPerformanceData;
import com.meicloud.webservice.SessionStatistics;
import com.meicloud.webservice.StartSessionLogFetchRequest;
import com.meicloud.webservice.StartWorkflowLogFetchRequest;
import com.meicloud.webservice.TableStatistics;
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

public class DataIntegrationServiceSoapBindingStub extends Stub implements DataIntegrationInterface {

   private Vector cachedSerClasses;
   private Vector cachedSerQNames;
   private Vector cachedSerFactories;
   private Vector cachedDeserFactories;
   static OperationDesc[] _operations = new OperationDesc[30];


   static {
      _initOperationDesc1();
      _initOperationDesc2();
      _initOperationDesc3();
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
      oper.setName("initializeDIServerConnection");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "InitializeDIServerConnection"), (byte)1, new QName("http://www.informatica.com/wsh", "InitializeDIServerConnectionRequest"), InitializeDIServerConnectionRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "InitializeDIServerConnectionReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[2] = oper;
      oper = new OperationDesc();
      oper.setName("deinitializeDIServerConnection");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "DeinitializeDIServerConnection"), (byte)1, new QName("http://www.informatica.com/wsh", "VoidRequest"), VoidRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "DeinitializeDIServerConnectionReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[3] = oper;
      oper = new OperationDesc();
      oper.setName("startWorkflowLogFetch");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "StartWorkflowLogFetch"), (byte)1, new QName("http://www.informatica.com/wsh", "StartWorkflowLogFetchRequest"), StartWorkflowLogFetchRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      oper.setReturnClass(Integer.TYPE);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "StartWorkflowLogFetchReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[4] = oper;
      oper = new OperationDesc();
      oper.setName("startSessionLogFetch");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "StartSessionLogFetch"), (byte)1, new QName("http://www.informatica.com/wsh", "StartSessionLogFetchRequest"), StartSessionLogFetchRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
      oper.setReturnClass(Integer.TYPE);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "StartSessionLogFetchReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[5] = oper;
      oper = new OperationDesc();
      oper.setName("getNextLogSegment");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetNextLogSegment"), (byte)1, new QName("http://www.informatica.com/wsh", "GetNextLogSegmentRequest"), GetNextLogSegmentRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "LogSegment"));
      oper.setReturnClass(LogSegment.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetNextLogSegmentReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[6] = oper;
      oper = new OperationDesc();
      oper.setName("pingDIServer");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "PingDIServer"), (byte)1, new QName("http://www.informatica.com/wsh", "PingDIServerRequest"), PingDIServerRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "EPingState"));
      oper.setReturnClass(EPingState.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "PingDIServerReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[7] = oper;
      oper = new OperationDesc();
      oper.setName("getDIServerProperties");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetDIServerProperties"), (byte)1, new QName("http://www.informatica.com/wsh", "DIServiceInfo"), DIServiceInfo.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "DIServerProperties"));
      oper.setReturnClass(DIServerProperties.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetDIServerPropertiesReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[8] = oper;
      oper = new OperationDesc();
      oper.setName("startWorkflow");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "StartWorkflow"), (byte)1, new QName("http://www.informatica.com/wsh", "WorkflowRequest"), WorkflowRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "StartWorkflowReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[9] = oper;
   }

   private static void _initOperationDesc2() {
      OperationDesc oper = new OperationDesc();
      oper.setName("startWorkflowEx");
      ParameterDesc param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "StartWorkflowEx"), (byte)1, new QName("http://www.informatica.com/wsh", "TypeStartWorkflowExRequest"), TypeStartWorkflowExRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "TypeStartWorkflowExResponse"));
      oper.setReturnClass(TypeStartWorkflowExResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "StartWorkflowExReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[10] = oper;
      oper = new OperationDesc();
      oper.setName("startWorkflowFromTask");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "StartWorkflowFromTask"), (byte)1, new QName("http://www.informatica.com/wsh", "WorkflowRequest"), WorkflowRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "StartWorkflowFromTaskReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[11] = oper;
      oper = new OperationDesc();
      oper.setName("stopWorkflow");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "StopWorkflow"), (byte)1, new QName("http://www.informatica.com/wsh", "WorkflowRequest"), WorkflowRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "StopWorkflowReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[12] = oper;
      oper = new OperationDesc();
      oper.setName("scheduleWorkflow");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "ScheduleWorkflow"), (byte)1, new QName("http://www.informatica.com/wsh", "WorkflowRequest"), WorkflowRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "ScheduleWorkflowReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[13] = oper;
      oper = new OperationDesc();
      oper.setName("unscheduleWorkflow");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "UnscheduleWorkflow"), (byte)1, new QName("http://www.informatica.com/wsh", "WorkflowRequest"), WorkflowRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "UnscheduleWorkflowReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[14] = oper;
      oper = new OperationDesc();
      oper.setName("waitTillWorkflowComplete");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "WaitTillWorkflowComplete"), (byte)1, new QName("http://www.informatica.com/wsh", "WorkflowRequest"), WorkflowRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "WaitTillWorkflowCompleteReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[15] = oper;
      oper = new OperationDesc();
      oper.setName("resumeWorkflow");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "ResumeWorkflow"), (byte)1, new QName("http://www.informatica.com/wsh", "WorkflowRequest"), WorkflowRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "ResumeWorkflowReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[16] = oper;
      oper = new OperationDesc();
      oper.setName("startTask");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "StartTask"), (byte)1, new QName("http://www.informatica.com/wsh", "TaskRequest"), TaskRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "StartTaskReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[17] = oper;
      oper = new OperationDesc();
      oper.setName("stopTask");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "StopTask"), (byte)1, new QName("http://www.informatica.com/wsh", "TaskRequest"), TaskRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "StopTaskReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[18] = oper;
      oper = new OperationDesc();
      oper.setName("waitTillTaskComplete");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "WaitTillTaskComplete"), (byte)1, new QName("http://www.informatica.com/wsh", "TaskRequest"), TaskRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "WaitTillTaskCompleteReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[19] = oper;
   }

   private static void _initOperationDesc3() {
      OperationDesc oper = new OperationDesc();
      oper.setName("recoverWorkflow");
      ParameterDesc param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "RecoverWorkflow"), (byte)1, new QName("http://www.informatica.com/wsh", "WorkflowRequest"), WorkflowRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "VoidResponse"));
      oper.setReturnClass(VoidResponse.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "RecoverWorkflowReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[20] = oper;
      oper = new OperationDesc();
      oper.setName("monitorDIServer");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "MonitorDIServer"), (byte)1, new QName("http://www.informatica.com/wsh", "MonitorDIServerRequest"), MonitorDIServerRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "DIServerDetails"));
      oper.setReturnClass(DIServerDetails.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "MonitorDIServerReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[21] = oper;
      oper = new OperationDesc();
      oper.setName("getWorkflowDetails");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetWorkflowDetails"), (byte)1, new QName("http://www.informatica.com/wsh", "WorkflowRequest"), WorkflowRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "WorkflowDetails"));
      oper.setReturnClass(WorkflowDetails.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetWorkflowDetailsReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[22] = oper;
      oper = new OperationDesc();
      oper.setName("getWorkflowDetailsEx");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetWorkflowDetailsEx"), (byte)1, new QName("http://www.informatica.com/wsh", "TypeGetWorkflowDetailsExRequest"), TypeGetWorkflowDetailsExRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "DIServerDetails"));
      oper.setReturnClass(DIServerDetails.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetWorkflowDetailsExReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[23] = oper;
      oper = new OperationDesc();
      oper.setName("getTaskDetails");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetTaskDetails"), (byte)1, new QName("http://www.informatica.com/wsh", "TaskRequest"), TaskRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "TaskDetails"));
      oper.setReturnClass(TaskDetails.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetTaskDetailsReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[24] = oper;
      oper = new OperationDesc();
      oper.setName("getTaskDetailsEx");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetTaskDetailsEx"), (byte)1, new QName("http://www.informatica.com/wsh", "TypeGetTaskDetailsExRequest"), TypeGetTaskDetailsExRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "DIServerDetails"));
      oper.setReturnClass(DIServerDetails.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetTaskDetailsExReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[25] = oper;
      oper = new OperationDesc();
      oper.setName("getSessionStatistics");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetSessionStatistics"), (byte)1, new QName("http://www.informatica.com/wsh", "GetSessionStatisticsRequest"), GetSessionStatisticsRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "SessionStatistics"));
      oper.setReturnClass(SessionStatistics.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetSessionStatisticsReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[26] = oper;
      oper = new OperationDesc();
      oper.setName("getSessionPerformanceData");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetSessionPerformanceData"), (byte)1, new QName("http://www.informatica.com/wsh", "GetSessionPerformanceDataRequest"), GetSessionPerformanceDataRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "SessionPerformanceData"));
      oper.setReturnClass(SessionPerformanceData.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetSessionPerformanceDataReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[27] = oper;
      oper = new OperationDesc();
      oper.setName("getWorkflowLog");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetWorkflowLog"), (byte)1, new QName("http://www.informatica.com/wsh", "GetWorkflowLogRequest"), GetWorkflowLogRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "Log"));
      oper.setReturnClass(Log.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetWorkflowLogReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[28] = oper;
      oper = new OperationDesc();
      oper.setName("getSessionLog");
      param = new ParameterDesc(new QName("http://www.informatica.com/wsh", "GetSessionLog"), (byte)1, new QName("http://www.informatica.com/wsh", "GetSessionLogRequest"), GetSessionLogRequest.class, false, false);
      oper.addParameter(param);
      oper.setReturnType(new QName("http://www.informatica.com/wsh", "Log"));
      oper.setReturnClass(Log.class);
      oper.setReturnQName(new QName("http://www.informatica.com/wsh", "GetSessionLogReturn"));
      oper.setStyle(Style.DOCUMENT);
      oper.setUse(Use.LITERAL);
      oper.addFault(new FaultDesc(new QName("http://www.informatica.com/wsh", "WSHFaultDetails"), "com.meicloud.webservice.FaultDetails", new QName("http://www.informatica.com/wsh", "FaultDetails"), true));
      _operations[29] = oper;
   }

   public DataIntegrationServiceSoapBindingStub() throws AxisFault {
      this((Service)null);
   }

   public DataIntegrationServiceSoapBindingStub(URL endpointURL, Service service) throws AxisFault {
      this(service);
      super.cachedEndpoint = endpointURL;
   }

   public DataIntegrationServiceSoapBindingStub(Service service) throws AxisFault {
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
      QName qName = new QName("http://www.informatica.com/wsh", "Attribute");
      this.cachedSerQNames.add(qName);
      Class cls = Attribute.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "DIServerDate");
      this.cachedSerQNames.add(qName);
      cls = DIServerDate.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "DIServerDetails");
      this.cachedSerQNames.add(qName);
      cls = DIServerDetails.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "DIServerProperties");
      this.cachedSerQNames.add(qName);
      cls = DIServerProperties.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "DIServiceInfo");
      this.cachedSerQNames.add(qName);
      cls = DIServiceInfo.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "EDIServerMode");
      this.cachedSerQNames.add(qName);
      cls = EDIServerMode.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(enumsf);
      this.cachedDeserFactories.add(enumdf);
      qName = new QName("http://www.informatica.com/wsh", "EDIServerMonitorMode");
      this.cachedSerQNames.add(qName);
      cls = EDIServerMonitorMode.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(enumsf);
      this.cachedDeserFactories.add(enumdf);
      qName = new QName("http://www.informatica.com/wsh", "EDIServerRunStatus");
      this.cachedSerQNames.add(qName);
      cls = EDIServerRunStatus.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(enumsf);
      this.cachedDeserFactories.add(enumdf);
      qName = new QName("http://www.informatica.com/wsh", "EPerformanceCounterType");
      this.cachedSerQNames.add(qName);
      cls = EPerformanceCounterType.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(enumsf);
      this.cachedDeserFactories.add(enumdf);
      qName = new QName("http://www.informatica.com/wsh", "EPingState");
      this.cachedSerQNames.add(qName);
      cls = EPingState.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(enumsf);
      this.cachedDeserFactories.add(enumdf);
      qName = new QName("http://www.informatica.com/wsh", "ETaskRunMode");
      this.cachedSerQNames.add(qName);
      cls = ETaskRunMode.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(enumsf);
      this.cachedDeserFactories.add(enumdf);
      qName = new QName("http://www.informatica.com/wsh", "ETaskRunStatus");
      this.cachedSerQNames.add(qName);
      cls = ETaskRunStatus.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(enumsf);
      this.cachedDeserFactories.add(enumdf);
      qName = new QName("http://www.informatica.com/wsh", "ETaskType");
      this.cachedSerQNames.add(qName);
      cls = ETaskType.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(enumsf);
      this.cachedDeserFactories.add(enumdf);
      qName = new QName("http://www.informatica.com/wsh", "EWorkflowRunStatus");
      this.cachedSerQNames.add(qName);
      cls = EWorkflowRunStatus.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(enumsf);
      this.cachedDeserFactories.add(enumdf);
      qName = new QName("http://www.informatica.com/wsh", "EWorkflowRunType");
      this.cachedSerQNames.add(qName);
      cls = EWorkflowRunType.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(enumsf);
      this.cachedDeserFactories.add(enumdf);
      qName = new QName("http://www.informatica.com/wsh", "FaultDetails");
      this.cachedSerQNames.add(qName);
      cls = FaultDetails.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "GetNextLogSegmentRequest");
      this.cachedSerQNames.add(qName);
      cls = GetNextLogSegmentRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "GetSessionLogRequest");
      this.cachedSerQNames.add(qName);
      cls = GetSessionLogRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "GetSessionPerformanceDataRequest");
      this.cachedSerQNames.add(qName);
      cls = GetSessionPerformanceDataRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "GetSessionStatisticsRequest");
      this.cachedSerQNames.add(qName);
      cls = GetSessionStatisticsRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "GetWorkflowLogRequest");
      this.cachedSerQNames.add(qName);
      cls = GetWorkflowLogRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "InitializeDIServerConnectionRequest");
      this.cachedSerQNames.add(qName);
      cls = InitializeDIServerConnectionRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "Key");
      this.cachedSerQNames.add(qName);
      cls = Key.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "LinkDetails");
      this.cachedSerQNames.add(qName);
      cls = LinkDetails.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "Log");
      this.cachedSerQNames.add(qName);
      cls = Log.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "LoginRequest");
      this.cachedSerQNames.add(qName);
      cls = LoginRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "LogSegment");
      this.cachedSerQNames.add(qName);
      cls = LogSegment.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "MonitorDIServerRequest");
      this.cachedSerQNames.add(qName);
      cls = MonitorDIServerRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "Parameter");
      this.cachedSerQNames.add(qName);
      cls = Parameter.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "ParameterArray");
      this.cachedSerQNames.add(qName);
      cls = Parameter[].class;
      this.cachedSerClasses.add(cls);
      qName = new QName("http://www.informatica.com/wsh", "Parameter");
      QName qName2 = new QName("", "Parameters");
      this.cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
      this.cachedDeserFactories.add(new ArrayDeserializerFactory());
      qName = new QName("http://www.informatica.com/wsh", "PerformanceCounter");
      this.cachedSerQNames.add(qName);
      cls = PerformanceCounter.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "PingDIServerRequest");
      this.cachedSerQNames.add(qName);
      cls = PingDIServerRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "SessionPerformanceData");
      this.cachedSerQNames.add(qName);
      cls = SessionPerformanceData.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "SessionStatistics");
      this.cachedSerQNames.add(qName);
      cls = SessionStatistics.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "StartSessionLogFetchRequest");
      this.cachedSerQNames.add(qName);
      cls = StartSessionLogFetchRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "StartWorkflowLogFetchRequest");
      this.cachedSerQNames.add(qName);
      cls = StartWorkflowLogFetchRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "TableStatistics");
      this.cachedSerQNames.add(qName);
      cls = TableStatistics.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "TaskDetails");
      this.cachedSerQNames.add(qName);
      cls = TaskDetails.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "TaskRequest");
      this.cachedSerQNames.add(qName);
      cls = TaskRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "TypeGetTaskDetailsExRequest");
      this.cachedSerQNames.add(qName);
      cls = TypeGetTaskDetailsExRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "TypeGetWorkflowDetailsExRequest");
      this.cachedSerQNames.add(qName);
      cls = TypeGetWorkflowDetailsExRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "TypeStartWorkflowExRequest");
      this.cachedSerQNames.add(qName);
      cls = TypeStartWorkflowExRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "TypeStartWorkflowExResponse");
      this.cachedSerQNames.add(qName);
      cls = TypeStartWorkflowExResponse.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
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
      qName = new QName("http://www.informatica.com/wsh", "WorkflowDetails");
      this.cachedSerQNames.add(qName);
      cls = WorkflowDetails.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
      qName = new QName("http://www.informatica.com/wsh", "WorkflowRequest");
      this.cachedSerQNames.add(qName);
      cls = WorkflowRequest.class;
      this.cachedSerClasses.add(cls);
      this.cachedSerFactories.add(beansf);
      this.cachedDeserFactories.add(beandf);
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

   public VoidResponse initializeDIServerConnection(InitializeDIServerConnectionRequest param) throws RemoteException, FaultDetails {
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
         _call.setOperationName(new QName("", "initializeDIServerConnection"));
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

   public VoidResponse deinitializeDIServerConnection(VoidRequest param) throws RemoteException, FaultDetails {
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
         _call.setOperationName(new QName("", "deinitializeDIServerConnection"));
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

   public int startWorkflowLogFetch(StartWorkflowLogFetchRequest param) throws RemoteException, FaultDetails {
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
         _call.setOperationName(new QName("", "startWorkflowLogFetch"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{param});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return ((Integer)axisFaultException).intValue();
               } catch (Exception var5) {
                  return ((Integer)JavaUtils.convert(axisFaultException, Integer.TYPE)).intValue();
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

   public int startSessionLogFetch(StartSessionLogFetchRequest param) throws RemoteException, FaultDetails {
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
         _call.setOperationName(new QName("", "startSessionLogFetch"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{param});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return ((Integer)axisFaultException).intValue();
               } catch (Exception var5) {
                  return ((Integer)JavaUtils.convert(axisFaultException, Integer.TYPE)).intValue();
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

   public LogSegment getNextLogSegment(GetNextLogSegmentRequest param) throws RemoteException, FaultDetails {
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
         _call.setOperationName(new QName("", "getNextLogSegment"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{param});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (LogSegment)axisFaultException;
               } catch (Exception var5) {
                  return (LogSegment)JavaUtils.convert(axisFaultException, LogSegment.class);
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

   public EPingState pingDIServer(PingDIServerRequest pingDIServer) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[7]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "pingDIServer"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{pingDIServer});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (EPingState)axisFaultException;
               } catch (Exception var5) {
                  return (EPingState)JavaUtils.convert(axisFaultException, EPingState.class);
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

   public DIServerProperties getDIServerProperties(DIServiceInfo getDIServerProperties) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[8]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getDIServerProperties"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{getDIServerProperties});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (DIServerProperties)axisFaultException;
               } catch (Exception var5) {
                  return (DIServerProperties)JavaUtils.convert(axisFaultException, DIServerProperties.class);
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

   public VoidResponse startWorkflow(WorkflowRequest startWorkflow) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[9]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "startWorkflow"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{startWorkflow});
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

   public TypeStartWorkflowExResponse startWorkflowEx(TypeStartWorkflowExRequest startWorkflowEx) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[10]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "startWorkflowEx"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{startWorkflowEx});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (TypeStartWorkflowExResponse)axisFaultException;
               } catch (Exception var5) {
                  return (TypeStartWorkflowExResponse)JavaUtils.convert(axisFaultException, TypeStartWorkflowExResponse.class);
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

   public VoidResponse startWorkflowFromTask(WorkflowRequest startWorkflowFromTask) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[11]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "startWorkflowFromTask"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{startWorkflowFromTask});
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

   public VoidResponse stopWorkflow(WorkflowRequest stopWorkflow) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[12]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "stopWorkflow"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{stopWorkflow});
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

   public VoidResponse scheduleWorkflow(WorkflowRequest scheduleWorkflow) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[13]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "scheduleWorkflow"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{scheduleWorkflow});
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

   public VoidResponse unscheduleWorkflow(WorkflowRequest unscheduleWorkflow) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[14]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "unscheduleWorkflow"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{unscheduleWorkflow});
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

   public VoidResponse waitTillWorkflowComplete(WorkflowRequest waitTillWorkflowComplete) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[15]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "waitTillWorkflowComplete"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{waitTillWorkflowComplete});
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

   public VoidResponse resumeWorkflow(WorkflowRequest resumeWorkflow) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[16]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "resumeWorkflow"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{resumeWorkflow});
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

   public VoidResponse startTask(TaskRequest startTask) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[17]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "startTask"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{startTask});
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

   public VoidResponse stopTask(TaskRequest stopTask) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[18]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "stopTask"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{stopTask});
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

   public VoidResponse waitTillTaskComplete(TaskRequest waitTillTaskComplete) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[19]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "waitTillTaskComplete"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{waitTillTaskComplete});
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

   public VoidResponse recoverWorkflow(WorkflowRequest recoverWorkflow) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[20]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "recoverWorkflow"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{recoverWorkflow});
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

   public DIServerDetails monitorDIServer(MonitorDIServerRequest monitorDIServer) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[21]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "monitorDIServer"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{monitorDIServer});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (DIServerDetails)axisFaultException;
               } catch (Exception var5) {
                  return (DIServerDetails)JavaUtils.convert(axisFaultException, DIServerDetails.class);
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

   public WorkflowDetails getWorkflowDetails(WorkflowRequest getWorkflowDetails) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[22]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getWorkflowDetails"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{getWorkflowDetails});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (WorkflowDetails)axisFaultException;
               } catch (Exception var5) {
                  return (WorkflowDetails)JavaUtils.convert(axisFaultException, WorkflowDetails.class);
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

   public DIServerDetails getWorkflowDetailsEx(TypeGetWorkflowDetailsExRequest getWorkflowDetailsEx) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[23]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getWorkflowDetailsEx"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{getWorkflowDetailsEx});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (DIServerDetails)axisFaultException;
               } catch (Exception var5) {
                  return (DIServerDetails)JavaUtils.convert(axisFaultException, DIServerDetails.class);
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

   public TaskDetails getTaskDetails(TaskRequest getTaskDetails) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[24]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getTaskDetails"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{getTaskDetails});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (TaskDetails)axisFaultException;
               } catch (Exception var5) {
                  return (TaskDetails)JavaUtils.convert(axisFaultException, TaskDetails.class);
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

   public DIServerDetails getTaskDetailsEx(TypeGetTaskDetailsExRequest getTaskDetailsEx) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[25]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getTaskDetailsEx"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{getTaskDetailsEx});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (DIServerDetails)axisFaultException;
               } catch (Exception var5) {
                  return (DIServerDetails)JavaUtils.convert(axisFaultException, DIServerDetails.class);
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

   public SessionStatistics getSessionStatistics(GetSessionStatisticsRequest getSessionStatistics) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[26]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getSessionStatistics"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{getSessionStatistics});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (SessionStatistics)axisFaultException;
               } catch (Exception var5) {
                  return (SessionStatistics)JavaUtils.convert(axisFaultException, SessionStatistics.class);
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

   public SessionPerformanceData getSessionPerformanceData(GetSessionPerformanceDataRequest getSessionPerformanceData) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[27]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getSessionPerformanceData"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{getSessionPerformanceData});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (SessionPerformanceData)axisFaultException;
               } catch (Exception var5) {
                  return (SessionPerformanceData)JavaUtils.convert(axisFaultException, SessionPerformanceData.class);
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

   public Log getWorkflowLog(GetWorkflowLogRequest getWorkflowLog) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[28]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getWorkflowLog"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{getWorkflowLog});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (Log)axisFaultException;
               } catch (Exception var5) {
                  return (Log)JavaUtils.convert(axisFaultException, Log.class);
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

   public Log getSessionLog(GetSessionLogRequest getSessionLog) throws RemoteException, FaultDetails {
      if(super.cachedEndpoint == null) {
         throw new NoEndPointException();
      } else {
         Call _call = this.createCall();
         _call.setOperation(_operations[29]);
         _call.setUseSOAPAction(true);
         _call.setSOAPActionURI("");
         _call.setEncodingStyle((String)null);
         _call.setProperty("sendXsiTypes", Boolean.FALSE);
         _call.setProperty("sendMultiRefs", Boolean.FALSE);
         _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
         _call.setOperationName(new QName("", "getSessionLog"));
         this.setRequestHeaders(_call);
         this.setAttachments(_call);

         try {
            Object axisFaultException = _call.invoke(new Object[]{getSessionLog});
            if(axisFaultException instanceof RemoteException) {
               throw (RemoteException)axisFaultException;
            } else {
               this.extractAttachments(_call);

               try {
                  return (Log)axisFaultException;
               } catch (Exception var5) {
                  return (Log)JavaUtils.convert(axisFaultException, Log.class);
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
