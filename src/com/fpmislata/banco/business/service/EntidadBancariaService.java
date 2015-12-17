package com.fpmislata.banco.business.service;

import com.fpmislata.banco.business.domain.EntidadBancaria;
import java.util.List;

/**
 *
 * @autho r alumno
 */
public interface EntidadBancariaService extends GenericService<EntidadBancaria>{

    List<EntidadBancaria> findByNombre(String nombre);
}
