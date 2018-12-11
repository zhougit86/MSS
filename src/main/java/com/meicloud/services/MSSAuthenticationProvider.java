package com.meicloud.services;

import com.meicloud.datasource.DynamicDataSourceContextHolder;
import com.meicloud.model.Account;
import com.meicloud.services.AccountService;
import com.meicloud.services.CustomUserDetailsService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service("mSSAuthenticationProvider")
public class MSSAuthenticationProvider implements AuthenticationProvider {

   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   @Resource
   private AccountService accountService;
   @Resource
   private CustomUserDetailsService customUserDetailsService;


   public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      this.logger.info("用户的用户名: {}", authentication.getName());
      DynamicDataSourceContextHolder.setDataSourceType("default");
      String username = authentication.getName();
      String password = (String)authentication.getCredentials();
      Account user = this.accountService.getByAccount(username);
      if(user == null) {
         throw new BadCredentialsException("Username not found.");
      } else if(!password.equals(user.getPassword())) {
         throw new BadCredentialsException("Wrong password.");
      } else {
         UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, password);
         this.customUserDetailsService.loadUserDetails(usernamePasswordAuthenticationToken);
         return usernamePasswordAuthenticationToken;
      }
   }

   public boolean supports(Class authentication) {
      return UsernamePasswordAuthenticationToken.class.equals(authentication);
   }
}
