package com.meicloud.dao;

import com.meicloud.model.RoleQueue;
import java.util.List;

public interface RoleQueueMapper {

   RoleQueue findOne(RoleQueue var1);

   List findList(RoleQueue var1);

   List findGroupList(String var1);

   void addRoleQueue(RoleQueue var1);

   void delRoleQueue(String var1);
}
