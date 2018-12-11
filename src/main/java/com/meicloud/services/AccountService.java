package com.meicloud.services;

import com.meicloud.model.Account;
import com.meicloud.model.Server;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface AccountService {

   void add(Account var1);

   void update(Account var1) throws Exception;

   List list(String var1) throws Exception;

   void updateEnable(Map var1) throws Exception;

   Account queryAccount(Account var1) throws Exception;

   boolean updatePassword(Account var1) throws Exception;

   void updateSvnOn(@Param("svnOn") int var1, @Param("account") String var2);

   String queryCmServer() throws Exception;

   List getAllAccountGroup(String var1);

   List getServerList(Server var1);

   Account getByAccount(String var1);

   Account login(String var1, String var2);

   List permList() throws Exception;

   List findAllGroup();

   List findAllAccountGroup(String var1);
}
