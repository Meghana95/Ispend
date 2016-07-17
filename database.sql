
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 13, 2016 at 04:10 PM
-- Server version: 10.0.20-MariaDB
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `u961619150_ispen`
--

-- --------------------------------------------------------

--
-- Table structure for table `bank_db`
--

CREATE TABLE IF NOT EXISTS `bank_db` (
  `cardno` varchar(20) NOT NULL,
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `contact` varchar(13) DEFAULT NULL,
  `emailid` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cardno`),
  UNIQUE KEY `userid_UNIQUE` (`userid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `bank_db`
--

INSERT INTO `bank_db` (`cardno`, `userid`, `name`, `contact`, `emailid`, `status`) VALUES
('4532737569523661', 5, 'Fahd', '8143488089', 'mdfahdfurqan@gmail.com', 'Active'),
('4916222059693577', 4, 'Sanjana', '9441240895', 'sushmi1611@gmail.com', 'Active'),
('4556282677870705', 2, 'Meghana', '8790687180', 'meghanareddy.voladri@gmail.com', 'Active'),
('4716015499785546', 6, 'asdf', '9090909090', 'asdf@gmail.com', 'Active'),
('4539944444567144', 9, 'Fatima', '7423823487', 'fatty@gmail.com', 'NotSignedUp'),
('4539258752164695', 7, 'Aparajitha', '9898989898', 'appa@gmail.com', 'NotSignedUp'),
('4653306326414086', 8, 'Rinky', '9786978698', 'rinky@gmail.com', 'NotSignedUp'),
('4024007179953905', 16, 'Chaitanya', '7212123789', 'chaitu@gmail.com', 'NotSignedUp'),
('4532508598120088', 17, 'Rithika', '9749832984', 'rith@gmail.com', 'NotSignedUp'),
('4024007112603955', 18, 'Muqeeth', '9893838998', 'muq@gmail.com', 'NotSignedUp'),
('4499543002493798', 28, 'Sushmita', '9844563217', 'sushmita.sanjana16@gmail.com', 'Signedup-NotVerified'),
('4556086312633299', 21, 'Tejasri', '9983277389', 'tejasri@gmail.com', 'NotSignedUp'),
('4215117530606768', 20, 'Mounika', '8249798273', 'mounika@gmail.com', 'NotSignedUp'),
('4716302478601955', 15, 'Rubeena', '7427892347', 'rubeenaf@gmail.com', 'NotSignedUp'),
('4567764567458745', 13, 'Rushil', '8686868686', 'rushil@gmail.com', 'NotSignedUp'),
('4579797216497212', 24, 'Raj', '9842156421', 'Raj1993@gmail.com', 'NotSignedUp'),
('4587845945784578', 14, 'Aishu', '9739832478', 'aishu@gmail.com', 'NotSignedUp'),
('4539274582447549', 25, 'Priya', '8996342315', 'sgpiya24@gmail.com', 'NotSignedUp'),
('4929213599043832', 23, 'Radhika', '9854585568', 'radhika32@gmail.com', 'NotSignedUp'),
('4539816910928903', 11, 'Ahmad', '8456224226', 'ahmad@gmail.com', 'NotSignedUp'),
('4024007143925393', 27, 'Satya', '7954554661', 'satya_jun@gmail.com', 'NotSignedUp'),
('4539028629985931', 26, 'Kevin', '7896548495', 'djkevins@gmail.com', 'NotSignedUp'),
('4485518140097376', 12, 'Amr', '8547383219', 'amr@gmail.com', 'NotSignedUp'),
('4532477720845444', 10, 'Ruchika', '8542697531', 'ruchika122@gmail.com', 'NotSignedUp'),
('4485222881880575', 22, 'Ram', '9138246788', 'ram@gmail.com', 'NotSignedUp'),
('4857258968973587', 31, 'Manogna', '8547525896', 'meghanavoladri95@gmail.com', 'NotSignedUp'),
('4485764790087727', 19, 'Sashank', '7648268824', 'sash@gmail.com', 'NotSignedUp'),
('4716774205998492', 30, 'Sanjana S', '8759587485', 'sushmita.sanjana16@gmail.com', 'NotSignedUp'),
('4857125485790705', 32, 'Bob', '8759586258', 'bob123@gmail.com', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `offers`
--

CREATE TABLE IF NOT EXISTS `offers` (
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(20) DEFAULT NULL,
  `offer` varchar(100) DEFAULT NULL,
  `datefrom` date DEFAULT NULL,
  `dateto` date DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `offers`
--

INSERT INTO `offers` (`sno`, `category`, `offer`, `datefrom`, `dateto`) VALUES
(1, 'Shopping', '20%off for First purchase anywhere', '2016-01-21', '2016-09-28'),
(2, 'Shopping', '5% cashback for creditcard users', '2016-03-02', '2016-07-28'),
(3, 'Restaurant', '5%off on the Bill on use of credit card', '2016-01-05', '2016-11-30'),
(4, 'Education', 'Education loans available at 0% interest', '2016-02-11', '2016-08-30'),
(5, 'Entertainment', '1 Movie Ticket free for any purchase of 5', '2016-03-31', '2017-01-31'),
(6, 'Transportation', '5% cashback for Air Tickets!!', '2016-03-01', '2016-10-26'),
(7, 'Groceries', 'Get points for online purchase of Groceries with credit card', '2016-03-22', '2016-04-08'),
(8, 'Medical', 'Get 10% off on purchase of medicines at Apollo pharmacy', '2016-03-18', '2016-08-10'),
(9, 'Loan', 'Housing loans available at low Rate of Interest(2%)', '2016-01-19', '2016-06-15'),
(10, 'Insurance', 'For yearly Car Insurance Subscription paid via credit card-get 5% cash back', '2015-12-25', '2017-02-16'),
(11, 'Education', 'GNAT Exam Fee 5% Off', '2016-04-01', '2016-04-30'),
(12, 'Restaurant', 'Early Bird Rs100/- off on Buffets', '2016-04-02', '2016-04-05'),
(13, 'Restaurant', 'First Order of Dinner only at Rs 199/-', '2016-03-22', '2016-06-09'),
(14, 'Groceries', '7% cash back to your wallet for purchase at any Reliance Fresh outlet', '2016-02-01', '2016-07-01'),
(15, 'Entertainment', 'Film City Entrance fee Rs 899/- only', '2016-04-02', '2016-04-29'),
(16, 'Transportation', 'Book Travel+Accomodation get 10% off', '2016-03-12', '2017-02-18'),
(17, 'Transportation', 'Book Return journey also and get Rs 500/- off', '2016-04-05', '2016-09-01'),
(18, 'Shopping', '20% off for crditcard users', '2015-09-17', '2016-04-06'),
(19, 'Shopping', 'Get credited with points on purchase at any mall', '2015-11-25', '2016-05-26'),
(20, 'Transportation', 'Get 10% off on jetSpice tickets through credit card ', '2015-07-09', '2017-01-20'),
(21, 'Transportation', 'Pay Ola cabs through card and get 5%cash back to your wallet', '2016-01-24', '2016-07-24'),
(22, 'Restaurant', 'Early bird 10%off for lunch buffet', '2016-01-24', '2016-09-24'),
(23, 'Restaurant', '20% off on first card payment at Dominos', '2015-10-24', '2016-07-24'),
(24, 'Groceries', 'Add points in your card for purchase at any BigBazaar store', '2015-11-15', '2016-12-16'),
(25, 'Groceries', '5% cash back to your wallet for purchase at any Heritage outlet', '2016-04-24', '2016-07-24'),
(26, 'Medical', 'Get 5% off on purchase of medicines at Medlife pharmacy', '2015-08-24', '2016-07-24'),
(27, 'Medical', 'Free general eye checkup', '2016-02-24', '2016-06-24'),
(28, 'Entertainment', 'Water park entrance Rs 100/- off', '2016-03-24', '2017-03-24'),
(29, 'Entertainment', 'Get a combo popcorn and coke with minimum purchase of 3 tickets and above', '2015-09-24', '2017-03-24'),
(30, 'Education', 'Mock Exam fee for GNAT 5% off', '2015-12-24', '2016-09-24'),
(31, 'Loan', 'Car loans at very low EMI', '2016-05-24', '2017-02-24'),
(32, 'Insurance', 'Bla Bla', '2016-05-25', '2016-05-26');

-- --------------------------------------------------------

--
-- Table structure for table `signup`
--

CREATE TABLE IF NOT EXISTS `signup` (
  `cardno` varchar(20) NOT NULL,
  `password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cardno`),
  KEY `fk_cardno_bankdb_idx` (`cardno`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `signup`
--

INSERT INTO `signup` (`cardno`, `password`) VALUES
('4532737569523661', 'fahd'),
('4916222059693577', 'sanjana'),
('4556282677870705', 'meghana'),
('4857125485790705', 'bob@ispend');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `cardno` varchar(20) DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  `trans_date` date DEFAULT NULL,
  `store_details` varchar(20) DEFAULT NULL,
  `amount` varchar(10) DEFAULT NULL,
  `currency` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sno`),
  KEY `fk_cardno_signup_idx` (`cardno`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`sno`, `cardno`, `category`, `trans_date`, `store_details`, `amount`, `currency`) VALUES
(1, '4916222059693577', 'Shopping', '2016-04-04', 'Max', '2000', 'Rs'),
(2, '4916222059693577    ', 'Restaurant', '2016-03-10', 'Surabhi Grand', '500', 'Rs'),
(3, '4916222059693577', 'Shopping', '2016-03-30', 'BigBazaar', '900', 'Rs'),
(4, '4556282677870705', 'Restaurant', '2016-04-01', 'Swagath', '500', 'Rs'),
(5, '4556282677870705', 'Transportation', '2016-03-21', 'AirCosta', '2000', 'Rs'),
(6, '4916222059693577', 'Entertainment', '2016-03-29', 'Inox', '200', 'Rs'),
(7, '4916222059693577', 'Entertainment', '2016-03-17', 'Imax', '250', 'Rs'),
(8, '4556282677870705', 'Entertainment', '2016-03-25', 'Imax', '250', 'Rs'),
(9, '4556282677870705', 'Shopping', '2016-04-01', 'Dmart', '1500', 'Rs'),
(10, '4556282677870705', 'Restaurant', '2016-02-26', 'Sweekar', '550', 'Rs'),
(11, '4556282677870705', 'Groceries', '2016-03-12', 'Spencers', '1345', 'Rs'),
(12, '4556282677870705', 'Groceries', '2016-03-13', 'Spencers', '1345', 'Rs'),
(13, '4916222059693577', 'Loan', '2016-04-05', 'HDFC', '10000', 'Rs'),
(14, '4532737569523661', 'Shopping', '2016-03-17', 'BrandFactory', '2334', 'Rs'),
(15, '4532737569523661', 'Transportation', '2016-04-01', 'AirIndia', '3500', 'Rs'),
(16, '4532737569523661', 'Shopping', '2016-03-26', 'BrandFactory', '1345', 'Rs'),
(17, '4532737569523661', 'Restaurant', '2016-03-28', 'Paradise', '645', 'Rs'),
(18, '4532737569523661', 'Restaurant', '2016-04-03', 'Surabhi', '480', 'Rs'),
(19, '4532737569523661', 'Shopping', '2016-04-05', 'Max', '1899', 'Rs'),
(20, '4916222059693577', 'Insurance', '2016-03-01', 'LIC', '20000', 'Rs'),
(21, '4556282677870705', 'Groceries', '2016-03-19', 'Spencers', '742', 'Rs'),
(22, '4916222059693577', 'Groceries', '2016-03-31', 'More', '1734', 'Rs'),
(23, '4556282677870705', 'Others', '2016-03-13', 'Mobile Store', '10000', 'Rs'),
(24, '4916222059693577', 'Others', '2016-03-05', 'LG', '5000', 'Rs');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
