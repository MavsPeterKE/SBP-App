-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 10, 2018 at 07:04 AM
-- Server version: 5.6.39-cll-lve
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cyressou_retrofit`
--

-- --------------------------------------------------------

--
-- Table structure for table `complains`
--

CREATE TABLE `complains` (
  `complain_id` int(255) NOT NULL,
  `vehicle_number` varchar(255) NOT NULL,
  `complain_body` varchar(255) NOT NULL,
  `sacco_id` int(255) NOT NULL,
  `complain_date` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `complains`
--

INSERT INTO `complains` (`complain_id`, `vehicle_number`, `complain_body`, `sacco_id`, `complain_date`) VALUES
(11, 'KQA 475', 'Loud music. Driver SpeedingCarrying Excess Passengers. Poor customer Service. Rude driver/Conductor. ', 1, '2018-08-08'),
(12, 'Loud music. Driver SpeedingCarrying Excess Passengers. ', 'KQA 475', 1, '2018-08-08'),
(13, 'Loud music. Driver SpeedingCarrying Excess Passengers. Poor customer Service. Rude driver/Conductor. ', 'KQA 475', 1, '2018-08-08'),
(14, 'Loud music. Driver Speeding', '', 0, '2018-08-09');

-- --------------------------------------------------------

--
-- Table structure for table `gcmuser`
--

CREATE TABLE `gcmuser` (
  `userID` int(100) NOT NULL,
  `first` varchar(255) NOT NULL,
  `last` varchar(255) NOT NULL,
  `user` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `payments_table`
--

CREATE TABLE `payments_table` (
  `payment_id` int(255) NOT NULL,
  `merchant_id` varchar(255) NOT NULL,
  `phone_no` varchar(255) NOT NULL,
  `vehicle_number` varchar(255) NOT NULL,
  `sacco_id` int(255) NOT NULL,
  `amount_paid` int(255) NOT NULL,
  `seat_paid` varchar(255) NOT NULL,
  `result_code` int(255) NOT NULL,
  `result_description` varchar(255) NOT NULL,
  `mpesa_refno` varchar(255) NOT NULL,
  `transaction_date` int(255) NOT NULL,
  `checkout_id` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payments_table`
--

INSERT INTO `payments_table` (`payment_id`, `merchant_id`, `phone_no`, `vehicle_number`, `sacco_id`, `amount_paid`, `seat_paid`, `result_code`, `result_description`, `mpesa_refno`, `transaction_date`, `checkout_id`) VALUES
(81, '11275-2799638-1', '254708000167', 'KQA 475', 1, 2, '3', 0, 'The service request is processed successfully.', 'MH84XZHUEK', 2147483647, 'ws_CO_DMZ_60344038_08082018120827095'),
(82, '7262-2761773-1', '254708000167', 'KQA 475', 1, 2, '3', 0, 'The service request is processed successfully.', 'MH88XZJJEI', 2147483647, 'ws_CO_DMZ_60441795_08082018121038196'),
(83, '11273-2801070-1', '254708000167', 'KQA 475', 1, 2, '2', 0, 'The service request is processed successfully.', 'MH86XZLQB0', 2147483647, 'ws_CO_DMZ_60345403_08082018121305785'),
(84, '11273-2801070-1', '', 'KQA 475', 1, 0, '2', 1037, 'DS timeout.', '', 0, 'ws_CO_DMZ_60345403_08082018121305785');

-- --------------------------------------------------------

--
-- Table structure for table `retrofitUsers`
--

CREATE TABLE `retrofitUsers` (
  `id` int(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  `age` varchar(250) NOT NULL,
  `year` varchar(250) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `retrofitUsers`
--

INSERT INTO `retrofitUsers` (`id`, `name`, `age`, `year`) VALUES
(0, 'peter', '25', '1993'),
(20, 'peter', '25', '1993');

-- --------------------------------------------------------

--
-- Table structure for table `sacco_time_defination`
--

CREATE TABLE `sacco_time_defination` (
  `def_id` int(11) NOT NULL,
  `sacco_id` int(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `start` time(6) NOT NULL,
  `end` time(6) NOT NULL,
  `fare` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sacco_time_defination`
--

INSERT INTO `sacco_time_defination` (`def_id`, `sacco_id`, `time`, `start`, `end`, `fare`) VALUES
(1, 1, 'morning', '00:00:00.000000', '11:59:59.999999', '1'),
(2, 1, 'afternoon', '12:00:00.000000', '17:59:59.999999', '2'),
(3, 1, 'evening', '18:00:00.000000', '23:59:59.999999', '70'),
(4, 2, 'evening', '18:00:00.000000', '23:59:59.999999', '100'),
(5, 2, 'morning', '00:00:00.000000', '11:59:59.999999', '80');

-- --------------------------------------------------------

--
-- Table structure for table `saco_data`
--

CREATE TABLE `saco_data` (
  `sacco_id` int(100) NOT NULL,
  `sacco_name` varchar(250) NOT NULL,
  `number_plate` varchar(250) NOT NULL,
  `paybill` varchar(60) NOT NULL,
  `vehicle_capacity` int(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saco_data`
--

INSERT INTO `saco_data` (`sacco_id`, `sacco_name`, `number_plate`, `paybill`, `vehicle_capacity`) VALUES
(1, 'Latema Sacco', 'KQA 475', '77077', 14),
(2, 'Kangaroo Shuttle', 'KCA 934Z', '46483', 11),
(3, 'North rift', 'KCA 398K', '12345', 11),
(4, 'Easy coach ', 'KBC 546N', '64647', 60),
(5, 'Ongatline sacco', 'KCA 393B', '56739', 38),
(8, 'Easy way', 'BTKJ 4657', '67895', 80),
(7, 'Kiserian sacco', 'BCJ 393H', '108393', 14),
(9, '', '', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `first` varchar(255) NOT NULL,
  `last` varchar(11) NOT NULL,
  `user` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mobile` int(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `first`, `last`, `user`, `pass`, `email`, `mobile`) VALUES
(1, '', '0', '', '', '', 0),
(1, '', '0', '', '', '', 0),
(1, 'peter', '23', '', '', '', 0),
(1, 'peter', '23', '', '', '', 71234567),
(1, 'peter', '23', '', '', '', 0),
(1, 'peter', '23', '', '', '', 71234567),
(1, 'peter', '', '', '', '', 71234567),
(1, 'peter', 'm23', '', '', '', 7),
(21, 'peter', '', '', '', '', 71234567),
(21, 'Jpeter', 'TZm23', 'tonyD', 'mack90', 'peter@gmail.com', 7),
(21, 'Jpeter', 'TZm23', 'tonyD', 'mack90', 'peter@gmail.com', 7),
(0, 'gh', 'fg', '', '', '', 0),
(4523, 'kiptoo', 'clintkip', 'cli', '123', 'clintonmaru@gmail.com', 895),
(0, 'cli', 'maru', 'clint', '123', 'clintonmaru@gmail.com', 78523),
(0, 'cli', 'maru', 'clint', '123', 'clintonmaru@gmail.com', 78523),
(0, 'cli', 'maru', 'clint', '123', 'clintonmaru@gmail.com', 78523),
(678, 'peter', 'onyango', 'peteronyi', '456tat@r', 'peter@gmail.com', 708000167),
(456, 'gha', 'gha', 'gha', 'gha', 'gja', 5668),
(567, 'hhj', 'ggg', 'hjj', '&-++875568', '567@ghhh.com', 978556),
(0, '', '', '', '', '', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `complains`
--
ALTER TABLE `complains`
  ADD PRIMARY KEY (`complain_id`);

--
-- Indexes for table `payments_table`
--
ALTER TABLE `payments_table`
  ADD PRIMARY KEY (`payment_id`);

--
-- Indexes for table `sacco_time_defination`
--
ALTER TABLE `sacco_time_defination`
  ADD PRIMARY KEY (`def_id`);

--
-- Indexes for table `saco_data`
--
ALTER TABLE `saco_data`
  ADD PRIMARY KEY (`sacco_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `complains`
--
ALTER TABLE `complains`
  MODIFY `complain_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `payments_table`
--
ALTER TABLE `payments_table`
  MODIFY `payment_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT for table `sacco_time_defination`
--
ALTER TABLE `sacco_time_defination`
  MODIFY `def_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `saco_data`
--
ALTER TABLE `saco_data`
  MODIFY `sacco_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
