package com.builder.builder;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.builder.Builder;

/**
 * Description:
 */
public class TestTestActivityIntentBuilder extends AndroidTestCase {

    public void testIntentBuilder() {
        Intent completedIntent = new TestActivity_IntentBuilder()
                .setTitle("this is my title")
                .build(getContext());

        assertNotNull(completedIntent.getStringExtra("title"));
        assertEquals(completedIntent.getStringExtra("title"), "this is my title");

        TestActivity testActivity = new TestActivity();
        Builder.inject(testActivity, completedIntent);
    }
}
