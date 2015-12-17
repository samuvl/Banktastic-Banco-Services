package com.fpmislata.banco.security.impl;

import com.fpmislata.banco.security.CheckManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author samu_
 */
public class CheckManagerImpl implements CheckManager{
    
    @Override
    public boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean checkNick(String nick) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+$");
        Matcher matcher = pattern.matcher(nick);
        return matcher.matches();

    }
    
//        public static void main(String[] args) {
//        CheckManagerImpl a = new CheckManagerImpl();
//        
//        System.out.println("----------------------email----------------");
//        System.out.println(a.checkEmail("samu_mislata@hotmail.com"));
//        
//        System.out.println("--------------------nick-----------------");
//        System.out.println(a.checkNick("Asdad2"));
//    }
}
