package com.meicloud.dao;

import com.meicloud.model.Queue;
import java.util.List;

public interface QueueMapper {

   Queue findOne(String var1);

   Queue findByQueueCode(String var1);

   List findList(Queue var1);

   void addQueue(Queue var1);

   void updateQueue(Queue var1);

   void delQueue(String var1);

   String getMaxQueueId();

   List findListByRoles(List var1);
}
