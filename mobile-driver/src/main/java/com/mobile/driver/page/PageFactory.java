package com.mobile.driver.page;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import com.annotation.FindBy;
import com.mobile.driver.element.Element;
import com.mobile.driver.nativedriver.NativeDriver;

/**
 * 
 * @author Maryia_Sakalouskaya
 * 
 *         PageFactory to create Mobile Page objects
 * 
 *         Page should containt eny Elements
 * 
 */
public class PageFactory {

	public static <T extends Page> T initElements(NativeDriver driver, Class<T> pageClass) {
		T page = instantiatePage(driver, pageClass);
		initElements(driver, page);
		return page;
	}

	private static <T extends Page> T instantiatePage(NativeDriver driver, Class<T> pageClass) {
		try {
			try {
				Constructor<T> constructor = pageClass.getConstructor(new Class[] { NativeDriver.class });
				return constructor.newInstance(driver);
			} catch (NoSuchMethodException e) {
				return pageClass.newInstance();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static <T extends Page> void initElements(NativeDriver driver, T page) {
		for (Field field : page.getClass().getDeclaredFields()) {

			try {

				Class<?> fieldClass = field.getType();

				if (Element.class.isAssignableFrom(fieldClass)) {
					Constructor<?> elementConstructor = fieldClass.getConstructor();

					Element element = (Element) elementConstructor.newInstance();

					element.setDriver(driver);
					FindBy annotation = field.getAnnotation(FindBy.class);

					if (null != annotation) {
						int id = annotation.id();
						String locator = annotation.locator();
						if (id != -1) {
							element.setFoundBy(id);
						} else if (!locator.isEmpty()) {
							element.setFoundBy(locator);
						}
					}
					field.setAccessible(true);
					field.set(page, element);

				}

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
