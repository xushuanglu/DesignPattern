package com.designpattern.decorator;

/**
 * 具体装饰类
 * @author Administrator
 *
 */
public class ConcreteDecorator extends Decorator{

	public ConcreteDecorator(Component component) {
		super(component);
	}
	
	public void biu() {
        System.out.println("ready?go!");
        this.component.biu();
    }

}
