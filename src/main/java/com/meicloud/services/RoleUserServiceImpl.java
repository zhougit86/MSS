package com.meicloud.services;

import com.meicloud.dao.RoleUserMapper;
import com.meicloud.model.RoleUser;
import com.meicloud.services.RoleUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleUserService")
public class RoleUserServiceImpl implements RoleUserService {

   @Autowired
   private RoleUserMapper roleUserMapper;


   public RoleUser findOne(RoleUser roleUser) {
      return this.roleUserMapper.findOne(roleUser);
   }

   public List findList(RoleUser roleUser) {
      return this.roleUserMapper.findList(roleUser);
   }

   public void addRoleUser(RoleUser roleUser) {
      this.roleUserMapper.addRoleUser(roleUser);
   }

   public void delRoleUser(String roleId) {
      this.roleUserMapper.delRoleUser(roleId);
   }

   public List findGroupList(String roleId) {
      return this.roleUserMapper.findGroupList(roleId);
   }
}
