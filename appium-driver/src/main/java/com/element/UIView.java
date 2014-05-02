package com.element;

import java.awt.Rectangle;

import org.openqa.selenium.Dimension;

public class UIView extends IOSElement {

	public void touch() {
		driver.touch(foundBy, variableName);
	}
	
	public void touchLong() {
		driver.clickLong(foundBy, variableName);
	}

	public void type(String text) {
		driver.type(foundBy, variableName, text);
	}

	public Dimension getSize() {
		return driver.getSize(foundBy);
	}

	public void clear() {
		driver.clear(foundBy, variableName);
	}

	public String getText() {
		return driver.getViewText(foundBy);
	}
	
	public String getAttribute(String attribute) {
		return driver.getAttribute(foundBy, attribute);
	}
	
	public void touchByName() {
		driver.touchByName(foundBy, variableName);
	}
	
	public void touchWithCoordinates(double x, double y) {
		driver.touchWithCoordinates(x, y, variableName);
	}
	
	public Rectangle getLocation() {
		return driver.getElementLocation(foundBy);
	}
}
