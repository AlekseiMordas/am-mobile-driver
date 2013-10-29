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

	public AppiumDriver(String url, DesiredCapabilities capabilities) {
		try {
			executor = Executor.getExecutor(new URL(url));
			driver = new RemoteWebDriver(executor, capabilities);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		} catch (MalformedURLException e) {
			LOGGER.error("Appium Server couldn't be found");
			throw new RuntimeException();
		}
	}

	public String getOrientation() {
		GetOrientationCommand command = new GetOrientationCommand(
				driver.getSessionId(), executor);
		return command.<String>execute();
	}


	public void waitForElement(final String locator, long timeOutInSeconds) {
		LOGGER.info("Waiting for element '" + locator + "' exists during "
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

	public void waitImplicitly(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}


	public void touch(String locator) {
		LOGGER.info("Touching element '" + locator + "' ...");
		driver.findElement(By.xpath(locator)).click();
		LOGGER.info("Element '" + locator + "' touched Successfully");
	}

	public void touchByName(String locator) {
		LOGGER.info("Touching element '" + locator + "' ...");
		driver.findElement(By.name(locator)).click();
		LOGGER.info("Element '" + locator + "' touched Successfully");
	}
 
	public void type(String locator, String text) {
		driver.findElement(By.xpath(locator)).sendKeys(text);
		LOGGER.info("Type text '" + text + "' ...");

	}

 
	public void type(int index, String text) {
		// TODO Auto-generated method stub

	}

 
	public void clear(String locator) {
		driver.findElement(By.xpath(locator)).clear();
		LOGGER.info("Clear field '" + locator + "' ...");

	}

 
	public void clear(int index) {
		// TODO Auto-generated method stub

	}

 
	public void takeScreenshot(String screenshotName) {
		AppiumUtils.makeScreenshot(driver, screenshotName);
	}

 
	public void click(String locator) {
		driver.findElement(By.xpath(locator)).click();
		LOGGER.info("Element '" + locator + "' clicked");
	}

 
	public void setLandscapeOrientation() {
		LOGGER.info("Setting Landscape orientation");
		SetOrientationCommand command = new SetOrientationCommand(
				driver.getSessionId(), Orientation.LANDSCAPE, executor);
		command.<Boolean>execute();
	}

 
	public void setPortraitOrientation() {
		LOGGER.info("Setting Portrait orientation");
		SetOrientationCommand command = new SetOrientationCommand(
				driver.getSessionId(), Orientation.PORTRAIT, executor);
		command.<Boolean>execute();
	}

 
	public boolean scrollDown() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

 
	public boolean scrollToTop() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

 
	public boolean isVisible(String locator, String drawableId) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

 
	public boolean waitForText(String text, long timeout) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

 
	public boolean scrollUp() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
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

	public String getAttribute(String foundBy,String name) {
		return driver.findElement(By.xpath(foundBy)).getAttribute(name);
	}
	
	public String getViewText(String foundBy) {
		return driver.findElement(By.xpath(foundBy)).getText();
	}

 
	public void clickLong(String foundBy) {
		WebElement element  = driver.findElement(By.xpath(foundBy));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Double> tapObject = new HashMap<String, Double>();
		tapObject.put("x", (double) element.getLocation().getX()); 
		tapObject.put("y", (double) element.getLocation().getY()); 
		tapObject.put("duration", 1.0);
		js.executeScript("mobile: tap", tapObject);
		LOGGER.info("Long tap on element");

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
		int x = driver.findElement(By.xpath(foundBy)).getLocation().x;
		int y = driver.findElement(By.xpath(foundBy)).getLocation().y;
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

 
	public boolean scrollToBottom() {
		// TODO Auto-generated method stub
		return false;
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

}
