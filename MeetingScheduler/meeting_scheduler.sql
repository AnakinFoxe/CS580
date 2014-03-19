-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 19, 2014 at 03:14 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `meeting_scheduler`
--
CREATE DATABASE IF NOT EXISTS `meeting_scheduler` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `meeting_scheduler`;

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--

CREATE TABLE IF NOT EXISTS `administrator` (
  `adm_usr_id` int(32) NOT NULL,
  `adm_description` varchar(1024) NOT NULL,
  KEY `adm_usr_id` (`adm_usr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`adm_usr_id`, `adm_description`) VALUES
(2, 'I''m your master!');

-- --------------------------------------------------------

--
-- Table structure for table `attendee`
--

CREATE TABLE IF NOT EXISTS `attendee` (
  `att_sch_id` int(32) NOT NULL,
  `att_emp_id` int(32) NOT NULL,
  `att_accept` varchar(32) DEFAULT NULL,
  KEY `att_sch_id` (`att_sch_id`,`att_emp_id`),
  KEY `att_emp_id` (`att_emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendee`
--

INSERT INTO `attendee` (`att_sch_id`, `att_emp_id`, `att_accept`) VALUES
(1, 1, 'YES'),
(1, 3, 'YES'),
(1, 4, 'YES'),
(1, 5, 'YES'),
(6, 4, 'YES'),
(6, 5, 'YES'),
(7, 3, 'YES'),
(7, 4, 'YES'),
(7, 5, 'YES'),
(8, 1, 'YES'),
(8, 4, 'YES'),
(8, 5, 'YES');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `emp_usr_id` int(32) NOT NULL,
  `emp_first_name` varchar(255) DEFAULT NULL,
  `emp_middle_name` varchar(255) DEFAULT NULL,
  `emp_last_name` varchar(255) NOT NULL,
  `emp_title` varchar(255) DEFAULT NULL,
  `emp_position` varchar(255) DEFAULT NULL,
  `emp_email` varchar(255) NOT NULL,
  KEY `emp_usr_id` (`emp_usr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`emp_usr_id`, `emp_first_name`, `emp_middle_name`, `emp_last_name`, `emp_title`, `emp_position`, `emp_email`) VALUES
(1, 'Xing', NULL, 'Hu', NULL, 'Manager', 'xingh@csupomona.edu'),
(3, 'Yiming', NULL, 'Shan', NULL, NULL, 'shanyiming@hotmail.com'),
(4, 'Brian', NULL, 'Truong', NULL, NULL, 'brntruong@gmail.com'),
(5, 'Lupe', NULL, 'Talavera', NULL, NULL, 'lupe.talavera@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `meeting`
--

CREATE TABLE IF NOT EXISTS `meeting` (
  `met_sch_id` int(32) NOT NULL,
  `met_rom_id` int(32) NOT NULL,
  `met_emp_id` int(32) NOT NULL,
  `emt_description` varchar(1024) DEFAULT NULL,
  KEY `met_sch_id` (`met_sch_id`,`met_rom_id`,`met_emp_id`),
  KEY `met_rom_id` (`met_rom_id`),
  KEY `met_emp_id` (`met_emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meeting`
--

INSERT INTO `meeting` (`met_sch_id`, `met_rom_id`, `met_emp_id`, `emt_description`) VALUES
(1, 1, 1, 'first meeting for cs 580'),
(6, 3, 1, NULL),
(7, 3, 1, NULL),
(8, 1, 3, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `personal`
--

CREATE TABLE IF NOT EXISTS `personal` (
  `psn_sch_id` int(32) NOT NULL,
  `psn_emp_id` int(32) NOT NULL,
  `psn_description` varchar(1024) DEFAULT NULL,
  KEY `psn_sch_id` (`psn_sch_id`,`psn_emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `rom_id` int(32) NOT NULL AUTO_INCREMENT,
  `rom_name` varchar(255) NOT NULL,
  `rom_capacity` int(32) NOT NULL,
  PRIMARY KEY (`rom_id`),
  UNIQUE KEY `rom_name` (`rom_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`rom_id`, `rom_name`, `rom_capacity`) VALUES
(1, 'cs 580', 4),
(2, 'cs 535', 2),
(3, 'cs 599', 10);

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE IF NOT EXISTS `schedule` (
  `sch_id` int(32) NOT NULL AUTO_INCREMENT,
  `sch_start_time` datetime NOT NULL,
  `sch_end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`sch_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`sch_id`, `sch_start_time`, `sch_end_time`) VALUES
(1, '2014-03-15 10:00:00', '2014-03-15 11:00:00'),
(6, '2014-03-16 13:00:00', '2014-03-16 14:00:00'),
(7, '2014-03-16 11:00:00', '2014-03-16 12:00:00'),
(8, '2014-03-16 21:00:00', '2014-03-16 22:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `usr_id` int(32) NOT NULL AUTO_INCREMENT,
  `usr_username` varchar(255) NOT NULL,
  `usr_password` varchar(255) NOT NULL,
  PRIMARY KEY (`usr_id`),
  UNIQUE KEY `usr_username` (`usr_username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`usr_id`, `usr_username`, `usr_password`) VALUES
(1, 'xingh', 'aaa'),
(2, 'admin', 'admin'),
(3, 'yiming', 'yiming'),
(4, 'brian', 'brian'),
(5, 'lupe', 'lupe');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `administrator`
--
ALTER TABLE `administrator`
  ADD CONSTRAINT `administrator_ibfk_1` FOREIGN KEY (`adm_usr_id`) REFERENCES `user` (`usr_id`);

--
-- Constraints for table `attendee`
--
ALTER TABLE `attendee`
  ADD CONSTRAINT `attendee_ibfk_1` FOREIGN KEY (`att_sch_id`) REFERENCES `schedule` (`sch_id`),
  ADD CONSTRAINT `attendee_ibfk_2` FOREIGN KEY (`att_emp_id`) REFERENCES `employee` (`emp_usr_id`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`emp_usr_id`) REFERENCES `user` (`usr_id`);

--
-- Constraints for table `meeting`
--
ALTER TABLE `meeting`
  ADD CONSTRAINT `meeting_ibfk_1` FOREIGN KEY (`met_sch_id`) REFERENCES `schedule` (`sch_id`),
  ADD CONSTRAINT `meeting_ibfk_2` FOREIGN KEY (`met_emp_id`) REFERENCES `employee` (`emp_usr_id`),
  ADD CONSTRAINT `meeting_ibfk_3` FOREIGN KEY (`met_rom_id`) REFERENCES `room` (`rom_id`);

--
-- Constraints for table `personal`
--
ALTER TABLE `personal`
  ADD CONSTRAINT `personal_ibfk_1` FOREIGN KEY (`psn_sch_id`) REFERENCES `schedule` (`sch_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
