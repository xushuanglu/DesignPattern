package com.designpattern.factory.reflexandsimple;

import com.designpattern.factory.abstractFactory.service.ILogin;
import com.designpattern.factory.abstractFactory.service.IUser;

public class EasyFactory1 {
	 
    private static String packName = "DesignPattern.abstractFactory";
    private static String sqlName = "mysql";
 
    public static IUser createUser() throws Exception{
        String className = packName+"."+sqlName+"User";
        return (IUser)Class.forName(className).newInstance();
    }
 
    public static ILogin createLogin() throws Exception{
        String className = packName+"."+sqlName+"Login";
        return (ILogin)Class.forName(className).newInstance();
    }
    
}
