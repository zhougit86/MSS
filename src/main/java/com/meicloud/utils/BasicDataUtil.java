package com.meicloud.utils;

import com.meicloud.utils.ColumnInfo;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

public class BasicDataUtil {

   private static Logger LOG = Logger.getLogger("BasicDataUtil");
   private List metadata = new ArrayList();
   private Long totalRows = Long.valueOf(0L);
   private List resultSet = new ArrayList();


   public BasicDataUtil(Object t, List objList, Long count, String type) {
      try {
         this.getCol(t);
         this.totalRows = Long.valueOf(count != null?count.longValue():0L);
         if(type != null) {
            this.resultSet.addAll(objList);
         } else {
            this.getColDetail(objList);
         }
      } catch (Exception var6) {
         LOG.error(var6.getMessage());
      }

   }

   private void getCol(Object t) {
      Field[] fields = t.getClass().getDeclaredFields();
      int m = 1;
      Field[] var7 = fields;
      int var6 = fields.length;

      for(int var5 = 0; var5 < var6; ++var5) {
         Field field = var7[var5];
         if(!field.getName().equals("serialVersionUID")) {
            ColumnInfo col = new ColumnInfo();
            col.setColIndex(Integer.valueOf(m));
            col.setColName(field.getName());
            String classType = field.getType().toString();
            int lastIndex = classType.lastIndexOf(".");
            col.setColType(classType.substring(lastIndex + 1));
            this.metadata.add(col);
            ++m;
         }
      }

   }

   private void getColDetail(List tList) throws IllegalAccessException {
      if(tList != null) {
         Iterator var3 = tList.iterator();

         while(var3.hasNext()) {
            Object obj = var3.next();
            ArrayList newList = new ArrayList();
            Field[] fields = obj.getClass().getDeclaredFields();
            Field[] var9 = fields;
            int var8 = fields.length;

            for(int var7 = 0; var7 < var8; ++var7) {
               Field field = var9[var7];
               if(!field.getName().equals("serialVersionUID")) {
                  field.setAccessible(true);
                  newList.add(field.get(obj));
               }
            }

            this.resultSet.add(newList);
         }
      }

   }

   public List getMetadata() {
      return this.metadata;
   }

   public void setMetadata(List metadata) {
      this.metadata = metadata;
   }

   public Long getTotalRows() {
      return this.totalRows;
   }

   public void setTotalRows(Long totalRows) {
      this.totalRows = totalRows;
   }

   public List getResultSet() {
      return this.resultSet;
   }

   public void setResultSet(List resultSet) {
      this.resultSet = resultSet;
   }
}
