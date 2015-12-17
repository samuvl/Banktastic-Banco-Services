package com.fpmislata.banco.business.service;

import com.fpmislata.banco.core.BusinessException;
import java.util.List;

/**
 *
 * @author Samuel Lao
 * @param <S>
 */
public interface GenericService <S> {
    
    S get (int id);
    S insert (S s) throws BusinessException;
    S update (S s) throws BusinessException;
    boolean delete (int id);
    List<S> findAll();
}
