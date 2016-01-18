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

INSERT INTO `banco`.`entidadbancaria` (`nombre`, `codigoEntidad`, `fechaCreacion`, `direccion`) VALUES ('santander', 'stn12', '2010-05-06', 'c/ botin 17');
INSERT INTO `banco`.`entidadbancaria` (`nombre`, `codigoEntidad`, `fechaCreacion`, `direccion`) VALUES ('evobank', 'svob23', '2011-07-08', 'c/ morales 34');

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

CREATE TABLE IF NOT EXISTS `usuario` (
	`idUsuario` INT(11) NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(50) NULL DEFAULT NULL,
        `email` VARCHAR(50) NULL DEFAULT NULL,
	`encryptedPassword` VARCHAR(80) NULL DEFAULT NULL,
	`rol` ENUM('trabajador','cliente') NULL DEFAULT NULL,
	`nick` VARCHAR(50)  NULL DEFAULT NULL UNIQUE,
	PRIMARY KEY (`idUsuario`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

INSERT INTO `usuario` (`idUsuario`, `nombre`, `encryptedPassword`, `rol`, `nick`) VALUES (2, 'cliente', 'oFQrspbsJhva3+HgRI4lXFw+Hv2hHcmT', 'cliente', 'cliente');
INSERT INTO `usuario` (`idUsuario`, `nombre`, `encryptedPassword`, `rol`, `nick`) VALUES (3, 'a', 'JVaiKkedo4saW1Jw9IEuCxdBzKc6UFxm', 'trabajador', 'a');
INSERT INTO `usuario` (`idUsuario`, `nombre`, `encryptedPassword`, `rol`, `nick`) VALUES (1, 'trabajador', 'fDTIfF8f4BnVkzkcvNdyINBmIXbRRxsJ', 'trabajador', 'trabajador');


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

INSERT INTO `cuentabancaria` ( `numeroCuenta`, `idUsuario`,`saldo`, `fechaCreacion`) VALUES ("12345", 3, 1566, '2016-01-10');
INSERT INTO `cuentabancaria` ( `numeroCuenta`, `idUsuario`,`saldo`, `fechaCreacion`) VALUES ("00001111", 2, 2533.22, '2015-01-10');
INSERT INTO `cuentabancaria` ( `numeroCuenta`, `idUsuario`,`saldo`, `fechaCreacion`) VALUES ("45564", 1, 7533.22, '2014-01-10');

CREATE TABLE IF NOT EXISTS `movimientobancario` (
	`idMovimientoBancario` INT(11) NOT NULL AUTO_INCREMENT,
	`tipoMovimiento` ENUM('debe', 'haber') NULL DEFAULT NULL,
        `idCuentaBancaria` INT(11) NULL DEFAULT NULL,
	`concepto` VARCHAR(40) NULL DEFAULT NULL,
        `importe` DECIMAL(15,2) NULL DEFAULT NULL,
        `saldo` DECIMAL(15,2) NULL DEFAULT NULL,
	`fechaMovimiento` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`idMovimientoBancario`),
        FOREIGN KEY (`idCuentaBancaria`) REFERENCES `cuentabancaria`(`idCuentaBancaria`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

INSERT INTO `banco`.`movimientobancario` (`tipoMovimiento`, `idCuentaBancaria`, `concepto`, `importe`) VALUES ('debe', '1', 'humbleBundle', '10.00');
INSERT INTO `banco`.`movimientobancario` (`tipoMovimiento`, `idCuentaBancaria`, `concepto`, `importe`) VALUES ('haber', '1', 'empresa', '234.00');

