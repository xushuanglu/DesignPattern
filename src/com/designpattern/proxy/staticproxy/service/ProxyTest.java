package com.designpattern.proxy.staticproxy.service;

import com.designpattern.proxy.staticproxy.service.impl.BuyHouseImpl;
import com.designpattern.proxy.staticproxy.service.impl.BuyHouseProxy;

/**
 * 第四步：编写测试类
 * @author Administrator
 */
public class ProxyTest {

	public static void main(String[] args) {
		
		BuyHouse buyHouse = new BuyHouseImpl();
		buyHouse.buyHouse();
		BuyHouseProxy buyHouseProxy = new BuyHouseProxy(buyHouse);
		buyHouseProxy.buyHouse();
		
	}
	
}
