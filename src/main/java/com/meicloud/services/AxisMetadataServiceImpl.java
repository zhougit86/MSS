package com.meicloud.services;

import com.meicloud.dto.TreeInfo;
import com.meicloud.services.AxisMetadataService;
import com.meicloud.webservice.FolderInfo;
import com.meicloud.webservice.LoginRequest;
import com.meicloud.webservice.MetadataInterface;
import com.meicloud.webservice.MetadataServiceLocator;
import com.meicloud.webservice.SessionHeader;
import com.meicloud.webservice.VoidRequest;
import com.meicloud.webservice.WorkflowInfo;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.axis.client.Stub;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service("axisMetadataService")
public class AxisMetadataServiceImpl extends SessionHeader implements AxisMetadataService {

   private static Logger LOG = Logger.getLogger(AxisMetadataServiceImpl.class);
   @Autowired
   private Environment environment;


   public List getWorkflowInfos() throws Exception {
      ArrayList treeInfos = new ArrayList();
      TreeInfo dto = new TreeInfo();
      dto.setId("T");
      dto.setName("工作流");
      dto.setpId("");
      dto.setType("0");
      dto.setExport("0");
      treeInfos.add(dto);
      LoginRequest loginReq = this.createLoginRequest();
      MetadataServiceLocator metaService = new MetadataServiceLocator();
      MetadataInterface MetadataProxy = metaService.getMetadata(new URL(this.environment.getProperty("webservices.metadata.url")));
      String sessionID_m = MetadataProxy.login(loginReq);
      LOG.info(sessionID_m);
      ((Stub)MetadataProxy).setHeader(this.createSessionHeader(sessionID_m));
      FolderInfo[] list = MetadataProxy.getAllFolders((VoidRequest)null);

      for(int i = 0; i < list.length; ++i) {
         FolderInfo fi = list[i];
         TreeInfo folderInfoTree = new TreeInfo();
         folderInfoTree.setId(fi.getName());
         folderInfoTree.setName(fi.getName());
         folderInfoTree.setType("D");
         folderInfoTree.setpId("T");
         folderInfoTree.setExport("0");
         treeInfos.add(folderInfoTree);
         LOG.info(fi.getName());
         WorkflowInfo[] workflowInfo = MetadataProxy.getAllWorkflows(fi);

         for(int j = 0; j < workflowInfo.length; ++j) {
            WorkflowInfo wfi = workflowInfo[j];
            TreeInfo workflowInfoTree = new TreeInfo();
            workflowInfoTree.setId(wfi.getName());
            workflowInfoTree.setName(wfi.getName());
            workflowInfoTree.setType("F");
            workflowInfoTree.setpId(fi.getName());
            workflowInfoTree.setExport("0");
            treeInfos.add(workflowInfoTree);
            LOG.info("--" + wfi.getName());
         }
      }

      MetadataProxy.logout((VoidRequest)null);
      return treeInfos;
   }
}
