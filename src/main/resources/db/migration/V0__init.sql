/*
Navicat MySQL Data Transfer

Source Server         : 10.18.37.30
Source Server Version : 50505
Source Host           : 10.18.37.30:3306
Source Database       : mss

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-05-02 14:34:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apd_fnd_schedule_parameter
-- ----------------------------
DROP TABLE IF EXISTS `apd_fnd_schedule_parameter`;
CREATE TABLE `apd_fnd_schedule_parameter` (
  `SCHEDULE_PARA_ID` bigint(64) NOT NULL AUTO_INCREMENT,
  `PARAMETER_NAME` varchar(240) NOT NULL,
  `PARAMETER_SORT` int(11) DEFAULT NULL,
  `SUBJECT_NAME` varchar(240) NOT NULL,
  `MAPPING_NAME` varchar(240) NOT NULL,
  `SESSION_NAME` varchar(240) NOT NULL,
  `WORKFLOW_NAME` varchar(240) NOT NULL,
  `PARAMETER_VALUE` varchar(4000) NOT NULL,
  `FORMAT_MASK` varchar(30) DEFAULT NULL,
  `PARA_OFFSET` int(11) DEFAULT NULL,
  `FREQUENCY` varchar(30) DEFAULT NULL,
  `ENABLE_FLAG` varchar(1) DEFAULT NULL,
  `START_DATE_ACTIVE` datetime DEFAULT NULL,
  `END_DATE_ACTIVE` datetime DEFAULT NULL,
  `CREATION_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(128) NOT NULL DEFAULT '-1',
  `LAST_UPDATED_BY` varchar(128) NOT NULL DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` int(11) DEFAULT NULL,
  `PARAMETER_DESC` varchar(240) DEFAULT NULL,
  `PARAMETER_VALUE_INI` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`SCHEDULE_PARA_ID`),
  UNIQUE KEY `APD_FND_SCHEDULE_PARAMETER_U1` (`PARAMETER_NAME`,`WORKFLOW_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apd_fnd_schedule_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for apd_ods_parameter
-- ----------------------------
DROP TABLE IF EXISTS `apd_ods_parameter`;
CREATE TABLE `apd_ods_parameter` (
  `JOB_NAME` varchar(100) NOT NULL COMMENT '作业名称',
  `SRC_NAME` varchar(50) DEFAULT NULL COMMENT '源schema',
  `SRC_TABLE_NAME` varchar(50) DEFAULT NULL COMMENT '源表',
  `TAR_STG_TAB_NAME` varchar(50) DEFAULT NULL COMMENT '目标stg表',
  `TAR_ODS_TAB_NAME` varchar(50) DEFAULT NULL COMMENT '目标ODS表',
  `TAR_HIS_TAB_NAME` varchar(50) DEFAULT NULL COMMENT '目标HIS表',
  `DB_HOST` varchar(50) DEFAULT NULL COMMENT '源系统主机',
  `DB_NAME` varchar(50) DEFAULT NULL COMMENT '源系统数据库名',
  `DB_PASSWORD` varchar(50) DEFAULT NULL COMMENT '源系统密码',
  `DB_PORT` varchar(50) DEFAULT NULL COMMENT '源系统端口',
  `DB_USER` varchar(50) DEFAULT NULL COMMENT '源系统用户',
  `DB_JDBC` varchar(100) DEFAULT NULL COMMENT '源系统JDBC',
  `SPLIT_BY` varchar(1000) DEFAULT NULL COMMENT '定义sqoop语句split-by',
  `TERMINATED_BY` varchar(50) DEFAULT NULL COMMENT '定义sqoop语句fields-terminated-by',
  `JOIN_CONDITION` varchar(500) DEFAULT NULL COMMENT '更新机制的JOIN条件',
  `EXTRACT_MODE` bigint(20) DEFAULT NULL COMMENT '判断走覆盖、更新、插入路线的值',
  `INCREMENT_FIELD` varchar(50) DEFAULT NULL COMMENT '定义sqoop语句where条件(LAST_UPDATE_DATE)',
  `PARAR_NUM` bigint(20) DEFAULT NULL COMMENT '定义sqoop语句-m',
  `TAB_OR_VIEW` bigint(20) DEFAULT NULL,
  `QUERY_STATEMENT` varchar(16000) DEFAULT NULL COMMENT '自定义sql',
  `SRC_DB_TYPE` varchar(50) DEFAULT NULL COMMENT '源系统类型',
  `TARGET_DIR` varchar(500) DEFAULT NULL COMMENT '定义sqoop语句target-dir ',
  `W_CREATE_DT` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `W_INSERT_DT` varchar(50) DEFAULT NULL COMMENT '插入时间',
  `FETCH_SIZE` varchar(50) DEFAULT NULL COMMENT '定义sqoop语句fetch-size',
  `SET_ENGINE` varchar(50) DEFAULT NULL COMMENT '定义hive.execution.engine参数',
  PRIMARY KEY (`JOB_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ODS作业配置';

-- ----------------------------
-- Records of apd_ods_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for cm_account
-- ----------------------------
DROP TABLE IF EXISTS `cm_account`;
CREATE TABLE `cm_account` (
  `ACCOUNT` varchar(128) NOT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  `PERM_STRING` varchar(1024) DEFAULT NULL,
  `C_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `IS_ENABLE` tinyint(1) DEFAULT '1',
  `PAGES` int(11) DEFAULT '25',
  `REAL_NAME` varchar(32) DEFAULT '',
  `SVN_ON` tinyint(1) DEFAULT '0' COMMENT '查询作业列表时是否追加ETL SVN文件版本信息',
  PRIMARY KEY (`ACCOUNT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帐号信息表';

-- ----------------------------
-- Records of cm_account
-- ----------------------------
INSERT INTO `cm_account` VALUES ('admin', 'e10adc3949ba59abbe56e057f20f883e', null, now(), '1', '25', '系统管理员', '1');
INSERT INTO `cm_account` VALUES ('zouhb2', null, null, now(), '1', '25', '邹汉标', '0');

-- ----------------------------
-- Table structure for cm_account_group
-- ----------------------------
DROP TABLE IF EXISTS `cm_account_group`;
CREATE TABLE `cm_account_group` (
  `ACCOUNT` varchar(100) NOT NULL COMMENT '账号',
  `GROUPID` int(11) NOT NULL COMMENT 'JOBGroupId'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账号JOB组表';

-- ----------------------------
-- Records of cm_account_group
-- ----------------------------

-- ----------------------------
-- Table structure for cm_chargor
-- ----------------------------
DROP TABLE IF EXISTS `cm_chargor`;
CREATE TABLE `cm_chargor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(128) DEFAULT NULL,
  `PHONE` varchar(32) DEFAULT NULL,
  `EMAIL` varchar(128) DEFAULT NULL,
  `ROLE` varchar(16) DEFAULT '',
  `QUEUE_ID` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='作业责任人';

-- ----------------------------
-- Records of cm_chargor
-- ----------------------------
INSERT INTO `cm_chargor` VALUES ('2', 'demo1', '15308444203', '141@qq.com', '', '1');
INSERT INTO `cm_chargor` VALUES ('3', 'demo2', '', '', '', '1');
INSERT INTO `cm_chargor` VALUES ('4', 'demo3', '744626', 'meidia@media.com', '', '2');
INSERT INTO `cm_chargor` VALUES ('6', 'sujq2', '15308444203', '131@media.com', '', '2');
INSERT INTO `cm_chargor` VALUES ('8', 'demo5-z1', '1111', '11@meicloud.com', '', '7');
INSERT INTO `cm_chargor` VALUES ('9', 'demo1', '13900000000', '1@qq.com', '', '7');

-- ----------------------------
-- Table structure for cm_database
-- ----------------------------
DROP TABLE IF EXISTS `cm_database`;
CREATE TABLE `cm_database` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '作业编码',
  `HOST` varchar(32) DEFAULT NULL,
  `PORT` varchar(32) DEFAULT NULL,
  `DB_TYPE` varchar(16) DEFAULT NULL,
  `DB_NAME` varchar(128) DEFAULT NULL,
  `USERNAME` varchar(128) DEFAULT NULL,
  `PASSWORD` varchar(128) DEFAULT NULL,
  `SID` varchar(128) DEFAULT NULL,
  `SERVICE_NAME` varchar(128) DEFAULT NULL,
  `DST_DB_NAME` varchar(128) DEFAULT NULL,
  `DB_MODE` varchar(10) DEFAULT NULL,
  `SRC_USER_NAME_KEY` varchar(32) DEFAULT NULL,
  `SRC_PASSWORD_KEY` varchar(32) DEFAULT NULL,
  `SRC_JDBC_URL_KEY` varchar(32) DEFAULT NULL,
  `DST_DB_NAME_KEY` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='连接信息表';

-- ----------------------------
-- Records of cm_database
-- ----------------------------

-- ----------------------------
-- Table structure for cm_group
-- ----------------------------
DROP TABLE IF EXISTS `cm_group`;
CREATE TABLE `cm_group` (
  `GROUP_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '组编码',
  `LEVEL_ID` int(11) DEFAULT NULL COMMENT '层级编码',
  `G_NAME` varchar(256) DEFAULT NULL COMMENT '组名称',
  `CRON` varchar(32) DEFAULT NULL COMMENT '调度表达式',
  `REFERED_GROUP_IDS` varchar(2048) DEFAULT NULL COMMENT '依赖组编码字符串',
  `C_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `U_DATE` datetime DEFAULT NULL COMMENT '最后修改时间爱你',
  `IS_ENABLE` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `IS_SORT_IN_GROUP` tinyint(1) DEFAULT '0' COMMENT '组内是否排序',
  `PROJECT_NAME` varchar(255) DEFAULT NULL COMMENT '项目组名称',
  `NOTICE` varchar(256) DEFAULT NULL COMMENT '注意事项',
  `DESC` varchar(128) DEFAULT NULL COMMENT '备注',
  `C_USER` varchar(16) DEFAULT NULL COMMENT '创建用户',
  `U_USER` varchar(16) DEFAULT NULL COMMENT '最后更新用户',
  `TOPIC_ID` int(11) DEFAULT '0' COMMENT '主题编码',
  `PARALLEL_LIMIT` int(11) DEFAULT '-11',
  `IS_TIME_TRIGGER` tinyint(1) DEFAULT '1' COMMENT '是否依赖触发,0是,1否',
  `QUEUE_ID` varchar(64) DEFAULT '1' COMMENT '作业分发至执行集群ID',
  PRIMARY KEY (`GROUP_ID`),
  KEY `idx_cm_group_l2` (`LEVEL_ID`) USING BTREE,
  KEY `idx_cm_group_t1` (`TOPIC_ID`) USING BTREE,
  KEY `idx_cm_group_name` (`G_NAME`(255)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='组信息';

-- ----------------------------
-- Records of cm_group
-- ----------------------------

-- ----------------------------
-- Table structure for cm_group_chlog
-- ----------------------------
DROP TABLE IF EXISTS `cm_group_chlog`;
CREATE TABLE `cm_group_chlog` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `GROUP_ID` int(11) NOT NULL DEFAULT '0' COMMENT '组编码',
  `LEVEL_ID` int(11) DEFAULT NULL COMMENT '层级编码',
  `G_NAME` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '组名称',
  `CRON` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '调度表达式',
  `REFERED_GROUP_IDS` varchar(2048) CHARACTER SET utf8 DEFAULT NULL COMMENT '依赖组编码',
  `C_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `U_DATE` datetime DEFAULT NULL COMMENT '最后更新时间爱你',
  `IS_ENABLE` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `IS_SORT_IN_GROUP` tinyint(1) DEFAULT '0' COMMENT '是否组内排序',
  `PROJECT_NAME` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '项目组名称',
  `NOTICE` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '注意事项',
  `DESC` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `C_USER` varchar(16) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建用户',
  `U_USER` varchar(16) CHARACTER SET utf8 DEFAULT NULL COMMENT '最后更新用户',
  `PARALLEL_LIMIT` int(11) DEFAULT '-1' COMMENT '组内并发最大数',
  `LOG_DATE` datetime DEFAULT NULL COMMENT '日志时间',
  `LOG_RESON` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '日志原因',
  `TOPIC_ID` int(11) DEFAULT '0' COMMENT '主题编码',
  `IS_TIME_TRIGGER` tinyint(4) DEFAULT '1',
  `QUEUE_ID` varchar(4) COLLATE utf8_bin DEFAULT '1' COMMENT '作业分发至执行集群ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cm_group_chlog
-- ----------------------------

-- ----------------------------
-- Table structure for cm_group_job_refer
-- ----------------------------
DROP TABLE IF EXISTS `cm_group_job_refer`;
CREATE TABLE `cm_group_job_refer` (
  `REFER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `JOB_ID` int(11) DEFAULT NULL,
  `GROUP_ID` int(11) DEFAULT NULL,
  `C_DATE` datetime DEFAULT NULL,
  `C_USER` varchar(16) DEFAULT NULL,
  `U_USER` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`REFER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='作业被组依赖信息表';

-- ----------------------------
-- Records of cm_group_job_refer
-- ----------------------------

-- ----------------------------
-- Table structure for cm_group_level
-- ----------------------------
DROP TABLE IF EXISTS `cm_group_level`;
CREATE TABLE `cm_group_level` (
  `LEVEL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LEVEL_ORDER_NO` int(11) DEFAULT NULL,
  `LEVEL_NAME` varchar(50) DEFAULT NULL,
  `QUEUE_ID` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`LEVEL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='层级信息';

-- ----------------------------
-- Records of cm_group_level
-- ----------------------------

-- ----------------------------
-- Table structure for cm_group_refer
-- ----------------------------
DROP TABLE IF EXISTS `cm_group_refer`;
CREATE TABLE `cm_group_refer` (
  `REFER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `REFERED_GROUP_ID` int(11) DEFAULT NULL,
  `GROUP_ID` int(11) DEFAULT NULL,
  `C_DATE` datetime DEFAULT NULL,
  `C_USER` varchar(16) DEFAULT NULL,
  `U_USER` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`REFER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='组依赖信息';

-- ----------------------------
-- Records of cm_group_refer
-- ----------------------------

-- ----------------------------
-- Table structure for cm_job
-- ----------------------------
DROP TABLE IF EXISTS `cm_job`;
CREATE TABLE `cm_job` (
  `JOB_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '作业编码',
  `GROUP_ID` int(11) DEFAULT NULL COMMENT '组编码',
  `JOB_NAME` varchar(256) DEFAULT NULL COMMENT '作业名称',
  `SERVER_FILE` varchar(256) DEFAULT NULL COMMENT '服务器文件',
  `LOG_FILE` varchar(256) DEFAULT NULL COMMENT '日志目录表达式',
  `SVN_FILE` varchar(256) DEFAULT NULL COMMENT 'svn文件',
  `CHARGOR_ID` int(11) DEFAULT NULL COMMENT '责任人ID',
  `IS_ENABLE` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `ORDER_NO` int(11) DEFAULT '0' COMMENT '跑数组内排序号',
  `DEBUG_LEVEL` varchar(8) DEFAULT NULL COMMENT '日志级别',
  `DESC` varchar(256) DEFAULT NULL COMMENT '作业描述',
  `C_USER` varchar(16) DEFAULT NULL COMMENT '创建者帐号',
  `U_USER` varchar(16) DEFAULT NULL COMMENT '最后更新者帐号',
  `C_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `U_DATE` datetime DEFAULT NULL COMMENT '最后更新时间',
  `RETRY_ID` int(11) DEFAULT '0' COMMENT '重试策略ID',
  `IS_ONLINE` tinyint(1) DEFAULT '1' COMMENT '上下限标志',
  `APPEND_PARAMS` varchar(512) DEFAULT '' COMMENT '执行命令追加参数',
  `RUN_PRIORITY` int(11) DEFAULT '100',
  `CHARGOR_ID2` int(11) DEFAULT '1' COMMENT '第二责任人ID',
  `CHARGOR_ID3` int(11) DEFAULT '1' COMMENT '第三责任人ID',
  `EXECUTE_SERVER_ID` int(11) DEFAULT NULL COMMENT 'JOB执行服务器ID',
  PRIMARY KEY (`JOB_ID`),
  KEY `idx_cm_job_n1` (`GROUP_ID`) USING BTREE,
  KEY `idx_cm_job_n2` (`JOB_NAME`(255)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='作业信息表';

-- ----------------------------
-- Records of cm_job
-- ----------------------------

-- ----------------------------
-- Table structure for cm_job_chlog
-- ----------------------------
DROP TABLE IF EXISTS `cm_job_chlog`;
CREATE TABLE `cm_job_chlog` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `JOB_ID` int(11) NOT NULL COMMENT '作业编码',
  `GROUP_ID` int(11) DEFAULT NULL COMMENT '组编码',
  `JOB_NAME` varchar(256) DEFAULT NULL COMMENT '作业名称',
  `SERVER_FILE` varchar(256) DEFAULT NULL COMMENT '服务器文件',
  `LOG_FILE` varchar(256) DEFAULT NULL COMMENT '日志目录表达式',
  `SVN_FILE` varchar(256) DEFAULT NULL COMMENT 'svn文件',
  `CHARGOR_ID` int(11) DEFAULT NULL COMMENT '责任人ID',
  `IS_ENABLE` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `ORDER_NO` int(11) DEFAULT '0' COMMENT '跑数组内排序号',
  `DEBUG_LEVEL` varchar(8) DEFAULT NULL COMMENT '日志级别',
  `DESC` varchar(256) DEFAULT NULL COMMENT '作业描述',
  `C_USER` varchar(16) DEFAULT NULL COMMENT '创建者帐号',
  `U_USER` varchar(16) DEFAULT NULL COMMENT '最后更新者帐号',
  `C_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `U_DATE` datetime DEFAULT NULL COMMENT '最后更新时间',
  `R_IS_TIME` int(11) DEFAULT NULL COMMENT '是否超时重试',
  `R_TIMEOUT` int(11) DEFAULT NULL COMMENT '超时时间',
  `R_IS_ERROR` int(11) DEFAULT NULL COMMENT '是否报错重试',
  `R_INTERVAL_TIME` int(11) DEFAULT NULL COMMENT '时间间隔',
  `R_ERROR_INTERVAL_NUM` int(11) DEFAULT '2' COMMENT '出错最多重试次数',
  `R_TIMEOUT_INTERVAL_NUM` int(11) DEFAULT '2' COMMENT '超时最多重试次数',
  `LOG_RESON` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `LOG_DATE` datetime DEFAULT NULL,
  `APPEND_PARAMS` varchar(512) DEFAULT '' COMMENT '执行命令追加参数',
  `RUN_PRIORITY` int(11) DEFAULT '100',
  `CHARGOR_ID2` int(11) DEFAULT '1' COMMENT '第二责任人ID',
  `EXECUTE_SERVER_ID` int(11) DEFAULT NULL,
  `CHARGOR_ID3` int(11) DEFAULT '1' COMMENT '第三责任人ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='作业信息表';

-- ----------------------------
-- Records of cm_job_chlog
-- ----------------------------

-- ----------------------------
-- Table structure for cm_job_log
-- ----------------------------
DROP TABLE IF EXISTS `cm_job_log`;
CREATE TABLE `cm_job_log` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `JOB_ID` int(11) DEFAULT NULL COMMENT '作业编码',
  `RUN_JOB_ID` int(11) DEFAULT NULL COMMENT '运行时作业编码',
  `STATE` int(11) DEFAULT NULL COMMENT '运行状态',
  `MSG` varchar(256) DEFAULT NULL COMMENT '更新原因',
  `EXECUTE_SERVER_ID` int(11) DEFAULT NULL COMMENT '执行服务器编码',
  `CONSOLE_FILE` varchar(256) DEFAULT NULL COMMENT '终端输出文件',
  `C_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CMD` varchar(768) DEFAULT NULL,
  `RUN_VERSION` int(11) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`LOG_ID`),
  KEY `idx_cm_job_log_j1` (`JOB_ID`) USING BTREE,
  KEY `idx_cm_job_log_j2` (`RUN_JOB_ID`) USING BTREE,
  KEY `idx_cm_job_log_c_d3` (`C_DATE`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39927 DEFAULT CHARSET=utf8 COMMENT='作业日志信息表';

-- ----------------------------
-- Records of cm_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for cm_job_retry_rule
-- ----------------------------
DROP TABLE IF EXISTS `cm_job_retry_rule`;
CREATE TABLE `cm_job_retry_rule` (
  `RETRY_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '重试策略ID',
  `R_NAME` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '重试策略名称',
  `R_DESC` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '策略定义描述',
  `R_IS_TIME` tinyint(1) DEFAULT NULL,
  `R_TIMEOUT` int(11) DEFAULT NULL COMMENT '超时时间',
  `R_IS_ERROR` tinyint(1) DEFAULT NULL,
  `R_INTERVAL_TIME` int(11) DEFAULT NULL COMMENT '时间间隔',
  `R_ERROR_INTERVAL_NUM` int(11) DEFAULT '2' COMMENT '出错最多重试次数',
  `R_TIMEOUT_INTERVAL_NUM` int(11) DEFAULT '2' COMMENT '超时最多重试次数',
  PRIMARY KEY (`RETRY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='任务规则表';

-- ----------------------------
-- Records of cm_job_retry_rule
-- ----------------------------
INSERT INTO `cm_job_retry_rule` VALUES ('3', '不重试跑数2小时', '超时时长2小时，失败不重试，超时不重试', '1', '7200', '1', '120', '1', '1');
INSERT INTO `cm_job_retry_rule` VALUES ('4', '不重试跑数24小时', '超时时长24小时，失败不重试，超时不重试', '1', '86400', '1', '120', '1', '1');
INSERT INTO `cm_job_retry_rule` VALUES ('5', 'ODS层作业规则', '失败重试1次', '1', '86400', '1', '180', '1', '1');
INSERT INTO `cm_job_retry_rule` VALUES ('6', '实时规则', '失败不重试', '1', '1800', '1', '180', '1', '1');
INSERT INTO `cm_job_retry_rule` VALUES ('7', '默认规则', '不重试、不重跑', '0', '86400', '0', '86400', '0', '0');

-- ----------------------------
-- Table structure for cm_job_state
-- ----------------------------
DROP TABLE IF EXISTS `cm_job_state`;
CREATE TABLE `cm_job_state` (
  `JOB_ID` int(11) NOT NULL COMMENT '作业ID',
  `GROUP_ID` int(11) DEFAULT NULL COMMENT '组ID',
  `STATE` tinyint(4) DEFAULT '0' COMMENT '当天状态',
  `MSG` varchar(200) DEFAULT '最后更新信息',
  `U_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作业状态表';

-- ----------------------------
-- Records of cm_job_state
-- ----------------------------

-- ----------------------------
-- Table structure for cm_job_statis
-- ----------------------------
DROP TABLE IF EXISTS `cm_job_statis`;
CREATE TABLE `cm_job_statis` (
  `JOB_ID` int(11) NOT NULL,
  `MAX_COST` int(11) DEFAULT NULL,
  `MIN_COST` int(11) DEFAULT NULL,
  `AVG_COST` int(11) DEFAULT NULL,
  `C_UPDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cm_job_statis
-- ----------------------------

-- ----------------------------
-- Table structure for cm_job_tables
-- ----------------------------
DROP TABLE IF EXISTS `cm_job_tables`;
CREATE TABLE `cm_job_tables` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `JOB_ID` int(11) DEFAULT NULL COMMENT '作业编码',
  `DB_NAME` varchar(100) DEFAULT NULL COMMENT '数据库名称',
  `TB_NAME` varchar(100) DEFAULT NULL COMMENT '表名称',
  `VERSION` int(11) DEFAULT NULL COMMENT '版本号',
  `DESC` varchar(256) DEFAULT NULL COMMENT '涉及表描述',
  `IS_ENABLE` tinyint(1) DEFAULT '1' COMMENT '是否使用',
  `C_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `U_DATE` datetime DEFAULT NULL COMMENT '最后更新时间',
  `C_USER` varchar(16) DEFAULT NULL COMMENT '创建人',
  `U_USER` varchar(16) DEFAULT NULL COMMENT '最后更新人',
  `TYPE` tinyint(1) DEFAULT '0' COMMENT '类型,0-源库表 1-目标库表',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作业涉及源库表及目标库表记录信息表';

-- ----------------------------
-- Records of cm_job_tables
-- ----------------------------

-- ----------------------------
-- Table structure for cm_kettle_detail_log
-- ----------------------------
DROP TABLE IF EXISTS `cm_kettle_detail_log`;
CREATE TABLE `cm_kettle_detail_log` (
  `ID_BATCH` int(11) DEFAULT NULL,
  `CHANNEL_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `LOG_DATE` datetime DEFAULT NULL,
  `TRANSNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `STEPNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `LINES_READ` bigint(20) DEFAULT NULL,
  `LINES_WRITTEN` bigint(20) DEFAULT NULL,
  `LINES_UPDATED` bigint(20) DEFAULT NULL,
  `LINES_INPUT` bigint(20) DEFAULT NULL,
  `LINES_OUTPUT` bigint(20) DEFAULT NULL,
  `LINES_REJECTED` bigint(20) DEFAULT NULL,
  `ERRORS` bigint(20) DEFAULT NULL,
  `RESULT` tinyint(1) DEFAULT NULL,
  `NR_RESULT_ROWS` bigint(20) DEFAULT NULL,
  `NR_RESULT_FILES` bigint(20) DEFAULT NULL,
  `LOG_FIELD` mediumtext CHARACTER SET utf8 COLLATE utf8_bin,
  `COPY_NR` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `IDX_CM_KETTLE_DETAIL_LOG_1` (`ID_BATCH`) USING BTREE,
  KEY `IDEX_DETAIL_N2` (`TRANSNAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_kettle_detail_log
-- ----------------------------

-- ----------------------------
-- Table structure for cm_kettle_job_log
-- ----------------------------
DROP TABLE IF EXISTS `cm_kettle_job_log`;
CREATE TABLE `cm_kettle_job_log` (
  `ID_JOB` int(11) NOT NULL DEFAULT '0',
  `CHANNEL_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `JOBNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `STATUS` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `LINES_READ` bigint(20) DEFAULT NULL,
  `LINES_WRITTEN` bigint(20) DEFAULT NULL,
  `LINES_UPDATED` bigint(20) DEFAULT NULL,
  `LINES_INPUT` bigint(20) DEFAULT NULL,
  `LINES_OUTPUT` bigint(20) DEFAULT NULL,
  `LINES_REJECTED` bigint(20) DEFAULT NULL,
  `ERRORS` bigint(20) DEFAULT NULL,
  `STARTDATE` datetime DEFAULT NULL,
  `ENDDATE` datetime DEFAULT NULL,
  `LOGDATE` datetime DEFAULT NULL,
  `DEPDATE` datetime DEFAULT NULL,
  `REPLAYDATE` datetime DEFAULT NULL,
  `LOG_FIELD` mediumtext,
  `EXECUTING_SERVER` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `EXECUTING_USER` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `START_JOB_ENTRY` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `CLIENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `IDX_CM_KETTLE_JOB_LOG_1` (`ID_JOB`) USING BTREE,
  KEY `IDX_CM_KETTLE_JOB_LOG_2` (`ERRORS`,`STATUS`,`JOBNAME`) USING BTREE,
  KEY `IDX_CM_KETTLE_JOB_LOG_3` (`JOBNAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_kettle_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for cm_perm_v
-- ----------------------------
DROP TABLE IF EXISTS `cm_perm_v`;
CREATE TABLE `cm_perm_v` (
  `PERM_ID` varchar(64) NOT NULL COMMENT '作业编号',
  `PERM_PARENT_ID` varchar(64) DEFAULT '0' COMMENT '父权限编号',
  `PERM_CODE` varchar(100) DEFAULT '' COMMENT '权限字符',
  `PERM_NAME` varchar(50) DEFAULT '' COMMENT '权限名称',
  `PERM_TYPE` varchar(1) DEFAULT 'X' COMMENT '类型',
  `PERM_PATH` varchar(1000) DEFAULT '' COMMENT '权限页面路径',
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `SORT` int(11) DEFAULT '0',
  PRIMARY KEY (`PERM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单功能信息表';

-- ----------------------------
-- Records of cm_perm_v
-- ----------------------------
INSERT INTO `cm_perm_v` VALUES ('1', '0', '1', '计划配置', 'X', '', now(), '2');
INSERT INTO `cm_perm_v` VALUES ('11', '1', '1.11', '作业组配置', 'X', '/admin/group', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('11001', '11', '1.11.11001', '作业组添加', 'Y', '/group/save', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('11002', '11', '1.11.11002', '作业组修改', 'Y', '/group/save', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('11003', '11', '1.11.11003', '作业组删除', 'Y', '/group/delete', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('11004', '11', '1.11.11004', '作业组启用/禁用', 'Y', '/group/updateState', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('11005', '11', '1.11.11005', '作业组查看依赖图', 'Y', '/group/relationGraph', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('12', '1', '1.12', '作业配置', 'X', '/admin/job', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('12001', '12', '1.12.12001', '作业查看', 'Y', '/job/list', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('12002', '12', '1.12.12002', '作业添加', 'Y', '/job/add', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('12003', '12', '1.12.12003', '作业修改', 'Y', '/job/update',now(), '0');
INSERT INTO `cm_perm_v` VALUES ('12004', '12', '1.12.12004', '作业删除', 'Y', '/job/delete',now(), '0');
INSERT INTO `cm_perm_v` VALUES ('12005', '12', '1.12.12005', '作业启用/禁用', 'Y', '/job/updateState', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('12006', '12', '1.12.12006', 'ETL参数编辑', 'Y', '/job/updateParameter', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('12007', '12', '1.12.12007', '下载ETL文件', 'Y', '/job/downloadETLFile', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('12008', '12', '1.12.12008', '作业上/下线', 'Y', '/job/online', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('12009', '12', '1.12.12009', '作业批量删除', 'Y', '/job/deleteByGroupId', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('12010', '12', '1.12.12010', '作业批量导入', 'Y', '/job/batchAddSave', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('2', '0', '2', '系统配置', 'X', '', now(), '99');
INSERT INTO `cm_perm_v` VALUES ('21', '2', '2.21', '用户管理', 'X', '/admin/user', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('22', '2', '2.22', '角色管理', 'X', '/admin/role', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('23', '2', '2.23', '主题设置', 'X', '/admin/topic', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('24', '2', '2.24', '责任人配置', 'X', '/admin/chargor', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('25', '2', '2.25', '层级配置', 'X', '/admin/groupLevel', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('26', '2', '2.26', '跑数规则', 'X', '/admin/retryRule', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('28', '2', '2.28', '主机管理', 'X', '/admin/server',now(), '0');
INSERT INTO `cm_perm_v` VALUES ('29', '2', '2.29', '集群管理', 'X', '/admin/queue', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('3', '0', '3', '调度监控', 'X', '',now(), '0');
INSERT INTO `cm_perm_v` VALUES ('31', '3', '3.31', '作业组监控', 'X', '/admin/runJobGroup', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('31001', '31', '3.31.31001', '作业组杀进程', 'Y', '/runJob/killGroup', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('31002', '31', '3.31.31002', '作业组重跑', 'Y', '/runJob/reRunGroup', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('32', '3', '3.32', '作业监控', 'X', '/runJob/admin', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('32001', '32', '3.32.32001', '作业触发跑', 'Y', '/runJob/reRunJob', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('32002', '32', '3.32.32002', '作业杀进程', 'Y', '/runJob/killJob', now(), '0');
INSERT INTO `cm_perm_v` VALUES ('32003', '32', '3.32.32003', '日志查看', 'Y', '/log/kettleJobLogList', now(), '0');

-- ----------------------------
-- Table structure for cm_queue
-- ----------------------------
DROP TABLE IF EXISTS `cm_queue`;
CREATE TABLE `cm_queue` (
  `QUEUE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '集群ID',
  `QUEUE_CODE` varchar(50) NOT NULL,
  `QUEUE_NAME` varchar(100) NOT NULL COMMENT '集群名称',
  `SVN_URL` varchar(300) NOT NULL COMMENT 'SVN地址',
  `SVN_USERNAME` varchar(64) NOT NULL COMMENT 'SVN用户',
  `SVN_PASSWORD` varchar(64) NOT NULL COMMENT 'SVN密码',
  PRIMARY KEY (`QUEUE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='集群表';

-- ----------------------------
-- Records of cm_queue
-- ----------------------------

-- ----------------------------
-- Table structure for cm_role
-- ----------------------------
DROP TABLE IF EXISTS `cm_role`;
CREATE TABLE `cm_role` (
  `ROLE_ID` varchar(64) NOT NULL COMMENT 'ROLE_ID',
  `ROLE_CODE` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `ROLE_NAME` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '角色备注',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of cm_role
-- ----------------------------
INSERT INTO `cm_role` VALUES ('1', 'ADMIN', '系统管理员', '负责系统运维');
INSERT INTO `cm_role` VALUES ('2', 'MANAGER', '普通管理员', null);

-- ----------------------------
-- Table structure for cm_role_group
-- ----------------------------
DROP TABLE IF EXISTS `cm_role_group`;
CREATE TABLE `cm_role_group` (
  `ROLE_ID` varchar(64) NOT NULL COMMENT 'ROLE_ID',
  `GROUP_ID` int(11) NOT NULL COMMENT 'GOURP_ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色job组表';

-- ----------------------------
-- Records of cm_role_group
-- ----------------------------
INSERT INTO `cm_role_group` VALUES ('bb7d9d269aba4506a001362754f12bee', '6');
INSERT INTO `cm_role_group` VALUES ('910cb44b92d4466397676866ff4402af', '6');

-- ----------------------------
-- Table structure for cm_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `cm_role_perm`;
CREATE TABLE `cm_role_perm` (
  `ROLE_ID` varchar(64) NOT NULL COMMENT 'ROLE_ID',
  `PERM_ID` varchar(50) NOT NULL COMMENT 'PERM_ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单权限表';

-- ----------------------------
-- Records of cm_role_perm
-- ----------------------------
INSERT INTO `cm_role_perm` VALUES ('1', '1');
INSERT INTO `cm_role_perm` VALUES ('1', '11');
INSERT INTO `cm_role_perm` VALUES ('1', '11001');
INSERT INTO `cm_role_perm` VALUES ('1', '11002');
INSERT INTO `cm_role_perm` VALUES ('1', '11003');
INSERT INTO `cm_role_perm` VALUES ('1', '11004');
INSERT INTO `cm_role_perm` VALUES ('1', '11005');
INSERT INTO `cm_role_perm` VALUES ('1', '12');
INSERT INTO `cm_role_perm` VALUES ('1', '12001');
INSERT INTO `cm_role_perm` VALUES ('1', '12002');
INSERT INTO `cm_role_perm` VALUES ('1', '12003');
INSERT INTO `cm_role_perm` VALUES ('1', '12004');
INSERT INTO `cm_role_perm` VALUES ('1', '12005');
INSERT INTO `cm_role_perm` VALUES ('1', '12006');
INSERT INTO `cm_role_perm` VALUES ('1', '12007');
INSERT INTO `cm_role_perm` VALUES ('1', '12008');
INSERT INTO `cm_role_perm` VALUES ('1', '12009');
INSERT INTO `cm_role_perm` VALUES ('1', '12010');
INSERT INTO `cm_role_perm` VALUES ('1', '2');
INSERT INTO `cm_role_perm` VALUES ('1', '21');
INSERT INTO `cm_role_perm` VALUES ('1', '22');
INSERT INTO `cm_role_perm` VALUES ('1', '23');
INSERT INTO `cm_role_perm` VALUES ('1', '24');
INSERT INTO `cm_role_perm` VALUES ('1', '25');
INSERT INTO `cm_role_perm` VALUES ('1', '26');
INSERT INTO `cm_role_perm` VALUES ('1', '28');
INSERT INTO `cm_role_perm` VALUES ('1', '29');
INSERT INTO `cm_role_perm` VALUES ('1', '3');
INSERT INTO `cm_role_perm` VALUES ('1', '31');
INSERT INTO `cm_role_perm` VALUES ('1', '31001');
INSERT INTO `cm_role_perm` VALUES ('1', '31002');
INSERT INTO `cm_role_perm` VALUES ('1', '32');
INSERT INTO `cm_role_perm` VALUES ('1', '32001');
INSERT INTO `cm_role_perm` VALUES ('1', '32002');
INSERT INTO `cm_role_perm` VALUES ('1', '32003');

-- ----------------------------
-- Table structure for cm_role_queue
-- ----------------------------
DROP TABLE IF EXISTS `cm_role_queue`;
CREATE TABLE `cm_role_queue` (
  `ROLE_ID` varchar(64) NOT NULL COMMENT 'ROLE_ID',
  `QUEUE_ID` int(11) NOT NULL COMMENT 'QUEUE_ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='集群权限表';

-- ----------------------------
-- Records of cm_role_queue
-- ----------------------------

-- ----------------------------
-- Table structure for cm_role_topic
-- ----------------------------
DROP TABLE IF EXISTS `cm_role_topic`;
CREATE TABLE `cm_role_topic` (
  `ROLE_ID` varchar(64) NOT NULL COMMENT 'ROLE_ID',
  `TOPIC_ID` int(11) NOT NULL COMMENT 'TOPIC_ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色主题权限表';

-- ----------------------------
-- Records of cm_role_topic
-- ----------------------------

-- ----------------------------
-- Table structure for cm_role_user
-- ----------------------------
DROP TABLE IF EXISTS `cm_role_user`;
CREATE TABLE `cm_role_user` (
  `ROLE_ID` varchar(64) NOT NULL COMMENT '角色ID',
  `USER_NO` varchar(64) NOT NULL COMMENT '用户账号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色用户表';

-- ----------------------------
-- Records of cm_role_user
-- ----------------------------
INSERT INTO `cm_role_user` VALUES ('1', 'admin');

-- ----------------------------
-- Table structure for cm_run_api
-- ----------------------------
DROP TABLE IF EXISTS `cm_run_api`;
CREATE TABLE `cm_run_api` (
  `SCHEDULE_RUN_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `GROUP_ID` int(11) NOT NULL COMMENT 'ODS层组编码',
  `C_DATE` datetime DEFAULT NULL COMMENT '创建时间/接口调用时间',
  `U_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `RUN_GROUP_ID` int(11) DEFAULT '-1' COMMENT '运行时组编码,若为-1表示还没推送至运行时表',
  `PUSH_DATE` datetime DEFAULT NULL COMMENT '推送至运行时时间',
  `PUSH_TYPE` tinyint(4) DEFAULT '2' COMMENT '推送类型：0-cron推送 1:依赖推送 2：手动推送 接口触发的推送归类为依赖推送',
  `JOB_TOTAL_COUNT` int(11) DEFAULT '0' COMMENT '等待中作业数量',
  `JOB_RUNNING_COUNT` int(11) DEFAULT '0' COMMENT '等待中作业数量',
  `JOB_ERROR_COUNT` int(11) DEFAULT '0' COMMENT '运行出错作业数量,被杀进程的作业也会改为错误状态,如果要后续作业跑起,可以禁用该作业',
  `JOB_SUCCESS_COUNT` int(11) DEFAULT '0' COMMENT '运行成功作业数量',
  `JOB_CANCEL_COUNT` int(11) DEFAULT '0' COMMENT '被取消作业数量',
  `IS_FINISH` tinyint(4) DEFAULT '0' COMMENT '是否已全部完成',
  `IS_CANCEL` tinyint(4) DEFAULT '0' COMMENT '是否已被取消执行',
  `APPEND_PARAMS` varchar(2000) DEFAULT NULL COMMENT '追加参数,接口调用时传入,如果不为空则推送作业时该作业链下所有作业均使用这个参数,否则使用原作业配置的追加参数',
  PRIMARY KEY (`SCHEDULE_RUN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口方式触发跑数信息表';

-- ----------------------------
-- Records of cm_run_api
-- ----------------------------

-- ----------------------------
-- Table structure for cm_run_group
-- ----------------------------
DROP TABLE IF EXISTS `cm_run_group`;
CREATE TABLE `cm_run_group` (
  `RUN_GROUP_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `GROUP_ID` int(11) DEFAULT NULL COMMENT '组编码',
  `C_DATE` datetime DEFAULT NULL COMMENT '推送时间',
  `U_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `REFERED_GROUP_IDS` varchar(2000) DEFAULT NULL COMMENT '依赖组编码字符串',
  `IS_SORT_IN_GROUP` tinyint(1) DEFAULT '0' COMMENT '是否组内排序',
  `PARALLEL_LIMIT` int(11) DEFAULT '-1',
  `ORDER_NO_LATEST_UPDATE` int(11) DEFAULT '-10',
  `PUSH_TYPE` tinyint(4) DEFAULT '0' COMMENT '推送类型：0-cron推送 1:依赖推送 2：手动推送',
  `REFERED_JOB_IDS` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`RUN_GROUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=877 DEFAULT CHARSET=utf8 COMMENT='运行时组信息表';

-- ----------------------------
-- Records of cm_run_group
-- ----------------------------

-- ----------------------------
-- Table structure for cm_run_group_his
-- ----------------------------
DROP TABLE IF EXISTS `cm_run_group_his`;
CREATE TABLE `cm_run_group_his` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `RUN_GROUP_ID` int(11) DEFAULT NULL COMMENT '跑数编码',
  `GROUP_ID` int(11) DEFAULT NULL COMMENT '组编码',
  `C_DATE` datetime DEFAULT NULL COMMENT '推送时间',
  `U_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `REFERED_GROUP_IDS` varchar(2000) DEFAULT NULL COMMENT '依赖组编码字符串',
  `IS_SORT_IN_GROUP` tinyint(1) DEFAULT '0' COMMENT '是否组内排序',
  `PARALLEL_LIMIT` int(11) DEFAULT '-1',
  `PUSH_TYPE` tinyint(4) DEFAULT '0',
  `REFERED_JOB_IDS` varchar(2000) DEFAULT NULL COMMENT '依赖作业ids',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=865 DEFAULT CHARSET=utf8 COMMENT='运行时组历史信息表';

-- ----------------------------
-- Records of cm_run_group_his
-- ----------------------------

-- ----------------------------
-- Table structure for cm_run_job
-- ----------------------------
DROP TABLE IF EXISTS `cm_run_job`;
CREATE TABLE `cm_run_job` (
  `RUN_JOB_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `RUN_GROUP_ID` int(11) DEFAULT NULL COMMENT '运行时组编码',
  `JOB_ID` int(11) DEFAULT NULL COMMENT '作业编码',
  `GROUP_ID` int(11) DEFAULT NULL COMMENT '组编码',
  `EXECUTE_SERVER_ID` int(11) DEFAULT NULL COMMENT 'JOB执行服务器ID',
  `STATE` int(11) DEFAULT NULL COMMENT '执行状态',
  `SERVER_FILE` varchar(256) DEFAULT NULL COMMENT '服务器文件',
  `LOG_FILE` varchar(256) DEFAULT NULL COMMENT '日志目录表达式',
  `ORDER_NO` int(11) DEFAULT '0' COMMENT '跑数组内排序号',
  `DEBUG_LEVEL` varchar(8) DEFAULT NULL COMMENT '日志级别',
  `C_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `U_DATE` datetime DEFAULT NULL COMMENT '最后更新时间',
  `R_IS_TIME` tinyint(4) DEFAULT '0' COMMENT '是否超时重试',
  `R_TIMEOUT` int(11) DEFAULT NULL COMMENT '超时时间',
  `R_IS_ERROR` tinyint(4) DEFAULT '0' COMMENT '是否报错重试',
  `R_INTERVAL_TIME` int(11) DEFAULT NULL COMMENT '时间间隔',
  `R_ERROR_INTERVAL_NUM` int(11) DEFAULT '2' COMMENT '出错最多重试次数',
  `R_TIMEOUT_INTERVAL_NUM` int(11) DEFAULT '2' COMMENT '超时最多重试次数',
  `DATE_LATEST_SEND` datetime DEFAULT NULL COMMENT '最后发送至队列的时间',
  `ERROR_RETRYED_COUNT` int(11) DEFAULT '0' COMMENT '失败已重试次数',
  `TIMEOUT_RETRYED_COUNT` int(11) DEFAULT '0' COMMENT '超时已重试次数',
  `MSG` varchar(256) DEFAULT NULL COMMENT '更新原因',
  `RUN_VERSION` int(11) DEFAULT '1',
  `APPEND_PARAMS` varchar(512) DEFAULT '' COMMENT '执行命令追加参数',
  `RUN_PRIORITY` int(11) DEFAULT '100',
  `NOTICE_DATE` datetime DEFAULT NULL COMMENT '值班人员预知作业报错时电话通知责任人时间,为空时表示还没通知',
  `QUEUE_ID` varchar(4) DEFAULT '1' COMMENT '作业分发至执行集群ID',
  PRIMARY KEY (`RUN_JOB_ID`),
  KEY `ids_cm_run_job_s1` (`STATE`) USING BTREE,
  KEY `ids_cm_run_job_j2` (`JOB_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1664 DEFAULT CHARSET=utf8 COMMENT='运行时作业信息表';

-- ----------------------------
-- Records of cm_run_job
-- ----------------------------

-- ----------------------------
-- Table structure for cm_run_job_confirm
-- ----------------------------
DROP TABLE IF EXISTS `cm_run_job_confirm`;
CREATE TABLE `cm_run_job_confirm` (
  `CONFIRM_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `FRONT_GROUP_NAME` varchar(256) DEFAULT NULL COMMENT '前置组',
  `POST_GROUP_NAME` varchar(256) DEFAULT NULL COMMENT '后置组',
  `C_ACCOUNT` varchar(16) DEFAULT NULL COMMENT '手动跑数帐号',
  `U_ACCOUNT` varchar(16) DEFAULT NULL COMMENT '确认人帐号',
  `C_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `U_DATE` datetime DEFAULT NULL COMMENT '确认时间',
  `MSG` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`CONFIRM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='手动跑作业确认信息表';

-- ----------------------------
-- Records of cm_run_job_confirm
-- ----------------------------

-- ----------------------------
-- Table structure for cm_run_job_his
-- ----------------------------
DROP TABLE IF EXISTS `cm_run_job_his`;
CREATE TABLE `cm_run_job_his` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `RUN_JOB_ID` int(11) DEFAULT NULL COMMENT '跑数编码',
  `RUN_GROUP_ID` int(11) DEFAULT NULL COMMENT '运行时组编码',
  `JOB_ID` int(11) DEFAULT NULL COMMENT '作业编码',
  `GROUP_ID` int(11) DEFAULT NULL COMMENT '组编码',
  `EXECUTE_SERVER_ID` int(11) DEFAULT NULL COMMENT '执行服务器编码',
  `STATE` int(11) DEFAULT NULL COMMENT '执行状态',
  `SERVER_FILE` varchar(256) DEFAULT NULL COMMENT '服务器文件',
  `LOG_FILE` varchar(256) DEFAULT NULL COMMENT '日志目录表达式',
  `ORDER_NO` int(11) DEFAULT '0' COMMENT '跑数组内排序号',
  `DEBUG_LEVEL` varchar(8) DEFAULT NULL COMMENT '日志级别',
  `C_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `U_DATE` datetime DEFAULT NULL COMMENT '最后更新时间',
  `R_IS_TIME` tinyint(4) DEFAULT '0' COMMENT '是否超时重试',
  `R_TIMEOUT` int(11) DEFAULT NULL COMMENT '超时时间',
  `R_IS_ERROR` tinyint(4) DEFAULT '0' COMMENT '是否报错重试',
  `R_INTERVAL_TIME` int(11) DEFAULT NULL COMMENT '时间间隔',
  `R_ERROR_INTERVAL_NUM` int(11) DEFAULT '2' COMMENT '出错最多重试次数',
  `R_TIMEOUT_INTERVAL_NUM` int(11) DEFAULT '2' COMMENT '超时最多重试次数',
  `DATE_LATEST_SEND` datetime DEFAULT NULL COMMENT '最后发送至队列的时间',
  `ERROR_RETRYED_COUNT` int(11) DEFAULT '0' COMMENT '失败已重试次数',
  `TIMEOUT_RETRYED_COUNT` int(11) DEFAULT '0' COMMENT '超时已重试次数',
  `MSG` varchar(256) DEFAULT NULL COMMENT '更新原因',
  `R_IS_KILL` tinyint(1) DEFAULT '0',
  `RUN_VERSION` int(11) DEFAULT '0',
  `APPEND_PARAMS` varchar(512) DEFAULT '' COMMENT '执行命令追加参数',
  `RUN_PRIORITY` int(11) DEFAULT '100',
  `NOTICE_DATE` datetime DEFAULT NULL COMMENT '值班人员预知作业报错时电话通知责任人时间,为空时表示还没通知',
  `QUEUE_ID` varchar(4) DEFAULT '1' COMMENT '作业分发至执行集群ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1643 DEFAULT CHARSET=utf8 COMMENT='运行时作业历史信息表';

-- ----------------------------
-- Records of cm_run_job_his
-- ----------------------------

-- ----------------------------
-- Table structure for cm_server
-- ----------------------------
DROP TABLE IF EXISTS `cm_server`;
CREATE TABLE `cm_server` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATE` int(11) DEFAULT NULL,
  `IP` varchar(50) DEFAULT NULL,
  `C_DATE` datetime DEFAULT NULL,
  `U_DATE` datetime DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `QUEUE_ID` varchar(64) DEFAULT '1' COMMENT '所属集群',
  `APP_PORT` varchar(64) DEFAULT '38888',
  `DESC` varchar(255) DEFAULT '' COMMENT '所属集群及分系统',
  `QUEUE_NAME` varchar(255) DEFAULT '默认集群' COMMENT '集群',
  `IS_PERFORM` bit(1) DEFAULT b'0' COMMENT '是否主执行机',
  `IS_AVAILABLE` bit(1) DEFAULT b'1' COMMENT '是否可用（0：宕机   1：可用）',
  `current_Execute_JobCount` int(255) DEFAULT '0',
  `SVN_LOGPATH` varchar(300) DEFAULT NULL,
  `LOG_APP_PORT` varchar(64) DEFAULT NULL,
  `MAX_PARALLE` int(10) DEFAULT '15' COMMENT '最大执行数',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1028 DEFAULT CHARSET=utf8 COMMENT='分布式执行引擎服务器信息表';

-- ----------------------------
-- Records of cm_server
-- ----------------------------

-- ----------------------------
-- Table structure for cm_svn_version
-- ----------------------------
DROP TABLE IF EXISTS `cm_svn_version`;
CREATE TABLE `cm_svn_version` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ip_num` char(32) NOT NULL COMMENT 'IP号',
  `version` varchar(50) NOT NULL DEFAULT '0' COMMENT '版本号',
  `last_update` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='svn版本号控制';

-- ----------------------------
-- Records of cm_svn_version
-- ----------------------------

-- ----------------------------
-- Table structure for cm_tags
-- ----------------------------
DROP TABLE IF EXISTS `cm_tags`;
CREATE TABLE `cm_tags` (
  `TID` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签编码',
  `TNAME` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名称',
  `QUEUE_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TNUM` int(11) DEFAULT NULL,
  PRIMARY KEY (`TID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='标签信息表';

-- ----------------------------
-- Records of cm_tags
-- ----------------------------

-- ----------------------------
-- Table structure for cm_tags_refer
-- ----------------------------
DROP TABLE IF EXISTS `cm_tags_refer`;
CREATE TABLE `cm_tags_refer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `GROUP_ID` int(11) DEFAULT '0' COMMENT '组编码',
  `TID` int(11) DEFAULT '0' COMMENT '标签编码',
  PRIMARY KEY (`ID`),
  KEY `c_tags_delete` (`TID`) USING BTREE,
  KEY `c_group_delete` (`GROUP_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='标签-组关联信息表';

-- ----------------------------
-- Records of cm_tags_refer
-- ----------------------------

-- ----------------------------
-- Table structure for cm_tool
-- ----------------------------
DROP TABLE IF EXISTS `cm_tool`;
CREATE TABLE `cm_tool` (
  `TOOL_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '小工具编码',
  `TOOL_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '小工具名称',
  `TOOL_URL` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '小工具访问URL',
  `C_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `IS_ENABLE` tinyint(1) DEFAULT '1',
  `ORDER_NO` int(11) DEFAULT '1',
  `DESC` varchar(16) COLLATE utf8_bin DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`TOOL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cm_tool
-- ----------------------------

-- ----------------------------
-- Table structure for cm_topic
-- ----------------------------
DROP TABLE IF EXISTS `cm_topic`;
CREATE TABLE `cm_topic` (
  `TOPIC_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '组编码',
  `NAME` varchar(16) DEFAULT NULL COMMENT '层级编码',
  `C_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `DESC` varchar(128) DEFAULT NULL COMMENT '备注',
  `QUEUE_ID` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`TOPIC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8 COMMENT='主题信息表';

-- ----------------------------
-- Records of cm_topic
-- ----------------------------

-- ----------------------------
-- Table structure for cm_url
-- ----------------------------
DROP TABLE IF EXISTS `cm_url`;
CREATE TABLE `cm_url` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `SERVER_ID` int(11) NOT NULL COMMENT 'ODS层组编码',
  `C_DATE` datetime DEFAULT NULL COMMENT '创建时间/接口调用时间',
  `U_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `NAME` varchar(16) DEFAULT '' COMMENT '名称',
  `URL` varchar(256) DEFAULT '' COMMENT '访问路径',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='URL信息表';

-- ----------------------------
-- Records of cm_url
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `CRON_EXPRESSION` varchar(120) COLLATE utf8_bin NOT NULL,
  `TIME_ZONE_ID` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('scheduler', 'triggerBatchUpdateEachDayJob', 'MSSGroup', '0 1 0 * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('scheduler', 'triggerCheckJobAndSendob', 'MSSGroup', '0/5 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('scheduler', 'triggerRetryErrorJob', 'MSSGroup', '0/2 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('scheduler', 'triggerRetryExpireJob', 'MSSGroup', '1/2 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `ENTRY_ID` varchar(95) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE,
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) COLLATE utf8_bin NOT NULL,
  `IS_DURABLE` varchar(1) COLLATE utf8_bin NOT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8_bin NOT NULL,
  `IS_UPDATE_DATA` varchar(1) COLLATE utf8_bin NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8_bin NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('scheduler', 'batchUpdateEachDayJob', 'MSSGroup', null, 'com.meicloud.schedule.job.BatchUpdateEachDayJob', '0', '1', '1', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `qrtz_job_details` VALUES ('scheduler', 'checkJobAndSendob', 'MSSGroup', null, 'com.meicloud.schedule.job.CheckJobAndSendJob', '0', '1', '1', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `qrtz_job_details` VALUES ('scheduler', 'retryErrorJob', 'MSSGroup', null, 'com.meicloud.schedule.job.RetryErrorJob', '0', '1', '1', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `qrtz_job_details` VALUES ('scheduler', 'retryExpireJob', 'MSSGroup', null, 'com.meicloud.schedule.job.RetryExpireJob', '0', '1', '1', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `LOCK_NAME` varchar(40) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('scheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('scheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `STR_PROP_1` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `STR_PROP_2` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `STR_PROP_3` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8_bin NOT NULL,
  `TRIGGER_TYPE` varchar(8) COLLATE utf8_bin NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE,
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('scheduler', 'triggerBatchUpdateEachDayJob', 'MSSGroup', 'batchUpdateEachDayJob', 'MSSGroup', null, '1525276860000', '-1', '5', 'WAITING', 'CRON', '1525233793000', '0', null, '0', '');
INSERT INTO `qrtz_triggers` VALUES ('scheduler', 'triggerCheckJobAndSendob', 'MSSGroup', 'checkJobAndSendob', 'MSSGroup', null, '1525242890000', '1525242885000', '5', 'WAITING', 'CRON', '1525233793000', '0', null, '0', '');
INSERT INTO `qrtz_triggers` VALUES ('scheduler', 'triggerRetryErrorJob', 'MSSGroup', 'retryErrorJob', 'MSSGroup', null, '1525242888000', '1525242886000', '5', 'ACQUIRED', 'CRON', '1525233793000', '0', null, '0', '');
INSERT INTO `qrtz_triggers` VALUES ('scheduler', 'triggerRetryExpireJob', 'MSSGroup', 'retryExpireJob', 'MSSGroup', null, '1525242889000', '1525242887000', '5', 'WAITING', 'CRON', '1525233793000', '0', null, '0', '');

-- ----------------------------
-- Table structure for schema_version
-- ----------------------------
DROP TABLE IF EXISTS `schema_version`;
CREATE TABLE `schema_version` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `schema_version_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schema_version
-- ----------------------------

-- ----------------------------
--  View definition for `v_cm_job_state`
-- ----------------------------
DROP VIEW IF EXISTS `v_cm_job_state`;
CREATE VIEW `v_cm_job_state` AS select `rj`.`JOB_ID` AS `JOB_ID`,`rj`.`C_DATE` AS `C_DATE`,max(`rj`.`U_DATE`) AS `U_DATE`,(case when ((`rj`.`STATE` = 0) or (`rj`.`STATE` = 1)) then 0 else `rj`.`STATE` end) AS `STATE`,`rj`.`GROUP_ID` AS `GROUP_ID`,'已推送' AS `TYPE` from ((`cm_run_job` `rj` join `cm_group` `g` on((`rj`.`GROUP_ID` = `g`.`GROUP_ID`))) join `cm_job` `j` on((`rj`.`JOB_ID` = `j`.`JOB_ID`))) where ((date_format(now(),'%Y%m%d') = date_format(`rj`.`U_DATE`,'%Y%m%d')) and `rj`.`JOB_ID` in (select `cm_job`.`JOB_ID` from `cm_job`)) group by `rj`.`JOB_ID` union all select `j`.`JOB_ID` AS `JOB_ID`,`j`.`C_DATE` AS `C_DATE`,max(`j`.`U_DATE`) AS `U_DATE`,(case when ((`g`.`IS_ENABLE` = 0) or (`j`.`IS_ENABLE` = 0)) then -(1) else 0 end) AS `STATE`,`j`.`GROUP_ID` AS `GROUP_ID`,'未推送' AS `TYPE` from (`cm_job` `j` join `cm_group` `g` on((`j`.`GROUP_ID` = `g`.`GROUP_ID`))) where (not(`j`.`JOB_ID` in (select distinct `rj`.`JOB_ID` from `cm_run_job` `rj` where (date_format(now(),'%Y%m%d') = date_format(`rj`.`U_DATE`,'%Y%m%d'))))) group by `j`.`JOB_ID`;

-- ----------------------------
--  View definition for `v_edit_header`
-- ----------------------------
DROP VIEW IF EXISTS `v_edit_header`;
CREATE VIEW `v_edit_header` AS select '主题数' AS `TY`,count(`cm_topic`.`TOPIC_ID`) AS `CN`,`cm_topic`.`QUEUE_ID` AS `QUEUE_ID` from `cm_topic` group by `cm_topic`.`QUEUE_ID` union select '总数量' AS `TY`,count(`j`.`JOB_ID`) AS `CN`,`g`.`QUEUE_ID` AS `QUEUE_ID` from (`cm_job` `j` join `cm_group` `g` on((`j`.`GROUP_ID` = `g`.`GROUP_ID`))) group by `g`.`QUEUE_ID` union select '启用数' AS `TY`,count(`j`.`JOB_ID`) AS `CN`,`g`.`QUEUE_ID` AS `QUEUE_ID` from (`cm_job` `j` join `cm_group` `g` on((`j`.`GROUP_ID` = `g`.`GROUP_ID`))) where ((`j`.`IS_ENABLE` = 1) and (`g`.`IS_ENABLE` = 1)) group by `g`.`QUEUE_ID` union select '禁用数' AS `TY`,count(`j`.`JOB_ID`) AS `CN`,`g`.`QUEUE_ID` AS `QUEUE_ID` from (`cm_job` `j` join `cm_group` `g` on((`j`.`GROUP_ID` = `g`.`GROUP_ID`))) where ((`j`.`IS_ENABLE` = 0) or (`g`.`IS_ENABLE` = 0)) group by `g`.`QUEUE_ID`;

-- ----------------------------
--  View definition for `v_job_run_state_current_window`
-- ----------------------------
DROP VIEW IF EXISTS `v_job_run_state_current_window`;
CREATE VIEW `v_job_run_state_current_window` AS select `t`.`TOPIC_ID` AS `TOPIC_ID`,`t`.`NAME` AS `T_NAME`,`g`.`GROUP_ID` AS `GROUP_ID`,`g`.`G_NAME` AS `G_NAME`,`g`.`CRON` AS `CRON`,`j`.`JOB_ID` AS `JOB_ID`,`j`.`JOB_NAME` AS `JOB_NAME`,`j`.`SVN_FILE` AS `SVN_FILE`,`j`.`IS_ENABLE` AS `IS_ENABLE`,`j`.`IS_ONLINE` AS `IS_ONLINE`,`j`.`ORDER_NO` AS `ORDER_NO`,`j`.`RUN_PRIORITY` AS `RUN_PRIORITY`,`j`.`C_DATE` AS `J_CDATE`,`j`.`U_DATE` AS `J_UDATE`,`j`.`CHARGOR_ID2` AS `CHARGOR_ID2`,`j`.`CHARGOR_ID3` AS `CHARGOR_ID3`,`c`.`ID` AS `C_ID`,`c`.`NAME` AS `C_NAME`,`c`.`EMAIL` AS `EMAIL`,`c`.`PHONE` AS `PHONE`,`rj`.`RUN_JOB_ID` AS `RUN_JOB_ID`,`rj`.`C_DATE` AS `RJ_CDATE`,`rj`.`STATE` AS `STATE`,`rj`.`EXECUTE_SERVER_ID` AS `EXECUTE_SERVER_ID`,`rj`.`RUN_VERSION` AS `RUN_VERSION`,(case when (`jl`.`C_DATE` is not null) then `jl`.`C_DATE` else 0 end) AS `JL_CDATE`,(case when (`jl`.`CONSOLE_FILE` is not null) then `jl`.`CONSOLE_FILE` else '' end) AS `CONSOLE_FILE` from (((((`cm_topic` `t` join `cm_group` `g` on((`g`.`TOPIC_ID` = `t`.`TOPIC_ID`))) join `cm_job` `j` on((`j`.`GROUP_ID` = `g`.`GROUP_ID`))) join `cm_chargor` `c` on((`j`.`CHARGOR_ID` = `c`.`ID`))) join `cm_run_job` `rj` on((`j`.`JOB_ID` = `rj`.`JOB_ID`))) left join `cm_job_log` `jl` on(((`rj`.`RUN_JOB_ID` = `jl`.`RUN_JOB_ID`) and (`rj`.`RUN_VERSION` = `jl`.`RUN_VERSION`)))) union select `t`.`TOPIC_ID` AS `TOPIC_ID`,`t`.`NAME` AS `T_NAME`,`g`.`GROUP_ID` AS `GROUP_ID`,`g`.`G_NAME` AS `G_NAME`,`g`.`CRON` AS `CRON`,`j`.`JOB_ID` AS `JOB_ID`,`j`.`JOB_NAME` AS `JOB_NAME`,`j`.`SVN_FILE` AS `SVN_FILE`,`j`.`IS_ENABLE` AS `IS_ENABLE`,`j`.`IS_ONLINE` AS `IS_ONLINE`,`j`.`ORDER_NO` AS `ORDER_NO`,`j`.`RUN_PRIORITY` AS `RUN_PRIORITY`,`j`.`C_DATE` AS `J_CDATE`,`j`.`U_DATE` AS `J_UDATE`,`j`.`CHARGOR_ID2` AS `CHARGOR_ID2`,`j`.`CHARGOR_ID3` AS `CHARGOR_ID3`,`c`.`ID` AS `C_ID`,`c`.`NAME` AS `C_NAME`,`c`.`EMAIL` AS `EMAIL`,`c`.`PHONE` AS `PHONE`,0 AS `RUN_JOB_ID`,NULL AS `RJ_CDATE`,0 AS `STATE`,0 AS `EXECUTE_SERVER_ID`,1 AS `RUN_VERSION`,0 AS `JL_CDATE`,'' AS `CONSOLE_FILE` from ((((`cm_job` `j` join `cm_group` `g` on((`j`.`GROUP_ID` = `g`.`GROUP_ID`))) join `cm_chargor` `c` on((`j`.`CHARGOR_ID` = `c`.`ID`))) join `cm_topic` `t` on((`g`.`TOPIC_ID` = `t`.`TOPIC_ID`))) left join `cm_run_job` `rj` on((`j`.`JOB_ID` = `rj`.`JOB_ID`))) where isnull(`rj`.`C_DATE`);

-- ----------------------------
--  View definition for `v_job_state`
-- ----------------------------
DROP VIEW IF EXISTS `v_job_state`;
CREATE VIEW `v_job_state` AS select `j`.`GROUP_ID` AS `GROUP_ID`,`j`.`JOB_ID` AS `JOB_ID`,0 AS `STATE`,`g`.`IS_ENABLE` AS `G_ENABLE`,`j`.`IS_ENABLE` AS `J_ENABLE`,`j`.`IS_ONLINE` AS `IS_ONLINE` from ((`cm_job` `j` join `cm_group` `g` on((`j`.`GROUP_ID` = `g`.`GROUP_ID`))) left join `cm_run_job` `rj` on((`j`.`JOB_ID` = `rj`.`JOB_ID`))) where isnull(`rj`.`JOB_ID`) union select `rj`.`GROUP_ID` AS `GROUP_ID`,`rj`.`JOB_ID` AS `JOB_ID`,`rj`.`STATE` AS `STATE`,`g`.`IS_ENABLE` AS `G_ENABLE`,`j`.`IS_ENABLE` AS `J_ENABLE`,`j`.`IS_ONLINE` AS `IS_ONLINE` from ((`cm_run_job` `rj` join `cm_group` `g` on((`rj`.`GROUP_ID` = `g`.`GROUP_ID`))) join `cm_job` `j` on((`rj`.`JOB_ID` = `j`.`JOB_ID`)));

-- ----------------------------
--  View definition for `v_run_header_cancel`
-- ----------------------------
DROP VIEW IF EXISTS `v_run_header_cancel`;
CREATE VIEW `v_run_header_cancel` AS select `cm_run_job`.`GROUP_ID` AS `GROUP_ID`,`cm_run_job`.`JOB_ID` AS `JOB_ID`,`cm_run_job`.`RUN_JOB_ID` AS `RUN_JOB_ID`,`cm_run_job`.`RUN_VERSION` AS `RUN_VERSION`,`cm_run_job`.`STATE` AS `STATE`,'CANCEL_TODAY' AS `TYPE`,`cm_run_job`.`QUEUE_ID` AS `QUEUE_ID` from `cm_run_job` where ((`cm_run_job`.`STATE` = 4) and (cast(now() as date) = cast(`cm_run_job`.`U_DATE` as date)));

-- ----------------------------
--  View definition for `v_run_header_error`
-- ----------------------------
DROP VIEW IF EXISTS `v_run_header_error`;
CREATE VIEW `v_run_header_error` AS select `cm_run_job`.`GROUP_ID` AS `GROUP_ID`,`cm_run_job`.`JOB_ID` AS `JOB_ID`,`cm_run_job`.`RUN_JOB_ID` AS `RUN_JOB_ID`,`cm_run_job`.`RUN_VERSION` AS `RUN_VERSION`,`cm_run_job`.`STATE` AS `STATE`,'ERROR_TODAY' AS `TYPE`,`cm_run_job`.`QUEUE_ID` AS `QUEUE_ID` from `cm_run_job` where ((`cm_run_job`.`STATE` = 3) and (cast(now() as date) = cast(`cm_run_job`.`U_DATE` as date)));

-- ----------------------------
--  View definition for `v_run_header_running_common`
-- ----------------------------
DROP VIEW IF EXISTS `v_run_header_running_common`;
CREATE VIEW `v_run_header_running_common` AS select `cm_run_job`.`GROUP_ID` AS `GROUP_ID`,`cm_run_job`.`JOB_ID` AS `JOB_ID`,`cm_run_job`.`RUN_JOB_ID` AS `RUN_JOB_ID`,`cm_run_job`.`RUN_VERSION` AS `RUN_VERSION`,`cm_run_job`.`STATE` AS `STATE`,'RUNING_COMMON_TODAY' AS `TYPE`,`cm_run_job`.`QUEUE_ID` AS `QUEUE_ID` from `cm_run_job` where ((`cm_run_job`.`STATE` = 2) and (timestampdiff(SECOND,`cm_run_job`.`U_DATE`,now()) <= 1500));

-- ----------------------------
--  View definition for `v_run_header_running_expire`
-- ----------------------------
DROP VIEW IF EXISTS `v_run_header_running_expire`;
CREATE VIEW `v_run_header_running_expire` AS select `cm_run_job`.`GROUP_ID` AS `GROUP_ID`,`cm_run_job`.`JOB_ID` AS `JOB_ID`,`cm_run_job`.`RUN_JOB_ID` AS `RUN_JOB_ID`,`cm_run_job`.`RUN_VERSION` AS `RUN_VERSION`,6 AS `STATE`,'RUNNING_EXPIRE_TODAY' AS `TYPE`,`cm_run_job`.`QUEUE_ID` AS `QUEUE_ID` from `cm_run_job` where ((`cm_run_job`.`STATE` = 2) and (timestampdiff(SECOND,`cm_run_job`.`U_DATE`,now()) > 1500));

-- ----------------------------
--  View definition for `v_run_header_success`
-- ----------------------------
DROP VIEW IF EXISTS `v_run_header_success`;
CREATE VIEW `v_run_header_success` AS select `cm_run_job`.`GROUP_ID` AS `GROUP_ID`,`cm_run_job`.`JOB_ID` AS `JOB_ID`,`cm_run_job`.`RUN_JOB_ID` AS `RUN_JOB_ID`,`cm_run_job`.`RUN_VERSION` AS `RUN_VERSION`,`cm_run_job`.`STATE` AS `STATE`,'SUCCESS_TODAY' AS `TYPE`,`cm_run_job`.`QUEUE_ID` AS `QUEUE_ID` from `cm_run_job` where ((`cm_run_job`.`STATE` = 5) and (cast(now() as date) = cast(`cm_run_job`.`C_DATE` as date)));

-- ----------------------------
--  View definition for `v_run_header_wait_runtime`
-- ----------------------------
DROP VIEW IF EXISTS `v_run_header_wait_runtime`;
CREATE VIEW `v_run_header_wait_runtime` AS select `cm_run_job`.`GROUP_ID` AS `GROUP_ID`,`cm_run_job`.`JOB_ID` AS `JOB_ID`,`cm_run_job`.`RUN_JOB_ID` AS `RUN_JOB_ID`,`cm_run_job`.`RUN_VERSION` AS `RUN_VERSION`,0 AS `STATE`,'WAIT_RUNTIME_TODAY' AS `TYPE`,`cm_run_job`.`QUEUE_ID` AS `QUEUE_ID` from `cm_run_job` where ((`cm_run_job`.`STATE` = 0) or ((`cm_run_job`.`STATE` = 1) and isnull(`cm_run_job`.`DATE_LATEST_SEND`) and (cast(now() as date) = cast(`cm_run_job`.`C_DATE` as date))));

-- ----------------------------
--  View definition for `v_run_header`
-- ----------------------------
DROP VIEW IF EXISTS `v_run_header`;
CREATE VIEW `v_run_header` AS select `v_run_header_wait_runtime`.`GROUP_ID` AS `GROUP_ID`,`v_run_header_wait_runtime`.`JOB_ID` AS `JOB_ID`,`v_run_header_wait_runtime`.`RUN_JOB_ID` AS `RUN_JOB_ID`,`v_run_header_wait_runtime`.`RUN_VERSION` AS `RUN_VERSION`,`v_run_header_wait_runtime`.`STATE` AS `STATE`,`v_run_header_wait_runtime`.`TYPE` AS `TYPE`,`v_run_header_wait_runtime`.`QUEUE_ID` AS `QUEUE_ID` from `v_run_header_wait_runtime` union select `v_run_header_running_common`.`GROUP_ID` AS `GROUP_ID`,`v_run_header_running_common`.`JOB_ID` AS `JOB_ID`,`v_run_header_running_common`.`RUN_JOB_ID` AS `RUN_JOB_ID`,`v_run_header_running_common`.`RUN_VERSION` AS `RUN_VERSION`,`v_run_header_running_common`.`STATE` AS `STATE`,`v_run_header_running_common`.`TYPE` AS `TYPE`,`v_run_header_running_common`.`QUEUE_ID` AS `QUEUE_ID` from `v_run_header_running_common` union select `v_run_header_running_expire`.`GROUP_ID` AS `GROUP_ID`,`v_run_header_running_expire`.`JOB_ID` AS `JOB_ID`,`v_run_header_running_expire`.`RUN_JOB_ID` AS `RUN_JOB_ID`,`v_run_header_running_expire`.`RUN_VERSION` AS `RUN_VERSION`,`v_run_header_running_expire`.`STATE` AS `STATE`,`v_run_header_running_expire`.`TYPE` AS `TYPE`,`v_run_header_running_expire`.`QUEUE_ID` AS `QUEUE_ID` from `v_run_header_running_expire` union select `v_run_header_error`.`GROUP_ID` AS `GROUP_ID`,`v_run_header_error`.`JOB_ID` AS `JOB_ID`,`v_run_header_error`.`RUN_JOB_ID` AS `RUN_JOB_ID`,`v_run_header_error`.`RUN_VERSION` AS `RUN_VERSION`,`v_run_header_error`.`STATE` AS `STATE`,`v_run_header_error`.`TYPE` AS `TYPE`,`v_run_header_error`.`QUEUE_ID` AS `QUEUE_ID` from `v_run_header_error` union select `v_run_header_cancel`.`GROUP_ID` AS `GROUP_ID`,`v_run_header_cancel`.`JOB_ID` AS `JOB_ID`,`v_run_header_cancel`.`RUN_JOB_ID` AS `RUN_JOB_ID`,`v_run_header_cancel`.`RUN_VERSION` AS `RUN_VERSION`,`v_run_header_cancel`.`STATE` AS `STATE`,`v_run_header_cancel`.`TYPE` AS `TYPE`,`v_run_header_cancel`.`QUEUE_ID` AS `QUEUE_ID` from `v_run_header_cancel` union select `v_run_header_success`.`GROUP_ID` AS `GROUP_ID`,`v_run_header_success`.`JOB_ID` AS `JOB_ID`,`v_run_header_success`.`RUN_JOB_ID` AS `RUN_JOB_ID`,`v_run_header_success`.`RUN_VERSION` AS `RUN_VERSION`,`v_run_header_success`.`STATE` AS `STATE`,`v_run_header_success`.`TYPE` AS `TYPE`,`v_run_header_success`.`QUEUE_ID` AS `QUEUE_ID` from `v_run_header_success`;
