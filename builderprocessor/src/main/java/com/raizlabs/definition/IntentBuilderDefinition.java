package com.raizlabs.definition;

import com.builder.core.IntentBuilder;
import com.raizlabs.BuilderManager;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.TypeElement;

/**
 * Description: Holds information related to the {@link IntentBuilder} annotation.
 */
public class IntentBuilderDefinition extends BaseDefinition {

    public IntentBuilderDefinition(BuilderManager manager, TypeElement typeElement) {
        super(manager, typeElement);
        setOutputClassNamePostfix("_IntentBuilder");
    }

    @Override
    protected void onWriteDefinition(TypeSpec.Builder typeBuilder) {
        // here we add to the class.
    }
}
