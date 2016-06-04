package com.lilaoftheday.lilaoftheday.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.adapters.CatListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    Context context;
    View view;
    CatListAdapter catListAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getContext();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);

        // Only do this stuff when the fragment is started for the very first time.
        if (savedInstanceState == null) {

            catListAdapter = new CatListAdapter(context, getActivity().getSupportFragmentManager());

            // Create a recycler view.
            RecyclerView recyclerView;
            recyclerView = (RecyclerView) view.findViewById(R.id.rv);
            StaggeredGridLayoutManager staggeredGridLayoutManager;
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);

            if (recyclerView != null) {
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                recyclerView.setAdapter(catListAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }

        } else {
            // Do nothing.
            // If savedInstanceState is not null, the activity has been recreated from a saved
            // instance so we don't want to do this stuff again.
        }

        return view;

    }

    @Override
    public void onClick(View view) {

    }

    public static MainFragment newInstance(){
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }

}

