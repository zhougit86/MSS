package com.meicloud.controller;

import com.meicloud.controller.BasicController;
import com.meicloud.dto.UserVO;
import com.meicloud.model.Account;
import com.meicloud.model.Perm;
import com.meicloud.model.Role;
import com.meicloud.model.RolePerm;
import com.meicloud.services.AccountService;
import com.meicloud.services.CustomUserDetailsService;
import com.meicloud.services.MSSAuthenticationProvider;
import com.meicloud.services.PermService;
import com.meicloud.services.RolePermService;
import com.meicloud.services.RoleService;
import com.meicloud.utils.SessionUser;
import com.meicloud.utils.Utils;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController extends BasicController {

   private static Logger log = LoggerFactory.getLogger(LoginController.class);
   @Autowired
   private AccountService accountService;
   @Resource
   private CustomUserDetailsService customUserDetailsService;
   @Resource
   private MSSAuthenticationProvider mSSAuthenticationProvider;
   @Resource
   private RoleService roleService;
   @Resource
   private RolePermService rolePermService;
   @Resource
   private PermService permService;
   @Value("${SECURITY_ADMIN_USERS}")
   private String securityAdminUsers;


   @ApiOperation(
      value = "用户登录",
      notes = "返回url"
   )
   @RequestMapping(
      value = {"/login"},
      method = {RequestMethod.POST}
   )
   public String login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
      Account account = this.accountService.login(username, password);
      if(account != null) {
         request.getSession().setAttribute("user", account);
         SessionUser.setSessionUser(account);
         this.setUserInfo(request);
         return "redirect:/";
      } else {
         return "redirect:/login/index.html";
      }
   }

   @ApiOperation(
      value = "用户登录注销",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/logout"},
      method = {RequestMethod.POST, RequestMethod.GET}
   )
   public String logout(String username, HttpServletRequest request, HttpServletResponse response) throws Exception {
      log.info("------------->用户登录 /logout 参数:logout" + username);
      request.getSession().removeAttribute("user");
      return "redirect:/login/index.html";
   }

   private void setUserInfo(HttpServletRequest request) {
      UserVO userVo = new UserVO();
      Account account = (Account)request.getSession().getAttribute("user");
      userVo.setUserName(account.getRealName());
      userVo.setUserAccount(account.getAccount());
      userVo.setSvnOn(account.isSvnOn());
      request.getSession().setAttribute("user", account);
      this.getRoleList(userVo, request);
      if(request.getSession().getAttribute("ifAdmin") == null) {
         request.getSession().setAttribute("ifAdmin", Boolean.FALSE);
         if(!Utils.isEmpityCollection(userVo.getRoleList())) {
            Iterator permMap = userVo.getRoleList().iterator();

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
         userVo.setIfAdmin(Boolean.TRUE.booleanValue());
      } else {
         userVo.setIfAdmin(((Boolean)request.getSession().getAttribute("ifAdmin")).booleanValue());
      }

      Map permMap1 = this.getPerm(userVo, request);
      if(!Utils.isEmpityMap(permMap1) && account.isEnable()) {
         ArrayList permList = new ArrayList();
         Iterator var8 = permMap1.keySet().iterator();

         while(var8.hasNext()) {
            String key = (String)var8.next();
            permList.add(key);
         }

         userVo.setPermList(permList);
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
