package com.raizlabs.validator;

import com.raizlabs.BuilderManager;
import com.raizlabs.definition.IntentArgDefinition;

/**
 * Description:
 */
public class IntentArgValidator implements Validator<IntentArgDefinition> {

    @Override
    public boolean isValid(BuilderManager manager, IntentArgDefinition intentArgDefinition) {

        return true;
    }
}
