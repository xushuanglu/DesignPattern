package com.designpattern.factory.abstractFactory.service;

import com.designpattern.factory.abstractFactory.eitity.Login;

public interface ILogin {
	 
    public void insert(Login login);
    
    public Login getLogin(int id);
 
}