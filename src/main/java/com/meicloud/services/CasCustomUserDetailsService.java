//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.meicloud.services;

import com.meicloud.datasource.DynamicDataSourceContextHolder;
import com.meicloud.model.Account;
import com.meicloud.services.AccountService;
import com.meicloud.utils.Utils;
import com.meicloud.utils.beans.AuthorityInfo;
import com.meicloud.utils.beans.UserInfo;
import java.util.HashSet;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CasCustomUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private AccountService accountService;

    public CasCustomUserDetailsService() {
    }

    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
        DynamicDataSourceContextHolder.setDataSourceType("default");
        UserInfo userInfo = new UserInfo();
        this.logger.info("查询ladp库是否存在用户当前的用户名是：" + token.getName());
        Account user = null;
        if(Utils.isEmptyStr(token.getName())) {
            throw new UsernameNotFoundException("token过期,请重新登录!");
        } else {
            try {
                user = this.accountService.getByAccount(token.getName());
            } catch (Exception var6) {
                this.logger.error(var6.getMessage());
                throw var6;
            }

            if(user == null) {
                throw new UsernameNotFoundException("用户不存在，请重新登录!");
            } else {
                userInfo.setUsername(Utils.isEmptyStr(user.getRealName())?token.getName():user.getRealName());
                userInfo.setName(token.getName());
                this.logger.info("查询结束：" + token.getName());
                HashSet authorities = new HashSet();
                AuthorityInfo authorityInfo = new AuthorityInfo("TEST");
                authorities.add(authorityInfo);
                userInfo.setAuthorities(authorities);
                return userInfo;
            }
        }
    }
}
