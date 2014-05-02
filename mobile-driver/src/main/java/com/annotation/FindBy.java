package com.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author Aleksei_Mordas
 * 
 *         FindBy annotation to identify element's locator. Could be String or
 *         Index
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FindBy {

	String locator() default "";
	
	String ios7() default "";

	int id() default -1;

}
