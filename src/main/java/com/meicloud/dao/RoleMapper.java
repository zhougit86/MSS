package com.meicloud.dao;

import com.meicloud.model.Role;
import java.util.List;

public interface RoleMapper {

   Role findOne(String var1);

   Role findOneByCode(String var1);

   List findList(Role var1);

   void addRole(Role var1);

   void updateRole(Role var1);

   void delRole(String var1);

   List findListByUser(String var1);
}
