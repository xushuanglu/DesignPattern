package com.designpattern.singleton.����ʽ;

/**
 * ����ģʽ����
 * ��1������ʽ
����  ��Ϊ��������˼�壬���ǲ����£�����Ҳ��ͬ�壬����ʽ���ǲ���ϵͳ����ʱ�ʹ�����ĵ����������ڵ�һ��ʹ��ʵ����ʱ���ٴ�����
 * @author Administrator
 */
public class LHanDanli {

	//����һ��˽�����������ŵ�����˽�е�Ŀ����ָ�ⲿ�޷�ֱ�ӻ�ȡ�����������Ҫʹ���ṩ�Ĺ�����������ȡ
	private static LHanDanli dl = null;
	//����˽�й���������ʾֻ�����ڲ�ʹ�ã���ָ������ʵ��ֻ���ڵ������ڲ�����
	private LHanDanli() {
		
	}
	
	/*
	 * ����һ�������Ĺ����ķ��������ظ����ʵ��������������ʽ����Ҫ�ڵ�һ��ʹ��ʱ����ʵ����
	 * ����Ϊ���̰߳�ȫ��ʹ��synchronized�ؼ�����ȷ��ֻ�����ɵ���
	 */
	public static synchronized LHanDanli getSingleton() {
		if(dl == null) {
			dl = new LHanDanli();
		}
		return dl;
	}
	
	/*
	 * ͬ�������
	 */
	public static  LHanDanli getSingleton1() {
		synchronized(LHanDanli.class) {
			if(dl == null) {
				dl = new LHanDanli();
			}
		}
		return dl;
	}
	
	/**
	 * ͬ���ӳټ��� �� ʹ���ڲ���ʵ���ӳټ���
	 * ������������ʾ�����ǿ���ʹ���ڲ���ʵ���̰߳�ȫ������ʽ���������ַ�ʽҲ��һ��Ч�ʱȽϸߵ�������
	 * ������Ϊʲô���̰߳�ȫ�ģ��������� ��Ϊʲô˵����ʽ�������������̰߳�ȫ�ģ��� �����ƣ��˲�׸����
	 * @author Administrator
	 *
	 */
	private static class Holder{
		private static LHanDanli lHanDanli = new LHanDanli();
	}
	
	public static  LHanDanli getSingleton2() {
		return Holder.lHanDanli;
	}
	
}
