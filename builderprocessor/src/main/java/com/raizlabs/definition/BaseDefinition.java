package com.raizlabs.definition;

import com.raizlabs.BuilderManager;

import javax.lang.model.element.TypeElement;

/**
 * Description: Holds onto a common-set of fields and provides a common-set of methods to output class files.
 */
public class BaseDefinition {

    protected BuilderManager manager;

    protected TypeElement typeElement;

    public BaseDefinition(BuilderManager manager, TypeElement typeElement) {
        this.manager = manager;
        this.typeElement = typeElement;
    }


}
