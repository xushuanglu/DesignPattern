package com.designpattern.factory.simplefactory;

public class Div implements Operation {

	// 除法计算
	public double getResult(double numberA, double numberB) throws Exception {
		if (numberB == 0) {
			throw new Exception("除数不能为0！");
		}
		return numberA / numberB;
	}
	
}