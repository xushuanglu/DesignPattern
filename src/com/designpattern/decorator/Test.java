package com.designpattern.decorator;

public class Test {

	public static void main(String[] args) {
		// Ê¹ÓÃ×°ÊÎÆ÷
		Component component = new ConcreteDecorator(new ConcretComponent());
		component.biu();
	}
}
