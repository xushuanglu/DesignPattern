package com.designpattern.adapter.classadapter;

/**
 * ���ܣ�Client�ࣺ�ֻ� .��Ҫ5V��ѹ
 */
public class Mobile {

	/**
	 * ��緽��
	 * 
	 * @param voltage5
	 */
	public void charging(Voltage5 voltage5) {
		if (voltage5.output5V() == 5) {
			System.out.println("��ѹ�ոպ�5V����ʼ���");
		} else if (voltage5.output5V() > 5) {
			System.out.println("��ѹ����5V�������� ��Ҫ���note7��");
		}
	}
}
