package com.designpattern.factory.abstractFactory.service;

public interface IFactory {

	public IUser createUser(); // 用于访问User表的对象

    public ILogin createLogin();
    
}
