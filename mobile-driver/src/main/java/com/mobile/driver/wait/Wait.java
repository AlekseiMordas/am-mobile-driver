package com.mobile.driver.wait;

/**
 * 
 * @author Maryia_Sakalouskaya
 * 
 * @param <F>
 * 
 *            Mobile Wait implementation
 */
public interface Wait<F> {

	<T> T until(Function<? super F, T> isTrue);
}
