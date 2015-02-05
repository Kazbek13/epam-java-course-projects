-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: airline
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
-- Table structure for table `aircraft`
--

DROP TABLE IF EXISTS `aircraft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aircraft` (
  `id_aircraft_reg` varchar(10) NOT NULL,
  `manufacturer` varchar(45) NOT NULL,
  `model_type` varchar(45) NOT NULL,
  `num_seats` int(11) NOT NULL,
  `num_pilots` int(11) NOT NULL,
  `num_navigators` int(11) NOT NULL,
  `num_radiomen` int(11) NOT NULL,
  `num_engineers` int(11) NOT NULL,
  `num_attendants` int(11) NOT NULL,
  PRIMARY KEY (`id_aircraft_reg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft`
--

LOCK TABLES `aircraft` WRITE;
/*!40000 ALTER TABLE `aircraft` DISABLE KEYS */;
INSERT INTO `aircraft` VALUES ('UR-WRI','Airbus','A320',180,2,0,0,0,6),('UR-WRQ','Airbus','A330',330,2,0,0,0,6);
/*!40000 ALTER TABLE `aircraft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airport` (
  `id_airport` varchar(4) NOT NULL,
  `title` varchar(60) NOT NULL,
  `city` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  PRIMARY KEY (`id_airport`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES ('LTFE','Bodrum Milas Airport','Milas','Turkey'),('UKBB','Boryspil','Kiev','Ukraine'),('UKHH','Osnova','Kharkiv','Ukraine');
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cabin_crew`
--

DROP TABLE IF EXISTS `cabin_crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cabin_crew` (
  `id_employee` int(11) NOT NULL,
  `id_flight_date` date NOT NULL,
  `id_flight_num` varchar(8) NOT NULL,
  PRIMARY KEY (`id_employee`,`id_flight_date`,`id_flight_num`),
  KEY `fk_employee_has_shuttle_flight_employee1_idx` (`id_employee`),
  KEY `fk_cabin_crew_shuttle_flight1_idx` (`id_flight_date`,`id_flight_num`),
  CONSTRAINT `fk_cabin_crew_employee1` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id_employee`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cabin_crew_shuttle_flight1` FOREIGN KEY (`id_flight_date`, `id_flight_num`) REFERENCES `shuttle_flight` (`id_flight_date`, `id_flight_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabin_crew`
--

LOCK TABLES `cabin_crew` WRITE;
/*!40000 ALTER TABLE `cabin_crew` DISABLE KEYS */;
INSERT INTO `cabin_crew` VALUES (16,'2015-01-10','WRC1103'),(17,'2015-01-10','WRC1103'),(18,'2015-01-10','WRC1103'),(19,'2015-01-10','WRC1103'),(20,'2015-01-10','WRC1103'),(21,'2015-01-10','WRC1103'),(22,'2015-01-10','WRC1103'),(23,'2015-01-10','WRC1103');
/*!40000 ALTER TABLE `cabin_crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id_employee` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `position` varchar(45) NOT NULL,
  PRIMARY KEY (`id_employee`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (13,'garfieldua','qwerty123','Andrii','Davydenko','admin'),(14,'aviaman','avia','Nikolay','Fomenko','dispatcher'),(16,NULL,NULL,'Vitali','Khaenko','pilot'),(17,NULL,NULL,'Sergey','Ulitskiy','pilot'),(18,NULL,NULL,'Karina','Volochkova','attendant'),(19,NULL,NULL,'Marina','Vasilyeva','attendant'),(20,NULL,NULL,'Liudmila','Bobrovitskaya','attendant'),(21,NULL,NULL,'Igor','Kikot','attendant'),(22,NULL,NULL,'Alena','Makoyev','attendant'),(23,NULL,NULL,'Yuliya','Sholomko','attendant'),(24,NULL,NULL,'Anton','Gerasimov','radioman'),(25,NULL,NULL,'Alexandr','Volsky','navigator'),(26,NULL,NULL,'Boghdan','Khudik','engineer');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight` (
  `id_flight_num` varchar(8) NOT NULL,
  `dep_time` time NOT NULL,
  `arr_time` time NOT NULL,
  `dep_airport` varchar(4) NOT NULL,
  `arr_airport` varchar(4) NOT NULL,
  PRIMARY KEY (`id_flight_num`),
  KEY `fk_flight_airport_idx` (`dep_airport`),
  KEY `fk_flight_airport1_idx` (`arr_airport`),
  CONSTRAINT `fk_flight_airport` FOREIGN KEY (`dep_airport`) REFERENCES `airport` (`id_airport`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_flight_airport1` FOREIGN KEY (`arr_airport`) REFERENCES `airport` (`id_airport`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES ('WRC1103','10:10:00','11:00:00','UKBB','UKHH'),('WRC1104','12:00:00','12:50:00','UKHH','UKBB'),('WRC7301','08:30:00','10:30:00','UKBB','LTFE'),('WRC7302','11:30:00','13:30:00','LTFE','UKBB');
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shuttle_flight`
--

DROP TABLE IF EXISTS `shuttle_flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shuttle_flight` (
  `id_flight_date` date NOT NULL,
  `aircraft_reg` varchar(10) NOT NULL,
  `id_flight_num` varchar(8) NOT NULL,
  PRIMARY KEY (`id_flight_date`,`id_flight_num`),
  KEY `fk_shuttle_flight_aircraft1_idx` (`aircraft_reg`),
  KEY `fk_shuttle_flight_flight1_idx` (`id_flight_num`),
  CONSTRAINT `fk_shuttle_flight_aircraft1` FOREIGN KEY (`aircraft_reg`) REFERENCES `aircraft` (`id_aircraft_reg`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_shuttle_flight_flight1` FOREIGN KEY (`id_flight_num`) REFERENCES `flight` (`id_flight_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shuttle_flight`
--

LOCK TABLES `shuttle_flight` WRITE;
/*!40000 ALTER TABLE `shuttle_flight` DISABLE KEYS */;
INSERT INTO `shuttle_flight` VALUES ('2015-01-10','UR-WRI','WRC1103');
/*!40000 ALTER TABLE `shuttle_flight` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-05 19:55:35
