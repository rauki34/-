/*
Navicat MySQL Data Transfer

Source Server         : mysql数据库
Source Server Version : 50726
Source Host           : localhost:3307
Source Database       : ruoyi

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2024-11-16 17:09:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cs42
-- ----------------------------
DROP TABLE IF EXISTS `cs42`;
CREATE TABLE `cs42` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `jcdw` varchar(64) DEFAULT NULL COMMENT '检测单位',
  `jlbh` varchar(64) DEFAULT NULL COMMENT '记录编号',
  `gcmc` varchar(64) DEFAULT NULL COMMENT '工程名称',
  `htd` varchar(64) DEFAULT NULL COMMENT '合同段',
  `sgdw` varchar(64) DEFAULT NULL COMMENT '施工单位',
  `jldw` varchar(64) DEFAULT NULL COMMENT '监理单位',
  `gcbw` varchar(64) DEFAULT NULL COMMENT '工程部位/用途',
  `ypxx` varchar(64) DEFAULT NULL COMMENT '样品信息',
  `jcrq` datetime DEFAULT NULL COMMENT '检测日期',
  `cstj` varchar(64) DEFAULT NULL COMMENT '试验条件 温度： ℃ ，相对湿度： %',
  `jcyj` varchar(64) DEFAULT NULL COMMENT '检测依据',
  `pdyj` varchar(64) DEFAULT NULL COMMENT '判定依据',
  `zyyqsbmc` varchar(64) DEFAULT NULL COMMENT '主要仪器设备名称及其编号',
  `sjb3` float(10,2) DEFAULT NULL COMMENT '水胶比',
  `lddcs` float(10,2) DEFAULT NULL COMMENT '流动度 初始',
  `ldd30` float(10,2) DEFAULT NULL COMMENT '流动度 30min',
  `ldd60` float(10,2) DEFAULT NULL COMMENT '流动度 60min',
  `msl3` float(10,2) DEFAULT NULL COMMENT '泌水率 3h',
  `msl24` float(10,2) DEFAULT NULL COMMENT '泌水率 24h',
  `zypzl3` float(10,2) DEFAULT NULL COMMENT '自由膨胀率 3h',
  `zypzl24` float(10,2) DEFAULT NULL COMMENT '自由膨胀率 24h',
  `ylmsl22` float(10,2) DEFAULT NULL COMMENT '压力泌水率 0.22MPa',
  `ylmsl36` float(10,2) DEFAULT NULL COMMENT '压力泌水率 0.36MPa',
  `cyd` varchar(64) DEFAULT NULL COMMENT '充盈度',
  `njsjcn` float(10,2) DEFAULT NULL COMMENT '凝结时间 初凝',
  `njsjzn` float(10,2) DEFAULT NULL COMMENT '凝结时间 终凝',
  `bz` varchar(64) DEFAULT NULL COMMENT '备注',
  `sjb4` float(10,2) DEFAULT NULL COMMENT '水胶比',
  `sjsl` int(11) DEFAULT NULL COMMENT '试件数量',
  `yhfs` varchar(64) DEFAULT NULL COMMENT '养护方式',
  `cxrq` datetime DEFAULT NULL COMMENT '成型日期',
  `yjrq7` datetime DEFAULT NULL COMMENT '压件日期 7d',
  `yjrq28` datetime DEFAULT NULL COMMENT '压件日期 28d',
  `kzqd7` float(10,2) DEFAULT NULL COMMENT '抗折强度 7d',
  `kzqd28` float(10,2) DEFAULT NULL COMMENT '抗折强度 28d',
  `kyqd7` float(10,2) DEFAULT NULL COMMENT '抗压强度 7d',
  `kyqd28` float(10,2) DEFAULT NULL COMMENT '抗压强度 28d',
  `sjb5` varchar(64) DEFAULT NULL COMMENT '水胶比',
  `sn` varchar(64) DEFAULT NULL COMMENT '水泥',
  `s` varchar(64) DEFAULT NULL COMMENT '水',
  `wjj` varchar(64) DEFAULT NULL COMMENT '外加剂',
  `wclname` varchar(64) DEFAULT NULL COMMENT '外掺料名',
  `wcl` varchar(64) DEFAULT NULL COMMENT '外掺料',
  `llmd` varchar(64) DEFAULT NULL COMMENT '理论密度',
  `scmd` varchar(64) DEFAULT NULL COMMENT '实测密度',
  `jzxs` varchar(64) DEFAULT NULL COMMENT '校正系数',
  `fjsm` varchar(64) DEFAULT NULL COMMENT '附加声明',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
