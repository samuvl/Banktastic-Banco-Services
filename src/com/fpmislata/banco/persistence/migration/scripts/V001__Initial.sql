CREATE TABLE IF NOT EXISTS `entidadbancaria` (
	`idEntidadBancaria` INT(11) NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(50) NULL DEFAULT NULL,
	`codigoEntidad` VARCHAR(50) NULL DEFAULT NULL UNIQUE,
	`fechaCreacion` DATE NULL DEFAULT NULL,
	`direccion` VARCHAR(50) NULL DEFAULT NULL,
	`cif` VARCHAR(10) NULL DEFAULT NULL,
	PRIMARY KEY (`idEntidadBancaria`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

INSERT INTO `banco`.`entidadbancaria` (`nombre`, `codigoEntidad`, `fechaCreacion`, `direccion`, `cif`) VALUES ('santander', '0001', '2010-05-06', 'c/ botin 17', '53258450H');
INSERT INTO `banco`.`entidadbancaria` (`nombre`, `codigoEntidad`, `fechaCreacion`, `direccion`, `cif`) VALUES ('evobank', '0002', '2011-07-08', 'c/ morales 34', '53258450H');

CREATE TABLE IF NOT EXISTS `sucursalbancaria` (
	`idSucursalBancaria` INT(11) NOT NULL AUTO_INCREMENT,
	`codigoSucursalBancaria` VARCHAR(4) NULL DEFAULT NULL,
	`direccion` VARCHAR(80) NULL DEFAULT NULL UNIQUE,
	`telefono` VARCHAR(20) NULL DEFAULT NULL,
	`idEntidadBancaria` INT(11) NULL DEFAULT NULL,
	`fechaCreacion` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`idSucursalBancaria`),
        FOREIGN KEY (`idEntidadBancaria`) REFERENCES `entidadbancaria`(`idEntidadBancaria`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

INSERT INTO `banco`.`sucursalbancaria` (`codigoSucursalBancaria`, `direccion`, `telefono`, `idEntidadBancaria`, `fechaCreacion`) VALUES ('1111', 'C/ Tu Sucursal', '968881144', '1','2010-05-06');
INSERT INTO `banco`.`sucursalbancaria` (`codigoSucursalBancaria`, `direccion`, `telefono`, `idEntidadBancaria`, `fechaCreacion`) VALUES ('2222', 'C/ Transformerica', '965552244', '2','2013-05-06');


CREATE TABLE IF NOT EXISTS `usuario` (
	`idUsuario` INT(11) NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(50) NULL DEFAULT NULL,
        `email` VARCHAR(50) NULL DEFAULT NULL,
	`encryptedPassword` VARCHAR(80) NULL DEFAULT NULL,
	`rol` ENUM('trabajador','cliente') NULL DEFAULT NULL,
	`nick` VARCHAR(50)  NULL DEFAULT NULL UNIQUE,
        `dni` VARCHAR(9) NULL DEFAULT NULL,
	PRIMARY KEY (`idUsuario`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

INSERT INTO `usuario` (`idUsuario`, `nombre`, `email`, `encryptedPassword`, `rol`, `nick`, `dni`) VALUES (2, 'cliente', 'cliente@cliente.com', 'oFQrspbsJhva3+HgRI4lXFw+Hv2hHcmT', 'cliente', 'cliente', '33333333P');
INSERT INTO `usuario` (`idUsuario`, `nombre`, `email`, `encryptedPassword`, `rol`, `nick`, `dni`) VALUES (3, 'a', 'a@a.com', 'JVaiKkedo4saW1Jw9IEuCxdBzKc6UFxm', 'trabajador', 'a', '22222222J');
INSERT INTO `usuario` (`idUsuario`, `nombre`, `email`, `encryptedPassword`, `rol`, `nick`, `dni`) VALUES (1, 'trabajador', 'trabajador@trabajador.com', 'fDTIfF8f4BnVkzkcvNdyINBmIXbRRxsJ', 'trabajador', 'trabajador', '11111111H');


CREATE TABLE IF NOT EXISTS `cuentabancaria` (
	`idCuentaBancaria` INT(11) NOT NULL AUTO_INCREMENT,
        `numeroCuenta` VARCHAR(16) NULL DEFAULT NULL,
        `digitoControl` VARCHAR(2) NULL DEFAULT NULL,
        `idUsuario` INT(11) NULL DEFAULT NULL,
        `idSucursalBancaria` INT(11) NULL DEFAULT NULL,
        `saldo` DECIMAL(15,2) NULL DEFAULT NULL,
        `fechaCreacion` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`idCuentaBancaria`),
        FOREIGN KEY (`idUsuario`) REFERENCES `usuario`(`idUsuario`) ON UPDATE CASCADE ON DELETE CASCADE,
        FOREIGN KEY (`idSucursalBancaria`) REFERENCES `sucursalbancaria`(`idSucursalBancaria`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

INSERT INTO `cuentabancaria` ( `numeroCuenta`, `digitoControl`, `idUsuario`, `idSucursalBancaria`, `saldo`, `fechaCreacion`) VALUES ("1234567891", 90, 3, 1, 1566, '2016-01-10');
INSERT INTO `cuentabancaria` ( `numeroCuenta`, `digitoControl`, `idUsuario`, `idSucursalBancaria`, `saldo`, `fechaCreacion`) VALUES ("0000111122", 76, 2, 2, 2533.22, '2015-01-10');
INSERT INTO `cuentabancaria` ( `numeroCuenta`, `digitoControl`, `idUsuario`, `idSucursalBancaria`, `saldo`, `fechaCreacion`) VALUES ("7777222255", 78, 1, 2, 7533.22, '2014-01-10');

CREATE TABLE IF NOT EXISTS `movimientobancario` (
	`idMovimientoBancario` INT(11) NOT NULL AUTO_INCREMENT,
	`tipoMovimiento` ENUM('debe', 'haber') NULL DEFAULT NULL,
        `idCuentaBancaria` INT(11) NULL DEFAULT NULL,
	`concepto` VARCHAR(40) NULL DEFAULT NULL,
        `importe` DECIMAL(15,2) NULL DEFAULT NULL,
        `saldoAnterior` DECIMAL(15,2) NULL DEFAULT NULL,
        `saldoPosterior` DECIMAL(15,2) NULL DEFAULT NULL,
	`fechaMovimiento` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`idMovimientoBancario`),
        FOREIGN KEY (`idCuentaBancaria`) REFERENCES `cuentabancaria`(`idCuentaBancaria`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

INSERT INTO `banco`.`movimientobancario` VALUES (null, 'debe', 1, 'humbleBundle', '20', 1676, 1656, curdate());
INSERT INTO `banco`.`movimientobancario` VALUES (null, 'haber', 1, 'empresa', '100', 1656, 1566, curdate());

