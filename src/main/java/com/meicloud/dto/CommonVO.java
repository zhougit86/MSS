package com.meicloud.dto;

import java.util.ArrayList;
import java.util.List;

public class CommonVO {

   private List groupIds = new ArrayList();
   private Integer groupId;


   public Integer getGroupId() {
      return this.groupId;
   }

   public void setGroupId(Integer groupId) {
      this.groupId = groupId;
   }

   public List getGroupIds() {
      return this.groupIds;
   }

   public void setGroupIds(List groupIds) {
      this.groupIds = groupIds;
   }
}
