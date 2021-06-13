/*
 Navicat Premium Data Transfer

 Source Server         : 121.5.241.18
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 121.5.241.18:3306
 Source Schema         : friends_springboot

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 02/06/2021 17:13:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commentContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `trendsId` int(11) NULL DEFAULT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  `userImages` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `commentTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `trends_id`(`trendsId`) USING BTREE,
  INDEX `user_id`(`userId`) USING BTREE,
  INDEX `userImages`(`userImages`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`trendsId`) REFERENCES `trends` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (109, '文艺', 31, 33, 'e1df2707-b89e-4a96-9348-2835dbb37c87.jpeg', '2021-05-09 23:30:55');
INSERT INTO `comment` VALUES (110, '今天是个文艺青年', 31, 32, 'f1a981df-3a90-4c13-825f-8fccee061498.jpg', '2021-05-10 10:25:49');
INSERT INTO `comment` VALUES (113, '学长，有计算机网络吗？', 32, 38, 'd8bcee4a-9e4b-4237-9f10-f1e3d46f102c.jpeg', '2021-05-11 19:45:55');
INSERT INTO `comment` VALUES (114, '艾玛，这段话爱了', 31, 35, '6e0b7a1d-aa90-480f-8e96-d2cc57f89c6c.jpg', '2021-05-11 23:21:41');
INSERT INTO `comment` VALUES (115, '讲的很好，点个赞', 36, 36, 'd68cb2ff-3e2d-4ed8-bc77-29acf31de394.jpg', '2021-05-11 23:22:56');
INSERT INTO `comment` VALUES (116, '哈哈哈', 37, 32, 'f1a981df-3a90-4c13-825f-8fccee061498.jpg', '2021-05-11 23:23:56');
INSERT INTO `comment` VALUES (117, '真的很棒', 36, 34, '46a19b3a-3fd5-46af-81b0-1b8babc9ea5e.jpeg', '2021-05-12 21:12:47');
INSERT INTO `comment` VALUES (118, '有道理', 36, 37, 'f4737dec-f8d1-4672-94f5-c19c4b9a0825.jpg', '2021-05-12 21:14:41');
INSERT INTO `comment` VALUES (128, '左手倒影右手年华，梦里花落知多少', 33, 32, 'f1a981df-3a90-4c13-825f-8fccee061498.jpg', '2021-05-11 20:01:34');

-- ----------------------------
-- Table structure for lost
-- ----------------------------
DROP TABLE IF EXISTS `lost`;
CREATE TABLE `lost`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lostName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `contactName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `contactPhone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `lookCount` int(11) NULL DEFAULT 0,
  `pushtime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  INDEX `uuid`(`uuid`) USING BTREE,
  CONSTRAINT `lost_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lost
-- ----------------------------
INSERT INTO `lost` VALUES (33, '钢琴', '我的钢琴丢在了未来世界', '贝多芬', '69785366890', 32, 'b1c84fe1-e185-4cbc-b53b-b66361dec0ca', 35, '2021-05-09 23:53:37');
INSERT INTO `lost` VALUES (34, '无线耳机', '我的无线耳机丢了，好像是4月30号周三周五下午丢的，请好心人捡到一定给我啊', '姚纪远', '17713268976', 32, '6fdf4e87-b6a4-4599-acde-74f9700f07f0', 32, '2021-05-10 10:53:51');
INSERT INTO `lost` VALUES (35, '小米手机', '艾玛，我手机丢了，丢哪了我也忘了，小米手机，捡到的好心人希望尽快联系我！！！', '薛梵曦', '18766453626', 35, '6a7b9ffa-17bd-494d-86ca-7073968b167c', 26, '2021-05-11 19:33:38');
INSERT INTO `lost` VALUES (36, '笔记本', '我记笔记的笔记本丢了，好像是5月3号丢的，捡到的联系我哦', '陈雨欣', '17732245998', 38, '642cbbd5-f06f-411b-9978-eee6b1d5ff29', 26, '2021-05-11 19:47:58');

-- ----------------------------
-- Table structure for lostimages
-- ----------------------------
DROP TABLE IF EXISTS `lostimages`;
CREATE TABLE `lostimages`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `images` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `lostUuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `lostUuid`(`lostUuid`) USING BTREE,
  CONSTRAINT `lostimages_ibfk_1` FOREIGN KEY (`lostUuid`) REFERENCES `lost` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lostimages
-- ----------------------------
INSERT INTO `lostimages` VALUES (30, '7d702bcf-49f4-4f5e-b2c6-121bdc7a655c.jpg', '6fdf4e87-b6a4-4599-acde-74f9700f07f0');
INSERT INTO `lostimages` VALUES (31, 'c6a0febf-af67-483a-aa7c-9540090affba.jpg', '6fdf4e87-b6a4-4599-acde-74f9700f07f0');
INSERT INTO `lostimages` VALUES (32, 'dbed7591-f986-4592-8573-655301f588aa.jpg', '6fdf4e87-b6a4-4599-acde-74f9700f07f0');
INSERT INTO `lostimages` VALUES (33, '1ac57be7-4e7f-4fde-b7a1-a2ee985523fa.jpg', '6a7b9ffa-17bd-494d-86ca-7073968b167c');
INSERT INTO `lostimages` VALUES (34, '20e304de-bedc-4de7-a6a9-c82bafb6996d.jpg', '6a7b9ffa-17bd-494d-86ca-7073968b167c');
INSERT INTO `lostimages` VALUES (35, 'be20e5d8-db4b-4aaf-87e0-cc348bc65d50.jpg', '642cbbd5-f06f-411b-9978-eee6b1d5ff29');
INSERT INTO `lostimages` VALUES (36, 'ccafa0c4-c6e7-46d7-97c5-7f26f1015c7f.jpg', '642cbbd5-f06f-411b-9978-eee6b1d5ff29');

-- ----------------------------
-- Table structure for online
-- ----------------------------
DROP TABLE IF EXISTS `online`;
CREATE TABLE `online`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personCount` int(255) NULL DEFAULT NULL,
  `onlineHour` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of online
-- ----------------------------
INSERT INTO `online` VALUES (1, 1, 0);
INSERT INTO `online` VALUES (2, 0, 1);
INSERT INTO `online` VALUES (3, 0, 2);
INSERT INTO `online` VALUES (4, 0, 3);
INSERT INTO `online` VALUES (5, 0, 4);
INSERT INTO `online` VALUES (6, 0, 5);
INSERT INTO `online` VALUES (7, 0, 6);
INSERT INTO `online` VALUES (8, 2, 7);
INSERT INTO `online` VALUES (9, 3, 8);
INSERT INTO `online` VALUES (10, 5, 9);
INSERT INTO `online` VALUES (11, 2, 10);
INSERT INTO `online` VALUES (12, 5, 11);
INSERT INTO `online` VALUES (13, 2, 12);
INSERT INTO `online` VALUES (14, 1, 13);
INSERT INTO `online` VALUES (15, 1, 14);
INSERT INTO `online` VALUES (16, 2, 15);
INSERT INTO `online` VALUES (17, 2, 16);
INSERT INTO `online` VALUES (18, 2, 17);
INSERT INTO `online` VALUES (19, 3, 18);
INSERT INTO `online` VALUES (20, 4, 19);
INSERT INTO `online` VALUES (21, 8, 20);
INSERT INTO `online` VALUES (22, 5, 21);
INSERT INTO `online` VALUES (23, 3, 22);
INSERT INTO `online` VALUES (24, 2, 23);

-- ----------------------------
-- Table structure for praise
-- ----------------------------
DROP TABLE IF EXISTS `praise`;
CREATE TABLE `praise`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `praiseUser` int(11) NULL DEFAULT NULL,
  `praiseTrends` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `praise_user`(`praiseUser`) USING BTREE,
  INDEX `praise_trends`(`praiseTrends`) USING BTREE,
  CONSTRAINT `praise_ibfk_1` FOREIGN KEY (`praiseUser`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `praise_ibfk_2` FOREIGN KEY (`praiseTrends`) REFERENCES `trends` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of praise
-- ----------------------------
INSERT INTO `praise` VALUES (58, 33, 31);
INSERT INTO `praise` VALUES (59, 32, 31);
INSERT INTO `praise` VALUES (61, 34, 31);
INSERT INTO `praise` VALUES (63, 35, 32);
INSERT INTO `praise` VALUES (64, 36, 32);
INSERT INTO `praise` VALUES (65, 38, 32);
INSERT INTO `praise` VALUES (66, 38, 31);
INSERT INTO `praise` VALUES (67, 35, 33);
INSERT INTO `praise` VALUES (68, 36, 36);
INSERT INTO `praise` VALUES (69, 32, 37);
INSERT INTO `praise` VALUES (70, 34, 36);
INSERT INTO `praise` VALUES (71, 37, 33);
INSERT INTO `praise` VALUES (72, 35, 42);
INSERT INTO `praise` VALUES (74, 32, 35);
INSERT INTO `praise` VALUES (76, 34, 42);

-- ----------------------------
-- Table structure for statistics
-- ----------------------------
DROP TABLE IF EXISTS `statistics`;
CREATE TABLE `statistics`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `allUser` int(11) NULL DEFAULT NULL,
  `allTrends` int(11) NULL DEFAULT NULL,
  `allLost` int(11) NULL DEFAULT NULL,
  `manCount` int(11) NULL DEFAULT NULL,
  `manRatio` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of statistics
-- ----------------------------
INSERT INTO `statistics` VALUES (1, 9, 8, 4, 6, 0.67);

-- ----------------------------
-- Table structure for trends
-- ----------------------------
DROP TABLE IF EXISTS `trends`;
CREATE TABLE `trends`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `praiseCount` int(11) NULL DEFAULT NULL,
  `pushTime` datetime NULL DEFAULT NULL,
  `commentCount` int(10) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`userId`) USING BTREE,
  CONSTRAINT `trends_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of trends
-- ----------------------------
INSERT INTO `trends` VALUES (31, 32, '你总是喜欢把事情拖到第二天，你不能总是这么拖了，有一天，你会有很多事情要做，你的余生都不够你用。——《余生的第一天》', 4, '2021-05-09 23:24:50', 3);
INSERT INTO `trends` VALUES (32, 34, '毕业卖书，在东校区，计算机专业课本，6-10元一本', 3, '2021-05-11 00:55:05', 1);
INSERT INTO `trends` VALUES (33, 35, '在这个忧伤而明媚的三月，我从我单薄的青春里打马而过，穿过紫堇，穿过木棉，穿过时隐时现的悲喜和无常。', 2, '2021-05-11 19:29:04', 1);
INSERT INTO `trends` VALUES (34, 36, '图书馆的小姐姐一直看我，我该如何表达', 0, '2021-05-11 19:35:39', 0);
INSERT INTO `trends` VALUES (35, 37, ' 时间决定你会在生命中遇见谁，你的心决定你想要谁出现在你的生命里，而你的行为决定最后谁能留下。――戴维.梭罗', 1, '2021-05-11 19:39:08', 0);
INSERT INTO `trends` VALUES (36, 38, ' 一次单程的旅途，所走过的每一步，都注定回不了头。有的路，是用脚去走。有的路，要用心去走。绊住脚的，往往不是荆棘石头，而是心。所以，看起来是路铺展在我们眼前，实际上，是心扑腾在路上。深一脚，浅一脚，欢喜在路上，悲伤在路上。但只要心不走在绝路上，生活也终不会给你绝路走。', 2, '2021-05-11 19:45:14', 3);
INSERT INTO `trends` VALUES (37, 33, '哈哈哈哈，强啊', 1, '2021-05-11 21:51:51', 1);
INSERT INTO `trends` VALUES (42, 32, '浮生若梦，此刻好时光', 2, '2021-05-20 20:04:36', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `trendsCount` int(10) UNSIGNED NULL DEFAULT 0,
  `focusCount` int(10) UNSIGNED NULL DEFAULT 0,
  `fansCount` int(10) UNSIGNED NULL DEFAULT 0,
  `sex` int(11) NULL DEFAULT NULL,
  `college` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `professional` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `titleImages` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `lostCount` int(11) NULL DEFAULT 0,
  `registerTime` datetime NULL DEFAULT NULL,
  `isResetPassword` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `titleImages`(`titleImages`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (32, '姚纪远', 'e10adc3949ba59abbe56e057f20f883e', '751522356@qq.com', 2, 0, 0, 1, '现代科技学院', '', '计算机科学与技术', 'f1a981df-3a90-4c13-825f-8fccee061498.jpg', 2, '2021-03-01 22:55:24', 0);
INSERT INTO `user` VALUES (33, '任佳鹏', '3f5854dbd6119b680c8f340474cb400e', '1172630689@qq.com', 1, 0, 0, 1, '现代科技学院', '', '计算机科学与技术', 'e1df2707-b89e-4a96-9348-2835dbb37c87.jpeg', 0, '2021-05-09 23:33:35', 0);
INSERT INTO `user` VALUES (34, '李千凡', 'e10adc3949ba59abbe56e057f20f883e', '751522357@qq.com', 1, 0, 0, 1, '信息科学与技术学院', '', '数据科学与大数据技术', '46a19b3a-3fd5-46af-81b0-1b8babc9ea5e.jpeg', 0, '2021-05-10 21:05:26', 0);
INSERT INTO `user` VALUES (35, '若曦', 'e10adc3949ba59abbe56e057f20f883e', '840874356@qq.com', 1, 0, 0, 0, '生命科学学院', '', '制药科学', '6e0b7a1d-aa90-480f-8e96-d2cc57f89c6c.jpg', 1, '2021-05-11 19:25:42', 0);
INSERT INTO `user` VALUES (36, '独白', 'e10adc3949ba59abbe56e057f20f883e', '751545676@qq.com', 1, 0, 0, 1, '外国语学院', '', '英语', 'd68cb2ff-3e2d-4ed8-bc77-29acf31de394.jpg', 0, '2021-05-11 19:34:45', 0);
INSERT INTO `user` VALUES (37, '易轩宇', 'e10adc3949ba59abbe56e057f20f883e', '751522346@qq.com', 1, 0, 0, 1, '动物科技学院', '', '动物科学', 'f4737dec-f8d1-4672-94f5-c19c4b9a0825.jpg', 0, '2021-05-11 19:38:23', 0);
INSERT INTO `user` VALUES (38, '南栀', 'e10adc3949ba59abbe56e057f20f883e', '751522341@qq.com', 1, 0, 0, 0, '理学院', '', '化学', 'd8bcee4a-9e4b-4237-9f10-f1e3d46f102c.jpeg', 1, '2021-05-11 19:43:37', 0);
INSERT INTO `user` VALUES (39, '陈雨欣', 'e10adc3949ba59abbe56e057f20f883e', '1285632361@qq.com', 0, 0, 0, 0, NULL, NULL, NULL, NULL, 0, '2021-05-13 22:30:58', 0);
INSERT INTO `user` VALUES (40, '吉炎臣', 'e10adc3949ba59abbe56e057f20f883e', '1119662286@qq.com', 0, 0, 0, 1, NULL, NULL, NULL, NULL, 0, '2021-06-01 14:58:11', 0);

SET FOREIGN_KEY_CHECKS = 1;
