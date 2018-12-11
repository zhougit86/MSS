package com.meicloud.utils.beans;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityInfo implements GrantedAuthority {

   private static final long serialVersionUID = -175781100474818800L;
   private String authority;


   public AuthorityInfo(String authority) {
      this.authority = authority;
   }

   public String getAuthority() {
      return this.authority;
   }

   public void setAuthority(String authority) {
      this.authority = authority;
   }
}
