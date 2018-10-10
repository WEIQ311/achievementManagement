/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : achievement

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-10-10 15:46:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for subject_info
-- ----------------------------
DROP TABLE IF EXISTS `subject_info`;
CREATE TABLE `subject_info` (
  `subject_id` varchar(255) NOT NULL,
  `subject_name` varchar(300) DEFAULT NULL COMMENT '科目名称',
  `subject_type` int(11) DEFAULT '1' COMMENT '学科类型：0:通用1:文科，2:理科，3:艺术',
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT '1',
  `insert_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`subject_id`),
  UNIQUE KEY `UK_41vmh8wx6jyiogxc3k9motpur` (`subject_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='学科';

-- ----------------------------
-- Records of subject_info
-- ----------------------------
INSERT INTO `subject_info` VALUES ('1', '语文', '0', null, '1', '2018-10-03 11:41:46', '2018-10-03 11:47:47');
INSERT INTO `subject_info` VALUES ('2', '数学', '0', null, '1', '2018-10-03 11:41:46', '2018-10-04 10:24:05');
INSERT INTO `subject_info` VALUES ('3', '英语', '0', null, '1', '2018-10-03 11:41:46', '2018-10-03 11:47:47');
INSERT INTO `subject_info` VALUES ('4', '物理', '2', null, '1', '2018-10-03 16:42:33', '2018-10-04 10:24:05');
INSERT INTO `subject_info` VALUES ('5', '历史', '1', null, '1', '2018-10-10 14:36:25', '2018-10-10 14:36:29');
INSERT INTO `subject_info` VALUES ('6', '地理', '1', null, '1', '2018-10-10 14:36:37', '2018-10-10 14:36:40');
INSERT INTO `subject_info` VALUES ('7', '生物', '2', null, '1', '2018-10-10 14:36:50', '2018-10-10 14:36:52');
INSERT INTO `subject_info` VALUES ('8', '化学', '2', null, '1', '2018-10-10 14:37:02', '2018-10-10 14:37:15');
INSERT INTO `subject_info` VALUES ('9', '政治', '1', null, '1', '2018-10-10 14:37:22', '2018-10-10 14:37:24');
INSERT INTO `subject_info` VALUES ('10', '计算机', '0', null, '1', '2018-10-10 14:37:32', '2018-10-10 14:37:39');
INSERT INTO `subject_info` VALUES ('11', '体育', '0', null, '1', '2018-10-10 14:37:42', '2018-10-10 14:37:48');
INSERT INTO `subject_info` VALUES ('12', '美术', '3', null, '1', '2018-10-10 14:37:52', '2018-10-10 14:37:58');
INSERT INTO `subject_info` VALUES ('13', '音乐', '3', null, '1', '2018-10-10 14:38:02', '2018-10-10 14:38:05');
SET FOREIGN_KEY_CHECKS=1;
