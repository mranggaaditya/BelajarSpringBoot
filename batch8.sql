-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2021 at 03:14 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `batch8`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllSalary` ()  NO SQL
select * from salary$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetDepartment` (IN `depar` VARCHAR(20))  BEGIN

    SELECT DEPARTMENT, COUNT(WORKER_ID) AS jumlah_pegawai
 	FROM worker
	WHERE DEPARTMENT = depar;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ulangBulan` (`dateFrom` DATETIME, `platfon` INT, `bunga` REAL, `lamapinjaman` INT)  BEGIN
    
   		DECLARE df DATETIME;
        DECLARE lama INT;
        DECLARE intTotalAngsuran REAL;
        DECLARE intAngsuranBunga REAL;
        DECLARE intSisaPinjaman REAL;
        DECLARE intAngsuranPokok REAL;
       
  
    SET lama = 0;
    
    IF ( DATE_FORMAT(dateFrom ,'%d-%m-%Y') = '00-00-0000') THEN
               SET df = CURRENT_DATE();
           ELSE
               SET df = dateFrom;
           END IF;
    
    DROP TEMPORARY TABLE IF EXISTS dummy;
    
    CREATE TEMPORARY TABLE dummy(
       angsuranke int,
       tanggal VARCHAR(100),
       totalAngsuran REAL,
       angsuranPokok REAL,
       angsuranBunga REAL,
       sisaPinjaman REAL
       
    );

    WHILE  lama < lamapinjaman DO
           
           
 
 			SET intTotalAngsuran  = hitungAngsuran(platfon,bunga,lamapinjaman);
           IF ( lama <1) THEN
             
            
              SET intAngsuranBunga = angsuranBunga(30 , bunga , platfon);
              SET intAngsuranPokok = intTotalAngsuran - intAngsuranBunga;
              SET intSisaPinjaman =  platfon-intAngsuranPokok;
              
           ELSE
           
             SET intAngsuranBunga = angsuranBunga(30 , bunga , intSisaPinjaman);
             SET intAngsuranPokok = intTotalAngsuran - intAngsuranBunga;
             SET intSisaPinjaman =  intSisaPinjaman-intAngsuranPokok;
             
           END IF;
        
           
             
            SET intAngsuranPokok = intTotalAngsuran - intAngsuranBunga;
            
           
  
            insert into dummy  values (lama +1 , DATE_FORMAT(df ,'%d-%m-%Y'),intTotalAngsuran,intAngsuranPokok,intAngsuranBunga,intSisaPinjaman);
            
            SET df = DATE_ADD(df, INTERVAL 1 MONTH);
            SET lama = lama +1;
               
    END WHILE;
    
    select * from dummy ;
    
END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `angsuranBunga` (`hari` INT, `bunga` REAL, `sisapinjaman` REAL) RETURNS DOUBLE BEGIN
          
           DECLARE hasil REAL;
          
           SET hasil =  bunga/360*30*sisapinjaman;
           RETURN hasil ;
           

 END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `hitungAngsuran` (`platfon` INT, `bunga` REAL, `lamapinjaman` INT) RETURNS DOUBLE BEGIN
           DECLARE bungaperbulan REAL;
           DECLARE hasil REAL;
           SET bungaperbulan = bunga/12;
           RETURN bungaperbulan*platfon*(POWER(((1+bungaperbulan)),lamapinjaman))/(POWER(((1+bungaperbulan)),lamapinjaman)-1);
           

 END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `tambah` (`nilaiA` INT(100), `id` INT(100)) RETURNS INT(100) NO SQL
BEGIN

DECLARE modulus INT;
set modulus = MOD(id,2);
IF(modulus=0) then
RETURN nilaiA + 2000;
ELSE
RETURN nilaiA+1000;
END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `absensi`
--

CREATE TABLE `absensi` (
  `id` int(11) NOT NULL,
  `nik` varchar(50) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `absensi`
--

INSERT INTO `absensi` (`id`, `nik`, `start_date`, `end_date`) VALUES
(1, 'N69', '2019-10-03 00:00:00', '2019-10-03 00:00:00'),
(2, 'N02', '2019-10-03 00:00:00', '2019-10-03 00:00:00'),
(3, 'N03', '2020-10-03 09:00:00', '2020-10-03 15:00:00'),
(4, 'N05', '2020-10-03 09:00:00', '2020-10-03 15:00:00'),
(5, 'N01', '2021-10-04 09:00:00', '2021-10-04 15:00:00'),
(69, 'N88', '2021-10-05 10:00:00', '2021-10-05 12:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `annualreviews`
--

CREATE TABLE `annualreviews` (
  `ID` varchar(8) DEFAULT NULL,
  `EmpID` varchar(8) DEFAULT NULL,
  `ReviewDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `annualreviews`
--

INSERT INTO `annualreviews` (`ID`, `EmpID`, `ReviewDate`) VALUES
('10', '1', '2016-01-01'),
('20', '2', '2016-04-12'),
('30', '10', '2015-02-13'),
('40', '22', '2010-10-12'),
('50', '11', '2009-01-01'),
('60', '12', '2009-03-03'),
('70', '13', '2008-12-01'),
('80', '1', '2003-04-12'),
('90', '1', '2014-04-30');

-- --------------------------------------------------------

--
-- Table structure for table `biodata`
--

CREATE TABLE `biodata` (
  `nik` varchar(50) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `id_salary` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `biodata`
--

INSERT INTO `biodata` (`nik`, `nama`, `alamat`, `id_salary`) VALUES
('111222333', 'Meliodas', 'MC', 122),
('2222222', 'Elizabeth', 'Herioine MC', 123),
('N01', 'Dewa', 'Jl Bogor', 1),
('N02', 'Aqil', 'Jakarta', 2),
('N03', 'Anton', 'Jakarta', 3),
('N04', 'Sumarno', 'Jakarta', 4);

-- --------------------------------------------------------

--
-- Table structure for table `bonus`
--

CREATE TABLE `bonus` (
  `WORKER_REF_ID` int(11) NOT NULL,
  `BONUS_DATE` datetime NOT NULL,
  `BONUS_AMOUNT` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bonus`
--

INSERT INTO `bonus` (`WORKER_REF_ID`, `BONUS_DATE`, `BONUS_AMOUNT`) VALUES
(2, '2016-06-11 00:00:00', 3000),
(2, '2016-06-11 00:00:00', 3500),
(3, '2016-02-20 00:00:00', 4000),
(1, '2016-02-20 00:00:00', 4500),
(10, '2016-02-20 00:00:00', 5000),
(10, '2016-02-20 00:00:00', 100000);

-- --------------------------------------------------------

--
-- Table structure for table `datapinjaman`
--

CREATE TABLE `datapinjaman` (
  `angsuranke` int(11) NOT NULL,
  `tanggal` datetime NOT NULL DEFAULT current_timestamp(),
  `totalAngsuran` double NOT NULL,
  `angsuranPokok` double NOT NULL,
  `angsuranBunga` double NOT NULL,
  `sisaPinjaman` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `datapinjaman`
--

INSERT INTO `datapinjaman` (`angsuranke`, `tanggal`, `totalAngsuran`, `angsuranPokok`, `angsuranBunga`, `sisaPinjaman`) VALUES
(1, '2021-03-19 23:16:13', 2629475.537747, 729475.537747, 3000000, 18370523.462253),
(2, '2021-03-19 23:16:13', 2629475.537747, 829475.537747, 4000000, 17370523.462253),
(3, '2021-03-19 23:16:13', 2629475.537747, 929475.537747, 5000000, 16370523.462253),
(4, '2021-03-19 23:16:13', 2629475.537747, 1029475.537747, 6000000, 15370523.462253),
(5, '2021-03-19 23:16:14', 2629475.537747, 1129475.537747, 7000000, 14370523.462253),
(6, '2021-03-19 23:16:14', 2629475.537747, 1229475.537747, 8000000, 13370523.462253),
(7, '2021-03-19 23:16:14', 2629475.537747, 1329475.537747, 9000000, 12370523.462253),
(8, '2021-03-19 23:16:14', 2629475.537747, 1429475.537747, 10000000, 11370523.462253),
(9, '2021-03-19 23:16:14', 2629475.537747, 1529475.537747, 11000000, 10370523.462253),
(10, '2021-03-19 23:16:14', 2629475.537747, 1629475.537747, 12000000, 9370523.462253),
(11, '2021-03-19 23:16:14', 2629475.537747, 1729475.537747, 13000000, 8370523.462253),
(12, '2021-03-19 23:16:14', 2629475.537747, 1829475.537747, 14000000, 7370523.462253),
(13, '2021-03-19 23:16:14', 2629475.537747, 1929475.537747, 15000000, 6370523.462253),
(14, '2021-03-19 23:16:14', 2629475.537747, 2029475.537747, 16000000, 5370523.462253),
(15, '2021-03-19 23:16:15', 2629475.537747, 2129475.537747, 17000000, 4370523.462253);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `FirstName` varchar(30) DEFAULT NULL,
  `LastName` varchar(30) DEFAULT NULL,
  `Id` varchar(8) DEFAULT NULL,
  `HireDate` date DEFAULT NULL,
  `TerminationDate` date DEFAULT NULL,
  `Salary` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`FirstName`, `LastName`, `Id`, `HireDate`, `TerminationDate`, `Salary`) VALUES
('Bob', 'Smith', '1', '2009-06-20', '2016-01-01', 10000),
('Joe', 'Jarrod', '2', '2010-02-12', NULL, 20000),
('Nancy', 'Soley', '3', '2012-03-14', NULL, 30000),
('Keith', 'Widjaja', '4', '2013-09-10', '2014-01-01', 20000),
('Kelly', 'Smalls', '5', '2013-09-10', NULL, 20000),
('Frank', 'Nguyen', '6', '2015-04-10', '2015-05-01', 60000);

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nim` int(10) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `umur` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`nim`, `nama`, `alamat`, `umur`) VALUES
(11223300, 'Muhammad Rangga Aditya Saputra', 'Puri Harmoni Blok C15 No.21', 18),
(2021430044, 'Febrian', 'Jalanin dulu', 25),
(2021490033, 'Aqil', 'Jalan jalan', 25),
(2021490066, 'Lookman', 'Jalan asik mantap', 28),
(2021490099, 'Hanif', 'Jalan gang senggol', 21),
(2021550044, 'Sidik', 'Jalan merakyats', 50);

-- --------------------------------------------------------

--
-- Table structure for table `salary`
--

CREATE TABLE `salary` (
  `id` int(11) NOT NULL,
  `salary` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `salary`
--

INSERT INTO `salary` (`id`, `salary`) VALUES
(1, 5000),
(2, 6000),
(4, 4000),
(5, 7000);

-- --------------------------------------------------------

--
-- Table structure for table `title`
--

CREATE TABLE `title` (
  `WORKER_REF_ID` int(11) NOT NULL,
  `WORKER_TITLE` varchar(50) NOT NULL,
  `AFFECTED_FROM` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `title`
--

INSERT INTO `title` (`WORKER_REF_ID`, `WORKER_TITLE`, `AFFECTED_FROM`) VALUES
(1, 'Manager', '2016-02-20 13:33:45'),
(2, 'Executive', '2016-06-11 00:00:00'),
(3, 'Lead', '2016-06-11 00:00:00'),
(4, 'Asst. Manager', '2016-06-11 00:00:00'),
(5, 'Manager', '2016-06-11 00:00:00'),
(6, 'Lead', '2016-06-11 00:00:00'),
(7, 'Executive', '2016-06-11 00:00:00'),
(8, 'CEO', '2016-06-11 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `worker`
--

CREATE TABLE `worker` (
  `WORKER_ID` varchar(50) NOT NULL,
  `FIRST_NAME` varchar(50) NOT NULL,
  `LAST_NAME` varchar(50) NOT NULL,
  `SALARY` int(50) NOT NULL,
  `JOINING_DATE` datetime NOT NULL,
  `DEPARTMENT` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `worker`
--

INSERT INTO `worker` (`WORKER_ID`, `FIRST_NAME`, `LAST_NAME`, `SALARY`, `JOINING_DATE`, `DEPARTMENT`) VALUES
('001', 'Monika', 'Arora', 100000, '2014-02-20 09:00:00', 'HR'),
('002', 'Niharika', 'Verma', 80000, '2014-06-11 09:00:00', 'Admin'),
('003', 'Vishal', 'Singhal', 300000, '2014-02-20 09:00:00', 'HR'),
('004', 'Amitabh', 'Singh', 500000, '2014-02-20 09:00:00', 'Admin'),
('005', 'Vivek', 'Bhati', 500000, '2014-06-11 09:00:00', 'Admin'),
('006', 'Vipul', 'Diwan', 200000, '2014-06-11 09:00:00', 'Account'),
('007', 'Satish', 'Kumar', 75000, '2014-01-20 09:00:00', 'Account'),
('008', 'Geetika', 'Chauhan', 90000, '2014-04-11 09:00:00', 'Admin'),
('009', 'Rangga', 'Aditya', 100000, '2021-04-11 09:00:00', 'Programming');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absensi`
--
ALTER TABLE `absensi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `biodata`
--
ALTER TABLE `biodata`
  ADD PRIMARY KEY (`nik`);

--
-- Indexes for table `bonus`
--
ALTER TABLE `bonus`
  ADD PRIMARY KEY (`BONUS_AMOUNT`);

--
-- Indexes for table `datapinjaman`
--
ALTER TABLE `datapinjaman`
  ADD PRIMARY KEY (`angsuranke`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `salary`
--
ALTER TABLE `salary`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `title`
--
ALTER TABLE `title`
  ADD PRIMARY KEY (`WORKER_REF_ID`);

--
-- Indexes for table `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`WORKER_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `absensi`
--
ALTER TABLE `absensi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT for table `datapinjaman`
--
ALTER TABLE `datapinjaman`
  MODIFY `angsuranke` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `salary`
--
ALTER TABLE `salary`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
