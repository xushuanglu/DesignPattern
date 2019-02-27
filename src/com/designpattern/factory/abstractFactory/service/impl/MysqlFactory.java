package com.designpattern.factory.abstractFactory.service.impl;

import com.designpattern.factory.abstractFactory.service.IFactory;
import com.designpattern.factory.abstractFactory.service.ILogin;
import com.designpattern.factory.abstractFactory.service.IUser;

public class MysqlFactory implements IFactory {

	@Override
	public IUser createUser() {
		return new MysqlUser(); // 访问mysql中User表的对象
	}
	
    public ILogin createLogin() {
        return new MysqlLogin();
    }

}
