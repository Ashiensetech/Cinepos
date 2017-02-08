-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 08, 2017 at 02:55 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cinepos`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `conc_sales_by_operator_view`
--
CREATE TABLE `conc_sales_by_operator_view` (
`Sd_Id` int(11)
,`Product` varchar(255)
,`QTY` int(11)
,`UnitPrice` decimal(8,2)
,`Gross` decimal(18,2)
,`Oparetor` int(11)
,`CreateDate` timestamp
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `product_summary_view`
--
CREATE TABLE `product_summary_view` (
`ProductId` int(11)
,`Product` varchar(255)
,`Price` decimal(8,2)
,`StockUnit` int(11)
,`StockValue` decimal(18,2)
,`CreateDate` timestamp
,`CreatedBy` int(11)
);

-- --------------------------------------------------------

--
-- Structure for view `conc_sales_by_operator_view`
--
DROP TABLE IF EXISTS `conc_sales_by_operator_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `conc_sales_by_operator_view`  AS  select `sd`.`id` AS `Sd_Id`,`cp`.`name` AS `Product`,`sd`.`quantity` AS `QTY`,`sd`.`unit_selling_amount` AS `UnitPrice`,(`sd`.`quantity` * `sd`.`unit_selling_amount`) AS `Gross`,`sd`.`created_by` AS `Oparetor`,`sd`.`created_at` AS `CreateDate` from (`sells_details` `sd` join `concession_product` `cp` on((`sd`.`concession_product_id` = `cp`.`id`))) where (`sd`.`selling_type` = 'product') group by `sd`.`id` ;

-- --------------------------------------------------------

--
-- Structure for view `product_summary_view`
--
DROP TABLE IF EXISTS `product_summary_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `product_summary_view`  AS  select `concession_product`.`id` AS `ProductId`,`concession_product`.`name` AS `Product`,`concession_product`.`buying_price` AS `Price`,`concession_product`.`unit` AS `StockUnit`,(`concession_product`.`buying_price` * `concession_product`.`unit`) AS `StockValue`,`concession_product`.`created_at` AS `CreateDate`,`concession_product`.`created_by` AS `CreatedBy` from `concession_product` ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
