--
-- DbNinja v3.2.7 for MySQL
--
-- Dump date: 2018-06-05 21:54:00 (UTC)
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
-- Structure for table: estadopedido
--
CREATE TABLE `estadopedido` (
  `IdEstado` int(11) NOT NULL AUTO_INCREMENT,
  `EstPedido` varchar(20) NOT NULL,
  PRIMARY KEY (`IdEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


--
-- Structure for table: mesas
--
CREATE TABLE `mesas` (
  `IdMesa` int(11) NOT NULL AUTO_INCREMENT,
  `NumMesa` int(3) NOT NULL,
  `NumAsientos` int(20) NOT NULL,
  `Estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


--
-- Structure for table: pedidoDetalle
--
CREATE TABLE `pedidodetalle` (
  `IdDetalle` int(11) NOT NULL AUTO_INCREMENT,
  `IdPedido` int(11) DEFAULT NULL,
  `IdPlato` int(11) DEFAULT NULL,
  `IdEstado` int(11) DEFAULT NULL,
  `Cantidad` int(11) DEFAULT NULL,
  `Observación` varchar(200) DEFAULT NULL,
  `FechaPedido` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdDetalle`),
  KEY `new_index_2` (`IdPedido`,`IdPlato`,`IdEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Structure for table: pedidos
--
CREATE TABLE `pedidos` (
  `IdPedidos` int(11) NOT NULL AUTO_INCREMENT,
  `IdMesa` int(11) DEFAULT NULL,
  `FechaHora` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdPedidos`),
  KEY `fkIdMesa` (`IdMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


--
-- Structure for table: platos
--
CREATE TABLE `platos` (
  `IdPlato` int(11) NOT NULL AUTO_INCREMENT,
  `NomPlato` varchar(200) NOT NULL,
  `DescPlato` varchar(500) NOT NULL,
  `PrecPlato` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`IdPlato`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


--
-- Structure for table: tipousuario
--
CREATE TABLE `tipousuario` (
  `IdTipoUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `TipoUsuario` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdTipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


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
  `FlagLogin` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdUsuario`),
  KEY `fkTipoUsuario` (`IdTipoUsuario`),
  CONSTRAINT `fkTipoUsuario` FOREIGN KEY (`IdTipoUsuario`) REFERENCES `tipousuario` (`IdTipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;


--
-- Data for table: estadopedido
--
LOCK TABLES `estadopedido` WRITE;
ALTER TABLE `estadopedido` DISABLE KEYS;

INSERT INTO `estadopedido` (`IdEstado`,`EstPedido`) VALUES (1,'Pendiente'),(2,'Tomado'),(3,'Realizado');

ALTER TABLE `estadopedido` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: mesas
--
LOCK TABLES `mesas` WRITE;
ALTER TABLE `mesas` DISABLE KEYS;

INSERT INTO `mesas` (`IdMesa`,`NumMesa`,`NumAsientos`,`Estado`) VALUES (1,1,5,NULL);

ALTER TABLE `mesas` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: pedidoDetalle
--
LOCK TABLES `pedidoDetalle` WRITE;
ALTER TABLE `pedidoDetalle` DISABLE KEYS;

-- Table contains no data

ALTER TABLE `pedidoDetalle` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: pedidos
--
LOCK TABLES `pedidos` WRITE;
ALTER TABLE `pedidos` DISABLE KEYS;

INSERT INTO `pedidos` (`IdPedidos`,`IdMesa`,`FechaHora`) VALUES (1,1,'2018-06-05 11:47:58');

ALTER TABLE `pedidos` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: platos
--
LOCK TABLES `platos` WRITE;
ALTER TABLE `platos` DISABLE KEYS;

INSERT INTO `platos` (`IdPlato`,`NomPlato`,`DescPlato`,`PrecPlato`) VALUES (1,'Adobo de chancho','Adobo de chancho',7.50);

ALTER TABLE `platos` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: tipousuario
--
LOCK TABLES `tipousuario` WRITE;
ALTER TABLE `tipousuario` DISABLE KEYS;

INSERT INTO `tipousuario` (`IdTipoUsuario`,`TipoUsuario`) VALUES (1,'admin'),(2,'cocinero');

ALTER TABLE `tipousuario` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: usuario
--
LOCK TABLES `usuario` WRITE;
ALTER TABLE `usuario` DISABLE KEYS;

INSERT INTO `usuario` (`IdUsuario`,`NomUsuario`,`ApeUsuario`,`LoginUsuario`,`CorreoUsuario`,`IdTipoUsuario`,`Contraseña`,`FlagLogin`) VALUES (2,'Jahir','Diaz','admin','jahirdiaz2595@gmail.com',1,X'bf427352f0f687a2d95d873b4059dc5c',1),(6,'jerry','contreras','jcontreras','jcontreras0011@hotmail.com',1,X'f3ba0c7fe279cc842427cda82ba404b5',1),(7,'chef','chef','chef','jahirdiaz2595@gmail.com',2,X'71edab04d0236f8c2f20157c21404003',1);

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

