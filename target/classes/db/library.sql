/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : library

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 21/04/2022 17:15:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员姓名',
  `admin_pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员密码',
  `admin_email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员邮箱',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', '501455447@qq.com');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '书的id',
  `book_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书的名字',
  `book_author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书的作者',
  `book_publish` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书的出版时间',
  `book_category` int(0) NULL DEFAULT NULL COMMENT '书的分类',
  `book_price` double NULL DEFAULT NULL COMMENT '书的价格',
  `book_introduction` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书的介绍',
  PRIMARY KEY (`book_id`) USING BTREE,
  INDEX `book_category`(`book_category`) USING BTREE,
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`book_category`) REFERENCES `book_category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '巨人的陨落', '肯.福莱特', '江苏凤凰文艺出版社', 1, 129, '在第一次世界大战中发生的故事');
INSERT INTO `book` VALUES (2, '三体', '刘慈欣', '南京大学出版社', 1, 68, '科幻小说');
INSERT INTO `book` VALUES (3, '复活', '列夫.托尔斯泰', '上海译文出版社', 1, 19, '俄国小说');
INSERT INTO `book` VALUES (6, '平凡的世界', '路遥', '上海文艺出版社', 1, 88, '孙少平和孙少安两兄弟...');
INSERT INTO `book` VALUES (15, '白鹿原', '陈忠实', '南京出版社', 1, 36, '当代小说');
INSERT INTO `book` VALUES (16, '计算机网络', '谢希仁', '电子工业出版社', 3, 49, '计算机专业书籍');
INSERT INTO `book` VALUES (17, '霍乱时期的爱情', '加西亚·马尔克斯', '译林出版社', 9, 39, '外国小说');
INSERT INTO `book` VALUES (18, '天才在左疯子在右', '高铭', '北京联合出版公司', 1, 39.8, '心理学');
INSERT INTO `book` VALUES (19, '废都', '贾平凹', '商务印书馆', 8, 29, '当代小说');
INSERT INTO `book` VALUES (20, 'jQuery', 'Ryan', '中国电力出版社', 3, 78, 'js库');
INSERT INTO `book` VALUES (21, 'python数据爬虫', '张博文', '清华大学出版社', 3, 52, '带你走进爬虫的世界');
INSERT INTO `book` VALUES (22, '入门python可视化', 'variation', '电子大学出版社', 8, 61, '探究数据背后的秘密');
INSERT INTO `book` VALUES (71, 'Springboot从入门到实践', '筱威', '北京大学出版社', 3, 78, '带你走进spirngboot');

-- ----------------------------
-- Table structure for book_category
-- ----------------------------
DROP TABLE IF EXISTS `book_category`;
CREATE TABLE `book_category`  (
  `category_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '书的分类id',
  `category_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_category
-- ----------------------------
INSERT INTO `book_category` VALUES (1, '小说');
INSERT INTO `book_category` VALUES (2, '历史');
INSERT INTO `book_category` VALUES (3, '计算机');
INSERT INTO `book_category` VALUES (4, '哲学');
INSERT INTO `book_category` VALUES (5, '社会科学');
INSERT INTO `book_category` VALUES (6, '政治法律');
INSERT INTO `book_category` VALUES (7, '军事科学');
INSERT INTO `book_category` VALUES (8, '中国文学');
INSERT INTO `book_category` VALUES (9, '外国文学');
INSERT INTO `book_category` VALUES (10, '外国传记');
INSERT INTO `book_category` VALUES (11, '英语');
INSERT INTO `book_category` VALUES (12, '俄国小说');
INSERT INTO `book_category` VALUES (13, '心理学');
INSERT INTO `book_category` VALUES (14, '言情小说');
INSERT INTO `book_category` VALUES (15, '武侠小说');
INSERT INTO `book_category` VALUES (16, '环境科学');
INSERT INTO `book_category` VALUES (17, '纪实文学');

-- ----------------------------
-- Table structure for borrowingbooks
-- ----------------------------
DROP TABLE IF EXISTS `borrowingbooks`;
CREATE TABLE `borrowingbooks`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `book_id` int(0) NULL DEFAULT NULL COMMENT '书id',
  `date` date NULL DEFAULT NULL COMMENT '借阅时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `book_id`(`book_id`) USING BTREE,
  CONSTRAINT `borrowingbooks_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `borrowingbooks_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrowingbooks
-- ----------------------------
INSERT INTO `borrowingbooks` VALUES (9, 5, 1, '2020-08-04');
INSERT INTO `borrowingbooks` VALUES (28, 5, 19, '2020-08-01');
INSERT INTO `borrowingbooks` VALUES (31, 2, 20, '2020-08-02');
INSERT INTO `borrowingbooks` VALUES (57, 1, 17, '2020-08-19');
INSERT INTO `borrowingbooks` VALUES (61, 1, 16, '2020-08-21');

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `dept_id` int(0) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '信息工程学院');
INSERT INTO `dept` VALUES (2, '体育学院');
INSERT INTO `dept` VALUES (3, '美术学院');
INSERT INTO `dept` VALUES (4, '电子工程学院');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `user_pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user1', '123456', '501455447@qq.com');
INSERT INTO `user` VALUES (2, 'zbw', '123456', '501455447@qq.com');
INSERT INTO `user` VALUES (5, 'user2', '123456', '501455447@qq.com');
INSERT INTO `user` VALUES (6, 'LeBronJames', '123456', '501455447@qq.com');
INSERT INTO `user` VALUES (7, '科比', '123456', '501455447@qq.com');
INSERT INTO `user` VALUES (8, '柏拉图', '123456', '501455447@qq.com');
INSERT INTO `user` VALUES (9, '拿破仑', '123456', '501455447@qq.com');
INSERT INTO `user` VALUES (10, '欧文', '123456', '501455447@qq.com');
INSERT INTO `user` VALUES (11, '库兹马', '123456', '501455447@qq.com');
INSERT INTO `user` VALUES (13, '魔术师', '123456', '501455447@qq.com');
INSERT INTO `user` VALUES (16, '周杰伦', '123456', '501455447@qq.com');
INSERT INTO `user` VALUES (23, 'Variation', '123456', '501455447@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
