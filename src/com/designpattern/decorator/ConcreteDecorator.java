package com.designpattern.decorator;

/**
 * ����װ����
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
