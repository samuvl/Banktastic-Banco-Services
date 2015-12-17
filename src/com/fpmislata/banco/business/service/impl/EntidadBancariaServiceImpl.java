package com.fpmislata.banco.business.service.impl;

import com.aeat.valida.Validador;
import com.fpmislata.banco.business.domain.EntidadBancaria;
import com.fpmislata.banco.business.service.EntidadBancariaService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.core.BusinessMessage;
import com.fpmislata.banco.persistence.dao.EntidadBancariaDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Samuel Lao
 */
public class EntidadBancariaServiceImpl implements EntidadBancariaService {

    @Autowired
    EntidadBancariaDAO entidadBancariaDAO;

    @Override
    public EntidadBancaria get(int id) {
        return entidadBancariaDAO.get(id);
    }
    
    
    @Override
    public EntidadBancaria insert(EntidadBancaria entidadBancaria) throws BusinessException {

        List<BusinessMessage> businessMessages = new ArrayList<>();

        if (entidadBancaria.codigoEntidad.equals("0")) {
            BusinessMessage businessMessage = new BusinessMessage("codigoEntidad: ", "No puede ser 0..");
            businessMessages.add(businessMessage);
        }

        if ((entidadBancaria.nombre == null) || (entidadBancaria.nombre.trim().isEmpty())) {
            BusinessMessage businessMessage = new BusinessMessage("Nombre: ", "El campo está vacio.");
            businessMessages.add(businessMessage);
        }

        if (entidadBancaria.fechaCreacion == null)  {
            BusinessMessage businessMessage = new BusinessMessage("Fecha: ", "El campo está vacio.");
            businessMessages.add(businessMessage);
        }
        
        if ((entidadBancaria.direccion == null) || (entidadBancaria.direccion.trim().isEmpty())) {
            BusinessMessage businessMessage = new BusinessMessage("Dirección: ", "El campo está vacio.");
            businessMessages.add(businessMessage);
        }
        
        Validador validador = new Validador();
        int validado = validador.checkNif(entidadBancaria.cif);
        
        if (validado == 0 || validado < 0) {
            BusinessMessage businessMessage = new BusinessMessage("CIF: ", "El formato es erróneo.");
            businessMessages.add(businessMessage);
        }
       
        if (businessMessages.size() > 0) {
            throw new BusinessException(businessMessages);
        }

        return entidadBancariaDAO.insert(entidadBancaria);

    }

    /**
     *
     * @param entidadBancaria
     * @return
     * @throws BusinessException
     */
    @Override
    public EntidadBancaria update(EntidadBancaria entidadBancaria) throws BusinessException {
        List<BusinessMessage> businessMessages = new ArrayList<>();

        if (entidadBancaria.codigoEntidad.equals("0")) {
            BusinessMessage businessMessage = new BusinessMessage("codigoEntidad: ", "No puede ser 0.");
            businessMessages.add(businessMessage);
        }

        if ((entidadBancaria.nombre == null) || (entidadBancaria.nombre.trim().isEmpty())) {
            BusinessMessage businessMessage = new BusinessMessage("Nombre: ", "El campo está vacio.");
            businessMessages.add(businessMessage);
        }

        if (entidadBancaria.fechaCreacion == null)  {
            BusinessMessage businessMessage = new BusinessMessage("Fecha: ", "El campo está vacio.");
            businessMessages.add(businessMessage);
        }
        
        if ((entidadBancaria.direccion == null) || (entidadBancaria.direccion.trim().isEmpty())) {
            BusinessMessage businessMessage = new BusinessMessage("Dirección: ", "El campo está vacio.");
            businessMessages.add(businessMessage);
        }
        Validador validador = new Validador();
        int validado = validador.checkNif(entidadBancaria.cif);
        
        if (validado == 0 || validado < 0 ) {
            BusinessMessage businessMessage = new BusinessMessage("CIF: ", "El formato es erróneo.");
            businessMessages.add(businessMessage);
        }

        if (businessMessages.size() > 0) {
            throw new BusinessException(businessMessages);
        }

        return entidadBancariaDAO.update(entidadBancaria);
    }

    @Override
    public boolean delete(int id) {
        return entidadBancariaDAO.delete(id);
    }

    @Override
    public List<EntidadBancaria> findAll() {
        List<EntidadBancaria> entidadesBancarias = entidadBancariaDAO.findAll();
        return entidadesBancarias;
    }

    @Override
    public List<EntidadBancaria> findByNombre(String nombre) {
        List<EntidadBancaria> entidadesBancarias = entidadBancariaDAO.findByNombre(nombre);
        return entidadesBancarias;
    }

}
