/*
Navicat MySQL Data Transfer

Source Server         :
Source Server Version : 50544
Source Host           :
Source Database       : classspirit

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2019-01-10 10:17:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) NOT NULL,
  `activity_image` varchar(255) NOT NULL,
  `activity_content` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `class_id` varchar(255) NOT NULL,
  `pub_time` varchar(255) NOT NULL,
  `start_time` varchar(255) NOT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `apply_id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `punch_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`apply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `class_id` varchar(255) NOT NULL,
  `class_name` varchar(255) NOT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` varchar(255) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  `student_image` varchar(255) DEFAULT NULL,
  `student_class` varchar(255) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `user_phone` varchar(20) DEFAULT NULL,
  `user_image` varchar(255) DEFAULT NULL,
  `user_type` varchar(2) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `class_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
