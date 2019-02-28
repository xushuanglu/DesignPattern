package com.designpattern.singleton.懒汉式;

/**
 * 借助于 ThreadLocal，我们可以实现双重检查模式的变体。我们将临界资源线程局部化，
 * 具体到本例就是将双重检测的第一层检测条件 if (instance == null) 转换为 线程局部范围内的操作 。
 * 这里的 ThreadLocal 也只是用作标识而已，用来标识每个线程是否已访问过：如果访问过，
 * 则不再需要走同步块，这样就提高了一定的效率。对应的代码清单如下：
 * @author Administrator
 *
 */
public class SingletonThreadLocal {

    // ThreadLocal 线程局部变量
	private static ThreadLocal<SingletonThreadLocal> threadLocal = new ThreadLocal<SingletonThreadLocal>();
	private static SingletonThreadLocal singletonThreadLocal = null;
	
	private SingletonThreadLocal() {
		
	}
	
	public static SingletonThreadLocal getSingletonThreadLocal() {
		if(threadLocal.get() == null) {// 第一次检查：该线程是否第一次访问
			createSingletonThreadLocal();
		}
		return singletonThreadLocal;
	}
	
	public static void createSingletonThreadLocal() {
        synchronized (SingletonThreadLocal.class) {
            if (singletonThreadLocal == null) {          // 第二次检查：该单例是否被创建
            	singletonThreadLocal = new SingletonThreadLocal();   // 只执行一次
            }
        }
        threadLocal.set(singletonThreadLocal);// 将单例放入当前线程的局部变量中 
	}
	
}
