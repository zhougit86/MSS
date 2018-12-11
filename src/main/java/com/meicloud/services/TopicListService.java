package com.meicloud.services;

import com.meicloud.model.Topic;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface TopicListService {

   List getTopicList() throws Exception;

   Topic getTopic(Integer var1) throws Exception;

   int addTopic(Topic var1) throws Exception;

   int deleteTopic(int var1) throws Exception;

   int updateTopic(Topic var1) throws Exception;

   boolean checkIfExists4New(Topic var1) throws Exception;

   boolean checkIfExists4Update(Topic var1) throws Exception;

   List findListByRoles(String var1, HttpServletRequest var2);
}
