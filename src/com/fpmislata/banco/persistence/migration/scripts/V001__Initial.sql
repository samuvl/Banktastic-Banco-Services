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
	`tipoCuenta` VARCHAR(40) NULL DEFAULT NULL,
        `saldoCuenta` INT(16) NULL DEFAULT NULL,
	PRIMARY KEY (`idCuenta`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE IF NOT EXISTS `movimientobancario` (
	`idMovimientoBancario` INT(11) NOT NULL AUTO_INCREMENT,
	`tipoMovimiento` VARCHAR(50) NULL DEFAULT NULL,
        `idCuentaBancaria` INT(11) NULL DEFAULT NULL,
	`concepto` VARCHAR(40) NULL DEFAULT NULL,
        `cantidad` INT(16) NULL DEFAULT NULL,
	`fechaMovimiento` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`idMovimientoBancario`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;