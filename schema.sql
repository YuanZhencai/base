# --------------------------------------------------------
# Host:                         127.0.0.1
# Database:                     btcbasecd
# Server version:               5.5.11
# Server OS:                    Win32
# HeidiSQL version:             5.0.0.3272
# Date/time:                    2012-08-23 13:13:21
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
# Dumping database structure for btcbasecd
DROP DATABASE IF EXISTS `btcbasecd`;
CREATE DATABASE IF NOT EXISTS `btcbasecd` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `btcbasecd`;


# Dumping structure for table btcbasecd.dict
DROP TABLE IF EXISTS `dict`;
CREATE TABLE IF NOT EXISTS `dict` (
  `id` bigint(20) NOT NULL,
  `category` varchar(30) NOT NULL,
  `defunct_ind` char(1) DEFAULT NULL,
  `code_key` varchar(30) NOT NULL,
  `lang` varchar(5) NOT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  `SEQ_NO` bigint(20) DEFAULT NULL,
  `SYS_IND` char(1) NOT NULL,
  `value` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.hibernate_sequence
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.o
DROP TABLE IF EXISTS `o`;
CREATE TABLE IF NOT EXISTS `o` (
  `id` varchar(20) NOT NULL,
  `bukrs` varchar(200) DEFAULT NULL,
  `DEFUNCT_IND` varchar(1) DEFAULT NULL,
  `kostl` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `stext` varchar(200) DEFAULT NULL,
  `zhrtxxlid` varchar(200) DEFAULT NULL,
  `zhrtxxlms` varchar(200) DEFAULT NULL,
  `zhrzzcjid` varchar(200) DEFAULT NULL,
  `zhrzzdwid` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.p
DROP TABLE IF EXISTS `p`;
CREATE TABLE IF NOT EXISTS `p` (
  `id` varchar(20) NOT NULL,
  `bukrs` varchar(200) DEFAULT NULL,
  `celno` varchar(200) DEFAULT NULL,
  `DEFUNCT_IND` varchar(1) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `gesch` varchar(200) DEFAULT NULL,
  `icnum` varchar(200) DEFAULT NULL,
  `kostl` varchar(200) DEFAULT NULL,
  `nachn` varchar(200) DEFAULT NULL,
  `name2` varchar(200) DEFAULT NULL,
  `orgeh` varchar(200) DEFAULT NULL,
  `telno` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `defunct_ind` char(1) DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NULL,
  `AVAILABLE` tinyint(1) DEFAULT NULL,
  `CATEGORY` varchar(255) DEFAULT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  `description` longtext,
  `NAME` varchar(255) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `PRODUCTION_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.ps
DROP TABLE IF EXISTS `ps`;
CREATE TABLE IF NOT EXISTS `ps` (
  `id` varchar(20) NOT NULL,
  `DEFUNCT_IND` varchar(1) DEFAULT NULL,
  `MAIN_IND` varchar(200) DEFAULT NULL,
  `pid` varchar(20) NOT NULL,
  `sid` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKA03FCB21C6F` (`sid`),
  KEY `FKA035D14ECD3` (`pid`),
  CONSTRAINT `FKA035D14ECD3` FOREIGN KEY (`pid`) REFERENCES `p` (`id`),
  CONSTRAINT `FKA03FCB21C6F` FOREIGN KEY (`sid`) REFERENCES `s` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.pu
DROP TABLE IF EXISTS `pu`;
CREATE TABLE IF NOT EXISTS `pu` (
  `id` varchar(50) NOT NULL,
  `DEFUNCT_IND` varchar(1) DEFAULT NULL,
  `pernr` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.resource
DROP TABLE IF EXISTS `resource`;
CREATE TABLE IF NOT EXISTS `resource` (
  `id` bigint(20) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(20) NOT NULL,
  `PARENT_ID` bigint(20) DEFAULT NULL,
  `SEQ_NO` smallint(6) DEFAULT NULL,
  `type` varchar(50) NOT NULL,
  `uri` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL,
  `code` varchar(50) NOT NULL,
  `DEFUNCT_IND` char(1) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.role_resource
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE IF NOT EXISTS `role_resource` (
  `id` bigint(20) NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `RESOURCE_ID` bigint(20) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF13191B71775B373` (`ROLE_ID`),
  KEY `FKF13191B75E8287F3` (`RESOURCE_ID`),
  CONSTRAINT `FKF13191B75E8287F3` FOREIGN KEY (`RESOURCE_ID`) REFERENCES `resource` (`id`),
  CONSTRAINT `FKF13191B71775B373` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.s
DROP TABLE IF EXISTS `s`;
CREATE TABLE IF NOT EXISTS `s` (
  `id` varchar(20) NOT NULL,
  `DEFUNCT_IND` varchar(1) DEFAULT NULL,
  `kostl` varchar(200) DEFAULT NULL,
  `oid` varchar(20) NOT NULL,
  `stext` varchar(200) DEFAULT NULL,
  `zhrcjid` varchar(200) DEFAULT NULL,
  `zhrcjms` varchar(200) DEFAULT NULL,
  `zhrtxxlid` varchar(200) DEFAULT NULL,
  `zhrtxxlms` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.sequence
DROP TABLE IF EXISTS `sequence`;
CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.synclog
DROP TABLE IF EXISTS `synclog`;
CREATE TABLE IF NOT EXISTS `synclog` (
  `id` bigint(20) NOT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `SYNC_DATETIME` datetime DEFAULT NULL,
  `SYNC_IND` varchar(1) DEFAULT NULL,
  `SYNC_TYPE` varchar(20) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.teacher
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `defunct_ind` char(1) DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `NAME` varchar(50) NOT NULL,
  `NATIONALITY` varchar(50) DEFAULT NULL,
  `PHONE` varchar(20) DEFAULT NULL,
  `REMARKS` longtext,
  `SEX` varchar(10) DEFAULT NULL,
  `VIP` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL,
  `AD_ACCOUNT` varchar(50) DEFAULT NULL,
  `pernr` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.user_role
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK143BF46A1775B373` (`role_id`),
  KEY `FK143BF46ABCA07753` (`user_id`),
  CONSTRAINT `FK143BF46ABCA07753` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK143BF46A1775B373` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.usr
DROP TABLE IF EXISTS `usr`;
CREATE TABLE IF NOT EXISTS `usr` (
  `id` varchar(50) NOT NULL,
  `celno` varchar(200) DEFAULT NULL,
  `DEFUNCT_IND` varchar(1) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `SEARCH_PHRASE` varchar(200) DEFAULT NULL,
  `telno` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
