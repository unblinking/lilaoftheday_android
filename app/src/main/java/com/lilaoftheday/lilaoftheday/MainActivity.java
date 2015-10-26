package com.lilaoftheday.lilaoftheday;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

public class MainActivity extends Activity {

    Context context;
    CatListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        // Initialize default preference values.
        PreferenceManager.setDefaultValues(
                context,
                R.xml.preferences,
                false // only if this method has never been called in the past
        );

        /* Using a toolbar as the actionbar */
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setActionBar(toolbar);

        /* The awesome recycler view */
        RecyclerView rv;
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        StaggeredGridLayoutManager sglm;
        sglm = new StaggeredGridLayoutManager(3, 1);
        rv.setLayoutManager(sglm);
        mAdapter = new CatListAdapter(this);
        rv.setAdapter(mAdapter);
        rv.setItemAnimator(new DefaultItemAnimator());

    }

    /* TODO: Uncomment at publication time with production ad unit ID
    // Here is AdMob
    AdView mAdView = (AdView) findViewById(R.id.adView);
    AdRequest adRequest = new AdRequest.Builder().build();
    mAdView.loadAd(adRequest);
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /* Inflate the menu; add items to the tool bar */
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
            intent.setClassName(this, "com.lilaoftheday.lilaoftheday.PreferencesActivity");
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_notification_check) {
            AlarmChecker alarmChecker;
            alarmChecker = new AlarmChecker();
            alarmChecker.checkAlarm(context);
        }

        return super.onOptionsItemSelected(item);

    }

}

