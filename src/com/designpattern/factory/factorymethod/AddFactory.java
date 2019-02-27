package com.designpattern.factory.factorymethod;

import com.designpattern.factory.simplefactory.Add;
import com.designpattern.factory.simplefactory.Operation;

public class AddFactory implements Factory {

	@Override
    public Operation createOperation() {
        System.out.println("º”∑®‘ÀÀ„");
        return new Add();
    }

}
