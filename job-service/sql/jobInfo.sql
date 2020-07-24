/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : jobInfo

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 24/07/2020 17:40:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for project_auto_report
-- ----------------------------
DROP TABLE IF EXISTS `project_auto_report`;
CREATE TABLE `project_auto_report` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `auto_report_title` varchar(255) DEFAULT NULL COMMENT '标题',
  `auto_report_to` varchar(255) DEFAULT NULL COMMENT '收件人',
  `auto_report_to_other` varchar(1024) DEFAULT NULL COMMENT '收件人',
  `auto_report_cc` varchar(255) DEFAULT NULL COMMENT '抄送人',
  `auto_report_cc_other` varchar(1024) DEFAULT NULL COMMENT '抄送人',
  `auto_report_template` int NOT NULL COMMENT '邮件模板 1公司级模板 2bu级模板 3项目周报模板 4风险报告模板 5聚合风险报告模板',
  `auto_report_data` varchar(1024) NOT NULL COMMENT '数据范围',
  `auto_report_cron` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `auto_report_creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `auto_report_status` int NOT NULL COMMENT '运行状态 1运行中 2已暂停 3已停止',
  `auto_report_last_execute_time` datetime DEFAULT NULL COMMENT '最后一次执行时间',
  `is_del` tinyint DEFAULT '0' COMMENT '标记删除',
  `created_stime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_stime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `auto_report_from` varchar(255) NOT NULL COMMENT '发件人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='项目自动化报告任务表';

-- ----------------------------
-- Records of project_auto_report
-- ----------------------------
BEGIN;
INSERT INTO `project_auto_report` VALUES (1, '测试', 'zhengweishan@autohome.com.cn', 'zhengweishan@autohome.com.cn', 'zhengweishan@autohome.com.cn', 'zhengweishan@autohome.com.cn', 1, 'wwww', '0 0/5 * * * ?', 'zhengweishan', 1, '2020-07-24 09:40:00', 0, '2020-07-24 13:52:53', '2020-07-24 17:40:00', 'zhengweishan@autohome.com.cn');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
