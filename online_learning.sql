/*
 Navicat Premium Data Transfer

 Source Server         : Ubuntu
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : online_learning

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 20/06/2023 19:38:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像链接',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'root', 'admin', NULL, 'admin', '2023-06-20 10:00:22');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程名称',
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程简介',
  `score` double(255, 1) NULL DEFAULT NULL COMMENT '课程评分',
  `visable` int(0) NULL DEFAULT NULL COMMENT '发布状态',
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `finish_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_comment
-- ----------------------------
DROP TABLE IF EXISTS `course_comment`;
CREATE TABLE `course_comment`  (
  `course_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_lesson
-- ----------------------------
DROP TABLE IF EXISTS `course_lesson`;
CREATE TABLE `course_lesson`  (
  `id` bigint(0) NOT NULL COMMENT '课时编号',
  `course_id` bigint(0) NOT NULL COMMENT '课程id',
  `create_user` bigint(0) NULL DEFAULT NULL COMMENT '创建用户',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'title',
  `chapter` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课时章节',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '课程内容',
  `type` int(0) NULL DEFAULT NULL COMMENT '课程类型',
  `visable` int(0) NULL DEFAULT NULL COMMENT '发布状态',
  `resource_id` int(0) NULL DEFAULT NULL COMMENT '课时资源',
  `display_time` datetime(0) NULL DEFAULT NULL COMMENT '课时展示时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`, `course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_note
-- ----------------------------
DROP TABLE IF EXISTS `course_note`;
CREATE TABLE `course_note`  (
  `course_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `lesson_id` bigint(0) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_resource
-- ----------------------------
DROP TABLE IF EXISTS `course_resource`;
CREATE TABLE `course_resource`  (
  `id` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源名称',
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `type` int(0) NULL DEFAULT NULL COMMENT '资源类型',
  `mode` int(0) NULL DEFAULT NULL COMMENT '资源状态',
  `visable` int(0) NULL DEFAULT NULL COMMENT '发布状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_student
-- ----------------------------
DROP TABLE IF EXISTS `course_student`;
CREATE TABLE `course_student`  (
  `course_id` bigint(0) NOT NULL COMMENT ' 课程id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_study
-- ----------------------------
DROP TABLE IF EXISTS `course_study`;
CREATE TABLE `course_study`  (
  `course_id` int(0) NOT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `lesson_id` bigint(0) NULL DEFAULT NULL,
  `progress` int(0) NULL DEFAULT NULL COMMENT '学习进度',
  `finish` int(0) NULL DEFAULT NULL COMMENT '是否完成',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_teacher
-- ----------------------------
DROP TABLE IF EXISTS `course_teacher`;
CREATE TABLE `course_teacher`  (
  `course_id` bigint(0) NOT NULL COMMENT '课程id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '教师id',
  `duty` int(0) NULL DEFAULT NULL COMMENT '教师身份：助教、讲师、教授等',
  `contact` int(0) NULL DEFAULT NULL COMMENT '是否为课程联系人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `paper_id` bigint(0) NULL DEFAULT NULL COMMENT '试卷id',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '测试描述',
  `pass_score` double(255, 2) NULL DEFAULT NULL COMMENT '通过分数',
  `time_limit` int(0) NULL DEFAULT NULL COMMENT '限时分钟数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(0) NULL DEFAULT NULL COMMENT '是否删除',
  `expire_at` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `show_status` int(0) NULL DEFAULT NULL COMMENT '发布状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (1, 4, 1, '数据结构期末', 60.00, 120, '2023-06-20 13:51:23', '2023-06-20 13:51:23', 0, NULL, NULL);
INSERT INTO `exam` VALUES (2, 4, 1, '数据结构期末', 60.00, 120, '2023-06-20 14:03:54', '2023-06-20 14:03:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (3, 5, 2, 'Description 1', 34.00, 70, '2023-06-20 06:04:49', '2023-06-20 06:04:49', 0, NULL, NULL);
INSERT INTO `exam` VALUES (4, 1, 3, 'Description 2', 69.00, 71, '2023-06-20 06:04:49', '2023-06-20 06:04:49', 0, NULL, NULL);
INSERT INTO `exam` VALUES (5, 8, 2, 'Description 3', 75.00, 83, '2023-06-20 06:04:49', '2023-06-20 06:04:49', 0, NULL, NULL);
INSERT INTO `exam` VALUES (6, 10, 2, 'Description 4', 80.00, 134, '2023-06-20 06:04:49', '2023-06-20 06:04:49', 0, NULL, NULL);
INSERT INTO `exam` VALUES (7, 10, 3, 'Description 5', 98.00, 130, '2023-06-20 06:04:49', '2023-06-20 06:04:49', 0, NULL, NULL);
INSERT INTO `exam` VALUES (8, 1, 2, 'Description 6', 26.00, 158, '2023-06-20 06:04:49', '2023-06-20 06:04:49', 0, NULL, NULL);
INSERT INTO `exam` VALUES (9, 1, 3, 'Description 7', 79.00, 90, '2023-06-20 06:04:49', '2023-06-20 06:04:49', 0, NULL, NULL);
INSERT INTO `exam` VALUES (10, 2, 3, 'Description 8', 83.00, 108, '2023-06-20 06:04:49', '2023-06-20 06:04:49', 0, NULL, NULL);
INSERT INTO `exam` VALUES (11, 7, 1, 'Description 9', 44.00, 116, '2023-06-20 06:04:49', '2023-06-20 06:04:49', 0, NULL, NULL);
INSERT INTO `exam` VALUES (12, 6, 2, 'Description 10', 65.00, 100, '2023-06-20 06:04:49', '2023-06-20 06:04:49', 0, NULL, NULL);
INSERT INTO `exam` VALUES (18, 1, 1, 'Description 1', 55.00, 63, '2023-06-20 06:04:50', '2023-06-20 06:04:50', 0, NULL, NULL);
INSERT INTO `exam` VALUES (19, 10, 3, 'Description 2', 69.00, 159, '2023-06-20 06:04:50', '2023-06-20 06:04:50', 0, NULL, NULL);
INSERT INTO `exam` VALUES (20, 5, 2, 'Description 3', 67.00, 73, '2023-06-20 06:04:50', '2023-06-20 06:04:50', 0, NULL, NULL);
INSERT INTO `exam` VALUES (21, 9, 1, 'Description 4', 73.00, 65, '2023-06-20 06:04:50', '2023-06-20 06:04:50', 0, NULL, NULL);
INSERT INTO `exam` VALUES (22, 3, 2, 'Description 5', 89.00, 85, '2023-06-20 06:04:50', '2023-06-20 06:04:50', 0, NULL, NULL);
INSERT INTO `exam` VALUES (23, 6, 3, 'Description 6', 25.00, 95, '2023-06-20 06:04:50', '2023-06-20 06:04:50', 0, NULL, NULL);
INSERT INTO `exam` VALUES (24, 5, 2, 'Description 7', 45.00, 143, '2023-06-20 06:04:50', '2023-06-20 06:04:50', 0, NULL, NULL);
INSERT INTO `exam` VALUES (25, 7, 1, 'Description 8', 35.00, 63, '2023-06-20 06:04:50', '2023-06-20 06:04:50', 0, NULL, NULL);
INSERT INTO `exam` VALUES (26, 8, 2, 'Description 9', 47.00, 62, '2023-06-20 06:04:50', '2023-06-20 06:04:50', 0, NULL, NULL);
INSERT INTO `exam` VALUES (27, 2, 3, 'Description 10', 33.00, 148, '2023-06-20 06:04:50', '2023-06-20 06:04:50', 0, NULL, NULL);
INSERT INTO `exam` VALUES (33, 4, 2, 'Description 1', 84.00, 84, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (34, 7, 3, 'Description 2', 61.00, 101, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (35, 3, 2, 'Description 3', 96.00, 135, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (36, 4, 3, 'Description 4', 29.00, 157, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (37, 10, 1, 'Description 5', 27.00, 138, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (38, 3, 1, 'Description 6', 84.00, 137, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (39, 9, 2, 'Description 7', 88.00, 139, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (40, 9, 1, 'Description 8', 42.00, 147, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (41, 10, 2, 'Description 9', 99.00, 81, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (42, 10, 1, 'Description 10', 64.00, 148, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (48, 2, 2, 'Description 1', 91.00, 63, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (49, 6, 2, 'Description 2', 91.00, 174, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (50, 2, 3, 'Description 3', 34.00, 127, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (51, 5, 2, 'Description 4', 44.00, 150, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (52, 10, 3, 'Description 5', 62.00, 103, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (53, 4, 3, 'Description 6', 47.00, 115, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (54, 5, 3, 'Description 7', 45.00, 86, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (55, 4, 3, 'Description 8', 51.00, 80, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (56, 9, 2, 'Description 9', 59.00, 120, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (57, 2, 1, 'Description 10', 100.00, 70, '2023-06-20 06:04:51', '2023-06-20 06:04:51', 0, NULL, NULL);
INSERT INTO `exam` VALUES (63, 5, 1, 'Description 1', 91.00, 84, '2023-06-20 06:04:52', '2023-06-20 06:04:52', 0, NULL, NULL);
INSERT INTO `exam` VALUES (64, 5, 2, 'Description 2', 91.00, 74, '2023-06-20 06:04:52', '2023-06-20 06:04:52', 0, NULL, NULL);
INSERT INTO `exam` VALUES (65, 10, 2, 'Description 3', 93.00, 156, '2023-06-20 06:04:52', '2023-06-20 06:04:52', 0, NULL, NULL);
INSERT INTO `exam` VALUES (66, 4, 1, 'Description 4', 95.00, 70, '2023-06-20 06:04:52', '2023-06-20 06:04:52', 0, NULL, NULL);
INSERT INTO `exam` VALUES (67, 7, 1, 'Description 5', 71.00, 141, '2023-06-20 06:04:52', '2023-06-20 06:04:52', 0, NULL, NULL);
INSERT INTO `exam` VALUES (68, 6, 2, 'Description 6', 78.00, 122, '2023-06-20 06:04:52', '2023-06-20 06:04:52', 0, NULL, NULL);
INSERT INTO `exam` VALUES (69, 5, 3, 'Description 7', 27.00, 113, '2023-06-20 06:04:52', '2023-06-20 06:04:52', 0, NULL, NULL);
INSERT INTO `exam` VALUES (70, 2, 2, 'Description 8', 47.00, 114, '2023-06-20 06:04:52', '2023-06-20 06:04:52', 0, NULL, NULL);
INSERT INTO `exam` VALUES (71, 4, 2, 'Description 9', 61.00, 157, '2023-06-20 06:04:52', '2023-06-20 06:04:52', 0, NULL, NULL);
INSERT INTO `exam` VALUES (72, 7, 3, 'Description 10', 86.00, 159, '2023-06-20 06:04:52', '2023-06-20 06:04:52', 0, NULL, NULL);
INSERT INTO `exam` VALUES (78, 8, 1, 'Description 1', 43.00, 60, '2023-06-20 06:04:53', '2023-06-20 06:04:53', 0, NULL, NULL);
INSERT INTO `exam` VALUES (79, 3, 2, 'Description 2', 44.00, 60, '2023-06-20 06:04:53', '2023-06-20 06:04:53', 0, NULL, NULL);
INSERT INTO `exam` VALUES (80, 3, 1, 'Description 3', 47.00, 167, '2023-06-20 06:04:53', '2023-06-20 06:04:53', 0, NULL, NULL);
INSERT INTO `exam` VALUES (81, 6, 1, 'Description 4', 27.00, 149, '2023-06-20 06:04:53', '2023-06-20 06:04:53', 0, NULL, NULL);
INSERT INTO `exam` VALUES (82, 7, 3, 'Description 5', 56.00, 119, '2023-06-20 06:04:53', '2023-06-20 06:04:53', 0, NULL, NULL);
INSERT INTO `exam` VALUES (83, 3, 3, 'Description 6', 92.00, 92, '2023-06-20 06:04:53', '2023-06-20 06:04:53', 0, NULL, NULL);
INSERT INTO `exam` VALUES (84, 7, 2, 'Description 7', 41.00, 72, '2023-06-20 06:04:53', '2023-06-20 06:04:53', 0, NULL, NULL);
INSERT INTO `exam` VALUES (85, 9, 3, 'Description 8', 53.00, 166, '2023-06-20 06:04:53', '2023-06-20 06:04:53', 0, NULL, NULL);
INSERT INTO `exam` VALUES (86, 4, 3, 'Description 9', 88.00, 94, '2023-06-20 06:04:53', '2023-06-20 06:04:53', 0, NULL, NULL);
INSERT INTO `exam` VALUES (87, 10, 3, 'Description 10', 41.00, 140, '2023-06-20 06:04:53', '2023-06-20 06:04:53', 0, NULL, NULL);
INSERT INTO `exam` VALUES (93, 8, 2, 'Description 1', 54.00, 110, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (94, 10, 2, 'Description 2', 100.00, 87, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (95, 2, 1, 'Description 3', 55.00, 112, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (96, 10, 2, 'Description 4', 93.00, 160, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (97, 5, 3, 'Description 5', 93.00, 171, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (98, 10, 3, 'Description 6', 82.00, 64, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (99, 9, 2, 'Description 7', 46.00, 83, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (100, 2, 1, 'Description 8', 67.00, 83, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (101, 3, 3, 'Description 9', 45.00, 162, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (102, 5, 3, 'Description 10', 70.00, 129, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (108, 2, 3, 'Description 1', 70.00, 139, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (109, 6, 2, 'Description 2', 45.00, 137, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (110, 5, 1, 'Description 3', 39.00, 62, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (111, 6, 3, 'Description 4', 79.00, 130, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (112, 8, 1, 'Description 5', 61.00, 163, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (113, 10, 3, 'Description 6', 98.00, 63, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (114, 3, 1, 'Description 7', 81.00, 115, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (115, 1, 1, 'Description 8', 86.00, 63, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (116, 7, 2, 'Description 9', 89.00, 68, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (117, 9, 3, 'Description 10', 34.00, 162, '2023-06-20 06:04:54', '2023-06-20 06:04:54', 0, NULL, NULL);
INSERT INTO `exam` VALUES (123, 10, 3, 'Description 1', 35.00, 151, '2023-06-20 06:04:55', '2023-06-20 06:04:55', 0, NULL, NULL);
INSERT INTO `exam` VALUES (124, 4, 3, 'Description 2', 51.00, 133, '2023-06-20 06:04:55', '2023-06-20 06:04:55', 0, NULL, NULL);
INSERT INTO `exam` VALUES (125, 1, 1, 'Description 3', 39.00, 83, '2023-06-20 06:04:55', '2023-06-20 06:04:55', 0, NULL, NULL);
INSERT INTO `exam` VALUES (126, 5, 2, 'Description 4', 49.00, 70, '2023-06-20 06:04:55', '2023-06-20 06:04:55', 0, NULL, NULL);
INSERT INTO `exam` VALUES (127, 5, 3, 'Description 5', 64.00, 139, '2023-06-20 06:04:55', '2023-06-20 06:04:55', 0, NULL, NULL);
INSERT INTO `exam` VALUES (128, 8, 3, 'Description 6', 30.00, 146, '2023-06-20 06:04:55', '2023-06-20 06:04:55', 0, NULL, NULL);
INSERT INTO `exam` VALUES (129, 5, 3, 'Description 7', 29.00, 146, '2023-06-20 06:04:55', '2023-06-20 06:04:55', 0, NULL, NULL);
INSERT INTO `exam` VALUES (130, 5, 1, 'Description 8', 82.00, 149, '2023-06-20 06:04:55', '2023-06-20 06:04:55', 0, NULL, NULL);
INSERT INTO `exam` VALUES (131, 5, 1, 'Description 9', 36.00, 109, '2023-06-20 06:04:55', '2023-06-20 06:04:55', 0, NULL, NULL);
INSERT INTO `exam` VALUES (132, 7, 3, 'Description 10', 29.00, 174, '2023-06-20 06:04:55', '2023-06-20 06:04:55', 0, NULL, NULL);
INSERT INTO `exam` VALUES (138, 7, 1, 'Description 1', 32.00, 172, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (139, 5, 1, 'Description 2', 80.00, 71, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (140, 3, 1, 'Description 3', 65.00, 114, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (141, 7, 1, 'Description 4', 36.00, 138, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (142, 8, 1, 'Description 5', 80.00, 127, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (143, 7, 2, 'Description 6', 89.00, 129, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (144, 4, 3, 'Description 7', 76.00, 132, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (145, 10, 1, 'Description 8', 38.00, 164, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (146, 9, 2, 'Description 9', 80.00, 99, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (147, 5, 2, 'Description 10', 57.00, 63, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (153, 9, 1, 'Description 1', 97.00, 157, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (154, 3, 2, 'Description 2', 57.00, 94, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (155, 2, 3, 'Description 3', 52.00, 167, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (156, 4, 1, 'Description 4', 45.00, 114, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (157, 5, 1, 'Description 5', 91.00, 77, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (158, 2, 1, 'Description 6', 65.00, 73, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (159, 10, 2, 'Description 7', 60.00, 172, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (160, 3, 2, 'Description 8', 53.00, 168, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (161, 4, 1, 'Description 9', 33.00, 152, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);
INSERT INTO `exam` VALUES (162, 6, 2, 'Description 10', 33.00, 126, '2023-06-20 06:04:56', '2023-06-20 06:04:56', 0, NULL, NULL);

-- ----------------------------
-- Table structure for forum
-- ----------------------------
DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum`  (
  `forum_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '论坛id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '论坛标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '论坛内容',
  `like_count` int(0) NULL DEFAULT NULL COMMENT '点赞数',
  `create_user_id` bigint(0) NULL DEFAULT NULL COMMENT '创建用户id',
  PRIMARY KEY (`forum_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for forum_comment
-- ----------------------------
DROP TABLE IF EXISTS `forum_comment`;
CREATE TABLE `forum_comment`  (
  `comment_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '评论内容',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '发布评论的用户id',
  `forum_id` bigint(0) NULL DEFAULT NULL COMMENT '所在论坛',
  `like_count` int(0) NULL DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '群组名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '群组描述',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '群组头像',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '组长id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_file
-- ----------------------------
DROP TABLE IF EXISTS `group_file`;
CREATE TABLE `group_file`  (
  `file_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件路径',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `group_id` bigint(0) NULL DEFAULT NULL COMMENT '所属群组id',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_label
-- ----------------------------
DROP TABLE IF EXISTS `group_label`;
CREATE TABLE `group_label`  (
  `label_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(0) NULL DEFAULT NULL COMMENT '群组id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签名',
  PRIMARY KEY (`label_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_member
-- ----------------------------
DROP TABLE IF EXISTS `group_member`;
CREATE TABLE `group_member`  (
  `user_id` bigint(0) NOT NULL COMMENT '组内用户id',
  `join_time` datetime(0) NULL DEFAULT NULL COMMENT '入组时间\r\n',
  `group_id` bigint(0) NULL DEFAULT NULL COMMENT '群组id',
  `study_time` int(0) NULL DEFAULT NULL COMMENT '总学习时长',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_plan
-- ----------------------------
DROP TABLE IF EXISTS `group_plan`;
CREATE TABLE `group_plan`  (
  `plan_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(0) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '学习计划',
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`plan_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `type` int(0) NULL DEFAULT NULL COMMENT '类型',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '消息内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message_attr
-- ----------------------------
DROP TABLE IF EXISTS `message_attr`;
CREATE TABLE `message_attr`  (
  `message_id` bigint(0) NOT NULL,
  `from` bigint(0) NULL DEFAULT NULL COMMENT '发送方',
  `to` bigint(0) NULL DEFAULT NULL COMMENT '接收方',
  `is_read` int(0) NULL DEFAULT NULL COMMENT '是否已读',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `type` int(0) NULL DEFAULT NULL COMMENT '类型',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '试卷描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES (4, 1, '数据结构期末测试卷一', '2023-06-20 13:02:55', '2023-06-20 13:02:55', 0);
INSERT INTO `paper` VALUES (5, 1, '数据结构期末测试卷一', '2023-06-20 13:52:03', '2023-06-20 13:52:03', 0);
INSERT INTO `paper` VALUES (6, 6, 'Description 1', '2023-06-20 05:53:32', '2023-06-20 05:53:32', 0);
INSERT INTO `paper` VALUES (7, 9, 'Description 2', '2023-06-20 05:53:32', '2023-06-20 05:53:32', 0);
INSERT INTO `paper` VALUES (8, 9, 'Description 3', '2023-06-20 05:53:32', '2023-06-20 05:53:32', 0);
INSERT INTO `paper` VALUES (9, 8, 'Description 4', '2023-06-20 05:53:32', '2023-06-20 05:53:32', 0);
INSERT INTO `paper` VALUES (10, 1, 'Description 5', '2023-06-20 05:53:32', '2023-06-20 05:53:32', 0);
INSERT INTO `paper` VALUES (11, 7, 'Description 6', '2023-06-20 05:53:32', '2023-06-20 05:53:32', 0);
INSERT INTO `paper` VALUES (12, 4, 'Description 7', '2023-06-20 05:53:32', '2023-06-20 05:53:32', 0);
INSERT INTO `paper` VALUES (13, 4, 'Description 8', '2023-06-20 05:53:32', '2023-06-20 05:53:32', 0);
INSERT INTO `paper` VALUES (14, 2, 'Description 9', '2023-06-20 05:53:32', '2023-06-20 05:53:32', 0);
INSERT INTO `paper` VALUES (15, 8, 'Description 10', '2023-06-20 05:53:32', '2023-06-20 05:53:32', 0);
INSERT INTO `paper` VALUES (21, 7, 'Description 1', '2023-06-20 05:53:39', '2023-06-20 05:53:39', 0);
INSERT INTO `paper` VALUES (22, 9, 'Description 2', '2023-06-20 05:53:39', '2023-06-20 05:53:39', 0);
INSERT INTO `paper` VALUES (23, 3, 'Description 3', '2023-06-20 05:53:39', '2023-06-20 05:53:39', 0);
INSERT INTO `paper` VALUES (24, 8, 'Description 4', '2023-06-20 05:53:39', '2023-06-20 05:53:39', 1);
INSERT INTO `paper` VALUES (25, 7, 'Description 5', '2023-06-20 05:53:39', '2023-06-20 05:53:39', 1);
INSERT INTO `paper` VALUES (26, 2, 'Description 6', '2023-06-20 05:53:39', '2023-06-20 05:53:39', 0);
INSERT INTO `paper` VALUES (27, 3, 'Description 7', '2023-06-20 05:53:39', '2023-06-20 05:53:39', 0);
INSERT INTO `paper` VALUES (28, 8, 'Description 8', '2023-06-20 05:53:39', '2023-06-20 05:53:39', 1);
INSERT INTO `paper` VALUES (29, 7, 'Description 9', '2023-06-20 05:53:39', '2023-06-20 05:53:39', 1);
INSERT INTO `paper` VALUES (30, 10, 'Description 10', '2023-06-20 05:53:39', '2023-06-20 05:53:39', 1);
INSERT INTO `paper` VALUES (36, 4, 'Description 1', '2023-06-20 05:53:42', '2023-06-20 05:53:42', 1);
INSERT INTO `paper` VALUES (37, 1, 'Description 2', '2023-06-20 05:53:42', '2023-06-20 05:53:42', 0);
INSERT INTO `paper` VALUES (38, 9, 'Description 3', '2023-06-20 05:53:42', '2023-06-20 05:53:42', 1);
INSERT INTO `paper` VALUES (39, 10, 'Description 4', '2023-06-20 05:53:42', '2023-06-20 05:53:42', 1);
INSERT INTO `paper` VALUES (40, 9, 'Description 5', '2023-06-20 05:53:42', '2023-06-20 05:53:42', 0);
INSERT INTO `paper` VALUES (41, 2, 'Description 6', '2023-06-20 05:53:42', '2023-06-20 05:53:42', 0);
INSERT INTO `paper` VALUES (42, 6, 'Description 7', '2023-06-20 05:53:42', '2023-06-20 05:53:42', 0);
INSERT INTO `paper` VALUES (43, 8, 'Description 8', '2023-06-20 05:53:42', '2023-06-20 05:53:42', 0);
INSERT INTO `paper` VALUES (44, 9, 'Description 9', '2023-06-20 05:53:42', '2023-06-20 05:53:42', 1);
INSERT INTO `paper` VALUES (45, 4, 'Description 10', '2023-06-20 05:53:42', '2023-06-20 05:53:42', 1);
INSERT INTO `paper` VALUES (51, 7, 'Description 1', '2023-06-20 05:53:43', '2023-06-20 05:53:43', 1);
INSERT INTO `paper` VALUES (52, 5, 'Description 2', '2023-06-20 05:53:43', '2023-06-20 05:53:43', 1);
INSERT INTO `paper` VALUES (53, 3, 'Description 3', '2023-06-20 05:53:43', '2023-06-20 05:53:43', 1);
INSERT INTO `paper` VALUES (54, 2, 'Description 4', '2023-06-20 05:53:43', '2023-06-20 05:53:43', 1);
INSERT INTO `paper` VALUES (55, 5, 'Description 5', '2023-06-20 05:53:43', '2023-06-20 05:53:43', 0);
INSERT INTO `paper` VALUES (56, 7, 'Description 6', '2023-06-20 05:53:43', '2023-06-20 05:53:43', 1);
INSERT INTO `paper` VALUES (57, 10, 'Description 7', '2023-06-20 05:53:43', '2023-06-20 05:53:43', 1);
INSERT INTO `paper` VALUES (58, 7, 'Description 8', '2023-06-20 05:53:43', '2023-06-20 05:53:43', 0);
INSERT INTO `paper` VALUES (59, 3, 'Description 9', '2023-06-20 05:53:43', '2023-06-20 05:53:43', 1);
INSERT INTO `paper` VALUES (60, 2, 'Description 10', '2023-06-20 05:53:43', '2023-06-20 05:53:43', 1);
INSERT INTO `paper` VALUES (66, 5, 'Description 1', '2023-06-20 05:53:44', '2023-06-20 05:53:44', 1);
INSERT INTO `paper` VALUES (67, 4, 'Description 2', '2023-06-20 05:53:44', '2023-06-20 05:53:44', 0);
INSERT INTO `paper` VALUES (68, 5, 'Description 3', '2023-06-20 05:53:44', '2023-06-20 05:53:44', 0);
INSERT INTO `paper` VALUES (69, 7, 'Description 4', '2023-06-20 05:53:44', '2023-06-20 05:53:44', 0);
INSERT INTO `paper` VALUES (70, 8, 'Description 5', '2023-06-20 05:53:44', '2023-06-20 05:53:44', 1);
INSERT INTO `paper` VALUES (71, 4, 'Description 6', '2023-06-20 05:53:44', '2023-06-20 05:53:44', 1);
INSERT INTO `paper` VALUES (72, 7, 'Description 7', '2023-06-20 05:53:44', '2023-06-20 05:53:44', 0);
INSERT INTO `paper` VALUES (73, 2, 'Description 8', '2023-06-20 05:53:44', '2023-06-20 05:53:44', 0);
INSERT INTO `paper` VALUES (74, 9, 'Description 9', '2023-06-20 05:53:44', '2023-06-20 05:53:44', 0);
INSERT INTO `paper` VALUES (75, 2, 'Description 10', '2023-06-20 05:53:44', '2023-06-20 05:53:44', 0);
INSERT INTO `paper` VALUES (81, 3, 'Description 1', '2023-06-20 05:53:45', '2023-06-20 05:53:45', 0);
INSERT INTO `paper` VALUES (82, 6, 'Description 2', '2023-06-20 05:53:45', '2023-06-20 05:53:45', 1);
INSERT INTO `paper` VALUES (83, 7, 'Description 3', '2023-06-20 05:53:45', '2023-06-20 05:53:45', 1);
INSERT INTO `paper` VALUES (84, 6, 'Description 4', '2023-06-20 05:53:45', '2023-06-20 05:53:45', 1);
INSERT INTO `paper` VALUES (85, 5, 'Description 5', '2023-06-20 05:53:45', '2023-06-20 05:53:45', 1);
INSERT INTO `paper` VALUES (86, 5, 'Description 6', '2023-06-20 05:53:45', '2023-06-20 05:53:45', 1);
INSERT INTO `paper` VALUES (87, 10, 'Description 7', '2023-06-20 05:53:45', '2023-06-20 05:53:45', 0);
INSERT INTO `paper` VALUES (88, 7, 'Description 8', '2023-06-20 05:53:45', '2023-06-20 05:53:45', 0);
INSERT INTO `paper` VALUES (89, 1, 'Description 9', '2023-06-20 05:53:45', '2023-06-20 05:53:45', 1);
INSERT INTO `paper` VALUES (90, 9, 'Description 10', '2023-06-20 05:53:45', '2023-06-20 05:53:45', 1);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `type` int(0) NULL DEFAULT NULL COMMENT '类型',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '题干',
  `option_A` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项A',
  `option_B` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项B',
  `option_C` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项C',
  `option_D` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项D',
  `option_E` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项E',
  `option_F` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项F',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '答案',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '是否删除 1删除 null 0未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (2, 1, '十二生肖过完要几年？', '12年', '13年', '13.5年', '12.5年', NULL, NULL, 'A', '2023-06-20 10:20:50', '2023-06-20 10:20:50', 1);
INSERT INTO `question` VALUES (3, 1, '十二生肖过完要几年？', '12年', '13年', '13.5年', '12.5年', NULL, NULL, 'C', '2023-06-20 11:15:34', '2023-06-20 11:15:34', 0);
INSERT INTO `question` VALUES (4, 1, '十二生肖过完要几年？', '12年', '13年', '13.5年', '12.5年', NULL, NULL, 'A', '2023-06-20 11:17:02', '2023-06-20 11:17:02', 0);
INSERT INTO `question` VALUES (5, 1, '十二生肖过完要几年？', '12年', '13年', '13.5年', '12.5年', NULL, NULL, 'A', '2023-06-20 13:54:26', '2023-06-20 13:54:26', 0);
INSERT INTO `question` VALUES (6, 7, 'Content 1', 'Option A 1', 'Option B 1', 'Option C 1', 'Option D 1', NULL, NULL, 'B', '2023-06-20 05:58:30', '2023-06-20 05:58:30', 0);
INSERT INTO `question` VALUES (7, 7, 'Content 2', 'Option A 2', 'Option B 2', 'Option C 2', 'Option D 2', NULL, NULL, 'B', '2023-06-20 05:58:30', '2023-06-20 05:58:30', 0);
INSERT INTO `question` VALUES (8, 6, 'Content 3', 'Option A 3', 'Option B 3', 'Option C 3', 'Option D 3', NULL, NULL, 'D', '2023-06-20 05:58:30', '2023-06-20 05:58:30', 0);
INSERT INTO `question` VALUES (9, 9, 'Content 4', 'Option A 4', 'Option B 4', 'Option C 4', 'Option D 4', NULL, NULL, 'B', '2023-06-20 05:58:30', '2023-06-20 05:58:30', 0);
INSERT INTO `question` VALUES (10, 9, 'Content 5', 'Option A 5', 'Option B 5', 'Option C 5', 'Option D 5', NULL, NULL, 'D', '2023-06-20 05:58:30', '2023-06-20 05:58:30', 0);
INSERT INTO `question` VALUES (11, 1, 'Content 6', 'Option A 6', 'Option B 6', 'Option C 6', 'Option D 6', NULL, NULL, 'B', '2023-06-20 05:58:30', '2023-06-20 05:58:30', 0);
INSERT INTO `question` VALUES (12, 6, 'Content 7', 'Option A 7', 'Option B 7', 'Option C 7', 'Option D 7', NULL, NULL, 'C', '2023-06-20 05:58:30', '2023-06-20 05:58:30', 0);
INSERT INTO `question` VALUES (13, 2, 'Content 8', 'Option A 8', 'Option B 8', 'Option C 8', 'Option D 8', NULL, NULL, 'B', '2023-06-20 05:58:30', '2023-06-20 05:58:30', 0);
INSERT INTO `question` VALUES (14, 2, 'Content 9', 'Option A 9', 'Option B 9', 'Option C 9', 'Option D 9', NULL, NULL, 'C', '2023-06-20 05:58:30', '2023-06-20 05:58:30', 0);
INSERT INTO `question` VALUES (15, 2, 'Content 10', 'Option A 10', 'Option B 10', 'Option C 10', 'Option D 10', NULL, NULL, 'C', '2023-06-20 05:58:30', '2023-06-20 05:58:30', 0);
INSERT INTO `question` VALUES (21, 3, 'Content 1', 'Option A 1', 'Option B 1', 'Option C 1', 'Option D 1', NULL, NULL, 'C', '2023-06-20 05:58:34', '2023-06-20 05:58:34', 0);
INSERT INTO `question` VALUES (22, 2, 'Content 2', 'Option A 2', 'Option B 2', 'Option C 2', 'Option D 2', NULL, NULL, 'A', '2023-06-20 05:58:34', '2023-06-20 05:58:34', 0);
INSERT INTO `question` VALUES (23, 2, 'Content 3', 'Option A 3', 'Option B 3', 'Option C 3', 'Option D 3', NULL, NULL, 'B', '2023-06-20 05:58:34', '2023-06-20 05:58:34', 0);
INSERT INTO `question` VALUES (24, 8, 'Content 4', 'Option A 4', 'Option B 4', 'Option C 4', 'Option D 4', NULL, NULL, 'B', '2023-06-20 05:58:34', '2023-06-20 05:58:34', 0);
INSERT INTO `question` VALUES (25, 9, 'Content 5', 'Option A 5', 'Option B 5', 'Option C 5', 'Option D 5', NULL, NULL, 'D', '2023-06-20 05:58:34', '2023-06-20 05:58:34', 0);
INSERT INTO `question` VALUES (26, 1, 'Content 6', 'Option A 6', 'Option B 6', 'Option C 6', 'Option D 6', NULL, NULL, 'C', '2023-06-20 05:58:34', '2023-06-20 05:58:34', 0);
INSERT INTO `question` VALUES (27, 2, 'Content 7', 'Option A 7', 'Option B 7', 'Option C 7', 'Option D 7', NULL, NULL, 'C', '2023-06-20 05:58:34', '2023-06-20 05:58:34', 0);
INSERT INTO `question` VALUES (28, 3, 'Content 8', 'Option A 8', 'Option B 8', 'Option C 8', 'Option D 8', NULL, NULL, 'D', '2023-06-20 05:58:34', '2023-06-20 05:58:34', 0);
INSERT INTO `question` VALUES (29, 6, 'Content 9', 'Option A 9', 'Option B 9', 'Option C 9', 'Option D 9', NULL, NULL, 'A', '2023-06-20 05:58:34', '2023-06-20 05:58:34', 0);
INSERT INTO `question` VALUES (30, 3, 'Content 10', 'Option A 10', 'Option B 10', 'Option C 10', 'Option D 10', NULL, NULL, 'C', '2023-06-20 05:58:34', '2023-06-20 05:58:34', 0);
INSERT INTO `question` VALUES (36, 6, 'Content 1', 'Option A 1', 'Option B 1', 'Option C 1', 'Option D 1', NULL, NULL, 'C', '2023-06-20 05:58:37', '2023-06-20 05:58:37', 0);
INSERT INTO `question` VALUES (37, 8, 'Content 2', 'Option A 2', 'Option B 2', 'Option C 2', 'Option D 2', NULL, NULL, 'D', '2023-06-20 05:58:37', '2023-06-20 05:58:37', 0);
INSERT INTO `question` VALUES (38, 3, 'Content 3', 'Option A 3', 'Option B 3', 'Option C 3', 'Option D 3', NULL, NULL, 'C', '2023-06-20 05:58:37', '2023-06-20 05:58:37', 0);
INSERT INTO `question` VALUES (39, 4, 'Content 4', 'Option A 4', 'Option B 4', 'Option C 4', 'Option D 4', NULL, NULL, 'C', '2023-06-20 05:58:37', '2023-06-20 05:58:37', 0);
INSERT INTO `question` VALUES (40, 4, 'Content 5', 'Option A 5', 'Option B 5', 'Option C 5', 'Option D 5', NULL, NULL, 'C', '2023-06-20 05:58:37', '2023-06-20 05:58:37', 0);
INSERT INTO `question` VALUES (41, 10, 'Content 6', 'Option A 6', 'Option B 6', 'Option C 6', 'Option D 6', NULL, NULL, 'A', '2023-06-20 05:58:37', '2023-06-20 05:58:37', 0);
INSERT INTO `question` VALUES (42, 10, 'Content 7', 'Option A 7', 'Option B 7', 'Option C 7', 'Option D 7', NULL, NULL, 'A', '2023-06-20 05:58:37', '2023-06-20 05:58:37', 0);
INSERT INTO `question` VALUES (43, 4, 'Content 8', 'Option A 8', 'Option B 8', 'Option C 8', 'Option D 8', NULL, NULL, 'D', '2023-06-20 05:58:37', '2023-06-20 05:58:37', 0);
INSERT INTO `question` VALUES (44, 2, 'Content 9', 'Option A 9', 'Option B 9', 'Option C 9', 'Option D 9', NULL, NULL, 'A', '2023-06-20 05:58:37', '2023-06-20 05:58:37', 0);
INSERT INTO `question` VALUES (45, 4, 'Content 10', 'Option A 10', 'Option B 10', 'Option C 10', 'Option D 10', NULL, NULL, 'A', '2023-06-20 05:58:37', '2023-06-20 05:58:37', 0);
INSERT INTO `question` VALUES (51, 8, 'Content 1', 'Option A 1', 'Option B 1', 'Option C 1', 'Option D 1', NULL, NULL, 'A', '2023-06-20 05:58:40', '2023-06-20 05:58:40', 0);
INSERT INTO `question` VALUES (52, 8, 'Content 2', 'Option A 2', 'Option B 2', 'Option C 2', 'Option D 2', NULL, NULL, 'B', '2023-06-20 05:58:40', '2023-06-20 05:58:40', 0);
INSERT INTO `question` VALUES (53, 4, 'Content 3', 'Option A 3', 'Option B 3', 'Option C 3', 'Option D 3', NULL, NULL, 'D', '2023-06-20 05:58:40', '2023-06-20 05:58:40', 0);
INSERT INTO `question` VALUES (54, 4, 'Content 4', 'Option A 4', 'Option B 4', 'Option C 4', 'Option D 4', NULL, NULL, 'A', '2023-06-20 05:58:40', '2023-06-20 05:58:40', 0);
INSERT INTO `question` VALUES (55, 10, 'Content 5', 'Option A 5', 'Option B 5', 'Option C 5', 'Option D 5', NULL, NULL, 'D', '2023-06-20 05:58:40', '2023-06-20 05:58:40', 0);
INSERT INTO `question` VALUES (56, 9, 'Content 6', 'Option A 6', 'Option B 6', 'Option C 6', 'Option D 6', NULL, NULL, 'C', '2023-06-20 05:58:40', '2023-06-20 05:58:40', 0);
INSERT INTO `question` VALUES (57, 7, 'Content 7', 'Option A 7', 'Option B 7', 'Option C 7', 'Option D 7', NULL, NULL, 'B', '2023-06-20 05:58:40', '2023-06-20 05:58:40', 0);
INSERT INTO `question` VALUES (58, 7, 'Content 8', 'Option A 8', 'Option B 8', 'Option C 8', 'Option D 8', NULL, NULL, 'B', '2023-06-20 05:58:40', '2023-06-20 05:58:40', 0);
INSERT INTO `question` VALUES (59, 10, 'Content 9', 'Option A 9', 'Option B 9', 'Option C 9', 'Option D 9', NULL, NULL, 'B', '2023-06-20 05:58:40', '2023-06-20 05:58:40', 0);
INSERT INTO `question` VALUES (60, 6, 'Content 10', 'Option A 10', 'Option B 10', 'Option C 10', 'Option D 10', NULL, NULL, 'B', '2023-06-20 05:58:40', '2023-06-20 05:58:40', 0);
INSERT INTO `question` VALUES (66, 3, 'Content 1', 'Option A 1', 'Option B 1', 'Option C 1', 'Option D 1', NULL, NULL, 'D', '2023-06-20 05:58:48', '2023-06-20 05:58:48', 0);
INSERT INTO `question` VALUES (67, 10, 'Content 2', 'Option A 2', 'Option B 2', 'Option C 2', 'Option D 2', NULL, NULL, 'D', '2023-06-20 05:58:48', '2023-06-20 05:58:48', 0);
INSERT INTO `question` VALUES (68, 6, 'Content 3', 'Option A 3', 'Option B 3', 'Option C 3', 'Option D 3', NULL, NULL, 'A', '2023-06-20 05:58:48', '2023-06-20 05:58:48', 0);
INSERT INTO `question` VALUES (69, 2, 'Content 4', 'Option A 4', 'Option B 4', 'Option C 4', 'Option D 4', NULL, NULL, 'A', '2023-06-20 05:58:48', '2023-06-20 05:58:48', 0);
INSERT INTO `question` VALUES (70, 4, 'Content 5', 'Option A 5', 'Option B 5', 'Option C 5', 'Option D 5', NULL, NULL, 'B', '2023-06-20 05:58:48', '2023-06-20 05:58:48', 0);
INSERT INTO `question` VALUES (71, 10, 'Content 6', 'Option A 6', 'Option B 6', 'Option C 6', 'Option D 6', NULL, NULL, 'C', '2023-06-20 05:58:48', '2023-06-20 05:58:48', 0);
INSERT INTO `question` VALUES (72, 10, 'Content 7', 'Option A 7', 'Option B 7', 'Option C 7', 'Option D 7', NULL, NULL, 'D', '2023-06-20 05:58:48', '2023-06-20 05:58:48', 0);
INSERT INTO `question` VALUES (73, 6, 'Content 8', 'Option A 8', 'Option B 8', 'Option C 8', 'Option D 8', NULL, NULL, 'A', '2023-06-20 05:58:48', '2023-06-20 05:58:48', 0);
INSERT INTO `question` VALUES (74, 4, 'Content 9', 'Option A 9', 'Option B 9', 'Option C 9', 'Option D 9', NULL, NULL, 'A', '2023-06-20 05:58:48', '2023-06-20 05:58:48', 0);
INSERT INTO `question` VALUES (75, 1, 'Content 10', 'Option A 10', 'Option B 10', 'Option C 10', 'Option D 10', NULL, NULL, 'B', '2023-06-20 05:58:48', '2023-06-20 05:58:48', 0);

-- ----------------------------
-- Table structure for question_paper
-- ----------------------------
DROP TABLE IF EXISTS `question_paper`;
CREATE TABLE `question_paper`  (
  `question_id` bigint(0) NOT NULL COMMENT '题目id',
  `question_score` int(0) NULL DEFAULT NULL COMMENT '分数',
  `paper_id` bigint(0) NOT NULL COMMENT '试卷id',
  PRIMARY KEY (`question_id`, `paper_id`) USING BTREE,
  INDEX `p_id`(`paper_id`) USING BTREE,
  CONSTRAINT `q_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `p_id` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_paper
-- ----------------------------
INSERT INTO `question_paper` VALUES (2, 16, 7);
INSERT INTO `question_paper` VALUES (2, 9, 10);
INSERT INTO `question_paper` VALUES (3, 15, 4);
INSERT INTO `question_paper` VALUES (3, 7, 5);
INSERT INTO `question_paper` VALUES (3, 12, 7);
INSERT INTO `question_paper` VALUES (3, 16, 8);
INSERT INTO `question_paper` VALUES (4, 19, 4);
INSERT INTO `question_paper` VALUES (4, 24, 6);
INSERT INTO `question_paper` VALUES (4, 12, 7);
INSERT INTO `question_paper` VALUES (4, 21, 9);
INSERT INTO `question_paper` VALUES (4, 18, 10);
INSERT INTO `question_paper` VALUES (5, 13, 10);
INSERT INTO `question_paper` VALUES (6, 11, 5);
INSERT INTO `question_paper` VALUES (6, 12, 6);
INSERT INTO `question_paper` VALUES (6, 21, 7);
INSERT INTO `question_paper` VALUES (6, 22, 9);
INSERT INTO `question_paper` VALUES (7, 7, 4);
INSERT INTO `question_paper` VALUES (7, 15, 5);
INSERT INTO `question_paper` VALUES (7, 8, 6);
INSERT INTO `question_paper` VALUES (7, 17, 9);
INSERT INTO `question_paper` VALUES (8, 11, 5);
INSERT INTO `question_paper` VALUES (8, 12, 6);
INSERT INTO `question_paper` VALUES (8, 25, 7);
INSERT INTO `question_paper` VALUES (8, 18, 9);
INSERT INTO `question_paper` VALUES (9, 24, 5);
INSERT INTO `question_paper` VALUES (9, 9, 9);
INSERT INTO `question_paper` VALUES (9, 18, 10);
INSERT INTO `question_paper` VALUES (10, 12, 6);
INSERT INTO `question_paper` VALUES (10, 17, 7);
INSERT INTO `question_paper` VALUES (11, 9, 7);
INSERT INTO `question_paper` VALUES (12, 8, 4);
INSERT INTO `question_paper` VALUES (12, 12, 5);
INSERT INTO `question_paper` VALUES (12, 9, 8);
INSERT INTO `question_paper` VALUES (12, 14, 10);
INSERT INTO `question_paper` VALUES (13, 21, 7);
INSERT INTO `question_paper` VALUES (13, 14, 9);
INSERT INTO `question_paper` VALUES (13, 23, 10);
INSERT INTO `question_paper` VALUES (14, 8, 4);
INSERT INTO `question_paper` VALUES (14, 25, 5);
INSERT INTO `question_paper` VALUES (14, 22, 8);
INSERT INTO `question_paper` VALUES (15, 24, 4);
INSERT INTO `question_paper` VALUES (15, 12, 5);
INSERT INTO `question_paper` VALUES (15, 13, 6);
INSERT INTO `question_paper` VALUES (15, 23, 10);
INSERT INTO `question_paper` VALUES (21, 13, 5);
INSERT INTO `question_paper` VALUES (21, 18, 6);
INSERT INTO `question_paper` VALUES (21, 18, 7);
INSERT INTO `question_paper` VALUES (21, 19, 8);
INSERT INTO `question_paper` VALUES (21, 15, 9);
INSERT INTO `question_paper` VALUES (22, 22, 6);
INSERT INTO `question_paper` VALUES (22, 23, 9);
INSERT INTO `question_paper` VALUES (22, 7, 10);
INSERT INTO `question_paper` VALUES (23, 5, 4);
INSERT INTO `question_paper` VALUES (23, 7, 9);
INSERT INTO `question_paper` VALUES (23, 15, 10);
INSERT INTO `question_paper` VALUES (24, 5, 4);
INSERT INTO `question_paper` VALUES (24, 22, 5);
INSERT INTO `question_paper` VALUES (24, 10, 6);
INSERT INTO `question_paper` VALUES (25, 5, 5);
INSERT INTO `question_paper` VALUES (25, 6, 7);
INSERT INTO `question_paper` VALUES (25, 24, 9);
INSERT INTO `question_paper` VALUES (25, 12, 10);
INSERT INTO `question_paper` VALUES (26, 7, 8);
INSERT INTO `question_paper` VALUES (26, 16, 10);
INSERT INTO `question_paper` VALUES (27, 10, 5);
INSERT INTO `question_paper` VALUES (28, 18, 4);
INSERT INTO `question_paper` VALUES (28, 7, 8);
INSERT INTO `question_paper` VALUES (29, 23, 5);
INSERT INTO `question_paper` VALUES (29, 24, 7);
INSERT INTO `question_paper` VALUES (30, 19, 6);
INSERT INTO `question_paper` VALUES (30, 19, 7);
INSERT INTO `question_paper` VALUES (30, 16, 8);
INSERT INTO `question_paper` VALUES (30, 16, 9);
INSERT INTO `question_paper` VALUES (36, 7, 5);
INSERT INTO `question_paper` VALUES (36, 24, 6);
INSERT INTO `question_paper` VALUES (36, 12, 9);
INSERT INTO `question_paper` VALUES (36, 21, 10);
INSERT INTO `question_paper` VALUES (37, 8, 9);
INSERT INTO `question_paper` VALUES (37, 5, 10);
INSERT INTO `question_paper` VALUES (38, 12, 7);
INSERT INTO `question_paper` VALUES (38, 21, 9);
INSERT INTO `question_paper` VALUES (39, 19, 4);
INSERT INTO `question_paper` VALUES (39, 25, 7);
INSERT INTO `question_paper` VALUES (39, 21, 9);
INSERT INTO `question_paper` VALUES (39, 18, 10);
INSERT INTO `question_paper` VALUES (40, 7, 4);
INSERT INTO `question_paper` VALUES (40, 12, 6);
INSERT INTO `question_paper` VALUES (40, 12, 7);
INSERT INTO `question_paper` VALUES (40, 5, 10);
INSERT INTO `question_paper` VALUES (41, 19, 4);
INSERT INTO `question_paper` VALUES (41, 17, 7);
INSERT INTO `question_paper` VALUES (41, 13, 10);
INSERT INTO `question_paper` VALUES (42, 16, 6);
INSERT INTO `question_paper` VALUES (42, 5, 9);
INSERT INTO `question_paper` VALUES (43, 7, 5);
INSERT INTO `question_paper` VALUES (43, 13, 9);
INSERT INTO `question_paper` VALUES (43, 22, 10);
INSERT INTO `question_paper` VALUES (44, 8, 6);
INSERT INTO `question_paper` VALUES (44, 17, 7);
INSERT INTO `question_paper` VALUES (44, 14, 9);
INSERT INTO `question_paper` VALUES (44, 18, 10);
INSERT INTO `question_paper` VALUES (45, 16, 4);
INSERT INTO `question_paper` VALUES (45, 12, 5);
INSERT INTO `question_paper` VALUES (51, 17, 5);
INSERT INTO `question_paper` VALUES (51, 22, 7);
INSERT INTO `question_paper` VALUES (51, 6, 8);
INSERT INTO `question_paper` VALUES (51, 15, 9);
INSERT INTO `question_paper` VALUES (52, 5, 5);
INSERT INTO `question_paper` VALUES (52, 5, 7);
INSERT INTO `question_paper` VALUES (52, 14, 8);
INSERT INTO `question_paper` VALUES (52, 23, 9);
INSERT INTO `question_paper` VALUES (52, 7, 10);
INSERT INTO `question_paper` VALUES (53, 9, 7);
INSERT INTO `question_paper` VALUES (53, 23, 8);
INSERT INTO `question_paper` VALUES (54, 5, 5);
INSERT INTO `question_paper` VALUES (54, 14, 6);
INSERT INTO `question_paper` VALUES (54, 14, 8);
INSERT INTO `question_paper` VALUES (55, 9, 4);
INSERT INTO `question_paper` VALUES (55, 22, 5);
INSERT INTO `question_paper` VALUES (55, 7, 10);
INSERT INTO `question_paper` VALUES (56, 23, 8);
INSERT INTO `question_paper` VALUES (56, 7, 10);
INSERT INTO `question_paper` VALUES (57, 7, 9);
INSERT INTO `question_paper` VALUES (58, 9, 4);
INSERT INTO `question_paper` VALUES (58, 9, 5);
INSERT INTO `question_paper` VALUES (58, 19, 9);
INSERT INTO `question_paper` VALUES (58, 7, 10);
INSERT INTO `question_paper` VALUES (59, 9, 4);
INSERT INTO `question_paper` VALUES (59, 18, 5);
INSERT INTO `question_paper` VALUES (59, 24, 9);
INSERT INTO `question_paper` VALUES (60, 9, 4);
INSERT INTO `question_paper` VALUES (60, 19, 7);
INSERT INTO `question_paper` VALUES (60, 15, 8);
INSERT INTO `question_paper` VALUES (60, 16, 9);
INSERT INTO `question_paper` VALUES (60, 12, 10);
INSERT INTO `question_paper` VALUES (66, 7, 7);
INSERT INTO `question_paper` VALUES (66, 25, 9);
INSERT INTO `question_paper` VALUES (67, 23, 5);
INSERT INTO `question_paper` VALUES (67, 19, 6);
INSERT INTO `question_paper` VALUES (67, 24, 7);
INSERT INTO `question_paper` VALUES (68, 11, 6);
INSERT INTO `question_paper` VALUES (68, 16, 8);
INSERT INTO `question_paper` VALUES (68, 9, 10);
INSERT INTO `question_paper` VALUES (69, 6, 4);
INSERT INTO `question_paper` VALUES (69, 24, 6);
INSERT INTO `question_paper` VALUES (69, 20, 8);
INSERT INTO `question_paper` VALUES (69, 25, 10);
INSERT INTO `question_paper` VALUES (70, 19, 5);
INSERT INTO `question_paper` VALUES (70, 24, 6);
INSERT INTO `question_paper` VALUES (70, 8, 9);
INSERT INTO `question_paper` VALUES (70, 5, 10);
INSERT INTO `question_paper` VALUES (71, 12, 7);
INSERT INTO `question_paper` VALUES (71, 8, 8);
INSERT INTO `question_paper` VALUES (72, 20, 6);
INSERT INTO `question_paper` VALUES (72, 21, 7);
INSERT INTO `question_paper` VALUES (73, 11, 5);
INSERT INTO `question_paper` VALUES (73, 17, 9);
INSERT INTO `question_paper` VALUES (74, 20, 4);
INSERT INTO `question_paper` VALUES (74, 17, 7);
INSERT INTO `question_paper` VALUES (74, 17, 8);
INSERT INTO `question_paper` VALUES (74, 13, 9);
INSERT INTO `question_paper` VALUES (74, 22, 10);
INSERT INTO `question_paper` VALUES (75, 15, 4);
INSERT INTO `question_paper` VALUES (75, 12, 7);
INSERT INTO `question_paper` VALUES (75, 13, 8);
INSERT INTO `question_paper` VALUES (75, 18, 9);
INSERT INTO `question_paper` VALUES (75, 22, 10);

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `exam_id` bigint(0) NOT NULL COMMENT '测评id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `score` double(255, 2) NULL DEFAULT NULL COMMENT '成绩',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `finish_time` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for submit
-- ----------------------------
DROP TABLE IF EXISTS `submit`;
CREATE TABLE `submit`  (
  `exam_id` bigint(0) NOT NULL COMMENT '测评id',
  `paper_id` bigint(0) NULL DEFAULT NULL COMMENT '试卷id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `question_id` bigint(0) NULL DEFAULT NULL COMMENT '问题id',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户选项',
  `submit_time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `status` int(0) NULL DEFAULT 0 COMMENT '封禁状态 1封禁 0正常',
  `teacher_access` int(0) NULL DEFAULT 0 COMMENT '是否有教师权限 1有 0无',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像链接',
  `gender` int(0) NULL DEFAULT NULL COMMENT '性别',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'yruns', 'root', 0, 0, 'yruns', NULL, 0, '2023-06-11 22:50:26');
INSERT INTO `user` VALUES (2, 'object', 'root', 0, 1, 'kka', NULL, 1, '2023-06-20 01:19:15');

SET FOREIGN_KEY_CHECKS = 1;
