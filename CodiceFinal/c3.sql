-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: c3
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `armadietto`
--

DROP TABLE IF EXISTS `armadietto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `armadietto` (
  `IDArmadietto` int NOT NULL,
  `idlocker` int DEFAULT NULL,
  `idOrdine` int DEFAULT NULL,
  PRIMARY KEY (`IDArmadietto`),
  UNIQUE KEY `IDArmadietto_UNIQUE` (`IDArmadietto`),
  KEY `idlock_idx` (`idlocker`),
  CONSTRAINT `idlock` FOREIGN KEY (`idlocker`) REFERENCES `locker` (`IDLocker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `armadietto`
--

LOCK TABLES `armadietto` WRITE;
/*!40000 ALTER TABLE `armadietto` DISABLE KEYS */;
INSERT INTO `armadietto` VALUES (1,1,287999515),(2,2,5),(3,2,NULL),(4,2,NULL),(5,3,NULL),(6,4,NULL),(7,11,NULL),(8,2,NULL),(9,11,NULL),(10,7,NULL),(11,1,NULL),(12,1,201251085);
/*!40000 ALTER TABLE `armadietto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrello`
--

DROP TABLE IF EXISTS `carrello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrello` (
  `IDCarrello` int NOT NULL,
  `totale` float DEFAULT NULL,
  PRIMARY KEY (`IDCarrello`),
  UNIQUE KEY `IDCarrello_UNIQUE` (`IDCarrello`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrello`
--

LOCK TABLES `carrello` WRITE;
/*!40000 ALTER TABLE `carrello` DISABLE KEYS */;
INSERT INTO `carrello` VALUES (1,7.8),(2,55.26),(3,29.29);
/*!40000 ALTER TABLE `carrello` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrelloprodotto`
--

DROP TABLE IF EXISTS `carrelloprodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrelloprodotto` (
  `idC` int NOT NULL,
  `idP` int NOT NULL,
  `quantita` int DEFAULT NULL,
  KEY `FKprodotto_idx` (`idP`),
  KEY `FKcarrello_idx` (`idC`),
  CONSTRAINT `FKcarrello` FOREIGN KEY (`idC`) REFERENCES `carrello` (`IDCarrello`),
  CONSTRAINT `FKprodotto` FOREIGN KEY (`idP`) REFERENCES `prodotto` (`IDProdotto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrelloprodotto`
--

LOCK TABLES `carrelloprodotto` WRITE;
/*!40000 ALTER TABLE `carrelloprodotto` DISABLE KEYS */;
INSERT INTO `carrelloprodotto` VALUES (2,3,1),(2,7,1),(3,9,1),(3,10,1),(1,1,1);
/*!40000 ALTER TABLE `carrelloprodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `IDCliente` int NOT NULL,
  `portafoglio` float DEFAULT NULL,
  `IDCarrello` int NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  `user` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IDCliente`),
  UNIQUE KEY `IDCliente_UNIQUE` (`IDCliente`),
  KEY `Carrello_idx` (`IDCarrello`),
  CONSTRAINT `Carrello3` FOREIGN KEY (`IDCarrello`) REFERENCES `carrello` (`IDCarrello`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,96835,1,'luca esposito','viale vesuvio 100, roma','pluto','pippo'),(2,68.3,2,'alessandro mimmo','piazza di spagna 13, roma','topolino','paperino'),(3,100,3,'luca luca','via non lo so','topo giogio','pippo');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commerciante`
--

DROP TABLE IF EXISTS `commerciante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commerciante` (
  `IDCommerciante` int NOT NULL,
  `portafoglio` float DEFAULT NULL,
  `user` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IDCommerciante`),
  UNIQUE KEY `IDCommerciante_UNIQUE` (`IDCommerciante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commerciante`
--

LOCK TABLES `commerciante` WRITE;
/*!40000 ALTER TABLE `commerciante` DISABLE KEYS */;
INSERT INTO `commerciante` VALUES (1,4630.87,'a','d','roberto mancini'),(2,1248.7,'b','d','marco marco'),(3,351.8,'c','d','filippo filippo'),(4,200.4,'d','d','luca luca');
/*!40000 ALTER TABLE `commerciante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corriere`
--

DROP TABLE IF EXISTS `corriere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corriere` (
  `IDCorriere` int NOT NULL,
  `disponibilita` tinyint DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  `user` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IDCorriere`),
  UNIQUE KEY `IDCorriere_UNIQUE` (`IDCorriere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corriere`
--

LOCK TABLES `corriere` WRITE;
/*!40000 ALTER TABLE `corriere` DISABLE KEYS */;
INSERT INTO `corriere` VALUES (1,1,'alessandro del piero','via madonna delle carceri 99, roma','pluto','pippo'),(2,1,'adriano celentano','piazza  della liberta 89, roma','a','pippo'),(3,0,'antonio rossi','via verdi 90, roma','b','pippo'),(4,0,'gerry scotti','via antonio razzi 1, roma','c','pippo'),(5,1,'matteo salvini','viale dei caduti 23, roma','d','pippo'),(6,1,'matteo renzi','piazza della vittoria 41, roma','e','pippo'),(7,1,'enrico letta','via dei tulipani 9, roma','f','pippo'),(8,0,'luca gianduiotto','via gin tonic 22, roma','g','pippo'),(9,1,'antonio pizzicotti','via 4 aprile 3, roma','h','pippo'),(10,1,'luca toni','viale berlino 25, roma','i','pippo');
/*!40000 ALTER TABLE `corriere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locker`
--

DROP TABLE IF EXISTS `locker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locker` (
  `IDLocker` int NOT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  `user` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IDLocker`),
  UNIQUE KEY `IDLocker_UNIQUE` (`IDLocker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locker`
--

LOCK TABLES `locker` WRITE;
/*!40000 ALTER TABLE `locker` DISABLE KEYS */;
INSERT INTO `locker` VALUES (1,'via aa','pluto','pippo'),(2,'via bb','a','pippo'),(3,'via cc','b','pippo'),(4,'via dd','c','pippo'),(5,'via ee','d','pippo'),(6,'via g','e','pippo'),(7,'via fff','f','pippo'),(8,'via jjj','g','pippo'),(9,'via llll','h','pippo'),(10,'via dssd','i','pippo'),(11,'via ds','j','pippo');
/*!40000 ALTER TABLE `locker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `negozio`
--

DROP TABLE IF EXISTS `negozio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `negozio` (
  `IDNegozio` int NOT NULL,
  `indirizzo` varchar(255) NOT NULL,
  `IDCommerciante` int NOT NULL,
  `nomeNegozio` varchar(255) NOT NULL,
  PRIMARY KEY (`IDNegozio`),
  UNIQUE KEY `IDNegozio_UNIQUE` (`IDNegozio`),
  KEY `negozioCommerciante_idx` (`IDCommerciante`),
  CONSTRAINT `negozioCommerciante` FOREIGN KEY (`IDCommerciante`) REFERENCES `commerciante` (`IDCommerciante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `negozio`
--

LOCK TABLES `negozio` WRITE;
/*!40000 ALTER TABLE `negozio` DISABLE KEYS */;
INSERT INTO `negozio` VALUES (1,'via pippo baudo 57, roma',1,'elettrodomestici di tutti i tipi'),(2,'via albano 55, roma',2,'alimentari H24'),(3,'via mario monti 33, roma',1,'televisori'),(4,'via vasco rossi 2, roma',2,'libri di tutti i tipi'),(5,'via le mosse 41, roma',2,'fumetteria'),(6,'via gialli 13, roma',4,'bomboniere'),(7,'via bianchi 66, roma',3,'libreria da franco');
/*!40000 ALTER TABLE `negozio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `IDOrdine` int NOT NULL,
  `IDCorriere` int DEFAULT NULL,
  `IDCliente` int NOT NULL,
  `IDCommerciante` int NOT NULL,
  `IDLocker` int DEFAULT NULL,
  `statoOrdine` varchar(255) DEFAULT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  `IDNegozio` int DEFAULT NULL,
  PRIMARY KEY (`IDOrdine`),
  UNIQUE KEY `IDOrdine_UNIQUE` (`IDOrdine`),
  KEY `Commerciante_idx` (`IDCommerciante`),
  KEY `Cliente_idx` (`IDCliente`),
  KEY `Locker_idx` (`IDLocker`),
  KEY `neg_idx` (`IDNegozio`),
  CONSTRAINT `Cliente` FOREIGN KEY (`IDCliente`) REFERENCES `cliente` (`IDCliente`),
  CONSTRAINT `Commerciante` FOREIGN KEY (`IDCommerciante`) REFERENCES `commerciante` (`IDCommerciante`),
  CONSTRAINT `Locker` FOREIGN KEY (`IDLocker`) REFERENCES `locker` (`IDLocker`),
  CONSTRAINT `neg` FOREIGN KEY (`IDNegozio`) REFERENCES `negozio` (`IDNegozio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (2,1,2,3,2,'DARITIRARE',NULL,5),(3,7,2,2,4,'INPREPARAZIONE','via madonna delle carceri',5),(4,6,1,1,NULL,'INPREPARAZIONE','via madonnina 33',1),(5,5,3,1,2,'DARITIRARE',NULL,3),(6,1,1,1,1,'RITIRATO',NULL,1),(14745944,0,3,1,NULL,'ORDINECREATO','',3),(25186798,0,3,1,NULL,'ORDINECREATO','',3),(41326624,0,1,3,NULL,'ORDINECREATO','',7),(86424723,0,1,3,NULL,'ORDINECREATO','',7),(92725389,0,3,1,NULL,'ORDINECREATO','',3),(143203453,0,3,1,1,'ORDINECREATO','',3),(144480573,1,3,1,10,'INTRANSITO','',3),(167453450,0,3,1,NULL,'ORDINECREATO','',3),(182312851,1,3,1,1,'INTRANSITO',NULL,3),(201251085,1,1,1,1,'DARITIRARE','',1),(207450802,0,1,2,NULL,'ORDINECREATO','',2),(217832874,1,3,1,1,'INPREPARAZIONE','',3),(222014381,0,1,2,NULL,'ORDINECREATO','',2),(235817464,0,3,1,NULL,'ORDINECREATO','',3),(261972002,0,3,1,NULL,'ORDINECREATO','',3),(268742107,0,1,1,NULL,'ORDINECREATO','',1),(269427689,0,3,1,NULL,'ORDINECREATO','',3),(287999515,1,3,1,1,'DARITIRARE',NULL,3),(331376671,0,3,1,NULL,'ORDINECREATO','',3),(336692250,0,3,1,NULL,'ORDINECREATO','',3),(346888735,0,1,2,NULL,'ORDINECREATO','',2),(368195194,0,1,1,NULL,'ORDINECREATO','',1),(374481950,0,1,2,NULL,'ORDINECREATO','',2),(400316225,0,3,1,NULL,'ORDINECREATO','',3),(403832866,0,1,1,NULL,'ORDINECREATO','',1),(425574500,0,3,1,NULL,'ORDINECREATO','',3),(461636178,0,3,1,NULL,'ORDINECREATO','',3),(524346693,0,1,2,NULL,'ORDINECREATO','',2),(539944542,0,1,3,NULL,'ORDINECREATO','',7),(590581539,0,1,3,NULL,'ORDINECREATO','',7),(622148331,0,3,1,NULL,'ORDINECREATO','',3),(640940137,0,3,1,NULL,'ORDINECREATO','',3),(642335572,0,3,1,NULL,'ORDINECREATO','',3),(669339165,0,3,1,NULL,'ORDINECREATO','',3),(691455627,0,3,1,NULL,'ORDINECREATO','',3),(719762994,0,1,1,NULL,'ORDINECREATO','',1),(802451457,0,1,2,NULL,'ORDINECREATO','',2),(818804985,0,1,3,NULL,'ORDINECREATO','',7),(865622479,0,3,1,NULL,'ORDINECREATO','',3),(894433279,0,1,1,NULL,'ORDINECREATO','',1),(914187123,0,3,1,NULL,'ORDINECREATO','',3),(941109690,0,3,1,NULL,'ORDINECREATO','',3),(946589235,0,1,1,NULL,'ORDINECREATO','',1),(969718891,0,3,1,NULL,'ORDINECREATO','',3),(976320978,0,1,2,NULL,'ORDINECREATO','',2),(988975306,0,3,1,NULL,'ORDINECREATO','',3),(1037952144,0,1,2,NULL,'ORDINECREATO','',2),(1041075984,1,3,1,NULL,'RITIRATO','via non lo so',3),(1076499687,0,3,1,NULL,'ORDINECREATO','',3),(1084200673,0,3,1,NULL,'ORDINECREATO','',3),(1084642203,0,1,1,NULL,'ORDINECREATO','',1),(1101031878,0,3,1,NULL,'ORDINECREATO','',3),(1155573616,0,3,1,NULL,'ORDINECREATO','',3),(1161080300,0,3,1,NULL,'ORDINECREATO','',3),(1165388828,0,3,1,NULL,'ORDINECREATO','',3),(1177813728,0,1,2,NULL,'ORDINECREATO','',2),(1207102533,0,1,3,NULL,'ORDINECREATO','',7),(1242033325,0,1,2,NULL,'ORDINECREATO','',2),(1273285806,0,1,2,NULL,'ORDINECREATO','',2),(1293933543,0,3,1,NULL,'ORDINECREATO','',3),(1332575893,0,3,1,NULL,'ORDINECREATO','',3),(1342598036,0,3,1,NULL,'ORDINECREATO','',3),(1355123413,0,3,1,8,'ORDINECREATO','',3),(1400226144,0,3,1,NULL,'ORDINECREATO','',3),(1403109296,0,3,1,NULL,'ORDINECREATO','',3),(1439524581,0,3,1,NULL,'ORDINECREATO','',3),(1463071199,0,3,1,NULL,'ORDINECREATO','',3),(1517884445,0,3,1,NULL,'ORDINECREATO','',3),(1544939733,0,3,1,NULL,'ORDINECREATO','',3),(1567033517,0,3,1,NULL,'ORDINECREATO','',3),(1593159592,0,3,1,NULL,'ORDINECREATO','',3),(1601392698,0,1,1,NULL,'ORDINECREATO','',1),(1616503591,0,1,1,NULL,'ORDINECREATO','',1),(1633966367,0,3,1,NULL,'ORDINECREATO','',3),(1679687583,0,3,1,NULL,'ORDINECREATO','',3),(1726785265,0,1,1,NULL,'ORDINECREATO','',1),(1823973692,0,3,1,NULL,'ORDINECREATO','',3),(1892392092,0,3,1,NULL,'ORDINECREATO','',3),(1896376034,0,3,1,NULL,'ORDINECREATO','',3),(1923463285,0,3,1,NULL,'ORDINECREATO','',3),(1946231670,0,3,1,NULL,'ORDINECREATO','',3),(1954356758,0,1,1,NULL,'ORDINECREATO','',1),(1972676743,0,3,1,NULL,'ORDINECREATO','',3),(2014257791,0,3,1,NULL,'ORDINECREATO','',3),(2019544747,1,1,1,1,'DARITIRARE','',1),(2036392249,0,3,1,NULL,'ORDINECREATO','',3),(2071859901,0,1,1,NULL,'ORDINECREATO','',1),(2096173090,0,1,2,NULL,'ORDINECREATO','',2),(2128789781,0,1,1,NULL,'ORDINECREATO','',1);
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordineprodotto`
--

DROP TABLE IF EXISTS `ordineprodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordineprodotto` (
  `idO` int DEFAULT NULL,
  `idP` int DEFAULT NULL,
  `quantita` int DEFAULT NULL,
  KEY `prodOrd_idx` (`idP`),
  CONSTRAINT `prodOrd` FOREIGN KEY (`idP`) REFERENCES `prodotto` (`IDProdotto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordineprodotto`
--

LOCK TABLES `ordineprodotto` WRITE;
/*!40000 ALTER TABLE `ordineprodotto` DISABLE KEYS */;
INSERT INTO `ordineprodotto` VALUES (4,5,1),(4,1,1),(1,1,1),(1,5,2),(1972676743,9,1),(1972676743,9,1),(1076499687,9,1),(1076499687,9,1),(287999515,9,1),(287999515,9,1),(182312851,9,1),(182312851,9,1),(1342598036,9,1),(1342598036,9,1),(1041075984,9,1),(1041075984,9,1),(1463071199,9,1),(1463071199,9,1),(1155573616,9,1),(1155573616,9,1),(1165388828,9,1),(1165388828,9,1),(669339165,9,1),(669339165,9,1),(1355123413,9,1),(1355123413,9,1),(969718891,9,1),(969718891,9,1),(167453450,9,1),(167453450,9,1),(941109690,9,1),(941109690,9,1),(1923463285,9,1),(1923463285,9,1),(144480573,9,1),(144480573,9,1),(1593159592,9,1),(1593159592,9,1),(217832874,9,1),(217832874,9,1),(230383731,1,1),(230383731,1,1),(230383731,1,1),(10491638,6,1),(1097635220,6,1),(143203453,9,1),(143203453,9,1),(1933782786,6,1),(2005900581,6,1),(151386018,6,1),(1101031878,9,1),(1101031878,9,1),(262027411,6,1),(1365034187,6,1),(640940137,9,1),(640940137,9,1),(235817464,9,1),(235817464,9,1),(593203680,6,1),(336692250,9,1),(336692250,9,1),(1709635015,6,1),(331376671,9,1),(331376671,9,1),(813174239,6,1),(2019544747,6,1),(1954356758,6,1),(1293933543,9,1),(1293933543,9,1),(946589235,6,1),(988975306,9,1),(988975306,9,1),(2071859901,6,1),(865622479,9,1),(865622479,9,1),(1726785265,6,1),(92725389,9,1),(92725389,9,1),(1616503591,6,1),(1823973692,9,1),(1823973692,9,1),(268742107,6,1),(268742107,6,1),(268742107,6,1),(894433279,1,1),(894433279,1,1),(2128789781,1,1),(2128789781,1,1),(524346693,8,1),(1273285806,8,1),(346888735,8,1),(222014381,8,1),(207450802,8,1),(1544939733,9,1),(1544939733,9,1),(1242033325,8,1),(374481950,8,1),(1177813728,8,1),(2096173090,8,1),(1037952144,8,1),(802451457,8,1),(976320978,8,1),(719762994,1,1),(403832866,1,1),(1207102533,12,1),(590581539,12,1),(2036392249,9,1),(2036392249,9,1),(41326624,12,1),(86424723,12,1),(818804985,12,1),(539944542,12,1),(1601392698,1,1),(201251085,1,1),(368195194,1,1),(1084642203,1,1),(1679687583,9,1),(1679687583,9,1),(1332575893,9,1),(1332575893,9,1),(425574500,9,1),(425574500,9,1),(914187123,9,1),(914187123,9,1),(622148331,9,1),(622148331,9,1),(461636178,9,1),(461636178,9,1),(1633966367,9,1),(1633966367,9,1),(1896376034,9,1),(1896376034,9,1),(642335572,9,1),(642335572,9,1),(269427689,9,1),(269427689,9,1),(1161080300,9,1),(1161080300,9,1),(1439524581,9,1),(1439524581,9,1),(1400226144,9,1),(1400226144,9,1),(691455627,9,1),(691455627,9,1),(1084200673,9,1),(1084200673,9,1),(1946231670,9,1),(1946231670,9,1),(400316225,9,1),(400316225,9,1),(1403109296,9,1),(1403109296,9,1),(261972002,9,1),(261972002,9,1),(1567033517,9,1),(1567033517,9,1),(1517884445,9,1),(1517884445,9,1),(25186798,9,1),(25186798,9,1),(2014257791,9,1),(2014257791,9,1),(1892392092,9,1),(1892392092,9,1),(14745944,9,1),(14745944,9,1);
/*!40000 ALTER TABLE `ordineprodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotto`
--

DROP TABLE IF EXISTS `prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotto` (
  `IDProdotto` int NOT NULL,
  `quantitaDisponibile` int DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `descrizione` varchar(255) DEFAULT NULL,
  `IDPromozione` int DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `prezzo` float DEFAULT NULL,
  `IDNegozio` int DEFAULT NULL,
  `counterPVenduti` int DEFAULT NULL,
  PRIMARY KEY (`IDProdotto`),
  UNIQUE KEY `IDProdotto_UNIQUE` (`IDProdotto`),
  KEY `Promozione_idx` (`IDPromozione`),
  KEY `prodottoToNegozio_idx` (`IDNegozio`),
  CONSTRAINT `prodottoToNegozio` FOREIGN KEY (`IDNegozio`) REFERENCES `negozio` (`IDNegozio`),
  CONSTRAINT `Promozione` FOREIGN KEY (`IDPromozione`) REFERENCES `promozione` (`IDPromozione`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES (1,10,'lavastoviglie','e\' un\'astuccio che contiene cose',NULL,'libreria',7.8,1,124),(2,13,'sony','tv bravia',2010619043,'televisori',159.89,3,6),(3,5,'topolino','un topo che parla',8,'fumetto',45.36,5,7),(5,10,'frigorifero','si indossano',NULL,'indumenti',5,1,155),(6,10,'lavatrice','ci lavi i panni',NULL,'elettrodomestici',236.91,1,27),(7,5,'paperino','un papero che parla',4,'fumetto',9.9,5,7),(8,10,'salame','lo indossi di notte',3,'indumenti',15.6,2,18),(9,28,'samsung','tv smasung',NULL,'televisori',19.3,3,6),(10,20,'bravia','tv bravia',NULL,'televisori',9.99,3,6),(12,1,'racconti fantasy','è un libro, lo leggi',NULL,'libri',5.3,7,12),(13,2,'bomboniera','aaaaaaa',1856951315,'bomboniera',777,6,0),(14,22,'torta di mele','è fatta con le mele',NULL,'alimentari',10,2,0),(15,54,'lo hobbit','libro fantasy',NULL,'libro',9.99,4,0);
/*!40000 ALTER TABLE `prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promozione`
--

DROP TABLE IF EXISTS `promozione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promozione` (
  `IDPromozione` int NOT NULL,
  `stato` tinyint DEFAULT NULL,
  `sconto` int DEFAULT NULL,
  PRIMARY KEY (`IDPromozione`),
  UNIQUE KEY `IDPromozione_UNIQUE` (`IDPromozione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promozione`
--

LOCK TABLES `promozione` WRITE;
/*!40000 ALTER TABLE `promozione` DISABLE KEYS */;
INSERT INTO `promozione` VALUES (3,0,20),(4,1,10),(7,1,33),(8,1,20),(1688107884,1,8),(1856951315,0,12),(2010619043,1,8);
/*!40000 ALTER TABLE `promozione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-18 15:55:25
