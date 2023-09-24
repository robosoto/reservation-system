CREATE TABLE `reservations` (
  `id` varchar(30) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `location` varchar(80) NOT NULL,
  `customer_id` int NOT NULL,
  `vehicle_id` int NOT NULL,
  `status` varchar(30) DEFAULT 'Reserved',
  PRIMARY KEY (`id`),
  KEY `customer_id_idx` (`customer_id`),
  KEY `vehicle_id_idx` (`vehicle_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vehicle_id` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci