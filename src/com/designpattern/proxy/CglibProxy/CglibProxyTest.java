package com.designpattern.proxy.CglibProxy;

import com.designpattern.proxy.staticproxy.service.BuyHouse;
import com.designpattern.proxy.staticproxy.service.impl.BuyHouseImpl;

public class CglibProxyTest {

	public static void main(String[] args) {
		BuyHouse buyHouse = new BuyHouseImpl();
		CglibProxy cglibProxy = new CglibProxy();
		BuyHouseImpl buyHouseCglibProxy = (BuyHouseImpl) cglibProxy.getInstance(buyHouse);
		buyHouseCglibProxy.buyHouse();
	}
}
