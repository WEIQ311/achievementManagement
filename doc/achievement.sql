/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : achievement

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-10-11 10:49:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `class_id` varchar(255) NOT NULL,
  `grade_id` varchar(255) DEFAULT NULL,
  `class_name` varchar(4000) DEFAULT NULL COMMENT '班级名称',
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `class_type` varchar(255) DEFAULT NULL COMMENT '班级类型-文科班,理科班,艺术班',
  `status` int(10) DEFAULT '1',
  `insert_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`class_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='班级';

-- ----------------------------
-- Table structure for conf_student_parent
-- ----------------------------
DROP TABLE IF EXISTS `conf_student_parent`;
CREATE TABLE `conf_student_parent` (
  `conf_id` varchar(255) NOT NULL COMMENT '主键',
  `student_id` varchar(255) NOT NULL COMMENT '学生ID',
  `parent_id` varchar(255) NOT NULL COMMENT '家长ID',
  `connection_type` varchar(255) DEFAULT NULL COMMENT '关系类型',
  PRIMARY KEY (`conf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for conf_teacher_class
-- ----------------------------
DROP TABLE IF EXISTS `conf_teacher_class`;
CREATE TABLE `conf_teacher_class` (
  `conf_id` varchar(255) NOT NULL COMMENT '主键',
  `teacher_id` varchar(255) NOT NULL COMMENT '教师ID',
  `class_id` varchar(255) NOT NULL COMMENT '班级ID',
  `teacher_duty` varchar(255) NOT NULL DEFAULT '代课教师' COMMENT '教师职责',
  `subject_id` varchar(255) NOT NULL DEFAULT '1' COMMENT '学科ID',
  PRIMARY KEY (`conf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for grade_info
-- ----------------------------
DROP TABLE IF EXISTS `grade_info`;
CREATE TABLE `grade_info` (
  `grade_id` varchar(255) NOT NULL,
  `grade_name` varchar(500) DEFAULT NULL COMMENT '年级',
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `insert_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`grade_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='年级';

-- ----------------------------
-- Table structure for parent_info
-- ----------------------------
DROP TABLE IF EXISTS `parent_info`;
CREATE TABLE `parent_info` (
  `parent_id` varchar(255) NOT NULL,
  `parent_name` varchar(4000) DEFAULT NULL COMMENT '家长名称',
  `tel_phone` varchar(12) DEFAULT NULL COMMENT '电话号码',
  `tx_qq` varchar(255) DEFAULT NULL,
  `tx_wx` varchar(255) DEFAULT NULL,
  `xl_wb` varchar(255) DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `insert_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='家长';

-- ----------------------------
-- Table structure for school_info
-- ----------------------------
DROP TABLE IF EXISTS `school_info`;
CREATE TABLE `school_info` (
  `sc_id` varchar(255) NOT NULL,
  `name` varchar(300) DEFAULT NULL COMMENT '学校名称',
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `insert_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sc_id`),
  UNIQUE KEY `UK_iqoncs1nrvmah9d1cirsec0u1` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='学校';

-- ----------------------------
-- Table structure for score_info
-- ----------------------------
DROP TABLE IF EXISTS `score_info`;
CREATE TABLE `score_info` (
  `score_id` varchar(255) NOT NULL,
  `semester_id` varchar(255) NOT NULL,
  `class_id` varchar(255) NOT NULL,
  `subject_id` varchar(255) NOT NULL,
  `teacher_id` varchar(255) NOT NULL,
  `student_id` varchar(255) NOT NULL,
  `score_number` double(16,2) NOT NULL DEFAULT '0.00' COMMENT '成绩',
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `insert_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`score_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='成绩';

-- ----------------------------
-- Table structure for semester_info
-- ----------------------------
DROP TABLE IF EXISTS `semester_info`;
CREATE TABLE `semester_info` (
  `semester_id` varchar(255) NOT NULL,
  `year_id` varchar(255) DEFAULT NULL,
  `semester_name` varchar(4000) DEFAULT NULL COMMENT '学期名称',
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `exam_time` datetime DEFAULT NULL COMMENT '考试时间',
  `score_begin_deadline` datetime DEFAULT NULL COMMENT '更新开始日期不能为空',
  `score_end_deadline` datetime DEFAULT NULL COMMENT '更新成绩截至日期',
  `insert_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`semester_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='学期';

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info` (
  `student_id` varchar(255) NOT NULL,
  `class_id` varchar(255) NOT NULL,
  `student_num` varchar(255) NOT NULL COMMENT '学号',
  `student_name` varchar(500) DEFAULT NULL COMMENT '学生姓名',
  `phone_url` varchar(255) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `tel_phone` varchar(12) DEFAULT NULL COMMENT '电话号码',
  `tx_qq` varchar(255) DEFAULT NULL,
  `tx_wx` varchar(255) DEFAULT NULL,
  `xl_wb` varchar(255) DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `insert_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `studentNum` (`student_num`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='学生';

-- ----------------------------
-- Table structure for subject_info
-- ----------------------------
DROP TABLE IF EXISTS `subject_info`;
CREATE TABLE `subject_info` (
  `subject_id` varchar(255) NOT NULL,
  `subject_name` varchar(300) DEFAULT NULL COMMENT '科目名称',
  `subject_type` int(11) DEFAULT '0' COMMENT '学科类型：0:通用1:文科，2:理科，3:艺术',
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `insert_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`subject_id`),
  UNIQUE KEY `UK_41vmh8wx6jyiogxc3k9motpur` (`subject_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='学科';

-- ----------------------------
-- Table structure for subject_score_info
-- ----------------------------
DROP TABLE IF EXISTS `subject_score_info`;
CREATE TABLE `subject_score_info` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `year_id` varchar(255) NOT NULL COMMENT '学年ID',
  `semester_id` varchar(255) NOT NULL COMMENT '学期ID',
  `grade_id` varchar(255) NOT NULL COMMENT '年级ID',
  `class_id` varchar(255) NOT NULL COMMENT '班级ID',
  `class_type` varchar(255) NOT NULL COMMENT '班级类型',
  `student_id` varchar(255) NOT NULL COMMENT '学生ID',
  `teacher_id` varchar(255) DEFAULT NULL COMMENT '教师ID',
  `sub_language` double(16,2) DEFAULT NULL COMMENT '语文成绩',
  `sub_mathematics` double(16,2) DEFAULT NULL COMMENT '数学成绩',
  `sub_english` double(16,2) DEFAULT NULL COMMENT '英语成绩',
  `sub_history` double(16,2) DEFAULT NULL COMMENT '历史成绩',
  `sub_geography` double(16,2) DEFAULT NULL COMMENT '地理成绩',
  `sub_biological` double(16,2) DEFAULT NULL COMMENT '生物成绩',
  `sub_chemistry` double(16,2) DEFAULT NULL COMMENT '化学成绩',
  `sub_physical` double(16,2) DEFAULT NULL COMMENT '物理成绩',
  `sub_political` double(16,2) DEFAULT NULL COMMENT '政治成绩',
  `sub_computer` double(16,2) DEFAULT NULL COMMENT '计算机成绩',
  `sub_sports` double(16,2) DEFAULT NULL COMMENT '体育成绩',
  `sub_art` double(16,2) DEFAULT NULL COMMENT '美术成绩',
  `sub_music` double(16,2) DEFAULT NULL COMMENT '音乐成绩',
  `insert_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sub_score_sum` double DEFAULT '0' COMMENT '总成绩',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `teacher_info`;
CREATE TABLE `teacher_info` (
  `teacher_id` varchar(255) NOT NULL,
  `teacher_name` varchar(4000) DEFAULT NULL COMMENT '教师名称',
  `teacher_num` varchar(255) NOT NULL COMMENT '教师编号',
  `subject_id` varchar(255) DEFAULT NULL COMMENT '学科ID',
  `phone_url` varchar(255) DEFAULT NULL,
  `teacher_duty` varchar(255) DEFAULT '2' COMMENT '教师职责',
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `tel_phone` varchar(12) DEFAULT NULL COMMENT '电话号码',
  `tx_qq` varchar(255) DEFAULT NULL,
  `tx_wx` varchar(255) DEFAULT NULL,
  `xl_wb` varchar(255) DEFAULT NULL,
  `status` int(10) DEFAULT '1',
  `insert_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`teacher_id`),
  UNIQUE KEY `uq_teacher_num` (`teacher_num`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='教师';

-- ----------------------------
-- Table structure for year_info
-- ----------------------------
DROP TABLE IF EXISTS `year_info`;
CREATE TABLE `year_info` (
  `year_id` varchar(255) NOT NULL,
  `year_name` varchar(4000) DEFAULT NULL COMMENT '学年名称',
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `insert_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`year_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='学年';
