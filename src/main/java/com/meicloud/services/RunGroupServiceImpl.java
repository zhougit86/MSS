package com.meicloud.services;

import com.meicloud.dao.RunGroupMapper;
import com.meicloud.model.JobPusherParam;
import com.meicloud.model.RunGroup;
import com.meicloud.services.RunGroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("runGroupService")
public class RunGroupServiceImpl implements RunGroupService {

   @Autowired
   private RunGroupMapper runGroupMapper;


   public void addByGroup(JobPusherParam jobPusherParam) {
      this.runGroupMapper.addByGroup(jobPusherParam);
   }

   public void update(RunGroup runGroup) {
      this.runGroupMapper.update(runGroup);
   }

   public void copy2His(int groupId) {
      this.runGroupMapper.copy2His(groupId);
   }

   public int delete(int groupId) {
      return this.runGroupMapper.delete(groupId);
   }

   public RunGroup getRunGroupByGroupId(int groupId) {
      return this.runGroupMapper.getRunGroupByGroupId(groupId);
   }

   public List findAllList(RunGroup runGroup) {
      return this.runGroupMapper.findAllList(runGroup);
   }
}
