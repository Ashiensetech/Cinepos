-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 27, 2017 at 03:38 PM
-- Server version: 5.6.33-0ubuntu0.14.04.1
-- PHP Version: 5.6.23-1+deprecated+dontuse+deb.sury.org~trusty+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cinepos`
--

-- --------------------------------------------------------

--
-- Table structure for table `film_time`
--

CREATE TABLE IF NOT EXISTS `film_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `film_schedule_id` int(11) DEFAULT NULL,
  `film_id` int(11) DEFAULT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `film_schedule_id` (`film_schedule_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `film_time`
--
ALTER TABLE `film_time`
  ADD CONSTRAINT `film_time_film_schedule_id` FOREIGN KEY (`film_schedule_id`) REFERENCES `film_schedule` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
