package com.meicloud.utils.beans;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfo implements UserDetails {

   private static final long serialVersionUID = -1041327031937199938L;
   private String id;
   private String name;
   private String username;
   private boolean isAccountNonExpired = true;
   private boolean isAccountNonLocked = true;
   private boolean isCredentialsNonExpired = true;
   private boolean isEnabled = true;
   private Set authorities = new HashSet();


   public static long getSerialVersionUID() {
      return -1041327031937199938L;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public boolean isAccountNonExpired() {
      return this.isAccountNonExpired;
   }

   public void setAccountNonExpired(boolean accountNonExpired) {
      this.isAccountNonExpired = accountNonExpired;
   }

   public boolean isAccountNonLocked() {
      return this.isAccountNonLocked;
   }

   public void setAccountNonLocked(boolean accountNonLocked) {
      this.isAccountNonLocked = accountNonLocked;
   }

   public boolean isCredentialsNonExpired() {
      return this.isCredentialsNonExpired;
   }

   public void setCredentialsNonExpired(boolean credentialsNonExpired) {
      this.isCredentialsNonExpired = credentialsNonExpired;
   }

   public boolean isEnabled() {
      return this.isEnabled;
   }

   public void setEnabled(boolean enabled) {
      this.isEnabled = enabled;
   }

   public Set getAuthorities() {
      return this.authorities;
   }

   public String getPassword() {
      return null;
   }

   public void setAuthorities(Set authorities) {
      this.authorities = authorities;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }
}
