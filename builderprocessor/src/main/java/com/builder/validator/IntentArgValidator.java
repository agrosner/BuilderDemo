package com.builder.validator;

import com.builder.BuilderManager;
import com.builder.definition.IntentArgDefinition;

/**
 * Description:
 */
public class IntentArgValidator implements Validator<IntentArgDefinition> {

    @Override
    public boolean isValid(BuilderManager manager, IntentArgDefinition intentArgDefinition) {

        return true;
    }
}
