package com.lilaoftheday.lilaoftheday;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreferencesFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource.
        addPreferencesFromResource(R.xml.preferences);

        context = getActivity();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        // If the preference that changed was for daily notifications.
        if (key.equals("receive_daily_notifications")) {

            SharedPreferences sharedPref;
            sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

            // What is the new daily notification setting? true or false?
            boolean notifyPref = sharedPref.getBoolean("receive_daily_notifications", false);
            // The daily notification setting just got turned on.
            if (notifyPref) {
                AlarmScheduler alarmScheduler;
                alarmScheduler = new AlarmScheduler();
                alarmScheduler.scheduleAlarm(context);
                /*Toast.makeText(
                        context,
                        "The alarm for notifications is now on..",
                        Toast.LENGTH_SHORT
                ).show();*/

            }
            // The daily notification setting just got turned off.
            else {
                AlarmCanceler alarmCanceler;
                alarmCanceler = new AlarmCanceler();
                alarmCanceler.cancelAlarm(context);
                /*Toast.makeText(
                        context,
                        "The alarm for notifications is now off.",
                        Toast.LENGTH_SHORT
                ).show();*/
            }
        }
    }

    // Register the shared preferences listener on resume to listen for changes.
    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen()
                .getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    // Unregister the listener on pause.
    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen()
                .getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

}

