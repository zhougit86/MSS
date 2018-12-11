package com.meicloud.services;

import com.meicloud.dao.ExportMapper;
import com.meicloud.services.ExportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("exportService")
public class ExportServiceImpl implements ExportService {

   @Autowired
   private ExportMapper exportMapper;


   public List dwTime() {
      try {
         return this.exportMapper.dwTime();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List out1500Time() {
      try {
         return this.exportMapper.out1500Time();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List forbiddenJob() {
      try {
         return this.exportMapper.forbiddenJob();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List errorJob() {
      try {
         return this.exportMapper.errorJob();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public int jobNum() {
      return this.exportMapper.jobNum();
   }

   public int waitNum() {
      return this.exportMapper.waitNum();
   }

   public int successNum() {
      return this.exportMapper.successNum();
   }
}
