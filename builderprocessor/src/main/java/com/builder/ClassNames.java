package com.builder;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

/**
 * Description: Holds all of the referenced / guessed {@link TypeName} in one place for easy modifications.
 */
public class ClassNames {

    private static final String BASE_PACKAGE = "com.builder.internal";

    public static final ClassName BASE_INTENT_BUILDER = ClassName.get(BASE_PACKAGE, "BaseIntentBuilder");

    public static final ClassName I_INTENT_INJECTOR = ClassName.get(BASE_PACKAGE, "IIntentInjector");

    public static final ClassName BUILDER_HOLDER = ClassName.get(BASE_PACKAGE, "Builder_Holder");

    public static final ClassName INTENT = ClassName.get("android.content", "Intent");
}
