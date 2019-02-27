package com.designpattern.factory.abstractFactory.service.impl;

import com.designpattern.factory.abstractFactory.eitity.Login;
import com.designpattern.factory.abstractFactory.service.ILogin;

public class OracleLogin implements ILogin {

	public void insert(Login login) {
		System.out.println("�� Oracle ��� Login �������һ������");
	}

	public Login getLogin(int id) {
		System.out.println("ͨ�� uid �� Oracle ��� Login ��õ���һ������");
		return null;
	}
	
}
