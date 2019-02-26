package com.designpattern.proxy.proxy;

import java.lang.reflect.Proxy;
import com.designpattern.proxy.staticproxy.service.BuyHouse;
import com.designpattern.proxy.staticproxy.service.impl.BuyHouseImpl;

/**
 * @Auther: dan gao
 * @Description:第二步：编写测试类
 * @Date: 20:38 2018/1/12 0012
 */
public class DynamicProxyTest {
	
	public static void main(String[] args) {

		BuyHouse buyHouse = new BuyHouseImpl();
		BuyHouse proxyBuyHouse = (BuyHouse) Proxy.newProxyInstance(BuyHouse.class.getClassLoader(),
				new Class[] { BuyHouse.class }, new DynamicProxyHandler(buyHouse));
		proxyBuyHouse.buyHouse();

	}

}
