package com.designpattern.factory.factorymethod;

import com.designpattern.factory.simplefactory.Operation;

public class Client {
	 
    public static void main(String[] args) throws Exception {
 
        // ʹ�÷������ʵ��������������Ϊ�ַ����ǿ���ͨ�������ı��
        Factory addFactory = (Factory) Class.forName("com.designpattern.factory.factorymethod.AddFactory").newInstance();
        Factory subFactory = (Factory) Class.forName("com.designpattern.factory.factorymethod.SubFactory").newInstance();
 
        // ͨ���������󴴽���Ӧ��ʵ������
        Operation add = addFactory.createOperation();
        Operation sub = subFactory.createOperation();
 
        System.out.println(add.getResult(1, 1));
        System.out.println(sub.getResult(1, 1));
    }
}
