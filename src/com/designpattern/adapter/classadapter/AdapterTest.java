package com.designpattern.adapter.classadapter;

/**
 * ������
 * @author Administrator
 *
 */
public class AdapterTest {

	public static void main(String[] args) {
		System.out.println("===============��������==============");
        Mobile mobile = new Mobile();
        mobile.charging(new VoltageAdapter());
	}
}
