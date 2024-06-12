CREATE DATABASE  IF NOT EXISTS `cachacariaantoniocarlos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cachacariaantoniocarlos`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: cachacariaantoniocarlos
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ingrediente`
--

DROP TABLE IF EXISTS `ingrediente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingrediente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  `dataCompra` date DEFAULT NULL,
  `lote` varchar(50) DEFAULT NULL,
  `kg_l` varchar(20) DEFAULT NULL,
  `fabricacao` date DEFAULT NULL,
  `validade` date DEFAULT NULL,
  `fornecedor` varchar(100) DEFAULT NULL,
  `valorTotal` decimal(10,2) DEFAULT NULL,
  `notaFiscal` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingrediente`
--

LOCK TABLES `ingrediente` WRITE;
/*!40000 ALTER TABLE `ingrediente` DISABLE KEYS */;
INSERT INTO `ingrediente` VALUES (2,'Açucar','2024-06-12','111','100 kg','2024-06-12','2024-06-12','Mercado Antonio Carlos',10.00,'1534548651'),(3,'Maracujá','2024-01-01','123','10','2023-12-31','2023-12-31','Fruteira Dois Irmãos',20.00,'123456789'),(5,'Coco Ralado','2023-11-10','333','50','2023-11-11','2024-11-11','Mercado Dois Irmãos',202.00,'12345');
/*!40000 ALTER TABLE `ingrediente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sabor_id` int DEFAULT NULL,
  `lote` varchar(20) DEFAULT NULL,
  `fabricacao` date DEFAULT NULL,
  `quantidade` int DEFAULT NULL,
  `restante` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sabor_id` (`sabor_id`),
  CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`sabor_id`) REFERENCES `sabor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,1,'004','2024-01-01',1000,950),(2,1,'012','2024-02-01',1000,1000),(4,10,'013','2024-05-06',550,550),(6,5,'002','2024-04-20',400,400),(7,7,'007','2024-03-14',605,605),(11,9,'001','2024-12-11',110,48),(12,4,'003','2024-12-12',100,100),(13,15,'006','2024-11-11',6606,300),(14,9,'010','2024-12-22',888,888),(15,5,'011','2025-01-30',989,989),(16,10,'005','2025-05-05',555,555),(17,16,'008','2024-12-12',500,500),(18,7,'014','2024-05-27',150,150);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sabor`
--

DROP TABLE IF EXISTS `sabor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sabor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipoSabor` varchar(50) DEFAULT NULL,
  `validade` tinyint(1) DEFAULT NULL,
  `codigoBarra` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sabor`
--

LOCK TABLES `sabor` WRITE;
/*!40000 ALTER TABLE `sabor` DISABLE KEYS */;
INSERT INTO `sabor` VALUES (1,'Cachaça',0,'7898973682001'),(3,'Canelinha',0,'7898973682087'),(4,'Bitter',0,'7898973682025'),(5,'Banana',0,'7898973682094'),(7,'Maracujá',1,'7898973682056'),(9,'Amendoim',1,'7898973682018'),(10,'Coco',1,'7898973682032'),(15,'Limãozinho',1,'7898973682049'),(16,'Vodka',0,'7898973682070'),(21,'Menta',0,'7898973682063');
/*!40000 ALTER TABLE `sabor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utensilio`
--

DROP TABLE IF EXISTS `utensilio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utensilio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  `dataCompra` date DEFAULT NULL,
  `lote` varchar(50) DEFAULT NULL,
  `quantidade` varchar(50) DEFAULT NULL,
  `fornecedor` varchar(100) DEFAULT NULL,
  `valorTotal` decimal(10,2) DEFAULT NULL,
  `notaFiscal` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utensilio`
--

LOCK TABLES `utensilio` WRITE;
/*!40000 ALTER TABLE `utensilio` DISABLE KEYS */;
INSERT INTO `utensilio` VALUES (2,'Rótulos','2024-06-11','','200','Adesivos Antonio Carlos',80.00,'123456789'),(3,'Garrafas','2024-06-12','','500','Embalagens Amigo Ltda',250.00,'123456789'),(4,'Rolhas','2024-05-02','','1000','Embalagens Amigo Ltda',75.00,'123654'),(5,'Filtro misturador','2024-06-12','513486','1','Filtros Ltda',387.00,'12368');
/*!40000 ALTER TABLE `utensilio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venda` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dataVenda` date DEFAULT NULL,
  `cliente` varchar(100) DEFAULT NULL,
  `produto_id` int DEFAULT NULL,
  `quantidade` int DEFAULT NULL,
  `valorTotal` decimal(10,2) DEFAULT NULL,
  `notaFiscal` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `produto_id` (`produto_id`),
  CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` VALUES (1,'2024-01-01','Guillherme',1,150,150.00,'11111'),(4,'2023-02-01','018.111.222.444-86',12,15,200.00,'12154384'),(5,'2024-05-28','Valério',6,2,30.00,'354846'),(8,'2024-05-28','Guilherme Batistella',11,40,500.00,'684264'),(9,'2024-05-28','Geisa',6,2,30.00,'3512855');
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-12 19:41:04
