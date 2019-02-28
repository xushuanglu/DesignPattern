package com.designpattern.singleton.懒汉式;

/**
 * 懒汉模式单例
 * （1）懒汉式
　　  何为懒？顾名思义，就是不做事，这里也是同义，懒汉式就是不在系统加载时就创建类的单例，而是在第一次使用实例的时候再创建。
 * @author Administrator
 */
public class LHanDanli {

	//定义一个私有类变量来存放单例，私有的目的是指外部无法直接获取这个变量，而要使用提供的公共方法来获取
	private static LHanDanli dl = null;
	//定义私有构造器，表示只在类内部使用，亦指单例的实例只能在单例类内部创建
	private LHanDanli() {
		
	}
	
	/*
	 * 定义一个公共的公开的方法来返回该类的实例，由于是懒汉式，需要在第一次使用时生成实例，
	 * 所以为了线程安全，使用synchronized关键字来确保只会生成单例
	 */
	public static synchronized LHanDanli getSingleton() {
		if(dl == null) {
			dl = new LHanDanli();
		}
		return dl;
	}
	
	/*
	 * 同步代码块
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
	 * 同步延迟加载 — 使用内部类实现延迟加载
	 * 如上述代码所示，我们可以使用内部类实现线程安全的懒汉式单例，这种方式也是一种效率比较高的做法。
	 * 至于其为什么是线程安全的，其与问题 “为什么说饿汉式单例天生就是线程安全的？” 相类似，此不赘述。
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
