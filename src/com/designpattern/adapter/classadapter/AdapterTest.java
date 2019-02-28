package com.designpattern.adapter.classadapter;

/**
 * ²âÊÔÀà
 * @author Administrator
 *
 */
public class AdapterTest {

	public static void main(String[] args) {
		System.out.println("===============ÀàÊÊÅäÆ÷==============");
        Mobile mobile = new Mobile();
        mobile.charging(new VoltageAdapter());
	}
}
