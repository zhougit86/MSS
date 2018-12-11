package com.meicloud.dao;

import com.meicloud.model.SvnVersion;
import org.apache.ibatis.annotations.Param;

public interface SvnVersionMapper {

   void add(SvnVersion var1);

   void update(SvnVersion var1);

   SvnVersion getByIpNum(@Param("ipNum") String var1);
}
