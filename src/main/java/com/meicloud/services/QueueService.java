package com.meicloud.services;

import com.meicloud.model.Queue;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface QueueService {

   Queue findOne(String var1);

   Queue findByQueueCode(String var1);

   List findList(Queue var1);

   void addQueue(Queue var1);

   void updateQueue(Queue var1);

   void delQueue(String var1);

   List findListByRoles(HttpServletRequest var1);
}
