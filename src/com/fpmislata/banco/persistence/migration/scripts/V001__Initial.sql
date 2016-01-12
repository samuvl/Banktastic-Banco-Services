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

INSERT INTO `entidadbancaria` (`nombre`, `codigoEntidad`, `fechaCreacion`, `direccion`, `cif`) VALUES('bankia', 'bank1', "2015-12-20", 'c/ fernando septimo 34', 'C7459058I');
INSERT INTO `entidadbancaria` (`nombre`, `codigoEntidad`, `fechaCreacion`, `direccion`, `cif`) VALUES('deustchbank', 'd345L', "2015-12-15", 'C/ Bahnstrausse', 'C4378752B');
INSERT INTO `entidadbancaria` (`nombre`, `codigoEntidad`, `fechaCreacion`, `direccion`, `cif`) VALUES('evrobank', '0893', "2014-03-11", 'c/ falsa 35', 'G8488757I');