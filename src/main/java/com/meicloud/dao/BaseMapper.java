package com.meicloud.dao;

import java.util.List;

public interface BaseMapper {

   Object get(String var1);

   @Deprecated
   Object get(Object var1);

   Object findOne(Object var1);

   List findList(Object var1);

   List findAllList(Object var1);

   @Deprecated
   List findAllList();

   int insert(Object var1);

   int update(Object var1);

   @Deprecated
   int delete(String var1);

   int delete(Object var1);
}
