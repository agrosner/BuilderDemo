package com.builder;

import android.content.Intent;

import com.builder.core.IntentBuilder;
import com.builder.internal.Builder_Holder;

/**
 * Description:
 */
public class Builder {

    private static Builder_Holder holder;

    static Builder_Holder getOrInitializeHolder() {
        if (holder == null) {
            try {
                holder = (Builder_Holder) Class.forName("com.builder.Builder_Holder_Generated").newInstance();
            } catch (Exception e) {
                // throwing the exception is better to acknowledge something is wrong
                // rather than supressing it.
                throw new RuntimeException(e);
            }
        }
        return holder;
    }

    /**
     * Will inject the specified class with the specified {@link Intent} object.
     *
     * @param object The object to initialize members for. It must have a {@link IntentBuilder}
     *               annotation, otherwise this call will fail.
     */
    @SuppressWarnings("unchecked")
    public static void inject(Object object, Intent intent) {
        Class type = object.getClass();
        getOrInitializeHolder().getIntentInjector(type).inject(object, intent);
    }
}
