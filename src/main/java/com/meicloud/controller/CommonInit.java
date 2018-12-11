package com.meicloud.controller;

import com.meicloud.Common;
import com.meicloud.model.Perm;
import com.meicloud.services.PermService;
import com.meicloud.utils.Utils;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommonInit implements CommandLineRunner {

   private static final Logger logger = LoggerFactory.getLogger(CommonInit.class);
   @Resource
   private PermService permService;


   public void run(String ... strings) throws Exception {
      logger.info(">>>>>>>>>>>>>>>开始初始化功能菜单<<<<<<<<<<<<<");
      Perm perm = new Perm();
      perm.setPermType("Y");
      List permList = this.permService.getList(perm);
      if(!Utils.isEmpityCollection(permList)) {
         Iterator var5 = permList.iterator();

         while(var5.hasNext()) {
            Perm p = (Perm)var5.next();
            Common.allParmMap.put(p.getPermId(), p.getPermPath());
         }
      }

      logger.info(">>>>>>>>>>>>>>>初始化功能菜单完成,操作功能数" + Common.allParmMap.size() + "<<<<<<<<<<<<<");
   }
}
