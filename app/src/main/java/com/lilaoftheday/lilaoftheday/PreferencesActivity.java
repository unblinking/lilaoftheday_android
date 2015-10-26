package com.lilaoftheday.lilaoftheday;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toolbar;

public class PreferencesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        /* Using a toolbar as the actionbar */
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setActionBar(toolbar);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

}

