package com.meicloud.services;

import com.meicloud.dao.ChargorMapper;
import com.meicloud.model.Chargor;
import com.meicloud.model.Role;
import com.meicloud.services.ChargorListService;
import com.meicloud.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("chargorListService")
public class ChargorListServiceImpl implements ChargorListService {

   @Autowired
   private ChargorMapper chargorMapper;


   public List getChargorList() throws Exception {
      try {
         return this.chargorMapper.getChargorList();
      } catch (Exception var2) {
         throw var2;
      }
   }

   public List getGroupLevelListByQueueId(String queueId, HttpServletRequest request) {
      if(((Boolean)request.getSession().getAttribute("ifAdmin")).booleanValue()) {
         return this.chargorMapper.getGroupLevelListByQueueId(queueId);
      } else {
         List roleList = (List)request.getSession().getAttribute("roles");
         if(Utils.isEmpityCollection(roleList)) {
            return null;
         } else {
            ArrayList list = new ArrayList();
            Iterator var6 = roleList.iterator();

            while(var6.hasNext()) {
               Role role = (Role)var6.next();
               list.add(role.getRoleId());
            }

            return this.chargorMapper.getGroupLevelListByQueueId(queueId);
         }
      }
   }

   public Chargor getChargor(Integer id) throws Exception {
      return this.chargorMapper.getChargor(id);
   }

   public int addChargor(Chargor chargor) throws Exception {
      try {
         return this.chargorMapper.addChargor(chargor);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public int deleteChargor(int Id) throws Exception {
      try {
         return this.chargorMapper.deleteChargor(Id);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public int updateChargor(Chargor chargor) throws Exception {
      try {
         return this.chargorMapper.upadteChargor(chargor);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public boolean checkIfExists4New(Chargor chargor) throws Exception {
      try {
         return this.chargorMapper.checkIfExists4New(chargor) > 0;
      } catch (Exception var3) {
         throw var3;
      }
   }

   public boolean checkIfExists4Update(Chargor chargor) throws Exception {
      try {
         return this.chargorMapper.checkIfExists4Update(chargor) > 0;
      } catch (Exception var3) {
         throw var3;
      }
   }
}
