package com.mobile.driver.wait;

import java.util.concurrent.TimeUnit;

import com.mobile.driver.nativedriver.NativeDriver;

/**
 * 
 * @author Maryia_Sakalouskaya
 * 
 *         Implementation for Mobile Wait based on basic FluenWait
 * 
 *         Default timeout is 30s
 * 
 *         Default Polling time is 100 ms
 * 
 */
public class MobileWait extends FluentWait<NativeDriver> {

	private static final long DEFAULT_POLLING_TIMEOUT = 100;

	private static final long DEFAULT_TIMEOUT_SEC = 30;

	public MobileWait(NativeDriver driver, long timeoutSec, long pollingTimeoutMillis) {
		super(driver);
		withTimeout(timeoutSec, TimeUnit.SECONDS).pollingEvery(pollingTimeoutMillis, TimeUnit.MILLISECONDS);
	}

	public MobileWait(NativeDriver driver, long timeoutSec) {
		super(driver);
		withTimeout(timeoutSec, TimeUnit.SECONDS).pollingEvery(DEFAULT_POLLING_TIMEOUT, TimeUnit.MILLISECONDS);
	}

	public MobileWait(NativeDriver driver) {
		super(driver);
		withTimeout(DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS).pollingEvery(DEFAULT_POLLING_TIMEOUT, TimeUnit.MILLISECONDS);
	}
}
