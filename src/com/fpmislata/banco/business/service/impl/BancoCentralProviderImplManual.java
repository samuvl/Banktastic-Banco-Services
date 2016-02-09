package com.fpmislata.banco.business.service.impl;

import com.fpmislata.banco.business.domain.BancoCentral;
import com.fpmislata.banco.business.service.BancoCentralProvider;
import com.fpmislata.banco.core.BusinessException;

public class BancoCentralProviderImplManual implements BancoCentralProvider {

    @Override
    public String getUrlByEntidad(BancoCentral bancoCentral) throws BusinessException {
        String urlParaRetirar;
        if (bancoCentral.getPin() != 1234) {
            throw new BusinessException("Pin", "Código pin Incorrecto.");
        }

        int codigoEntidadBancaria = Integer.parseInt(bancoCentral.getCodigoCuentaCorriente().substring(0, 4));

        if (codigoEntidadBancaria < 0 && codigoEntidadBancaria < 999) {
            urlParaRetirar = "http://banco-GERMAN.rhcloud.com/retirar";

        } else if (codigoEntidadBancaria > 1000 && codigoEntidadBancaria < 1999) {
            urlParaRetirar = "http://banco-ajanicorp.rhcloud.com/retirar";

        } else if (codigoEntidadBancaria > 2000 && codigoEntidadBancaria < 2999) {
            urlParaRetirar = "http://banco-samuvl.rhcloud.com/banktastic-banco-api/api/retirar";

        } else {
            throw new BusinessException("Entidad Bancaria", "Ha introducido una entidad internacional y no trabajamos con ellas.");
        }
        return urlParaRetirar;
    }

    @Override
    public String getUrlByGerman() throws BusinessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
