/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : websocket

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2016-11-16 00:25:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for visit
-- ----------------------------
DROP TABLE IF EXISTS `visit`;
CREATE TABLE `visit` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `nick` varchar(32) DEFAULT NULL,
  `address` varchar(32) DEFAULT NULL,
  `joinTime` datetime DEFAULT NULL,
  `uid` varchar(32) DEFAULT NULL,
  `ip` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
