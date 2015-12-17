package com.fpmislata.banco.core;

/**
 *
 * @author alumno
 */
public class BusinessMessage {
    private String fieldName;
    private String mensaje;

    public BusinessMessage(String fieldName, String mensaje) {
        this.fieldName = fieldName;
        this.mensaje = mensaje;
    }
    
    public BusinessMessage (){
        
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
