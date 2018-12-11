package com.meicloud.controller;

import com.meicloud.controller.BasicController;
import com.meicloud.services.AxisMetadataService;
import com.meicloud.utils.FileUtil;
import com.meicloud.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"svn"})
public class SvnController extends BasicController {

   private static Logger LOG = LoggerFactory.getLogger(SvnController.class);
   @Autowired
   private AxisMetadataService axisMetadataService;


   @ApiOperation(
      value = "获取SVN树目录信息(文件查询在前端处理)",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/getSvnTree"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object getSvnTree(@RequestParam String jobType) {
      try {
         if(!StringUtil.isBlank(jobType) && jobType.equalsIgnoreCase("informatica")) {
            List e = this.axisMetadataService.getWorkflowInfos();
            return this.outBound(e);
         } else {
            return this.outBound(FileUtil.svnTree);
         }
      } catch (Exception var3) {
         LOG.error("------------->/svn/getSvnTree 接口异常" + var3.getMessage());
         return this.errorHandler("获取目录信息查询异常:" + var3.getMessage());
      }
   }
}
