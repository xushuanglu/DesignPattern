package com.designpattern.factory.factory;

import com.designpattern.factory.Circle;
import com.designpattern.factory.Rectangle;
import com.designpattern.factory.Square;
import com.designpattern.factory.service.Shape;

public class ShapeFactory {

	// ʹ�� getShape ������ȡ��״���͵Ķ���
	public Shape getShape(String shapeType) {
		if (shapeType == null) {
			return null;
		}
		if (shapeType.equalsIgnoreCase("CIRCLE")) {
			return new Circle();
		} else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();
		} else if (shapeType.equalsIgnoreCase("SQUARE")) {
			return new Square();
		}
		return null;
	}

}
