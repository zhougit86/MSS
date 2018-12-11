package com.meicloud.dao;

import com.meicloud.dto.CommonVO;
import com.meicloud.model.Group;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupMapper {

   void add(Group var1);

   int checkIfExists4New(Group var1);

   int checkIfExists4Update(Group var1);

   void update(Group var1);

   void update2SordInGroup(int var1);

   void update2UnSordInGroup(int var1);

   void cleanUpGroupMsg(int var1);

   Group getById(int var1);

   Group getByGroupName(@Param("groupName") String var1);

   List getList(Group var1);

   List getNavCheckList();

   List getListByTagsId(int var1);

   List getGroupReferOptions(Group var1);

   List getGroupByLeverAndRefer(Group var1);

   List getRefGroupById(int var1);

   List getGroupRefById(int var1);

   List getReCronTriggerGroupList();

   List getHistoryList(int var1);

   List getGroupsByState(@Param("state") int var1, @Param("queueId") String var2);

   void addChlog(Group var1);

   String getGroupByState(@Param("groupId") int var1, @Param("state") int var2);

   Group groupExectime(@Param("groupId") int var1);

   int getTopicCount();

   List getAllGroup() throws Exception;

   List getAllEnableGroup() throws Exception;

   List getAllEnableGroupByCron(String var1) throws Exception;

   List forwardActiveAndEvenTriggerGroupIds(int var1);

   List getHeaderCountEditPage(String var1);

   List getHeaderCountRunPage(String var1);

   List findAllGroup();

   List findListByGroupIds(CommonVO var1);

   List getListByAllParameters(Group var1);

   void deleteGroupReferByGroupId(@Param("groupId") int var1);

   void delete(@Param("groupId") int var1);
}
