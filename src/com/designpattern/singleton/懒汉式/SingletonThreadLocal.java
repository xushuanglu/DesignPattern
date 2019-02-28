package com.designpattern.singleton.����ʽ;

/**
 * ������ ThreadLocal�����ǿ���ʵ��˫�ؼ��ģʽ�ı��塣���ǽ��ٽ���Դ�ֲ߳̾�����
 * ���嵽�������ǽ�˫�ؼ��ĵ�һ�������� if (instance == null) ת��Ϊ �ֲ߳̾���Χ�ڵĲ��� ��
 * ����� ThreadLocal Ҳֻ��������ʶ���ѣ�������ʶÿ���߳��Ƿ��ѷ��ʹ���������ʹ���
 * ������Ҫ��ͬ���飬�����������һ����Ч�ʡ���Ӧ�Ĵ����嵥���£�
 * @author Administrator
 *
 */
public class SingletonThreadLocal {

    // ThreadLocal �ֲ߳̾�����
	private static ThreadLocal<SingletonThreadLocal> threadLocal = new ThreadLocal<SingletonThreadLocal>();
	private static SingletonThreadLocal singletonThreadLocal = null;
	
	private SingletonThreadLocal() {
		
	}
	
	public static SingletonThreadLocal getSingletonThreadLocal() {
		if(threadLocal.get() == null) {// ��һ�μ�飺���߳��Ƿ��һ�η���
			createSingletonThreadLocal();
		}
		return singletonThreadLocal;
	}
	
	public static void createSingletonThreadLocal() {
        synchronized (SingletonThreadLocal.class) {
            if (singletonThreadLocal == null) {          // �ڶ��μ�飺�õ����Ƿ񱻴���
            	singletonThreadLocal = new SingletonThreadLocal();   // ִֻ��һ��
            }
        }
        threadLocal.set(singletonThreadLocal);// ���������뵱ǰ�̵߳ľֲ������� 
	}
	
}
