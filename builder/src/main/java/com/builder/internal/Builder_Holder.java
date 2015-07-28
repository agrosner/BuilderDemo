package com.builder.internal;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: The base for the generated class that holds all of the information and injectors we need.
 */
public abstract class Builder_Holder {

    protected final Map<Class, IIntentInjector> INTENT_INJECTOR_MAP = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> IIntentInjector<T> getIntentInjector(Class<T> type) {
        return INTENT_INJECTOR_MAP.get(type);
    }

}
