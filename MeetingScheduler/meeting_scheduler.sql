-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 16, 2014 at 10:54 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `attendee`
--

CREATE TABLE IF NOT EXISTS `attendee` (
  `att_sch_id` int(32) NOT NULL,
  `att_emp_id` int(32) NOT NULL,
  KEY `att_sch_id` (`att_sch_id`,`att_emp_id`),
  KEY `att_emp_id` (`att_emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE IF NOT EXISTS `schedule` (
  `sch_id` int(32) NOT NULL AUTO_INCREMENT,
  `sch_start_time` date NOT NULL,
  `sch_end_time` date NOT NULL,
  PRIMARY KEY (`sch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
  ADD CONSTRAINT `attendee_ibfk_2` FOREIGN KEY (`att_emp_id`) REFERENCES `employee` (`emp_usr_id`),
  ADD CONSTRAINT `attendee_ibfk_1` FOREIGN KEY (`att_sch_id`) REFERENCES `schedule` (`sch_id`);

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
