package com.meicloud.services;

import com.meicloud.model.SvnVersion;

public interface SvnVersionService {

   void add(SvnVersion var1);

   void update(SvnVersion var1);

   SvnVersion getByIpNum(String var1);
}
