package com.meicloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meicloud.controller.BasicController;
import com.meicloud.dto.UserVO;
import com.meicloud.enums.LengthLimit;
import com.meicloud.model.Account;
import com.meicloud.model.Perm;
import com.meicloud.model.Role;
import com.meicloud.model.RolePerm;
import com.meicloud.services.AccountService;
import com.meicloud.services.PermService;
import com.meicloud.services.RolePermService;
import com.meicloud.services.RoleService;
import com.meicloud.utils.Utils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"account"})
public class AccountController extends BasicController {

   private static Logger log = LoggerFactory.getLogger(AccountController.class);
   @Autowired
   private AccountService accountService;
   @Resource
   private RoleService roleService;
   @Resource
   private RolePermService rolePermService;
   @Resource
   private PermService permService;
   @Value("${SECURITY_ADMIN_USERS}")
   private String securityAdminUsers;
   @Autowired
   private Environment environment;


   @ApiOperation(
      value = "账户列表",
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
      ) @RequestParam String rownum, 
      @ApiParam(
         value = "账号或姓名",
         required = false
      ) 
      @RequestParam(
         required = false
      ) String queryKey) {
      log.info("------------->/account/list 账户列表");

      try {
         PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum));
         List e = this.accountService.list(queryKey);
         PageInfo page = new PageInfo(e);
         return this.outBound(this.getDataInfo(new Account(), page.getList(), Long.valueOf(page.getTotal())));
      } catch (Exception var6) {
         log.error("------------->/account/list 接口异常" + var6.getMessage());
         return this.errorHandler("账户列表查询异常:" + var6.getMessage());
      }
   }

   @ApiOperation(
      value = "账户详情",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/detail"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object detail(
      @ApiParam(
         value = "用户账号",
         required = true
      ) @RequestParam String accounNo) {
      log.info("------------->/account/detail 账户详情");

      try {
         Account e = new Account();
         e.setAccount(accounNo);
         Account account = this.accountService.queryAccount(e);
         return account == null?this.errorHandler("账号不存在"):this.outBound(account);
      } catch (Exception var4) {
         log.error("------------->/account/detail 接口异常" + var4.getMessage());
         return this.errorHandler("账户详情查询异常:" + var4.getMessage());
      }
   }

   @RequestMapping(
      value = {"/save"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "账号新增",
      notes = "返回json"
   )
   @ResponseBody
   public Object save(@RequestBody Account account) {
      log.info("------------->/account/save 用户账号新增");

      try {
         if(account.getAccount().length() > Integer.parseInt(LengthLimit.Account_account.toString())) {
            return this.errorHandler("用户账号过长！");
         } else if(account.getRealName().length() > Integer.parseInt(LengthLimit.Account_realName.toString())) {
            return this.errorHandler("用户姓名过长！");
         } else {
            Account e = this.accountService.getByAccount(account.getAccount());
            if(e != null) {
               return this.errorHandler("用户账号已存在!:");
            } else {
               this.accountService.add(account);
               return this.outBound("用户账号新增成功");
            }
         }
      } catch (Exception var3) {
         log.error("------------->/account/save 接口异常" + var3.getMessage());
         return this.errorHandler("用户账号新增异常:" + var3.getMessage());
      }
   }

   @RequestMapping(
      value = {"/update"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "账号修改",
      notes = "返回json"
   )
   @ResponseBody
   public Object update(@RequestBody Account account, HttpServletRequest request) {
      log.info("------------->/account/update 用户账号修改");

      try {
         if(account.getAccount().length() > LengthLimit.Account_account.ordinal()) {
            return this.errorHandler("用户账号过长");
         } else if(account.getRealName().length() > LengthLimit.Account_realName.ordinal()) {
            return this.errorHandler("用户姓名过长");
         } else {
            this.accountService.update(account);
            return this.outBound("用户账号修改成功");
         }
      } catch (Exception var4) {
         log.error("------------->/account/update 接口异常" + var4.getMessage());
         return this.errorHandler("用户账号修改异常:" + var4.getMessage());
      }
   }

   @RequestMapping(
      value = {"/updateState"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "账号状态更改",
      notes = "返回json"
   )
   @ResponseBody
   public Object updateState(
      @ApiParam(
         value = "用户账号",
         required = true
      ) @RequestParam String account) {
      log.info("------------->/account/updateState 用户账号状态修改");

      try {
         HashMap e = new HashMap();
         e.put("account", account);
         this.accountService.updateEnable(e);
         return this.outBound("用户账号状态修改成功");
      } catch (Exception var3) {
         log.error("------------->/account/updateState 接口异常" + var3.getMessage());
         return this.errorHandler("用户账号状态修改异常:" + var3.getMessage());
      }
   }

   @ApiOperation(
      value = "获取用户登录信息",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/getUserInfo"},
      method = {RequestMethod.POST, RequestMethod.GET}
   )
   @ResponseBody
   public Object getUserInfo(HttpServletRequest request) {
      try {
         UserVO e = new UserVO();
         Account account = (Account)request.getSession().getAttribute("user");
         e.setUserName(account.getRealName());
         e.setUserAccount(account.getAccount());
         e.setSvnOn(account.isSvnOn());
         request.getSession().setAttribute("user", account);
         this.getRoleList(e, request);
         if(request.getSession().getAttribute("ifAdmin") == null) {
            request.getSession().setAttribute("ifAdmin", Boolean.FALSE);
            if(!Utils.isEmpityCollection(e.getRoleList())) {
               Iterator permMap = e.getRoleList().iterator();

               while(permMap.hasNext()) {
                  Role usersList = (Role)permMap.next();
                  if(usersList.getRoleCode().equalsIgnoreCase("ADMIN")) {
                     request.getSession().setAttribute("ifAdmin", Boolean.TRUE);
                     break;
                  }
               }
            }
         }

         List usersList1 = Arrays.asList(this.securityAdminUsers.split(","));
         if(usersList1 != null && usersList1.contains(account.getAccount())) {
            e.setIfAdmin(Boolean.TRUE.booleanValue());
         } else {
            e.setIfAdmin(((Boolean)request.getSession().getAttribute("ifAdmin")).booleanValue());
         }

         Map permMap1 = this.getPerm(e, request);
         String webTitle;
         if(!Utils.isEmpityMap(permMap1) && account.isEnable()) {
            ArrayList parseObject = new ArrayList();
            Iterator copyRight = permMap1.keySet().iterator();

            while(copyRight.hasNext()) {
               webTitle = (String)copyRight.next();
               parseObject.add(webTitle);
            }

            e.setPermList(parseObject);
         }

         JSONObject parseObject1 = JSON.parseObject(JSON.toJSONString(e));
         webTitle = this.environment.getProperty("app.web.title");
         String copyRight1 = this.environment.getProperty("app.copy.right");
         String appVersion = "4.2.5";
         parseObject1.put("title", StringUtils.isEmpty(webTitle)?"调度平台":new String(webTitle.getBytes("iso-8859-1"), "utf-8"));
         parseObject1.put("copyRight", StringUtils.isEmpty(copyRight1)?"深圳美云智数科技有限公司":new String(copyRight1.getBytes("iso-8859-1"), "utf-8"));
         parseObject1.put("appVersion", appVersion);
         return this.outBound(parseObject1.toString());
      } catch (Exception var10) {
         log.error("------------->/account/getUserInfo 接口异常" + var10.getMessage());
         return this.errorHandler("获取用户登录信息失败");
      }
   }

   private void getRoleList(UserVO userVo, HttpServletRequest request) {
      Object roleList = null;
      if(request.getSession().getAttribute("roles") == null) {
         roleList = this.roleService.findListByUser(userVo.getUserAccount());
         if(roleList == null) {
            roleList = new ArrayList();
         }

         request.getSession().setAttribute("roles", roleList);
      } else {
         roleList = (List)request.getSession().getAttribute("roles");
      }

      userVo.setRoleList((List)roleList);
   }

   private Map getPerm(UserVO userVo, HttpServletRequest request) {
      Object permMap = null;
      if(request.getSession().getAttribute("perms") == null) {
         permMap = new HashMap();
         if(userVo.isIfAdmin()) {
            List role = this.permService.getList(new Perm());
            Iterator rp = role.iterator();

            while(rp.hasNext()) {
               Perm perm = (Perm)rp.next();
               if(!((Map)permMap).containsKey(perm.getPermId())) {
                  ((Map)permMap).put(perm.getPermId(), perm.getPermPath());
               }
            }
         } else if(!Utils.isEmpityCollection(userVo.getRoleList())) {
            userVo.setRoleList(userVo.getRoleList());
            Iterator perm1 = userVo.getRoleList().iterator();

            while(perm1.hasNext()) {
               Role role1 = (Role)perm1.next();
               RolePerm rp1 = new RolePerm();
               rp1.setRoleId(role1.getRoleId());
               List rolePermList = this.rolePermService.findList(rp1);
               Iterator var9 = rolePermList.iterator();

               while(var9.hasNext()) {
                  RolePerm rolePerm = (RolePerm)var9.next();
                  if(!((Map)permMap).containsKey(rolePerm.getPermId())) {
                     ((Map)permMap).put(rolePerm.getPermId(), rolePerm.getPermPath());
                  }
               }
            }
         }

         request.getSession().setAttribute("perms", permMap);
      } else {
         permMap = (Map)request.getSession().getAttribute("perms");
      }

      return (Map)permMap;
   }
}
