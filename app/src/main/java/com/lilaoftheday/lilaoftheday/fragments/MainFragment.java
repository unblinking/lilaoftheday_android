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
        view = inflater.inflate(R.layout.fragment_main, container, false);
        catListAdapter = new CatListAdapter(context);

        RecyclerView rv;
        rv = (RecyclerView) view.findViewById(R.id.rv);
        StaggeredGridLayoutManager sglm;
        sglm = new StaggeredGridLayoutManager(3, 1);
        if (rv != null) {
            rv.setHasFixedSize(true);
            rv.setLayoutManager(sglm);
            rv.setAdapter(catListAdapter);
            rv.setItemAnimator(new DefaultItemAnimator());
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

