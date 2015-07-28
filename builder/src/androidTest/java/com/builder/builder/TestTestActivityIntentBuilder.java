package com.builder.builder;

import android.content.Intent;
import android.test.AndroidTestCase;

/**
 * Description:
 */
public class TestTestActivityIntentBuilder extends AndroidTestCase {

    public void testIntentBuilder() {
        Intent completedIntent = new TestActivity_IntentBuilder()
                .setTitle("this is my title")
                .build();

        assertNotNull(completedIntent.getStringExtra("title"));
        assertEquals(completedIntent.getStringExtra("title"), "this is my title");
    }
}
