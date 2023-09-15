CREATE TABLE `reservations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `location` varchar(80) NOT NULL,
  `customer_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id_idx` (`customer_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci