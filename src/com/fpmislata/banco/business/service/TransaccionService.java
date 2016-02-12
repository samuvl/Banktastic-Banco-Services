
package com.fpmislata.banco.business.service;

import com.fpmislata.banco.business.domain.Transaccion;
import com.fpmislata.banco.core.BusinessException;

/**
 *
 * @author adrian
 */
public interface TransaccionService {
    
    public void insert(Transaccion transaccion) throws BusinessException;
}
