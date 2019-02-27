package com.designpattern.factory.abstractFactory.service.impl;

import com.designpattern.factory.abstractFactory.service.IUser;
import com.designpattern.factory.abstractFactory.service.SqlFactory;

public class OracleFactory implements SqlFactory {
	
	@Override
	public IUser createUser() {
		return new OracleUser(); // 访问oracle中User表的对象
	}
	
}
