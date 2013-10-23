package com.ios;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
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

	@Override
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

	@Override
	public void touch(String locator) {
		LOGGER.info("Touching element '" + locator + "' ...");
		driver.findElement(By.xpath(locator)).click();
		LOGGER.info("Element '" + locator + "' touched Successfully");
	}

	@Override
	public void type(String locator, String text) {
		driver.findElement(By.xpath(locator)).sendKeys(text);
		LOGGER.info("Type text '" + text + "' ...");

	}

	@Override
	public void type(int index, String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear(String locator) {
		driver.findElement(By.xpath(locator)).clear();
		LOGGER.info("Clear field '" + locator + "' ...");

	}

	@Override
	public void clear(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void takeScreenshot(String screenshotName) {
		AppiumUtils.makeScreenshot(driver, screenshotName);
	}

	@Override
	public void click(String locator) {
		driver.findElement(By.xpath(locator)).click();
		LOGGER.info("Element '" + locator + "' clicked");
	}

	@Override
	public void setLandscapeOrientation() {
		LOGGER.info("Setting Landscape orientation");
		SetOrientationCommand command = new SetOrientationCommand(
				driver.getSessionId(), Orientation.LANDSCAPE, executor);
		command.<Boolean>execute();
	}

	@Override
	public void setPortraitOrientation() {
		LOGGER.info("Setting Portrait orientation");
		SetOrientationCommand command = new SetOrientationCommand(
				driver.getSessionId(), Orientation.PORTRAIT, executor);
		command.<Boolean>execute();
	}

	@Override
	public boolean scrollDown() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

	@Override
	public boolean scrollToTop() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

	@Override
	public boolean isVisible(String locator, String drawableId) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

	@Override
	public boolean waitForText(String text, long timeout) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

	@Override
	public boolean scrollUp() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

	@Override
	public boolean waitForText(String text, int minNumberOfMatches,
			long timeOut, boolean doScroll, boolean onlyVisible) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");
	}

	@Override
	public boolean isElementExists(String foundBy) {
		try {
			driver.findElement(By.xpath(foundBy));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getViewText(String foundBy) {
		return driver.findElement(By.xpath(foundBy)).getText();
	}

	@Override
	public void clickLong(String foundBy) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");

	}

	@Override
	public void clickLong(float x, float y) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");

	}

	@Override
	public void dragTo(float fromX, float fromY, float toX, float toY, int steps) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Need implementation");

	}

	@Override
	public Rectangle getElementLocation(String foundBy) {
		int x = driver.findElement(By.xpath(foundBy)).getLocation().x;
		int y = driver.findElement(By.xpath(foundBy)).getLocation().y;
		Point point = new Point(x, y);
		return new Rectangle(point);
	}

	@Override
	public boolean isVisible(String foundBy) {
		// TODO Auto-generated method stub
		return driver.findElement(By.xpath(foundBy)).isDisplayed();
	}

	@Override
	public void waitForTextNotVisible(String text, int timeout) {
		throw new RuntimeException("Need implementation");

	}

	@Override
	public Dimension getScreenSize() {
		throw new RuntimeException("Need implementation");
	}

	@Override
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

	@Override
	public boolean searchText(String text) {
		// TODO Auto-generated method stub
		return driver.findElement(By.name(text)).isDisplayed();
	}

	@Override
	public String findLogMessage(String[] logCmd, String messageToFind,
			int timeoutSeconds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSeekBarProgressMax(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setProgress(String id, int progress) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSeekBarProgress(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getThumbOffset(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean searchButton(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void swipeRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean scrollToBottom() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void flick(float fromX, float fromY, float toX, float toY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void touch(float x, float y) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean waitForLogMessage(String[] logCmd, String messageToWait,
			int timeoutSeconds) {
		// TODO Auto-generated method stub
		return false;
	}

	public void quit() {
		LOGGER.info("Quit driver");
		driver.quit();
	}

}
