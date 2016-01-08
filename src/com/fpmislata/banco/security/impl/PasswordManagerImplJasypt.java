package com.fpmislata.banco.security.impl;

import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.security.PasswordManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author Samuel Lao
 */
public class PasswordManagerImplJasypt implements PasswordManager {

    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

    @Override
    public String encrypt(String password) {
        return passwordEncryptor.encryptPassword(password);
    }

    @Override
    public boolean check(String password, String encyptedPassword) {
        return passwordEncryptor.checkPassword(password, encyptedPassword);
    }

    @Override
    public boolean checkComplexity(String password) {
        boolean passed;
        if (password == null) {
            password = "";
        }

        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$");
        Matcher matcher = pattern.matcher(password);

        if (matcher.find()) {
            passed = true;
        } else {
            passed = false;
        }
        return passed;
    }
//
//    public static void main(String[] args) throws BusinessException {
//        PasswordManagerImplJasypt passwordManagerImplJasypt = new PasswordManagerImplJasypt();
//
//        System.out.println(passwordManagerImplJasypt.encrypt("a"));
//        System.out.println(passwordManagerImplJasypt.check("a", "NdRS0GeFxhEa6VTYC7IDp4zMNTGzCY49"));
//
//        System.out.println("-----------------complejidad---------------------");
//        System.out.println(passwordManagerImplJasypt.checkComplexity("abc7ABC"));
//
//    }
}
