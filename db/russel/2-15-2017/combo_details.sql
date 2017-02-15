-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 15, 2017 at 12:32 PM
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
-- Table structure for table `combo_details`
--

CREATE TABLE `combo_details` (
  `id` int(11) NOT NULL,
  `combo_id` int(11) DEFAULT NULL,
  `type` varchar(25) NOT NULL,
  `concession_product_id` int(11) DEFAULT '0',
  `product_quantity` int(3) DEFAULT NULL,
  `ticket_quantity` int(3) NOT NULL DEFAULT '1' COMMENT 'Ticket Id Will be add while 2nd pahse',
  `ticket_id` int(11) DEFAULT '0',
  `seat_type_id` int(11) DEFAULT '0',
  `created_by` int(11) DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Indexes for dumped tables
--

--
-- Indexes for table `combo_details`
--
ALTER TABLE `combo_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `combo_id` (`combo_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `combo_details`
--
ALTER TABLE `combo_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `combo_details`
--
ALTER TABLE `combo_details`
  ADD CONSTRAINT `combo_details_combo_id` FOREIGN KEY (`combo_id`) REFERENCES `combo` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
