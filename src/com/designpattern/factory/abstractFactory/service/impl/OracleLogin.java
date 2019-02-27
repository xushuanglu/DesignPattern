package com.designpattern.factory.abstractFactory.service.impl;

import com.designpattern.factory.abstractFactory.eitity.Login;
import com.designpattern.factory.abstractFactory.service.ILogin;

public class OracleLogin implements ILogin {

	public void insert(Login login) {
		System.out.println("对 Oracle 里的 Login 表插入了一条数据");
	}

	public Login getLogin(int id) {
		System.out.println("通过 uid 在 Oracle 里的 Login 表得到了一条数据");
		return null;
	}
	
}
