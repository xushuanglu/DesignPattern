package com.designpattern.decorator;

/**
 * װ����
 * 
 * @author Administrator
 */
public class Decorator implements Component {

	Component component;

	public Decorator(Component component) {
		this.component = component;
	}

	@Override
	public void biu() {
		this.component.biu();
	}

}
