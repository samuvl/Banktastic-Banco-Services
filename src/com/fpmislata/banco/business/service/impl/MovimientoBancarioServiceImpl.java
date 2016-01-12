package com.fpmislata.banco.business.service.impl;

import com.fpmislata.banco.business.domain.MovimientoBancario;
import com.fpmislata.banco.business.service.MovimientoBancarioService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.persistence.dao.MovimientoBancarioDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class MovimientoBancarioServiceImpl extends GenericServiceImpl<MovimientoBancario> implements MovimientoBancarioService {

    @Autowired
    MovimientoBancarioDAO movimientoBancarioDAO;

    @Override
    public List<MovimientoBancario> getByIdCuenta(int idCuentaBancaria) throws BusinessException {
        return movimientoBancarioDAO.getByIdCuenta(idCuentaBancaria);
    }

}
