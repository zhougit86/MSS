package com.meicloud.dto;

import java.util.ArrayList;
import java.util.List;

public class TopicVO {

   private String queueId;
   private List roles = new ArrayList();


   public String getQueueId() {
      return this.queueId;
   }

   public void setQueueId(String queueId) {
      this.queueId = queueId;
   }

   public List getRoles() {
      return this.roles;
   }

   public void setRoles(List roles) {
      this.roles = roles;
   }
}
