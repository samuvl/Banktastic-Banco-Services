
package com.fpmislata.banco.business.domain;

import java.io.Serializable;


public class BancoCentral implements Serializable{
    private String codigoCuentaCorriente;
    private int pin;

    public BancoCentral(String codigoCuentaCorriente, int pin) {
        this.codigoCuentaCorriente = codigoCuentaCorriente;
        this.pin = pin;
    }

    public String getCodigoCuentaCorriente() {
        return codigoCuentaCorriente;
    }

    public void setCodigoCuentaCorriente(String codigoCuentaCorriente) {
        this.codigoCuentaCorriente = codigoCuentaCorriente;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
    
    
}
