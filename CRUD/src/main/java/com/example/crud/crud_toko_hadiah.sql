-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 23, 2022 at 02:25 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crud_toko_hadiah`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_barang`
--

CREATE TABLE `data_barang` (
  `Kode_Barang` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Jenis_Barang` varchar(50) NOT NULL,
  `Nama_Barang` varchar(50) DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Harga_barang` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `data_barang`
--

INSERT INTO `data_barang` (`Kode_Barang`, `Jenis_Barang`, `Nama_Barang`, `Quantity`, `Harga_barang`) VALUES
('BKTBG 2', 'Bucket Bunga', 'Bunga Tulip', 15, 70000),
('BKTBG 3', 'Bucket Bunga', 'Bunga Biru', 22, 90000),
('BKTBG 4', 'Bucket Bunga', 'Bunga Pink', 25, 92000),
('BKTBG 5', 'Buket Bunga', 'Bunga Merah Putih', 30, 87000),
('BKTBG 6', 'Buket Bunga', 'Bunga Biru Putih', 19, 100000),
('BKTBG 7', 'Bucket Bunga', 'Bunga Melati', 15, 95000),
('BKTBNK 1', 'Buket Boneka', 'Boneka Panda', 15, 70000),
('BKTBNK 2', 'Buket Boneka', 'Boneka Beruang', 20, 80000),
('BKTBNK 3', 'Buket Boneka', 'Boneka Koala', 24, 75000),
('BKTBNK 4', 'Buket Boneka', 'Boneka Awan', 17, 68000),
('BKTBNK 5', 'Buket Boneka', 'Boneka Pinguin', 22, 85000),
('BKTBNK 6', 'Buket Boneka', 'Boneka Kucing', 18, 83000),
('GFTBOX1', 'Gift Box', 'Paket 1', 10, 55000),
('GFTBOX2', 'Gift Box', 'Paket 2', 15, 60000),
('GFTBOX3', 'Gift Box', 'Paket 3', 25, 65000),
('GFTBOX4', 'Gift Box', 'Paket 4', 20, 70000),
('GFTBOX5', 'Gift Box', 'Paket 5', 19, 72000),
('GFTBOX6', 'Gift Box', 'Paket 6', 22, 75000);

-- --------------------------------------------------------

--
-- Table structure for table `login_admin`
--

CREATE TABLE `login_admin` (
  `Username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `login_admin`
--

INSERT INTO `login_admin` (`Username`, `Password`) VALUES
('Admin', 'Admin123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_barang`
--
ALTER TABLE `data_barang`
  ADD PRIMARY KEY (`Kode_Barang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
