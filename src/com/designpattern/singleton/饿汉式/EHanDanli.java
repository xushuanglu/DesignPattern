package com.designpattern.singleton.����ʽ;

/**
 * ��������ģʽ
 * @author Administrator
 *
 */
public class EHanDanli {

	//�˴����������ʵ����ֱ��ʵ������������ص�ʱ��������ʵ����������������
	private static EHanDanli dl = new EHanDanli();
	
	 //�����޲ι����������ڵ���ʵ��
	private EHanDanli() {
		
	}
	
	//���幫�������������Ѵ����ĵ���
	public static EHanDanli getInstance() {
		return dl;
	}
	
}
