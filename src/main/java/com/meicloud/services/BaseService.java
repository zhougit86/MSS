package com.meicloud.services;

import java.util.List;

public interface BaseService {

   Object get(String var1);

   @Deprecated
   Object get(Object var1);

   Object findOne(Object var1);

   List findList(Object var1);

   List findAllList(Object var1);

   @Deprecated
   List findAllList();

   int save(Object var1);

   int update(Object var1);

   @Deprecated
   int delete(String var1);

   int delete(Object var1);

   int saveInfo(Object var1);
}
