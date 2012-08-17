# --------------------------------------------------------
# Host:                         127.0.0.1
# Database:                     btcbasecd
# Server version:               5.5.11
# Server OS:                    Win32
# HeidiSQL version:             5.0.0.3272
# Date/time:                    2012-08-17 16:18:31
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
  `ID` bigint(20) NOT NULL,
  `CODE` varchar(30) NOT NULL,
  `DEFUNCT_IND` tinyint(1) DEFAULT '0',
  `NAME` varchar(30) NOT NULL,
  `PARENT_CODE` varchar(30) DEFAULT NULL,
  `VALUE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `ID` bigint(20) NOT NULL,
  `AVAILABLE` tinyint(1) DEFAULT '0',
  `CATEGORY` varchar(255) DEFAULT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `defunct_ind` char(1) DEFAULT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `PRODUCTION_DATE` datetime DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.resource
DROP TABLE IF EXISTS `resource`;
CREATE TABLE IF NOT EXISTS `resource` (
  `ID` bigint(20) NOT NULL,
  `CODE` varchar(50) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `PARENT_ID` bigint(20) DEFAULT NULL,
  `SEQ_NO` smallint(6) DEFAULT NULL,
  `TYPE` varchar(50) NOT NULL,
  `URI` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `ID` bigint(20) NOT NULL,
  `CODE` varchar(50) NOT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.role_resource
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE IF NOT EXISTS `role_resource` (
  `ID` bigint(20) NOT NULL,
  `CODE` varchar(50) DEFAULT NULL,
  `RESOURCE_ID` bigint(20) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ROLE_RESOURCE_ROLE_ID` (`ROLE_ID`),
  KEY `FK_ROLE_RESOURCE_RESOURCE_ID` (`RESOURCE_ID`),
  CONSTRAINT `FK_ROLE_RESOURCE_RESOURCE_ID` FOREIGN KEY (`RESOURCE_ID`) REFERENCES `resource` (`ID`),
  CONSTRAINT `FK_ROLE_RESOURCE_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`)
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


# Dumping structure for table btcbasecd.teacher
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
  `ID` bigint(20) NOT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `defunct_ind` char(1) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `NAME` varchar(50) NOT NULL,
  `NATIONALITY` varchar(50) DEFAULT NULL,
  `PHONE` varchar(20) DEFAULT NULL,
  `REMARKS` varchar(500) DEFAULT NULL,
  `SEX` varchar(10) DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NULL,
  `VIP` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `ID` bigint(20) NOT NULL,
  `AD_ACCOUNT` varchar(50) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `defunct_ind` char(1) DEFAULT NULL,
  `PERNR` varchar(20) DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table btcbasecd.user_role
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_user_role_role_id` (`role_id`),
  CONSTRAINT `FK_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`),
  CONSTRAINT `FK_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
