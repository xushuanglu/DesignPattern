package com.designpattern.factory.abstractFactory.service.impl;

import com.designpattern.factory.abstractFactory.service.IFactory;
import com.designpattern.factory.abstractFactory.service.ILogin;
import com.designpattern.factory.abstractFactory.service.IUser;

public class OracleFactory implements IFactory {
	
	@Override
	public IUser createUser() {
		return new OracleUser(); // ����oracle��User���Ķ���
	}
	
    public ILogin createLogin() {
        return new OracleLogin();
    }
	
}