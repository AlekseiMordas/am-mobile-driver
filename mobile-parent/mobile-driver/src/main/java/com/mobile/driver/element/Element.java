package com.mobile.driver.element;

import com.mobile.driver.nativedriver.NativeDriver;

/**
 * 
 * @author Maryia_Sakalouskaya
 * 
 *         Abstract representation of mobile UI elements
 */
public abstract class Element {

	public abstract void setFoundBy(String foundBy);

	public abstract void setFoundBy(int index);

	public abstract void setDriver(NativeDriver driver);

	public abstract boolean isExists();

	public abstract void waitForElement(long timeoutSeconds);

	public abstract String getLocator();
}
