package com.meicloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("数据源配置信息")
public class DataSource {

   @ApiModelProperty(
      value = "系统Code",
      example = "localhost_test",
      required = true
   )
   private String dsrcCode;
   @ApiModelProperty(
      value = "数据库名称",
      example = "本地测试",
      required = true
   )
   private String dsrcName;
   @ApiModelProperty(
      hidden = true
   )
   private String beanName;
   @ApiModelProperty(
      value = "数据库类型",
      example = "MYSQL",
      required = true
   )
   private String dbType;
   @ApiModelProperty(
      value = "数据库IP",
      example = "10.24.66.169",
      required = true
   )
   private String ip;
   @ApiModelProperty(
      value = "端口",
      example = "3306",
      required = true
   )
   private String port;
   @ApiModelProperty(
      value = "数据库连接URL",
      example = "jdbc:mysql://localhost:3306/dc?useUnicode=true&characterEncoding=UTF-8",
      required = true
   )
   private String url;
   @ApiModelProperty(
      value = "库名称（数据库实例）",
      example = "dc",
      required = true
   )
   private String serviceName;
   @ApiModelProperty(
      value = "数据库连接用户名",
      example = "root",
      required = true
   )
   private String username;
   @ApiModelProperty(
      value = "用户信息密码",
      example = "123456",
      required = true
   )
   private String password;
   @ApiModelProperty(
      value = "初始化连接池大小",
      example = "0"
   )
   private Integer initSize;
   @ApiModelProperty(
      value = "最小空闲连接数",
      example = "0"
   )
   private Integer minIdle;
   @ApiModelProperty(
      value = "最大连接会话数目",
      example = "10"
   )
   private Integer maxActive;
   @ApiModelProperty(
      value = "连接等待超时的时间",
      example = "120000"
   )
   private Integer maxWait;
   @ApiModelProperty(
      value = " 检测间隔",
      example = "120000"
   )
   private Integer timeBetweenEvictionRunMillis;
   @ApiModelProperty(
      value = "连接在池中最小生存的时间",
      example = "1800000"
   )
   private Integer minEvictableIdleTimeMillis;
   @ApiModelProperty(
      value = "验证查询SQL",
      example = "select * from sys_user"
   )
   private String validationQuery;
   @ApiModelProperty(
      value = "用户信息密码",
      example = "120000"
   )
   private Integer queryTimeout;


   public String getDsrcCode() {
      return this.dsrcCode;
   }

   public void setDsrcCode(String dsrcCode) {
      this.dsrcCode = dsrcCode;
   }

   public String getDsrcName() {
      return this.dsrcName;
   }

   public void setDsrcName(String dsrcName) {
      this.dsrcName = dsrcName;
   }

   public String getBeanName() {
      return this.beanName;
   }

   public void setBeanName(String beanName) {
      this.beanName = beanName;
   }

   public String getDbType() {
      return this.dbType;
   }

   public void setDbType(String dbType) {
      this.dbType = dbType;
   }

   public String getIp() {
      return this.ip;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }

   public String getPort() {
      return this.port;
   }

   public void setPort(String port) {
      this.port = port;
   }

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getServiceName() {
      return this.serviceName;
   }

   public void setServiceName(String serviceName) {
      this.serviceName = serviceName;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Integer getInitSize() {
      return this.initSize;
   }

   public void setInitSize(Integer initSize) {
      this.initSize = initSize;
   }

   public Integer getMinIdle() {
      return this.minIdle;
   }

   public void setMinIdle(Integer minIdle) {
      this.minIdle = minIdle;
   }

   public Integer getMaxActive() {
      return this.maxActive;
   }

   public void setMaxActive(Integer maxActive) {
      this.maxActive = maxActive;
   }

   public Integer getMaxWait() {
      return this.maxWait;
   }

   public void setMaxWait(Integer maxWait) {
      this.maxWait = maxWait;
   }

   public Integer getTimeBetweenEvictionRunMillis() {
      return this.timeBetweenEvictionRunMillis;
   }

   public void setTimeBetweenEvictionRunMillis(Integer timeBetweenEvictionRunMillis) {
      this.timeBetweenEvictionRunMillis = timeBetweenEvictionRunMillis;
   }

   public Integer getMinEvictableIdleTimeMillis() {
      return this.minEvictableIdleTimeMillis;
   }

   public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
      this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
   }

   public String getValidationQuery() {
      return this.validationQuery;
   }

   public void setValidationQuery(String validationQuery) {
      this.validationQuery = validationQuery;
   }

   public Integer getQueryTimeout() {
      return this.queryTimeout;
   }

   public void setQueryTimeout(Integer queryTimeout) {
      this.queryTimeout = queryTimeout;
   }
}
