package com.meicloud.services;

import com.meicloud.dto.CommonVO;
import com.meicloud.model.Group;
import com.meicloud.model.GroupLevel;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface GroupService {

   void add(Group var1) throws Exception;

   boolean checkIfExist(Group var1, boolean var2) throws Exception;

   void update(Group var1) throws Exception;

   List getGroupEditJobReferOption(int var1) throws Exception;

   void updateState(int var1, String var2, String var3) throws Exception;

   boolean delete(int var1, String var2, String var3) throws Exception;

   List getList(String var1, String var2, int var3, int var4, boolean var5, List var6, List var7) throws Exception;

   List getListByQuequeId(String var1) throws Exception;

   List getNavCheckList() throws Exception;

   List getList(int var1) throws Exception;

   Group getById(int var1) throws Exception;

   Group getByGroupName(String var1);

   List getGroupLevelList() throws Exception;

   GroupLevel getGroupLevelByGroupId(Integer var1) throws Exception;

   List getGroupJobReferList(int var1) throws Exception;

   List getGroupReferOptions(Group var1) throws Exception;

   List getRefGroupById(int var1) throws Exception;

   List getGroupRefById(int var1) throws Exception;

   List getHistoryList(int var1) throws Exception;

   List getGroupsByState(int var1, String var2) throws Exception;

   String getGroupByState(int var1, int var2) throws Exception;

   Group groupExectime(int var1) throws Exception;

   List getAllGroup() throws Exception;

   List getAllEnableGroup() throws Exception;

   List getAllEnableGroupByCron(String var1) throws Exception;

   int getEPCount() throws Exception;

   List getHeaderCount(boolean var1, String var2) throws Exception;

   GroupLevel getGroupLevelById(Integer var1) throws Exception;

   void insertGroupLevel(GroupLevel var1) throws Exception;

   void updateGroupLevel(GroupLevel var1) throws Exception;

   void deleteGroupLevel(Integer var1) throws Exception;

   boolean checkLevelNameExists(GroupLevel var1) throws Exception;

   void setNextFireDateAndQueueName(List var1, HttpServletRequest var2);

   List getGroupLevelListByQueueId(String var1) throws Exception;

   List findListByGroupIds(CommonVO var1);

   List getListByAllParameters(String var1, String var2, String var3, int var4, int var5, boolean var6, List var7, List var8);

   void deleteGroupReferByGroupId(int var1);

   void delete(int var1);
}
