package com.meicloud.services;

import com.meicloud.dao.RolePermMapper;
import com.meicloud.model.RolePerm;
import com.meicloud.services.RolePermService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rolePermService")
public class RolePermServiceImpl implements RolePermService {

   @Autowired
   private RolePermMapper rolePermMapper;


   public RolePerm findOne(RolePerm rolePerm) {
      return this.rolePermMapper.findOne(rolePerm);
   }

   public List findList(RolePerm rolePerm) {
      return this.rolePermMapper.findList(rolePerm);
   }

   public List findGroupList(String roleId) {
      return this.rolePermMapper.findGroupList(roleId);
   }

   public void addRolePerm(RolePerm rolePerm) {
      this.rolePermMapper.addRolePerm(rolePerm);
   }

   public void delRolePerm(String roleId) {
      this.rolePermMapper.delRolePerm(roleId);
   }
}
