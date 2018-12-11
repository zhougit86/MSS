package com.meicloud.services;

import com.meicloud.dao.PermMapper;
import com.meicloud.model.Perm;
import com.meicloud.services.PermService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("permService")
public class PermServiceImpl implements PermService {

   @Autowired
   private PermMapper permMapper;


   public List getList(Perm perm) {
      return this.permMapper.getList(perm);
   }
}
