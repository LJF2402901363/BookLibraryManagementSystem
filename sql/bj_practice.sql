/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : bj_practice

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 05/03/2022 01:32:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键，自增',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `status` int(11) NULL DEFAULT 0,
  `type` int(11) NULL DEFAULT NULL,
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hobby` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `signature` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(4) NULL DEFAULT NULL,
  `accountTypeId` int(11) NULL DEFAULT NULL,
  `headImgPath` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (19, 'b', 'b', '2020-02-09 00:00:00', 0, 2, '中性', '打篮球', '我的世界我做主', 54, NULL, NULL);
INSERT INTO `account` VALUES (21, '陌意随影', 'a', '2020-02-09 00:00:00', 0, 3, '男', '打篮球', '我是一只小小鸟', 54, NULL, NULL);
INSERT INTO `account` VALUES (23, '牛军', 'a', '2020-02-09 00:00:00', 0, 2, '男性', '打篮球', '你是狗吗？', 54, NULL, NULL);
INSERT INTO `account` VALUES (24, 'gg', 'a', '2020-12-20 16:00:00', 0, 2, '男', '的的', '的的', 88, NULL, NULL);
INSERT INTO `account` VALUES (27, '张琪', '123456', '2020-02-19 16:00:00', 0, 2, '男', '打篮球', '暂无', 15, NULL, NULL);
INSERT INTO `account` VALUES (29, '张三', '123456', '2020-02-19 16:00:00', 0, 2, '男', '打篮球', '暂无', 15, NULL, NULL);
INSERT INTO `account` VALUES (30, '李四', '123456', '2020-02-19 16:00:00', 0, 2, '男', '打篮球', '暂无', 15, NULL, NULL);
INSERT INTO `account` VALUES (31, '王五', '123456', '2020-03-14 16:00:00', 0, 2, '男', '打篮球', '暂无', 15, NULL, NULL);
INSERT INTO `account` VALUES (32, '赵柳', '123456', '2020-02-19 16:00:00', 0, 2, '男', '打篮球', '暂无', 15, NULL, NULL);
INSERT INTO `account` VALUES (33, '王八', '123456', '2020-02-19 16:00:00', 0, 2, '男', '打篮球', '暂无', 15, NULL, NULL);
INSERT INTO `account` VALUES (34, '刘思', '123456', '2020-02-19 16:00:00', 0, 2, '男', '打篮球', '暂无', 15, NULL, NULL);
INSERT INTO `account` VALUES (35, '刘琦', '123456', '2020-02-19 16:00:00', 0, 2, '男', '打篮球', '暂无', 15, NULL, NULL);
INSERT INTO `account` VALUES (36, 'rwdsedsssh', '123456', '2020-02-19 16:00:00', 0, 2, '男', '打篮球', '暂无', 15, NULL, NULL);
INSERT INTO `account` VALUES (37, 'rwdsedssshd', '123456', '2020-02-19 16:00:00', 0, 2, '男', '打篮球', '暂无', 15, NULL, NULL);
INSERT INTO `account` VALUES (38, 'rwdsedssshdd', '123456', '2020-02-27 16:00:00', 0, 2, '男', '打篮球', '暂无', 15, NULL, NULL);
INSERT INTO `account` VALUES (39, 'dg', '123456', '2020-02-23 16:00:00', 0, 2, '男', '你说你', '你好啊世界', 95, NULL, NULL);
INSERT INTO `account` VALUES (41, 'dge', '123456', '2020-02-21 16:00:00', 0, 2, '男', '000', '你好啊世界', 95, NULL, NULL);
INSERT INTO `account` VALUES (42, 'a', 'a', '2020-12-21 12:00:50', 0, 3, '男', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for accounttype
-- ----------------------------
DROP TABLE IF EXISTS `accounttype`;
CREATE TABLE `accounttype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `canborrowdays` int(11) NULL DEFAULT NULL,
  `canborrowTimes` int(11) NULL DEFAULT NULL,
  `canborrowbooks` int(11) NULL DEFAULT NULL,
  `typeName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accounttype
-- ----------------------------
INSERT INTO `accounttype` VALUES (3, 10, 5, 4, '高级');
INSERT INTO `accounttype` VALUES (4, 10, 5, 4, '顶级');
INSERT INTO `accounttype` VALUES (5, 10, 5, 45, '终极');
INSERT INTO `accounttype` VALUES (7, 56, 5, 4, '菜鸟');
INSERT INTO `accounttype` VALUES (8, 10, 5, 4, '大佬');
INSERT INTO `accounttype` VALUES (9, 10, 5, 4, '神王');

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `accountId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phoneNumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `employeeNumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工编号',
  `departmentName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator
-- ----------------------------

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键，自增',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书名',
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `price` double NOT NULL COMMENT '价格',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT 0,
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sbn` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `bookImgPath` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '书籍表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (10, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 00:00:00', NULL);
INSERT INTO `book` VALUES (11, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 11:01:09', NULL);
INSERT INTO `book` VALUES (16, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 11:01:09', NULL);
INSERT INTO `book` VALUES (17, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 11:01:09', NULL);
INSERT INTO `book` VALUES (20, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 11:01:09', NULL);
INSERT INTO `book` VALUES (21, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 11:01:09', NULL);
INSERT INTO `book` VALUES (22, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 11:01:09', NULL);
INSERT INTO `book` VALUES (23, '是吗', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 11:01:09', NULL);
INSERT INTO `book` VALUES (24, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-19 16:00:00', NULL);
INSERT INTO `book` VALUES (25, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 11:01:09', NULL);
INSERT INTO `book` VALUES (26, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 11:01:09', NULL);
INSERT INTO `book` VALUES (27, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 11:01:09', NULL);
INSERT INTO `book` VALUES (28, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 11:01:09', NULL);
INSERT INTO `book` VALUES (29, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 11:01:09', NULL);
INSERT INTO `book` VALUES (30, '围城', '钱钟书', 45, '文学', 0, '这是一本文学书', '1226333', '2020-02-09 11:01:09', NULL);
INSERT INTO `book` VALUES (63, '清明河上图', '江南', 54, '其它', 0, '龙族作者', '512255', '2020-02-19 16:00:00', NULL);
INSERT INTO `book` VALUES (69, '围城', '战三', 152, '文学', 1, '你好世界', 'sbnang', '2020-04-06 04:49:15', NULL);
INSERT INTO `book` VALUES (70, '围城', '战三', 152, '文学', 1, '你好世界', 'sbnang', '2020-04-06 05:46:36', NULL);
INSERT INTO `book` VALUES (71, '围城', '战三', 152, '文学', 1, '你好世界', 'sbnang', '2020-04-06 05:49:49', NULL);
INSERT INTO `book` VALUES (72, '围城', '战三', 152, '文学', 1, '你好世界', 'sbnang', '2020-04-06 06:05:08', NULL);
INSERT INTO `book` VALUES (73, '围城', '战三', 152, '文学', 1, '你好世界', 'sbnang', '2020-04-06 07:05:50', NULL);

-- ----------------------------
-- Table structure for bookcate
-- ----------------------------
DROP TABLE IF EXISTS `bookcate`;
CREATE TABLE `bookcate`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `parentTypeCode` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bookcate
-- ----------------------------
INSERT INTO `bookcate` VALUES (5, '', '全部类别', '2020-03-08 14:02:04');
INSERT INTO `bookcate` VALUES (6, '全部类别', '文学', '2020-03-08 00:00:00');
INSERT INTO `bookcate` VALUES (7, '全部类别', '戏剧', '2020-03-08 14:02:04');
INSERT INTO `bookcate` VALUES (8, '全部类别', '玄幻', '2020-03-08 14:02:04');
INSERT INTO `bookcate` VALUES (10, '全部类别', '言情', '2020-03-08 14:02:04');
INSERT INTO `bookcate` VALUES (11, '言情', '小说', '2020-03-08 00:00:00');
INSERT INTO `bookcate` VALUES (12, '言情', '散文', '2020-03-08 00:00:00');
INSERT INTO `bookcate` VALUES (13, '言情', '文言文', '2020-03-08 00:00:00');
INSERT INTO `bookcate` VALUES (50, '言情', '江南', '2020-03-09 11:15:48');
INSERT INTO `bookcate` VALUES (55, '言情', '青春', '2020-03-19 14:28:06');
INSERT INTO `bookcate` VALUES (56, '玄幻', '热血', '2020-12-16 10:30:38');

-- ----------------------------
-- Table structure for booktype
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updateTime` date NULL DEFAULT NULL,
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of booktype
-- ----------------------------
INSERT INTO `booktype` VALUES (1, '情感', '这是情感分类', '2020-12-24 17:43:09', NULL, 0);

-- ----------------------------
-- Table structure for borrowbook
-- ----------------------------
DROP TABLE IF EXISTS `borrowbook`;
CREATE TABLE `borrowbook`  (
  `accountid` int(11) NULL DEFAULT NULL,
  `bookid` int(11) NULL DEFAULT NULL,
  `borrowtime` datetime(0) NULL DEFAULT NULL,
  `returntime` datetime(0) NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrowbook
-- ----------------------------
INSERT INTO `borrowbook` VALUES (23, 23, '2020-02-09 19:39:56', '2020-03-07 19:40:24', 1);
INSERT INTO `borrowbook` VALUES (23, 23, '2020-02-09 19:39:56', '2020-02-09 19:39:58', 2);
INSERT INTO `borrowbook` VALUES (23, 23, '2020-02-09 19:39:56', '2020-02-09 19:39:58', 3);
INSERT INTO `borrowbook` VALUES (23, 22, '2020-02-09 19:39:56', '2020-02-09 19:39:58', 4);
INSERT INTO `borrowbook` VALUES (23, 24, '2020-02-09 16:32:24', NULL, 6);
INSERT INTO `borrowbook` VALUES (19, 23, '2020-02-10 07:47:11', NULL, 7);
INSERT INTO `borrowbook` VALUES (23, 23, '2020-02-10 08:12:51', NULL, 8);
INSERT INTO `borrowbook` VALUES (30, 23, '2020-03-17 22:56:21', '2020-03-17 22:56:25', 9);
INSERT INTO `borrowbook` VALUES (27, 11, '2020-03-18 23:17:41', '2020-03-18 23:17:47', 10);

-- ----------------------------
-- Table structure for compensationstrategy
-- ----------------------------
DROP TABLE IF EXISTS `compensationstrategy`;
CREATE TABLE `compensationstrategy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(2) NOT NULL COMMENT '赔偿类型',
  `money` float NULL DEFAULT NULL COMMENT '赔偿金额',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of compensationstrategy
-- ----------------------------

-- ----------------------------
-- Table structure for librarian
-- ----------------------------
DROP TABLE IF EXISTS `librarian`;
CREATE TABLE `librarian`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `accountId` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `email` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phoneNumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `departmentName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门名字',
  `employeeId` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工ID',
  `sex` tinyint(1) NULL DEFAULT 0 COMMENT '性别',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of librarian
-- ----------------------------

-- ----------------------------
-- Table structure for syslog
-- ----------------------------
DROP TABLE IF EXISTS `syslog`;
CREATE TABLE `syslog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `operationType` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `details` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of syslog
-- ----------------------------
INSERT INTO `syslog` VALUES (2, 'B', '2020-02-27 00:00:00', '添加书籍', '在，，');
INSERT INTO `syslog` VALUES (3, 'B', '2020-02-27 00:00:00', '添加书籍', '你是？');
INSERT INTO `syslog` VALUES (4, 'ss', '2020-02-27 00:00:00', '添加书籍', '在，，');
INSERT INTO `syslog` VALUES (5, 'B', '2020-02-27 00:00:00', '添加书籍', '在，，');
INSERT INTO `syslog` VALUES (6, 'B', '2020-02-27 00:00:00', '添加书籍', '在，，');
INSERT INTO `syslog` VALUES (7, 'dd', '2020-02-27 00:00:00', '添加书籍', '在，，');
INSERT INTO `syslog` VALUES (8, 'B', '2020-02-27 16:58:13', '添加书籍', '在，，');
INSERT INTO `syslog` VALUES (9, 'B', '2020-02-27 16:58:14', '添加书籍', '在，，');
INSERT INTO `syslog` VALUES (10, 'B', '2020-02-27 16:58:14', '添加书籍', '在，，');
INSERT INTO `syslog` VALUES (11, 'B', '2020-02-27 16:58:15', '添加书籍', '在，，');
INSERT INTO `syslog` VALUES (12, 'B', '2020-02-27 16:58:16', '添加书籍', '在，，');
INSERT INTO `syslog` VALUES (13, 'B', '2020-02-27 16:58:17', '添加书籍', '在，，');
INSERT INTO `syslog` VALUES (14, 'B', '2020-02-27 16:58:17', '添加书籍', '在，，');
INSERT INTO `syslog` VALUES (15, 'A', '2020-02-27 16:58:18', '添加书籍', '在，，');
INSERT INTO `syslog` VALUES (17, 'C', '2020-02-28 15:31:39', '添加账户', 'sss');
INSERT INTO `syslog` VALUES (18, 'C', '2020-02-28 15:31:46', '添加账户', 'sss');
INSERT INTO `syslog` VALUES (19, 'C', '2020-02-28 15:31:47', '添加账户', 'sss');
INSERT INTO `syslog` VALUES (20, 'C', '2020-02-28 15:31:48', '添加账户', 'sss');
INSERT INTO `syslog` VALUES (21, 'C', '2020-02-28 15:31:49', '添加账户', 'sss');
INSERT INTO `syslog` VALUES (22, 'C', '2020-02-28 15:31:50', '添加账户', 'sss');
INSERT INTO `syslog` VALUES (23, 'C', '2020-02-28 15:31:50', '添加账户', 'sss');
INSERT INTO `syslog` VALUES (24, 'aa', '2020-03-15 08:40:37', '修改账号', '更新用户');
INSERT INTO `syslog` VALUES (25, 'aa', '2020-03-15 09:07:21', '修改账号', '更新用户');
INSERT INTO `syslog` VALUES (26, 'aa', '2020-03-17 23:17:52', '添加账户', 'ddd');
INSERT INTO `syslog` VALUES (27, 'aa', '2020-03-19 13:53:06', '修改账号', '更新用户');
INSERT INTO `syslog` VALUES (28, 'a', '2020-03-19 13:58:18', '修改账号', '更新用户');
INSERT INTO `syslog` VALUES (29, '陌意随影', '2020-12-21 03:31:13', '修改账号', '更新用户');
INSERT INTO `syslog` VALUES (30, '陌意随影', '2020-12-24 09:19:30', '删除书籍', '删除书籍');
INSERT INTO `syslog` VALUES (31, '陌意随影', '2020-12-24 09:19:30', '删除书籍', '删除书籍');
INSERT INTO `syslog` VALUES (32, '陌意随影', '2020-12-24 09:25:51', '修改账号', '更新用户');

SET FOREIGN_KEY_CHECKS = 1;
