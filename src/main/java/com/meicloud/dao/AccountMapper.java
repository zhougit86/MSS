package com.meicloud.dao;

import com.meicloud.model.Account;
import com.meicloud.model.Server;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {

   Account queryAccount(Account var1);

   int updatePassword(Account var1);

   void updateSvnOn(@Param("svnOn") int var1, @Param("account") String var2);

   String queryCmServer();

   List getServerList(Server var1);

   void add(Account var1);

   void update(Account var1);

   void delete(String var1);

   List list(String var1) throws Exception;

   Account queryByAccount(String var1);

   Account queryByAccountAndPassword(Account var1);

   void updateEnable(Map var1);

   List permList();
}
