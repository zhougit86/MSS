package com.meicloud.services;

import com.meicloud.dao.BaseMapper;
import com.meicloud.model.Account;
import com.meicloud.model.BaseBean;
import com.meicloud.services.BaseService;
import com.meicloud.utils.SessionUser;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl implements BaseService {

   @Autowired
   protected BaseMapper dao;


   public BaseBean get(String id) {
      return (BaseBean)this.dao.get(id);
   }

   @Deprecated
   public BaseBean get(BaseBean entity) {
      return (BaseBean)this.dao.get((Object)entity);
   }

   public BaseBean findOne(BaseBean entity) {
      return (BaseBean)this.dao.findOne(entity);
   }

   public List findList(BaseBean entity) {
      return this.dao.findList(entity);
   }

   public List findAllList(BaseBean entity) {
      return this.dao.findAllList(entity);
   }

   public List findAllList() {
      return this.dao.findAllList();
   }

   public int save(BaseBean entity) {
      entity.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
      entity.setDelFlag("0");
      entity.setCreateDate(new Date());
      entity.setUpdateDate(new Date());
      Account account = SessionUser.getSessionUser();
      entity.setCreateBy(account.getAccount());
      entity.setUpdateBy(account.getAccount());
      return this.dao.insert(entity);
   }

   public int saveInfo(BaseBean entity) {
      entity.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
      entity.setDelFlag("0");
      entity.setCreateDate(new Date());
      entity.setUpdateDate(new Date());
      return this.dao.insert(entity);
   }

   public int update(BaseBean entity) {
      entity.setUpdateDate(new Date());
      Account account = SessionUser.getSessionUser();
      entity.setUpdateBy(account.getAccount());
      return this.dao.update(entity);
   }

   public int delete(String id) {
      return this.dao.delete(id);
   }

   public int delete(BaseBean entity) {
      return this.dao.delete((Object)entity);
   }
}
