package com.element;

import org.openqa.selenium.NoSuchElementException;

import com.exception.IosDriverException;
import com.exception.WaitForElementException;
import com.ios.AppiumDriver;
import com.mobile.driver.element.Element;
import com.mobile.driver.nativedriver.NativeDriver;

public class IOSElement extends Element {

	protected AppiumDriver driver;

	protected String foundBy;

	@Override
	public boolean isExists() {
		return driver.isElementExists(foundBy);
	}

	@Override
	public void setDriver(NativeDriver driver) {
		Class<? extends NativeDriver> driverClass = driver.getClass();
		if (!driverClass.equals(AppiumDriver.class)) {
			throw new IosDriverException("Driver class " + driverClass + " is invalid for IOSElement");
		}
		this.driver = (AppiumDriver) driver;
	}

	@Override
	public void waitForElement(long timeoutSeconds) {
		try {
			driver.waitForElement(foundBy, timeoutSeconds);
		}
		catch(NoSuchElementException e) {
			throw new WaitForElementException("Failed to wait element " + e.getMessage());
		}

	}

	public void waitImplicitly(int seconds) {
		driver.waitImplicitly(seconds);
	}

	public String getFoundBy() {
		return foundBy;
	}

	@Override
	public void setFoundBy(String foundBy) {
		this.foundBy = foundBy;
	}

	@Override
	public void setFoundBy(int foundBy) {
		this.foundBy = String.valueOf(foundBy);
	}

	@Override
	public String getLocator() {

		return getFoundBy();
	}

}
