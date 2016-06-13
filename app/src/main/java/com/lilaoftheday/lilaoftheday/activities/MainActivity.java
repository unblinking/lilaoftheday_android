package com.lilaoftheday.lilaoftheday.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.fragments.MainFragment;
import com.lilaoftheday.lilaoftheday.utilities.Utilities;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize default preference values.
        PreferenceManager.setDefaultValues(
                this, // Context
                R.xml.preferences, // Resource ID
                false // only if this method has never been called in the past
        );

        // Create a toolbar.
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Only do this stuff when the activity is started for the very first time.
        if (savedInstanceState == null) {
            Utilities.replaceFragmentInContainer(
                    R.id.mainContainer,
                    getSupportFragmentManager(),
                    MainFragment.newInstance(),
                    "Lila of the day"
            );
        }

        updateSupportActionBarTitle("Lila of the day");

    }

    @Override
    public void onBackPressed() {

        FragmentManager fm = getSupportFragmentManager();
        int backStackCount = fm.getBackStackEntryCount();

        if (backStackCount == 1) {
            finish();
        } else {
            super.onBackPressed();
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
            intent.setClassName(this, className);
            startActivity(intent);

            return true;

        }
        // Notification check for debugging
        /*if (id == R.id.action_notification_check) {
            AlarmChecker alarmChecker;
            alarmChecker = new AlarmChecker();
            alarmChecker.checkAlarm(getApplicationContext());
        }*/
        return super.onOptionsItemSelected(item);
    }

    public void updateSupportActionBarTitle(String tag) {

        /*ActionBar ab = getSupportActionBar();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = Utilities.getActiveFragment(fm);
        if (ab != null && fragment != null) {
            ab.setTitle(fragment.getTag());
        }*/

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(tag);
        }


    }

}

