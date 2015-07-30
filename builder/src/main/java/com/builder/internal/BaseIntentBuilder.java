package com.builder.internal;

import android.content.Context;
import android.content.Intent;

/**
 * Description: Holds common properties for the generated classes.
 */
public abstract class BaseIntentBuilder<T> {

    protected Intent intent;

    protected Intent getIntent() {
        if (intent == null) {
            intent = new Intent();
        }
        return intent;
    }

    protected abstract Class<T> getTypeClass();

    protected BaseIntentBuilder() {

    }

    /**
     * @return The completed {@link Intent} object. Subsequently clears out the contained {@link Intent},
     * so we can reuse this object if needed.
     */
    public Intent build(Context context) {
        Intent retIntent = intent;
        retIntent.setClass(context, getTypeClass());
        intent = null;
        return retIntent;
    }
}
