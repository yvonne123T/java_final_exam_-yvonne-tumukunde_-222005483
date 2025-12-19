-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 19, 2025 at 09:46 PM
-- Server version: 8.3.0
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `govtech_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `casetable`
--

DROP TABLE IF EXISTS `casetable`;
CREATE TABLE IF NOT EXISTS `casetable` (
  `CaseID` varchar(50) NOT NULL,
  `Attribute1` varchar(255) DEFAULT NULL,
  `Attribute2` varchar(255) DEFAULT NULL,
  `Attribute3` varchar(255) DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `OfficerID` varchar(50) DEFAULT NULL,
  `ServiceRequestID` varchar(50) DEFAULT NULL,
  `CaseType` varchar(100) DEFAULT NULL,
  `Priority` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CaseID`),
  KEY `OfficerID` (`OfficerID`),
  KEY `ServiceRequestID` (`ServiceRequestID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `casetable`
--

INSERT INTO `casetable` (`CaseID`, `Attribute1`, `Attribute2`, `Attribute3`, `CreatedAt`, `OfficerID`, `ServiceRequestID`, `CaseType`, `Priority`) VALUES
('CASE001', 'Open', 'Infrastructure', 'Assigned', '2025-10-27 17:22:49', 'OFF001', 'SR001', 'Public Works Case', 'High'),
('CASE002', 'Closed', 'Healthcare', 'Resolved', '2025-10-27 17:22:49', 'OFF002', 'SR002', 'Health Compliance', 'Medium'),
('CASE003', 'Open', 'Safety', 'Investigation', '2025-10-27 17:22:49', 'OFF003', 'SR003', 'Building Safety', 'Critical'),
('CASE004', 'In Review', 'Environment', 'Monitoring', '2025-10-27 17:22:49', 'OFF004', 'SR004', 'Environmental Complaint', 'Low'),
('CASE005', 'Completed', 'Transport', 'Approved', '2025-10-27 17:22:49', 'OFF005', 'SR005', 'Transportation Service', 'Medium'),
('CASE006', 'Open', 'Sanitation', 'Action Required', '2025-10-27 17:22:49', 'OFF006', 'SR006', 'Sanitation Services', 'High'),
('CASE007', 'Pending', 'Public Space', 'Assessment', '2025-10-27 17:22:49', 'OFF007', 'SR007', 'Public Facility Maintenance', 'Medium');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `DepartmentID` varchar(50) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Address` text,
  `Capacity` int DEFAULT NULL,
  `Manager` varchar(255) DEFAULT NULL,
  `Contact` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`DepartmentID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`DepartmentID`, `Name`, `Address`, `Capacity`, `Manager`, `Contact`) VALUES
('DEPT001', 'Public Works', '123 Main Street, City Center', 50, 'David Chen', '555-1001'),
('DEPT002', 'Health Services', '456 Health Ave, Medical District', 35, 'Dr. Maria Garcia', '555-1002'),
('DEPT003', 'Building Safety', '789 Safety Road, Industrial Zone', 25, 'James Wilson', '555-1003'),
('DEPT004', 'Environmental Protection', '321 Green Street, Eco Park', 20, 'Lisa Thompson', '555-1004'),
('DEPT005', 'Transportation', '654 Transit Lane, Hub Center', 40, 'Kevin Martinez', '555-1005');

-- --------------------------------------------------------

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
CREATE TABLE IF NOT EXISTS `document` (
  `DocumentID` varchar(50) NOT NULL,
  `Attribute1` varchar(255) DEFAULT NULL,
  `Attribute2` varchar(255) DEFAULT NULL,
  `Attribute3` varchar(255) DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `CaseID` varchar(50) DEFAULT NULL,
  `DocumentType` varchar(100) DEFAULT NULL,
  `FilePath` varchar(500) DEFAULT NULL,
  `UploaderID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DocumentID`),
  KEY `CaseID` (`CaseID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `document`
--

INSERT INTO `document` (`DocumentID`, `Attribute1`, `Attribute2`, `Attribute3`, `CreatedAt`, `CaseID`, `DocumentType`, `FilePath`, `UploaderID`) VALUES
('DOC001', 'Official', 'Report', 'Final', '2025-10-27 17:22:49', 'CASE001', 'Inspection Report', '/documents/case001/report1.pdf', 'OFF001'),
('DOC002', 'Supporting', 'Photo', 'Evidence', '2025-10-27 17:22:49', 'CASE001', 'Photographic Evidence', '/documents/case001/photo1.jpg', 'OFF001'),
('DOC003', 'Legal', 'Certificate', 'Approved', '2025-10-27 17:22:49', 'CASE002', 'Health Certificate', '/documents/case002/certificate.pdf', 'OFF002'),
('DOC004', 'Technical', 'Assessment', 'Preliminary', '2025-10-27 17:22:49', 'CASE003', 'Structural Assessment', '/documents/case003/assessment.pdf', 'OFF003'),
('DOC005', 'Administrative', 'Application', 'Processed', '2025-10-27 17:22:49', 'CASE005', 'Permit Application', '/documents/case005/application.pdf', 'OFF005'),
('DOC006', 'Evidence', 'Video', 'Recording', '2025-10-27 17:22:49', 'CASE004', 'Noise Recording', '/documents/case004/video1.mp4', 'CIT004'),
('DOC007', 'Official', 'Complaint', 'Filed', '2025-10-27 17:22:49', 'CASE007', 'Formal Complaint', '/documents/case007/complaint.pdf', 'CIT002');

-- --------------------------------------------------------

--
-- Table structure for table `officer`
--

DROP TABLE IF EXISTS `officer`;
CREATE TABLE IF NOT EXISTS `officer` (
  `OfficerID` varchar(50) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Identifier` varchar(100) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `Location` varchar(255) DEFAULT NULL,
  `Contact` varchar(100) DEFAULT NULL,
  `AssignedSince` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `DepartmentID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`OfficerID`),
  UNIQUE KEY `Identifier` (`Identifier`),
  KEY `DepartmentID` (`DepartmentID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `officer`
--

INSERT INTO `officer` (`OfficerID`, `Name`, `Identifier`, `Status`, `Location`, `Contact`, `AssignedSince`, `DepartmentID`) VALUES
('OFF001', 'Officer Alice Brown', 'OB-2024-001', 'Active', 'Field Office A', '555-2001', '2025-10-27 17:15:06', 'DEPT001'),
('OFF002', 'Officer Brian Davis', 'BD-2024-002', 'Active', 'Field Office B', '555-2002', '2025-10-27 17:15:06', 'DEPT002'),
('OFF003', 'Officer Carol Evans', 'CE-2024-003', 'On Leave', 'Field Office C', '555-2003', '2025-10-27 17:15:06', 'DEPT003'),
('OFF004', 'Officer Daniel Foster', 'DF-2024-004', 'Active', 'Field Office D', '555-2004', '2025-10-27 17:15:06', 'DEPT004'),
('OFF005', 'Officer Emily Green', 'EG-2024-005', 'Active', 'Field Office E', '555-2005', '2025-10-27 17:15:06', 'DEPT005'),
('OFF006', 'Officer Frank Harris', 'FH-2024-006', 'Training', 'Field Office A', '555-2006', '2025-10-27 17:15:06', 'DEPT001'),
('OFF007', 'Officer Grace Lee', 'GL-2024-007', 'Active', 'Field Office B', '555-2007', '2025-10-27 17:15:06', 'DEPT002');

-- --------------------------------------------------------

--
-- Table structure for table `officercase`
--

DROP TABLE IF EXISTS `officercase`;
CREATE TABLE IF NOT EXISTS `officercase` (
  `OfficerID` varchar(50) NOT NULL,
  `CaseID` varchar(50) NOT NULL,
  `AssignedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`OfficerID`,`CaseID`),
  KEY `CaseID` (`CaseID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `officercase`
--

INSERT INTO `officercase` (`OfficerID`, `CaseID`, `AssignedDate`) VALUES
('OFF001', 'CASE001', '2025-10-27 17:22:49'),
('OFF002', 'CASE002', '2025-10-27 17:22:49'),
('OFF003', 'CASE003', '2025-10-27 17:22:49'),
('OFF004', 'CASE004', '2025-10-27 17:22:49'),
('OFF005', 'CASE005', '2025-10-27 17:22:49'),
('OFF006', 'CASE006', '2025-10-27 17:22:49'),
('OFF007', 'CASE007', '2025-10-27 17:22:49'),
('OFF001', 'CASE003', '2025-10-27 17:22:49'),
('OFF002', 'CASE005', '2025-10-27 17:22:49'),
('OFF004', 'CASE007', '2025-10-27 17:22:49');

-- --------------------------------------------------------

--
-- Table structure for table `servicerequest`
--

DROP TABLE IF EXISTS `servicerequest`;
CREATE TABLE IF NOT EXISTS `servicerequest` (
  `ServiceRequestID` varchar(50) NOT NULL,
  `Attribute1` varchar(255) DEFAULT NULL,
  `Attribute2` varchar(255) DEFAULT NULL,
  `Attribute3` varchar(255) DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `CitizenID` varchar(50) DEFAULT NULL,
  `RequestType` varchar(100) DEFAULT NULL,
  `Description` text,
  `Status` varchar(50) DEFAULT 'Pending',
  PRIMARY KEY (`ServiceRequestID`),
  KEY `CitizenID` (`CitizenID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `servicerequest`
--

INSERT INTO `servicerequest` (`ServiceRequestID`, `Attribute1`, `Attribute2`, `Attribute3`, `CreatedAt`, `CitizenID`, `RequestType`, `Description`, `Status`) VALUES
('SR001', 'Urgent', 'Infrastructure', 'High Priority', '2025-10-27 17:15:06', 'CIT001', 'Road Repair', 'Pothole on Main Street causing traffic issues', 'In Progress'),
('SR002', 'Routine', 'Healthcare', 'Medium Priority', '2025-10-27 17:15:06', 'CIT002', 'Health Inspection', 'Request for restaurant health certificate renewal', 'Pending'),
('SR003', 'Emergency', 'Safety', 'Critical', '2025-10-27 17:15:06', 'CIT003', 'Building Inspection', 'Crack observed in apartment building foundation', 'Completed'),
('SR004', 'Complaint', 'Environment', 'Low Priority', '2025-10-27 17:15:06', 'CIT004', 'Noise Pollution', 'Construction noise during non-working hours', 'Under Review'),
('SR005', 'Inquiry', 'Transport', 'Medium Priority', '2025-10-27 17:15:06', 'CIT005', 'Parking Permit', 'Application for residential parking permit', 'Approved'),
('SR006', 'Urgent', 'Sanitation', 'High Priority', '2025-10-27 17:15:06', 'CIT001', 'Garbage Collection', 'Missed garbage collection for 3 consecutive days', 'In Progress'),
('SR007', 'Complaint', 'Public Space', 'Medium Priority', '2025-10-27 17:15:06', 'CIT002', 'Park Maintenance', 'Broken playground equipment in city park', 'Pending');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
