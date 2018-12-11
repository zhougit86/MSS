package com.meicloud.services;

import com.meicloud.dao.SvnVersionMapper;
import com.meicloud.model.SvnVersion;
import com.meicloud.services.SvnVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("svnVersionService")
public class SvnVersionServiceImpl implements SvnVersionService {

   @Autowired
   private SvnVersionMapper svnVersionMapper;


   public void add(SvnVersion svnVersion) {
      this.svnVersionMapper.add(svnVersion);
   }

   public void update(SvnVersion svnVersion) {
      this.svnVersionMapper.update(svnVersion);
   }

   public SvnVersion getByIpNum(String ipNum) {
      return this.svnVersionMapper.getByIpNum(ipNum);
   }
}
