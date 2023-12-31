-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: absolute_minds
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `bookId` int NOT NULL AUTO_INCREMENT,
  `author` varchar(255) NOT NULL,
  `availability` int NOT NULL DEFAULT '1',
  `bookName` varchar(255) NOT NULL,
  `genre` varchar(255) NOT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Harper Lee',1,'To Kill a Mockingbird','Fiction'),(2,'George Orwell',1,'1984','Fiction'),(3,'J.D. Salinger',1,'The Catcher in the Rye','Fiction'),(4,'Margaret Atwood',1,'The Handmaid\'s Tale','Fiction'),(5,'Dan Brown',1,'The Da Vinci Code','Mystery'),(6,'Agatha Christie',1,'And Then There Were None','Mystery'),(7,'Arthur Conan Doyle',1,'The Hound of the Baskervilles ','Mystery'),(8,'Gillian Flynn',1,'Gone Giri','Mystery'),(9,'Paula Hawkins',1,'The Girl on the Train','Thriller'),(10,'Thomas Harris',1,'The Silence of the Lambs','Thriller'),(11,'Robert Ludlum',1,'The Bourne Identity','Thriller'),(12,'Stieg Larsson',1,'The Girl with the Dragon Tattoo','Thriller'),(13,'J.R.R. Tolkien',1,'The Lord of the Rings: The Fellowship of the Ring','Fantasy'),(14,'George R.R. Martin',1,'A Game of Thrones','Fantasy'),(15,'Brandon Sanderson',1,'Mistborn: The Final Empire','Fantasy'),(16,'J.K. Rowling',1,'Harry Potter and the Philosopher\'s Stone','Fantasy'),(17,'Alex Michaelides',1,'The Silent Patient','Thriller'),(18,'Patrick Rothfuss',0,'The Name of the Wind','Fantasy');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `librarian`
--

DROP TABLE IF EXISTS `librarian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `librarian` (
  `id` int NOT NULL AUTO_INCREMENT,
  `isDeleted` int NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rcqos3h3puy2cutjelt95a566` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `librarian`
--

LOCK TABLES `librarian` WRITE;
/*!40000 ALTER TABLE `librarian` DISABLE KEYS */;
INSERT INTO `librarian` VALUES (1,0,'Aniket Sengar','Ani','Ani'),(2,0,'Willy Dcuz','Willy','Willy');
/*!40000 ALTER TABLE `librarian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental`
--

DROP TABLE IF EXISTS `rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rental` (
  `rentalId` int NOT NULL AUTO_INCREMENT,
  `rentedDate` date NOT NULL,
  `returnDate` date NOT NULL,
  `book_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`rentalId`),
  KEY `FK4gi44e6r300a8l6fnw0ss0be6` (`book_id`),
  KEY `FKm6f1r8a0m7w8n5upyjslprj25` (`user_id`),
  CONSTRAINT `FK4gi44e6r300a8l6fnw0ss0be6` FOREIGN KEY (`book_id`) REFERENCES `book` (`bookId`),
  CONSTRAINT `FKm6f1r8a0m7w8n5upyjslprj25` FOREIGN KEY (`user_id`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental`
--

LOCK TABLES `rental` WRITE;
/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
INSERT INTO `rental` VALUES (6,'2023-07-25','2023-08-01',18,1);
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `isDeleted` int NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `UK_4bakctviobmdk6ddh2nwg08c2` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,0,'Emma Willamson','E','Emma'),(2,0,'Murunal Thakur','Muru','Murunal');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-13  0:10:20
