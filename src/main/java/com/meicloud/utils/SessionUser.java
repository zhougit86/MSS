package com.meicloud.utils;

import com.meicloud.model.Account;

public class SessionUser {

   static ThreadLocal sessionUser = new ThreadLocal();


   public static void setSessionUser(Account account) {
      sessionUser.set(account);
   }

   public static Account getSessionUser() {
      return (Account)sessionUser.get();
   }
}
