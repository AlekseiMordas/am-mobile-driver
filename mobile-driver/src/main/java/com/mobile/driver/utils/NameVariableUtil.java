package com.mobile.driver.utils;

import java.lang.reflect.Field;

public class NameVariableUtil {
	public static <T> String getNameVariable(Class<T> clazz, String matcher) {
		Field[] fields = clazz.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				if (field.get(clazz).toString().equals(matcher)) {
					return field.getName();
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	}

}
