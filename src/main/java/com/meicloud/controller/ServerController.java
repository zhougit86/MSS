package com.meicloud.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meicloud.Common;
import com.meicloud.controller.BasicController;
import com.meicloud.dto.DicVO;
import com.meicloud.enums.LengthLimit;
import com.meicloud.model.MonitorInfoBean;
import com.meicloud.model.Queue;
import com.meicloud.model.Server;
import com.meicloud.services.IndexConfigService;
import com.meicloud.services.QueueService;
import com.meicloud.services.ServerService;
import com.meicloud.utils.HttpUtil;
import com.meicloud.utils.SystemInfo;
import com.meicloud.utils.Utils;
import com.meicloud.utils.WebUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"server"})
public class ServerController extends BasicController {

   private static Logger logger = LoggerFactory.getLogger(ServerController.class);
   @Autowired
   private ServerService serverService;
   @Autowired
   private IndexConfigService indexConfigService;
   @Autowired
   private QueueService queueService;


   @ApiOperation(
      value = "服务器列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/list"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object list(
      @ApiParam(
         value = "页数",
         required = true
      ) @RequestParam String pagenum, 
      @ApiParam(
         value = "行数",
         required = true
      ) @RequestParam String rownum) {
      logger.info("------------->/server/list 服务器列表");

      try {
         PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum));
         List e = this.serverService.getServerList((Server)null);
         if(!Utils.isEmpityCollection(e)) {
            Iterator var5 = e.iterator();

            while(var5.hasNext()) {
               Server page = (Server)var5.next();
               if(page.getType().equals("PORTAL")) {
                  page.setType_desc("分发服务器");
               }

               if(page.getType().equals("ES")) {
                  page.setType_desc("执行服务器");
               }

               if(page.getType().equals(Common.SERVER_XY)) {
                  page.setType_desc("综合服务器");
               }
            }
         }

         PageInfo page1 = new PageInfo(e);
         return this.outBound(this.getDataInfo(new Server(), page1.getList(), Long.valueOf(page1.getTotal())));
      } catch (Exception var6) {
         logger.error("------------->/server/list 接口异常" + var6.getMessage());
         return this.errorHandler("服务器列表查询异常:" + var6.getMessage());
      }
   }

   @ApiOperation(
      value = "根据集群获取服务器列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/getServerlistByQueueId"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object getServerlistByQueueId(@RequestParam String queueId) {
      try {
         Server e = new Server();
         e.setState(0);
         e.setType("ES");
         e.setQueueId(queueId);
         List servers = this.serverService.getServerList(e);
         return this.outBound(servers);
      } catch (Exception var4) {
         logger.error("------------->/server/getServerlistByQueueId 接口异常" + var4.getMessage());
         return this.errorHandler("根据集群获取服务器列表查询异常:" + var4.getMessage());
      }
   }

   @ApiOperation(
      value = "根据集群获取服务器列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/getServerByQueueIdAndMinJob"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object getServerByQueueIdAndMinJob(@RequestParam String queueId) {
      try {
         Server e = new Server();
         e.setQueueId(queueId);
         Server server1 = this.serverService.getServerByQueueIdAndMinJob(e);
         Server server = this.serverService.getServerById(server1.getId().intValue());
         return this.outBound(server);
      } catch (Exception var5) {
         logger.error("------------->/server/getServerlistByQueueId 接口异常" + var5.getMessage());
         return this.errorHandler("根据集群获取服务器列表查询异常:" + var5.getMessage());
      }
   }

   @ApiOperation(
      value = "服务器类型接口",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/type"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object type() {
      logger.info("------------->/server/type 服务器类型列表");

      try {
         ArrayList e = new ArrayList();
         DicVO vo2 = new DicVO();
         vo2.setKey(Common.SERVER_XY);
         vo2.setName("综合服务器");
         e.add(vo2);
         DicVO vo = new DicVO();
         vo.setKey("PORTAL");
         vo.setName("分发服务器");
         e.add(vo);
         DicVO vo1 = new DicVO();
         vo1.setKey("ES");
         vo1.setName("执行服务器");
         e.add(vo1);
         return this.outBound(e);
      } catch (Exception var5) {
         logger.error("------------->/server/type 接口异常" + var5.getMessage());
         return this.errorHandler("服务器类型列表查询异常:" + var5.getMessage());
      }
   }

   @ApiOperation(
      value = "所有服务器列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/allList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object allList() {
      logger.info("------------->/server/list 服务器列表");

      try {
         List e = this.serverService.getServerList((Server)null);
         return this.outBound(e);
      } catch (Exception var2) {
         logger.error("------------->/server/list 接口异常" + var2.getMessage());
         return this.errorHandler("服务器列表查询异常:" + var2.getMessage());
      }
   }

   @ApiOperation(
      value = "服务器主机详情",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/detail"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object detail(
      @ApiParam(
         value = "主机Id",
         required = true
      ) @RequestParam Integer id) {
      logger.info("------------->/server/detail 服务器详情");

      try {
         Server e = this.serverService.getServerById(id.intValue());
         if(e.getType().equals("PORTAL")) {
            e.setType_desc("分发服务器");
         }

         if(e.getType().equals("ES")) {
            e.setType_desc("执行服务器");
         }

         if(e.getType().equals(Common.SERVER_XY)) {
            e.setType_desc("综合服务器");
         }

         return this.outBound(e);
      } catch (Exception var3) {
         logger.error("------------->/server/detail 接口异常" + var3.getMessage());
         return this.errorHandler("服务器详情查询异常:" + var3.getMessage());
      }
   }

   @ApiOperation(
      value = "服务器保存",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/save"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object save(@RequestBody Server server) {
      logger.info("------------->/server/save 服务器保存");

      try {
         if(server.getAppPort().equals(server.getLogAppPort())) {
            return this.errorHandler("保存服务器信息失败,相同IP不允许存在相同端口【端口：" + server.getAppPort() + "】");
         } else if(server.getName().length() > Integer.parseInt(LengthLimit.Server_name.toString())) {
            return this.errorHandler("服务器部署项目名过长！");
         } else {
            String e = server.getDesc();
            if(e != null && !"".equals(e) && e.length() > Integer.parseInt(LengthLimit.Server_desc.toString())) {
               return this.errorHandler("主机描述过长！");
            } else if(server.getIp().length() > Integer.parseInt(LengthLimit.Server_ip.toString())) {
               return this.errorHandler("主机IP过长！");
            } else if(server.getAppPort().length() > Integer.parseInt(LengthLimit.Server_appPort.toString())) {
               return this.errorHandler("端口号过长！");
            } else if(server.getLogAppPort().length() > Integer.parseInt(LengthLimit.Server_logAppPort.toString())) {
               return this.errorHandler("日志服务器端口过长！");
            } else {
               String logPath = server.getSvnLogPath();
               if(logPath != null && !"".equals(logPath) && logPath.length() > Integer.parseInt(LengthLimit.Server_svnLogPath.toString())) {
                  return this.errorHandler("svn执行脚本存储目录过长！");
               } else {
                  String queryServer;
                  int servers;
                  if(server.getAppPort() != null && !"".equals(server.getAppPort())) {
                     queryServer = server.getAppPort();
                     servers = queryServer.length();

                     while(true) {
                        --servers;
                        if(servers < 0) {
                           break;
                        }

                        if(!Character.isDigit(queryServer.charAt(servers))) {
                           return this.errorHandler("端口号只能由数字组成！");
                        }
                     }
                  }

                  if(server.getLogAppPort() != null && !"".equals(server.getLogAppPort())) {
                     queryServer = server.getLogAppPort();
                     servers = queryServer.length();

                     while(true) {
                        --servers;
                        if(servers < 0) {
                           break;
                        }

                        if(!Character.isDigit(queryServer.charAt(servers))) {
                           return this.errorHandler("日志服务器端口号只能由数字组成！");
                        }
                     }
                  }

                  Server var10 = new Server();
                  var10.setIp(server.getIp());
                  var10.setAppPort(server.getAppPort());
                  List var11 = this.serverService.getServerListByPort(var10);
                  List servers1 = null;
                  Server server2;
                  if(server.getLogAppPort() != null && !"".equals(server.getLogAppPort())) {
                     server2 = new Server();
                     server2.setIp(server.getIp());
                     server2.setAppPort(server.getLogAppPort());
                     servers1 = this.serverService.getServerListByPort(server2);
                  }

                  if(server.getQueueId() != null && !server.getQueueId().equals("")) {
                     Queue var12 = this.queueService.findOne(server.getQueueId());
                     server.setQueueName(var12.getQueueName());
                  }

                  if(server.getId() == null) {
                     if(var11 != null && var11.size() > 0) {
                        logger.error("------------->/server/save 保存服务器信息失败,相同IP不允许存在相同端口");
                        return this.errorHandler("保存服务器信息失败,相同IP不允许存在相同端口【端口：" + server.getAppPort() + "】");
                     }

                     if(servers1 != null && servers1.size() > 0) {
                        logger.error("------------->/server/save 保存服务器信息失败,相同IP不允许存在相同端口【端口：" + server.getLogAppPort() + "】");
                        return this.errorHandler("保存服务器信息失败,相同IP不允许存在相同端口【端口：" + server.getLogAppPort() + "】");
                     }

                     this.serverService.add(server);
                  } else {
                     Iterator var8;
                     if(var11 != null && var11.size() > 0) {
                        var8 = var11.iterator();

                        while(var8.hasNext()) {
                           server2 = (Server)var8.next();
                           if(!server2.getId().equals(server.getId())) {
                              logger.error("------------->/server/save 保存服务器信息失败,相同IP不允许存在相同端口");
                              return this.errorHandler("保存服务器信息失败,相同IP不允许存在相同端口【端口：" + server.getAppPort() + "】");
                           }
                        }
                     }

                     if(servers1 != null && servers1.size() > 0) {
                        var8 = servers1.iterator();

                        while(var8.hasNext()) {
                           server2 = (Server)var8.next();
                           if(!server2.getId().equals(server.getId())) {
                              logger.error("------------->/server/save 保存服务器信息失败,相同IP不允许存在相同端口【端口：" + server.getLogAppPort() + "】");
                              return this.errorHandler("保存服务器信息失败,相同IP不允许存在相同端口【端口：" + server.getLogAppPort() + "】");
                           }
                        }
                     }

                     this.serverService.update(server);
                  }

                  return this.outBound("服务器保存成功!");
               }
            }
         }
      } catch (Exception var9) {
         logger.error("------------->/server/save 接口异常" + var9.getMessage());
         return this.errorHandler("服务器保存异常:" + var9.getMessage());
      }
   }

   @ApiOperation(
      value = "服务器删除",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/delete"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object delete(int id) {
      logger.info("------------->/sever/delete 服务器删除");

      try {
         this.serverService.delete(id);
         return this.outBound("服务器删除成功!");
      } catch (Exception var3) {
         logger.error("------------->/server/delete 服务器删除接口异常" + var3.getMessage());
         return this.errorHandler("服务器删除异常:" + var3.getMessage());
      }
   }

   @ApiOperation(
      value = "服务器配置信息",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/queryConfigInfo"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object queryConfigInfo(Integer serverId, HttpServletResponse response) {
      logger.info("------------->/server/queryConfigInfo 服务器配置信息");
      if(Utils.isEmptyStr((Object)serverId)) {
         return this.errorHandler("服务器id不能为空!");
      } else {
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html; charset=UTF-8");
         String top = "http://";
         String url_monitorInfo = "";

         try {
            HashMap e = new HashMap();
            Server server = this.indexConfigService.queryCmServer(serverId.intValue());
            url_monitorInfo = top + server.getIp() + ":" + server.getAppPort() + "/" + server.getName() + "/server/monitorInfo.do";
            String str_monitorInfo = HttpUtil.getForm(url_monitorInfo, e);
            MonitorInfoBean monitorInfo = (MonitorInfoBean)JSONArray.parseObject(str_monitorInfo, MonitorInfoBean.class);
            return this.outBound(monitorInfo);
         } catch (Exception var9) {
            logger.error("------------->/server/queryConfigInfo 接口异常" + var9.getMessage());
            return this.errorHandler("服务器配置信息查询异常:" + var9.getMessage());
         }
      }
   }

   @RequestMapping(
      value = {"/monitorInfo"},
      method = {RequestMethod.POST, RequestMethod.GET}
   )
   public String monitorInfo(HttpServletResponse response) {
      try {
         MonitorInfoBean e = SystemInfo.getMonitorInfoBean();
         String json = JSONArray.toJSONString(e);
         logger.info(json);
         WebUtil.writeJson(response, json);
         return null;
      } catch (Exception var4) {
         logger.error(var4.getMessage());
         return null;
      }
   }
}
