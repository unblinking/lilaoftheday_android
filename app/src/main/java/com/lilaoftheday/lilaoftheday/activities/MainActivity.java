package com.lilaoftheday.lilaoftheday.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.fragments.MainFragment;
import com.lilaoftheday.lilaoftheday.utilities.FragmentBoss;

public class MainActivity extends AppCompatActivity {

    // TODO: Write unit tests.
    // https://developer.android.com/training/testing/unit-testing/local-unit-tests.html

    public Boolean savedInstanceNow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        savedInstanceNow = false;

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
            showMainFragment();
        }

        updateSupportActionBarTitle(getString(R.string.app_name));

    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        int backStackCount = fm.getBackStackEntryCount();
        // If there's only one fragment left open, finish() the activity. If not, proceed.
        if (backStackCount == 1 && !savedInstanceNow) {
            finish();
        } else if (backStackCount > 1 && !savedInstanceNow) {
            // Details to identify the mainFragment photo list.
            int containerViewId = R.id.mainContainer;
            String tagTitle = getString(R.string.app_name);
            int dbRecordId = -1;
            String tagCombo = FragmentBoss.tagJoiner(tagTitle, containerViewId, dbRecordId);
            // If the fragment being backed out of is the mainFragment photo list, bury it instead
            // of removing it. If not, pop it.
            if (fm.getBackStackEntryAt(backStackCount - 1).getName().equals(tagCombo)) {
                FragmentBoss.buryFragmentInBackStack(fm, tagCombo);
            } else {
                FragmentBoss.popBackStack(fm);
            }
        } else {
            // If all else fails, call the super.onBackPressed() method.
            super.onBackPressed();
        }
        // Redundancy to call the resulting top fragment's onResume() method.
        FragmentBoss.topFragmentOnResume(fm);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceNow = true;
        super.onSaveInstanceState(savedInstanceState);
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

    public void showMainFragment() {
        String tagTitle = getString(R.string.app_name);
        int containerViewId = R.id.mainContainer;
        int dbRecordId = -1;
        String tagCombo = FragmentBoss.tagJoiner(tagTitle, containerViewId, dbRecordId);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = MainFragment.newInstance(dbRecordId);
        FragmentBoss.replaceFragmentInContainer(
                containerViewId,
                fm,
                fragment,
                tagCombo
        );
    }

    public void resurfaceView(int containerViewId) {
        View v = findViewById(containerViewId);
        v.bringToFront();
    }

    private void updateSupportActionBarTitle(String tag) {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(tag);
        }
    }

}

