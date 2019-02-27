package com.designpattern.factory.simplefactory;

public class EasyFactory {
	 
    // �򵥹����������ַ���������Ӧ�Ķ���
    public static Operation createOperation(String name) {
        Operation operationObj = null;
        switch (name) {
            case "+":
                operationObj = new Add();
                break;
            case "-":
                operationObj = new Sub();
                break;
            case "*":
                operationObj = new Mul();
                break;
            case "/":
                operationObj = new Div();
                break;
        }
        return operationObj;
    }
}