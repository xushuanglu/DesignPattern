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
 
        // ֻ��Ҫȷ��ʵ������һ�����ݿ���ʶ����factory
        // IFactory factory=new MysqlFactory();
        IFactory factory=new OracleFactory();
 
        // �����������ݿ���ʽ�������
        IUser userOperation=factory.createUser();
 
        userOperation.getUser(1);
        userOperation.insert(user);
 
        // �����������ݿ���ʽ�������
        ILogin loginOperation=factory.createLogin();
 
        loginOperation.insert(login);
        loginOperation.getLogin(1);
 
    }
}
