package com.meicloud.dao;

import com.meicloud.model.JobPusherParam;
import com.meicloud.model.RunGroup;
import java.util.List;

public interface RunGroupMapper {

   void addByGroup(JobPusherParam var1);

   void update(RunGroup var1);

   void copy2His(int var1);

   int delete(int var1);

   RunGroup getRunGroupByGroupId(int var1);

   List findAllList(RunGroup var1);
}
