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
-- Structure for view `BOX_OFFICE_SCHEDULING_VIEW`
--

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `BOX_OFFICE_SCHEDULING_VIEW` AS select distinct `f`.`id` AS `id`,`f`.`id` AS `film_id`,`ft`.`film_schedule_id` AS `schedule_id` from (`film` `f` join `film_time` `ft` on((`ft`.`film_id` = `f`.`id`))) order by `ft`.`film_schedule_id` desc;

--
-- VIEW  `BOX_OFFICE_SCHEDULING_VIEW`
-- Data: None
--


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
