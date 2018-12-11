package com.meicloud.services;

import com.meicloud.dao.AccountGroupMapper;
import com.meicloud.dao.AccountMapper;
import com.meicloud.dao.GroupMapper;
import com.meicloud.model.Account;
import com.meicloud.model.AccountGroup;
import com.meicloud.model.Server;
import com.meicloud.services.AccountService;
import com.meicloud.utils.StringUtil;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

   @Autowired
   private AccountMapper accountMapper;
   @Autowired
   private AccountGroupMapper accountGroupMapper;
   @Autowired
   private GroupMapper groupMapper;


   public void add(Account accout) {
      String password = accout.getPassword();
      if(password != null && !"".equals(password)) {
         String md5 = StringUtil.toMD5(password);
         accout.setPassword(md5);
      }

      this.accountMapper.add(accout);
   }

   public void update(Account accout) throws Exception {
      String password = accout.getPassword();
      if(password != null && !"".equals(password)) {
         String md5 = StringUtil.toMD5(password);
         accout.setPassword(md5);
      } else {
         accout.setPassword((String)null);
      }

      this.accountMapper.update(accout);
   }

   public void updateEnable(Map map) throws Exception {
      this.accountMapper.updateEnable(map);
   }

   public List list(String queryKey) throws Exception {
      try {
         return this.accountMapper.list(queryKey);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public Account login(String username, String password) {
      Account accout = new Account();
      accout.setAccount(username);
      accout.setPassword(StringUtil.toMD5(password));
      Account account = this.accountMapper.queryByAccountAndPassword(accout);
      return account;
   }

   public Account getByAccount(String account) {
      try {
         return this.accountMapper.queryByAccount(account);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List permList() throws Exception {
      List list = this.accountMapper.permList();
      return list;
   }

   public List findAllGroup() {
      return this.groupMapper.findAllGroup();
   }

   public void handgroup(String account, String groupIds) {
      this.accountGroupMapper.deleteAccountGroup(account);
      if(groupIds != null && !groupIds.equals("")) {
         String[] strs = groupIds.split("\\@");
         String[] var7 = strs;
         int var6 = strs.length;

         for(int var5 = 0; var5 < var6; ++var5) {
            String str = var7[var5];
            AccountGroup accountGroup = new AccountGroup();
            accountGroup.setAccount(account);
            accountGroup.setGroupId(Integer.valueOf(Integer.parseInt(str)));
            this.accountGroupMapper.addAccountGroup(accountGroup);
         }
      }

   }

   public Account queryAccount(Account query) throws Exception {
      try {
         return this.accountMapper.queryAccount(query);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public boolean updatePassword(Account account) throws Exception {
      boolean result = false;

      try {
         result = this.accountMapper.updatePassword(account) > 0;
         return result;
      } catch (Exception var4) {
         throw var4;
      }
   }

   public void updateSvnOn(@Param("svnOn") int svnOn, @Param("account") String account) {
      try {
         this.accountMapper.updateSvnOn(svnOn, account);
      } catch (Exception var4) {
         throw var4;
      }
   }

   public String queryCmServer() throws Exception {
      try {
         return this.accountMapper.queryCmServer();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List findAllAccountGroup(String account) {
      return this.accountGroupMapper.getAllAccountGroup(account);
   }

   public List getAllAccountGroup(String account) {
      return this.accountGroupMapper.getAllAccountGroup(account);
   }

   public List getServerList(Server server) {
      return this.accountMapper.getServerList(server);
   }
}
