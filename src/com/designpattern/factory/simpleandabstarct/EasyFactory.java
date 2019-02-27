package com.designpattern.factory.simpleandabstarct;

import com.designpattern.factory.abstractFactory.service.ILogin;
import com.designpattern.factory.abstractFactory.service.IUser;
import com.designpattern.factory.abstractFactory.service.impl.MysqlLogin;
import com.designpattern.factory.abstractFactory.service.impl.MysqlUser;
import com.designpattern.factory.abstractFactory.service.impl.OracleLogin;
import com.designpattern.factory.abstractFactory.service.impl.OracleUser;

public class EasyFactory {

	// Êý¾Ý¿âÃû³Æ
	private static String db = "MySQL";
	// private static String db="Oracle";

	public static IUser createUser() {

		IUser user = null;
		switch (db) {
		case "MySQL":
			user = new MysqlUser();
			break;

		case "Oracle":
			user = new OracleUser();
			break;
		}
		return user;
	}

	public static ILogin createLogin() {

		ILogin login = null;
		switch (db) {
		case "MySQL":
			login = new MysqlLogin();
			break;

		case "Oracle":
			login = new OracleLogin();
			break;
		}
		return login;
	}
}
