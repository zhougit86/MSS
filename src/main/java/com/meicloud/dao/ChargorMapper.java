package com.meicloud.dao;

import com.meicloud.model.Chargor;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChargorMapper {

   List getChargorList();

   Chargor getChargor(Integer var1) throws Exception;

   Chargor getChargorByName(@Param("name") String var1);

   List getGroupLevelListByQueueId(String var1);

   int addChargor(Chargor var1);

   int upadteChargor(Chargor var1);

   int deleteChargor(int var1);

   int checkIfExists4New(Chargor var1);

   int checkIfExists4Update(Chargor var1);
}
