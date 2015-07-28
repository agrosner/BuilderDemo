package com.builder.handler;

import com.builder.BuilderManager;
import com.builder.core.IntentBuilder;
import com.builder.definition.BaseDefinition;
import com.builder.definition.IntentBuilderDefinition;
import com.builder.validator.IntentBuilderValidator;
import com.builder.validator.Validator;
import com.squareup.javapoet.JavaFile;

import java.io.IOException;

import javax.lang.model.element.TypeElement;

/**
 * Description: Handles the {@link IntentBuilder} annotation. Generates {@link IntentBuilderDefinition}
 * to wrap the annotations found into a readable and exportable class.
 */
public class IntentBuilderHandler extends BaseHandler<IntentBuilder> {

    @Override
    protected Class<IntentBuilder> getAnnotationClass() {
        return IntentBuilder.class;
    }

    @Override
    protected Validator getValidator() {
        return new IntentBuilderValidator();
    }

    @Override
    protected BaseDefinition createDefinition(TypeElement element, BuilderManager manager) {
        return new IntentBuilderDefinition(manager, element);
    }

    @Override
    protected void onValidated(BuilderManager manager, BaseDefinition baseDefinition) {
        IntentBuilderDefinition definition = (IntentBuilderDefinition) baseDefinition;
        JavaFile javaFile = JavaFile
                .builder(manager.packageOf(baseDefinition.typeElement), definition.getInjectorTypeSpec())
                .build();
        try {
            javaFile.writeTo(manager.getFiler());
        } catch (IOException e) {
            // crash if error!
            throw new RuntimeException(e);
        }

        manager.addIntentBuilderForType(definition.elementClassName, definition);
    }
}
