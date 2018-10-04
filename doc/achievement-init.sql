/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : achievement

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-10-03 11:03:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `class_id` varchar(255) NOT NULL,
  `class_name` varchar(4000) DEFAULT NULL COMMENT '班级名称',
  `grade_id` varchar(255) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`class_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='班级';

-- ----------------------------
-- Records of class_info
-- ----------------------------

-- ----------------------------
-- Table structure for grade_info
-- ----------------------------
DROP TABLE IF EXISTS `grade_info`;
CREATE TABLE `grade_info` (
  `grade_id` varchar(255) NOT NULL,
  `grade_name` varchar(500) DEFAULT NULL COMMENT '年级',
  `insert_time` datetime DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`grade_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='年级';

-- ----------------------------
-- Records of grade_info
-- ----------------------------

-- ----------------------------
-- Table structure for parent_info
-- ----------------------------
DROP TABLE IF EXISTS `parent_info`;
CREATE TABLE `parent_info` (
  `parent_id` varchar(255) NOT NULL,
  `insert_time` datetime DEFAULT NULL,
  `parent_name` varchar(4000) DEFAULT NULL COMMENT '家长名称',
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `tel_phone` varchar(12) DEFAULT NULL COMMENT '电话号码',
  `tx_qq` varchar(255) DEFAULT NULL,
  `tx_wx` varchar(255) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `xl_wb` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='家长';

-- ----------------------------
-- Records of parent_info
-- ----------------------------

-- ----------------------------
-- Table structure for school_info
-- ----------------------------
DROP TABLE IF EXISTS `school_info`;
CREATE TABLE `school_info` (
  `sc_id` varchar(255) NOT NULL,
  `insert_time` datetime DEFAULT NULL,
  `name` varchar(300) DEFAULT NULL COMMENT '学校名称',
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sc_id`),
  UNIQUE KEY `UK_iqoncs1nrvmah9d1cirsec0u1` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='学校';

-- ----------------------------
-- Records of school_info
-- ----------------------------

-- ----------------------------
-- Table structure for score_info
-- ----------------------------
DROP TABLE IF EXISTS `score_info`;
CREATE TABLE `score_info` (
  `score_id` varchar(255) NOT NULL,
  `class_id` varchar(255) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `score_number` varchar(300) DEFAULT '0' COMMENT '成绩',
  `semester_id` varchar(255) DEFAULT NULL,
  `status` int(10) DEFAULT '1',
  `student_id` varchar(255) DEFAULT NULL,
  `subject_id` varchar(255) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`score_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='成绩';

-- ----------------------------
-- Records of score_info
-- ----------------------------

-- ----------------------------
-- Table structure for semester_info
-- ----------------------------
DROP TABLE IF EXISTS `semester_info`;
CREATE TABLE `semester_info` (
  `semester_id` varchar(255) NOT NULL,
  `insert_time` datetime DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `semester_name` varchar(4000) DEFAULT NULL COMMENT '学期名称',
  `status` int(10) DEFAULT '1',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `year_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`semester_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='学期';

-- ----------------------------
-- Records of semester_info
-- ----------------------------

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info` (
  `student_id` varchar(255) NOT NULL,
  `insert_time` datetime DEFAULT NULL,
  `phone_url` varchar(255) DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `status` int(10) DEFAULT '1',
  `student_name` varchar(500) DEFAULT NULL COMMENT '学生姓名',
  `tel_phone` varchar(12) DEFAULT NULL COMMENT '电话号码',
  `tx_qq` varchar(255) DEFAULT NULL,
  `tx_wx` varchar(255) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `xl_wb` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='学生';

-- ----------------------------
-- Records of student_info
-- ----------------------------

-- ----------------------------
-- Table structure for subject_info
-- ----------------------------
DROP TABLE IF EXISTS `subject_info`;
CREATE TABLE `subject_info` (
  `subject_id` varchar(255) NOT NULL,
  `insert_time` datetime DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `subject_name` varchar(300) DEFAULT NULL COMMENT '科目名称',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`subject_id`),
  UNIQUE KEY `UK_41vmh8wx6jyiogxc3k9motpur` (`subject_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='学科';

-- ----------------------------
-- Records of subject_info
-- ----------------------------

-- ----------------------------
-- Table structure for teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `teacher_info`;
CREATE TABLE `teacher_info` (
  `teacher_id` varchar(255) NOT NULL,
  `insert_time` datetime DEFAULT NULL,
  `phone_url` varchar(255) DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `status` int(10) DEFAULT '1',
  `teacher_name` varchar(4000) DEFAULT NULL COMMENT '教师名称',
  `tel_phone` varchar(12) DEFAULT NULL COMMENT '电话号码',
  `tx_qq` varchar(255) DEFAULT NULL,
  `tx_wx` varchar(255) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `xl_wb` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='教师';

-- ----------------------------
-- Records of teacher_info
-- ----------------------------

-- ----------------------------
-- Table structure for year_info
-- ----------------------------
DROP TABLE IF EXISTS `year_info`;
CREATE TABLE `year_info` (
  `year_id` varchar(255) NOT NULL,
  `insert_time` datetime DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `semester_name` varchar(4000) DEFAULT NULL COMMENT '学年名称',
  `status` int(10) DEFAULT '1',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`year_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='学年';

-- ----------------------------
-- Records of year_info
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
