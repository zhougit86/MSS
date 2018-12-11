package com.meicloud.services;

import com.meicloud.dao.RoleGroupMapper;
import com.meicloud.model.Role;
import com.meicloud.model.RoleGroup;
import com.meicloud.services.RoleGroupService;
import com.meicloud.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleGroupService")
public class RoleGroupServiceImpl implements RoleGroupService {

   @Autowired
   private RoleGroupMapper roleGroupMapper;


   public RoleGroup findOne(RoleGroup roleGroup) {
      return this.roleGroupMapper.findOne(roleGroup);
   }

   public List findList(RoleGroup roleGroup) {
      return this.roleGroupMapper.findList(roleGroup);
   }

   public void addRoleGroup(RoleGroup roleGroup) {
      this.roleGroupMapper.addRoleGroup(roleGroup);
   }

   public void delRoleGroup(String roleId) {
      this.roleGroupMapper.delRoleGroup(roleId);
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
      return this.roleGroupMapper.findGroupList(map);
   }

   public Map findHandleList(String roleId, HttpServletRequest request) {
      HashMap map = new HashMap();
      if(Utils.isEmptyStr(roleId)) {
         List roleGroup;
         if(((Boolean)request.getSession().getAttribute("ifAdmin")).booleanValue()) {
            roleGroup = (List)request.getSession().getAttribute("roles");
            if(Utils.isEmpityCollection(roleGroup)) {
               return map;
            }

            Iterator var6 = roleGroup.iterator();

            while(var6.hasNext()) {
               Role roleGroupList = (Role)var6.next();
               RoleGroup roleGroup1 = new RoleGroup();
               roleGroup1.setRoleId(roleGroupList.getRoleId());
               List roleGroupList1 = this.roleGroupMapper.findList(roleGroup1);
               this.getMap(roleGroupList1, map);
            }
         } else {
            roleGroup = this.roleGroupMapper.findList(new RoleGroup());
            this.getMap(roleGroup, map);
         }
      } else {
         RoleGroup roleGroup2 = new RoleGroup();
         roleGroup2.setRoleId(roleId);
         List roleGroupList2 = this.roleGroupMapper.findList(roleGroup2);
         this.getMap(roleGroupList2, map);
      }

      return map;
   }

   private void getMap(List roleGroupList, Map map) {
      if(!Utils.isEmpityCollection(roleGroupList)) {
         Iterator var4 = roleGroupList.iterator();

         while(var4.hasNext()) {
            RoleGroup roleGroup = (RoleGroup)var4.next();
            if(!map.containsKey(roleGroup.getGroupId())) {
               map.put(roleGroup.getGroupId(), roleGroup.getGroupId());
            }
         }
      }

   }
}
