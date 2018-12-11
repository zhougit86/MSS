package com.meicloud.services;

import com.meicloud.dao.RoleQueueMapper;
import com.meicloud.model.Role;
import com.meicloud.model.RoleQueue;
import com.meicloud.services.RoleQueueService;
import com.meicloud.utils.Utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleQueueService")
public class RoleQueueServiceImpl implements RoleQueueService {

   @Autowired
   private RoleQueueMapper roleQueuepMapper;


   public RoleQueue findOne(RoleQueue roleQueue) {
      return this.roleQueuepMapper.findOne(roleQueue);
   }

   public List findList(RoleQueue roleQueue) {
      return this.roleQueuepMapper.findList(roleQueue);
   }

   public List findGroupList(String roleId) {
      return this.roleQueuepMapper.findGroupList(roleId);
   }

   public void addRoleQueue(RoleQueue roleQueue) {
      this.roleQueuepMapper.addRoleQueue(roleQueue);
   }

   public void delRoleQueue(String roleId) {
      this.roleQueuepMapper.delRoleQueue(roleId);
   }

   public Map findHandleList(String roleId, HttpServletRequest request) {
      HashMap map = new HashMap();
      if(Utils.isEmptyStr(roleId)) {
         List roleQueue;
         if(((Boolean)request.getSession().getAttribute("ifAdmin")).booleanValue()) {
            roleQueue = (List)request.getSession().getAttribute("roles");
            if(Utils.isEmpityCollection(roleQueue)) {
               return map;
            }

            Iterator var6 = roleQueue.iterator();

            while(var6.hasNext()) {
               Role roleQueueList = (Role)var6.next();
               RoleQueue roleQueue1 = new RoleQueue();
               roleQueue1.setRoleId(roleQueueList.getRoleId());
               List roleQueueList1 = this.roleQueuepMapper.findList(roleQueue1);
               this.getMap(roleQueueList1, map);
            }
         } else {
            roleQueue = this.roleQueuepMapper.findList(new RoleQueue());
            this.getMap(roleQueue, map);
         }
      } else {
         RoleQueue roleQueue2 = new RoleQueue();
         roleQueue2.setRoleId(roleId);
         List roleQueueList2 = this.roleQueuepMapper.findList(roleQueue2);
         this.getMap(roleQueueList2, map);
      }

      return map;
   }

   private void getMap(List roleQueueList, Map map) {
      if(!Utils.isEmpityCollection(roleQueueList)) {
         Iterator var4 = roleQueueList.iterator();

         while(var4.hasNext()) {
            RoleQueue roleQueue = (RoleQueue)var4.next();
            if(!map.containsKey(roleQueue.getQueueId())) {
               map.put(roleQueue.getQueueId(), roleQueue.getQueueId());
            }
         }
      }

   }
}
