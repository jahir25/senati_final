--
-- DbNinja v3.2.7 for MySQL
--
-- Dump date: 2018-06-11 00:51:50 (UTC)
-- Server version: 10.1.30-MariaDB
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

DROP DATABASE IF EXISTS `senati_final`;
CREATE DATABASE `senati_final` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `senati_final`;

--
-- Structure for table: estadopedido
--
CREATE TABLE `estadopedido` (
  `IdEstado` int(11) NOT NULL AUTO_INCREMENT,
  `EstPedido` varchar(20) NOT NULL,
  PRIMARY KEY (`IdEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


--
-- Structure for table: mesas
--
CREATE TABLE `mesas` (
  `IdMesa` int(11) NOT NULL AUTO_INCREMENT,
  `NumMesa` int(3) NOT NULL,
  `NumAsientos` int(20) NOT NULL,
  `IdEstadoMesa` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdMesa`),
  KEY `new_index_1` (`IdEstadoMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


--
-- Structure for table: pedidodetalle
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


--
-- Structure for table: pedidos
--
CREATE TABLE `pedidos` (
  `IdPedidos` int(11) NOT NULL AUTO_INCREMENT,
  `IdMesa` int(11) DEFAULT NULL,
  `IdEstadoPedidos` int(1) DEFAULT NULL,
  `FechaHora` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdPedidos`),
  KEY `fkIdMesa` (`IdMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;


--
-- Structure for table: estadomesa
--
CREATE TABLE `estadomesa` (
  `IdEstadoMesa` int(11) NOT NULL AUTO_INCREMENT,
  `Estado` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdEstadoMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


--
-- Structure for table: estadopedidos
--
CREATE TABLE `estadopedidos` (
  `IdEstadoPedidos` int(11) NOT NULL AUTO_INCREMENT,
  `EstadoPedidos` varchar(50) NOT NULL,
  PRIMARY KEY (`IdEstadoPedidos`),
  KEY `new_index_1` (`EstadoPedidos`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


--
-- Data for table: estadopedido
--
LOCK TABLES `estadopedido` WRITE;
ALTER TABLE `estadopedido` DISABLE KEYS;

INSERT INTO `estadopedido` (`IdEstado`,`EstPedido`) VALUES (1,'Pendiente'),(2,'Tomado'),(3,'Realizado'),(4,'Cancelado'),(5,'Cerrado');

ALTER TABLE `estadopedido` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: mesas
--
LOCK TABLES `mesas` WRITE;
ALTER TABLE `mesas` DISABLE KEYS;

INSERT INTO `mesas` (`IdMesa`,`NumMesa`,`NumAsientos`,`IdEstadoMesa`) VALUES (1,1,5,2),(2,2,6,NULL);

ALTER TABLE `mesas` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: pedidodetalle
--
LOCK TABLES `pedidodetalle` WRITE;
ALTER TABLE `pedidodetalle` DISABLE KEYS;

INSERT INTO `pedidodetalle` (`IdDetalle`,`IdPedido`,`IdPlato`,`IdEstado`,`Cantidad`,`Observación`,`FechaPedido`) VALUES (1,1,1,1,1,'','2018-06-10 19:42:36'),(2,2,1,1,2,'asd','2018-06-10 19:46:48');

ALTER TABLE `pedidodetalle` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: pedidos
--
LOCK TABLES `pedidos` WRITE;
ALTER TABLE `pedidos` DISABLE KEYS;

INSERT INTO `pedidos` (`IdPedidos`,`IdMesa`,`IdEstadoPedidos`,`FechaHora`) VALUES (1,1,2,'2018-06-10 19:42:28'),(2,1,2,'2018-06-10 19:46:44');

ALTER TABLE `pedidos` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: platos
--
LOCK TABLES `platos` WRITE;
ALTER TABLE `platos` DISABLE KEYS;

INSERT INTO `platos` (`IdPlato`,`NomPlato`,`DescPlato`,`PrecPlato`) VALUES (1,'Adobo de chancho','Descripcion del Plato',7.50);

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

INSERT INTO `usuario` (`IdUsuario`,`NomUsuario`,`ApeUsuario`,`LoginUsuario`,`CorreoUsuario`,`IdTipoUsuario`,`Contraseña`,`FlagLogin`) VALUES (2,'Jahir','Diaz','admin','jahirdiaz2595@gmail.com',1,X'bf427352f0f687a2d95d873b4059dc5c',1),(6,'jerry','contreras','jcontreras','jcontreras0011@hotmail.com',1,X'f3ba0c7fe279cc842427cda82ba404b5',1),(7,'chef','chef','chef','jahirdiaz2595@gmail.com',2,X'71edab04d0236f8c2f20157c21404003',1),(8,'jorge','jorge','jorge','jahir_diaz25@hotmail.com',1,X'f3ba0c7fe279cc842427cda82ba404b5',0);

ALTER TABLE `usuario` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: estadomesa
--
LOCK TABLES `estadomesa` WRITE;
ALTER TABLE `estadomesa` DISABLE KEYS;

INSERT INTO `estadomesa` (`IdEstadoMesa`,`Estado`) VALUES (1,'Libre'),(2,'Ocupado'),(3,'No Disponible');

ALTER TABLE `estadomesa` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;

--
-- Data for table: estadopedidos
--
LOCK TABLES `estadopedidos` WRITE;
ALTER TABLE `estadopedidos` DISABLE KEYS;

INSERT INTO `estadopedidos` (`IdEstadoPedidos`,`EstadoPedidos`) VALUES (1,'Abierto'),(2,'Cerrado');

ALTER TABLE `estadopedidos` ENABLE KEYS;
UNLOCK TABLES;
COMMIT;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;

