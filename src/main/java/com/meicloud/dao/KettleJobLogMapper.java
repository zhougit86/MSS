package com.meicloud.dao;

import java.util.List;

public interface KettleJobLogMapper {

   List getListByJobName(String var1);

   void deleteByJobId(int var1);

   void deleteByGroupId(int var1);

   String getLogTextByJobName(String var1);
}
