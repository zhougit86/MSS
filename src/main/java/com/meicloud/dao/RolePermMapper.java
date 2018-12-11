package com.meicloud.dao;

import com.meicloud.model.RolePerm;
import java.util.List;

public interface RolePermMapper {

   RolePerm findOne(RolePerm var1);

   List findList(RolePerm var1);

   List findGroupList(String var1);

   void addRolePerm(RolePerm var1);

   void delRolePerm(String var1);
}
