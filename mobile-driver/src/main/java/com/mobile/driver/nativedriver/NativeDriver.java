package com.mobile.driver.nativedriver;

import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * 
 * @author Maryia_Sakalouskaya
 * 
 *         Native Driver interface to interact with mobile elements
 * 
 */
public interface NativeDriver {

	void waitForElement(final String locator,final String nameVariable, long timeOutInSeconds);

	void touch(String locator, String nameVariable);

	void type(String locator,final String nameVariable,String text);

	void type(int index, String text);

	void clear(String locator,final String nameVariable);

	void clear(int index);

	void takeScreenshot(String screenshotName);

	void click(String locator,final String nameVariable);

	void setLandscapeOrientation();

	void setPortraitOrientation();

	void scrollDown();
	
	void scrollToText(String text);

	void scrollTop();

	boolean isVisible(String locator, String drawableId);

	boolean waitForText(String text, long timeout);

	boolean waitForText(String text, int minNumberOfMatches, long timeOut, boolean doScroll, boolean onlyVisible);

	boolean isElementExists(String foundBy);

	String getViewText(String foundBy);

	void clickLong(String foundBy,final String nameVariable);

	void clickLong(float x, float y);

	void dragTo(float fromX, float fromY, float toX, float toY, int steps);

	Rectangle getElementLocation(String foundBy);

	boolean isVisible(String foundBy);

	void waitForTextNotVisible(final String text, int timeout);

	Dimension getScreenSize();

	boolean isChildExists(String foundBy, String childId);

	boolean searchText(String text);

	String findLogMessage(String[] logCmd, String messageToFind, int timeoutSeconds);

	int getSeekBarProgressMax(String id);

	void setProgress(String id, int progress);

	int getSeekBarProgress(String id);

	int getThumbOffset(String id);

	boolean searchButton(String id);

	void swipeRight();

	void swipeLeft();

	void flick(float fromX, float fromY, float toX, float toY);

	void touch(float x, float y);

	boolean waitForLogMessage(String[] logCmd, final String messageToWait, int timeoutSeconds);
	
	DriverType getDriverType();
	
	void setDriverType(String device);
}
