package com.designpattern.adapter.objectaceadapter;

import com.designpattern.adapter.classadapter.Voltage220;
import com.designpattern.adapter.classadapter.Voltage5;

/**
 * ���ܣ�����������ģʽ��
 * ���� src�࣬ʵ�� dst ��ӿڣ����src->dst�����䡣 ���Դﵽ���**������**�����⡣
 *     ����˼·�����������ģʽ��ͬ��ֻ�ǽ�Adapter�����޸ģ���β��̳�src�࣬���ǳ���src���ʵ�����Խ�������Ե����⡣ 
        �������� src�࣬ʵ�� dst ��ӿڣ����src->dst�����䡣 
	�����ݡ��ϳɸ���ԭ�򡱣���ϵͳ�о���ʹ�ù�����ϵ������̳й�ϵ����˴󲿷ֽṹ��ģʽ���Ƕ���ṹ��ģʽ���� 
 * @author Administrator
 */
public class VoltageAdapter2 implements Voltage5 {
	
    private Voltage220 mVoltage220;

    public VoltageAdapter2(Voltage220 voltage220) {
        mVoltage220 = voltage220;
    }

    @Override
    public int output5V() {
        int dst = 0;
        if (null != mVoltage220) {
            int src = mVoltage220.output220V();
            System.out.println("������������������ʼ�����ѹ");
            dst = src / 44;
            System.out.println("������ɺ������ѹ��" + dst);
        }
        return dst;
    }
}
