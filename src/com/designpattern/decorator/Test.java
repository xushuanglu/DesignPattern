package com.designpattern.decorator;

public class Test {

	public static void main(String[] args) {
		// ʹ��װ����
		Component component = new ConcreteDecorator(new ConcretComponent());
		component.biu();
	}
}
