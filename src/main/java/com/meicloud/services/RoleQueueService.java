package com.meicloud.services;

import com.meicloud.model.RoleQueue;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface RoleQueueService {

   RoleQueue findOne(RoleQueue var1);

   List findList(RoleQueue var1);

   List findGroupList(String var1);

   void addRoleQueue(RoleQueue var1);

   void delRoleQueue(String var1);

   Map findHandleList(String var1, HttpServletRequest var2);
}
