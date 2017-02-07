-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 07, 2017 at 05:06 PM
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
-- Structure for view `BOX_OFFICE_FILM_VIEW`
--

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `BOX_OFFICE_FILM_VIEW` AS select `film`.`id` AS `id`,`film`.`name` AS `name`,`film`.`distributor_id` AS `distributor_id`,`film`.`rating` AS `rating`,`film`.`duration_hour` AS `duration_hour`,`film`.`duration_min` AS `duration_min`,`film`.`status` AS `status`,`film`.`start_date` AS `start_date`,`film`.`end_date` AS `end_date`,`film`.`is_price_shift` AS `is_price_shift`,`film`.`created_by` AS `created_by`,`film`.`created_at` AS `created_at` from `film`;

--
-- VIEW  `BOX_OFFICE_FILM_VIEW`
-- Data: None
--


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
