package com.meicloud.services;

import com.meicloud.dao.AccountMapper;
import com.meicloud.dao.RoleMapper;
import com.meicloud.dao.RoleUserMapper;
import com.meicloud.model.Account;
import com.meicloud.model.Role;
import com.meicloud.model.RoleUser;
import com.meicloud.services.AuthritySyncService;
import com.meicloud.sync.JsonUtil;
import com.meicloud.sync.RoleInfo;
import com.meicloud.sync.UserInfo;
import com.meicloud.sync.UserRoleInfo;
import com.meicloud.utils.Utils;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authritySyncService")
public class AuthritySyncServiceImpl implements AuthritySyncService {

   private static Logger LOG = Logger.getLogger("AuthritySyncServiceImpl");
   @Autowired
   private AccountMapper userMapper;
   @Autowired
   private RoleMapper roleMapper;
   @Autowired
   private RoleUserMapper roleUserMapper;


   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveUser(JSONObject jsonObject) throws Exception {
      List userInfos = JsonUtil.getUserList(jsonObject);
      if(!Utils.isEmpityCollection(userInfos)) {
         try {
            Iterator var4 = userInfos.iterator();

            while(var4.hasNext()) {
               UserInfo e = (UserInfo)var4.next();
               Account account = this.userMapper.queryByAccount(e.getUserAccount());
               if(e.getOpStatus().equals("DELETE")) {
                  if(account != null) {
                     this.userMapper.delete(account.getAccount());
                  }
               } else if(account == null) {
                  account = new Account();
                  account.setAccount(e.getUserAccount());
                  account.setRealName(e.getUserName());
                  account.setEnable(true);
                  account.setSvnOn(true);
                  account.setCreateDate(new Date());
                  this.userMapper.add(account);
               } else {
                  account.setRealName(e.getUserName());
                  this.userMapper.update(account);
               }
            }

         } catch (Exception var6) {
            LOG.error(var6.getMessage());
            throw var6;
         }
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveRole(JSONObject jsonObject) throws Exception {
      List roleInfos = JsonUtil.getRoleList(jsonObject);
      if(!Utils.isEmpityCollection(roleInfos)) {
         try {
            Iterator var4 = roleInfos.iterator();

            while(var4.hasNext()) {
               RoleInfo e = (RoleInfo)var4.next();
               Role role = this.roleMapper.findOneByCode(e.getRoleCode());
               if(e.getOpStatus().equals("DELETE")) {
                  if(role != null) {
                     this.roleMapper.delRole(role.getRoleId());
                  }
               } else if(role == null) {
                  role = new Role();
                  role.setRoleId(UUID.randomUUID().toString().replaceAll("-", ""));
                  role.setRoleCode(e.getRoleCode());
                  role.setRoleName(e.getRoleName());
                  role.setRemark(e.getRoleDesc());
                  this.roleMapper.addRole(role);
               } else {
                  role.setRoleCode(e.getRoleCode());
                  role.setRoleName(e.getRoleName());
                  role.setRemark(e.getRoleDesc());
                  this.roleMapper.updateRole(role);
               }
            }

         } catch (Exception var6) {
            LOG.error(var6.getMessage());
            throw var6;
         }
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveUserRole(JSONObject jsonObject) throws Exception {
      Map roleMap = this.getRoleMap();

      try {
         List e = JsonUtil.getUserRoleList(jsonObject, roleMap);
         if(!Utils.isEmpityCollection(e)) {
            Iterator var5 = e.iterator();

            while(var5.hasNext()) {
               UserRoleInfo userRoleInfo = (UserRoleInfo)var5.next();
               this.roleUserMapper.delRoleUser(userRoleInfo.getRoleId());
               if(!Utils.isEmpityCollection(userRoleInfo.getUserCodes())) {
                  Iterator var7 = userRoleInfo.getUserCodes().iterator();

                  while(var7.hasNext()) {
                     UserInfo info = (UserInfo)var7.next();
                     RoleUser roleUser = new RoleUser();
                     roleUser.setRoleId(userRoleInfo.getRoleId());
                     roleUser.setUserNo(info.getUserAccount());
                     this.roleUserMapper.addRoleUser(roleUser);
                  }
               }
            }
         }
      } catch (Exception var9) {
         LOG.error(var9.getMessage());
         throw var9;
      }

      roleMap = null;
   }

   private Map getUserMap() throws Exception {
      HashMap map = new HashMap();
      List accountList = this.userMapper.list((String)null);
      if(Utils.isEmpityCollection(accountList)) {
         return map;
      } else {
         Iterator var4 = accountList.iterator();

         while(var4.hasNext()) {
            Account account = (Account)var4.next();
            map.put(account.getAccount(), account.getAccount());
         }

         return map;
      }
   }

   private Map getRoleMap() {
      HashMap map = new HashMap();
      List roleList = this.roleMapper.findList(new Role());
      if(Utils.isEmpityCollection(roleList)) {
         return map;
      } else {
         Iterator var4 = roleList.iterator();

         while(var4.hasNext()) {
            Role role = (Role)var4.next();
            map.put(role.getRoleCode(), role.getRoleId());
         }

         return map;
      }
   }
}
