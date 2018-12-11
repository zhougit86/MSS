package com.meicloud.services;

import com.meicloud.model.RoleTopic;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface RoleTopicService {

   RoleTopic findOne(RoleTopic var1);

   List findList(RoleTopic var1);

   List findGroupList(String var1, String var2);

   void addRoleTopic(RoleTopic var1);

   void delRoleTopic(String var1);

   Map findHandleList(String var1, HttpServletRequest var2);
}
