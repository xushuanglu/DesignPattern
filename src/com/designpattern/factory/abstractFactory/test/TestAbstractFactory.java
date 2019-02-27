package com.designpattern.factory.abstractFactory.test;

import com.designpattern.factory.abstractFactory.eitity.User;
import com.designpattern.factory.abstractFactory.service.IUser;
import com.designpattern.factory.abstractFactory.service.SqlFactory;
import com.designpattern.factory.abstractFactory.service.impl.MysqlFactory;

public class TestAbstractFactory {

	public static void main(String[] args) {
		SqlFactory factory = new MysqlFactory();
		IUser userOperator = factory.createUser();
		userOperator.getUser(1);
		userOperator.insert(new User());
	}
}
