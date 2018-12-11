package com.meicloud.dao;

import com.meicloud.model.AccountGroup;
import java.util.List;

public interface AccountGroupMapper {

   List getAllAccountGroup(String var1);

   void deleteAccountGroup(String var1);

   void addAccountGroup(AccountGroup var1);
}
