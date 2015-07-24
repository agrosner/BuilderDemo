package com.raizlabs.validator;

import com.raizlabs.BuilderManager;
import com.raizlabs.definition.IntentBuilderDefinition;

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
