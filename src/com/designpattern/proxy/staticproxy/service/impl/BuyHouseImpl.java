package com.designpattern.proxy.staticproxy.service.impl;

import com.designpattern.proxy.staticproxy.service.BuyHouse;

/**
 * 第二步：实现服务接口
 * @author Administrator
 *
 */
public class BuyHouseImpl implements BuyHouse {

	@Override
	public void buyHouse() {
		System.out.println("我要买房");
	}

}
