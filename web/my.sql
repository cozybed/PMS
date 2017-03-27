/*
SQLyog Enterprise - MySQL GUI v8.14 
MySQL - 5.0.22-community-nt : Database - pms_db
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = '' */;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
DROP DATABASE IF EXISTS `pms_db`;
CREATE DATABASE `pms_db`
  DEFAULT CHARACTER SET utf8;

USE `pms_db`;


/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id`       INT(11) NOT NULL   AUTO_INCREMENT
  COMMENT '菜单表id',
  `menuname` VARCHAR(500)
             CHARACTER SET utf8 DEFAULT NULL
  COMMENT '菜单名称',
  `menuurl`  VARCHAR(500)       DEFAULT NULL
  COMMENT '菜单地址',
  `isfather` INT(11)            DEFAULT NULL
  COMMENT '1:1级菜单2:2级菜单',
  `pid`      INT(11)            DEFAULT NULL
  COMMENT '父菜单',
  `remark`   VARCHAR(500)
             CHARACTER SET utf8 DEFAULT NULL
  COMMENT '备注',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `t_menu` */

INSERT INTO `t_menu` (`id`, `menuname`, `menuurl`, `isfather`, `pid`, `remark`)
VALUES (1, '系统管理', '', 1, 0, NULL), (2, '用户信息管理', '/user/userList', 2, 1, NULL),
  (3, '角色信息管理', '/role/roleList', 2, 1, NULL), (4, '项目储备', '', 1, 0, NULL), (5, '项目管理', NULL, 1, 0, NULL),
  (6, '项目申报', 'project/toAddProject', 2, 4, NULL), (7, '项目审批', '/project/approvalProjectList', 2, 4, NULL),
  (8, '项目查询', '/project/projectList', 2, 5, NULL), (9, '我的项目', '/project/myProjectList', 2, 5, NULL),
  (10, '项目类型信息管理', '/protype/protypeList', 2, 1, NULL), (12, '进度信息管理', '/role/processList', 2, 1, NULL),
  (13, '个人信息管理', '/role/myList', 2, 1, NULL), (14, '储备总览', '/project/notProjectList', 2, 4, NULL),
  (15, '我的储备', '/project/notMyProjectList', 2, 5, NULL);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id`       INT(11)            NOT NULL AUTO_INCREMENT
  COMMENT '角色表id',
  `rolename` VARCHAR(50)
             CHARACTER SET utf8 NOT NULL
  COMMENT '角色名称',
  `remark`   VARCHAR(500)
             CHARACTER SET utf8          DEFAULT NULL
  COMMENT '备注',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `t_role` */

INSERT INTO `t_role` (`id`, `rolename`, `remark`) VALUES (1, '管理员', '无');
INSERT INTO `t_role` (`id`, `rolename`, `remark`) VALUES (2, '政府部门', '无');
INSERT INTO `t_role` (`id`, `rolename`, `remark`) VALUES (3, '技术', '无');

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `role_id` INT(11)     DEFAULT NULL
  COMMENT '角色名称',
  `menu_id` VARCHAR(11) DEFAULT NULL
  COMMENT '菜单名称'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `t_role_menu` */

INSERT INTO `t_role_menu` (`role_id`, `menu_id`)
VALUES (1, '2'), (1, '3'), (1, '3.Add'), (1, '3.Delete'), (1, '3.Update'), (1, '3.View'), (1, '3.Import'),
  (1, '3.Export'), (1, '4'), (1, '5'), (1, '6'), (1, '7'), (1, '8'), (1, '9'), (1, '10'), (1, '10.Add'),
  (1, '10.Delete'), (1, '10.Update'), (1, '10.View'), (1, '11'), (1, '12'), (1, '12.Add'), (1, '12.Delete'),
  (1, '12.Update'), (1, '12.View'),(1,'2.View'),(1,'2.Edit');
INSERT INTO `t_role_menu` (`role_id`, `menu_id`) VALUES (2, '5'), (2, '6'), (2, '7'), (2, '8'), (2, '9');
INSERT INTO `t_role_menu` (`role_id`, `menu_id`) VALUES (3, '5'), (3, '6'), (3, '8'), (3, '9');

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id`        INT(11) NOT NULL   AUTO_INCREMENT
  COMMENT '用户表id',
  `loginname` VARCHAR(50)        DEFAULT NULL
  COMMENT '用户名',
  `loginpass` VARCHAR(50)        DEFAULT NULL
  COMMENT '密码',
  `username`  VARCHAR(50)
              CHARACTER SET utf8 DEFAULT NULL
  COMMENT '姓名',
  `phone`     VARCHAR(50)        DEFAULT NULL
  COMMENT '电话',
  `email`     VARCHAR(50)        DEFAULT NULL
  COMMENT '邮箱',
  `remark`    VARCHAR(500)
              CHARACTER SET utf8 DEFAULT NULL
  COMMENT '备注',
  `sex`       VARCHAR(50)
              CHARACTER SET utf8 DEFAULT NULL
  COMMENT '性别',
  `role_id`   INT(11)            DEFAULT NULL
  COMMENT '角色id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `t_user` */

INSERT INTO `t_user` (`id`, `loginname`, `loginpass`, `username`, `phone`, `email`, `remark`, `sex`, `role_id`)
VALUES (1, 'admin', '1', '张三1', '15590887888', 'yugyut@qq.com1', '无1', '女', 1);
INSERT INTO `t_user` (`id`, `loginname`, `loginpass`, `username`, `phone`, `email`, `remark`, `sex`, `role_id`)
VALUES (2, 'admin2', '2', '张三2', '15590887888', 'yugyut@qq.com1', '无1', '女', 2);
INSERT INTO `t_user` (`id`, `loginname`, `loginpass`, `username`, `phone`, `email`, `remark`, `sex`, `role_id`)
VALUES (3, 'admin3', '3', '张三3', '15590887888', 'yugyut@qq.com1', '无1', '女', 3);


/*Table structure for table `t_protype` */

DROP TABLE IF EXISTS `t_protype`;

CREATE TABLE `t_protype` (
  `id`       INT(11) NOT NULL   AUTO_INCREMENT
  COMMENT '项目类型表id',
  `typename` VARCHAR(500)
             CHARACTER SET utf8 DEFAULT NULL
  COMMENT '项目类型名称',
  `isfather` INT(11)            DEFAULT NULL
  COMMENT '1:1级类型2:2级类型',
  `pid`      INT(11)            DEFAULT NULL
  COMMENT '父类型id',
  `remark`   VARCHAR(500)
             CHARACTER SET utf8 DEFAULT NULL
  COMMENT '备注',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `t_protype` */

INSERT INTO `t_protype` (`id`, `typename`, `isfather`, `pid`, `remark`)
VALUES (1, '城镇生活污水垃圾处理设施建设工程', 1, 0, NULL), (2, '重点地区污染治理工程', 1, 0, NULL), (3, '城镇生活污水处理设施及配套管网建设', 2, 1, NULL),
  (4, '城镇生活垃圾处理设施建设', 2, 1, NULL), (5, '大气污染治理', 2, 2, NULL), (6, '资源节约循环利用重点工程', 1, 5, NULL),
  (7, '学校（机场、高铁站）和城市道路（隧道）照明节能改造城镇化节能升级改造工程', 2, 6, NULL), (8, '资源循环利用示范基地建设（包括产业废弃物综合利用基地建设）', 2, 6, NULL),
  (9, '农业循环经济基地建设', 2, 6, NULL);


/*Table structure for table `t_process` */

DROP TABLE IF EXISTS `t_process`;

CREATE TABLE `t_process` (
  `id`    INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '进度表id',
  `pname` VARCHAR(500)     DEFAULT NULL
  COMMENT '进度名称',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `t_process` */

INSERT INTO `t_process` (`id`, `pname`) VALUES (1, '谋划'), (2, '前期'), (3, '拟开工'), (4, '在建'), (5, '竣工');

/*Table structure for table `t_project` */

DROP TABLE IF EXISTS `t_project`;

CREATE TABLE IF NOT EXISTS `t_project` (
  `id`                  INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '项目id',
  `proname`             VARCHAR(500)     DEFAULT NULL
  COMMENT '项目名称',
  `startTime`           DATE             DEFAULT NULL
  COMMENT '开始时间',
  `endTime`             DATE             DEFAULT NULL
  COMMENT '结束时间',
  `budget`              FLOAT            DEFAULT NULL
  COMMENT '投资预算',
  `type1`               INT(11)          DEFAULT NULL
  COMMENT '一级类型',
  `type2`               INT(11)          DEFAULT NULL
  COMMENT '二级类型',
  `scale`               VARCHAR(500)     DEFAULT NULL
  COMMENT '建设规模',
  `userId`              INT(11)          DEFAULT NULL
  COMMENT '申请人id',
  `areaId`              INT(11)          DEFAULT NULL
  COMMENT '区县id',
  `cityId`              INT(11)          DEFAULT NULL
  COMMENT '市id',
  `address`             VARCHAR(500)     DEFAULT NULL
  COMMENT '详细地址',
  `processId`           INT(11)          DEFAULT NULL
  COMMENT '进展阶段',
  `sourceDepartment`    VARCHAR(500)     DEFAULT NULL
  COMMENT '报送部门',
  `processDiscription`  VARCHAR(500)     DEFAULT NULL
  COMMENT '进展说明',
  `approvalState`       INT(11)          DEFAULT NULL
  COMMENT '审核状态',
  `approvalDiscription` VARCHAR(500)     DEFAULT NULL
  COMMENT '审核批复'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*Data for the table `t_project` */


/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;