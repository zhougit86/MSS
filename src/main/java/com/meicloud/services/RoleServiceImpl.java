package com.meicloud.services;

import com.meicloud.dao.RoleGroupMapper;
import com.meicloud.dao.RoleMapper;
import com.meicloud.dao.RolePermMapper;
import com.meicloud.dao.RoleQueueMapper;
import com.meicloud.dao.RoleTopicMapper;
import com.meicloud.dao.RoleUserMapper;
import com.meicloud.model.Role;
import com.meicloud.model.RoleGroup;
import com.meicloud.model.RolePerm;
import com.meicloud.model.RoleQueue;
import com.meicloud.model.RoleTopic;
import com.meicloud.model.RoleUser;
import com.meicloud.services.RoleService;
import com.meicloud.utils.Utils;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

   private static Logger LOG = Logger.getLogger("RoleServiceImpl");
   @Autowired
   private RoleMapper roleMapper;
   @Autowired
   private RolePermMapper rolePermMapper;
   @Autowired
   private RoleGroupMapper roleGroupMapper;
   @Autowired
   private RoleQueueMapper roleQueueMapper;
   @Autowired
   private RoleUserMapper roleUserMapper;
   @Autowired
   private RoleTopicMapper roleTopicMapper;


   public Role findOne(String roleId) {
      return this.roleMapper.findOne(roleId);
   }

   public Role findOneByCode(String roleCode) {
      return this.roleMapper.findOneByCode(roleCode);
   }

   public List findList(Role role) {
      return this.roleMapper.findList(role);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void addRole(Role role) throws Exception {
      try {
         role.setRoleId(UUID.randomUUID().toString().replaceAll("-", ""));
         this.addInfo(role);
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
         throw var3;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateRole(Role role) throws Exception {
      try {
         this.delInfo(role.getRoleId());
         this.addInfo(role);
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
         throw var3;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void delRole(String roleId) throws Exception {
      try {
         this.delInfo(roleId);
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
         throw var3;
      }
   }

   public List findListByUser(String userNo) {
      return this.roleMapper.findListByUser(userNo);
   }

   private void addInfo(Role role) throws Exception {
      try {
         this.roleMapper.addRole(role);
         String e;
         Iterator var3;
         if(!Utils.isEmpityCollection(role.getPermList())) {
            var3 = role.getPermList().iterator();

            while(var3.hasNext()) {
               e = (String)var3.next();
               RolePerm roleTopic = new RolePerm();
               roleTopic.setPermId(e);
               roleTopic.setRoleId(role.getRoleId());
               this.rolePermMapper.addRolePerm(roleTopic);
            }
         }

         Integer e1;
         if(!Utils.isEmpityCollection(role.getQueueList())) {
            var3 = role.getQueueList().iterator();

            while(var3.hasNext()) {
               e1 = (Integer)var3.next();
               RoleQueue roleTopic1 = new RoleQueue();
               roleTopic1.setQueueId(e1);
               roleTopic1.setRoleId(role.getRoleId());
               this.roleQueueMapper.addRoleQueue(roleTopic1);
            }
         }

         if(!Utils.isEmpityCollection(role.getGroupList())) {
            var3 = role.getGroupList().iterator();

            while(var3.hasNext()) {
               e1 = (Integer)var3.next();
               RoleGroup roleTopic2 = new RoleGroup();
               roleTopic2.setGroupId(e1);
               roleTopic2.setRoleId(role.getRoleId());
               this.roleGroupMapper.addRoleGroup(roleTopic2);
            }
         }

         if(!Utils.isEmpityCollection(role.getUserList())) {
            var3 = role.getUserList().iterator();

            while(var3.hasNext()) {
               e = (String)var3.next();
               RoleUser roleTopic3 = new RoleUser();
               roleTopic3.setUserNo(e);
               roleTopic3.setRoleId(role.getRoleId());
               this.roleUserMapper.addRoleUser(roleTopic3);
            }
         }

         if(!Utils.isEmpityCollection(role.getTopicList())) {
            var3 = role.getTopicList().iterator();

            while(var3.hasNext()) {
               e1 = (Integer)var3.next();
               RoleTopic roleTopic4 = new RoleTopic();
               roleTopic4.setTopicId(e1);
               roleTopic4.setRoleId(role.getRoleId());
               this.roleTopicMapper.addRoleTopic(roleTopic4);
            }
         }

      } catch (Exception var5) {
         LOG.error(var5.getMessage());
         throw var5;
      }
   }

   private void delInfo(String roleId) throws Exception {
      try {
         this.rolePermMapper.delRolePerm(roleId);
         this.roleGroupMapper.delRoleGroup(roleId);
         this.roleQueueMapper.delRoleQueue(roleId);
         this.roleUserMapper.delRoleUser(roleId);
         this.roleTopicMapper.delRoleTopic(roleId);
         this.roleMapper.delRole(roleId);
      } catch (Exception var3) {
         LOG.error(var3.getMessage());
         throw var3;
      }
   }
}
