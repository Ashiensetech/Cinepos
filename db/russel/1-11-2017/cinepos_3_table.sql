-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 11, 2017 at 12:26 PM
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
-- Table structure for table `circuit`
--

CREATE TABLE `circuit` (
  `id` int(11) NOT NULL,
  `site_code` varchar(60) DEFAULT NULL,
  `site_name` varchar(60) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(60) NOT NULL,
  `country` varchar(60) NOT NULL,
  `website` varchar(150) NOT NULL,
  `phone_no` varchar(20) NOT NULL,
  `screen_no` int(11) NOT NULL,
  `booking_cancellation_time` time NOT NULL,
  `refund_deduction_percentage` decimal(8,2) NOT NULL,
  `refund_cancellation_time` time NOT NULL,
  `created_by` int(11) DEFAULT '0',
  `updated_by` int(11) DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `circuit`
--

INSERT INTO `circuit` (`id`, `site_code`, `site_name`, `address`, `city`, `country`, `website`, `phone_no`, `screen_no`, `booking_cancellation_time`, `refund_deduction_percentage`, `refund_cancellation_time`, `created_by`, `updated_by`, `created_at`, `updated_at`) VALUES
(5, 'VPGCJ005', 'Sarwar Sikder', 'Nikunja-2', 'Dhaka1', 'Bangladesh', 'www.aiub.edu', '1713523713', 8, '13:00:00', '6.00', '01:00:00', 1, 1, '2017-01-11 11:17:11', '2017-01-11 11:20:44');

-- --------------------------------------------------------

--
-- Table structure for table `concession_product_category`
--

CREATE TABLE `concession_product_category` (
  `id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `name` int(11) NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `distributors`
--

CREATE TABLE `distributors` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `primary_email` varchar(200) NOT NULL,
  `secondary_email` varchar(200) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `distributors`
--

INSERT INTO `distributors` (`id`, `name`, `primary_email`, `secondary_email`, `phone`, `address`, `description`, `status`, `created_by`, `updated_by`, `created_at`, `updated_at`) VALUES
(1, 'Sarwar Sikder', 'russelaiub08@gmail.com', 'russelaiub081@gmail.com', '01713523713', 'Dhaka', 'Dhaka DhakaDhakaDhakaDhakaDhakaDhakaDhakaDhakaDhakaDhakaDhakaDhakaDhaka', 0, 0, 1, '2017-01-04 10:21:51', '2017-01-06 11:50:42'),
(2, 'Sarwar1 Sikder1', 'russelaiub087@gmail.com', 'russelaiub0817@gmail.com', '01713523713', 'Dhaka', 'Dhaka', 0, 0, 0, '2017-01-04 10:21:51', NULL),
(3, 'Sarwar2 Sikder2', 'russelaiub087@gmail.com', 'russelaiub0817@gmail.com', '01713523713', 'Dhaka', 'Dhaka', 0, 0, 0, '2017-01-04 11:46:52', NULL),
(4, 'russel1 sikder2', 'russel1@gmail.com', 'russel2@gmail.com', '01713523716', 'Dhaka9', 'Hello World! hellll leoooo loeleoelo leo leo leo leo leo leo leo leo leo elo ', 1, 1, 1, '2017-01-05 07:21:57', '2017-01-06 09:34:30'),
(5, 'russel1', 'russel@gmail.com', 'russel@gmail.com', '01713523713', 'Dhaka', ' Hello World! Hello World! Hello World! Hello World!Hello World!Hello World!Hello World!Hello World! Hello World!', 1, 1, 1, '2017-01-05 07:17:48', '2017-01-06 11:32:28'),
(6, 'russel1', 'russel@gmail.com', 'russel@gmail.com', '01713523713', 'Dhaka', 'Hello World!', 1, 1, 0, '2017-01-05 07:18:40', NULL),
(7, 'russel1', 'russel@gmail.com', 'russel@gmail.com', '01713523713', 'Dhaka', 'Hello World!   Hello World! Hello World! Hello World! Hello World! Hello World! Hello World! Hello World! Hello World! Hello World! ', 0, 1, 1, '2017-01-05 07:21:18', '2017-01-06 11:35:18'),
(8, 'Shawon1', 'russelaiub081@gmail.com', 'russelaiub084@gmail.com', '1713523717', 'Nikunja-21', 'Dhaka ffdf fdfd fdf fdfd fdf dfd fd fdfd fdf dfd fdf dfdf df ', 1, 1, 1, '2017-01-06 05:58:12', '2017-01-06 09:36:55'),
(9, 'Shawon1', 'russelaiub081@gmail.com', 'russelaiub084@gmail.com', '1713523713', 'Nikunja-2', 'Dhaka fdfdfdf dfd fdfdfd fdf dfd fdfd fdf dfd fdf dfd fd fdf df dfd df dfd df dfd ', 1, 1, 1, '2017-01-06 05:59:41', '2017-01-09 11:35:19'),
(10, 'sarwar sikder raza', 'raza@gmail.com', 'raza1@gmail.com', '01713523713', 'dhaka', 'dhaka bangladesh , pbna dhaka khaka ,,,,, google dhaka ... hello', 1, 1, 1, '2017-01-06 08:08:11', '2017-01-09 11:28:42'),
(11, 'raton sikeder1', 'russelaiub081@gmail.com', 'russelaiub0812@gmail.com', '1713523711', 'Nikunja-1', 'hgh hgh ghgh hg hghg gh ghgh ghgh ghgh ghg hghg hgh ghg hghg hgh ghg hgh ghghghg hgh gh ghgh1', 0, 1, 1, '2017-01-06 09:32:32', '2017-01-06 09:36:10'),
(12, 'sarwar Hossain fdfdf', 'russelaiub08@gmail.com', 'russelaiub08@gmail.com', '1713523713', 'Nikunja-2', 'fdfdfdfdfd fdfd f fdf fd fdfd fd ffd ffdf dfd fdf dfd fd', 0, 1, 0, '2017-01-06 11:25:41', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `terminal`
--

CREATE TABLE `terminal` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `ip_address` varchar(100) NOT NULL,
  `terminal_code` varchar(11) DEFAULT NULL,
  `type` enum('pos','kiosk') NOT NULL,
  `status` tinyint(4) NOT NULL,
  `created_by` int(11) DEFAULT '0',
  `updated_by` int(11) DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `terminal`
--

INSERT INTO `terminal` (`id`, `name`, `ip_address`, `terminal_code`, `type`, `status`, `created_by`, `updated_by`, `created_at`, `updated_at`) VALUES
(1, 'gfgfgfgf', '192.168.1.30', 'FAB001', 'pos', 1, 1, 1, '2017-01-10 10:09:09', '2017-01-10 10:40:14'),
(2, 'wsit', '192.168.1.10', 'OVZ002', 'kiosk', 1, 1, 1, '2017-01-10 10:39:04', '2017-01-10 10:39:04'),
(3, 'sarwar Hossain', '192.168.1.10', 'TIXM003', 'pos', 1, 1, 1, '2017-01-10 10:48:45', '2017-01-10 10:48:45');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `circuit`
--
ALTER TABLE `circuit`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `concession_product_category`
--
ALTER TABLE `concession_product_category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `parent_id` (`parent_id`);

--
-- Indexes for table `distributors`
--
ALTER TABLE `distributors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `terminal`
--
ALTER TABLE `terminal`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `circuit`
--
ALTER TABLE `circuit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `concession_product_category`
--
ALTER TABLE `concession_product_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `distributors`
--
ALTER TABLE `distributors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `terminal`
--
ALTER TABLE `terminal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
