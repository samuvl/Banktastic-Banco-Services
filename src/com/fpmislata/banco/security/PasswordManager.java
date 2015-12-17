package com.fpmislata.banco.security;

/**
 *
 * @author alumno
 */
public interface PasswordManager {
    public String encrypt(String password);
    public boolean check(String password, String encyptedPassword);
    public boolean checkComplexity(String password) ;
}
