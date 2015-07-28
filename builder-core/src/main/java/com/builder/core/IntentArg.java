package com.builder.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface IntentArg {

    /**
     * @return The name of the intent argument.
     */
    String key();
}
