package com.designpattern.proxy.staticproxy.service.impl;

import com.designpattern.proxy.staticproxy.service.BuyHouse;

/**
 * ������������������
 * @author Administrator
 */
public class BuyHouseProxy implements BuyHouse {
	
	private BuyHouse buyHouse;
	
	public BuyHouseProxy(final BuyHouse buyHouse) {
		this.buyHouse = buyHouse;
	}

	@Override
	public void buyHouse() {
		System.out.println("��ǰ׼��");
		buyHouse.buyHouse();
		System.out.println("�򷿺�װ��");
	}

}
