package com.designpattern.factory.simplefactory;

public class Div implements Operation {

	// ��������
	public double getResult(double numberA, double numberB) throws Exception {
		if (numberB == 0) {
			throw new Exception("��������Ϊ0��");
		}
		return numberA / numberB;
	}
	
}