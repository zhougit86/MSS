package com.meicloud.services;

import com.meicloud.dao.JobRetryRuleMapper;
import com.meicloud.model.RetryRule;
import com.meicloud.model.Server;
import com.meicloud.services.JobRetryRuleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("jobRetryRuleService")
public class JobRetryRuleServiceImpl implements JobRetryRuleService {

   @Autowired
   private JobRetryRuleMapper jobRetryRuleMapper;


   public void add(RetryRule retryRule) throws Exception {
      try {
         this.jobRetryRuleMapper.add(retryRule);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void delete(int retryId) throws Exception {
      try {
         this.jobRetryRuleMapper.delete(retryId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void update(RetryRule retryRule) throws Exception {
      try {
         this.jobRetryRuleMapper.update(retryRule);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public RetryRule getById(int retryId) throws Exception {
      try {
         return this.jobRetryRuleMapper.getById(retryId);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List list() {
      try {
         return this.jobRetryRuleMapper.list();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public boolean checkIfExists4Update(RetryRule retryRule) throws Exception {
      try {
         return this.jobRetryRuleMapper.checkIfExists4Update(retryRule) > 0;
      } catch (Exception var3) {
         throw var3;
      }
   }

   public boolean checkIfExists4New(RetryRule retryRule) throws Exception {
      try {
         return this.jobRetryRuleMapper.checkIfExists4New(retryRule) > 0;
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List queryList() {
      try {
         return this.jobRetryRuleMapper.queryList();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public String queryCmServerUrl() {
      try {
         return this.jobRetryRuleMapper.queryCmServerUrl();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List getServerList(Server server) {
      return this.jobRetryRuleMapper.getServerList(server);
   }
}
