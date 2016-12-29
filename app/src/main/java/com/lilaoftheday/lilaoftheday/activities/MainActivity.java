/*
 * Copyright (C) 2015-2016 Joshua Gray <joshua@joshgray.com>. All Rights Reserved.
 * Proprietary and confidential. Unauthorized access strictly prohibited.
 */

package com.lilaoftheday.lilaoftheday.activities;

import android.content.res.Configuration;
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
import com.lilaoftheday.lilaoftheday.fragments.GridFragment;
import com.lilaoftheday.lilaoftheday.fragments.PreferenceFragment;
import com.nothingworksright.fragmentboss.FragmentBoss;

/**
 * Main class of the Lila of the day application.
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    /*
     * TODO: Write unit tests.
     * https://developer.android.com/training/testing/unit-testing/local-unit-tests.html
     */

    public Boolean savedInstanceNow = false;
    public int screenSize;

    /**
     * Starts the Lila of the day application.
     *
     * Sets the main content view, loads user preferences, creates the toolbar, shows the grid
     * fragment, and determines the device screen size.
     *
     * @param savedInstanceState Bundle: if the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). Note: Otherwise
     *                           it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        savedInstanceNow = false;
        screenSize = getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;

        PreferenceManager.setDefaultValues(
                this, // Context
                R.xml.preferences, // Resource ID
                false // only if this method has never been called in the past
        );

        setContentView(R.layout.activity_main);

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        updateSupportActionBarTitle(getString(R.string.app_name));

        if (savedInstanceState == null) {
            showGridFragment();
        }

    }

    /**
     * Takes action when the user pressed the back button.
     *
     *
     */
    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        int backStackCount = fm.getBackStackEntryCount();
        // If there's only one fragment left open, finish() the activity. If not, proceed.
        if (backStackCount == 1 && !savedInstanceNow) {
            finish();
        } else if (backStackCount > 1 && !savedInstanceNow) {
            // Details to identify the grid fragment.
            int containerViewId = R.id.mainContainer;
            String tagTitle = getString(R.string.app_name);
            int dbRecordId = -1;
            String tagCombo = FragmentBoss.tagJoiner(tagTitle, containerViewId, dbRecordId);
            // If the fragment being backed out of is the grid fragment, bury it instead
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

            int containerViewId = R.id.photoContainer;
            String tagTitle = getString(R.string.action_preferences);
            int dbRecordId = -1;
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = new PreferenceFragment();
            String tagCombo = FragmentBoss.tagJoiner(tagTitle, containerViewId, dbRecordId);
            FragmentBoss.replaceFragmentInContainer(
                    containerViewId,
                    fm,
                    fragment,
                    tagCombo
            );

            return true;
        }
        // Notification check for debugging
        /*if (id == R.id.action_notification_check) {
            AlarmChecker alarmChecker;
            alarmChecker = new AlarmChecker();
            alarmChecker.checkAlarm(getApplicationContext());
        }*/
        /*return super.onOptionsItemSelected(item);*/
        return false;
    }

    public void showGridFragment() {

        String tagTitle = getString(R.string.app_name);
        int containerViewId = R.id.mainContainer;
        int dbRecordId = -1;
        String tagCombo = FragmentBoss.tagJoiner(tagTitle, containerViewId, dbRecordId);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = GridFragment.newInstance(dbRecordId);

        FragmentBoss.replaceFragmentInContainer(containerViewId, fm, fragment, tagCombo);

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

