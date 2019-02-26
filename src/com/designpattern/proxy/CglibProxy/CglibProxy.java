package com.designpattern.proxy.CglibProxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @Auther: dan gao
 * @Description:��һ��������CGLIB������
 * @Date: 20:38 2018/1/16 0016
 */
public class CglibProxy implements MethodInterceptor {
	private Object target;

	public Object getInstance(final Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("��ǰ׼��");
		Object result = methodProxy.invokeSuper(object, args);
		System.out.println("�򷿺�װ��");
		return result;
	}
}
