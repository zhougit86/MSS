package com.meicloud.dao;

import com.meicloud.dto.TopicVO;
import com.meicloud.model.Topic;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicMapper {

   List topicList();

   Topic getTopic(Integer var1) throws Exception;

   Topic getTopicByName(@Param("name") String var1);

   List topicListByQueueId(String var1);

   List topicListByQueueIds(List var1) throws Exception;

   int addTopic(Topic var1);

   int upadteTopic(Topic var1);

   int deleteTopic(int var1);

   int checkIfExists4New(Topic var1);

   int checkIfExists4Update(Topic var1);

   List findListByRoles(TopicVO var1);
}
