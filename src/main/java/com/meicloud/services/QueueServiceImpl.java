package com.meicloud.services;

import com.meicloud.dao.QueueMapper;
import com.meicloud.model.Queue;
import com.meicloud.model.Role;
import com.meicloud.services.QueueService;
import com.meicloud.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("queueService")
public class QueueServiceImpl implements QueueService {

   @Autowired
   private QueueMapper queueMapper;


   public Queue findOne(String queueId) {
      return this.queueMapper.findOne(queueId);
   }

   public Queue findByQueueCode(String queueCode) {
      return this.queueMapper.findByQueueCode(queueCode);
   }

   public List findList(Queue queue) {
      return this.queueMapper.findList(queue);
   }

   public void addQueue(Queue queue) {
      String queryId = this.queueMapper.getMaxQueueId();
      if(!Utils.isEmptyStr(queryId)) {
         queue.setQueueId(String.valueOf(Integer.parseInt(queryId) + 1));
      } else {
         queue.setQueueId("1");
      }

      this.queueMapper.addQueue(queue);
   }

   public void updateQueue(Queue queue) {
      this.queueMapper.updateQueue(queue);
   }

   public void delQueue(String queueId) {
      this.queueMapper.delQueue(queueId);
   }

   public List findListByRoles(HttpServletRequest request) {
      if(((Boolean)request.getSession().getAttribute("ifAdmin")).booleanValue()) {
         return this.queueMapper.findListByRoles((List)null);
      } else {
         List roleList = (List)request.getSession().getAttribute("roles");
         if(Utils.isEmpityCollection(roleList)) {
            return null;
         } else {
            ArrayList list = new ArrayList();
            Iterator var5 = roleList.iterator();

            while(var5.hasNext()) {
               Role role = (Role)var5.next();
               list.add(role.getRoleId());
            }

            return this.queueMapper.findListByRoles(list);
         }
      }
   }
}
