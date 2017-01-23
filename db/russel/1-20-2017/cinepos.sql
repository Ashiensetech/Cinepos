-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 20, 2017 at 01:23 PM
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
(1, 'fdfdfdfd', 'fdfdfdfd', 'product', '2017-01-20', '2017-01-20', '45.00', 1, 1, '2017-01-20 11:24:42'),
(2, 'Tanveer', 'fdfdfdf', 'product', '2016-12-26', '2017-02-04', '45.00', 1, 1, '2017-01-20 11:46:10'),
(3, 'dsdsds', 'dsdsdsd', 'product', '2017-01-20', '2017-02-03', '45.00', 1, 0, '2017-01-20 12:20:19'),
(4, 'Tanveer', 'fdfdfdfd', 'product', '2017-01-04', '2017-02-04', '45.00', 1, 0, '2017-01-20 12:21:04'),
(5, 'WSIT', 'ffdfdfd fdfdfdfd', 'product', '2016-12-27', '2017-01-27', '34.00', 1, 0, '2017-01-20 12:21:40');

-- --------------------------------------------------------

--
-- Table structure for table `combo_product`
--

CREATE TABLE `combo_product` (
  `id` int(11) NOT NULL,
  `combo_id` int(11) DEFAULT NULL,
  `type` varchar(25) NOT NULL,
  `concession_product_id` int(11) DEFAULT NULL,
  `ticket_id` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `combo_product`
--

INSERT INTO `combo_product` (`id`, `combo_id`, `type`, `concession_product_id`, `ticket_id`, `created_by`, `created_at`) VALUES
(1, 1, 'product', 2, NULL, 0, '2017-01-20 11:24:42'),
(2, 2, 'product', 2, NULL, 0, '2017-01-20 11:46:10'),
(3, 2, 'product', 3, NULL, 0, '2017-01-20 11:46:10'),
(4, 3, 'product', 1, NULL, 0, '2017-01-20 12:20:19'),
(5, 3, 'product', 2, NULL, 0, '2017-01-20 12:20:19'),
(6, 4, 'product', 2, NULL, 0, '2017-01-20 12:21:04'),
(7, 4, 'product', 3, NULL, 0, '2017-01-20 12:21:04'),
(8, 5, 'product', 1, NULL, 0, '2017-01-20 12:21:40'),
(9, 5, 'product', 3, NULL, 0, '2017-01-20 12:21:40');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `combo`
--
ALTER TABLE `combo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `combo_product`
--
ALTER TABLE `combo_product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `combo`
--
ALTER TABLE `combo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `combo_product`
--
ALTER TABLE `combo_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
