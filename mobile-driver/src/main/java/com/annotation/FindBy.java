package com.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author Maryia_Sakalouskaya
 * 
 *         FindBy annotation to identify element's locator. Could be String or
 *         Index
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FindBy {

	String locator() default "";

	int id() default -1;

}
