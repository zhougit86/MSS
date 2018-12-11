package com.meicloud.dao;

import java.util.List;

public interface JobExecStatusMapper {

   List getJobCountOfEachStateByGroup4Today(String[] var1);

   List getStatOfLatestRoundHis(String[] var1);

   int getRunServers(String var1) throws Exception;

   List getJobStateCount() throws Exception;
}
