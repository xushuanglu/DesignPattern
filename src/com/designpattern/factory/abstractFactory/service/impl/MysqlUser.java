package com.designpattern.factory.abstractFactory.service.impl;

import com.designpattern.factory.abstractFactory.eitity.User;
import com.designpattern.factory.abstractFactory.service.IUser;

public class MysqlUser implements IUser {

	public void insert(User user) {
		System.out.println("在mysql中的user表中插入一条元素");
	}

	public User getUser(int id) {
		System.out.println("在mysql中的user表得到id为" + id + "的一条数据");
		return null;
	}
	
}
