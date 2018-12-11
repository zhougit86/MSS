package com.meicloud.dao;

import com.meicloud.model.GroupJobRefer;
import java.util.List;

public interface GroupJobReferMapper {

   void add(GroupJobRefer var1);

   List getByGroupId(int var1);

   void deleteByGroupId(int var1);

   List getGroupEditReferOption(int var1);

   void deleteByJobId(int var1);
}
