package com.lilaoftheday.lilaoftheday.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.activities.MainActivity;
import com.lilaoftheday.lilaoftheday.alarms.AlarmCanceler;
import com.lilaoftheday.lilaoftheday.alarms.AlarmScheduler;
import com.lilaoftheday.lilaoftheday.utilities.FragmentBoss;
import com.lilaoftheday.lilaoftheday.utilities.Utilities;


public class PreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    MainActivity mainActivity;

    private Context context;
    int menuItemHome = Utilities.generateViewId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mainActivity = (MainActivity) getActivity();

        if (mainActivity != null && mainActivity.getSupportActionBar() != null) {
            ActionBar sab = mainActivity.getSupportActionBar();
            boolean landscape = mainActivity.getResources().getBoolean(R.bool.is_landscape);
            if (!landscape) {
                sab.setDisplayHomeAsUpEnabled(true);
                sab.setDisplayShowHomeEnabled(true);
            } else {
                sab.setDisplayHomeAsUpEnabled(false);
                sab.setDisplayShowHomeEnabled(false);
            }

        }
        setHasOptionsMenu(true);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (menu != null) {
            menu.clear(); // Clear the existing menu.
            // If not landscape, add a menu icon to retrieve the "home" fragment.
            if (!mainActivity.getResources().getBoolean(R.bool.is_landscape)) {
                Drawable iconHome = ContextCompat.getDrawable(
                        getContext(),
                        R.drawable.ic_home_white_48dp
                );
                iconHome.setColorFilter(
                        ContextCompat.getColor(getContext(), R.color.ColorAccent),
                        PorterDuff.Mode.SRC_ATOP
                );
                menu.add(Menu.NONE, menuItemHome, 0, R.string.actionHome)
                        .setIcon(iconHome)
                        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            }
        }
        super.onPrepareOptionsMenu(menu);
        mainActivity.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            mainActivity.onBackPressed();
            return true;
        } else if (itemId == menuItemHome) {
            mainActivity.showGridFragment();
            FragmentBoss.topFragmentOnResume(getFragmentManager());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String string) {

        // Load the preferences from an XML resource.
        addPreferencesFromResource(R.xml.preferences);

        /*context = getActivity();*/
        context = getPreferenceManager().getContext();

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        // If the preference that changed was for daily notifications.
        if (key.equals("receive_daily_notifications")) {

            SharedPreferences sharedPref;
            sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

            // What is the new daily notification setting? true or false?
            String preferenceKey = context.getResources().getString(R.string.preference_notifications_checkbox_key);
            boolean notifyPref = sharedPref.getBoolean(preferenceKey, false);

            if (notifyPref) { // The daily notification setting just got turned on.
                new AlarmScheduler().scheduleAlarm(context);
            } else { // The daily notification setting just got turned off.
                new AlarmCanceler().cancelAlarm(context);
            }

        }
    }

    @Override
    public void onResume() {
        if (((MainActivity) getActivity()) != null) {
            mainActivity = (MainActivity) getActivity();
            mainActivity.resurfaceView(R.id.photoContainer);
        }
        // Update the action bar title and menu.
        if (mainActivity != null && mainActivity.getSupportActionBar() != null) {
            ActionBar sab = mainActivity.getSupportActionBar();

            boolean landscape = mainActivity.getResources().getBoolean(R.bool.is_landscape);
            if (!landscape) {
                sab.setTitle(R.string.action_preferences);
                sab.setDisplayHomeAsUpEnabled(true);
                sab.setDisplayShowHomeEnabled(true);
            } else {
                sab.setTitle(R.string.fragmentTitleMain);
                sab.setDisplayHomeAsUpEnabled(false);
                sab.setDisplayShowHomeEnabled(false);
            }
            sab.invalidateOptionsMenu();
        }
        super.onResume();

        // Register the shared preferences listener on resume to listen for changes.
        getPreferenceScreen()
                .getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Unregister the listener on pause.
        getPreferenceScreen()
                .getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

}

