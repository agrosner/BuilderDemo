package com.builder;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

/**
 * Description: Holds all of the referenced / guessed {@link TypeName} in one place for easy modifications.
 */
public class TypeNames {

    private static final String BASE_PACKAGE = "com.raizlabs.builder";

    public static final TypeName BASE_INTENT_BUILDER = ClassName.get(BASE_PACKAGE, "BaseIntentBuilder");
}
