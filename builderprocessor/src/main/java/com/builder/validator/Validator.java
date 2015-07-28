package com.builder.validator;

import com.builder.BuilderManager;

/**
 * Description: Validates a class and throws errors if an annotation is used improperly.
 */
public interface Validator<ValidatorDefinition> {

    /**
     * @param manager             The manager.
     * @param validatorDefinition The definition to check and validate.
     * @return true if its a valid implementation, false if an error thrown.
     */
    boolean isValid(BuilderManager manager, ValidatorDefinition validatorDefinition);
}
