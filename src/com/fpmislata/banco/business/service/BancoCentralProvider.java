
package com.fpmislata.banco.business.service;

import com.fpmislata.banco.business.domain.BancoCentral;
import com.fpmislata.banco.core.BusinessException;


public interface BancoCentralProvider {
     public BancoCentral getBancoCentralAnswer(BancoCentral bancoCentral) throws BusinessException;
}
