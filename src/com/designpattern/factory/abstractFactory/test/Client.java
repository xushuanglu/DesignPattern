package com.designpattern.factory.abstractFactory.test;

import com.designpattern.factory.abstractFactory.eitity.Login;
import com.designpattern.factory.abstractFactory.eitity.User;
import com.designpattern.factory.abstractFactory.service.IFactory;
import com.designpattern.factory.abstractFactory.service.ILogin;
import com.designpattern.factory.abstractFactory.service.IUser;
import com.designpattern.factory.abstractFactory.service.impl.OracleFactory;

public class Client {
	 
    public static void main(String[] args){
 
        User user=new User();
        Login login = new Login();
 
        // 只需要确定实例化哪一个数据库访问对象给factory
        // IFactory factory=new MysqlFactory();
        IFactory factory=new OracleFactory();
 
        // 已与具体的数据库访问解除了耦合
        IUser userOperation=factory.createUser();
 
        userOperation.getUser(1);
        userOperation.insert(user);
 
        // 已与具体的数据库访问解除了耦合
        ILogin loginOperation=factory.createLogin();
 
        loginOperation.insert(login);
        loginOperation.getLogin(1);
 
    }
}
