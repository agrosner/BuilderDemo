package com.raizlabs.handler;

import com.builder.core.IntentBuilder;
import com.raizlabs.BuilderManager;
import com.raizlabs.definition.BaseDefinition;
import com.raizlabs.definition.IntentBuilderDefinition;
import com.raizlabs.validator.IntentBuilderValidator;
import com.raizlabs.validator.Validator;

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

}
