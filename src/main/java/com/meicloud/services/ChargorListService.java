package com.meicloud.services;

import com.meicloud.model.Chargor;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface ChargorListService {

   List getChargorList() throws Exception;

   List getGroupLevelListByQueueId(String var1, HttpServletRequest var2);

   Chargor getChargor(Integer var1) throws Exception;

   int addChargor(Chargor var1) throws Exception;

   int deleteChargor(int var1) throws Exception;

   int updateChargor(Chargor var1) throws Exception;

   boolean checkIfExists4New(Chargor var1) throws Exception;

   boolean checkIfExists4Update(Chargor var1) throws Exception;
}
