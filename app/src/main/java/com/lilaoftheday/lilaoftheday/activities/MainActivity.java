package com.lilaoftheday.lilaoftheday.activities;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.fragments.MainFragment;
import com.lilaoftheday.lilaoftheday.models.BackStack;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*CatListAdapter catListAdapter;*/
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

        // Create a recycler view.
        /*RecyclerView recyclerView;
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        StaggeredGridLayoutManager staggeredGridLayoutManager;
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        catListAdapter = new CatListAdapter(this);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            recyclerView.setAdapter(catListAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }*/

        // Only do this stuff when the activity is started for the very first time.
        if (savedInstanceState == null) {
            mainFragment = MainFragment.newInstance();
            replaceFragmentInContainer(
                    R.id.mainContainer,
                    mainFragment,
                    "Home"
            );
        } else {
            // Do nothing.
            // If savedInstanceState is not null, the activity has been recreated from a saved
            // instance so we don't want to do this stuff again.
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
            return true;
        }
        /*if (id == R.id.action_notification_check) {
            AlarmChecker alarmChecker;
            alarmChecker = new AlarmChecker();
            alarmChecker.checkAlarm(getApplicationContext());
        }*/
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragmentInContainer(int containerViewId, Fragment fragment, String tagName) {

        // If the fragment is already in the fragment manager
        if (getSupportFragmentManager().findFragmentByTag(tagName) != null) {
            resurfaceFragmentInBackStack(tagName);
        } else {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(
                    containerViewId,
                    fragment,
                    tagName // Tag name, to later retrieve the fragment with.
            );
            fragmentTransaction.addToBackStack(tagName);
            fragmentTransaction.commit();
        }

        getSupportFragmentManager().executePendingTransactions();

        /*updateToolbarTitle();*/

    }

    public void resurfaceFragmentInBackStack(String tagName) {

        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();

        // Create our own ArrayList of the current back stack entries
        ArrayList<BackStack> backStackArrayList = new ArrayList<>();
        // Populate our own ArrayList of back stack entries
        for (int entry = 0; entry < backStackEntryCount; entry++) {

            BackStack backStack;
            backStack = new BackStack();

            String tag = getSupportFragmentManager().getBackStackEntryAt(entry).getName();
            Fragment frag = getSupportFragmentManager().findFragmentByTag(tag);
            int contViewId = R.id.mainContainer;

            backStack.setTagName(tag);
            backStack.setFragment(frag);
            backStack.setContainerViewId(contViewId);

            backStackArrayList.add(backStack);

        }

        int backStackArrayListSize = backStackArrayList.size();

        // Clear the fragment manager back stack completely
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry firstEntry = getSupportFragmentManager().getBackStackEntryAt(0);
            getSupportFragmentManager().popBackStackImmediate(
                    firstEntry.getId(),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
            );
        }

        // Remove all fragments from the fragment manager
        for (int entry = 0; entry < backStackArrayListSize; entry++) {
            BackStack backStack;
            backStack = backStackArrayList.get(entry);

            Fragment entryFragment = backStack.getFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(entryFragment);
            fragmentTransaction.commit();
        }

        // Add fragments back, starting with anything that is not the desired fragment
        for (int entry = 0; entry < backStackArrayListSize; entry++) {
            BackStack backStack;
            backStack = backStackArrayList.get(entry);

            int entryContainerViewId = backStack.getContainerViewId();
            Fragment entryFragment = backStack.getFragment();
            String entryTagName = backStack.getTagName();

            if (!entryTagName.equals(tagName)) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(
                        entryContainerViewId,
                        entryFragment,
                        entryTagName
                );
                fragmentTransaction.addToBackStack(entryTagName);
                fragmentTransaction.commit();
            }
        }

        // Now add the desired fragment back, so that it is on top of the back stack
        for (int entry = 0; entry < backStackArrayListSize; entry++) {
            BackStack backStack;
            backStack = backStackArrayList.get(entry);

            int entryContainerViewId = backStack.getContainerViewId();
            Fragment entryFragment = backStack.getFragment();
            String entryTagName = backStack.getTagName();

            if (entryTagName.equals(tagName)) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(
                        entryContainerViewId,
                        entryFragment,
                        entryTagName
                );
                fragmentTransaction.addToBackStack(entryTagName);
                fragmentTransaction.commit();
            }
        }

    }

}

