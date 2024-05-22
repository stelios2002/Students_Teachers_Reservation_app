CREATE DATABASE `reservation_app` ;

CREATE TABLE `user` (
  `Id` varchar(9) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `professor` (
  `Id` varchar(9) NOT NULL,
  `Department` varchar(50) NOT NULL,
  `School` varchar(50) NOT NULL,
  `Specialty` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `ProfessorID` FOREIGN KEY (`Id`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `student` (
  `Id` varchar(9) NOT NULL,
  `Department` varchar(50) NOT NULL,
  `School` varchar(50) NOT NULL,
  `Year` int NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `StudentID` FOREIGN KEY (`Id`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `reservations` (
  `ReservationID` varchar(15) NOT NULL,
  `ProfessorID` varchar(9) NOT NULL,
  `StudentID` varchar(9) NOT NULL,
  `LocalDate` date NOT NULL,
  `LocalTime` time NOT NULL,
  `Room` int NOT NULL,
  PRIMARY KEY (`ReservationID`),
  CONSTRAINT `ResProfessorID` FOREIGN KEY (`ProfessorID`) REFERENCES `professor` (`Id`),
  CONSTRAINT `ResStudentID` FOREIGN KEY (`StudentID`) REFERENCES `student` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
