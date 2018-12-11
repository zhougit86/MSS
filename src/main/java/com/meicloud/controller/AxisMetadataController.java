package com.meicloud.controller;

import com.meicloud.controller.BasicController;
import com.meicloud.services.AxisMetadataService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"axisMetadata"})
public class AxisMetadataController extends BasicController {

   private static Logger logger = LoggerFactory.getLogger(AxisMetadataController.class);
   @Autowired
   private AxisMetadataService axisMetadataService;


   @ApiOperation(
      value = "工作流信息树",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/workflowTree"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object workflowTree() {
      try {
         List e = this.axisMetadataService.getWorkflowInfos();
         return this.outBound(e);
      } catch (Exception var2) {
         logger.error("------------->/axisMetadata/workflowTree 接口异常" + var2.getMessage());
         return this.errorHandler("工作流信息树查询异常:" + var2.getMessage());
      }
   }
}
