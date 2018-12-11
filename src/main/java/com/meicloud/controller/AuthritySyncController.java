package com.meicloud.controller;

import com.meicloud.controller.BasicController;
import com.meicloud.services.AuthritySyncService;
import com.meicloud.utils.Base64Util;
import com.meicloud.utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"authrity"})
public class AuthritySyncController extends BasicController {

   private static Logger logger = LoggerFactory.getLogger(AuthritySyncController.class);
   @Autowired
   private AuthritySyncService authritySyncService;


   @ApiOperation(
      value = "authrity系统权限同步接收",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/sync"},
      method = {RequestMethod.POST, RequestMethod.GET}
   )
   public Object getUserInfo(String jsonStr) {
      logger.info("------------->/authrity/sync authrity系统权限同步接收:" + jsonStr);
      jsonStr = jsonStr.replaceAll(" ", "+");
      jsonStr = jsonStr.replaceAll("\\n", "");
      String jsonStr_N = Base64Util.jdkBase64Decoder(jsonStr);
      logger.info("------------->接收报文（解密后的报文）：" + Base64Util.jdkBase64Decoder(jsonStr));

      try {
         if(Utils.isEmptyStr(jsonStr_N)) {
            return this.outBound("权限同步成功!");
         } else {
            JSONObject e = new JSONObject(jsonStr_N);
            this.authritySyncService.saveRole(e);
            this.authritySyncService.saveUser(e);
            this.authritySyncService.saveUserRole(e);
            return this.outBound("权限同步成功!");
         }
      } catch (Exception var4) {
         logger.error("------------->/authrity/sync authrity系统权限同步接收异常" + var4.getMessage());
         return this.errorHandler("authrity系统权限同步接收异常");
      }
   }
}
