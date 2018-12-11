package com.meicloud.dao;

import com.meicloud.model.RoleUser;
import java.util.List;

public interface RoleUserMapper {

   RoleUser findOne(RoleUser var1);

   List findList(RoleUser var1);

   void addRoleUser(RoleUser var1);

   void delRoleUser(String var1);

   List findGroupList(String var1);
}
