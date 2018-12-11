package com.meicloud.dao;

import com.meicloud.model.GroupLevel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupLevelMapper {

   List getList();

   List getLevelNot0();

   GroupLevel getLevelById(Integer var1);

   int insert(GroupLevel var1);

   int update(GroupLevel var1);

   int delete(Integer var1);

   List selectByTopicId(Integer var1);

   int checkLevelNameExists(GroupLevel var1);

   List getGroupLevelListByQueueId(String var1) throws Exception;

   List getLevelNot0ByQueueIds(List var1) throws Exception;

   GroupLevel getLevelByLevelName(@Param("levelName") String var1);
}
