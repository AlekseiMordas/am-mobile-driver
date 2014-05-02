package com.mobile.driver.page;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import com.annotation.FindBy;
import com.mobile.driver.element.Element;
import com.mobile.driver.nativedriver.DriverType;
import com.mobile.driver.nativedriver.NativeDriver;

/**
 * 
 * @author Aleksei_Mordas
 * 
 *         PageFactory to create Mobile Page objects
 * 
 *         Page should containt eny Elements
 * 
 */
public class PageFactory {

	public static <T extends Page> T initElements(NativeDriver driver,
			Class<T> pageClass) {
		T page = instantiatePage(driver, pageClass);
		initElements(driver, page);
		return page;
	}

	private static <T extends Page> T instantiatePage(NativeDriver driver,
			Class<T> pageClass) {
		try {
			try {
				Constructor<T> constructor = pageClass
						.getConstructor(new Class[] { NativeDriver.class });
				return constructor.newInstance(driver);
			} catch (NoSuchMethodException e) {
				return pageClass.newInstance();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static <T extends Page> void initElements(NativeDriver driver,
			T page) {
		for (Field field : page.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {

				Class<?> fieldClass = field.getType();

				if (Element.class.isAssignableFrom(fieldClass)) {
					Constructor<?> elementConstructor = fieldClass
							.getConstructor();

					Element element = (Element) elementConstructor
							.newInstance();

					element.setDriver(driver);
					FindBy annotation = field.getAnnotation(FindBy.class);

					if (null != annotation) {
						int id = annotation.id();
						DriverType type = driver.getDriverType();
						String locator = annotation.locator();
						String ios7 = annotation.ios7();
						if (id != -1) {
							element.setFoundBy(id);
						} else if (!locator.isEmpty()) {
							switch (type) {
							case IOS:
								element.setFoundBy(locator);
								
								break;
							case IOS7:
								if (!ios7.isEmpty()) {
									element.setFoundBy(ios7);
								} else
									element.setFoundBy(locator);
								break;
							case ANDROID:
								element.setFoundBy(locator);
								break;
							}

						}
						element.setVariableName(field.getName());
					}
					
					
					field.set(page, element);

				}

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
