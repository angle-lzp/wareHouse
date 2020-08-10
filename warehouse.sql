/*
 Navicat MySQL Data Transfer

 Source Server         : Linux_Aliyun
 Source Server Type    : MySQL
 Source Server Version : 50647
 Source Host           : 120.26.43.45:3306
 Source Schema         : warehouse

 Target Server Type    : MySQL
 Target Server Version : 50647
 File Encoding         : 65001

 Date: 10/08/2020 20:37:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for courier
-- ----------------------------
DROP TABLE IF EXISTS `courier`;
CREATE TABLE `courier`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` int(11) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `appPassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of courier
-- ----------------------------
INSERT INTO `courier` VALUES (1, 2, 'tom', '$2a$10$YJCxR7ZUW0a5zXV64.ygOupTkNHEfWB56i7Rh0j4fpgvYmKp8D4lC', '123', '1231', '北京通州区');
INSERT INTO `courier` VALUES (4, 2, 'lucy', '$2a$10$zy0YMfUplsvINud.bbHudOe17Gf4XKHixCXoXmbq0DzxZh7pnLqC2', '123', '110120119', '海南省文昌市会文镇胜利街963');
INSERT INTO `courier` VALUES (5, 1, 'admin', '$2a$10$sBWEQHBYqkPbVISGrs91cObUAGXJGeIZqOO.H1UAT6szQZVCzhbl2', '123', '120119', '北京通州区');
INSERT INTO `courier` VALUES (7, 1, 'superAdmin', '$2a$10$4mAqbRX0qPZPX8896sZxUOZJutFsmKstiMVXVcX7kYLfk.klUAkDe', '123', '1897123548', '北京');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `onCarOrder` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `line` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `codeNum` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `cid` int(11) NULL DEFAULT NULL COMMENT '对应送货人',
  `number` int(11) NULL DEFAULT NULL COMMENT '货物件数',
  `imgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '照片路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES (1, '1', 'A', '000637', '文昌市人民医院', '文昌市文清大道42号', 1, NULL, NULL);
INSERT INTO `warehouse` VALUES (3, '2', 'A', '000722', '文昌文城汉松西医诊所', '海南省文昌市文城镇教育路2号', 1, NULL, NULL);
INSERT INTO `warehouse` VALUES (4, '3', 'A', '000727', '文昌文城益广药店', '海南省文昌市文城镇和平里7号', 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
