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

    /*MainActivity mainActivity;*/

    Context context;

    View view;
    ImageView imageViewCatPhoto;
    int currentImageResourceId;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*mainActivity = (MainActivity) getActivity();*/

        context = getContext();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_photo, container, false);

        imageViewCatPhoto = (ImageView) view.findViewById(R.id.photo);

        getCurrentImageResourceId();

        /*String imageName = "lila_back_blue_curtains";
        int imageResourceId = Utilities.getDrawableResourceId(context, imageName);*/

        imageViewCatPhoto.setImageResource(currentImageResourceId);

        return view;

    }

    /*@Override
    public void onResume() {
        super.onResume();
        // Only populate fields if current data exists, and only do it once.
        if (currentImageResourceId > 0){

        }
    }*/

    @Override
    public void onClick(View view) {

    }

    /*public int getImageResourceId(String imageName) {
        return mainActivity.getResources().getIdentifier(
                imageName,
                "drawable",
                mainActivity.getPackageName()
        );
    }*/

    public static PhotoFragment newInstance(int imageResourceId){
        PhotoFragment fragment = new PhotoFragment();
        if (imageResourceId > 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("imageResourceId", imageResourceId);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    private void getCurrentImageResourceId(){
        Bundle args = getArguments();
        if (args != null && args.containsKey("imageResourceId")){
            int imageResourceId = args.getInt("imageResourceId", 0);
            if (imageResourceId > 0){
                currentImageResourceId = imageResourceId;
            }
        }
    }

}

