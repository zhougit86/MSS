package com.meicloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
   prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   protected void configure(HttpSecurity http) throws Exception {
      ((HttpSecurity)((HttpSecurity)((FormLoginConfigurer)((FormLoginConfigurer)((FormLoginConfigurer)((HttpSecurity)((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((HttpSecurity)http.csrf().disable()).authorizeRequests().antMatchers(new String[]{"/**"})).hasRole("USER").antMatchers(new String[]{"/**"})).authenticated().antMatchers(new String[]{"/login"})).permitAll().and()).formLogin().loginPage("/login/index.html").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login")).permitAll()).defaultSuccessUrl("/")).and()).logout().logoutSuccessUrl("/logout").and()).httpBasic();
   }

   public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers(new String[]{"/**", "/**"});
   }
}
