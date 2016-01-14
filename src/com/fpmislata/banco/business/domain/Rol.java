package com.fpmislata.banco.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author alumno
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public enum Rol {
    cliente, trabajador;
}
