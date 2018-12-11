package com.meicloud.services;

import com.meicloud.model.RetryRule;
import com.meicloud.model.Server;
import java.util.List;

public interface JobRetryRuleService {

   void add(RetryRule var1) throws Exception;

   void delete(int var1) throws Exception;

   void update(RetryRule var1) throws Exception;

   RetryRule getById(int var1) throws Exception;

   List list();

   boolean checkIfExists4Update(RetryRule var1) throws Exception;

   boolean checkIfExists4New(RetryRule var1) throws Exception;

   List queryList() throws Exception;

   String queryCmServerUrl() throws Exception;

   List getServerList(Server var1);
}
