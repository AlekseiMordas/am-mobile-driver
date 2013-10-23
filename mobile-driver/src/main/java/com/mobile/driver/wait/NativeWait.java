package com.mobile.driver.wait;

import java.util.concurrent.TimeUnit;

import com.mobile.driver.nativedriver.NativeDriver;

public class NativeWait extends FluentWait<NativeDriver> {

	private static final long DEFAULT_POLLING_TIMEOUT = 100;

	private static final long DEFAULT_TIMEOUT_SEC = 30;

	public NativeWait(NativeDriver driver, long timeoutSec, long pollingTimeoutMillis) {
		super(driver);
		withTimeout(timeoutSec, TimeUnit.SECONDS).pollingEvery(pollingTimeoutMillis, TimeUnit.MILLISECONDS);
	}

	public NativeWait(NativeDriver driver, long timeoutSec) {
		super(driver);
		withTimeout(timeoutSec, TimeUnit.SECONDS).pollingEvery(DEFAULT_POLLING_TIMEOUT, TimeUnit.MILLISECONDS);
	}

	public NativeWait(NativeDriver driver) {
		super(driver);
		withTimeout(DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS).pollingEvery(DEFAULT_POLLING_TIMEOUT, TimeUnit.MILLISECONDS);
	}
}
