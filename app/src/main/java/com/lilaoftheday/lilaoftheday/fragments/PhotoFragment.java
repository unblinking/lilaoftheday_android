package com.lilaoftheday.lilaoftheday.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.activities.MainActivity;
import com.lilaoftheday.lilaoftheday.utilities.FragmentBoss;
import com.lilaoftheday.lilaoftheday.utilities.Utilities;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment implements View.OnClickListener {

    View view;
    MainActivity mainActivity;

    int menuItemShowGrid = Utilities.generateViewId();

    long dbRecordId;
    ImageView imageViewCatPhoto;
    int imageResourceId;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_photo, container, false);

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

        imageViewCatPhoto = (ImageView) view.findViewById(R.id.photo);

        getFragmentArguments();

        imageResourceId = (int) dbRecordId;
        imageViewCatPhoto.setImageResource(imageResourceId);

        return view;

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (menu != null) {
            menu.clear(); // Clear the existing menu.
            // Add a home button if android is not using landscape resources.
            if (!mainActivity.getResources().getBoolean(R.bool.is_landscape)) {
                menu.add(Menu.NONE, menuItemShowGrid, 0, R.string.actionShowGrid)
                        .setIcon(R.drawable.ic_home_black_48dp)
                        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            }
        }
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            mainActivity.onBackPressed();
            return true;
        } else if (itemId == menuItemShowGrid) {
            mainActivity.showMainFragment();
            FragmentBoss.topFragmentOnResume(getFragmentManager());
            return true;
        } else {
            return false;
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
            sab.setTitle(R.string.fragmentTitlePhoto);
            boolean landscape = mainActivity.getResources().getBoolean(R.bool.is_landscape);
            if (!landscape) {
                sab.setDisplayHomeAsUpEnabled(true);
                sab.setDisplayShowHomeEnabled(true);
            } else {
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

    public static PhotoFragment newInstance(int dbRecordID){
        PhotoFragment fragment = new PhotoFragment();
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

