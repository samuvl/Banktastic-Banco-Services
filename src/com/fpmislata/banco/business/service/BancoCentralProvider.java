
package com.fpmislata.banco.business.service;

import com.fpmislata.banco.business.domain.BancoCentral;
import com.fpmislata.banco.core.BusinessException;


public interface BancoCentralProvider {
     public String getUrlByEntidad(BancoCentral bancoCentral) throws BusinessException;
     public String getUrlByGerman() throws BusinessException;
}
