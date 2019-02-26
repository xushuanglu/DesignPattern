package com.designpattern.factory;

import com.designpattern.factory.service.Shape;

public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Circle::draw() method.");
	}

}
