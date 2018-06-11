/*
SQLyog Ultimate v8.32 
MySQL - 5.7.17 : Database - lottery_portal
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lottery_portal` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lottery_portal`;

/*Table structure for table `AUTOMATIC_PMKEY` */

DROP TABLE IF EXISTS `AUTOMATIC_PMKEY`;

CREATE TABLE `AUTOMATIC_PMKEY` (
  `PK_ID` char(10) NOT NULL COMMENT '主键ID',
  `PK_NAME` varchar(45) DEFAULT NULL COMMENT '主键名称',
  `TABLE_NAME` varchar(30) DEFAULT NULL COMMENT '表名',
  `FIELD_NAME` varchar(30) DEFAULT NULL COMMENT '字段名',
  `MAX_VAL` int(11) DEFAULT NULL COMMENT '当前最大值',
  `STEP_LEN` int(11) DEFAULT NULL COMMENT '步长',
  `PK_LEN` int(11) DEFAULT NULL COMMENT '主键长度',
  `PREFIX` char(4) DEFAULT NULL COMMENT '前缀',
  `SYS_CD` varchar(10) DEFAULT NULL COMMENT '所属系统编码',
  PRIMARY KEY (`PK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `AUTOMATIC_PMKEY` */

insert  into `AUTOMATIC_PMKEY`(`PK_ID`,`PK_NAME`,`TABLE_NAME`,`FIELD_NAME`,`MAX_VAL`,`STEP_LEN`,`PK_LEN`,`PREFIX`,`SYS_CD`) values ('0000000001','active_flag','ACTIVE_FLAG','active_flag',10,10,10,NULL,NULL);

/*Table structure for table `DMPAR` */

DROP TABLE IF EXISTS `DMPAR`;

CREATE TABLE `DMPAR` (
  `DMPAR_ID` varchar(20) NOT NULL,
  `DMPAR_NAME` varchar(45) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `DICT_TYPE` char(2) DEFAULT NULL,
  `MT_USER` char(10) DEFAULT NULL,
  `MT_TIME` varchar(19) DEFAULT NULL,
  `MEMO` varchar(500) DEFAULT NULL,
  `MODL_CD` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`DMPAR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `DMPAR` */

/*Table structure for table `DMPAR_DETAIL` */

DROP TABLE IF EXISTS `DMPAR_DETAIL`;

CREATE TABLE `DMPAR_DETAIL` (
  `DMPAR_ID` varchar(20) NOT NULL,
  `ITEM_ID` varchar(200) NOT NULL,
  `ITEM_VAL` varchar(60) DEFAULT NULL,
  `DISPLAY_ORDER` int(11) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `MT_USER` char(10) DEFAULT NULL,
  `MT_TIME` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`DMPAR_ID`,`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `DMPAR_DETAIL` */

/*Table structure for table `PARAM_SPS` */

DROP TABLE IF EXISTS `PARAM_SPS`;

CREATE TABLE `PARAM_SPS` (
  `records` varchar(100) NOT NULL,
  `originalMessages` text,
  `solutionName` varchar(100) DEFAULT NULL,
  `mobileVersion` varchar(100) DEFAULT NULL,
  `version` varchar(100) DEFAULT NULL,
  `language` varchar(100) DEFAULT NULL,
  `convertToEnglish` varchar(100) DEFAULT NULL,
  `userVocDate` varchar(100) DEFAULT NULL,
  `mailFwdDate` varchar(100) DEFAULT NULL,
  `bysWorkDate` varchar(100) DEFAULT NULL,
  `senderMailAddress` varchar(100) DEFAULT NULL,
  `countryId` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `supportedCountry` varchar(100) DEFAULT NULL,
  `investigationMailCategory` varchar(100) DEFAULT NULL,
  `issueCommnets` varchar(100) DEFAULT NULL,
  `vocType` varchar(100) DEFAULT NULL,
  `detailType` varchar(100) DEFAULT NULL,
  `sameWith` varchar(100) DEFAULT NULL,
  `actionTaken` varchar(100) DEFAULT NULL,
  `printerBrands` varchar(100) DEFAULT NULL,
  `printermModelName` varchar(100) DEFAULT NULL,
  `tatSinceUserSent` varchar(100) DEFAULT NULL,
  `tatSinceMailForwared` varchar(100) DEFAULT NULL,
  `tatSinceBysWorking` varchar(100) DEFAULT NULL,
  `replyDate` varchar(100) DEFAULT NULL,
  `subVoc` varchar(100) DEFAULT NULL,
  `replyDetails` varchar(100) DEFAULT NULL,
  `tryOrNot` varchar(100) DEFAULT NULL,
  `resolved` varchar(100) DEFAULT NULL,
  `needUserToReply` varchar(100) DEFAULT NULL,
  `gotReply` varchar(100) DEFAULT NULL,
  `waitrR_D` varchar(100) DEFAULT NULL,
  `needTracking` varchar(100) DEFAULT NULL,
  `Status` varchar(100) DEFAULT NULL,
  `worloadMins` varchar(100) DEFAULT NULL,
  `counts` varchar(100) DEFAULT NULL,
  `toCustomer` varchar(100) DEFAULT NULL,
  `typeAnalysis` varchar(100) DEFAULT NULL,
  `submitDefectBySsolution` varchar(100) DEFAULT NULL,
  `mt_user` varchar(10) NOT NULL,
  `mt_time` varchar(19) NOT NULL,
  PRIMARY KEY (`records`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `PARAM_SPS` */

/*Table structure for table `PORTAL_USER` */

DROP TABLE IF EXISTS `PORTAL_USER`;

CREATE TABLE `PORTAL_USER` (
  `USER_ID` char(10) NOT NULL COMMENT '用户ID',
  `USER_PASS` char(32) DEFAULT NULL COMMENT '用户密码',
  `USER_NAME` varchar(45) DEFAULT NULL COMMENT '用户姓名',
  `TEL` varchar(50) DEFAULT NULL COMMENT '座机',
  `MOBILE` varchar(20) DEFAULT NULL COMMENT '手机',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `MT_USER` char(10) DEFAULT NULL COMMENT '维护人',
  `VALID_DATE` varchar(10) DEFAULT NULL COMMENT '生效日期',
  `INVALID_DATE` varchar(10) DEFAULT NULL COMMENT '失效日期',
  `STATUS` varchar(2) DEFAULT NULL COMMENT '用户状态',
  `MT_TIME` varchar(19) DEFAULT NULL COMMENT '维护日期',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

/*Data for the table `PORTAL_USER` */

insert  into `PORTAL_USER`(`USER_ID`,`USER_PASS`,`USER_NAME`,`TEL`,`MOBILE`,`EMAIL`,`MT_USER`,`VALID_DATE`,`INVALID_DATE`,`STATUS`,`MT_TIME`) values ('asdasd','9BBCAFBAF4023F12AB1A066E5016A775','asd',NULL,'123','pengfei19890227@163.com',NULL,NULL,NULL,'1',NULL),('terstere','9BBCAFBAF4023F12AB1A066E5016A775','zpf',NULL,'123','pengfei19890227@163.com',NULL,'2017-08-27',NULL,'1','2017-08-27 18:08:02'),('test','206BC4803D6CC60C2E3C0B05E5AB4AC9','123',NULL,'13146233450','290079626@qq.com',NULL,NULL,NULL,'1',NULL),('test4','3C8E9B1EBA4ED0564656C44B397EA747','王五赵六',NULL,'13146233450','290079626@qq.com',NULL,NULL,NULL,'1',NULL),('test5','9BBCAFBAF4023F12AB1A066E5016A775','123',NULL,'123','pengfei19890227@163.com',NULL,NULL,NULL,'1',NULL),('test6','9BBCAFBAF4023F12AB1A066E5016A775','name',NULL,'123123','pengfei19890227@163.com',NULL,NULL,NULL,'1',NULL),('zpf','206BC4803D6CC60C2E3C0B05E5AB4AC9','张鹏飞',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `SYSTEM_PARAMS` */

DROP TABLE IF EXISTS `SYSTEM_PARAMS`;

CREATE TABLE `SYSTEM_PARAMS` (
  `PARAM_ID` char(10) NOT NULL,
  `PARAM_NAME` varchar(60) DEFAULT NULL,
  `PARAM_VALUE` varchar(90) DEFAULT NULL,
  `MT_USER` char(10) DEFAULT NULL,
  `MT_TIME` varchar(19) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `MEMO` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`PARAM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `SYSTEM_PARAMS` */

insert  into `SYSTEM_PARAMS`(`PARAM_ID`,`PARAM_NAME`,`PARAM_VALUE`,`MT_USER`,`MT_TIME`,`STATUS`,`MEMO`) values ('0000000001','mq_active_period_time','120','zpf','2017-03-18 17:15:00','1','activeMq 服务定时更新活动状态周期（单位：秒）'),('0000000002','registe_valid_code_allow_time','5','zpf','2017-03-19 17:15:00','1','注册时，邮箱验证码的超时时间');



CREATE TABLE `OPEN_SSQ_INFO` (
`id` BIGINT(20) NOT NULL AUTO_INCREMENT  COMMENT '自增主键',
`red_1` TINYINT NOT NULL COMMENT '红球1',
`red_2` TINYINT NOT NULL COMMENT '红球2',
`red_3` TINYINT NOT NULL COMMENT '红球3',
`red_4` TINYINT NOT NULL COMMENT '红球4',
`red_5` TINYINT NOT NULL COMMENT '红球5',
`red_6` TINYINT NOT NULL COMMENT '红球6',
`blue` TINYINT NOT NULL COMMENT '篮球',
`expect` VARCHAR(8) NOT NULL COMMENT '开奖批次',
`open_time` VARCHAR(10) NOT NULL COMMENT '开奖日期',
`created` TIMESTAMP NOT NULL COMMENT '创建时间',
`modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
`operator` VARCHAR(20) NOT NULL  COMMENT '操作人',
`yn` TINYINT(2) NOT NULL  COMMENT '是否有效',
PRIMARY KEY (`id`)
)
ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT
COMMENT '双色球开奖表';


CREATE TABLE `OPEN_SSQ_ALL_INFO` (
`id` BIGINT(20) NOT NULL AUTO_INCREMENT  COMMENT '自增主键',
`red_1` TINYINT DEFAULT 0 COMMENT '红球1',
`red_2` TINYINT DEFAULT 0 COMMENT '红球2',
`red_3` TINYINT DEFAULT 0 COMMENT '红球3',
`red_4` TINYINT DEFAULT 0 COMMENT '红球4',
`red_5` TINYINT DEFAULT 0 COMMENT '红球5',
`red_6` TINYINT DEFAULT 0 COMMENT '红球6',
`red_7` TINYINT DEFAULT 0 COMMENT '红球7',
`red_8` TINYINT DEFAULT 0 COMMENT '红球8',
`red_9` TINYINT DEFAULT 0 COMMENT '红球9',
`red_10` TINYINT DEFAULT 0 COMMENT '红球10',
`red_11` TINYINT DEFAULT 0 COMMENT '红球11',
`red_12` TINYINT DEFAULT 0 COMMENT '红球12',
`red_13` TINYINT DEFAULT 0 COMMENT '红球13',
`red_14` TINYINT DEFAULT 0 COMMENT '红球14',
`red_15` TINYINT DEFAULT 0 COMMENT '红球15',
`red_16` TINYINT DEFAULT 0 COMMENT '红球16',
`red_17` TINYINT DEFAULT 0 COMMENT '红球17',
`red_18` TINYINT DEFAULT 0 COMMENT '红球18',
`red_19` TINYINT DEFAULT 0 COMMENT '红球19',
`red_20` TINYINT DEFAULT 0 COMMENT '红球20',
`red_21` TINYINT DEFAULT 0 COMMENT '红球21',
`red_22` TINYINT DEFAULT 0 COMMENT '红球22',
`red_23` TINYINT DEFAULT 0 COMMENT '红球23',
`red_24` TINYINT DEFAULT 0 COMMENT '红球24',
`red_25` TINYINT DEFAULT 0 COMMENT '红球25',
`red_26` TINYINT DEFAULT 0 COMMENT '红球26',
`red_27` TINYINT DEFAULT 0 COMMENT '红球27',
`red_28` TINYINT DEFAULT 0 COMMENT '红球28',
`red_29` TINYINT DEFAULT 0 COMMENT '红球29',
`red_30` TINYINT DEFAULT 0 COMMENT '红球30',
`red_31` TINYINT DEFAULT 0 COMMENT '红球31',
`red_32` TINYINT DEFAULT 0 COMMENT '红球32',
`red_33` TINYINT DEFAULT 0 COMMENT '红球33',
`blue_1` TINYINT DEFAULT 0 COMMENT '篮球1',
`blue_2` TINYINT DEFAULT 0 COMMENT '篮球2',
`blue_3` TINYINT DEFAULT 0 COMMENT '篮球3',
`blue_4` TINYINT DEFAULT 0 COMMENT '篮球4',
`blue_5` TINYINT DEFAULT 0 COMMENT '篮球5',
`blue_6` TINYINT DEFAULT 0 COMMENT '篮球6',
`blue_7` TINYINT DEFAULT 0 COMMENT '篮球7',
`blue_8` TINYINT DEFAULT 0 COMMENT '篮球8',
`blue_9` TINYINT DEFAULT 0 COMMENT '篮球9',
`blue_10` TINYINT DEFAULT 0 COMMENT '篮球10',
`blue_11` TINYINT DEFAULT 0 COMMENT '篮球11',
`blue_12` TINYINT DEFAULT 0 COMMENT '篮球12',
`blue_13` TINYINT DEFAULT 0 COMMENT '篮球13',
`blue_14` TINYINT DEFAULT 0 COMMENT '篮球14',
`blue_15` TINYINT DEFAULT 0 COMMENT '篮球15',
`blue_16` TINYINT DEFAULT 0 COMMENT '篮球16',
`expect` VARCHAR(8) NOT NULL COMMENT '开奖批次',
`open_time` VARCHAR(10) NOT NULL COMMENT '开奖日期',
`created` TIMESTAMP NOT NULL COMMENT '创建时间',
`modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
`operator` VARCHAR(20) NOT NULL  COMMENT '操作人',
`yn` TINYINT(2) NOT NULL  COMMENT '是否有效',
PRIMARY KEY (`id`)
)
ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT
COMMENT '双色球开奖表(展开方式)';


CREATE TABLE `USER_SUBSCRIPTION_OPEN_INFO` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` char(10) NOT NULL COMMENT '用户ID',
  `sub_type` varchar(200) NOT NULL COMMENT '订阅类型',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `operator` varchar(20) NOT NULL COMMENT '操作人',
  `yn` tinyint(2) NOT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开奖信息订阅表';