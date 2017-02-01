-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 01, 2017 at 01:47 PM
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
-- Table structure for table `combo`
--

CREATE TABLE `combo` (
  `id` int(11) NOT NULL,
  `combo_name` varchar(150) NOT NULL,
  `details` varchar(255) NOT NULL,
  `type` varchar(20) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `combo`
--

INSERT INTO `combo` (`id`, `combo_name`, `details`, `type`, `start_date`, `end_date`, `price`, `created_by`, `status`, `created_at`) VALUES
(8, 'fdfdf', 'fdfdf', 'product', '2017-01-24', '2017-01-25', '34.00', 1, 1, '2017-01-23 11:08:42'),
(9, 'jjj', 'jjjhj jhjh jhjh jh', 'product', '2017-02-01', '2017-02-28', '56.89', 1, 1, '2017-02-01 05:36:35'),
(10, 'Buber', 'fdfd fdfd fdfdfd', 'product', '2017-02-01', '2017-02-28', '12.36', 1, 1, '2017-02-01 07:13:20'),
(11, 'gfgfg', 'gfgfg', 'product', '2017-02-01', '2017-02-23', '45.00', 1, 1, '2017-02-01 07:14:58'),
(12, 'hghgh', 'hghghg', 'product', '2017-02-01', '2017-02-28', '56.00', 1, 1, '2017-02-01 07:16:56'),
(13, 'hghgh', 'hghghg', 'product', '2017-02-01', '2017-02-28', '56.00', 1, 1, '2017-02-01 07:17:41'),
(14, 'gfgf', 'gfgfg gfgf gfg gfgf', 'product', '2017-02-01', '2017-02-23', '56.00', 1, 1, '2017-02-01 07:19:24'),
(15, 'vccvc', 'vcvcv', 'product', '2017-02-01', '2017-02-28', '545.00', 1, 1, '2017-02-01 07:25:24'),
(16, 'Combo TEST', 'dsdsd sd sdsds', 'product', '2017-02-01', '2017-02-28', '34.00', 1, 1, '2017-02-01 07:26:15');

-- --------------------------------------------------------

--
-- Table structure for table `combo_details`
--

CREATE TABLE `combo_details` (
  `id` int(11) NOT NULL,
  `combo_id` int(11) DEFAULT NULL,
  `type` varchar(25) NOT NULL,
  `concession_product_id` int(11) DEFAULT '0',
  `ticket_id` int(11) DEFAULT '0',
  `seat_type_id` int(11) DEFAULT '0',
  `created_by` int(11) DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `combo_details`
--

INSERT INTO `combo_details` (`id`, `combo_id`, `type`, `concession_product_id`, `ticket_id`, `seat_type_id`, `created_by`, `created_at`) VALUES
(24, 8, 'product', 3, 0, 2, 0, '2017-02-01 09:10:27'),
(25, 9, 'product', 1, 0, 2, 0, '2017-02-01 09:11:26'),
(26, 9, 'product', 3, 0, 2, 0, '2017-02-01 09:11:30'),
(27, 10, 'product', 1, 0, 2, 0, '2017-02-01 09:11:34'),
(28, 10, 'product', 2, 0, 2, 0, '2017-02-01 09:11:39'),
(29, 11, 'product', 1, 0, 2, 0, '2017-02-01 09:11:45'),
(30, 12, 'product', 3, 0, 2, 0, '2017-02-01 09:11:49'),
(31, 13, 'product', 3, 0, 2, 0, '2017-02-01 09:11:51'),
(32, 14, 'product', 2, 0, 2, 0, '2017-02-01 09:11:54'),
(33, 15, 'product', 2, 0, 2, 0, '2017-02-01 09:10:35'),
(34, 16, 'product', 3, 0, 2, 0, '2017-02-01 09:10:33'),
(35, 16, 'product', 1, 0, 2, 0, '2017-02-01 09:10:32');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `combo`
--
ALTER TABLE `combo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `combo_details`
--
ALTER TABLE `combo_details`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `combo`
--
ALTER TABLE `combo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `combo_details`
--
ALTER TABLE `combo_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
