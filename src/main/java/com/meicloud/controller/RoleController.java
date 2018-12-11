package com.meicloud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meicloud.controller.BasicController;
import com.meicloud.dto.GroupInfo;
import com.meicloud.dto.PermInfo;
import com.meicloud.dto.QueueInfo;
import com.meicloud.dto.TopicInfo;
import com.meicloud.dto.TreeInfo;
import com.meicloud.model.Perm;
import com.meicloud.model.Role;
import com.meicloud.model.RoleGroup;
import com.meicloud.model.RolePerm;
import com.meicloud.model.RoleQueue;
import com.meicloud.model.RoleTopic;
import com.meicloud.model.RoleUser;
import com.meicloud.services.PermService;
import com.meicloud.services.RoleGroupService;
import com.meicloud.services.RolePermService;
import com.meicloud.services.RoleQueueService;
import com.meicloud.services.RoleService;
import com.meicloud.services.RoleTopicService;
import com.meicloud.services.RoleUserService;
import com.meicloud.utils.Utils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"role"})
public class RoleController extends BasicController {

   private static Logger log = Logger.getLogger(RoleController.class);
   @Autowired
   private RoleQueueService roleQueueService;
   @Autowired
   private RoleGroupService roleGroupService;
   @Autowired
   private RoleService roleService;
   @Autowired
   private RolePermService rolePermService;
   @Autowired
   private RoleTopicService roleTopicService;
   @Autowired
   private PermService permService;
   @Autowired
   private RoleUserService roleUserService;


   @ApiOperation(
      value = "菜单信息列表(有权限的,供首页菜单展示使用)",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/menuInfoList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object menuInfoList(HttpServletRequest request) {
      log.info("------------->/role/menuInfoList 查询用户有权限的菜单树信息列表");
      ArrayList newMenuList = new ArrayList();
      Map permMap = (Map)request.getSession().getAttribute("perms");

      try {
         if(!Utils.isEmpityMap(permMap)) {
            Perm e = new Perm();
            e.setPermType("X");
            List menuList = this.permService.getList(e);
            if(Utils.isEmpityCollection(menuList)) {
               return this.outBound(newMenuList);
            }

            Iterator var7 = menuList.iterator();

            while(var7.hasNext()) {
               Perm perm = (Perm)var7.next();
               if(permMap.containsKey(perm.getPermId())) {
                  newMenuList.add(perm);
               }
            }
         }

         return this.outBound(newMenuList);
      } catch (Exception var8) {
         log.error("------------->/role/menuInfoList 接口异常" + var8.getMessage());
         return this.errorHandler("菜单树信息列表查询异常:" + var8.getMessage());
      }
   }

   @ApiOperation(
      value = "集群-->主题-->job组树权限信息",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/jobByRoleIdAndQueueIds"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object jobByRoleIdAndQueueIds(String roleId, String queueIds) {
      ArrayList list = new ArrayList();
      HashMap topicMap = new HashMap();

      try {
         if(queueIds != null && !queueIds.equals("")) {
            List e = this.roleTopicService.findGroupList(roleId, queueIds);
            if(!Utils.isEmpityCollection(e)) {
               Iterator group = e.iterator();

               while(group.hasNext()) {
                  TopicInfo groupList = (TopicInfo)group.next();
                  topicMap.put(groupList.getTopicId(), groupList.getTopicId());
                  TreeInfo dto = new TreeInfo();
                  dto.setId("M_" + groupList.getTopicId());
                  dto.setName(groupList.getTopicName());
                  dto.setpId("X_" + groupList.getQueueId());
                  dto.setChecked(groupList.getIfFlag().booleanValue());
                  dto.setType("M");
                  list.add(dto);
               }
            }

            List groupList1 = this.roleGroupService.findGroupList(roleId, queueIds);
            if(!Utils.isEmpityCollection(groupList1)) {
               Iterator dto2 = groupList1.iterator();

               while(dto2.hasNext()) {
                  GroupInfo group1 = (GroupInfo)dto2.next();
                  if(topicMap.containsKey(group1.getTopicId())) {
                     TreeInfo dto1 = new TreeInfo();
                     dto1.setId("Y_" + group1.getGroupId());
                     dto1.setName(group1.getGroupName());
                     dto1.setpId("M_" + group1.getTopicId());
                     dto1.setChecked(group1.getIfFlag().booleanValue());
                     dto1.setType("Y");
                     list.add(dto1);
                  }
               }
            }
         }

         return this.outBound(list);
      } catch (Exception var10) {
         log.error("------------->/role/jobByRoleIdAndQueueIds 接口异常" + var10.getMessage());
         return this.errorHandler("集群-->主题-->job组树权限信息查询异常:" + var10.getMessage());
      }
   }

   @ApiOperation(
      value = "集群权限信息",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/queueListByRoleId"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object queueListByRoleId(String roleId) {
      try {
         ArrayList e = new ArrayList();
         List queueInfoList = this.roleQueueService.findGroupList(roleId);
         if(!Utils.isEmpityCollection(queueInfoList)) {
            Iterator var5 = queueInfoList.iterator();

            while(var5.hasNext()) {
               QueueInfo info = (QueueInfo)var5.next();
               TreeInfo dto = new TreeInfo();
               dto.setId("X_" + info.getQueueId());
               dto.setName(info.getQueueName());
               dto.setpId("0");
               dto.setType("X");
               dto.setChecked(info.getIfFlag().booleanValue());
               e.add(dto);
            }
         }

         return this.outBound(e);
      } catch (Exception var7) {
         log.error("------------->/role/queueListByRoleId 接口异常" + var7.getMessage());
         return this.errorHandler("集群权限信息查询异常:" + var7.getMessage());
      }
   }

   @ApiOperation(
      value = "集群-->主题-->job组树权限信息",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/jobList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object jobList(String roleId, String queueIds) {
      log.info("------------->/role/jobList 集群-->主题-->job组树权限信息");

      try {
         ArrayList e = new ArrayList();
         HashMap queueMap = new HashMap();
         HashMap topicMap = new HashMap();
         List queueInfoList = this.roleQueueService.findGroupList(roleId);
         if(!Utils.isEmpityCollection(queueInfoList)) {
            Iterator groupList = queueInfoList.iterator();

            while(groupList.hasNext()) {
               QueueInfo topicList = (QueueInfo)groupList.next();
               queueMap.put(topicList.getQueueId(), topicList.getQueueId());
               TreeInfo group = new TreeInfo();
               group.setId("X_" + topicList.getQueueId());
               group.setName(topicList.getQueueName());
               group.setpId("0");
               group.setType("X");
               group.setChecked(topicList.getIfFlag().booleanValue());
               e.add(group);
            }
         }

         if(queueIds != null && !queueIds.equals("")) {
            List topicList1 = this.roleTopicService.findGroupList(roleId, queueIds);
            if(!Utils.isEmpityCollection(topicList1)) {
               Iterator group1 = topicList1.iterator();

               while(group1.hasNext()) {
                  TopicInfo groupList1 = (TopicInfo)group1.next();
                  if(queueMap.containsKey(groupList1.getQueueId())) {
                     topicMap.put(groupList1.getTopicId(), groupList1.getTopicId());
                     TreeInfo dto = new TreeInfo();
                     dto.setId("M_" + groupList1.getTopicId());
                     dto.setName(groupList1.getTopicName());
                     dto.setpId("X_" + groupList1.getQueueId());
                     dto.setChecked(groupList1.getIfFlag().booleanValue());
                     dto.setType("M");
                     e.add(dto);
                  }
               }
            }

            List groupList2 = this.roleGroupService.findGroupList(roleId, queueIds);
            if(!Utils.isEmpityCollection(groupList2)) {
               Iterator dto2 = groupList2.iterator();

               while(dto2.hasNext()) {
                  GroupInfo group2 = (GroupInfo)dto2.next();
                  if(topicMap.containsKey(group2.getTopicId())) {
                     TreeInfo dto1 = new TreeInfo();
                     dto1.setId("Y_" + group2.getGroupId());
                     dto1.setName(group2.getGroupName());
                     dto1.setpId("M_" + group2.getTopicId());
                     dto1.setChecked(group2.getIfFlag().booleanValue());
                     dto1.setType("Y");
                     e.add(dto1);
                  }
               }
            }
         }

         return this.outBound(e);
      } catch (Exception var12) {
         log.error("------------->/role/jobList 接口异常" + var12.getMessage());
         return this.errorHandler("集群-->主题-->job组树权限信息查询异常:" + var12.getMessage());
      }
   }

   @ApiOperation(
      value = "菜单功能权限信息",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/menuOperationList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object menuOperationList(String roleId) {
      log.info("------------->/role/menuOperationList 菜单功能权限信息");

      try {
         ArrayList e = new ArrayList();
         List menuList = this.rolePermService.findGroupList(roleId);
         if(!Utils.isEmpityCollection(menuList)) {
            Iterator var5 = menuList.iterator();

            while(var5.hasNext()) {
               PermInfo info = (PermInfo)var5.next();
               TreeInfo dto = new TreeInfo();
               dto.setId(info.getId().toString());
               dto.setName(info.getPermName());
               dto.setpId(info.getpId() != null?info.getpId().toString():"0");
               dto.setCode(info.getPermCode());
               dto.setExport(info.getPermPath());
               dto.setChecked(info.getIfFlag().booleanValue());
               e.add(dto);
            }
         }

         return this.outBound(e);
      } catch (Exception var7) {
         log.error("------------->/role/menuOperationList 接口异常" + var7.getMessage());
         return this.errorHandler("菜单功能权限信息查询异常:" + var7.getMessage());
      }
   }

   @ApiOperation(
      value = "角色列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/roleList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object roleList(@ApiParam("页数") @RequestParam String pagenum, @ApiParam("每页的行数") @RequestParam String rownum) {
      log.info("------------->/role/roleList 角色列表信息");

      try {
         PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum));
         List e = this.roleService.findList(new Role());
         PageInfo page = new PageInfo(e);
         return this.outBound(this.getDataInfo(new Role(), page.getList(), Long.valueOf(page.getTotal())));
      } catch (Exception var5) {
         log.error("------------->/role/roleList 接口异常" + var5.getMessage());
         return this.errorHandler("角色列表查询异常:" + var5.getMessage());
      }
   }

   @RequestMapping(
      value = {"/detail"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "角色详情",
      notes = "返回json"
   )
   @ResponseBody
   public Object detail(String roleId) {
      log.info("------------->/role/detail 角色详情");
      if(Utils.isEmptyStr(roleId)) {
         return this.errorHandler("角色编码不能为空");
      } else {
         try {
            Role e = this.roleService.findOne(roleId);
            RoleUser roleUser = new RoleUser();
            roleUser.setRoleId(roleId);
            List roleUsers = this.roleUserService.findList(roleUser);
            if(!Utils.isEmpityCollection(roleUsers)) {
               Iterator roleQueues = roleUsers.iterator();

               while(roleQueues.hasNext()) {
                  RoleUser roleQueue = (RoleUser)roleQueues.next();
                  e.getUserList().add(roleQueue.getUserNo());
                  e.getUserMap().put(roleQueue.getUserNo(), roleQueue.getUserName());
               }
            }

            RoleQueue roleQueue1 = new RoleQueue();
            roleQueue1.setRoleId(roleId);
            List roleQueues1 = this.roleQueueService.findList(roleQueue1);
            if(!Utils.isEmpityCollection(roleQueues1)) {
               Iterator roleGroups = roleQueues1.iterator();

               while(roleGroups.hasNext()) {
                  RoleQueue roleGroup = (RoleQueue)roleGroups.next();
                  e.getQueueList().add(roleGroup.getQueueId());
               }
            }

            RoleGroup roleGroup1 = new RoleGroup();
            roleGroup1.setRoleId(roleId);
            List roleGroups1 = this.roleGroupService.findList(roleGroup1);
            if(!Utils.isEmpityCollection(roleGroups1)) {
               Iterator roleTopics = roleGroups1.iterator();

               while(roleTopics.hasNext()) {
                  RoleGroup roleTopic = (RoleGroup)roleTopics.next();
                  e.getGroupList().add(roleTopic.getGroupId());
               }
            }

            RoleTopic roleTopic1 = new RoleTopic();
            roleTopic1.setRoleId(roleId);
            List roleTopics1 = this.roleTopicService.findList(roleTopic1);
            if(!Utils.isEmpityCollection(roleTopics1)) {
               Iterator rolePerms = roleTopics1.iterator();

               while(rolePerms.hasNext()) {
                  RoleTopic rolePerm = (RoleTopic)rolePerms.next();
                  e.getTopicList().add(rolePerm.getTopicId());
               }
            }

            RolePerm rolePerm1 = new RolePerm();
            rolePerm1.setRoleId(roleId);
            List rolePerms1 = this.rolePermService.findList(rolePerm1);
            if(!Utils.isEmpityCollection(rolePerms1)) {
               Iterator var14 = rolePerms1.iterator();

               while(var14.hasNext()) {
                  RolePerm ru = (RolePerm)var14.next();
                  e.getPermList().add(ru.getPermId());
               }
            }

            return this.outBound(e);
         } catch (Exception var15) {
            log.error("------------->/role/detail 接口异常" + var15.getMessage());
            return this.errorHandler("角色详情查询异常:" + var15.getMessage());
         }
      }
   }

   @RequestMapping(
      value = {"/checkRole"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "角色重复校验",
      notes = "返回json"
   )
   @ResponseBody
   public Object checkRole(String roleCode) {
      log.info("------------->/role/checkRole 角色重复校验");
      if(Utils.isEmptyStr(roleCode)) {
         return this.errorHandler("角色编码不能为空");
      } else {
         try {
            Role e = this.roleService.findOneByCode(roleCode);
            return e != null?this.errorHandler("角色编码已经存在"):this.outBound("成功");
         } catch (Exception var3) {
            log.error("------------->/role/checkRole 接口异常" + var3.getMessage());
            return this.errorHandler("角色重复校验异常:" + var3.getMessage());
         }
      }
   }

   @RequestMapping(
      value = {"/roleSave"},
      method = {RequestMethod.POST},
      consumes = {"application/json"}
   )
   @ApiOperation(
      value = "角色新增/修改",
      notes = "返回json"
   )
   @ResponseBody
   public Object roleSave(@RequestBody Role role) {
      log.info("------------->/role/roleSave 角色新增/修改");
      if(Utils.isEmptyStr(role.getRoleCode())) {
         return this.errorHandler("角色编码不能为空");
      } else {
         try {
            if(Utils.isEmptyStr(role.getRoleId())) {
               this.roleService.addRole(role);
            } else {
               this.roleService.updateRole(role);
            }

            return this.outBound("角色保存成功");
         } catch (Exception var3) {
            log.error("------------->/role/roleSave 接口异常" + var3.getMessage());
            return this.errorHandler("角色保存异常:" + var3.getMessage());
         }
      }
   }

   @RequestMapping(
      value = {"/roleDelete"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "删除角色",
      notes = "返回json"
   )
   @ResponseBody
   public Object roleDelete(String roleId) {
      log.info("------------->/role/roleDelete 角色删除");

      try {
         this.roleService.delRole(roleId);
         return this.outBound("角色删除成功");
      } catch (Exception var3) {
         log.error("------------->/role/roleDelete 接口异常" + var3.getMessage());
         return this.errorHandler("角色删除异常:" + var3.getMessage());
      }
   }
}
