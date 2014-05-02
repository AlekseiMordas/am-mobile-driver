package com.ios;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mobile.driver.nativedriver.DriverType;
import com.mobile.driver.nativedriver.NativeDriver;
import com.orientation.Executor;
import com.orientation.GetOrientationCommand;
import com.orientation.Orientation;
import com.orientation.SetOrientationCommand;
import com.utils.AppiumUtils;

/**
 * @author aleksei_mordas
 * 
 */
public class AppiumDriver implements NativeDriver {

	private RemoteWebDriver driver;

	private HttpCommandExecutor executor = null;

	private static final Logger LOGGER = Logger.getLogger(AppiumDriver.class);

	private DriverType type;
	
	public AppiumDriver(String url, DesiredCapabilities capabilities) {
		try {
			executor = Executor.getExecutor(new URL(url));
			driver = new RemoteWebDriver(executor, capabilities);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		} catch (MalformedURLException e) {
			LOGGER.error("Appium Server couldn't be found");
			throw new RuntimeException();
		}
	}

	public String getOrientation() {
		GetOrientationCommand command = new GetOrientationCommand(
				driver.getSessionId(), executor);
		return command.<String> execute();
	}

	public void waitForElement(final String locator, String nameVariable, long timeOutInSeconds) {
		LOGGER.info("Waiting for element '" + nameVariable + "' exists during "
				+ timeOutInSeconds + "sec timeout ...");
		new WebDriverWait(driver, timeOutInSeconds)
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						try {
							return d.findElement(By.xpath(locator))
									.isDisplayed();
						} catch (NoSuchElementException e) {
							return false;
						}
					}
				});
	}

	public void waitForElementByName(final String locator, String nameVariable, long timeOutInSeconds) {
		LOGGER.info("Waiting for element '" + nameVariable + "' exists during "
				+ timeOutInSeconds + "sec timeout ...");
		new WebDriverWait(driver, timeOutInSeconds)
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						try {
							return d.findElement(By.name(locator))
									.isDisplayed();
						} catch (NoSuchElementException e) {
							return false;
						}
					}
				});
	}

	public void waitImplicitly(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	public void touch(String locator, String nameVariable) {
		LOGGER.info("Touching element '" + nameVariable + "' ...");
		driver.findElement(By.xpath(locator)).click();
		LOGGER.info("Element '" + nameVariable + "' touched Successfully");
	}

	public void touchByName(String locator, String nameVariable) {
		LOGGER.info("Touching element '" + nameVariable + "' ...");
		driver.findElement(By.name(locator)).click();
		LOGGER.info("Element '" + nameVariable + "' touched Successfully");
	}

	public void type(String locator, String nameVariable, String text) {
		driver.findElement(By.xpath(locator)).sendKeys(text);
		LOGGER.info("Type text '" + text + "' to '" + nameVariable + "'");

	}

	public void touchWithCoordinates(double x, double y, String nameVariable) {
		HashMap<String, Double> tapObject = new HashMap<String, Double>();
		tapObject.put("x", x); // in pixels from left
		tapObject.put("y", y + 5); // in pixels from top
		JavascriptExecutor js = driver;
		js.executeScript("mobile: tap", tapObject);
		LOGGER.info("Element '" + nameVariable +  "' touched successfully by coordinates");
	}

	public void type(int index, String text) {
		// TODO Auto-generated method stub

	}

	public void clear(String locator, String nameVariable) {
		driver.findElement(By.xpath(locator)).clear();
		LOGGER.info("Clear field '" + nameVariable + "'");

	}

	public void clear(int index) {
		// TODO Auto-generated method stub

	}

	public void takeScreenshot(String screenshotName) {
		AppiumUtils.makeScreenshot(driver, screenshotName);
	}

	public void click(String locator, String nameVariable) {
		driver.findElement(By.xpath(locator)).click();
		LOGGER.info("Element '" + nameVariable + "' clicked");
	}

	public void setLandscapeOrientation() {
		LOGGER.info("Setting Landscape orientation");
		SetOrientationCommand command = new SetOrientationCommand(
				driver.getSessionId(), Orientation.LANDSCAPE, executor);
		command.<Boolean> execute();
	}

	public void setPortraitOrientation() {
		LOGGER.info("Setting Portrait orientation");
		SetOrientationCommand command = new SetOrientationCommand(
				driver.getSessionId(), Orientation.PORTRAIT, executor);
		command.<Boolean> execute();
	}

	

	public void swipe(double startX, double startY, double endX, double endY,
			double duration) {
		JavascriptExecutor js = getDriver();
		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
		swipeObject.put("startX", Double.valueOf(startX));
		swipeObject.put("startY", Double.valueOf(startY));
		swipeObject.put("endX", Double.valueOf(endX));
		swipeObject.put("endY", Double.valueOf(endY));
		swipeObject.put("duration", Double.valueOf(duration));
		js.executeScript("mobile: swipe", new Object[] { swipeObject });
		LOGGER.info("Swipe executed");
	}

	public boolean isVisible(String locator, String drawableId) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

	public boolean waitForText(String text, long timeout) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

	public void scrollTop() {
		JavascriptExecutor js = driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "top");
		js.executeScript("mobile: scroll", scrollObject);
		LOGGER.info("Scroll top");
	}

	public void scrollDown() {
		JavascriptExecutor js = driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);
		LOGGER.info("Scroll down");
	}
	
	public boolean waitForText(String text, int minNumberOfMatches,
			long timeOut, boolean doScroll, boolean onlyVisible) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

	public boolean isElementExists(String foundBy) {
		try {
			driver.findElement(By.xpath(foundBy));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getAttribute(String foundBy, String name) {
		return driver.findElement(By.xpath(foundBy)).getAttribute(name);
	}

	public String getViewText(String foundBy) {
		return driver.findElement(By.xpath(foundBy)).getText();
	}

	public void clickLong(String foundBy, String nameVariable) {
		WebElement element = driver.findElement(By.xpath(foundBy));
		JavascriptExecutor js = driver;
		HashMap<String, Double> tapObject = new HashMap<String, Double>();
		tapObject.put("x", (double) element.getLocation().getX());
		tapObject.put("y", (double) element.getLocation().getY());
		tapObject.put("duration", 1.0);
		js.executeScript("mobile: tap", tapObject);
		LOGGER.info("Long tap on '" + nameVariable + "'");

	}

	public void clickLong(float x, float y) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");

	}

	public void dragTo(float fromX, float fromY, float toX, float toY, int steps) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");

	}

	public Rectangle getElementLocation(String foundBy) {
		int x = 0;
		int y = 0;
		if (foundBy.contains("//")) {
			x = driver.findElement(By.xpath(foundBy)).getLocation().x;
			y = driver.findElement(By.xpath(foundBy)).getLocation().y;
		} else {
			x = driver.findElement(By.name(foundBy)).getLocation().x;
			y = driver.findElement(By.name(foundBy)).getLocation().y;
		}
		Point point = new Point(x, y);
		return new Rectangle(point);
	}

	public boolean isVisible(String foundBy) {
		// TODO Auto-generated method stub
		return driver.findElement(By.xpath(foundBy)).isDisplayed();
	}

	public void waitForTextNotVisible(String text, int timeout) {
		throw new RuntimeException("Need implementation");

	}

	public Dimension getScreenSize() {
		throw new RuntimeException("Need implementation");
	}

	public boolean isChildExists(String foundBy, String childId) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Dimension> getDimensions(String locator) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

	public org.openqa.selenium.Dimension getSize(String locator) {
		LOGGER.info("Get size of " + locator);
		return driver.findElement(By.xpath(locator)).getSize();
	}

	public boolean searchText(String text) {
		// TODO Auto-generated method stub
		return driver.findElement(By.name(text)).isDisplayed();
	}

	public String findLogMessage(String[] logCmd, String messageToFind,
			int timeoutSeconds) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getSeekBarProgressMax(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setProgress(String id, int progress) {
		// TODO Auto-generated method stub

	}

	public int getSeekBarProgress(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getThumbOffset(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean searchButton(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public void swipeRight() {
		// TODO Auto-generated method stub

	}

	public void swipeLeft() {
		// TODO Auto-generated method stub

	}



	public void flick(float fromX, float fromY, float toX, float toY) {
		// TODO Auto-generated method stub

	}

	public void touch(float x, float y) {
		// TODO Auto-generated method stub

	}

	public boolean waitForLogMessage(String[] logCmd, String messageToWait,
			int timeoutSeconds) {
		// TODO Auto-generated method stub
		return false;
	}

	public RemoteWebDriver getDriver() {
		return driver;
	}

	public void quit() {
		LOGGER.info("Quit driver");
		driver.quit();
	}

	public DriverType getDriverType() {
		return type;
	}

	public void setDriverType(String device) {
		if (device.contains("ios7".toUpperCase())) {
			type =  DriverType.IOS7;
		} else if (device.contains("android".toUpperCase())) {
			type =  DriverType.ANDROID;
		} else {
			type =  DriverType.IOS;
		}

	}

}
