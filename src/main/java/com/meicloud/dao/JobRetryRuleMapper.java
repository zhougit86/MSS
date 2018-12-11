package com.meicloud.dao;

import com.meicloud.model.RetryRule;
import com.meicloud.model.Server;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobRetryRuleMapper {

   void add(RetryRule var1);

   void delete(int var1);

   void update(RetryRule var1);

   RetryRule getById(int var1);

   RetryRule getByRetryName(@Param("retryName") String var1);

   List list();

   int checkIfExists4Update(RetryRule var1);

   int checkIfExists4New(RetryRule var1);

   List queryList();

   String queryCmServerUrl();

   List getServerList(Server var1);
}
