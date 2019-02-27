package com.designpattern.factory.factorymethod;

import com.designpattern.factory.simplefactory.Operation;

/**
 * 工厂方法模式
 * @author Administrator
 *
 */
public interface Factory {
 
    public Operation createOperation() ;
 
}
