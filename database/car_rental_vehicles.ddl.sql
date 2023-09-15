CREATE TABLE `vehicles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `make` varchar(60) NOT NULL,
  `model` varchar(60) NOT NULL,
  `type` varchar(60) NOT NULL,
  `location` varchar(80) NOT NULL,
  `reservation_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `reservation_id_idx` (`reservation_id`),
  CONSTRAINT `reservation_id` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci