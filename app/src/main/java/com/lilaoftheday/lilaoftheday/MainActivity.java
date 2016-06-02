package com.lilaoftheday.lilaoftheday;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    CatListAdapter catListAdapter;

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

        // Create a recycler view.

        RecyclerView recyclerView;
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        StaggeredGridLayoutManager staggeredGridLayoutManager;
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);

        catListAdapter = new CatListAdapter(this);

        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            recyclerView.setAdapter(catListAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
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
            String className = "com.lilaoftheday.lilaoftheday.PreferencesActivity";
            intent.setClassName(getApplicationContext(), className);
            startActivity(intent);
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

