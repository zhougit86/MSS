package com.meicloud.services;

import com.meicloud.model.Tags;
import java.util.List;

public interface TagsService {

   boolean add(int var1, String var2, int var3) throws Exception;

   boolean delete(int var1) throws Exception;

   List list();

   List findByqueueIds(List var1);

   Tags getByName(Tags var1);

   List getByNameAndQueueId(Tags var1);
}
