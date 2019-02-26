package com.designpattern.factory;

import com.designpattern.factory.service.Shape;

public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}

}
