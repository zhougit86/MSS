package com.meicloud.dao;

import java.util.List;

public interface KettleJobLogDetailMapper {

   List getKettleJobLogDetailListByIdBatch(int var1);

   void deleteByJobId(int var1);

   void deleteByGroupId(int var1);
}
