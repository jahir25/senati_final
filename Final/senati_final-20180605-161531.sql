--
-- DbNinja v3.2.7 for MySQL
--
-- Dump date: 2018-06-05 16:15:31 (UTC)
-- Server version: 10.1.22-MariaDB
-- Database: senati_final
--

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

CREATE DATABASE `senati_final` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `senati_final`;

--
-- Structure for table: EstadoPedido
--
CREATE TABLE `estadopedido` (
  `IdEstado` int(11) NOT NULL AUTO_INCREMENT,
  `EstPedido` int(20) NOT NULL,
  PRIMARY KEY (`IdEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Structure for table: mesas
--
CREATE TABLE `mesas` (
  `IdMesa` int(11) NOT NULL AUTO_INCREMENT,
  `NumMesa` int(3) NOT NULL,
  `NumAsientos` int(20) NOT NULL,
  PRIMARY KEY (`IdMesa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Structure for table: pedidos
--
CREATE TABLE `pedidos` (
  `IdPedidos` int(11) NOT NULL AUTO_INCREMENT,
  `IdMesa` int(11) DEFAULT NULL,
  `IdPlato` int(11) DEFAULT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `IdEstado` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdPedidos`),
  KEY `fkIdMesa` (`IdMesa`),
  KEY `fkIdPlato` (`IdPlato`),
  KEY `fkIdEstado` (`IdEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Structure for table: platos
--
CREATE TABLE `platos` (
  `IdPlato` int(11) NOT NULL AUTO_INCREMENT,
  `NomPlato` int(50) NOT NULL,
  `DescPlato` int(10) NOT NULL,
  `PrecPlato` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdPlato`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Structure for table: tipousuario
--
CREATE TABLE `tipousuario` (
  `IdTipoUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `TipoUsuario` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdTipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


--
-- Structure for table: usuario
--
CREATE TABLE `usuario` (
  `IdUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `NomUsuario` varchar(200) NOT NULL,
  `ApeUsuario` varchar(200) NOT NULL,
  `LoginUsuario` varchar(50) DEFAULT NULL,
  `CorreoUsuario` varchar(400) DEFAULT NULL,
  `IdTipoUsuario` int(11) DEFAULT NULL,
  `Contraseña` blob,
  PRIMARY KEY (`IdUsuario`),
  KEY `fkTipoUsuario` (`IdTipoUsuario`),
  CONSTRAINT `fkTipoUsuario` FOREIGN KEY (`IdTipoUsuario`) REFERENCES `tipousuario` (`IdTipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


--
-- Data for table: EstadoPedido
--
LOCK TABLES `EstadoPedido` WRITE;
ALTER TABLE `EstadoPedido` DISABLE KEYS;

-- Table contains no data

ALTER TABLE `EstadoPedido` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: mesas
--
LOCK TABLES `mesas` WRITE;
ALTER TABLE `mesas` DISABLE KEYS;

-- Table contains no data

ALTER TABLE `mesas` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: pedidos
--
LOCK TABLES `pedidos` WRITE;
ALTER TABLE `pedidos` DISABLE KEYS;

-- Table contains no data

ALTER TABLE `pedidos` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: platos
--
LOCK TABLES `platos` WRITE;
ALTER TABLE `platos` DISABLE KEYS;

-- Table contains no data

ALTER TABLE `platos` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: tipousuario
--
LOCK TABLES `tipousuario` WRITE;
ALTER TABLE `tipousuario` DISABLE KEYS;

INSERT INTO `tipousuario` (`IdTipoUsuario`,`TipoUsuario`) VALUES (1,'admin');

ALTER TABLE `tipousuario` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: usuario
--
LOCK TABLES `usuario` WRITE;
ALTER TABLE `usuario` DISABLE KEYS;

INSERT INTO `usuario` (`IdUsuario`,`NomUsuario`,`ApeUsuario`,`LoginUsuario`,`CorreoUsuario`,`IdTipoUsuario`,`Contraseña`) VALUES (1,'Jahir','Diaz','admin',NULL,1,X'bf427352f0f687a2d95d873b4059dc5c');

ALTER TABLE `usuario` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;

