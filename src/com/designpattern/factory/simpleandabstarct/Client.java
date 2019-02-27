package com.designpattern.factory.simpleandabstarct;

import com.designpattern.factory.abstractFactory.eitity.Login;
import com.designpattern.factory.abstractFactory.eitity.User;
import com.designpattern.factory.abstractFactory.service.ILogin;
import com.designpattern.factory.abstractFactory.service.IUser;

public class Client {
	 
    public static void main(String[] args){
 
        User user=new User();
        Login login = new Login();
 
        // ֱ�ӵõ�ʵ�ʵ����ݿ����ʵ�������������κ�����
        IUser userOperation= EasyFactory.createUser();
 
        userOperation.getUser(1);
        userOperation.insert(user);
 
        // ֱ�ӵõ�ʵ�ʵ����ݿ����ʵ�������������κ�����
        ILogin loginOperation=EasyFactory.createLogin();
 
        loginOperation.insert(login);
        loginOperation.getLogin(1);
 
    }
}
