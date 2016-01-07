package com.fpmislata.banco.business.service.impl;

import com.fpmislata.banco.business.service.GenericService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.persistence.dao.GenericDAO;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericServiceImpl<S> implements GenericService<S> {

    @Autowired
    GenericDAO<S> genericDAO;

    private Class<S> getEntityClass() {
        return (Class<S>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public S get(int id) {
        return genericDAO.get(id);
    }

    @Override
    public S insert(S s) throws BusinessException {
        return genericDAO.insert(s);
    }

    @Override
    public S update(S s) throws BusinessException {
        return genericDAO.update(s);
    }

    @Override
    public boolean delete(int id) {
        return genericDAO.delete(id);
    }

    @Override
    public List<S> findAll() {
        return genericDAO.findAll();
    }

}
