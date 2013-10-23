package com.mobile.driver.wait.conditions;

import com.mobile.driver.nativedriver.NativeDriver;
import com.mobile.driver.wait.Function;

public abstract interface ExpectedCondition<T> extends Function<NativeDriver, T> {

}
