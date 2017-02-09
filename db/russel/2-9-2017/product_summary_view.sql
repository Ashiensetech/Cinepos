-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 09, 2017 at 07:06 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.5




/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cinepos`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `product_summary_view`
--

-- --------------------------------------------------------

--
-- Structure for view `product_summary_view`
--
DROP TABLE IF EXISTS `product_summary_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `product_summary_view`  AS  select `sd`.`id` AS `id`,`cp`.`name` AS `product_name`,`sd`.`quantity` AS `qty`,`sd`.`unit_selling_amount` AS `selling_price`,(`sd`.`quantity` * `sd`.`unit_selling_amount`) AS `total`,`sd`.`created_at` AS `created_at` from (`sells_details` `sd` join `concession_product` `cp` on((`sd`.`concession_product_id` = `cp`.`id`))) where (`sd`.`selling_type` = 'product') group by `sd`.`id` ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
