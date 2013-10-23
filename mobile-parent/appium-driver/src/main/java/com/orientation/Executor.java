package com.orientation;

import java.net.URL;

import org.openqa.selenium.remote.HttpCommandExecutor;

public class Executor {
	private static HttpCommandExecutor executor;

	public static HttpCommandExecutor getExecutor(URL url) {
		if (executor == null) {
			executor = new HttpCommandExecutor(url);
		}
		return executor;
	}
}
