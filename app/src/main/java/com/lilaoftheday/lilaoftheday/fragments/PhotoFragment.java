package com.lilaoftheday.lilaoftheday.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lilaoftheday.lilaoftheday.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    Context context;

    View view;
    ImageView imageViewCatPhoto;
    int currentImageResourceId;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getContext();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_photo, container, false);

        imageViewCatPhoto = (ImageView) view.findViewById(R.id.photo);

        Bundle args = getArguments();
        if (args != null && args.containsKey("imageResourceId")){
            int imageResourceId = args.getInt("imageResourceId", 0);
            if (imageResourceId > 0){
                currentImageResourceId = imageResourceId;
            }
        }

        imageViewCatPhoto.setImageResource(currentImageResourceId);

        return view;

    }

    @Override
    public void onClick(View view) {
        // Do nothing.
    }

    public static PhotoFragment newInstance(int imageResourceId){
        PhotoFragment fragment = new PhotoFragment();
        if (imageResourceId > 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("imageResourceId", imageResourceId);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

}

