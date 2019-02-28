package com.designpattern.singleton.����ʽ;

/**
 * �̰߳�ȫ������ʽ����
 * ������������ʾ��Ϊ���ڱ�֤������ǰ�����������Ч�ʣ�������Ҫ�� singleton3 ���еڶ��μ�飬
 * Ŀ���Ǳܿ������ͬ������Ϊ�����ͬ��ֻ���ڵ�һ�δ���ʵ��ʱ��ͬ����һ�������ɹ����Ժ��ȡʵ��ʱ�Ͳ���Ҫͬ����ȡ���ˣ���
 * ������������������ģ��������Ǳ���ע��һ�㣺����ʹ��volatile�ؼ������ε������á�
 * @author Administrator
 *
 */
public class SingletonVolatile {

	//ʹ��volatile�ؼ��ַ�ֹ��������Ϊ new Instance()��һ����ԭ�Ӳ��������ܴ���һ����������ʵ��
	private static volatile SingletonVolatile singleton;
	
	private SingletonVolatile() {
		
	}
	
	public static SingletonVolatile getSingleton3() {
        // Double-Check idiom
		if(singleton == null) {
			synchronized (SingletonVolatile.class) {//1
                // ֻ���ڵ�һ�δ���ʵ��ʱ��ͬ��
				if(singleton == null) {//2
					singleton = new SingletonVolatile();//3
				}
			}
		}
		return singleton;
	}
}
