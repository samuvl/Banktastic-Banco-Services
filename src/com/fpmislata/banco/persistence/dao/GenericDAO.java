package com.fpmislata.banco.persistence.dao;

import com.fpmislata.banco.core.BusinessException;
import java.util.List;

/**
 *
 * @author alumno
 * @param <T>
 */
public interface GenericDAO <T> {
    
    T get (int id);
    T insert(T t) throws BusinessException;
    T update(T t) throws BusinessException;
    boolean delete (int id);
    List<T> findAll();
}
