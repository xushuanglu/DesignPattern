package com.designpattern.adapter.objectaceadapter;

import com.designpattern.adapter.classadapter.Mobile;
import com.designpattern.adapter.classadapter.Voltage220;

/**
 * С��:
	����������������������ʵ����ͬһ��˼�룬ֻ����ʵ�ַ�ʽ��ͬ�� 
	���ݺϳɸ���ԭ����ϴ��ڼ̳У� 
	�����������������������̳�src�ľ��������⣬Ҳ����ǿ��dst�����ǽӿڡ� 
	ͬ������ʹ�óɱ����ͣ�����
	����װ����ģʽ��ѧʱ���ܻ�Ū�죬����Ҫ���壬װ�����Ƕ�src��װ�Σ�ʹ���ߺ��޲����src�Ѿ���װ���ˣ�ʹ�����÷����䣩�� ������������Ժ�ʹ���ߵ��÷����Ǳ�ġ� 
	����װ�����÷��� setSrc->setSrc�������������÷���setSrc->setAdapter.)
 * @author Administrator
 *
 */
public class ObjectAdapterTest {

	public static void main(String[] args) {
		System.out.println("\n===============����������==============");
        VoltageAdapter2 voltageAdapter2 = new VoltageAdapter2(new Voltage220());
        Mobile mobile2 = new Mobile();
        mobile2.charging(voltageAdapter2);
	}
}
