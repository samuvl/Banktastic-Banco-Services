package com.fpmislata.banco.business.service.impl;

import com.fpmislata.banco.business.domain.BancoCentral;
import com.fpmislata.banco.business.service.BancoCentralProvider;
import com.fpmislata.banco.core.BusinessException;

public class BancoCentralProviderImplManual implements BancoCentralProvider {

    @Override
    public BancoCentral getBancoCentralAnswer(BancoCentral bancoCentral) throws BusinessException {
        BancoCentral bancoCentralDevuelto = null;

        int codigoEntidadCuentaCorriente = Integer.parseInt(bancoCentral.getCodigoCuentaCorriente().substring(0, 4));
        int codigoEntidadBancariaComprobar = Integer.parseInt(bancoCentral.getCodigoEntidadBancaria());
/*
        if ((codigoEntidadBancariaComprobar < 0 && codigoEntidadBancariaComprobar < 999) && bancoCentral.getPin().equals(1111)) {
            if (codigoEntidadCuentaCorriente > 0 && codigoEntidadCuentaCorriente < 999) {
                bancoCentralDevuelto.setUrl("http://banco-GERMAN.rhcloud.com/retirar");
                bancoCentralDevuelto.setPin("1111");
            } else if (codigoEntidadCuentaCorriente > 1000 && codigoEntidadCuentaCorriente < 1999) {
                bancoCentralDevuelto.setUrl("http://ecobanco-juankza.rhcloud.com/api/retirar");
                bancoCentralDevuelto.setPin("2222");
            } else if (codigoEntidadCuentaCorriente > 2000 && codigoEntidadCuentaCorriente < 2999) {
                bancoCentralDevuelto.setUrl("http://banco-samuvl.rhcloud.com/banktastic-banco-api/api/retirar");
                bancoCentralDevuelto.setPin("2045");
            } else {
                throw new BusinessException("Entidad Bancaria", "Ha introducido una entidad internacional y no trabajamos con ellas.");
            }
        } else {
            throw new BusinessException("Pin", "Ha introducido un pin incorrecto.");
        }

        if ((codigoEntidadBancariaComprobar > 1000 && codigoEntidadBancariaComprobar < 1999) && bancoCentral.getPin().equals(2222)) {
           if (codigoEntidadCuentaCorriente > 0 && codigoEntidadCuentaCorriente < 999) {
                bancoCentralDevuelto.setUrl("http://banco-GERMAN.rhcloud.com/retirar");
                bancoCentralDevuelto.setPin("1111");
            } else if (codigoEntidadCuentaCorriente > 1000 && codigoEntidadCuentaCorriente < 1999) {
                bancoCentralDevuelto.setUrl("http://ecobanco-juankza.rhcloud.com/api/retirar");
                bancoCentralDevuelto.setPin("2222");
            } else if (codigoEntidadCuentaCorriente > 2000 && codigoEntidadCuentaCorriente < 2999) {
                bancoCentralDevuelto.setUrl("http://banco-samuvl.rhcloud.com/banktastic-banco-api/api/retirar");
                bancoCentralDevuelto.setPin("2045");
            } else {
                throw new BusinessException("Entidad Bancaria", "Ha introducido una entidad internacional y no trabajamos con ellas.");
            }
        } else {
            throw new BusinessException("Pin", "Ha introducido un pin incorrecto.");
        }
*/
        if ((codigoEntidadBancariaComprobar > 2000 && codigoEntidadBancariaComprobar < 2999) && bancoCentral.getPin().equals(3333)) {
            if (codigoEntidadCuentaCorriente > 0 && codigoEntidadCuentaCorriente < 999) {
                bancoCentralDevuelto.setUrl("http://banco-GERMAN.rhcloud.com/retirar");
                bancoCentralDevuelto.setPin("1111");
            } else if (codigoEntidadCuentaCorriente > 1000 && codigoEntidadCuentaCorriente < 1999) {
                bancoCentralDevuelto.setUrl("http://ecobanco-juankza.rhcloud.com/api/retirar");
                bancoCentralDevuelto.setPin("2222");
            } else if (codigoEntidadCuentaCorriente > 2000 && codigoEntidadCuentaCorriente < 2999) {
                bancoCentralDevuelto.setUrl("http://banco-samuvl.rhcloud.com/banktastic-banco-api/api/retirar");
                bancoCentralDevuelto.setPin("2045");
            } else {
                throw new BusinessException("Entidad Bancaria", "Ha introducido una entidad internacional y no trabajamos con ellas.");
            }
        } else {
            throw new BusinessException("Pin", "Ha introducido un pin incorrecto.");
        }

        return bancoCentralDevuelto;
    }

}
