package com.meicloud.dao;

import com.meicloud.model.RoleTopic;
import java.util.List;
import java.util.Map;

public interface RoleTopicMapper {

   RoleTopic findOne(RoleTopic var1);

   List findList(RoleTopic var1);

   List findGroupList(Map var1);

   void addRoleTopic(RoleTopic var1);

   void delRoleTopic(String var1);
}
