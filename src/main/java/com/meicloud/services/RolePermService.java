package com.meicloud.services;

import com.meicloud.model.RolePerm;
import java.util.List;

public interface RolePermService {

   RolePerm findOne(RolePerm var1);

   List findList(RolePerm var1);

   List findGroupList(String var1);

   void addRolePerm(RolePerm var1);

   void delRolePerm(String var1);
}
