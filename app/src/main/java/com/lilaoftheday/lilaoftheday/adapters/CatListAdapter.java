package com.lilaoftheday.lilaoftheday.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.activities.MainActivity;
import com.lilaoftheday.lilaoftheday.data.CatArray;
import com.lilaoftheday.lilaoftheday.fragments.PhotoFragment;
import com.lilaoftheday.lilaoftheday.models.Cat;
import com.lilaoftheday.lilaoftheday.utilities.Utilities;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CatListAdapter extends RecyclerView.Adapter<CatListAdapter.CatViewHolder> {

    Context context;
    ArrayList<Cat> catArrayList = new CatArray().catArray();

    public CatListAdapter(Context context) {

        this.context = context;

    }

    public class CatViewHolder extends RecyclerView.ViewHolder {

        public CardView cv;
        public ImageView catPhoto;

        public CatViewHolder(View itemView) {
            super(itemView);
            this.cv = (CardView) itemView.findViewById(R.id.cv);
            this.catPhoto = (ImageView) itemView.findViewById(R.id.catPhoto);
        }

    }

    @Override
    public int getItemCount () {

        return catArrayList.size();

    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv, parent, false);
        return new CatViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final CatViewHolder holder, final int position) {

        final Cat cat = catArrayList.get(position);
        String imageName = cat.getPhotoName();
        final int imageResourceID = Utilities.getDrawableResourceId(context, imageName);

        if (imageResourceID != 0) {
            Picasso.with(context)
                    .load(imageResourceID)
                    .resize(200,200)
                    .centerInside()
                    .into(holder.catPhoto);
        }

        holder.catPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (MainActivity) context;
                FragmentManager fragmentManager = activity.getSupportFragmentManager();

                Utilities.replaceFragmentInContainer(
                        R.id.photoContainer,
                        fragmentManager,
                        PhotoFragment.newInstance(imageResourceID),
                        cat.getPhotoName()
                );

            }

        });
        /*setAnimation(holder.cv, position);*/
    }

    /*private int lastPosition = -1;
    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }*/

}

