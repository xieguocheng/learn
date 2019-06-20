/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : learn

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-05-29 21:11:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for announce
-- ----------------------------
DROP TABLE IF EXISTS `announce`;
CREATE TABLE `announce` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `start_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `cancel` int(11) DEFAULT '1' COMMENT '管理员是否撤销0-撤销发布 1-已经发布',
  `cancel_time` datetime DEFAULT NULL COMMENT '撤销时间',
  `remove` int(11) DEFAULT '1' COMMENT '用户是否删除0-逻辑删除 1-正常接收',
  `remove_time` datetime DEFAULT NULL COMMENT '用户删除时间',
  `priority` int(11) DEFAULT NULL COMMENT '优先级高的信息放前面',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户id',
  `see` int(11) DEFAULT '0' COMMENT '用户是否阅读过了0-未阅读 1-已经阅读',
  `see_time` datetime DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` varchar(64) NOT NULL COMMENT '课程ID',
  `user_id` varchar(64) NOT NULL COMMENT '任课老师ID',
  `course_name` varchar(64) NOT NULL COMMENT '课程名称',
  `classnum` varchar(64) NOT NULL COMMENT '每门课程对应一个编号',
  `added` int(11) DEFAULT '1' COMMENT '允许加入0-不允许 1-允许',
  `introduction` longtext COMMENT '课程简介',
  `course_type` int(11) DEFAULT NULL COMMENT '课程类型(学科分类)',
  `course_img` varchar(256) DEFAULT NULL COMMENT '课程封面',
  `request` varchar(64) DEFAULT NULL COMMENT '学习要求',
  `classname` varchar(64) DEFAULT NULL COMMENT '课程所在班级名称（由老师描述）',
  `semeter` varchar(64) DEFAULT NULL COMMENT '所属学期',
  `examin` varchar(64) DEFAULT NULL COMMENT '考试安排',
  `question_list_id` varchar(256) DEFAULT NULL COMMENT '每个习题编号中间以","分隔',
  `speed` varchar(64) DEFAULT NULL COMMENT '课程进度',
  `sign` varchar(256) DEFAULT NULL COMMENT '签到情况',
  `people` int(11) DEFAULT '0' COMMENT '每门课程对应的班级人数',
  `status` int(11) DEFAULT '1' COMMENT '0为逻辑删除，1为未删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `AK_Key_2` (`classnum`),
  KEY `FK_Reference_6` (`course_type`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`course_type`) REFERENCES `course_type` (`course_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course_experiment
-- ----------------------------
DROP TABLE IF EXISTS `course_experiment`;
CREATE TABLE `course_experiment` (
  `cour_experiment_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` varchar(64) DEFAULT NULL COMMENT '课程id',
  `title` varchar(64) DEFAULT NULL COMMENT '实验标题',
  `detail` varchar(256) DEFAULT NULL COMMENT '任务详情描述',
  `type` int(11) DEFAULT NULL COMMENT '资源类型0-doc 1-ppt 2-png',
  `cour_url` longtext COMMENT '实验资源url（存放在七牛云）',
  `score` double DEFAULT NULL COMMENT '实验设置分值（教师设置）',
  `over_submit` int(11) DEFAULT '1' COMMENT '允许超时间提交0-不允许 1-允许',
  `status` int(11) DEFAULT '1' COMMENT '状态0-已超时，1-进行中',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `end_time` datetime DEFAULT NULL COMMENT '截至时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`cour_experiment_id`),
  KEY `FK_Reference_3` (`course_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course_task
-- ----------------------------
DROP TABLE IF EXISTS `course_task`;
CREATE TABLE `course_task` (
  `course_task_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'cour_task_id',
  `course_id` varchar(64) DEFAULT NULL COMMENT 'courseid',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `question_list_id` longtext COMMENT '题目列表',
  `total_grade` double DEFAULT NULL COMMENT '总分',
  `status` int(11) DEFAULT NULL COMMENT '0-未发布1-发布2-截至',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `end_time` datetime DEFAULT NULL COMMENT '截至时间',
  PRIMARY KEY (`course_task_id`),
  KEY `FK_Reference_9` (`course_id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course_type
-- ----------------------------
DROP TABLE IF EXISTS `course_type`;
CREATE TABLE `course_type` (
  `course_type` int(11) NOT NULL COMMENT '课程分类',
  `name` varchar(64) DEFAULT NULL COMMENT '类名',
  PRIMARY KEY (`course_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_desc` longtext,
  `choice` longtext,
  `question_type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `question_source` varchar(255) DEFAULT NULL,
  `question_list_id` int(11) DEFAULT NULL,
  `question_grade` int(11) DEFAULT NULL,
  `answer` varchar(1024) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '课程类型，相当于学科分类',
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=702 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(16) NOT NULL,
  `place` char(8) NOT NULL,
  `type` char(8) NOT NULL,
  `properties` char(8) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2577 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `address` varchar(255) DEFAULT NULL COMMENT '获取主机登录地址',
  `nickname` varchar(255) DEFAULT NULL COMMENT '别名',
  `operate_time` varchar(255) DEFAULT NULL COMMENT '操作时间',
  `status` int(11) DEFAULT NULL COMMENT '状态1：在线',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '用户ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `telephone` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `password` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `number` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '用于绑定学号',
  `nickname` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '昵称',
  `birthday` datetime DEFAULT NULL COMMENT '出生年月',
  `sex` int(11) DEFAULT '0' COMMENT '0为男，1为女,默认为0',
  `school_id` int(11) DEFAULT NULL COMMENT '所在学校id',
  `dept` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '所在院系名称',
  `identity` int(11) DEFAULT '0' COMMENT '0为学生，1为老师，2为其他，默认为0',
  `user_img` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '头像的url',
  `email` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `delete_status` int(11) DEFAULT '1' COMMENT '0-逻辑删除，1-未删除，默认为1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `AK_Key_2` (`telephone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_course
-- ----------------------------
DROP TABLE IF EXISTS `user_course`;
CREATE TABLE `user_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` varchar(64) NOT NULL COMMENT '用户id',
  `course_id` varchar(64) NOT NULL COMMENT '课程id',
  `answer` varchar(5000) DEFAULT NULL COMMENT '答案（用-连接）',
  `grade` double DEFAULT NULL COMMENT '做题分数（自动生成）',
  `comment` varchar(256) DEFAULT NULL COMMENT '教师评语',
  `sign` varchar(256) DEFAULT NULL COMMENT '签到记录',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`user_id`),
  KEY `FK_Reference_2` (`course_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_experiment
-- ----------------------------
DROP TABLE IF EXISTS `user_experiment`;
CREATE TABLE `user_experiment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户id',
  `cour_experiment_id` int(64) DEFAULT NULL COMMENT '对应的实验题目',
  `desr` varchar(64) DEFAULT NULL COMMENT '实验描述',
  `score` double DEFAULT NULL COMMENT '分数',
  `type` int(11) DEFAULT NULL COMMENT '资源类型0-doc 1-ppt 2-png',
  `user_url` longtext COMMENT '上交实验文档url',
  `comment` varchar(256) DEFAULT NULL COMMENT '教师评语',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `up_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '实验上交时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(11) DEFAULT '0' COMMENT '0-未提交 1-已经提交',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_4` (`user_id`),
  KEY `FK_Reference_5` (`cour_experiment_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`cour_experiment_id`) REFERENCES `course_experiment` (`cour_experiment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_super
-- ----------------------------
DROP TABLE IF EXISTS `user_super`;
CREATE TABLE `user_super` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '超级管理员id',
  `username` varchar(64) DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(64) DEFAULT NULL COMMENT '别名',
  `sex` int(11) DEFAULT NULL COMMENT '性别"1"男"2"女',
  `telephone` varchar(64) DEFAULT NULL COMMENT '电话',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `user_intro` varchar(255) DEFAULT NULL COMMENT '简介',
  `user_img` varchar(255) DEFAULT NULL COMMENT '图片',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用户账号创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `login_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `status` int(11) DEFAULT NULL COMMENT '状态0-已经停用 1-正常管理员 2-最超级管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_task
-- ----------------------------
DROP TABLE IF EXISTS `user_task`;
CREATE TABLE `user_task` (
  `user_task_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) DEFAULT NULL COMMENT 'userid',
  `course_task_id` int(11) DEFAULT NULL COMMENT 'coursetaskid',
  `answer` varchar(5000) DEFAULT NULL COMMENT '答案',
  `status` varchar(64) DEFAULT '0' COMMENT '0-未交 1-提交了',
  `grade` double DEFAULT NULL COMMENT '得分',
  `answer_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '答题时间',
  PRIMARY KEY (`user_task_id`),
  KEY `FK_Reference_7` (`user_id`),
  KEY `FK_Reference_8` (`course_task_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`course_task_id`) REFERENCES `course_task` (`course_task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for we_user
-- ----------------------------
DROP TABLE IF EXISTS `we_user`;
CREATE TABLE `we_user` (
  `user_id` varchar(64) NOT NULL,
  `open_id` varchar(255) NOT NULL,
  `session_key` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
