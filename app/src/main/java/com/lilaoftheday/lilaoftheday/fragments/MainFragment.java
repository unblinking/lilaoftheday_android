package com.lilaoftheday.lilaoftheday.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.activities.MainActivity;
import com.lilaoftheday.lilaoftheday.adapters.CatListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    View view;
    MainActivity mainActivity;

    long dbRecordId;
    CatListAdapter catListAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);

        mainActivity = (MainActivity) getActivity();

        if (mainActivity != null && mainActivity.getSupportActionBar() != null) {
            ActionBar sab = mainActivity.getSupportActionBar();
            boolean landscape = mainActivity.getResources().getBoolean(R.bool.is_landscape);
            if (!landscape) {
                sab.setDisplayHomeAsUpEnabled(false);
                sab.setDisplayShowHomeEnabled(false);
            } else {
                sab.setDisplayHomeAsUpEnabled(false);
                sab.setDisplayShowHomeEnabled(false);
            }

        }
        setHasOptionsMenu(true);
        getFragmentArguments();

        catListAdapter = new CatListAdapter(getContext());
        RecyclerView rv;
        rv = (RecyclerView) view.findViewById(R.id.rv);
        StaggeredGridLayoutManager sglm;
        sglm = new StaggeredGridLayoutManager(4, 1);
        if (rv != null) {
            rv.setHasFixedSize(true);
            rv.setLayoutManager(sglm);
            rv.setAdapter(catListAdapter);
            rv.setItemAnimator(new DefaultItemAnimator());
        }

        return view;

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (menu != null) {
            menu.clear(); // Clear the existing menu.
        }
        super.onPrepareOptionsMenu(menu);
        mainActivity.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void onResume() {
        if (((MainActivity) getActivity()) != null) {
            mainActivity = (MainActivity) getActivity();
            mainActivity.resurfaceView(R.id.mainContainer);
        }
        // Update the action bar title and menu.
        if (mainActivity != null && mainActivity.getSupportActionBar() != null) {
            ActionBar sab = mainActivity.getSupportActionBar();

            boolean landscape = mainActivity.getResources().getBoolean(R.bool.is_landscape);
            if (!landscape) {
                sab.setTitle(R.string.fragmentTitleMain);
                sab.setDisplayHomeAsUpEnabled(false);
                sab.setDisplayShowHomeEnabled(false);
            } else {
                sab.setTitle(R.string.fragmentTitleMain);
                sab.setDisplayHomeAsUpEnabled(false);
                sab.setDisplayShowHomeEnabled(false);
            }
            sab.invalidateOptionsMenu();
        }
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        // Do nothing.
    }

    public static MainFragment newInstance(int dbRecordID){
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("dbRecordID", dbRecordID);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void getFragmentArguments() {
        Bundle args = getArguments();
        if (args != null && args.containsKey("dbRecordID")){
            dbRecordId = args.getLong("dbRecordID", 0);
        }
    }

}

