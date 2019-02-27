package com.designpattern.factory.factorymethod;

import com.designpattern.factory.simplefactory.Operation;
import com.designpattern.factory.simplefactory.Sub;

public class SubFactory implements Factory {

	@Override
    public Operation createOperation() {
        System.out.println("ºı∑®‘ÀÀ„");
        return new Sub();
    }

}
