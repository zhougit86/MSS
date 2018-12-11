package com.meicloud.services;

import com.meicloud.model.Proportion;
import java.util.List;

public interface IndexService {

   List getTopicList() throws Exception;

   List topicListByQueueId(String var1) throws Exception;

   List topicListByQueueIds(List var1) throws Exception;

   List getLevelNot0() throws Exception;

   List selectLevelByTopicId(Integer var1);

   List getLevelNot0ByQueueId(String var1) throws Exception;

   List getLevelNot0ByQueueIds(List var1) throws Exception;

   List getGroupByLeverAndRefer(int var1, int var2) throws Exception;

   List proportionsLatestRound(List var1, boolean var2) throws Exception;

   Proportion proportionsLatestRound(Integer var1, boolean var2) throws Exception;

   int getRunServers(String var1) throws Exception;

   List getJobStateCount() throws Exception;
}
