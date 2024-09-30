CREATE DATABASE `reservationdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `reservationdb`.`user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `role` int NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `reservationdb`.`professor` (
  `username` varchar(20) NOT NULL,
  `department` varchar(45) NOT NULL,
  `school` varchar(45) NOT NULL,
  `specialty` varchar(45) NOT NULL,
  `id` varchar(10) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  CONSTRAINT `professor_username` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `reservationdb`.`student` (
  `username` varchar(20) NOT NULL,
  `department` varchar(45) NOT NULL,
  `school` varchar(45) NOT NULL,
  `year` int NOT NULL,
  `id` varchar(10) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  CONSTRAINT `student_username` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `reservationdb`.`reservations` (
  `studid` varchar(20) NOT NULL,
  `profid` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `room` int NOT NULL,
  `id` varchar(45) NOT NULL,
  `accepted` int NOT NULL,
  `comment` varchar(50) DEFAULT NULL,
  `priority` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id_idx` (`studid`),
  KEY `professor_id_idx` (`profid`),
  CONSTRAINT `professor_id` FOREIGN KEY (`profid`) REFERENCES `professor` (`id`),
  CONSTRAINT `student_id` FOREIGN KEY (`studid`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `reservationdb`.`availability` (
  `profid` varchar(10) NOT NULL,
  `time` tinyint NOT NULL,
  `day` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `is_available` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `prof_idx` (`profid`),
  CONSTRAINT `prof_id` FOREIGN KEY (`profid`) REFERENCES `professor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
