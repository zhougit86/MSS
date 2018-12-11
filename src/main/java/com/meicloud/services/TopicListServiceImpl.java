package com.meicloud.services;

import com.meicloud.dao.TopicMapper;
import com.meicloud.dto.TopicVO;
import com.meicloud.model.Role;
import com.meicloud.model.Topic;
import com.meicloud.services.TopicListService;
import com.meicloud.utils.Utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("topicListService")
public class TopicListServiceImpl implements TopicListService {

   @Autowired
   private TopicMapper topicMapper;


   public List getTopicList() throws Exception {
      try {
         return this.topicMapper.topicList();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public Topic getTopic(Integer topicId) throws Exception {
      return this.topicMapper.getTopic(topicId);
   }

   public int addTopic(Topic topic) throws Exception {
      try {
         topic.setCreateDate(new Date());
         return this.topicMapper.addTopic(topic);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public int deleteTopic(int topicId) throws Exception {
      try {
         return this.topicMapper.deleteTopic(topicId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public int updateTopic(Topic topic) throws Exception {
      try {
         topic.setCreateDate(new Date());
         return this.topicMapper.upadteTopic(topic);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public boolean checkIfExists4New(Topic topic) throws Exception {
      try {
         return this.topicMapper.checkIfExists4New(topic) > 0;
      } catch (Exception var3) {
         throw var3;
      }
   }

   public boolean checkIfExists4Update(Topic topic) throws Exception {
      try {
         return this.topicMapper.checkIfExists4Update(topic) > 0;
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List findListByRoles(String queueId, HttpServletRequest request) {
      if(((Boolean)request.getSession().getAttribute("ifAdmin")).booleanValue()) {
         TopicVO roleList1 = new TopicVO();
         roleList1.setQueueId(queueId);
         return this.topicMapper.findListByRoles(roleList1);
      } else {
         List roleList = (List)request.getSession().getAttribute("roles");
         if(Utils.isEmpityCollection(roleList)) {
            return null;
         } else {
            ArrayList list = new ArrayList();
            Iterator var6 = roleList.iterator();

            while(var6.hasNext()) {
               Role vo = (Role)var6.next();
               list.add(vo.getRoleId());
            }

            TopicVO vo1 = new TopicVO();
            vo1.setQueueId(queueId);
            vo1.setRoles(list);
            return this.topicMapper.findListByRoles(vo1);
         }
      }
   }
}
