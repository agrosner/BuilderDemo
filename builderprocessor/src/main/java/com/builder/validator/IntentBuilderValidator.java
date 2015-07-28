package com.builder.validator;

import com.builder.BuilderManager;
import com.builder.definition.IntentBuilderDefinition;

/**
 * Description:
 */
public class IntentBuilderValidator implements Validator<IntentBuilderDefinition> {
    @Override
    public boolean isValid(BuilderManager manager, IntentBuilderDefinition intentBuilderDefinition) {
        // validate our class.
        return true;
    }
}
