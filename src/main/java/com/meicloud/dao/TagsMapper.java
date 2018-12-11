package com.meicloud.dao;

import com.meicloud.model.Tags;
import java.util.List;

public interface TagsMapper {

   Tags getByName(Tags var1);

   List getByNameAndQueueId(Tags var1);

   void addTag(Tags var1);

   void delete(int var1);

   void addTagRefer(Tags var1);

   List list();

   List findByqueueIds(List var1);

   int countRefer(Tags var1);
}
