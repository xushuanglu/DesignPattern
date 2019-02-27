package com.designpattern.factory.abstractFactory.service.impl;

import com.designpattern.factory.abstractFactory.eitity.User;
import com.designpattern.factory.abstractFactory.service.IUser;

public class OracleUser implements IUser {

	@Override
	public void insert(User user) {
		System.out.println("��oracle�е�user���в���һ��Ԫ��");
	}

	@Override
	public User getUser(int uid) {
		System.out.println("��oracle�е�user��õ�idΪ" + uid + "��һ������");
		return null;
	}

}
