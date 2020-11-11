package com.miro.widgetcrud.util;

import com.miro.widgetcrud.model.CartesianCoordinates;
import com.miro.widgetcrud.model.Widget;

public class WidgetUtil {
	public static boolean isInArea(Widget widget, CartesianCoordinates firstPoint, CartesianCoordinates secondPoint) {
		Integer widgetFirstPointX =  widget.getCoordinates().getX() - widget.getWidth() / 2;
		Integer widgetFirstPointY =  widget.getCoordinates().getY() - widget.getHeight() / 2;
		Integer widgetSecondPointX =  widgetFirstPointX + widget.getWidth();
		Integer widgetSecondPointY =  widgetFirstPointY + widget.getHeight();

		return (firstPoint.getX()<=widgetFirstPointX)&&
				(firstPoint.getY()<=widgetFirstPointY)&&
				(secondPoint.getX()>=widgetSecondPointX)&&
				(secondPoint.getY()>=widgetSecondPointY);
	}
}
