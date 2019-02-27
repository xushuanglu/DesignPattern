package com.designpattern.factory.simpleandabstarct;

import com.designpattern.factory.abstractFactory.eitity.Login;
import com.designpattern.factory.abstractFactory.eitity.User;
import com.designpattern.factory.abstractFactory.service.ILogin;
import com.designpattern.factory.abstractFactory.service.IUser;

public class Client {
	 
    public static void main(String[] args){
 
        User user=new User();
        Login login = new Login();
 
        // 直接得到实际的数据库访问实例，而不存在任何依赖
        IUser userOperation= EasyFactory.createUser();
 
        userOperation.getUser(1);
        userOperation.insert(user);
 
        // 直接得到实际的数据库访问实例，而不存在任何依赖
        ILogin loginOperation=EasyFactory.createLogin();
 
        loginOperation.insert(login);
        loginOperation.getLogin(1);
 
    }
}
