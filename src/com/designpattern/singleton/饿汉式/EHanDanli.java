package com.designpattern.singleton.饿汉式;

/**
 * 饿汉单例模式
 * @author Administrator
 *
 */
public class EHanDanli {

	//此处定义类变量实例并直接实例化，在类加载的时候就完成了实例化并保存在类中
	private static EHanDanli dl = new EHanDanli();
	
	 //定义无参构造器，用于单例实例
	private EHanDanli() {
		
	}
	
	//定义公开方法，返回已创建的单例
	public static EHanDanli getInstance() {
		return dl;
	}
	
}
