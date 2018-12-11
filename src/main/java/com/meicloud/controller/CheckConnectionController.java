package com.meicloud.controller;

import com.meicloud.utils.WebUtil;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckConnectionController {

   private static Logger LOG = Logger.getLogger(CheckConnectionController.class);


   @RequestMapping({"check/connection"})
   public String configs(HttpServletResponse response) {
      String json = "\"connectionStatus\":\"true\"";
      WebUtil.writeJson(response, json);
      LOG.info(json);
      return null;
   }
}
