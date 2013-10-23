package com.mobile.driver.wait;

public class Clock {

	public long laterBy(long durationInMillis) {
		return System.currentTimeMillis() + durationInMillis;
	}

	public boolean isNowBefore(long endInMillis) {
		return System.currentTimeMillis() < endInMillis;
	}

	public long now() {
		return System.currentTimeMillis();
	}
}
