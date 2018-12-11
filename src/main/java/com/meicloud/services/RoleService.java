package com.meicloud.services;

import com.meicloud.model.Role;
import java.util.List;

public interface RoleService {

   Role findOne(String var1);

   Role findOneByCode(String var1);

   List findList(Role var1);

   void addRole(Role var1) throws Exception;

   void updateRole(Role var1) throws Exception;

   void delRole(String var1) throws Exception;

   List findListByUser(String var1);
}
