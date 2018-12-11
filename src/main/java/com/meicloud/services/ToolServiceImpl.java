package com.meicloud.services;

import com.meicloud.dao.ToolMapper;
import com.meicloud.model.Tool;
import com.meicloud.services.ToolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("toolService")
public class ToolServiceImpl implements ToolService {

   @Autowired
   private ToolMapper toolMapper;


   public List dwTime() {
      try {
         return this.toolMapper.dwTime();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List out1500Time() {
      try {
         return this.toolMapper.out1500Time();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List forbiddenJob() {
      try {
         return this.toolMapper.forbiddenJob();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List errorJob() {
      try {
         return this.toolMapper.errorJob();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public int jobNum() {
      return this.toolMapper.jobNum();
   }

   public int waitNum() {
      return this.toolMapper.waitNum();
   }

   public int successNum() {
      return this.toolMapper.successNum();
   }

   public List list() {
      try {
         return this.toolMapper.list();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public void add(Tool tool) {
      try {
         this.toolMapper.add(tool);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public void delete(Tool tool) {
      try {
         this.toolMapper.delete(tool);
      } catch (Exception var3) {
         throw var3;
      }
   }
}
