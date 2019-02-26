package com.designpattern.proxy.staticproxy.service.impl;

import com.designpattern.proxy.staticproxy.service.BuyHouse;

/**
 * 第三步：创建代理类
 * @author Administrator
 */
public class BuyHouseProxy implements BuyHouse {
	
	private BuyHouse buyHouse;
	
	public BuyHouseProxy(final BuyHouse buyHouse) {
		this.buyHouse = buyHouse;
	}

	@Override
	public void buyHouse() {
		System.out.println("买房前准备");
		buyHouse.buyHouse();
		System.out.println("买房后装修");
	}

}
