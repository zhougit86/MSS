package com.meicloud.dao;

import com.meicloud.model.GroupRefer;
import java.util.List;

public interface GroupReferMapper {

   List getAll();

   List getReferedList(int var1);

   List getPostList(int var1);

   void add(GroupRefer var1);

   void deleteByGroupId(int var1);

   void deleteByReferedGroupId(int var1);

   List getGroupReferByGroupId(GroupRefer var1) throws Exception;
}
