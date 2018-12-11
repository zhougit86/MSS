package com.meicloud.services;

import com.meicloud.dao.RoleTopicMapper;
import com.meicloud.model.Role;
import com.meicloud.model.RoleTopic;
import com.meicloud.services.RoleTopicService;
import com.meicloud.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleTopicService")
public class RoleTopicServiceImpl implements RoleTopicService {

   @Autowired
   private RoleTopicMapper roleTopicMapper;


   public RoleTopic findOne(RoleTopic RoleTopic) {
      return this.roleTopicMapper.findOne(RoleTopic);
   }

   public List findList(RoleTopic RoleTopic) {
      return this.roleTopicMapper.findList(RoleTopic);
   }

   public List findGroupList(String roleId, String queueIds) {
      HashMap map = new HashMap();
      map.put("roleId", roleId);
      ArrayList list = new ArrayList();
      String[] var8;
      int var7 = (var8 = queueIds.split(",")).length;

      for(int var6 = 0; var6 < var7; ++var6) {
         String queueId = var8[var6];
         if(!queueId.equals("")) {
            list.add(queueId);
         }
      }

      map.put("queueIds", list);
      return this.roleTopicMapper.findGroupList(map);
   }

   public void addRoleTopic(RoleTopic RoleTopic) {
      this.roleTopicMapper.addRoleTopic(RoleTopic);
   }

   public void delRoleTopic(String roleId) {
      this.roleTopicMapper.delRoleTopic(roleId);
   }

   public Map findHandleList(String roleId, HttpServletRequest request) {
      HashMap map = new HashMap();
      if(Utils.isEmptyStr(roleId)) {
         List roleTopic;
         if(((Boolean)request.getSession().getAttribute("ifAdmin")).booleanValue()) {
            roleTopic = (List)request.getSession().getAttribute("roles");
            if(Utils.isEmpityCollection(roleTopic)) {
               return map;
            }

            Iterator var6 = roleTopic.iterator();

            while(var6.hasNext()) {
               Role roleTopicList = (Role)var6.next();
               RoleTopic roleTopic1 = new RoleTopic();
               roleTopic1.setRoleId(roleTopicList.getRoleId());
               List roleTopicList1 = this.roleTopicMapper.findList(roleTopic1);
               this.getMap(roleTopicList1, map);
            }
         } else {
            roleTopic = this.roleTopicMapper.findList(new RoleTopic());
            this.getMap(roleTopic, map);
         }
      } else {
         RoleTopic roleTopic2 = new RoleTopic();
         roleTopic2.setRoleId(roleId);
         List roleTopicList2 = this.roleTopicMapper.findList(roleTopic2);
         this.getMap(roleTopicList2, map);
      }

      return map;
   }

   private void getMap(List roleTopicList, Map map) {
      if(!Utils.isEmpityCollection(roleTopicList)) {
         Iterator var4 = roleTopicList.iterator();

         while(var4.hasNext()) {
            RoleTopic roleTopic = (RoleTopic)var4.next();
            if(!map.containsKey(roleTopic.getTopicId())) {
               map.put(roleTopic.getTopicId(), roleTopic.getTopicId());
            }
         }
      }

   }
}
