package com.builder.internal;

import android.content.Intent;

/**
 * Description: Used in injecting the {@link Intent} into the specified {@link T} class.
 */
public interface IIntentInjector<T> {

    void inject(T object, Intent intent);
}
