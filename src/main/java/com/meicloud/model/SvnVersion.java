package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel("控制SVN版本记录")
public class SvnVersion {

   @ApiModelProperty(
      value = "主键ID",
      example = "1"
   )
   private int id;
   @ApiModelProperty("ip号")
   private String ipNum;
   @ApiModelProperty(
      value = "标记名称",
      example = "1.0"
   )
   private Long version;
   @ApiModelProperty("更新时间")
   private Date lastUpdate;


   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getIpNum() {
      return this.ipNum;
   }

   public void setIpNum(String ipNum) {
      this.ipNum = ipNum;
   }

   public Long getVersion() {
      return this.version;
   }

   public void setVersion(Long version) {
      this.version = version;
   }

   public Date getLastUpdate() {
      return this.lastUpdate;
   }

   public void setLastUpdate(Date lastUpdate) {
      this.lastUpdate = lastUpdate;
   }
}
