package com.mobile.driver.wait.conditions;

import org.apache.log4j.Logger;

import com.mobile.driver.element.Element;
import com.mobile.driver.nativedriver.NativeDriver;

public class ExpectedConditions {
	private static final Logger log = Logger.getLogger(ExpectedConditions.class.getName());

	public static ExpectedCondition<Boolean> isVisible(final Element element) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(NativeDriver driver) {
				String el = element.getLocator();
				log.info("Is '" + el + "' element visible");
				return driver.isVisible(el);
			}

			@Override
			public String toString() {
				return "Is '" + element.getLocator() + "' element visible";
			}
		};

	}

	public static ExpectedCondition<Boolean> isNotVisible(final Element element) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(NativeDriver driver) {
				String el = element.getLocator();
				log.info("Is '" + el + "' element not visible");
				return !driver.isVisible(el);
			}

			@Override
			public String toString() {
				return "Is '" + element.getLocator() + "' element not visible";
			}
		};

	}

}
