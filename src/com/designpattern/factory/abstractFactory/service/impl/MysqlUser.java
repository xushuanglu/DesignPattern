package com.designpattern.factory.abstractFactory.service.impl;

import com.designpattern.factory.abstractFactory.eitity.User;
import com.designpattern.factory.abstractFactory.service.IUser;

public class MysqlUser implements IUser {

	public void insert(User user) {
		System.out.println("��mysql�е�user���в���һ��Ԫ��");
	}

	public User getUser(int id) {
		System.out.println("��mysql�е�user��õ�idΪ" + id + "��һ������");
		return null;
	}
	
}
