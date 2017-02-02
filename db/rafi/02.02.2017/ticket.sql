-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 02, 2017 at 07:16 PM
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
-- Table structure for table `ticket`
--

CREATE TABLE IF NOT EXISTS `ticket` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `screen_seat_id` int(11) DEFAULT NULL,
  `film_time_id` int(11) DEFAULT NULL,
  `description` text,
  `annotation` varchar(100) DEFAULT NULL,
  `printed_price` decimal(8,2) NOT NULL,
  `sell_on_web` tinyint(1) NOT NULL,
  `sell_on_pos` tinyint(1) NOT NULL,
  `sell_on_kiosk` tinyint(1) NOT NULL,
  `vat_id` int(11) DEFAULT NULL,
  `is_child` tinyint(1) NOT NULL,
  `is_adult` tinyint(1) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `current_state` enum('AVAILABLE','BOOKED','SOLD') NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `seat_type_id` (`screen_seat_id`),
  KEY `film_schedule_id` (`film_time_id`),
  KEY `screen_seat_id` (`screen_seat_id`),
  KEY `film_time_id` (`film_time_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_film_time_id` FOREIGN KEY (`film_time_id`) REFERENCES `film_time` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `ticket_screen_seat_id` FOREIGN KEY (`screen_seat_id`) REFERENCES `screen_seat` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
