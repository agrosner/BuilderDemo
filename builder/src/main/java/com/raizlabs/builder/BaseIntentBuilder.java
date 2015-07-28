package com.raizlabs.builder;

import android.content.Intent;

/**
 * Description: Holds common properties for the generated classes.
 */
public abstract class BaseIntentBuilder {

    protected Intent intent;

    /**
     * @return The completed {@link Intent} object. Subsequently clears out the contained {@link Intent},
     * so we can reuse this object if needed.
     */
    public Intent build() {
        Intent retIntent = intent;
        intent = null;
        return retIntent;
    }
}
