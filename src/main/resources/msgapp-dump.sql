-- MySQL dump 10.13  Distrib 5.5.18, for Win64 (x86)
--
-- Host: localhost    Database: msgapp
-- ------------------------------------------------------
-- Server version	5.5.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `addressId` int(13) NOT NULL AUTO_INCREMENT,
  `provOrgId` int(13) NOT NULL,
  `locationId` int(13) NOT NULL,
  `zipCode` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `latitude` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `longitude` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `state` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `county` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `addrLine1` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `addrLine2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `addressType` enum('BUSPRACTICE','MAILING') COLLATE utf8_unicode_ci NOT NULL,
  `phoneNumber` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `faxNumber` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(75) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`addressId`),
  KEY `address_ibfk_1` (`provOrgId`,`locationId`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`provOrgId`, `locationId`) REFERENCES `location` (`provOrgId`, `locationId`)
) ENGINE=InnoDB AUTO_INCREMENT=1261 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1256,1231,1250,'06902','41.0534302','-73.5387341','Stamford','CT',NULL,'USA','25 Fieldstone rd',NULL,'BUSPRACTICE','203-355-9879','203-355-9879','2014-08-09 21:28:04','2014-08-09 21:28:04','user1@gmail.com'),(1257,1231,1252,'06902','41.0534302','-73.5387341','Stamford','CT',NULL,'USA','29 Fieldstone rd',NULL,'BUSPRACTICE','203-355-9879','203-355-9879','2014-08-09 21:29:30','2014-08-09 21:29:30','user1@gmail.com'),(1258,1231,1253,'06902','41.0534302','-73.5387341','Stamford','CT',NULL,'USA','25 Fieldstone rd',NULL,'BUSPRACTICE','203-355-9879','203-355-9879','2014-08-11 22:53:15','2014-08-11 22:53:15','user1@gmail.com'),(1259,1236,1254,'10504','41.1264849','-73.7140195','Armonk','NY','','USA','16 Orchard Drive','','BUSPRACTICE','(914) 273-3404','','2014-08-28 19:45:11','2014-08-28 19:45:11','west@gmail.com'),(1260,1236,1257,'10509','41.3973163','-73.6170721','Brewster','NY','','USA','1663 Route 22','','BUSPRACTICE','(914) 666-5125','','2014-08-28 20:04:20','2014-08-28 20:04:20','west@gmail.com');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `doctorid` int(13) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `lastName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(75) COLLATE utf8_unicode_ci NOT NULL,
  `doctorContentBytes` blob NOT NULL,
  `speciality` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `imageName` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `provOrgId` int(13) NOT NULL,
  PRIMARY KEY (`doctorid`),
  UNIQUE KEY `doctorUk1` (`provOrgId`,`firstName`,`lastName`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (6,'Donald','Cohen','2014-08-24 12:39:47','2014-09-26 22:17:36','user1@gmail.com','xmS=SA��)� �\Z,���29��C��](�������Ygz�v\r,�\rF�&�sc#s����w�&[S��������Yk�S_����/��zlCH�z�c���}Ҧ���l�Ƿ��g��l���\\�tja�[�\\@�%�p�##T��aw`\0&��Tٔ�\\�K.�t�]���DO�@B�w���E�`��	�ǘQ�갻���4��r�m\"d��R\\�+H����KM��EH�B�C�{ʽ�N-C^ڛ�O��/��&,IgN��\\.D�k�[�@^�}����\'|�aS�f�{��������V�`I\"ok�UΏu�+�ycc�9��]�3m.�!��7\n62[0��D|܊�]�.4��w]����$�PHl�v�M�!;�ɈM��ڈ�V	�{~��l�%�h� ୔�r�����p�j&|M�q��d��D��/(�G@�\0r���g�&ET��\Z^����GX�|�A�7x�`lY�n/S�v�\n��u��ê��Y��\'�	V���{B�ѰƷ�V�\Z�{��R�AE�����-��%Z:+��N��!H+yu��<rj�S��G�f�Gly�8=��(�����P\n��P���%�x���xOZ#w�|0��A,dΏ��:w�r���<��Ӿ@ʉ=���֩���\ncTE�?�r�','Internal Medicine','/images/doc/1231_6.png',1231),(12,'Priyanka','Poddar','2014-08-24 12:47:39','2014-08-24 12:47:39','user1@gmail.com','xUP�NA\\\"%Z��F\\>�\n%�	��D����}����\Z\n>�_��<�W֌53��_Տ��ko��T#tIJ[����r�k�<�N�������԰RCX�$�L�)�\ZW���Āk\'sar�m������-��̤�(�/|��B\r� �ݛ�P�J�jF����d�U�u:8��4�x�1���k\Z2t�����y�K�w9����{���8uyd�3�@���Ag�}�1�`�݄^�.�]���}A����Z�vD������8�?�I�0','Cardiologist',NULL,1231),(13,'Debal','Guha','2014-08-26 23:35:46','2014-08-26 23:35:46','user1@gmail.com','x���NA����AP#�:�YI��2\rp8HFI=��;ٻ��Ι��Ǡ����-ҧ�+��\"A���7;�}��#t����fA!.ر�K�(��f�y�o#�2��R����ݟ��~z!�pfK�.�T\n��.ph0O�S��/!��9J���i�l�!;�l{f=�(\'����4C�GB�?�hN�Һ�a�����ZJyL�w���hFI��E��Ҵ�?��n�qlh�������D���ekZ&�Z�\n�	9����*�Rt�ؚX�{߃e0��|�;u(Am;��E���{�o[�8���K�\n\0h��}�*t����a������;��\n�c����\r������T�sTf(\n�}	|=�`��\'���','Cardiologist','/home/img/debal_img.png',1231),(14,'Donald','Cohen','2014-08-28 20:46:52','2014-08-28 21:16:49','west@gmail.com','xeT=sEɲ]6.CY�]\r��%\n�	�?Tu�g�J�h��v�ݙ�L��-��b�919��7������L��ׯ_�o���1�O3_��`3�3���8�U�7�����}&>�v����_��m�]WwF�>�+\\W�����.��n�%X7}<R7�\\E}н�b�푍��{goCb\'6�b�{��S�#u7lP���/$ݮ��-6�;a7��������ʊ.��-�;x�T��<��9/��.�1�/ �s8�R��\Zד+W�W�O8F����0mI/*Y��$gG9�\n6��H%�W��	Yz�����\r\r}�S�isLZh��Y�ogL�� �ޝ�v5���9�x��5�c��d��h�C�n�+hUcB���C�����p�S��ÌCC9��-3A`b�����t�8\Z \0��[�˲֡P��(4��P��M�wװ�-PIoM�/9V���P��������ok���N��b�JƉ�#b�\"�h����lgV�>\\����O��B�M���?������Z;P�~P���|�ۢ��\0�1�w\r������v�|&����ԇs:d	�?19�:�֖�JƦiGO��SCOqg\r@�h���V����m\0�g����v���hC�Z���b����F���ԅͼ��lpp0X�G�KN�Z��t��f�E/.6���	�U�7�61k�h�\Z֘X�˸gX$�G�=�X1ӵ\0�]��N��F�q�e����z8s�R�У\'��E��NE}�����;»t���Z-z����[u7u�+%/{��P�N8b[�����<@����!�m�*��\\��|�]�1us\\i�J��Z���\'����6\\6i��ti����&b���/y騱h��g�3�҈��*ᡯ�\\�&٢Z���&','Internal Medicine','/images/doc/1236_14.jpg',1236),(17,'Nina','Inamdar','2014-08-28 23:44:22','2014-08-28 23:45:45','west@gmail.com','xmT�rEɲ\\����TCA�ie��r��dY�:˂�K�p�ӷ;���x��n���o ��N�rb\"r���]�I�$[S�=�_�~���#�� ��]���s�35eU,���r��6�s9�0r��R���o��m�^���<s5�MX{w�/�Lni��	m�c�.�2�H��>Z�6[c�cw��FHOu.I;�R�$V��vPq9-�3l(ݮ����w���\n�Ex�t�k�f8�j���K�jY�Ļs\\T�Yi��\n/ >�|>x�hw5��W�>��M1F�+��Pt�O+�NJ�P\"��� ��RG�Pq{(��,�\0�B{鵂���Զ�rc�	Al߇�!/Y\"��3ma*g�AZ�5X[cr��fZ!��u��ˢ����	�\Z:%f���1��Q�w��J.7�et�\'&��~s?�C��a���E�I�(�-7��2L�S=����Vh���TR,�AC�*����k���hm��\Z���S�����d���CLbB�0c���xD�f�3Mm�.�H�̒��a�Ml~����?�kb�P�E�#�\n!V�k��I���a��q��g����6�ν�H|r�s8u�� ���{MLθ~��a��i�������#�a�-��OX;�~��@˔���^X�-?�0X�I^:gv��d�J*��,/5�\Z�RI#_� ���ܑ�9�%�=�w�k*�XR9��(������%�;�\' ��9��y�;�2�؍nRs[�4Ʊc{F�Bշ�����ݓ.�y`���œ���j��]��?���l3��S��1n�ށӮ���v�݈�3����.X��}�������?r�J�N0�J���%��ǁ�kv=���_�����#^hK7&^Z>qQ������.�1̥ªM�ؗ�6-�mdsH˿�K�MHҥ@�k���gW	�\\m��\\�}R�/<�','Cadiology','/images/doc/1236_17.jpg',1236);
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctorlocmap`
--

DROP TABLE IF EXISTS `doctorlocmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctorlocmap` (
  `doctorLocMapId` int(13) NOT NULL AUTO_INCREMENT,
  `provOrgId` int(13) NOT NULL,
  `locationId` int(13) NOT NULL,
  `doctorId` int(13) NOT NULL,
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(75) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`doctorLocMapId`),
  UNIQUE KEY `provorguserlocmapuk1` (`provOrgId`,`locationId`,`doctorId`),
  KEY `doctorlocmap_fk2` (`doctorId`),
  CONSTRAINT `doctorlocmap_fk1` FOREIGN KEY (`provOrgId`, `locationId`) REFERENCES `location` (`provOrgId`, `locationId`),
  CONSTRAINT `doctorlocmap_fk2` FOREIGN KEY (`doctorId`) REFERENCES `doctor` (`doctorid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctorlocmap`
--

LOCK TABLES `doctorlocmap` WRITE;
/*!40000 ALTER TABLE `doctorlocmap` DISABLE KEYS */;
INSERT INTO `doctorlocmap` VALUES (7,1231,1250,13,'2014-08-26 23:35:46','2014-08-26 23:35:46','user1@gmail.com'),(8,1231,1252,13,'2014-08-26 23:35:46','2014-08-26 23:35:46','user1@gmail.com'),(9,1236,1254,14,'2014-08-28 21:01:22','2014-08-28 21:01:22','west@gmail.com'),(10,1236,1257,14,'2014-08-28 21:01:22','2014-08-28 21:01:22','west@gmail.com'),(11,1236,1254,17,'2014-08-28 23:44:22','2014-08-28 23:44:22','west@gmail.com'),(12,1236,1257,17,'2014-08-28 23:44:22','2014-08-28 23:44:22','west@gmail.com');
/*!40000 ALTER TABLE `doctorlocmap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hop`
--

DROP TABLE IF EXISTS `hop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hop` (
  `HOPId` int(13) NOT NULL AUTO_INCREMENT,
  `provOrgId` int(13) NOT NULL,
  `locationId` int(13) NOT NULL,
  `dayOfTheWeek` enum('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday') COLLATE utf8_unicode_ci NOT NULL,
  `openHour` int(2) NOT NULL,
  `openMin` int(2) NOT NULL,
  `openPeriod` enum('AM','PM') COLLATE utf8_unicode_ci NOT NULL,
  `closeHour` int(2) NOT NULL,
  `closeMin` int(2) NOT NULL,
  `closePeriod` enum('AM','PM') COLLATE utf8_unicode_ci NOT NULL,
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(75) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`HOPId`),
  KEY `provOrgId` (`provOrgId`,`locationId`),
  CONSTRAINT `hop_ibfk_1` FOREIGN KEY (`provOrgId`, `locationId`) REFERENCES `location` (`provOrgId`, `locationId`)
) ENGINE=InnoDB AUTO_INCREMENT=370 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hop`
--

LOCK TABLES `hop` WRITE;
/*!40000 ALTER TABLE `hop` DISABLE KEYS */;
INSERT INTO `hop` VALUES (357,1231,1250,'Tuesday',30,0,'AM',30,0,'PM','2014-08-09 21:34:38','2014-08-09 21:34:38','user1@gmail.com'),(358,1231,1250,'Wednesday',30,0,'AM',30,0,'PM','2014-08-09 21:34:38','2014-08-09 21:34:38','user1@gmail.com'),(359,1231,1250,'Monday',30,0,'AM',30,0,'PM','2014-08-09 21:34:38','2014-08-09 21:34:38','user1@gmail.com'),(360,1236,1254,'Monday',9,0,'AM',5,0,'PM','2014-08-28 19:45:50','2014-08-28 19:45:50','west@gmail.com'),(361,1236,1254,'Tuesday',9,0,'AM',5,0,'PM','2014-08-28 19:45:50','2014-08-28 19:45:50','west@gmail.com'),(362,1236,1254,'Wednesday',9,0,'AM',5,0,'PM','2014-08-28 19:45:50','2014-08-28 19:45:50','west@gmail.com'),(363,1236,1254,'Thursday',9,0,'AM',5,0,'PM','2014-08-28 19:45:50','2014-08-28 19:45:50','west@gmail.com'),(364,1236,1254,'Friday',9,0,'AM',5,0,'PM','2014-08-28 19:45:50','2014-08-28 19:45:50','west@gmail.com'),(365,1236,1257,'Monday',9,0,'AM',5,0,'PM','2014-08-28 20:04:36','2014-08-28 20:04:36','west@gmail.com'),(366,1236,1257,'Tuesday',9,0,'AM',5,0,'PM','2014-08-28 20:04:36','2014-08-28 20:04:36','west@gmail.com'),(367,1236,1257,'Wednesday',9,0,'AM',5,0,'PM','2014-08-28 20:04:36','2014-08-28 20:04:36','west@gmail.com'),(368,1236,1257,'Thursday',9,0,'AM',5,0,'PM','2014-08-28 20:04:36','2014-08-28 20:04:36','west@gmail.com'),(369,1236,1257,'Friday',9,0,'AM',5,0,'PM','2014-08-28 20:04:36','2014-08-28 20:04:36','west@gmail.com');
/*!40000 ALTER TABLE `hop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurance_lk`
--

DROP TABLE IF EXISTS `insurance_lk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `insurance_lk` (
  `insuranceId` int(13) NOT NULL AUTO_INCREMENT,
  `insuranceProvider` varchar(200) NOT NULL,
  `insurancePlan` varchar(200) NOT NULL,
  `state` varchar(2) DEFAULT NULL,
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(75) DEFAULT NULL,
  `popular` tinyint(3) unsigned DEFAULT '0',
  `active` tinyint(3) unsigned DEFAULT '0',
  `payerid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`insuranceId`)
) ENGINE=InnoDB AUTO_INCREMENT=6166 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurance_lk`
--

LOCK TABLES `insurance_lk` WRITE;
/*!40000 ALTER TABLE `insurance_lk` DISABLE KEYS */;
INSERT INTO `insurance_lk` VALUES (3160,'1199SEIU','NONE','DC','2014-05-16 03:54:18','2014-05-16 03:54:18','system',0,1,NULL),(3169,'AARP','NONE','DC','2014-05-16 03:54:19','2014-05-16 03:54:19','system',0,1,'AARP'),(3172,'Abrazo Advantage','NONE','DC','2014-05-16 03:54:19','2014-05-16 03:54:19','system',0,1,NULL),(3174,'Access Plus Program','NONE','DC','2014-05-16 03:54:19','2014-05-16 03:54:19','system',0,1,NULL),(3175,'Accountable Health Plan','NONE','DC','2014-05-16 03:54:20','2014-05-16 03:54:20','system',0,1,NULL),(3177,'ACE','NONE','DC','2014-05-16 03:54:20','2014-05-16 03:54:20','system',0,1,NULL),(3182,'Advanced Reproductive Care Inc.','NONE','DC','2014-05-16 03:54:20','2014-05-16 03:54:20','system',0,1,NULL),(3199,'Advantage Health','NONE','DC','2014-05-16 03:54:22','2014-05-16 03:54:22','system',0,1,NULL),(3201,'Adventist Health','NONE','DC','2014-05-16 03:54:22','2014-05-16 03:54:22','system',0,1,NULL),(3270,'Aetna','NONE','DC','2014-05-16 03:54:28','2014-05-16 03:54:28','system',0,1,'00002'),(3298,'Aetna National Transplant Network','NONE','DC','2014-05-16 03:54:30','2014-05-16 03:54:30','system',0,1,'00002'),(3308,'Affinity Health Plan','NONE','DC','2014-05-16 03:54:31','2014-05-16 03:54:31','system',0,1,'AFNTY'),(3312,'AIG','NONE','DC','2014-05-16 03:54:31','2014-05-16 03:54:31','system',0,1,NULL),(3313,'Alliance PPO','NONE','DC','2014-05-16 03:54:31','2014-05-16 03:54:31','system',0,1,NULL),(3316,'Allied Insurance Group','NONE','DC','2014-05-16 03:54:32','2014-05-16 03:54:32','system',0,1,NULL),(3323,'Alta Bates Medical Group','NONE','DC','2014-05-16 03:54:32','2014-05-16 03:54:32','system',0,1,NULL),(3324,'Altius (Coventry Health Care)','NONE','DC','2014-05-16 03:54:32','2014-05-16 03:54:32','system',0,1,'COVTY00505'),(3329,'American Family Insurance','NONE','DC','2014-05-16 03:54:33','2014-05-16 03:54:33','system',0,1,NULL),(3334,'AmeriChoice by UnitedHealthcare','NONE','DC','2014-05-16 03:54:33','2014-05-16 03:54:33','system',0,1,NULL),(3338,'AmeriChoice of PA','NONE','DC','2014-05-16 03:54:34','2014-05-16 03:54:34','system',0,1,NULL),(3345,'AmeriGroup','NONE','DC','2014-05-16 03:54:34','2014-05-16 03:54:34','system',0,1,'AMGRP'),(3374,'AmeriHealth','NONE','DC','2014-05-16 03:54:37','2014-05-16 03:54:37','system',0,1,NULL),(3379,'AmeriHealth Administrators','NONE','DC','2014-05-16 03:54:37','2014-05-16 03:54:37','system',0,1,'54763'),(3381,'AmeriHealth Mercy Family of Companies','NONE','DC','2014-05-16 03:54:37','2014-05-16 03:54:37','system',0,1,NULL),(3416,'Anthem Blue Cross Blue Shield','NONE','DC','2014-05-16 03:54:40','2014-05-16 03:54:40','system',0,1,'00039'),(3453,'Anthem Blue Cross of California','NONE','DC','2014-05-16 03:54:44','2014-05-16 03:54:44','system',0,1,NULL),(3467,'Anthem Blue Cross/ Blue Shield Transplant','NONE','DC','2014-05-16 03:54:45','2014-05-16 03:54:45','system',0,1,NULL),(3477,'APWU','NONE','DC','2014-05-16 03:54:46','2014-05-16 03:54:46','system',0,1,NULL),(3481,'Arapahoe County Dept Human Services','NONE','DC','2014-05-16 03:54:46','2014-05-16 03:54:46','system',0,1,NULL),(3489,'Arcadian Health Plan','NONE','DC','2014-05-16 03:54:47','2014-05-16 03:54:47','system',0,1,NULL),(3495,'Arise Health Plan','NONE','DC','2014-05-16 03:54:47','2014-05-16 03:54:47','system',0,1,NULL),(3497,'Arizona Physicians IPA (APIPA)','NONE','DC','2014-05-16 03:54:47','2014-05-16 03:54:47','system',0,1,'00322'),(3503,'Assurant Health Employee Benefits','NONE','DC','2014-05-16 03:54:48','2014-05-16 03:54:48','system',0,1,'00252'),(3509,'Atlantis Health Plan','NONE','DC','2014-05-16 03:54:48','2014-05-16 03:54:48','system',0,1,NULL),(3527,'Avesis','NONE','DC','2014-05-16 03:54:50','2014-05-16 03:54:50','system',0,1,NULL),(3556,'AvMed','NONE','DC','2014-05-16 03:54:53','2014-05-16 03:54:53','system',0,1,NULL),(3565,'Badgercare','NONE','DC','2014-05-16 03:54:54','2014-05-16 03:54:54','system',0,1,NULL),(3575,'Banner Choice Plus (Northcare)','NONE','DC','2014-05-16 03:54:54','2014-05-16 03:54:54','system',0,1,NULL),(3583,'Banner MediSun Inc.','NONE','DC','2014-05-16 03:54:55','2014-05-16 03:54:55','system',0,1,NULL),(3591,'BCBS New Mexico Medicaid Transplant','NONE','DC','2014-05-16 03:54:56','2014-05-16 03:54:56','system',0,1,NULL),(3601,'Beech Street','NONE','DC','2014-05-16 03:54:57','2014-05-16 03:54:57','system',0,1,NULL),(3608,'Behavioral Health Inc (BHI)','NONE','DC','2014-05-16 03:54:58','2014-05-16 03:54:58','system',0,1,NULL),(3619,'Black Lung Program Fed Claimants Only','NONE','DC','2014-05-16 03:54:59','2014-05-16 03:54:59','system',0,1,NULL),(3628,'Block Vision','NONE','DC','2014-05-16 03:54:59','2014-05-16 03:54:59','system',0,1,NULL),(3632,'Blue Bell Benefits Trust','NONE','DC','2014-05-16 03:55:00','2014-05-16 03:55:00','system',0,1,NULL),(3637,'Blue Cross and Blue Shield of Massachusetts','NONE','DC','2014-05-16 03:55:01','2014-05-16 03:55:01','system',0,1,'00139'),(3641,'Blue Cross Blue Shield','NONE','DC','2014-05-16 03:55:02','2014-05-16 03:55:02','system',0,1,NULL),(3646,'Blue Cross Blue Shield FEP','NONE','DC','2014-05-16 03:55:02','2014-05-16 03:55:02','system',0,1,NULL),(3653,'Blue Cross Blue Shield of Arizona','NONE','DC','2014-05-16 03:55:03','2014-05-16 03:55:03','system',0,1,'00090'),(3664,'Blue Cross Blue Shield of Delaware','NONE','DC','2014-05-16 03:55:04','2014-05-16 03:55:04','system',0,1,NULL),(3685,'Blue Cross Blue Shield of Florida','NONE','DC','2014-05-16 03:55:06','2014-05-16 03:55:06','system',0,1,'00267'),(3706,'Blue Cross Blue Shield of Georgia','NONE','DC','2014-05-16 03:55:07','2014-05-16 03:55:07','system',0,1,'00151'),(3725,'Blue Cross Blue Shield of Illinois','NONE','DC','2014-05-16 03:55:09','2014-05-16 03:55:09','system',0,1,'00268'),(3739,'Blue Cross Blue Shield of Texas','NONE','DC','2014-05-16 03:55:10','2014-05-16 03:55:10','system',0,1,'00271'),(3748,'Blue Distinctive Centers for Transplantation','NONE','DC','2014-05-16 03:55:11','2014-05-16 03:55:11','system',0,1,NULL),(3757,'Blue Shield','NONE','DC','2014-05-16 03:55:12','2014-05-16 03:55:12','system',0,1,'BSWAC'),(3771,'Blue Shield of California','NONE','DC','2014-05-16 03:55:13','2014-05-16 03:55:13','system',0,1,'00370'),(3782,'Bravo Health','NONE','DC','2014-05-16 03:55:14','2014-05-16 03:55:14','system',0,1,'ELDER'),(3788,'Bridgeway Health Solutions','NONE','DC','2014-05-16 03:55:15','2014-05-16 03:55:15','system',0,1,'CBRIA'),(3806,'Brown & Toland','NONE','DC','2014-05-16 03:55:16','2014-05-16 03:55:16','system',0,1,NULL),(3820,'California Division of Workers Compensation','NONE','DC','2014-05-16 03:55:17','2014-05-16 03:55:17','system',0,1,NULL),(3823,'Capital BlueCross','NONE','DC','2014-05-16 03:55:18','2014-05-16 03:55:18','system',0,1,'BCCBC'),(3835,'Care Improvement Plus','NONE','DC','2014-05-16 03:55:19','2014-05-16 03:55:19','system',0,1,NULL),(3838,'Care IQ','NONE','DC','2014-05-16 03:55:19','2014-05-16 03:55:19','system',0,1,NULL),(3839,'Care1st','NONE','DC','2014-05-16 03:55:19','2014-05-16 03:55:19','system',0,1,NULL),(3842,'Carecore National','NONE','DC','2014-05-16 03:55:19','2014-05-16 03:55:19','system',0,1,NULL),(3852,'CareFirst Blue Cross Blue Shield','NONE','DC','2014-05-16 03:55:20','2014-05-16 03:55:20','system',0,1,NULL),(3866,'CareMore','NONE','DC','2014-05-16 03:55:21','2014-05-16 03:55:21','system',0,1,NULL),(3873,'CarePlus Health Plans','NONE','DC','2014-05-16 03:55:22','2014-05-16 03:55:22','system',0,1,NULL),(3875,'Caterpillar','NONE','DC','2014-05-16 03:55:22','2014-05-16 03:55:22','system',0,1,NULL),(3888,'CDPHP','NONE','DC','2014-05-16 03:55:23','2014-05-16 03:55:23','system',0,1,NULL),(3897,'CeDar','NONE','DC','2014-05-16 03:55:24','2014-05-16 03:55:24','system',0,1,NULL),(3920,'Celtic Insurance Company','NONE','DC','2014-05-16 03:55:26','2014-05-16 03:55:26','system',0,1,NULL),(3923,'Childrens Community Health Plan','NONE','DC','2014-05-16 03:55:27','2014-05-16 03:55:27','system',0,1,NULL),(3925,'Chinese Community Health Plan','NONE','DC','2014-05-16 03:55:27','2014-05-16 03:55:27','system',0,1,NULL),(3930,'CHN','NONE','DC','2014-05-16 03:55:28','2014-05-16 03:55:28','system',0,1,NULL),(3931,'Choice Care Network','NONE','DC','2014-05-16 03:55:28','2014-05-16 03:55:28','system',0,1,NULL),(3936,'CHP+ State Funded Presumptive Elig.','NONE','DC','2014-05-16 03:55:28','2014-05-16 03:55:28','system',0,1,NULL),(3954,'Cigna','NONE','DC','2014-05-16 03:55:30','2014-05-16 03:55:30','system',0,1,'00001'),(3978,'Cigna Behavioral Health','NONE','DC','2014-05-16 03:55:32','2014-05-16 03:55:32','system',0,1,'00001'),(3989,'Cigna Healthcare of Colorado','NONE','DC','2014-05-16 03:55:32','2014-05-16 03:55:32','system',0,1,'00001'),(4000,'Cigna Lifesource Transplant','NONE','DC','2014-05-16 03:55:33','2014-05-16 03:55:33','system',0,1,'00001'),(4018,'Clements International','NONE','DC','2014-05-16 03:55:35','2014-05-16 03:55:35','system',0,1,NULL),(4024,'Cofinity','NONE','DC','2014-05-16 03:55:35','2014-05-16 03:55:35','system',0,1,NULL),(4035,'Cofinity Transplant','NONE','DC','2014-05-16 03:55:36','2014-05-16 03:55:36','system',0,1,NULL),(4046,'Colorado Access','NONE','DC','2014-05-16 03:55:37','2014-05-16 03:55:37','system',0,1,NULL),(4057,'Colorado Indigent Care Program (CICP)','NONE','DC','2014-05-16 03:55:38','2014-05-16 03:55:38','system',0,1,NULL),(4066,'Community Behavioral Health','NONE','DC','2014-05-16 03:55:39','2014-05-16 03:55:39','system',0,1,NULL),(4069,'Community First Health Plans','NONE','DC','2014-05-16 03:55:39','2014-05-16 03:55:39','system',0,1,NULL),(4077,'Community Health Alliance','NONE','DC','2014-05-16 03:55:40','2014-05-16 03:55:40','system',0,1,NULL),(4080,'Community Health Choice','NONE','DC','2014-05-16 03:55:41','2014-05-16 03:55:41','system',0,1,NULL),(4087,'Community Health Partners','NONE','DC','2014-05-16 03:55:41','2014-05-16 03:55:41','system',0,1,NULL),(4096,'Community Partners Health Plan','NONE','DC','2014-05-16 03:55:42','2014-05-16 03:55:42','system',0,1,NULL),(4102,'Comprehensive Health Insurance Plan (CHIP) of Illinois','NONE','DC','2014-05-16 03:55:43','2014-05-16 03:55:43','system',0,1,NULL),(4106,'Comprehensive Medical and Dental Program','NONE','DC','2014-05-16 03:55:43','2014-05-16 03:55:43','system',0,1,NULL),(4108,'ComPsych','NONE','DC','2014-05-16 03:55:43','2014-05-16 03:55:43','system',0,1,NULL),(4112,'Concert Health Plan','NONE','DC','2014-05-16 03:55:43','2014-05-16 03:55:43','system',0,1,NULL),(4125,'ConnectiCare','NONE','DC','2014-05-16 03:55:45','2014-05-16 03:55:45','system',0,1,NULL),(4130,'ConnectiCare of Massachusetts Inc.','NONE','DC','2014-05-16 03:55:45','2014-05-16 03:55:45','system',0,1,NULL),(4134,'Conoco','NONE','DC','2014-05-16 03:55:46','2014-05-16 03:55:46','system',0,1,NULL),(4141,'Consolidated Health Plans','NONE','DC','2014-05-16 03:55:46','2014-05-16 03:55:46','system',0,1,NULL),(4149,'Consumer Health Network','NONE','DC','2014-05-16 03:55:47','2014-05-16 03:55:47','system',0,1,NULL),(4154,'CorVel','NONE','DC','2014-05-16 03:55:47','2014-05-16 03:55:47','system',0,1,NULL),(4162,'Corvel','NONE','DC','2014-05-16 03:55:48','2014-05-16 03:55:48','system',0,1,NULL),(4177,'Coventry Health Care','NONE','DC','2014-05-16 03:55:49','2014-05-16 03:55:49','system',0,1,NULL),(4183,'Cover Colorado','NONE','DC','2014-05-16 03:55:50','2014-05-16 03:55:50','system',0,1,NULL),(4194,'CU Athletes UCB Sports','NONE','DC','2014-05-16 03:55:51','2014-05-16 03:55:51','system',0,1,NULL),(4205,'CU Housestaff - GME','NONE','DC','2014-05-16 03:55:52','2014-05-16 03:55:52','system',0,1,NULL),(4216,'CU UA Network','NONE','DC','2014-05-16 03:55:53','2014-05-16 03:55:53','system',0,1,NULL),(4224,'Davis Vision','NONE','DC','2014-05-16 03:55:54','2014-05-16 03:55:54','system',0,1,NULL),(4227,'DC Workers Compensation Commission','NONE','DC','2014-05-16 03:55:54','2014-05-16 03:55:54','system',0,1,NULL),(4232,'DCP Community Behavioral Health','NONE','DC','2014-05-16 03:55:54','2014-05-16 03:55:54','system',0,1,NULL),(4243,'DDHS Behavioral Health Community','NONE','DC','2014-05-16 03:55:55','2014-05-16 03:55:55','system',0,1,NULL),(4251,'Definity Health','NONE','DC','2014-05-16 03:55:56','2014-05-16 03:55:56','system',0,1,NULL),(4256,'Denver Health Medical Plan','NONE','DC','2014-05-16 03:55:57','2014-05-16 03:55:57','system',0,1,NULL),(4266,'Destiny Health Insurance Co','NONE','DC','2014-05-16 03:55:57','2014-05-16 03:55:57','system',0,1,NULL),(4267,'Devon Health Services','NONE','DC','2014-05-16 03:55:57','2014-05-16 03:55:57','system',0,1,NULL),(4270,'Dimensions Incorporated','NONE','DC','2014-05-16 03:55:58','2014-05-16 03:55:58','system',0,1,NULL),(4278,'Donor Alliance - organ recovery & acquisition','NONE','DC','2014-05-16 03:55:58','2014-05-16 03:55:58','system',0,1,NULL),(4287,'Drexel University Student Health','NONE','DC','2014-05-16 03:55:59','2014-05-16 03:55:59','system',0,1,NULL),(4289,'DRN (Diagnostic Radiology Network)','NONE','DC','2014-05-16 03:55:59','2014-05-16 03:55:59','system',0,1,NULL),(4293,'DU Sports Medicine','NONE','DC','2014-05-16 03:56:00','2014-05-16 03:56:00','system',0,1,NULL),(4304,'DYC Savio House Community','NONE','DC','2014-05-16 03:56:01','2014-05-16 03:56:01','system',0,1,NULL),(4315,'Ear Prof International Corp (EPIC) hearing aid','NONE','DC','2014-05-16 03:56:02','2014-05-16 03:56:02','system',0,1,NULL),(4327,'Elderplan','NONE','DC','2014-05-16 03:56:03','2014-05-16 03:56:03','system',0,1,NULL),(4337,'Emblem','NONE','DC','2014-05-16 03:56:04','2014-05-16 03:56:04','system',0,1,NULL),(4357,'Empire Blue Cross Blue Shield','NONE','DC','2014-05-16 03:56:06','2014-05-16 03:56:06','system',0,1,NULL),(4363,'Empire Plan','NONE','DC','2014-05-16 03:56:06','2014-05-16 03:56:06','system',0,1,NULL),(4368,'Encore Health Network','NONE','DC','2014-05-16 03:56:07','2014-05-16 03:56:07','system',0,1,NULL),(4369,'Erie Insurance Group','NONE','DC','2014-05-16 03:56:07','2014-05-16 03:56:07','system',0,1,NULL),(4372,'ESSENCE','NONE','DC','2014-05-16 03:56:07','2014-05-16 03:56:07','system',0,1,NULL),(4373,'Evercare','NONE','DC','2014-05-16 03:56:07','2014-05-16 03:56:07','system',0,1,NULL),(4379,'Everence Association Inc','NONE','DC','2014-05-16 03:56:08','2014-05-16 03:56:08','system',0,1,NULL),(4387,'Excellus Blue Cross Blue Shield','NONE','DC','2014-05-16 03:56:09','2014-05-16 03:56:09','system',0,1,NULL),(4391,'EyeMed','NONE','DC','2014-05-16 03:56:09','2014-05-16 03:56:09','system',0,1,NULL),(4398,'Fallon Community Health Plan (FCHP)','NONE','DC','2014-05-16 03:56:09','2014-05-16 03:56:09','system',0,1,NULL),(4407,'Family Planning Only Services','NONE','DC','2014-05-16 03:56:10','2014-05-16 03:56:10','system',0,1,NULL),(4410,'Fidelis Care','NONE','DC','2014-05-16 03:56:11','2014-05-16 03:56:11','system',0,1,NULL),(4415,'First Care','NONE','DC','2014-05-16 03:56:11','2014-05-16 03:56:11','system',0,1,NULL),(4420,'First Choice Health','NONE','DC','2014-05-16 03:56:11','2014-05-16 03:56:11','system',0,1,NULL),(4425,'First Choice of the Midwest','NONE','DC','2014-05-16 03:56:12','2014-05-16 03:56:12','system',0,1,NULL),(4433,'First Health (Coventry Health Care)','NONE','DC','2014-05-16 03:56:13','2014-05-16 03:56:13','system',0,1,NULL),(4437,'Fortified Provider Network','NONE','DC','2014-05-16 03:56:13','2014-05-16 03:56:13','system',0,1,NULL),(4440,'Freelancers Insurance Company (BlueCard PPO Network)','NONE','DC','2014-05-16 03:56:13','2014-05-16 03:56:13','system',0,1,NULL),(4443,'Galaxy Health','NONE','DC','2014-05-16 03:56:13','2014-05-16 03:56:13','system',0,1,NULL),(4447,'Gateway Health Plan','NONE','DC','2014-05-16 03:56:14','2014-05-16 03:56:14','system',0,1,NULL),(4453,'GEHA Health Plans','NONE','DC','2014-05-16 03:56:14','2014-05-16 03:56:14','system',0,1,NULL),(4458,'General Electric','NONE','DC','2014-05-16 03:56:15','2014-05-16 03:56:15','system',0,1,NULL),(4462,'GettingUScovered','NONE','DC','2014-05-16 03:56:15','2014-05-16 03:56:15','system',0,1,NULL),(4487,'GHI','NONE','DC','2014-05-16 03:56:17','2014-05-16 03:56:17','system',0,1,NULL),(4502,'Golden Rule','NONE','DC','2014-05-16 03:56:19','2014-05-16 03:56:19','system',0,1,NULL),(4506,'Great West Healthcare','NONE','DC','2014-05-16 03:56:19','2014-05-16 03:56:19','system',0,1,NULL),(4512,'GroupHealth','NONE','DC','2014-05-16 03:56:20','2014-05-16 03:56:20','system',0,1,NULL),(4517,'Guardian','NONE','DC','2014-05-16 03:56:20','2014-05-16 03:56:20','system',0,1,NULL),(4525,'Hanover Insurance','NONE','DC','2014-05-16 03:56:21','2014-05-16 03:56:21','system',0,1,NULL),(4533,'HAP (Alliance)','NONE','DC','2014-05-16 03:56:21','2014-05-16 03:56:21','system',0,1,NULL),(4536,'Harmony Health Plan of Illinois','NONE','DC','2014-05-16 03:56:22','2014-05-16 03:56:22','system',0,1,NULL),(4552,'Harvard Pilgrim Health Care','NONE','DC','2014-05-16 03:56:23','2014-05-16 03:56:23','system',0,1,NULL),(4559,'HCPC','NONE','DC','2014-05-16 03:56:24','2014-05-16 03:56:24','system',0,1,NULL),(4564,'Health America/Health Assurance','NONE','DC','2014-05-16 03:56:24','2014-05-16 03:56:24','system',0,1,NULL),(4567,'Health Care Payers Coalition of NJ','NONE','DC','2014-05-16 03:56:24','2014-05-16 03:56:24','system',0,1,NULL),(4569,'Health Choice','NONE','DC','2014-05-16 03:56:24','2014-05-16 03:56:24','system',0,1,NULL),(4575,'Health InfoNet (HIN)','NONE','DC','2014-05-16 03:56:25','2014-05-16 03:56:25','system',0,1,NULL),(4603,'Health Net','NONE','DC','2014-05-16 03:56:27','2014-05-16 03:56:27','system',0,1,NULL),(4624,'Health New England','NONE','DC','2014-05-16 03:56:29','2014-05-16 03:56:29','system',0,1,NULL),(4628,'Health Partners','NONE','DC','2014-05-16 03:56:30','2014-05-16 03:56:30','system',0,1,NULL),(4637,'Health Right','NONE','DC','2014-05-16 03:56:30','2014-05-16 03:56:30','system',0,1,NULL),(4643,'HealthChoice','NONE','DC','2014-05-16 03:56:31','2014-05-16 03:56:31','system',0,1,NULL),(4654,'HealthEOS','NONE','DC','2014-05-16 03:56:32','2014-05-16 03:56:32','system',0,1,NULL),(4664,'Healthfirst','NONE','DC','2014-05-16 03:56:33','2014-05-16 03:56:33','system',0,1,NULL),(4674,'Healthlink','NONE','DC','2014-05-16 03:56:34','2014-05-16 03:56:34','system',0,1,NULL),(4680,'Healthmarkets Inc.','NONE','DC','2014-05-16 03:56:34','2014-05-16 03:56:34','system',0,1,NULL),(4685,'HealthPlus','NONE','DC','2014-05-16 03:56:35','2014-05-16 03:56:35','system',0,1,NULL),(4695,'HealthSmart','NONE','DC','2014-05-16 03:56:36','2014-05-16 03:56:36','system',0,1,NULL),(4710,'Healthspring','NONE','DC','2014-05-16 03:56:37','2014-05-16 03:56:37','system',0,1,NULL),(4728,'Healthy Families','NONE','DC','2014-05-16 03:56:39','2014-05-16 03:56:39','system',0,1,NULL),(4742,'HFN','NONE','DC','2014-05-16 03:56:40','2014-05-16 03:56:40','system',0,1,NULL),(4748,'HFS Medical Benefits','NONE','DC','2014-05-16 03:56:40','2014-05-16 03:56:40','system',0,1,NULL),(4755,'HighMark Blue Cross Blue Shield','NONE','DC','2014-05-16 03:56:41','2014-05-16 03:56:41','system',0,1,NULL),(4763,'HighMark Blue Shield','NONE','DC','2014-05-16 03:56:42','2014-05-16 03:56:42','system',0,1,NULL),(4775,'Hill Physicians','NONE','DC','2014-05-16 03:56:43','2014-05-16 03:56:43','system',0,1,NULL),(4806,'HIP','NONE','DC','2014-05-16 03:56:45','2014-05-16 03:56:45','system',0,1,NULL),(4819,'Horizon Blue Cross Blue Shield','NONE','DC','2014-05-16 03:56:47','2014-05-16 03:56:47','system',0,1,NULL),(4827,'Horizon NJ Health','NONE','DC','2014-05-16 03:56:47','2014-05-16 03:56:47','system',0,1,NULL),(4834,'Hudson Health Plus','NONE','DC','2014-05-16 03:56:48','2014-05-16 03:56:48','system',0,1,NULL),(4866,'Humana Health','NONE','DC','2014-05-16 03:56:51','2014-05-16 03:56:51','system',0,1,NULL),(4891,'Humana National Transplant Network','NONE','DC','2014-05-16 03:56:53','2014-05-16 03:56:53','system',0,1,NULL),(4899,'Illinicare','NONE','DC','2014-05-16 03:56:54','2014-05-16 03:56:54','system',0,1,NULL),(4902,'Illinois Workers Compensation Commission','NONE','DC','2014-05-16 03:56:54','2014-05-16 03:56:54','system',0,1,NULL),(4904,'IMO (Independent Medical Systems)','NONE','DC','2014-05-16 03:56:54','2014-05-16 03:56:54','system',0,1,NULL),(4906,'Independence Blue Cross Blue Shield','NONE','DC','2014-05-16 03:56:54','2014-05-16 03:56:54','system',0,1,NULL),(4915,'Indiana Health Network (IHN)','NONE','DC','2014-05-16 03:56:55','2014-05-16 03:56:55','system',0,1,NULL),(4918,'Ingalls Provider Group (IPG)','NONE','DC','2014-05-16 03:56:55','2014-05-16 03:56:55','system',0,1,NULL),(4919,'Integrated','NONE','DC','2014-05-16 03:56:55','2014-05-16 03:56:55','system',0,1,NULL),(4921,'Integrated Behavioral Health(IBH)','NONE','DC','2014-05-16 03:56:56','2014-05-16 03:56:56','system',0,1,NULL),(4923,'Intergroup','NONE','DC','2014-05-16 03:56:56','2014-05-16 03:56:56','system',0,1,NULL),(4928,'Interlink','NONE','DC','2014-05-16 03:56:56','2014-05-16 03:56:56','system',0,1,NULL),(4936,'International Medical Group (IMG)','NONE','DC','2014-05-16 03:56:57','2014-05-16 03:56:57','system',0,1,NULL),(4948,'JMH Health Plan','NONE','DC','2014-05-16 03:56:58','2014-05-16 03:56:58','system',0,1,NULL),(4956,'Johns Hopkins Employer Health Programs','NONE','DC','2014-05-16 03:56:58','2014-05-16 03:56:58','system',0,1,NULL),(4967,'Kaiser Permanente','NONE','DC','2014-05-16 03:56:59','2014-05-16 03:56:59','system',0,1,NULL),(4981,'Kaiser Permanente National Transplant','NONE','DC','2014-05-16 03:57:01','2014-05-16 03:57:01','system',0,1,NULL),(4989,'Keystone Mercy Health Plan','NONE','DC','2014-05-16 03:57:02','2014-05-16 03:57:02','system',0,1,NULL),(4991,'Liberty Health Advantage','NONE','DC','2014-05-16 03:57:02','2014-05-16 03:57:02','system',0,1,NULL),(4993,'Liberty Mutual','NONE','DC','2014-05-16 03:57:02','2014-05-16 03:57:02','system',0,1,NULL),(4998,'LifeTrac Transplant Network','NONE','DC','2014-05-16 03:57:03','2014-05-16 03:57:03','system',0,1,NULL),(5006,'LifeWise','NONE','DC','2014-05-16 03:57:03','2014-05-16 03:57:03','system',0,1,NULL),(5011,'Lutheran Preferred','NONE','DC','2014-05-16 03:57:04','2014-05-16 03:57:04','system',0,1,NULL),(5013,'M.D. IPA','NONE','DC','2014-05-16 03:57:04','2014-05-16 03:57:04','system',0,1,NULL),(5025,'Magellan Behavioral Health (OP)','NONE','DC','2014-05-16 03:57:05','2014-05-16 03:57:05','system',0,1,NULL),(5034,'MagnaCare','NONE','DC','2014-05-16 03:57:06','2014-05-16 03:57:06','system',0,1,NULL),(5037,'Mail Handlers Benefit Plan','NONE','DC','2014-05-16 03:57:06','2014-05-16 03:57:06','system',0,1,NULL),(5041,'Mamsi','NONE','DC','2014-05-16 03:57:06','2014-05-16 03:57:06','system',0,1,NULL),(5056,'Managed Health Network (MHN)','NONE','DC','2014-05-16 03:57:08','2014-05-16 03:57:08','system',0,1,NULL),(5058,'Maricopa Health Plan','NONE','DC','2014-05-16 03:57:08','2014-05-16 03:57:08','system',0,1,NULL),(5072,'Maryland Medical Assistance (Medicaid)','NONE','DC','2014-05-16 03:57:09','2014-05-16 03:57:09','system',0,1,NULL),(5077,'Maryland Physicians Care','NONE','DC','2014-05-16 03:57:09','2014-05-16 03:57:09','system',0,1,NULL),(5080,'Maryland Workers Compensation','NONE','DC','2014-05-16 03:57:10','2014-05-16 03:57:10','system',0,1,NULL),(5082,'MedCorp Southwest','NONE','DC','2014-05-16 03:57:10','2014-05-16 03:57:10','system',0,1,NULL),(5084,'Medi-Cal','NONE','DC','2014-05-16 03:57:10','2014-05-16 03:57:10','system',0,1,NULL),(5086,'Medica HealthCare Plans','NONE','DC','2014-05-16 03:57:10','2014-05-16 03:57:10','system',0,1,NULL),(5090,'Medicaid','NONE','DC','2014-05-16 03:57:11','2014-05-16 03:57:11','system',0,1,NULL),(5092,'Medicaid Medi-Cal Dual Eligible','NONE','DC','2014-05-16 03:57:11','2014-05-16 03:57:11','system',0,1,NULL),(5094,'Medicaid Medicare Dual Eligible','NONE','DC','2014-05-16 03:57:11','2014-05-16 03:57:11','system',0,1,NULL),(5099,'Medical Network Colorado Springs (MNCS)','NONE','DC','2014-05-16 03:57:11','2014-05-16 03:57:11','system',0,1,NULL),(5107,'Medicare','NONE','DC','2014-05-16 03:57:12','2014-05-16 03:57:12','system',0,1,NULL),(5109,'MedX','NONE','DC','2014-05-16 03:57:12','2014-05-16 03:57:12','system',0,1,NULL),(5111,'Merchants Insurance Group','NONE','DC','2014-05-16 03:57:12','2014-05-16 03:57:12','system',0,1,NULL),(5113,'Mercy Care','NONE','DC','2014-05-16 03:57:13','2014-05-16 03:57:13','system',0,1,NULL),(5118,'Metro Plus','NONE','DC','2014-05-16 03:57:13','2014-05-16 03:57:13','system',0,1,NULL),(5125,'Midlands Choice','NONE','DC','2014-05-16 03:57:14','2014-05-16 03:57:14','system',0,1,NULL),(5153,'Mills-Peninsula Medical Group','NONE','DC','2014-05-16 03:57:16','2014-05-16 03:57:16','system',0,1,NULL),(5164,'Mines & Associates Behavioral Health (OP)','NONE','DC','2014-05-16 03:57:17','2014-05-16 03:57:17','system',0,1,NULL),(5172,'Missouri Employers Mutual Insurance','NONE','DC','2014-05-16 03:57:18','2014-05-16 03:57:18','system',0,1,NULL),(5174,'Molina Healthcare','NONE','DC','2014-05-16 03:57:18','2014-05-16 03:57:18','system',0,1,NULL),(5178,'Motion Picture Industry Health Plan','NONE','DC','2014-05-16 03:57:19','2014-05-16 03:57:19','system',0,1,NULL),(5182,'MultiPlan','NONE','DC','2014-05-16 03:57:19','2014-05-16 03:57:19','system',0,1,NULL),(5188,'Mutual of Omaha','NONE','DC','2014-05-16 03:57:19','2014-05-16 03:57:19','system',0,1,NULL),(5197,'MVP','NONE','DC','2014-05-16 03:57:20','2014-05-16 03:57:20','system',0,1,NULL),(5199,'National Capital','NONE','DC','2014-05-16 03:57:20','2014-05-16 03:57:20','system',0,1,NULL),(5204,'National Preferred Provider Network (NPPN)','NONE','DC','2014-05-16 03:57:21','2014-05-16 03:57:21','system',0,1,NULL),(5214,'National Vision Administrators','NONE','DC','2014-05-16 03:57:22','2014-05-16 03:57:22','system',0,1,NULL),(5215,'Nationwide','NONE','DC','2014-05-16 03:57:22','2014-05-16 03:57:22','system',0,1,NULL),(5224,'NCAS','NONE','DC','2014-05-16 03:57:23','2014-05-16 03:57:23','system',0,1,NULL),(5237,'Neighborhood Health Plan','NONE','DC','2014-05-16 03:57:24','2014-05-16 03:57:24','system',0,1,NULL),(5244,'Neighborhood Health Providers','NONE','DC','2014-05-16 03:57:24','2014-05-16 03:57:24','system',0,1,NULL),(5248,'Network Health','NONE','DC','2014-05-16 03:57:25','2014-05-16 03:57:25','system',0,1,NULL),(5253,'NewYork-Presbyterian Community Health Plan','NONE','DC','2014-05-16 03:57:25','2014-05-16 03:57:25','system',0,1,NULL),(5255,'NJ Americhoice','NONE','DC','2014-05-16 03:57:25','2014-05-16 03:57:25','system',0,1,NULL),(5261,'NJ Amerihealth','NONE','DC','2014-05-16 03:57:26','2014-05-16 03:57:26','system',0,1,NULL),(5264,'NJ Carpenters','NONE','DC','2014-05-16 03:57:26','2014-05-16 03:57:26','system',0,1,NULL),(5266,'NJ Medicaid','NONE','DC','2014-05-16 03:57:26','2014-05-16 03:57:26','system',0,1,'NJMCD'),(5268,'NJ Medicare','NONE','DC','2014-05-16 03:57:26','2014-05-16 03:57:26','system',0,1,'00431'),(5270,'NovaNet','NONE','DC','2014-05-16 03:57:27','2014-05-16 03:57:27','system',0,1,NULL),(5273,'NY State No-Fault','NONE','DC','2014-05-16 03:57:27','2014-05-16 03:57:27','system',0,1,NULL),(5276,'NY State Workers Compensation Board','NONE','DC','2014-05-16 03:57:27','2014-05-16 03:57:27','system',0,1,NULL),(5278,'OneNet PPO LLC','NONE','DC','2014-05-16 03:57:27','2014-05-16 03:57:27','system',0,1,NULL),(5281,'Operating Engineers','NONE','DC','2014-05-16 03:57:28','2014-05-16 03:57:28','system',0,1,NULL),(5288,'OptimaHealth','NONE','DC','2014-05-16 03:57:28','2014-05-16 03:57:28','system',0,1,NULL),(5294,'Optimum Choice','NONE','DC','2014-05-16 03:57:29','2014-05-16 03:57:29','system',0,1,'52152'),(5295,'Optum Health','NONE','DC','2014-05-16 03:57:29','2014-05-16 03:57:29','system',0,1,'41194'),(5312,'Oxford Health Plans','NONE','DC','2014-05-16 03:57:31','2014-05-16 03:57:31','system',0,1,NULL),(5325,'Pacificare','NONE','DC','2014-05-16 03:57:33','2014-05-16 03:57:33','system',0,1,NULL),(5332,'Palmetto GBA','NONE','DC','2014-05-16 03:57:33','2014-05-16 03:57:33','system',0,1,NULL),(5337,'Park Care Plus','NONE','DC','2014-05-16 03:57:34','2014-05-16 03:57:34','system',0,1,NULL),(5348,'Parkland Community Health Plan','NONE','DC','2014-05-16 03:57:35','2014-05-16 03:57:35','system',0,1,NULL),(5351,'Peach State Health Plan','NONE','DC','2014-05-16 03:57:35','2014-05-16 03:57:35','system',0,1,NULL),(5355,'PeachCare for Kids','NONE','DC','2014-05-16 03:57:35','2014-05-16 03:57:35','system',0,1,NULL),(5357,'Penn National Insurance','NONE','DC','2014-05-16 03:57:35','2014-05-16 03:57:35','system',0,1,NULL),(5360,'Perfect Health','NONE','DC','2014-05-16 03:57:36','2014-05-16 03:57:36','system',0,1,NULL),(5369,'PersonalCare Insurance of Illinois Inc','NONE','DC','2014-05-16 03:57:36','2014-05-16 03:57:36','system',0,1,NULL),(5383,'PHCS','NONE','DC','2014-05-16 03:57:38','2014-05-16 03:57:38','system',0,1,NULL),(5388,'Phoenix Health Plan','NONE','DC','2014-05-16 03:57:38','2014-05-16 03:57:38','system',0,1,NULL),(5393,'Physician Health Partners - DOC','NONE','DC','2014-05-16 03:57:39','2014-05-16 03:57:39','system',0,1,NULL),(5402,'Physicians Medical Group of San Jose','NONE','DC','2014-05-16 03:57:39','2014-05-16 03:57:39','system',0,1,NULL),(5410,'Pinnacol Assurance','NONE','DC','2014-05-16 03:57:40','2014-05-16 03:57:40','system',0,1,NULL),(5425,'POMCO','NONE','DC','2014-05-16 03:57:41','2014-05-16 03:57:41','system',0,1,NULL),(5428,'Preferred Network Access (PNA)','NONE','DC','2014-05-16 03:57:42','2014-05-16 03:57:42','system',0,1,NULL),(5437,'Premera Blue Cross','NONE','DC','2014-05-16 03:57:42','2014-05-16 03:57:42','system',0,1,NULL),(5445,'Presbyterian Health Plan/Presbyterian Insurance Company','NONE','DC','2014-05-16 03:57:43','2014-05-16 03:57:43','system',0,1,NULL),(5459,'Prevea Health Network (PHN)','NONE','DC','2014-05-16 03:57:44','2014-05-16 03:57:44','system',0,1,NULL),(5464,'Principal Financial Group','NONE','DC','2014-05-16 03:57:45','2014-05-16 03:57:45','system',0,1,'00143'),(5465,'Priority Partners','NONE','DC','2014-05-16 03:57:45','2014-05-16 03:57:45','system',0,1,NULL),(5469,'Providence','NONE','DC','2014-05-16 03:57:45','2014-05-16 03:57:45','system',0,1,NULL),(5487,'Public Aid (Illinois Medicaid)','NONE','DC','2014-05-16 03:57:47','2014-05-16 03:57:47','system',0,1,NULL),(5490,'Pyramid','NONE','DC','2014-05-16 03:57:47','2014-05-16 03:57:47','system',0,1,NULL),(5493,'QualCare','NONE','DC','2014-05-16 03:57:47','2014-05-16 03:57:47','system',0,1,NULL),(5495,'Railroad Medicare','NONE','DC','2014-05-16 03:57:48','2014-05-16 03:57:48','system',0,1,'00431'),(5504,'Ravenswood Physicians Associates (RPA)','NONE','DC','2014-05-16 03:57:48','2014-05-16 03:57:48','system',0,1,NULL),(5513,'Regence Blue Shield','NONE','DC','2014-05-16 03:57:49','2014-05-16 03:57:49','system',0,1,NULL),(5527,'Rocky Mountain Health Plan','NONE','DC','2014-05-16 03:57:51','2014-05-16 03:57:51','system',0,1,NULL),(5538,'Rose Medical Center Limited Svcs. Agmt.','NONE','DC','2014-05-16 03:57:52','2014-05-16 03:57:52','system',0,1,NULL),(5547,'SafeGuard','NONE','DC','2014-05-16 03:57:52','2014-05-16 03:57:52','system',0,1,NULL),(5549,'Sagamore Health Network','NONE','DC','2014-05-16 03:57:52','2014-05-16 03:57:52','system',0,1,NULL),(5555,'San Francisco Health Plan','NONE','DC','2014-05-16 03:57:53','2014-05-16 03:57:53','system',0,1,NULL),(5575,'Santa Clara County IPA (SCCIPA)','NONE','DC','2014-05-16 03:57:55','2014-05-16 03:57:55','system',0,1,NULL),(5587,'Saudi Medical Office','NONE','DC','2014-05-16 03:57:56','2014-05-16 03:57:56','system',0,1,NULL),(5598,'SCAN Health Plan','NONE','DC','2014-05-16 03:57:57','2014-05-16 03:57:57','system',0,1,NULL),(5607,'Scott & White Health Plan','NONE','DC','2014-05-16 03:57:58','2014-05-16 03:57:58','system',0,1,NULL),(5611,'Secure Horizons','NONE','DC','2014-05-16 03:57:58','2014-05-16 03:57:58','system',0,1,NULL),(5613,'Security Health Plan','NONE','DC','2014-05-16 03:57:58','2014-05-16 03:57:58','system',0,1,NULL),(5619,'Select Health Network','NONE','DC','2014-05-16 03:57:59','2014-05-16 03:57:59','system',0,1,NULL),(5629,'SelectHealth','NONE','DC','2014-05-16 03:58:00','2014-05-16 03:58:00','system',0,1,NULL),(5642,'Senior Answers & Services (SAS) Hearing Aid Program','NONE','DC','2014-05-16 03:58:01','2014-05-16 03:58:01','system',0,1,NULL),(5650,'Significa','NONE','DC','2014-05-16 03:58:02','2014-05-16 03:58:02','system',0,1,NULL),(5656,'Special Select Student BH','NONE','DC','2014-05-16 03:58:03','2014-05-16 03:58:03','system',0,1,NULL),(5664,'Spectera','NONE','DC','2014-05-16 03:58:03','2014-05-16 03:58:03','system',0,1,NULL),(5666,'State Accident Insurance Fund Corporation (SAIF)','NONE','DC','2014-05-16 03:58:04','2014-05-16 03:58:04','system',0,1,NULL),(5668,'State Farm','NONE','DC','2014-05-16 03:58:04','2014-05-16 03:58:04','system',0,1,'CSSHP'),(5670,'State Farm','NONE','DC','2014-05-16 03:58:04','2014-05-16 03:58:04','system',0,1,'CSSHP'),(5674,'State Insurance (Wisconsin)','NONE','DC','2014-05-16 03:58:04','2014-05-16 03:58:04','system',0,1,NULL),(5681,'State of Colorado-Air National Guard Mammo','NONE','DC','2014-05-16 03:58:05','2014-05-16 03:58:05','system',0,1,NULL),(5690,'SummaCare','NONE','DC','2014-05-16 03:58:06','2014-05-16 03:58:06','system',0,1,NULL),(5697,'Superior HealthPlan','NONE','DC','2014-05-16 03:58:06','2014-05-16 03:58:06','system',0,1,NULL),(5702,'Superior Vision','NONE','DC','2014-05-16 03:58:07','2014-05-16 03:58:07','system',0,1,NULL),(5704,'SuperMed PPO','NONE','DC','2014-05-16 03:58:07','2014-05-16 03:58:07','system',0,1,NULL),(5708,'SutterSelect','NONE','DC','2014-05-16 03:58:07','2014-05-16 03:58:07','system',0,1,NULL),(5712,'TexanPlus','NONE','DC','2014-05-16 03:58:08','2014-05-16 03:58:08','system',0,1,NULL),(5716,'Texas Children?s Health Plan','NONE','DC','2014-05-16 03:58:08','2014-05-16 03:58:08','system',0,1,NULL),(5718,'Texas Community Care','NONE','DC','2014-05-16 03:58:08','2014-05-16 03:58:08','system',0,1,NULL),(5723,'Texas TrueChoice','NONE','DC','2014-05-16 03:58:08','2014-05-16 03:58:08','system',0,1,NULL),(5735,'The Hartford','NONE','DC','2014-05-16 03:58:09','2014-05-16 03:58:09','system',0,1,NULL),(5738,'Touchstone','NONE','DC','2014-05-16 03:58:10','2014-05-16 03:58:10','system',0,1,NULL),(5744,'Travelers','NONE','DC','2014-05-16 03:58:10','2014-05-16 03:58:10','system',0,1,NULL),(5747,'Tricare','NONE','DC','2014-05-16 03:58:11','2014-05-16 03:58:11','system',0,1,'00080'),(5763,'Trilogy Health','NONE','DC','2014-05-16 03:58:12','2014-05-16 03:58:12','system',0,1,NULL),(5764,'TrustMark','NONE','DC','2014-05-16 03:58:12','2014-05-16 03:58:12','system',0,1,NULL),(5781,'Tufts Associated Health Plans Inc.','NONE','DC','2014-05-16 03:58:13','2014-05-16 03:58:13','system',0,1,NULL),(5788,'UniCare','NONE','DC','2014-05-16 03:58:14','2014-05-16 03:58:14','system',0,1,NULL),(5812,'Uniform Medical Plan','NONE','DC','2014-05-16 03:58:16','2014-05-16 03:58:16','system',0,1,NULL),(5814,'Uniformed Services Family Health Plan','NONE','DC','2014-05-16 03:58:16','2014-05-16 03:58:16','system',0,1,NULL),(5823,'Union','NONE','DC','2014-05-16 03:58:17','2014-05-16 03:58:17','system',0,1,NULL),(5828,'Union Health Services Inc','NONE','DC','2014-05-16 03:58:18','2014-05-16 03:58:18','system',0,1,NULL),(5829,'Unison Health Plan','NONE','DC','2014-05-16 03:58:18','2014-05-16 03:58:18','system',0,1,NULL),(5832,'United Behavioral Health','NONE','DC','2014-05-16 03:58:18','2014-05-16 03:58:18','system',0,1,NULL),(5837,'United Medical Alliance','NONE','DC','2014-05-16 03:58:18','2014-05-16 03:58:18','system',0,1,NULL),(5848,'United Military and Veterans','NONE','DC','2014-05-16 03:58:19','2014-05-16 03:58:19','system',0,1,NULL),(5859,'United Student Resources (aka megalife)','NONE','DC','2014-05-16 03:58:20','2014-05-16 03:58:20','system',0,1,NULL),(5873,'UnitedHealthcare','NONE','DC','2014-05-16 03:58:21','2014-05-16 03:58:21','system',0,1,'00112'),(5904,'UnitedHealthcare Community Plan','NONE','DC','2014-05-16 03:58:24','2014-05-16 03:58:24','system',0,1,NULL),(5910,'Unitrin','NONE','DC','2014-05-16 03:58:25','2014-05-16 03:58:25','system',0,1,NULL),(5913,'Unity Health Insurance','NONE','DC','2014-05-16 03:58:25','2014-05-16 03:58:25','system',0,1,NULL),(5928,'Univera','NONE','DC','2014-05-16 03:58:26','2014-05-16 03:58:26','system',0,1,NULL),(5945,'Universal American','NONE','DC','2014-05-16 03:58:28','2014-05-16 03:58:28','system',0,1,NULL),(5950,'Universal Healthcare','NONE','DC','2014-05-16 03:58:28','2014-05-16 03:58:28','system',0,1,NULL),(5954,'University Family Care','NONE','DC','2014-05-16 03:58:28','2014-05-16 03:58:28','system',0,1,NULL),(5959,'University of Chicago Health Plan','NONE','DC','2014-05-16 03:58:29','2014-05-16 03:58:29','system',0,1,NULL),(5964,'University Physicians Healthcare Group','NONE','DC','2014-05-16 03:58:29','2014-05-16 03:58:29','system',0,1,NULL),(5970,'US - Dept of Interior - Geological Survey','NONE','DC','2014-05-16 03:58:30','2014-05-16 03:58:30','system',0,1,NULL),(5978,'US Family Plan','NONE','DC','2014-05-16 03:58:31','2014-05-16 03:58:31','system',0,1,NULL),(5980,'USA Managed Care','NONE','DC','2014-05-16 03:58:31','2014-05-16 03:58:31','system',0,1,NULL),(5986,'VA- Radiation Therapy','NONE','DC','2014-05-16 03:58:31','2014-05-16 03:58:31','system',0,1,NULL),(5996,'ValueOptions','NONE','DC','2014-05-16 03:58:32','2014-05-16 03:58:32','system',0,1,NULL),(5998,'Viant','NONE','DC','2014-05-16 03:58:32','2014-05-16 03:58:32','system',0,1,NULL),(6001,'Virginia Health Network','NONE','DC','2014-05-16 03:58:33','2014-05-16 03:58:33','system',0,1,NULL),(6008,'Virginia Premier Health Plan','NONE','DC','2014-05-16 03:58:33','2014-05-16 03:58:33','system',0,1,NULL),(6012,'Virginia Workers Compensation Commission','NONE','DC','2014-05-16 03:58:33','2014-05-16 03:58:33','system',0,1,NULL),(6014,'Vision Benefits of America','NONE','DC','2014-05-16 03:58:34','2014-05-16 03:58:34','system',0,1,NULL),(6017,'Vista','NONE','DC','2014-05-16 03:58:34','2014-05-16 03:58:34','system',0,1,NULL),(6022,'VNS Choice Select','NONE','DC','2014-05-16 03:58:34','2014-05-16 03:58:34','system',0,1,NULL),(6028,'Vocational Rehab','NONE','DC','2014-05-16 03:58:35','2014-05-16 03:58:35','system',0,1,NULL),(6036,'VSP','NONE','DC','2014-05-16 03:58:36','2014-05-16 03:58:36','system',0,1,NULL),(6042,'Vytra','NONE','DC','2014-05-16 03:58:36','2014-05-16 03:58:36','system',0,1,NULL),(6044,'WEA Trust Health Plan','NONE','DC','2014-05-16 03:58:36','2014-05-16 03:58:36','system',0,1,NULL),(6065,'Wellcare','NONE','DC','2014-05-16 03:58:38','2014-05-16 03:58:38','system',0,1,NULL),(6087,'Wellchoice','NONE','DC','2014-05-16 03:58:40','2014-05-16 03:58:40','system',0,1,NULL),(6089,'Wells Fargo (Acordia)','NONE','DC','2014-05-16 03:58:40','2014-05-16 03:58:40','system',0,1,NULL),(6103,'Western Health Advantage','NONE','DC','2014-05-16 03:58:41','2014-05-16 03:58:41','system',0,1,NULL),(6127,'WinHealth Partners','NONE','DC','2014-05-16 03:58:43','2014-05-16 03:58:43','system',0,1,NULL),(6144,'Workers Compensation','NONE','DC','2014-05-16 03:58:45','2014-05-16 03:58:45','system',0,1,NULL),(6162,'Zenith','NONE','DC','2014-05-16 03:58:46','2014-05-16 03:58:46','system',0,1,NULL),(6165,'Zurich','NONE','DC','2014-05-16 03:58:47','2014-05-16 03:58:47','system',0,1,NULL);
/*!40000 ALTER TABLE `insurance_lk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `locationId` int(13) NOT NULL AUTO_INCREMENT,
  `provOrgId` int(13) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') COLLATE utf8_unicode_ci NOT NULL,
  `locBusName` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `website` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(75) COLLATE utf8_unicode_ci DEFAULT NULL,
  `copyEmailToAdmin` tinyint(3) unsigned DEFAULT '0',
  `timeZone` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`locationId`),
  UNIQUE KEY `ux_locName` (`locBusName`,`provOrgId`),
  KEY `provOrgKey` (`provOrgId`),
  CONSTRAINT `location_ibfk_1` FOREIGN KEY (`provOrgId`) REFERENCES `provorg` (`provOrgId`)
) ENGINE=InnoDB AUTO_INCREMENT=1258 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1250,1231,'ACTIVE','Amit\'s Number One radilogy 1','http://www.test.com','2014-08-09 21:28:04','2014-08-09 21:32:02','user1@gmail.com',0,''),(1252,1231,'ACTIVE','Amit\'s Number One radilogy 2','http://www.test.com','2014-08-09 21:29:30','2014-08-09 21:29:30','user1@gmail.com',0,''),(1253,1231,'ACTIVE','Amit\'s Number One radilogy 3','http://www.test.com','2014-08-11 22:53:15','2014-08-11 22:53:15','user1@gmail.com',0,''),(1254,1236,'ACTIVE','Westchester Health Armonk','','2014-08-28 19:45:11','2014-08-28 19:45:11','west@gmail.com',0,NULL),(1257,1236,'ACTIVE','Westchester Health Brewster','','2014-08-28 20:04:20','2014-08-28 20:04:20','west@gmail.com',0,NULL);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locationcontact`
--

DROP TABLE IF EXISTS `locationcontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locationcontact` (
  `locationContactId` int(13) NOT NULL AUTO_INCREMENT,
  `provOrgId` int(13) NOT NULL,
  `locationId` int(13) NOT NULL,
  `contactType` enum('Primary','Secondary_01','Secondary_02') COLLATE utf8_unicode_ci NOT NULL,
  `firstName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `lastName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `middleName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emailAddress` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phoneNumber` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(75) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`locationContactId`),
  KEY `locationContact_ibfk_1` (`provOrgId`),
  KEY `locationContact_ibfk_2` (`locationId`),
  CONSTRAINT `locationContact_ibfk_1` FOREIGN KEY (`provOrgId`) REFERENCES `provorg` (`provOrgId`),
  CONSTRAINT `locationContact_ibfk_2` FOREIGN KEY (`locationId`) REFERENCES `location` (`locationId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locationcontact`
--

LOCK TABLES `locationcontact` WRITE;
/*!40000 ALTER TABLE `locationcontact` DISABLE KEYS */;
INSERT INTO `locationcontact` VALUES (4,1231,1250,'Primary','Sonya','Soriano','','scanmaster@gatewayradiology.com','mr','(727) 525-2121','2014-08-11 22:56:25','2014-08-11 22:56:25','user1@gmail.com'),(5,1231,1250,'Secondary_01','Gagan','Mangat','','gmangat@gatewayradiology.com','mr','(727) 512-7802','2014-08-11 22:56:25','2014-08-11 22:56:25','user1@gmail.com'),(6,1231,1250,'Secondary_02','','','','',NULL,'','2014-08-11 22:56:25','2014-08-11 22:56:25','user1@gmail.com'),(7,1236,1254,'Primary','Donald','Cohen','','','mr','(203) 355-9879','2014-08-28 19:46:41','2014-08-28 19:46:41','west@gmail.com'),(8,1236,1254,'Secondary_01','','','','',NULL,'','2014-08-28 19:46:41','2014-08-28 19:46:41','west@gmail.com'),(9,1236,1254,'Secondary_02','','','','',NULL,'','2014-08-28 19:46:41','2014-08-28 19:46:41','west@gmail.com');
/*!40000 ALTER TABLE `locationcontact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locmsg`
--

DROP TABLE IF EXISTS `locmsg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locmsg` (
  `locMsgId` int(13) NOT NULL AUTO_INCREMENT,
  `locationId` int(13) DEFAULT NULL,
  `provOrgId` int(13) NOT NULL,
  `msgType` varchar(200) NOT NULL,
  `msgFor` varchar(100) DEFAULT NULL,
  `apptReqDate` date DEFAULT NULL,
  `status` enum('NEW','FAXED','COMPLETED','CALLED_ONCE','CALLED_TWICE','CALLED_THRICE','ARCHIVE','ALL_DOCS_RCVD','PARTIAL_DOCS_RCVD','SPECIAL_CASE') DEFAULT NULL,
  `msgCode` varchar(10) NOT NULL,
  `fromType` enum('DOCOFF','PATIENT') DEFAULT NULL,
  `appStartDateTime` datetime DEFAULT NULL,
  `appEndDateTime` datetime DEFAULT NULL,
  `assignedTo` varchar(255) DEFAULT NULL,
  `locMsgContent` blob NOT NULL,
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(255) NOT NULL,
  `newPatient` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`locMsgId`),
  UNIQUE KEY `locMsgUK1` (`msgCode`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locmsg`
--

LOCK TABLES `locmsg` WRITE;
/*!40000 ALTER TABLE `locmsg` DISABLE KEYS */;
INSERT INTO `locmsg` VALUES (7,1250,1231,'APPT',NULL,NULL,'FAXED','A350EB24C3',NULL,'2014-08-22 06:45:00','2014-08-22 07:45:00','test','x�G�C@\0\0�Y�0$��Q�Qw���N�߳�����pVI��ذ�]�K��#��ƚY�9��N3�9#���Ҩ�yׁN�\Z�9�`\"�\n������<Z��-��Pr�#/↞�6��7�<<z��`ΕBޑ�k�8`��ﾷ��]X�3	�tjg�؁~��9���iO{�[L��>���N��Q��xɓ���e�ጚ3����ܖ���i�:���\r���n�Q8�� Ux����WR�Ű�:�ʹ\\J��N�B,��+�A\r�5���I���i*\0Ù�`��f�h��]i�=��\\�8E��-��:�B�Fj�V�x# �߉S��\'2K��gB��,C4ͽ�]��ڝJ��_]�_��ˠNR�����7��y�Aw�ltc.1N��\\#g2�X����󍗒�Pp!��zx5�|��q]od�+��|�:A-��%�@��=�\\5�F:��ˉ4V%p���5�3��MU�boG]�J��-\Z�ZzH?�3\ns��P��\0�N.n˺)	�j��d1��ƍS<;���N��p','2014-08-15 20:41:59','2014-08-22 22:13:01','Administrator [user1@gmail.com]',0),(8,0,1231,'APPT',NULL,NULL,'NEW','4B96591DE5',NULL,NULL,NULL,NULL,'x\r�I�0\0\0�q\0ŀz�WYo�$� �׷�	�]M��e���d�5hŵ����ʥ��d���ю��ǈ�`��^�*��6�ۋ$�?���$\\�!|\"C�\Z+�1֓	�!}m+��\Z����r����΍`�%8?F.2iʰA�{8v}a��8��`q؝�z؈(x���S:��5u�BC��h���<�Ԕ����_���z��U7���~�	�[�����7�&p��ȗ,���r�{$V�J�mN���\\,c�E\"�F�����+L��HL3�MZ.��=�:�o�v�0�O�1\".��|��ѱ�)*�ms0V��r-����4	�|L���ٖT�JD/�ĭ}U{jG�?�\0,��','2014-08-17 21:22:40','2014-08-17 21:22:40','system',0),(9,0,1231,'APPT',NULL,NULL,'NEW','BD9856E38A',NULL,NULL,NULL,NULL,'x\r�I�0\0\0�q\0ŀz�WYo�$� �׷�	�]M��e���d�5hŵ����ʥ��d���ю��ǈ�`��^�*��6�ۋ$�?���$\\�!|\"C�\Z+�1֓	�!}m+��\Z����r����΍`�%8?F.2iʰA�{8v}a��8��`q؝�z؈(x���S:��5u�BC��h���<�Ԕ����_���z��U7���~�	�[�����7�&p��ȗ,���r�{$V�J�mN���\\,c�E\"�F�����+L��HL3�MZ.��=�:�o�v�0�O�1\".��|��ѱ�)*�ms0V��r-����4	�|L���ٖT�JD/�ĭ}U{jG�?�\0,��','2014-08-20 23:18:28','2014-08-20 23:18:28','system',0),(10,0,1231,'APPT',NULL,NULL,'FAXED','F37DE9AA6E',NULL,'2014-08-22 06:45:00','2014-08-22 07:45:00',NULL,'x�G��0\0\0�q����JG�7@z�P}����]�l��%���ѷ��΢�K�ϽGD�H:��k0�`\nI���g�F_�:�,J��6Ӌ��fy���umD\'�!~mxJ����aA�誑�	�؍������cRߠ�#߶�q�2h����A�A�UF��_�l6H��tj���^�9���]�{wc�.D-��S��M��!��^�G�[��<�ػ��`��,��0�����C�����M�gmA���(~%_RP��[I��0�ěE����r�!$�S���\n�ގ�q	�g����h���f����#y�嗥Z�Ӯ��DU�\Z YH]��8@3���?��!Fyj��O��5R植��yop��=�	ڙ�\\��xoI�/�J7C\r B�SA�&���,!s���HJ�=a���]K8����\\�����\Z���B<%PF�m�zq�\r�`����c+A7R���7��}S~��)�_�\\/!���i�5��t����8��ջCt��5���	�^���G��ٵ�����b��F','2014-08-22 22:13:32','2014-08-22 22:19:38','Administrator [user1@gmail.com]',0),(11,0,1231,'APPT',NULL,NULL,'NEW','C10178CE46',NULL,NULL,NULL,NULL,'x\r�I�0\0\0�q\0ŀz�WYo�$� �׷�	�]M��e���d�5hŵ����ʥ��d���ю��ǈ�`��^�*��6�ۋ$�?���$\\�!|\"C�\Z+�1֓	�!}m+��\Z����r����΍`�%8?F.2iʰA�{8v}a��8��`q؝�z؈(x���S:��5u�BC��h���<�Ԕ����_���z��U7���~�	�[�����7�&p��ȗ,���r�{$V�J�mN���\\,c�E\"�F�����+L��HL3�MZ.��=�:�o�v�0�O�1\".��|��ѱ�)*�ms0V��r-����4	�|L���ٖT�JD/�ĭ}U{jG�?�\0,��','2014-08-23 18:31:46','2014-08-23 18:31:46','system',1),(12,0,1231,'Medicine refill','Nurse',NULL,'NEW','045AF62C89',NULL,NULL,NULL,NULL,'x�I�C0\0\0�Y�j�ek&E��b�)�����<�P\"�ٽ�6L-��y-�u6�JE��.l��Աw�R4�4˴Y���b3�#��>���ZCX/ao,�\'D�~:�8�7�5��$f.�]���Ǳ��ߜbJ�J?j��y�|�q���I�]�\Zj��{#�\0�4�N��b�#UQq�_�x2��wv�:n��>X-x�e�������r�,��{N<מ�kz/��\0�,]�7j��/���i5x{�h����R+�|�@~qs�UM�J1(ֵ7���W���3`;����8��W�u66/����g_8�)2+	�b��P~ËEj��y���͞�o���uN��Id�U���ˊ�D�!��gءm�K8�Ɓ#R���|o���h�ϛV61\"+��w��o�ս�������g�H\n��','2014-08-25 01:03:11','2014-08-25 01:03:11','system',1);
/*!40000 ALTER TABLE `locmsg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `messageId` int(13) NOT NULL AUTO_INCREMENT,
  `bookingId` int(13) DEFAULT NULL,
  `msgRcp` varchar(150) NOT NULL,
  `msgTarget` enum('CONSUMER','PROVIDER') NOT NULL,
  `msgType` enum('EMAIL','APP_NOTIFICATION','SMS') NOT NULL,
  `msgStatus` enum('SENT','CREATED','LOCKED','PROCESSING','ERRORED','EXPIRED') NOT NULL,
  `msgAttemptCount` int(11) DEFAULT '0',
  `msgSentTime` datetime DEFAULT NULL,
  `msgData` mediumblob,
  `processorName` varchar(100) NOT NULL,
  `templateName` varchar(100) DEFAULT NULL,
  `msgSubject` varchar(150) DEFAULT NULL,
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,0,'test@test.com','PROVIDER','EMAIL','ERRORED',0,NULL,NULL,'Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 17:57:16','2014-08-31 17:57:22',NULL),(2,0,'test@test.com','PROVIDER','EMAIL','ERRORED',0,NULL,NULL,'Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 17:58:21','2014-08-31 17:58:25',NULL),(3,0,'harsh@contactmydoc.com','PROVIDER','EMAIL','ERRORED',0,NULL,NULL,'Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 18:02:37','2014-08-31 18:02:42',NULL),(4,0,'harsh@contactmydoc.com','PROVIDER','EMAIL','ERRORED',0,NULL,NULL,'Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 18:04:48','2014-08-31 18:04:52',NULL),(5,0,'harsh@contactmydoc.com','PROVIDER','EMAIL','ERRORED',0,NULL,NULL,'Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 18:05:11','2014-08-31 18:05:12',NULL),(6,0,'harsh@contactmydoc.com','PROVIDER','EMAIL','ERRORED',0,NULL,NULL,'Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 18:05:42','2014-08-31 18:05:42',NULL),(7,0,'admin@contactmydoc.com','PROVIDER','EMAIL','ERRORED',0,NULL,NULL,'Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 18:07:45','2014-08-31 18:07:47',NULL),(8,0,'admin@contactmydoc.com','PROVIDER','EMAIL','ERRORED',0,NULL,NULL,'Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 18:09:27','2014-08-31 18:10:19',NULL),(9,0,'admin@contactmydoc.com','PROVIDER','EMAIL','PROCESSING',0,NULL,'qMAX92ac2/OLT4twumeExntjK+X8bZrkOs6KIQ7Eoj/oCrvuENGqk9bXVu2dj9tAL+0gbuoI00V5g770ng1uE6Lx7hQ9QNj2wXD4pnzobTUxHLwcLYnyo1WoAcNWGzHSGTArhm04d/cmYHuVxNccVdIL+YHwNn0+H3d27Vs5fMu84G/M0WtIWYjqDx/91fIR77PuyvhHu/b750WBp750WZQwmOJP/YeB40fIEsBRfgCGkgdH5GH2w+TSBz5qVaYvhQYE+bAojCIB3Grb2uaeLA==','Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 18:12:09','2014-08-31 18:12:13',NULL),(10,0,'admin@contactmydoc.com','PROVIDER','EMAIL','ERRORED',0,NULL,'qMAX92ac2/OLT4twumeExntjK+X8bZrkOs6KIQ7Eoj/oCrvuENGqk9bXVu2dj9tAL+0gbuoI00V5g770ng1uE6Lx7hQ9QNj2wXD4pnzobTUxHLwcLYnyo1WoAcNWGzHSGTArhm04d/cmYHuVxNccVdIL+YHwNn0+H3d27Vs5fMu84G/M0WtIWYjqDx/91fIR77PuyvhHu/b750WBp750WQnk07pbpbTbaSCbpr3zySyGkgdH5GH2w+TSBz5qVaYvDaMUkSbTExABqRtcORvwv6iFtXJwVWVFjA1dYr7qdVwSAGJ47v27RbyPH8mCvkvfhCxcK6VzWJMI8niqjaO4gQ==','Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 18:13:13','2014-08-31 18:13:20',NULL),(11,0,'admin@contactmydoc.com','PROVIDER','EMAIL','PROCESSING',0,NULL,'qMAX92ac2/OLT4twumeExntjK+X8bZrkOs6KIQ7Eoj/oCrvuENGqk9bXVu2dj9tAL+0gbuoI00V5g770ng1uE6Lx7hQ9QNj2wXD4pnzobTUxHLwcLYnyo1WoAcNWGzHSGTArhm04d/cmYHuVxNccVdIL+YHwNn0+H3d27Vs5fMu84G/M0WtIWYjqDx/91fIR77PuyvhHu/b750WBp750WQnk07pbpbTbaSCbpr3zySyGkgdH5GH2w+TSBz5qVaYvDaMUkSbTExABqRtcORvwv6iFtXJwVWVFjA1dYr7qdVwSAGJ47v27RbyPH8mCvkvfhCxcK6VzWJMI8niqjaO4gQ==','Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 18:16:00','2014-08-31 18:16:09',NULL),(12,0,'admin@contactmydoc.com','PROVIDER','EMAIL','ERRORED',0,NULL,'qMAX92ac2/OLT4twumeExntjK+X8bZrkOs6KIQ7Eoj/oCrvuENGqk9bXVu2dj9tAL+0gbuoI00V5g770ng1uE6Lx7hQ9QNj2wXD4pnzobTUxHLwcLYnyo1WoAcNWGzHSGTArhm04d/cmYHuVxNccVdIL+YHwNn0+H3d27Vs5fMu84G/M0WtIWYjqDx/91fIR77PuyvhHu/b750WBp750WQnk07pbpbTbaSCbpr3zySyGkgdH5GH2w+TSBz5qVaYvDaMUkSbTExABqRtcORvwv6iFtXJwVWVFjA1dYr7qdVwSAGJ47v27RbyPH8mCvkvfhCxcK6VzWJMI8niqjaO4gQ==','Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 18:16:09','2014-08-31 18:19:33',NULL),(13,0,'admin@contactmydoc.com','PROVIDER','EMAIL','ERRORED',0,NULL,'qMAX92ac2/OLT4twumeExntjK+X8bZrkOs6KIQ7Eoj/oCrvuENGqk9bXVu2dj9tAL+0gbuoI00V5g770ng1uE6Lx7hQ9QNj2wXD4pnzobTUxHLwcLYnyo1WoAcNWGzHSGTArhm04d/cmYHuVxNccVdIL+YHwNn0+H3d27Vs5fMu84G/M0WtIWYjqDx/91fIR77PuyvhHu/b750WBp750WQnk07pbpbTbaSCbpr3zySyGkgdH5GH2w+TSBz5qVaYvDaMUkSbTExABqRtcORvwv6iFtXJwVWVFjA1dYr7qdVwSAGJ47v27RbyPH8mCvkvfhCxcK6VzWJMI8niqjaO4gQ==','Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 18:22:32','2014-08-31 18:22:38',NULL),(14,0,'admin@contactmydoc.com','PROVIDER','EMAIL','ERRORED',0,NULL,'qMAX92ac2/OLT4twumeExntjK+X8bZrkOs6KIQ7Eoj/oCrvuENGqk9bXVu2dj9tAL+0gbuoI00V5g770ng1uE6Lx7hQ9QNj2wXD4pnzobTUxHLwcLYnyo1WoAcNWGzHSGTArhm04d/cmYHuVxNccVdIL+YHwNn0+H3d27Vs5fMu84G/M0WtIWYjqDx/91fIR77PuyvhHu/b750WBp750WQnk07pbpbTbaSCbpr3zySyGkgdH5GH2w+TSBz5qVaYvDaMUkSbTExABqRtcORvwv6iFtXJwVWVFjA1dYr7qdVwSAGJ47v27RbyPH8mCvkvfhCxcK6VzWJMI8niqjaO4gQ==','Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 18:24:16','2014-08-31 18:24:19',NULL),(15,0,'admin@contactmydoc.com','PROVIDER','EMAIL','SENT',0,'2014-08-31 18:26:38','qMAX92ac2/OLT4twumeExntjK+X8bZrkOs6KIQ7Eoj/oCrvuENGqk9bXVu2dj9tAL+0gbuoI00V5g770ng1uE6Lx7hQ9QNj2wXD4pnzobTUxHLwcLYnyo1WoAcNWGzHSGTArhm04d/cmYHuVxNccVdIL+YHwNn0+H3d27Vs5fMu84G/M0WtIWYjqDx/91fIR77PuyvhHu/b750WBp750WQnk07pbpbTbaSCbpr3zySyGkgdH5GH2w+TSBz5qVaYvDaMUkSbTExABqRtcORvwv6iFtXJwVWVFjA1dYr7qdVwSAGJ47v27RbyPH8mCvkvfhCxcK6VzWJMI8niqjaO4gQ==','Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-08-31 18:26:28','2014-08-31 18:26:38',NULL),(16,0,'admin@contactmydoc.com','PROVIDER','EMAIL','SENT',0,'2014-09-01 13:35:34','qMAX92ac2/OLT4twumeExntjK+X8bZrkOs6KIQ7Eoj/oCrvuENGqk9bXVu2dj9tAL+0gbuoI00V5g770ng1uE6Lx7hQ9QNj2wXD4pnzobTUxHLwcLYnyo1WoAcNWGzHSGTArhm04d/cmYHuVxNccVdIL+YHwNn0+H3d27Vs5fMu84G/M0WtIWYjqDx/91fIR77PuyvhHu/b750WBp750WQnk07pbpbTbaSCbpr3zySxv0Te1ufQJKZebM22h8VWU85DZsVn4YeXKv55sch7MWxuW9N5pxxKN5hFA0CvFzvAdhvy3cU0zKmYe223kamy8YV8HqsRbxDw9hbwudvqRxQ==','Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-09-01 13:35:33','2014-09-01 13:35:35',NULL),(17,0,'admin@contactmydoc.com','PROVIDER','EMAIL','SENT',0,'2014-09-01 13:37:14','qMAX92ac2/OLT4twumeExntjK+X8bZrkOs6KIQ7Eoj/oCrvuENGqk9bXVu2dj9tAL+0gbuoI00V5g770ng1uE6Lx7hQ9QNj2wXD4pnzobTUxHLwcLYnyo1WoAcNWGzHSGTArhm04d/cmYHuVxNccVdIL+YHwNn0+H3d27Vs5fMu84G/M0WtIWYjqDx/91fIR77PuyvhHu/b750WBp750WQnk07pbpbTbaSCbpr3zySxv0Te1ufQJKZebM22h8VWU85DZsVn4YeXKv55sch7MWxuW9N5pxxKN5hFA0CvFzvAdhvy3cU0zKmYe223kamy8YV8HqsRbxDw9hbwudvqRxQ==','Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-09-01 13:37:08','2014-09-01 13:37:14',NULL),(18,0,'admin@contactmydoc.com','PROVIDER','EMAIL','SENT',0,'2014-09-03 08:33:19','qMAX92ac2/OLT4twumeExntjK+X8bZrkOs6KIQ7Eoj/oCrvuENGqk9bXVu2dj9tAL+0gbuoI00V5g770ng1uE6Lx7hQ9QNj2wXD4pnzobTUxHLwcLYnyo1WoAcNWGzHSGTArhm04d/cmYHuVxNccVdIL+YHwNn0+H3d27Vs5fMu84G/M0WtIWYjqDx/91fIR77PuyvhHu/b750WBp750WQnk07pbpbTbaSCbpr3zySy8XvDM3L4kBsDyX+r8ZmPcPxwzsoOsmQHlDp+wqteh1BuW9N5pxxKN5hFA0CvFzvAdhvy3cU0zKmYe223kamy8YV8HqsRbxDw9hbwudvqRxQ==','Ganga-MessageSchedulerService','NewContactProvider',NULL,'2014-09-03 08:33:15','2014-09-03 08:33:19',NULL);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provorg`
--

DROP TABLE IF EXISTS `provorg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provorg` (
  `provOrgId` int(13) NOT NULL AUTO_INCREMENT,
  `primaryEmailAddress` varchar(75) COLLATE utf8_unicode_ci NOT NULL,
  `secMsgEnabled` tinyint(1) DEFAULT '1',
  `appPushEnabled` tinyint(1) DEFAULT '0',
  `noOfUsers` tinyint(2) DEFAULT '5',
  `termsAndConditionAccepted` tinyint(1) DEFAULT '5',
  `paymentInfoCompleted` tinyint(1) DEFAULT '0',
  `locationsAdded` tinyint(1) DEFAULT '0',
  `providersAdded` tinyint(1) DEFAULT '0',
  `insuranceAdded` tinyint(1) DEFAULT '0',
  `status` enum('ACTIVE','SUSPENDED','DELETED','PENDING') COLLATE utf8_unicode_ci NOT NULL,
  `description` blob,
  `primaryPassword` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `emailActivationId` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `memberInitials` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orgName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(75) COLLATE utf8_unicode_ci DEFAULT NULL,
  `webSiteURL` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `configDetails` blob,
  PRIMARY KEY (`provOrgId`),
  UNIQUE KEY `ux_primary_email` (`primaryEmailAddress`),
  KEY `emailAddressIndex` (`primaryEmailAddress`)
) ENGINE=InnoDB AUTO_INCREMENT=1237 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provorg`
--

LOCK TABLES `provorg` WRITE;
/*!40000 ALTER TABLE `provorg` DISABLE KEYS */;
INSERT INTO `provorg` VALUES (1231,'user1@gmail.com',1,0,5,1,0,0,0,0,'ACTIVE','Test Description','$2a$12$DAiuZO4sdHapHP9P/JCck.TRWnt4gioWAvrLvudjcfaUD8n8OUPRq','93d6882d-974c-4d8b-9885-925b86d9671e','ap','radiology Associates','2014-08-09 21:10:12','2014-09-28 10:42:57','user1@gmail.com','http://www.contactmydoc.com','x\r�Ǣk@\0\0���%BF/���N������/��A\'�1�љ(l�]�7Z�EDb�̭��ڀ	^%u�R\'�1gQ#�0�����^�����OBC�S\0]X�x�*��K`O-tY~�|	��c�����i�geo��A��v����(��C��5�d��ǵI�y��Q u��L�F�v��W��zUw 7�����O�DM?���z��S�\"����B��H���5\r�l�T1j᎚}ຆ\0���i�[��T���\r!@�$�7~�����Y&�A[�7��5���:��_�Ȏ7M���8��\n��g�ł5�jB�kގ��=�4�=�h�$���C�;}�]%��a]��I(;�cW�����~�K��y�/���\'�$�n�=�\Z�Ti7DgSX;�>��C2X��D	]X\'gj�O�s�7���/�n»D/��uU!3�<1�c��B_��\'}ף�f|�[-��C9��C���\n�R��ay�4�<���<��6�/���qz�\Z{�9yqU���o�b�\">.�vO��8b�<6�J\\�mvM�݂oJ����o&��3m�t��\'\0>�U��v�Em��9�e�\n�;�r��7E��\n�0g�4}{B%�S����V_��x�\0���'),(1234,'user2@gmail.com',1,0,5,0,0,0,0,0,'ACTIVE',NULL,'$2a$12$886/lKyNgZ/R2RX89lzLz.Q3iLxSR5EMD81jlnWMEQZBpuR/kyMjW',NULL,NULL,NULL,'2014-08-09 22:26:06','2014-08-09 22:26:06','user2@gmail.com',NULL,NULL),(1235,'apoddar71@gmail.com',1,0,5,0,0,0,0,0,'ACTIVE',NULL,'$2a$12$mNJpKTZAxhY67IxSM43CP.vk8wgUPugNMTBedJWzNt9a5xi4eAMIa','853dae0e-7ab6-4ae0-a1f5-950accc798c1',NULL,NULL,'2014-08-09 23:38:42','2014-08-09 23:38:42','apoddar71@gmail.com',NULL,NULL),(1236,'west@gmail.com',1,0,5,0,0,0,0,0,'ACTIVE',NULL,'$2a$12$sL.sVSs6Rd4/dSwcZ2W7vOMgUbtt2v5S59GFAegOX3lg1h5jlZ.hC',NULL,'WH','Westchester Health','2014-08-28 19:39:25','2014-08-28 19:39:25','west@gmail.com',NULL,NULL);
/*!40000 ALTER TABLE `provorg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provorginsinfo`
--

DROP TABLE IF EXISTS `provorginsinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provorginsinfo` (
  `provOrgInsInfoId` int(13) NOT NULL AUTO_INCREMENT,
  `provOrgId` int(13) NOT NULL,
  `insuranceId` int(13) NOT NULL,
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(75) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`provOrgInsInfoId`),
  UNIQUE KEY `provInsuId` (`provOrgId`,`insuranceId`),
  KEY `insuranceConst` (`insuranceId`),
  CONSTRAINT `insuranceConst` FOREIGN KEY (`insuranceId`) REFERENCES `insurance_lk` (`insuranceId`),
  CONSTRAINT `provConst` FOREIGN KEY (`provOrgId`) REFERENCES `provorg` (`provOrgId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provorginsinfo`
--

LOCK TABLES `provorginsinfo` WRITE;
/*!40000 ALTER TABLE `provorginsinfo` DISABLE KEYS */;
INSERT INTO `provorginsinfo` VALUES (14,1231,3160,'2014-09-28 09:42:07','2014-09-28 09:42:07','user1@gmail.com'),(15,1231,3169,'2014-09-28 09:42:07','2014-09-28 09:42:07','user1@gmail.com'),(16,1231,3177,'2014-09-28 09:42:07','2014-09-28 09:42:07','user1@gmail.com'),(17,1231,3172,'2014-09-28 09:42:07','2014-09-28 09:42:07','user1@gmail.com'),(18,1231,3270,'2014-09-28 09:42:07','2014-09-28 09:42:07','user1@gmail.com');
/*!40000 ALTER TABLE `provorginsinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provorguser`
--

DROP TABLE IF EXISTS `provorguser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provorguser` (
  `provOrgUserId` int(13) NOT NULL AUTO_INCREMENT,
  `provOrgId` int(13) NOT NULL,
  `primaryEmailAddress` varchar(75) NOT NULL,
  `status` enum('ACTIVE','SUSPENDED','DELETED','PENDING') NOT NULL,
  `role` enum('LEVEL0','LEVEL1','LEVEL2','ADMIN') DEFAULT NULL,
  `displayName` varchar(256) DEFAULT NULL,
  `primaryPassword` varchar(512) NOT NULL,
  `emailActivationId` varchar(512) DEFAULT NULL,
  `initialPasswordChanged` tinyint(4) NOT NULL DEFAULT '0',
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`provOrgUserId`),
  UNIQUE KEY `ux_primary_email` (`primaryEmailAddress`),
  KEY `provOrgIdIndex` (`provOrgId`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provorguser`
--

LOCK TABLES `provorguser` WRITE;
/*!40000 ALTER TABLE `provorguser` DISABLE KEYS */;
INSERT INTO `provorguser` VALUES (61,1231,'dguha@sapient.com','ACTIVE','LEVEL2','Aman India','$2a$12$DkmpN9Av.dteBU7H2dz/GuhOM.ybP4gnmFnUJR5.yVRL0RxQwolBO',NULL,0,'2014-08-09 21:35:09','2014-08-13 22:43:06','user1@gmail.com'),(64,1231,'user3@gmail.com','ACTIVE','LEVEL1','Debal Guha','$2a$12$qJE0MghnJbJFCFuKMqkGr.zTBg68mJS5ejRpkMrMIRrffQ7agOX9.',NULL,0,'2014-08-09 22:32:13','2014-08-17 13:26:38','user1@gmail.com'),(65,0,'apoddar71@yahoo.com','ACTIVE','LEVEL1','Amit','$2a$12$fR5UyuRtYSwuqdFYWIfXA.ZOKBRU6x7HvOEisZnIJ8LHGqOrAQe1.',NULL,0,'2014-08-17 13:27:28','2014-08-17 13:27:28','user1@gmail.com'),(68,1231,'apoddar7112@yahoo.com','ACTIVE','LEVEL1','Amit','$2a$12$JVHWKJosNfZNIMD/mtc7W.hyo4Erjta0vZWXrJre.C9JNxbCM3PXq',NULL,0,'2014-08-17 13:32:41','2014-08-17 16:15:11','user1@gmail.com'),(69,1231,'apoddar1774@yahoo.com','ACTIVE','LEVEL1','amit','$2a$12$LfFnXVNnRr69AtOX2HDJaejSyZTWWjzhdl1hg6OAneGzJadsgWRbK',NULL,0,'2014-08-17 16:15:56','2014-08-20 21:32:42','user1@gmail.com'),(70,1231,'apoddar1975@gmail.com','ACTIVE','LEVEL1','test','$2a$12$gea40GNTrkGaLHvNO4GAaex1kPdbx/KyWRSKO8EpQopsIjkqBpCEa',NULL,0,'2014-08-17 16:24:00','2014-08-17 16:24:00','user1@gmail.com'),(71,1231,'anjali1@gmail.com','ACTIVE','LEVEL1','anjali','$2a$12$rn6MxmRIuU9TJBm0kkxXE.N/argnSlETjfnri6QpxqDL42EoEax9y',NULL,0,'2014-08-17 21:14:34','2014-08-17 21:51:22','user1@gmail.com'),(72,1231,'user100@gmail.com','ACTIVE','LEVEL1','user100','$2a$12$3vCEOPYXlas4tRgoLNimleGbeZVLVYTeMA4c1ok7Am4V6efI1YR32',NULL,0,'2014-08-17 21:52:10','2014-08-17 21:52:10','user1@gmail.com'),(73,1231,'user101@gmail.com','ACTIVE','LEVEL1','user100','$2a$12$H.k46Q./J1B9YucVOijtMuUCkgRxcgjbfjApGd.qeXKbKwsiqE6KW',NULL,0,'2014-08-17 22:01:17','2014-08-17 22:01:17','user1@gmail.com'),(76,1231,'user500@gmail.com','ACTIVE','ADMIN','user500','$2a$12$zwdTui.at3fPgCA3s9uAQeru9g2I3E9h7VmzM2ZY23BYr0/6/zDs6',NULL,0,'2014-08-18 02:13:32','2014-08-18 02:13:32','user1@gmail.com'),(77,1231,'user600@gmail.com','ACTIVE','LEVEL1','ABCD','$2a$12$FF8qVxTmtyHHJj/s7ZQymOkT2WVzUYGcFqRGvbqtTt5RG.U4Fqg6q',NULL,0,'2014-08-20 21:30:59','2014-08-20 21:30:59','user1@gmail.com'),(78,1231,'user700@gmail.com','ACTIVE','ADMIN','NEW user','$2a$12$2MMraJFs3wQaZsPKmrg5de0js2cw983DWxPKUO.4BuyOc7XTBLxpy',NULL,0,'2014-08-20 21:32:17','2014-08-20 21:32:17','user1@gmail.com');
/*!40000 ALTER TABLE `provorguser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provorguserlocmap`
--

DROP TABLE IF EXISTS `provorguserlocmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provorguserlocmap` (
  `provOrgUserLocMapId` int(13) NOT NULL AUTO_INCREMENT,
  `provOrgId` int(13) NOT NULL,
  `locationId` int(13) NOT NULL,
  `provOrgUserId` int(13) NOT NULL,
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(75) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emailEnabled` tinyint(3) unsigned DEFAULT '1',
  PRIMARY KEY (`provOrgUserLocMapId`),
  UNIQUE KEY `provorguserlocmapuk1` (`provOrgId`,`locationId`,`provOrgUserId`),
  KEY `servicebylocfk2` (`provOrgUserId`),
  CONSTRAINT `locOrgfk1` FOREIGN KEY (`provOrgId`, `locationId`) REFERENCES `location` (`provOrgId`, `locationId`),
  CONSTRAINT `servicebylocfk2` FOREIGN KEY (`provOrgUserId`) REFERENCES `provorguser` (`provOrgUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=418922 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provorguserlocmap`
--

LOCK TABLES `provorguserlocmap` WRITE;
/*!40000 ALTER TABLE `provorguserlocmap` DISABLE KEYS */;
INSERT INTO `provorguserlocmap` VALUES (418899,1231,1250,61,'2014-08-13 22:43:06','2014-08-13 22:43:06','user1@gmail.com',1),(418900,1231,1252,61,'2014-08-13 22:43:06','2014-08-13 22:43:06','user1@gmail.com',1),(418903,1231,1250,64,'2014-08-17 13:26:38','2014-08-17 13:26:38','user1@gmail.com',0),(418904,1231,1252,64,'2014-08-17 13:26:38','2014-08-17 13:26:38','user1@gmail.com',0),(418907,1231,1250,68,'2014-08-17 16:15:11','2014-08-17 16:15:11','user1@gmail.com',1),(418911,1231,1250,71,'2014-08-17 21:51:22','2014-08-17 21:51:22','user1@gmail.com',1),(418915,1231,1250,76,'2014-08-18 02:13:32','2014-08-18 02:13:32','user1@gmail.com',0),(418916,1231,1252,76,'2014-08-18 02:13:32','2014-08-18 02:13:32','user1@gmail.com',1),(418917,1231,1253,76,'2014-08-18 02:13:32','2014-08-18 02:13:32','user1@gmail.com',0),(418918,1231,1250,77,'2014-08-20 21:30:59','2014-08-20 21:30:59','user1@gmail.com',1),(418919,1231,1250,78,'2014-08-20 21:32:17','2014-08-20 21:32:17','user1@gmail.com',0),(418920,1231,1253,78,'2014-08-20 21:32:17','2014-08-20 21:32:17','user1@gmail.com',1),(418921,1231,1250,69,'2014-08-20 21:32:42','2014-08-20 21:32:42','user1@gmail.com',1);
/*!40000 ALTER TABLE `provorguserlocmap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provsession`
--

DROP TABLE IF EXISTS `provsession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provsession` (
  `pavSessionId` varchar(100) NOT NULL,
  `primaryEmailAddress` varchar(75) NOT NULL,
  `valid` enum('Y','N') NOT NULL DEFAULT 'N',
  `maxInactive` int(11) NOT NULL,
  `lastAccess` bigint(20) NOT NULL,
  `sessionData` mediumblob,
  `creationDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `updateUserName` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`pavSessionId`),
  UNIQUE KEY `ux_primary_email` (`primaryEmailAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provsession`
--

LOCK TABLES `provsession` WRITE;
/*!40000 ALTER TABLE `provsession` DISABLE KEYS */;
INSERT INTO `provsession` VALUES ('f1db3b70-a4af-4807-a1cc-b25ff620e874','user1@gmail.com','Y',155552000,1411916042059,'x�S�n1u_���@�MٰȌ�tB��4I���D}J��v&.3�k{�f�/�\'K�m?�]��;�>���Yx�k�s�9���\r�1\Z�\"2r���>7�:�	�R���ii���֯�ݬ�<�M��m�wZ��UB�k,�)M�$�r)L\Z��9!��côi�u���.��,#2�(\\Q@T�0�\'ԣ�6Z0�����e:2��e)(O�|B��Z RtyPa��X�k�LwL�;��d��L�yH�>C��a�-�ɳ�zB�!���Ӣ��9�c7�\"p��\"�N�p�B<��#v���T�0p4���ԗ:�=��wʢ�Xly��cH��:L�Hġ�͙�:�^i��jJA�f�����Q3���tZK0��0�s��\n �yd,�1XQ��Ա�9L�E��زd�$�X�C���j�T(�f`���&&��h���Qy�b��kZ��<͖�\n$[�^\'[��l)���T��1����MN�Sæ���^����)4���C��&VjD�{2����NZ���c�/\'�K���\ZS.C7|c$�\n�Y�AYn\'Hz���Y�6�Ǚ\\>S�y�n�aUk�Z��2���\n[�<���я�9���\'��q��B]iT��+M�4�TGV�eò\nk���O�ԩ�8\Z?)���Q�����_��Uo\\w0�\Za��hH%I���h�','2014-09-28 10:52:56','2014-09-28 10:54:02','user1@gmail.com');
/*!40000 ALTER TABLE `provsession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table1`
--

DROP TABLE IF EXISTS `table1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table1` (
  `field1` int(11) NOT NULL,
  `field2` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `field2Index` (`field2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table1`
--

LOCK TABLES `table1` WRITE;
/*!40000 ALTER TABLE `table1` DISABLE KEYS */;
/*!40000 ALTER TABLE `table1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table2`
--

DROP TABLE IF EXISTS `table2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table2` (
  `field1` int(11) NOT NULL,
  `field2` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  KEY `field2Table2Index` (`field2`),
  CONSTRAINT `myconst` FOREIGN KEY (`field2`) REFERENCES `table1` (`field2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table2`
--

LOCK TABLES `table2` WRITE;
/*!40000 ALTER TABLE `table2` DISABLE KEYS */;
/*!40000 ALTER TABLE `table2` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-29  7:58:59
