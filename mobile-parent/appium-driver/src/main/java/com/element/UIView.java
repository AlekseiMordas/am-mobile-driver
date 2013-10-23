package com.element;

import java.awt.Rectangle;
import org.openqa.selenium.Dimension;

public class UIView extends IOSElement {

	public void touch() {
		driver.touch(foundBy);
	}

	public void type(String text) {
		driver.type(foundBy, text);
	}

	public Dimension getSize() {
		return driver.getSize(foundBy);
	}

	public void clear() {
		driver.clear(foundBy);
	}

	public String getText() {
		return driver.getViewText(foundBy);
	}
	
	public Rectangle getLocation() {
		return driver.getElementLocation(foundBy);
	}
}
