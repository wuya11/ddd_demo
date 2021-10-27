/*
Navicat MySQL Data Transfer

Source Database       : epet_finance

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2021-10-21 20:00:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for as_compensate
-- ----------------------------
DROP TABLE IF EXISTS `as_compensate`;
CREATE TABLE `as_compensate` (
  `coid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '补偿编号',
  `sub_oid` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '订单编号',
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺编号',
  `reference_money` decimal(12,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '补偿建议总额',
  `compensate_money` decimal(12,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '补偿总额',
  `cstate` tinyint(1) unsigned NOT NULL DEFAULT '99' COMMENT '补偿单状态（99待审核，1补偿中，4待补偿，2完成）',
  `iscomplete` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否完成',
  `cuid` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '补偿用户编号',
  `repayway` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '补偿方式（1.红包，2.代金券，3.退款，5补发，6余额）',
  `dutytype` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '追责分类（1.公司原因，2.物流原因，3.供应商原因）',
  `repay_attribute` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '商品属性 1商品，2 非商品',
  `strategy_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '补偿策略类型 1补发，2 退款 3非商品补偿',
  `strategy_json` text NOT NULL COMMENT '补偿策略内容',
  `reasonid` varchar(100) NOT NULL DEFAULT '' COMMENT '原因编号(多级原因逗号隔开)',
  `imgids` varchar(512) DEFAULT NULL COMMENT '附件编号',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '说明',
  `finished_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '完成时间',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `actuid` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `creator_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '创建人归属（1前台，2后台）',
  PRIMARY KEY (`coid`)
) ENGINE=InnoDB AUTO_INCREMENT=1634621164846 DEFAULT CHARSET=utf8mb4 COMMENT='售后补偿表';

-- ----------------------------
-- Records of as_compensate
-- ----------------------------
INSERT INTO `as_compensate` VALUES ('1634618056351', '252840521259467465', '14', '2.00', '2.00', '99', '0', '123131312', '3', '1', '1', '2', '{\"compensateBillId\":{\"uuid\":\"1cc8c4ea-ba36-4d44-9930-d886bc3a1ac9\",\"coid\":1634617707926},\"strategySupplyAgainList\":null,\"strategyGoodsList\":[{\"ogid\":252840521267851982,\"amount\":1,\"goodsNum\":1,\"referencePrice\":1,\"gid\":null},{\"ogid\":252840521267851983,\"amount\":1,\"goodsNum\":1,\"referencePrice\":1,\"gid\":null}],\"strategyNoGoodsV\":null}', '12', 'www.test,www.biatu.tom', 'ces', '1970-01-01 08:00:00', '2021-10-19 12:28:34', '2021-10-21 19:44:56', '31321506522838016', '1');
INSERT INTO `as_compensate` VALUES ('1634618056352', '252840521259467465', '14', '2.00', '2.00', '99', '0', '123131312', '3', '1', '1', '2', '{\"compensateBillId\":{\"uuid\":\"9f10abdf-2b77-43b5-8ba4-6948f789b43c\",\"coid\":1634618056352},\"strategySupplyAgainList\":null,\"strategyGoodsList\":[{\"ogid\":252840521267851982,\"amount\":1,\"goodsNum\":1,\"referencePrice\":1,\"gid\":null},{\"ogid\":252840521267851983,\"amount\":1,\"goodsNum\":1,\"referencePrice\":1,\"gid\":null}],\"strategyNoGoodsV\":null}', '12', 'www.test,www.biatu.tom', 'ces', '1970-01-01 08:00:00', '2021-10-19 12:34:23', '2021-10-19 12:34:23', '31321506522838016', '1');
INSERT INTO `as_compensate` VALUES ('1634621164845', '252840521259467465', '14', '2.00', '2.00', '2', '0', '123131312', '3', '1', '1', '2', '{\"compensateBillId\":{\"uuid\":\"1fefaee1-027e-4df9-916f-08c049f489e0\",\"coid\":1634621164845},\"strategySupplyAgainList\":null,\"strategyGoodsList\":[{\"ogid\":252840521267851982,\"amount\":1,\"goodsNum\":1,\"referencePrice\":1,\"gid\":null},{\"ogid\":252840521267851983,\"amount\":1,\"goodsNum\":1,\"referencePrice\":1,\"gid\":null}],\"strategyNoGoodsV\":null}', '12', 'www.test,www.biatu.tom', 'ces', '1970-01-01 08:00:00', '2021-10-19 13:26:09', '2021-10-21 19:51:54', '31321506522838016', '1');
