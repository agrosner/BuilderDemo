package com.raizlabs.builder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.builder.core.IntentArg;
import com.builder.core.IntentBuilder;

/**
 * Description:
 */
@IntentBuilder
public class TestActivity extends AppCompatActivity {

    @IntentArg(key = "title")
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
