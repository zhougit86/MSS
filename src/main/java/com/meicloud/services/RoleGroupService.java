package com.meicloud.services;

import com.meicloud.model.RoleGroup;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface RoleGroupService {

   RoleGroup findOne(RoleGroup var1);

   List findList(RoleGroup var1);

   void addRoleGroup(RoleGroup var1);

   void delRoleGroup(String var1);

   List findGroupList(String var1, String var2);

   Map findHandleList(String var1, HttpServletRequest var2);
}
