-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 09, 2017 at 06:40 AM
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
-- Table structure for table `sells_details`
--

CREATE TABLE `sells_details` (
  `id` int(11) NOT NULL,
  `sell_id` int(11) DEFAULT NULL,
  `concession_product_id` int(11) DEFAULT NULL,
  `combo_id` int(11) DEFAULT NULL,
  `seat_type_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `unit_selling_amount` decimal(8,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `selling_type` varchar(25) NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sells_details`
--

INSERT INTO `sells_details` (`id`, `sell_id`, `concession_product_id`, `combo_id`, `seat_type_id`, `user_id`, `unit_selling_amount`, `quantity`, `selling_type`, `created_by`, `created_at`) VALUES
(1, 1, 2, NULL, NULL, 1, '45.00', 3, 'product', 1, '2017-02-07 12:53:33'),
(2, 1, 3, NULL, NULL, 1, '45.00', 6, 'product', 1, '2017-02-07 12:53:33'),
(3, 4, 3, 0, NULL, 1, '45.00', 3, 'combo', 1, '2017-02-08 09:25:58'),
(4, 4, NULL, NULL, 3, 1, '45.00', 6, 'ticket', 1, '2017-02-07 12:58:16');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sells_details`
--
ALTER TABLE `sells_details`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sells_details`
--
ALTER TABLE `sells_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
