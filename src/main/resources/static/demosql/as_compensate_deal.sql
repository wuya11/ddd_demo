/*
Navicat MySQL Data Transfer


Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2021-10-21 20:00:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for as_compensate_deal
-- ----------------------------
DROP TABLE IF EXISTS `as_compensate_deal`;
CREATE TABLE `as_compensate_deal` (
  `deal_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '履约单号',
  `coid` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '补偿单号',
  `return_id` varchar(64) NOT NULL DEFAULT '' COMMENT '补偿下级单编号',
  `deal_state` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '履约单处理状态  1 成功 2失败  3处理中',
  `creator` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '操作人',
  `msg` varchar(126) DEFAULT NULL COMMENT '日志信息',
  `send_deal_json` varchar(215) DEFAULT NULL COMMENT '请求json记录',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`deal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='售后补偿履约日志表';

-- ----------------------------
-- Records of as_compensate_deal
-- ----------------------------
INSERT INTO `as_compensate_deal` VALUES ('3', '1634621164845', 'refund_id', '1', '0', '退款处理成功', 'ask refund json', '2021-10-21 19:30:52', '2021-10-21 19:51:36');
