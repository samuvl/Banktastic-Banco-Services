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
	`nombreTitular` VARCHAR(50) NULL DEFAULT NULL,
        `nCuenta` INT(16) NULL DEFAULT NULL,
        /*`sucursalBancaria` INT(11) NULL DEFAULT NULL,*/
        `usuario` INT(11) NULL DEFAULT NULL,
        `saldo` DECIMAL(15,2) NULL DEFAULT NULL,
	PRIMARY KEY (`idCuentaBancaria`),
        FOREIGN KEY (usuario) REFERENCES usuario(idUsuario)
        /*FOREIGN KEY (sucursalBancaria) REFERENCES sucursalbancaria(idSucursalBancaria)*/
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

INSERT INTO `cuentabancaria` (`nombreTitular`, `nCuenta`, `usuario`,`saldo`) VALUES ('Samuel Lao', 12345, 3, 1566);
INSERT INTO `cuentabancaria` (`nombreTitular`, `nCuenta`, `usuario`,`saldo`) VALUES ('Daniel ', 00001111, 2, 2533.22);
INSERT INTO `cuentabancaria` (`nombreTitular`, `nCuenta`, `usuario`,`saldo`) VALUES ('Adrian ', 45564, 1, 7533.22);

CREATE TABLE IF NOT EXISTS `movimientobancario` (
	`idMovimientoBancario` INT(11) NOT NULL AUTO_INCREMENT,
	`tipoMovimiento` ENUM('debe', 'haber') NULL DEFAULT NULL,
        `cuentaBancaria` INT(11) NULL DEFAULT NULL,
	`concepto` VARCHAR(40) NULL DEFAULT NULL,
        `importe` DECIMAL(15,2) NULL DEFAULT NULL,
        `saldo` DECIMAL(15,2) NULL DEFAULT NULL,
	`fechaMovimiento` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`idMovimientoBancario`),
        FOREIGN KEY (cuentaBancaria) REFERENCES cuentabancaria(idCuentaBancaria)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;