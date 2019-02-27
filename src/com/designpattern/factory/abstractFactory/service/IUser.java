package com.designpattern.factory.abstractFactory.service;

import com.designpattern.factory.abstractFactory.eitity.User;

public interface IUser {
	
    public void insert(User user);
    
    public User getUser(int uid);
    
}