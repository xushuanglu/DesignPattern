package com.designpattern.singleton.����ʽ;

public class Test {

	public static void main(String[] args) {
		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new TestThread();
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}

}

	class TestThread extends Thread {
		@Override
		public void run() {
			// ���ڲ�ͬ����ģʽ��ʵ�֣�ֻ�������Ӧ�ĵ����������乫�о�̬��������������
			int hash = LHanDanli.getSingleton().hashCode();
			System.out.println(hash);
		}
	}
