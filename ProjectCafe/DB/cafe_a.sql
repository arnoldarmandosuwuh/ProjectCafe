-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2019 at 04:19 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cafe_a`
--
CREATE DATABASE IF NOT EXISTS `cafe_a` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cafe_a`;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `menu` varchar(50) NOT NULL,
  `harga` int(11) NOT NULL,
  `link_gambar` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id`, `menu`, `harga`, `link_gambar`) VALUES
(1, 'Nasi Goreng', 10000, 'https://media-cdn.tripadvisor.com/media/photo-s/06/48/07/44/nasi-goreng-sms.jpg'),
(2, 'Mie Goreng', 10000, 'http://www.dapurkobe.co.id/wp-content/uploads/mie-goreng-saus-tiram.jpg'),
(3, 'Soto Ayam', 15000, 'https://thegorbalsla.com/wp-content/uploads/2019/04/Resep-Soto-Ayam-Kuning.jpg'),
(4, 'Es Genderuwo', 8000, 'https://scontent-iad3-1.cdninstagram.com/vp/a93d05e7b397eb073896c18248336f97/5D69D6B9/t51.2885-15/sh0.08/e35/c0.70.960.960/s640x640/56433458_417875269043482_6240829181051248494_n.jpg?_nc_ht=scontent-iad3-1.cdninstagram.com'),
(5, 'Es Pocong', 6000, 'https://3.bp.blogspot.com/-muvj4UTCVnU/V2EfFiscGxI/AAAAAAAADIQ/ZaCfAmPi3OMO3FUjucmThutIOKd9KiaCgCLcB/s1600/Es%2BPocong.jpg'),
(6, 'Kopi Tuyul', 5000, 'https://asset.kompas.com/crop/7x6:993x663/750x500/data/photo/2017/04/18/2438001086.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `no_meja` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `diskon` int(11) NOT NULL,
  `tgl_transaksi` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `id_user`, `no_meja`, `total`, `diskon`, `tgl_transaksi`) VALUES
(1, 1, 1, 40500, 4500, '2019-05-11'),
(2, 1, 1, 63000, 7000, '2019-05-12'),
(3, 1, 2, 43200, 4800, '2019-05-12'),
(4, 1, 1, 68400, 7600, '2019-05-12');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `url_gambar` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `nama`, `username`, `password`, `url_gambar`) VALUES
(1, 'rarawidya', 'rarawidya', 'e617f1bf2667fc7130dae6eb89fed305', 'https://instagram.fsub2-1.fna.fbcdn.net/vp/07b0b5d0a23b7201ad5584d70f4e13d1/5D6F9EE2/t51.2885-15/sh0.08/e35/s640x640/23824599_1963203020595418_7471944833800601600_n.jpg?_nc_ht=instagram.fsub2-1.fna.fbcdn.net&_nc_cat=110');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
