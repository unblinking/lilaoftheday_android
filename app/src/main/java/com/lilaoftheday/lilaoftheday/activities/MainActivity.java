package com.lilaoftheday.lilaoftheday.activities;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.fragments.MainFragment;
import com.lilaoftheday.lilaoftheday.utilities.Utilities;

public class MainActivity extends AppCompatActivity {

    Fragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Initialize default preference values.
        PreferenceManager.setDefaultValues(
                getApplicationContext(),
                R.xml.preferences,
                false // only if this method has never been called in the past
        );

        // Create a toolbar.
        android.support.v7.widget.Toolbar toolbar;
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Only do this stuff when the activity is started for the very first time.
        if (savedInstanceState == null) {
            mainFragment = MainFragment.newInstance();
            Utilities.replaceFragmentInContainer(
                    R.id.mainContainer,
                    getSupportFragmentManager(),
                    mainFragment,
                    "Lila of the day"
            );
            Utilities.updateSupportActionBarTitle(this);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        Handle tool bar item clicks here. The tool bar will automatically handle clicks on the
        Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        */
        int id = item.getItemId();
        if (id == R.id.action_preferences) {

            Intent intent = new Intent();
            String className = "com.lilaoftheday.lilaoftheday.activities.PreferencesActivity";
            intent.setClassName(getApplicationContext(), className);
            startActivity(intent);

            Utilities.updateSupportActionBarTitle(this);

            return true;

        }
        /*if (id == R.id.action_notification_check) {
            AlarmChecker alarmChecker;
            alarmChecker = new AlarmChecker();
            alarmChecker.checkAlarm(getApplicationContext());
        }*/
        return super.onOptionsItemSelected(item);
    }

}

