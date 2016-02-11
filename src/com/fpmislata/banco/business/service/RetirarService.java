package com.fpmislata.banco.business.service;

import com.fpmislata.banco.business.domain.Extraccion;
import com.fpmislata.banco.core.BusinessException;

public interface RetirarService {

    public void retirar(Extraccion extraccion) throws BusinessException;
}
