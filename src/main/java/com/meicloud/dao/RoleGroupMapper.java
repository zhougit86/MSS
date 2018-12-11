package com.meicloud.dao;

import com.meicloud.model.RoleGroup;
import java.util.List;
import java.util.Map;

public interface RoleGroupMapper {

   RoleGroup findOne(RoleGroup var1);

   List findList(RoleGroup var1);

   List findGroupList(Map var1);

   void addRoleGroup(RoleGroup var1);

   void delRoleGroup(String var1);
}
