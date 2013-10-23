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

	void waitForElement(final String locator, long timeOutInSeconds);

	void touch(String locator);

	void type(String locator, String text);

	void type(int index, String text);

	void clear(String locator);

	void clear(int index);

	void takeScreenshot(String screenshotName);

	void click(String locator);

	void setLandscapeOrientation();

	void setPortraitOrientation();

	boolean scrollDown();

	boolean scrollToTop();

	boolean isVisible(String locator, String drawableId);

	boolean waitForText(String text, long timeout);

	boolean scrollUp();

	boolean waitForText(String text, int minNumberOfMatches, long timeOut, boolean doScroll, boolean onlyVisible);

	boolean isElementExists(String foundBy);

	String getViewText(String foundBy);

	void clickLong(String foundBy);

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

	boolean scrollToBottom();

	void flick(float fromX, float fromY, float toX, float toY);

	void touch(float x, float y);

	boolean waitForLogMessage(String[] logCmd, final String messageToWait, int timeoutSeconds);
}
